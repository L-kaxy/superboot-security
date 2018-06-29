/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wteam.superboot.core.entity.po.PageinfoPo;
import com.wteam.superboot.core.entity.po.SuperPersistentObject;
import com.wteam.superboot.core.entity.po.UserPo;
import com.wteam.superboot.core.enums.ResultEnum;
import com.wteam.superboot.core.exception.SuperException;
import com.wteam.superboot.core.helper.ResultHelper;
import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.security.entity.po.AuthitemPo;
import com.wteam.superboot.security.entity.po.AuthitemmapPo;
import com.wteam.superboot.security.entity.po.PermissionresourcemapPo;
import com.wteam.superboot.security.entity.vo.RoleListItemVo;
import com.wteam.superboot.security.repository.AuthitemRepository;
import com.wteam.superboot.security.repository.AuthitemmapRepository;
import com.wteam.superboot.security.repository.PermissionresourcemapRepository;

/**
 * 权限条目Service类.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@Service
@Transactional
public class AuthitemService {

	/**
	 * 注入 Repository.
	 */
	@Autowired
	private AuthitemRepository authitemRepository;
	@Autowired
	private AuthitemmapRepository authitemmapRepository;
	@Autowired
	private PermissionresourcemapRepository permissionresourcemapRepository;

	/**
	 * 查询非删除权限条目分页列表.
	 * 
	 * @param pageinfo
	 *            分页信息，不得为null.
	 * @param aimPo
	 *            权限条目实体精确查询信息，不得为null.
	 * @param likePo
	 *            权限条目实体模糊查询信息，不得为null.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage pageAuthitem(final PageinfoPo pageinfo, final AuthitemPo authitem, final AuthitemPo likePo)
			throws Exception {
		if (pageinfo == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (pageinfo.getSortFieldNames() == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (pageinfo.getOrder() == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		AuthitemPo aimPo = (authitem == null ? new AuthitemPo() : authitem);

		Page<AuthitemPo> pageResult = authitemRepository.pageNonDeleteEntity(pageinfo, aimPo, likePo);

		List<AuthitemPo> resultList = new ArrayList<>();
		AuthitemPo tempPo = null;
		for (SuperPersistentObject po : pageResult.getContent()) {
			tempPo = (AuthitemPo) po;
			if (!"系统".equals(tempPo.getAuthitemname())) {
				resultList.add(tempPo);
			}
		}

		int minus = 2;
		if (aimPo.getAuthitemtype() != null) {
			minus = 1;
		}

		Map<String, Object> parm = new HashMap<>();
		parm.put("pageList", resultList);
		parm.put("totalCount", pageResult.getTotalElements() - minus);

		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);
		return rs;
	}

	/**
	 * 角色分页附对应行为列表.
	 * 
	 * @param pageinfo
	 *            分页信息，不得为null.
	 * @param likePo
	 *            接口实体模糊查询信息，不得为null.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage pageRole(final PageinfoPo pageinfo, final AuthitemPo authitem) throws Exception {
		if (pageinfo == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (pageinfo.getSortFieldNames() == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (pageinfo.getOrder() == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		AuthitemPo roleTypeParm = new AuthitemPo();
		roleTypeParm.setAuthitemtype(false);
		Page<AuthitemPo> pageResult = authitemRepository.pageNonDeleteEntity(pageinfo, roleTypeParm, authitem);
		// 获取角色对应的行为列表
		AuthitemPo rPo = null;
		AuthitemmapPo rpmapPo = null;
		AuthitemPo pPo = null;
		RoleListItemVo rItemPo = null;
		List<AuthitemPo> permissionList = null;
		List<RoleListItemVo> itemVoList = new ArrayList<>();
		for (SuperPersistentObject po : pageResult.getContent()) {
			rPo = (AuthitemPo) po;
			rItemPo = new RoleListItemVo();
			rItemPo.setRoleid(rPo.getAuthitemid());
			rItemPo.setAuthitemname(rPo.getAuthitemname());
			rItemPo.setAuthitemtype(rPo.getAuthitemtype());
			rItemPo.setCreatetime(rPo.getCreatetime());

			rpmapPo = new AuthitemmapPo();
			rpmapPo.setParentid(rPo.getAuthitemid());
			permissionList = new ArrayList<AuthitemPo>();
			for (AuthitemmapPo tampAmapPo : authitemmapRepository.queryNonDeleteList(rpmapPo)) {
				pPo = authitemRepository.getEntityById(AuthitemPo.class, tampAmapPo.getChildid());
				permissionList.add(pPo);
			}
			rItemPo.setPermissionList(permissionList);
			itemVoList.add(rItemPo);
		}

		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("pageList", itemVoList);
		parm.put("totalCount", pageResult.getTotalElements());

		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);
		return rs;
	}

	/**
	 * 添加角色.
	 * 
	 * @param role
	 *            角色实体.
	 * @param permissions
	 *            权限列表.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage addRole(final AuthitemPo role, final List<AuthitemPo> permissions, final UserPo currentUser)
			throws Exception {
		if (role == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		ResultMessage rs = null;

		List<AuthitemPo> thePermissions = permissions;
		if (thePermissions == null) {
			thePermissions = new ArrayList<AuthitemPo>();
		}

		AuthitemPo theRole = role;
		theRole.setAuthitemtype(false);
		if (authitemRepository.queryCount(theRole) == 0L) {
			authitemRepository.addEntity(theRole, currentUser);

			AuthitemmapPo amap = null;
			for (AuthitemPo tempPermission : thePermissions) {
				amap = new AuthitemmapPo();
				amap.setParentid(theRole.getAuthitemid());
				amap.setChildid(tempPermission.getAuthitemid());
				authitemmapRepository.addEntity(amap, currentUser);
			}
			rs = ResultHelper.result(ResultEnum.ADD_SUCCESS);
		} else {
			rs = ResultHelper.result(ResultEnum.MULTIPLE_NAME);
		}

		return rs;
	}

	/**
	 * 批量添加权限条目.
	 * 
	 * @param list
	 *            权限列表.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage addAuthitemByList(final List<AuthitemPo> list, final UserPo currentUser) throws Exception {
		if (list == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		ResultMessage rs = null;

		AuthitemPo valid = null;
		List<AuthitemPo> sameName = new ArrayList<AuthitemPo>();
		for (AuthitemPo po : list) {
			valid = new AuthitemPo();
			valid.setAuthitemtype(po.getAuthitemtype());
			valid.setAuthitemname(po.getAuthitemname());
			if (authitemRepository.hasNonDeleteEntity(valid)) {
				sameName.add(po);
			} else {
				authitemRepository.addEntity(valid, currentUser);
			}
		}

		rs = ResultHelper.result(ResultEnum.ADD_SUCCESS);

		if (!sameName.isEmpty()) {
			Map<String, Object> resultParm = new HashMap<String, Object>();
			resultParm.put("sameName", sameName);
			rs.setResultParm(resultParm);
		}

		return rs;
	}

	/**
	 * 批量编辑权限条目.
	 * 
	 * @param list
	 *            权限列表.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage editAuthitemByList(final List<AuthitemPo> list, final UserPo currentUser) throws Exception {
		if (list == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		ResultMessage rs = null;

		AuthitemPo valid = null;
		List<AuthitemPo> sameName = new ArrayList<AuthitemPo>();
		for (AuthitemPo po : list) {
			valid = new AuthitemPo();
			valid.setAuthitemtype(po.getAuthitemtype());
			valid.setAuthitemname(po.getAuthitemname());

			AuthitemPo tempValid = authitemRepository.queryEntity(valid);
			if (tempValid != null && tempValid.getAuthitemid() != po.getAuthitemid()) {
				sameName.add(po);
			} else {
				valid.setAuthitemid(po.getAuthitemid());
				authitemRepository.editEntity(valid, currentUser);
			}
		}

		rs = ResultHelper.result(ResultEnum.EDIT_SUCCESS);

		if (!sameName.isEmpty()) {
			Map<String, Object> resultParm = new HashMap<String, Object>();
			resultParm.put("sameName", sameName);
			rs.setResultParm(resultParm);
		}

		return rs;
	}

	/**
	 * 获取指定角色所属行为.
	 * 
	 * @param theRole
	 *            角色实体.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage getRolePermissionList(final AuthitemPo theRole) throws Exception {
		if (theRole == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		// 获取角色对应的行为列表
		AuthitemPo rPo = theRole;
		AuthitemmapPo rpmapPo = new AuthitemmapPo();
		rpmapPo.setParentid(rPo.getAuthitemid());
		AuthitemPo pPo = null;
		List<AuthitemPo> permissionList = new ArrayList<AuthitemPo>();
		for (AuthitemmapPo tampAmapPo : authitemmapRepository.queryNonDeleteList(rpmapPo)) {
			pPo = authitemRepository.getEntityById(AuthitemPo.class, tampAmapPo.getChildid());
			permissionList.add(pPo);
		}

		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("permissionList", permissionList);

		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);
		return rs;
	}

	/**
	 * 编辑角色.
	 * 
	 * @param role
	 *            角色实体.
	 * @param addPermissions
	 *            添加权限列表.
	 * @param subPermissions
	 *            删除权限列表.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage editRole(final AuthitemPo role, final List<AuthitemPo> addPermissions,
			final List<AuthitemPo> subPermissions, final UserPo currentUser) throws Exception {
		if (role == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		ResultMessage rs = null;

		List<AuthitemPo> theAddPermissions = addPermissions;
		if (theAddPermissions == null) {
			theAddPermissions = new ArrayList<AuthitemPo>();
		}

		List<AuthitemPo> theSubPermissions = subPermissions;
		if (theSubPermissions == null) {
			theSubPermissions = new ArrayList<AuthitemPo>();
		}

		boolean isSameName = false;
		AuthitemPo theRole = null;
		theRole = authitemRepository.getEntityById(AuthitemPo.class, role.getAuthitemid());
		if (role.getAuthitemname() != null) {
			theRole = new AuthitemPo();
			theRole.setAuthitemtype(false);
			theRole.setAuthitemname(role.getAuthitemname());
			AuthitemPo tempRole = authitemRepository.queryEntity(theRole);
			if (tempRole != null && tempRole.getAuthitemid() != role.getAuthitemid()) {
				isSameName = true;
			}
		}

		if (!isSameName) {
			theRole = authitemRepository.getEntityById(AuthitemPo.class, role.getAuthitemid());
			if (role.getAuthitemname() != null) {
				theRole.setAuthitemname(role.getAuthitemname());
				authitemRepository.editEntity(theRole, currentUser);
			}

			AuthitemmapPo amap = null;
			for (AuthitemPo tempPermission : theAddPermissions) {
				amap = new AuthitemmapPo();
				amap.setParentid(theRole.getAuthitemid());
				amap.setChildid(tempPermission.getAuthitemid());
				authitemmapRepository.addEntity(amap, currentUser);
			}
			for (AuthitemPo tempPermission : theSubPermissions) {
				amap = new AuthitemmapPo();
				amap.setParentid(theRole.getAuthitemid());
				amap.setChildid(tempPermission.getAuthitemid());
				authitemmapRepository.deleteEntity(amap);
			}
			rs = ResultHelper.result(ResultEnum.EDIT_SUCCESS);
		} else {
			rs = ResultHelper.result(ResultEnum.MULTIPLE_NAME);
		}
		return rs;
	}

	/**
	 * 批量删除角色.
	 * 
	 * @param list
	 *            要删除的角色列表.
	 * @param currentUser
	 *            当期用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage deleteRoleByList(final List<AuthitemPo> list, final UserPo currentUser) throws Exception {
		if (list == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		AuthitemmapPo rolepermission = null;
		for (AuthitemPo po : list) {

			rolepermission = new AuthitemmapPo();
			rolepermission.setParentid(po.getAuthitemid());
			if (authitemmapRepository.hasNonDeleteEntity(rolepermission)) {
				for (AuthitemmapPo po3 : authitemmapRepository.queryList(rolepermission)) {
					authitemmapRepository.deleteEntity(po3);
				}
			}
			authitemRepository.deleteEntity(po);
		}
		ResultMessage rs = ResultHelper.result(ResultEnum.DEL_SUCCESS);
		return rs;
	}

	/**
	 * 批量删除行为.
	 * 
	 * @param list
	 *            要删除的行为列表.
	 * @param currentUser
	 *            当期用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage deletePermissionByList(final List<AuthitemPo> list, final UserPo currentUser)
			throws Exception {
		if (list == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		PermissionresourcemapPo permissionresource = null;
		AuthitemmapPo rolepermission = null;
		for (AuthitemPo po : list) {

			permissionresource = new PermissionresourcemapPo();
			permissionresource.setPermissionid(po.getAuthitemid());
			if (permissionresourcemapRepository.hasNonDeleteEntity(permissionresource)) {
				for (PermissionresourcemapPo po2 : permissionresourcemapRepository.queryList(permissionresource)) {
					permissionresourcemapRepository.deleteEntity(po2);
				}
			}

			rolepermission = new AuthitemmapPo();
			rolepermission.setChildid(po.getAuthitemid());
			if (authitemmapRepository.hasNonDeleteEntity(rolepermission)) {
				for (AuthitemmapPo po3 : authitemmapRepository.queryList(rolepermission)) {
					authitemmapRepository.deleteEntity(po3);
				}
			}
			authitemRepository.deleteEntity(po);
		}

		ResultMessage rs = ResultHelper.result(ResultEnum.DEL_SUCCESS);

		return rs;
	}

	/**
	 * 行为分页.
	 * 
	 * @param pageinfo
	 *            分页信息.
	 * @param likePo
	 *            模糊查询对象.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage pagePermission(final PageinfoPo pageinfo, final AuthitemPo authitem) throws Exception {
		if (pageinfo == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (pageinfo.getSortFieldNames() == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (pageinfo.getOrder() == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		AuthitemPo permissionTypeParm = new AuthitemPo();
		permissionTypeParm.setAuthitemtype(true);
		Page<AuthitemPo> pageResult = authitemRepository.pageNonDeleteEntity(pageinfo, permissionTypeParm, authitem);

		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("pageList", pageResult.getContent());
		parm.put("totalCount", pageResult.getTotalElements());

		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);
		return rs;
	}

}
