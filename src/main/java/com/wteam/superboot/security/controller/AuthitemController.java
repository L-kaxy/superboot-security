/**
 * Copyright (c) 2007-2017 Wteam.  All rights reserved. 网维网络技术创业团队 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wteam.superboot.core.entity.po.UserPo;
import com.wteam.superboot.core.enums.ResultEnum;
import com.wteam.superboot.core.helper.JsonHelper;
import com.wteam.superboot.core.helper.ResultHelper;
import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.security.controller.Param.SecurityParam;
import com.wteam.superboot.security.entity.po.AuthitemPo;
import com.wteam.superboot.security.service.AuthitemService;

/**
 * 权限Controller.
 * 
 * @authod 罗佳欣
 * 
 */
@RestController
public class AuthitemController {

	@Autowired
	private AuthitemService service;

	@PostMapping("/addAuthitemByList")
	public ResultMessage addAuthitemByList(@RequestBody SecurityParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		List<AuthitemPo> list = JsonHelper.jsonToBeanList(param.getAuthitemList(), AuthitemPo.class);
		return service.addAuthitemByList(list, currentUser);
	}

	@PostMapping("/editAuthitemByList")
	public ResultMessage editAuthitemByList(@RequestBody SecurityParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		List<AuthitemPo> list = JsonHelper.jsonToBeanList(param.getAuthitemList(), AuthitemPo.class);
		return service.editAuthitemByList(list, currentUser);
	}

	@PostMapping("/deleteAuthitemByList")
	public ResultMessage deleteAuthitemByList(@RequestBody SecurityParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		ResultMessage resultMessage = null;

		List<AuthitemPo> list = JsonHelper.jsonToBeanList(param.getAuthitemList(), AuthitemPo.class);

		boolean hasPermission = false;
		boolean hasRole = false;
		for (AuthitemPo po : list) {
			if (!hasPermission && po.getAuthitemtype()) {
				hasPermission = true;
			}
			if (!hasRole && !po.getAuthitemtype()) {
				hasRole = true;
			}
		}

		if (hasPermission == hasRole) {
			resultMessage = ResultHelper.result(ResultEnum.PARAM_ERROR);
		} else {
			if (hasPermission) {
				resultMessage = service.deletePermissionByList(list, currentUser);
			} else if (hasRole) {
				resultMessage = service.deleteRoleByList(list, currentUser);
			}
		}

		return resultMessage;
	}

	@PostMapping("/pageAuthitem")
	public ResultMessage pageAuthitem(@RequestBody SecurityParam param) throws Exception {
		AuthitemPo aimPo = param.getAuthitem().voToPo(AuthitemPo.class);
		if (aimPo == null) {
			aimPo = new AuthitemPo();
		}
		AuthitemPo likePo = param.getAuthitem2().voToPo(AuthitemPo.class);
		if (likePo == null) {
			likePo = new AuthitemPo();
		}
		return service.pageAuthitem(param.getPageinfo(), aimPo, likePo);
	}

	@PostMapping("/pagePermission")
	public ResultMessage pagePermission(@RequestBody SecurityParam param) throws Exception {
		AuthitemPo likePo = param.getAuthitem().voToPo(AuthitemPo.class);
		if (likePo == null) {
			likePo = new AuthitemPo();
		}
		return service.pagePermission(param.getPageinfo(), likePo);
	}

	@PostMapping("/pageRole")
	public ResultMessage pageRole(@RequestBody SecurityParam param) throws Exception {
		AuthitemPo likePo = param.getAuthitem().voToPo(AuthitemPo.class);
		if (likePo == null) {
			likePo = new AuthitemPo();
		}
		return service.pageRole(param.getPageinfo(), likePo);
	}

	@PostMapping("/addRole")
	public ResultMessage addRole(@RequestBody SecurityParam param, @RequestAttribute("currentUser") UserPo currentUser)
			throws Exception {
		AuthitemPo role = param.getAuthitem().voToPo(AuthitemPo.class);
		List<AuthitemPo> permissionPos = JsonHelper.jsonToBeanList(param.getAuthitemList(), AuthitemPo.class);

		return service.addRole(role, permissionPos, currentUser);
	}

	@PostMapping("/getRolePermissionList")
	public ResultMessage getRolePermissionList(@RequestBody SecurityParam param) throws Exception {
		AuthitemPo role = param.getAuthitem().voToPo(AuthitemPo.class);
		return service.getRolePermissionList(role);
	}

	@PostMapping("/editRole")
	public ResultMessage editRole(@RequestBody SecurityParam param, @RequestAttribute("currentUser") UserPo currentUser)
			throws Exception {
		AuthitemPo role = param.getAuthitem().voToPo(AuthitemPo.class);
		List<AuthitemPo> addPermissionPos = JsonHelper.jsonToBeanList(param.getAuthitemList(), AuthitemPo.class);
		List<AuthitemPo> subPermissionPos = JsonHelper.jsonToBeanList(param.getAuthitemList2(), AuthitemPo.class);

		return service.editRole(role, addPermissionPos, subPermissionPos, currentUser);
	}

}
