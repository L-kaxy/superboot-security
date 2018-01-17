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
import com.wteam.superboot.core.helper.JsonHelper;
import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.security.controller.Param.SecurityParam;
import com.wteam.superboot.security.entity.po.ActionPo;
import com.wteam.superboot.security.entity.po.AuthitemPo;
import com.wteam.superboot.security.service.ActionService;

/**
 * 接口Controller.
 * 
 * @authod 罗佳欣
 * 
 */
@RestController
public class ActionController {

	@Autowired
	private ActionService service;

	@PostMapping("/pageAction")
	public ResultMessage pageAction(@RequestBody SecurityParam param) throws Exception {
		ActionPo likePo = param.getAction().voToPo(ActionPo.class);
		return service.pageAction(param.getPageinfo(), likePo);
	}

	@PostMapping("/addAction")
	public ResultMessage addAction(@RequestBody SecurityParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		List<AuthitemPo> permissions = JsonHelper.jsonToBeanList(param.getPermissionList(), AuthitemPo.class);
		ActionPo actionPo = param.getAction().voToPo(ActionPo.class);
		return service.addAction(actionPo, permissions, currentUser);
	}

	@PostMapping("/getActionPermissionList")
	public ResultMessage getActionPermissionList(@RequestBody SecurityParam param) throws Exception {
		ActionPo actionPo = param.getAction().voToPo(ActionPo.class);
		return service.getActionPermissionList(actionPo);
	}

	@PostMapping("/editAction")
	public ResultMessage editAction(@RequestBody SecurityParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		List<AuthitemPo> addPermissionPos = JsonHelper.jsonToBeanList(param.getPermissionList(), AuthitemPo.class);
		List<AuthitemPo> subPermissionPos = JsonHelper.jsonToBeanList(param.getPermissionList2(), AuthitemPo.class);
		ActionPo actionPo = param.getAction().voToPo(ActionPo.class);
		return service.editAction(actionPo, addPermissionPos, subPermissionPos, currentUser);
	}

	@PostMapping("/deleteActionByList")
	public ResultMessage deleteActionByList(@RequestBody SecurityParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		List<ActionPo> list = JsonHelper.jsonToBeanList(param.getActionList(), ActionPo.class);
		return service.deleteActionByList(list, currentUser);
	}

}
