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
import com.wteam.superboot.security.entity.po.ActionPo;
import com.wteam.superboot.security.entity.po.AuthitemPo;
import com.wteam.superboot.security.entity.po.PermissionresourcemapPo;
import com.wteam.superboot.security.entity.po.ResourcePo;
import com.wteam.superboot.security.entity.po.ResourcetypePo;
import com.wteam.superboot.security.entity.vo.AuthitemVo;
import com.wteam.superboot.security.repository.ActionRepository;
import com.wteam.superboot.security.repository.AuthitemRepository;
import com.wteam.superboot.security.repository.PermissionresourcemapRepository;
import com.wteam.superboot.security.repository.ResourceRepository;
import com.wteam.superboot.security.repository.ResourcetypeRepository;
import com.wteam.superboot.security.view.po.ActionListItemPo;
import com.wteam.superboot.security.view.vo.ActionListItemVo;

/**
 * 接口Service类.
 * 
 * @author 罗佳欣
 *
 */
@Service
@Transactional
public class ActionService {

	/**
	 * 注入actionRepository.
	 */
	@Autowired
	private ActionRepository actionRepository;

	/**
	 * 注入resourcetypeRepository.
	 */
	@Autowired
	private ResourcetypeRepository resourcetypeRepository;

	/**
	 * 注入resourceRepository.
	 */
	@Autowired
	private ResourceRepository resourceRepository;

	/**
	 * 注入authitemRepository.
	 */
	@Autowired
	private AuthitemRepository authitemRepository;

	/**
	 * 注入permissionresourcemapRepository.
	 */
	@Autowired
	private PermissionresourcemapRepository permissionresourcemapRepository;

	/**
	 * 接口分页附对应行为列表.
	 * 
	 * @param pageinfo
	 *            分页信息，不得为null.
	 * @param likePo
	 *            接口实体模糊查询信息，不得为null.
	 * @return
	 * @throws Exception
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
		ActionListItemPo aItemPo = null;
		List<AuthitemPo> permissionList = null;
		List<ActionListItemPo> itemPoList = new ArrayList<ActionListItemPo>();
		for (SuperPersistentObject po : pageResult.getContent()) {
			aPo = (ActionPo) po;
			aItemPo = new ActionListItemPo();
			aItemPo.setActionid(aPo.getActionid());
			aItemPo.setActionname(aPo.getActionname());
			aItemPo.setCreatetime(aPo.getCreatetime());

			resource = new ResourcePo();
			resource.setResourcetypeid(actionType.getResourcetypeid());
			resource.setRealid(aPo.getActionid());
			resource = resourceRepository.queryEntity(resource);

			prmapPo = new PermissionresourcemapPo();
			prmapPo.setResourceid(resource.getResourceid());
			permissionList = new ArrayList<AuthitemPo>();
			for (PermissionresourcemapPo tampPmapPo : permissionresourcemapRepository.queryNonDeleteList(prmapPo)) {
				pPo = authitemRepository.getEntityById(AuthitemPo.class, tampPmapPo.getPermissionid());
				permissionList.add(pPo);
			}
			aItemPo.setPermissionList(permissionList);
			itemPoList.add(aItemPo);
		}

		Map<String, Object> parm = new HashMap<String, Object>();

		List<ActionListItemVo> resultList = new ArrayList<ActionListItemVo>();
		ActionListItemVo tempVo = null;
		for (ActionListItemPo po : itemPoList) {
			tempVo = new ActionListItemVo();
			tempVo.poViewToVo(po);
			resultList.add(tempVo);
		}

		parm.put("pageList", resultList);
		parm.put("totalCount", pageResult.getTotalElements());
		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);

		return rs;
	}

	/**
	 * 添加接口.
	 * 
	 * @return
	 * @throws Exception
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
	 * @return
	 * @throws Exception
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
	 * 编辑接口.
	 * 
	 * @return
	 * @throws Exception
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
			if (actionRepository.queryCount(theAction) != 0L) {
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
	 * @return
	 * @throws Exception
	 */
	public ResultMessage deleteActionByList(final List<ActionPo> list, final UserPo currentUser) throws Exception {
		if (list == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
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
