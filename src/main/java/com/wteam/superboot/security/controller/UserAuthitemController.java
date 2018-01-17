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
import com.wteam.superboot.security.entity.po.UserauthitemmapPo;
import com.wteam.superboot.security.service.UserAuthitemService;

/**
 * 用户权限条目Controller.
 * 
 * @authod 罗佳欣
 * 
 */
@RestController
public class UserAuthitemController {

	@Autowired
	private UserAuthitemService service;

	@PostMapping("/addUserAuthitemByList")
	public ResultMessage addUserAuthitemByList(@RequestBody SecurityParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		List<UserauthitemmapPo> poList = JsonHelper.jsonToBeanList(param.getUserauthitemmapList(),
				UserauthitemmapPo.class);
		return service.addUserAuthitemByList(poList, currentUser);
	}

	@PostMapping("/deleteUserAuthitemByList")
	public ResultMessage deleteUserAuthitemByList(@RequestBody SecurityParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		List<UserauthitemmapPo> poList = JsonHelper.jsonToBeanList(param.getUserauthitemmapList(),
				UserauthitemmapPo.class);
		return service.deleteUserAuthitemByList(poList, currentUser);
	}

}
