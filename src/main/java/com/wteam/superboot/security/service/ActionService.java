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
import com.wteam.superboot.security.entity.po.ActionPo;
import com.wteam.superboot.security.entity.po.AuthitemPo;
import com.wteam.superboot.security.entity.po.PermissionresourcemapPo;
import com.wteam.superboot.security.entity.po.ResourcePo;
import com.wteam.superboot.security.entity.po.ResourcetypePo;
import com.wteam.superboot.security.entity.vo.ActionListItemVo;
import com.wteam.superboot.security.repository.ActionRepository;
import com.wteam.superboot.security.repository.AuthitemRepository;
import com.wteam.superboot.security.repository.PermissionresourcemapRepository;
import com.wteam.superboot.security.repository.ResourceRepository;
import com.wteam.superboot.security.repository.ResourcetypeRepository;

/**
 * 接口Service类.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@Service
@Transactional
public class ActionService {

	/**
	 * 注入 Repository.
	 */
	@Autowired
	private ActionRepository actionRepository;
	@Autowired
	private ResourcetypeRepository resourcetypeRepository;
	@Autowired
	private ResourceRepository resourceRepository;
	@Autowired
	private AuthitemRepository authitemRepository;
	@Autowired
	private PermissionresourcemapRepository permissionresourcemapRepository;

	/**
	 * 接口分页附对应行为列表.
	 * 
	 * @param pageinfo
	 *            分页信息，不得为null.
	 * @param likePo
	 *            接口实体模糊查询信息，不得为null.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage pageAction(final PageinfoPo pageinfo, final ActionPo likePo) throws Exception {
		if (pageinfo == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (pageinfo.getSortFieldNames() == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (pageinfo.getOrder() == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		Page<ActionPo> pageResult = actionRepository.pageNonDeleteEntity(pageinfo, new ActionPo(), likePo);
		// 获取接口对应的行为列表
		ActionPo aPo = null;
		ResourcetypePo actionType = new ResourcetypePo();
		actionType.setResourcetypename("action");
		actionType = resourcetypeRepository.queryEntity(actionType);
		ResourcePo resource = null;
		PermissionresourcemapPo prmapPo = null;
		AuthitemPo pPo = null;
		ActionListItemVo aItemVo = null;
		List<AuthitemPo> permissionList = null;
		List<ActionListItemVo> itemVoList = new ArrayList<>();
		for (SuperPersistentObject po : pageResult.getContent()) {
			aPo = (ActionPo) po;
			aItemVo = new ActionListItemVo();
			aItemVo.setActionid(aPo.getActionid());
			aItemVo.setActionname(aPo.getActionname());
			aItemVo.setCreatetime(aPo.getCreatetime());

			resource = new ResourcePo();
			resource.setResourcetypeid(actionType.getResourcetypeid());
			resource.setRealid(aPo.getActionid());
			resource = resourceRepository.queryEntity(resource);

			prmapPo = new PermissionresourcemapPo();
			prmapPo.setResourceid(resource.getResourceid());
			permissionList = new ArrayList<>();
			for (PermissionresourcemapPo tampPmapPo : permissionresourcemapRepository.queryNonDeleteList(prmapPo)) {
				pPo = authitemRepository.getEntityById(AuthitemPo.class, tampPmapPo.getPermissionid());
				permissionList.add(pPo);
			}
			aItemVo.setPermissionList(permissionList);
			itemVoList.add(aItemVo);
		}

		Map<String, Object> parm = new HashMap<>();
		parm.put("pageList", itemVoList);
		parm.put("totalCount", pageResult.getTotalElements());

		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);
		return rs;
	}

	/**
	 * 添加接口.
	 * 
	 * @param action
	 *            接口实体.
	 * @param permissions
	 *            权限列表.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage addAction(final ActionPo action, final List<AuthitemPo> permissions, final UserPo currentUser)
			throws Exception {
		if (action == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		ResultMessage rs = null;

		ActionPo theAction = action;
		if (actionRepository.queryCount(theAction) == 0L) {
			actionRepository.addEntity(theAction, currentUser);

			ResourcetypePo actionType = new ResourcetypePo();
			actionType.setResourcetypename("action");
			actionType = resourcetypeRepository.queryEntity(actionType);
			ResourcePo resource = new ResourcePo();
			resource.setResourcetypeid(actionType.getResourcetypeid());
			resource.setRealid(theAction.getActionid());
			resourceRepository.addEntity(resource, currentUser);

			PermissionresourcemapPo prmap = null;
			for (AuthitemPo tempPermission : permissions) {
				prmap = new PermissionresourcemapPo();
				prmap.setPermissionid(tempPermission.getAuthitemid());
				prmap.setResourceid(resource.getResourceid());
				permissionresourcemapRepository.addEntity(prmap, currentUser);
			}
			rs = ResultHelper.result(ResultEnum.ADD_SUCCESS);
		} else {
			rs = ResultHelper.result(ResultEnum.MULTIPLE_NAME);
		}

		return rs;
	}

	/**
	 * 获取指定接口所属行为.
	 * 
	 * @param action
	 *            接口实体.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage getActionPermissionList(final ActionPo action) throws Exception {
		if (action == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		// 获取接口对应的行为列表
		ResourcetypePo actionType = new ResourcetypePo();
		actionType.setResourcetypename("action");
		actionType = resourcetypeRepository.queryEntity(actionType);
		ResourcePo resource = new ResourcePo();
		resource.setResourcetypeid(actionType.getResourcetypeid());
		resource.setRealid(action.getActionid());
		resource = resourceRepository.queryEntity(resource);

		PermissionresourcemapPo prmapPo = new PermissionresourcemapPo();
		prmapPo.setResourceid(resource.getResourceid());
		AuthitemPo pPo = null;
		List<AuthitemPo> permissionList = new ArrayList<AuthitemPo>();
		for (PermissionresourcemapPo tampPmapPo : permissionresourcemapRepository.queryNonDeleteList(prmapPo)) {
			pPo = authitemRepository.getEntityById(AuthitemPo.class, tampPmapPo.getPermissionid());
			permissionList.add(pPo);
		}

		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("permissionList", permissionList);

		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);
		return rs;
	}

	/**
	 * 编辑接口.
	 * 
	 * @param action
	 *            接口实体.
	 * @param addPermissions
	 *            添加的权限.
	 * @param subPermissions
	 *            删除的权限.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage editAction(final ActionPo action, final List<AuthitemPo> addPermissions,
			final List<AuthitemPo> subPermissions, final UserPo currentUser) throws Exception {
		if (action == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		ResultMessage rs = null;

		boolean isSameName = false;
		ActionPo theAction = null;
		theAction = actionRepository.getEntityById(ActionPo.class, action.getActionid());
		if (action.getActionname() != null) {
			theAction = new ActionPo();
			theAction.setActionname(action.getActionname());
			ActionPo tempAction = actionRepository.queryEntity(theAction);
			if (tempAction != null && tempAction.getActionid() != action.getActionid()) {
				isSameName = true;
			}
		}

		if (!isSameName) {
			theAction = actionRepository.getEntityById(ActionPo.class, action.getActionid());
			if (action.getActionname() != null) {
				theAction.setActionname(action.getActionname());
				actionRepository.editEntity(theAction, currentUser);
			}

			ResourcetypePo actionType = new ResourcetypePo();
			actionType.setResourcetypename("action");
			actionType = resourcetypeRepository.queryEntity(actionType);
			ResourcePo resource = new ResourcePo();
			resource.setResourcetypeid(actionType.getResourcetypeid());
			resource.setRealid(theAction.getActionid());
			resource = resourceRepository.queryEntity(resource);

			PermissionresourcemapPo prmapPo = null;
			for (AuthitemPo tempPermission : addPermissions) {
				prmapPo = new PermissionresourcemapPo();
				prmapPo.setResourceid(resource.getResourceid());
				prmapPo.setPermissionid(tempPermission.getAuthitemid());
				permissionresourcemapRepository.addEntity(prmapPo, currentUser);
			}
			for (AuthitemPo tempPermission : subPermissions) {
				prmapPo = new PermissionresourcemapPo();
				prmapPo.setResourceid(resource.getResourceid());
				prmapPo.setPermissionid(tempPermission.getAuthitemid());
				permissionresourcemapRepository.deleteEntity(prmapPo);
			}
			rs = ResultHelper.result(ResultEnum.EDIT_SUCCESS);
		} else {
			rs = ResultHelper.result(ResultEnum.MULTIPLE_NAME);
		}
		return rs;
	}

	/**
	 * 批量删除接口.
	 * 
	 * @param list
	 *            要删除的接口实体列表.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage deleteActionByList(final List<ActionPo> list) throws Exception {
		if (list == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		ResourcetypePo type = new ResourcetypePo();
		type.setResourcetypename("action");
		type = resourcetypeRepository.queryEntity(type);

		ResourcePo resource = null;
		PermissionresourcemapPo map = null;
		for (ActionPo po : list) {

			resource = new ResourcePo();
			resource.setRealid(po.getActionid());
			resource.setResourcetypeid(type.getResourcetypeid());
			if (resourceRepository.hasNonDeleteEntity(resource)) {
				resource = resourceRepository.queryEntity(resource);

				map = new PermissionresourcemapPo();
				map.setResourceid(resource.getResourceid());

				for (PermissionresourcemapPo po2 : permissionresourcemapRepository.queryList(map)) {
					permissionresourcemapRepository.deleteEntity(po2);
				}
				resourceRepository.deleteEntity(resource);
			}
			actionRepository.deleteEntity(po);
		}
		ResultMessage rs = ResultHelper.result(ResultEnum.DEL_SUCCESS);
		return rs;
	}

}
