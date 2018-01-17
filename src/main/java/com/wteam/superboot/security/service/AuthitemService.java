/**
 * Copyright (c) 2007-2017 Wteam.  All rights reserved. 网维网络技术创业团队 版权所有.
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
import com.wteam.superboot.security.entity.vo.AuthitemVo;
import com.wteam.superboot.security.repository.AuthitemRepository;
import com.wteam.superboot.security.repository.AuthitemmapRepository;
import com.wteam.superboot.security.repository.PermissionresourcemapRepository;
import com.wteam.superboot.security.view.po.RoleListItemPo;
import com.wteam.superboot.security.view.vo.RoleListItemVo;

/**
 * 权限条目Service类.
 * 
 * @author 罗佳欣
 *
 */
@Service
@Transactional
public class AuthitemService {

	/**
	 * 注入authitemRepository.
	 */
	@Autowired
	private AuthitemRepository authitemRepository;

	/**
	 * 注入authitemRepository.
	 */
	@Autowired
	private AuthitemmapRepository authitemmapRepository;

	/**
	 * 注入permissionresourcemapRepository.
	 */
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
	 * @return
	 * @throws Exception
	 */
	public ResultMessage pageAuthitem(final PageinfoPo pageinfo, final AuthitemPo aimPo, final AuthitemPo likePo)
			throws Exception {
		if (pageinfo == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (aimPo == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (pageinfo.getSortFieldNames() == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (pageinfo.getOrder() == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		Page<AuthitemPo> pageResult = authitemRepository.pageNonDeleteEntity(pageinfo, aimPo, likePo);
		Map<String, Object> parm = new HashMap<String, Object>();

		List<AuthitemVo> resultList = new ArrayList<AuthitemVo>();
		AuthitemVo tempVo = null;
		for (SuperPersistentObject po : pageResult.getContent()) {
			tempVo = new AuthitemVo();
			tempVo.poToVo(po);
			if (!"系统".equals(tempVo.getAuthitemname())) {
				resultList.add(tempVo);
			}
		}

		int minus = 2;
		if (aimPo.getAuthitemtype() != null) {
			minus = 1;
		}

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
	 * @return
	 * @throws Exception
	 */
	public ResultMessage pageRole(final PageinfoPo pageinfo, final AuthitemPo likePo) throws Exception {
		if (pageinfo == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (likePo == null) {
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
		Page<AuthitemPo> pageResult = authitemRepository.pageNonDeleteEntity(pageinfo, roleTypeParm, likePo);
		// 获取角色对应的行为列表
		AuthitemPo rPo = null;
		AuthitemmapPo rpmapPo = null;
		AuthitemPo pPo = null;
		RoleListItemPo rItemPo = null;
		List<AuthitemPo> permissionList = null;
		List<RoleListItemPo> itemPoList = new ArrayList<RoleListItemPo>();
		for (SuperPersistentObject po : pageResult.getContent()) {
			rPo = (AuthitemPo) po;
			rItemPo = new RoleListItemPo();
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
			itemPoList.add(rItemPo);
		}

		Map<String, Object> parm = new HashMap<String, Object>();

		List<RoleListItemVo> resultList = new ArrayList<RoleListItemVo>();
		RoleListItemVo tempVo = null;
		for (RoleListItemPo po : itemPoList) {
			tempVo = new RoleListItemVo();
			tempVo.poViewToVo(po);
			resultList.add(tempVo);
		}

		parm.put("pageList", resultList);
		parm.put("totalCount", pageResult.getTotalElements());
		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);

		return rs;
	}

	/**
	 * 添加角色.
	 * 
	 * @return
	 * @throws Exception
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
	 * @return
	 * @throws Exception
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
	 * @return
	 * @throws Exception
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
			if (authitemRepository.hasNonDeleteEntity(valid)) {
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
	 * @return
	 * @throws Exception
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

		List<AuthitemVo> resultList = new ArrayList<AuthitemVo>();
		AuthitemVo tempVo = null;
		for (AuthitemPo po : permissionList) {
			tempVo = new AuthitemVo();
			tempVo.poToVo(po);
			resultList.add(tempVo);
		}

		parm.put("permissionList", resultList);
		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);

		return rs;
	}

	/**
	 * 编辑角色.
	 * 
	 * @return
	 * @throws Exception
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
			if (authitemRepository.queryCount(theRole) != 0L) {
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
	 * @return
	 * @throws Exception
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
	 * @return
	 * @throws Exception
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
	 * @return
	 * @throws Exception
	 */
	public ResultMessage pagePermission(final PageinfoPo pageinfo, final AuthitemPo likePo) throws Exception {
		if (pageinfo == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (likePo == null) {
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
		Page<AuthitemPo> pageResult = authitemRepository.pageNonDeleteEntity(pageinfo, permissionTypeParm, likePo);

		Map<String, Object> parm = new HashMap<String, Object>();

		List<AuthitemVo> resultList = new ArrayList<AuthitemVo>();
		AuthitemVo tempVo = null;
		for (SuperPersistentObject po : pageResult.getContent()) {
			tempVo = new AuthitemVo();
			tempVo.poToVo(po);
			resultList.add(tempVo);
		}

		parm.put("pageList", resultList);
		parm.put("totalCount", pageResult.getTotalElements());
		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);

		return rs;
	}

}
