/**
 * Copyright (c) 2007-2017 Wteam.  All rights reserved. 网维网络技术创业团队 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.security.controller.Param.SecurityParam;
import com.wteam.superboot.security.entity.po.UserkeyPo;
import com.wteam.superboot.security.service.UserkeyService;

/**
 * 授权Controller.
 * 
 * @authod 罗佳欣
 * 
 */
@RestController
public class UserkeyController {

	@Autowired
	private UserkeyService service;

	@PostMapping("/pageUserkey")
	public ResultMessage pageUserkey(@RequestBody SecurityParam param) throws Exception {
		UserkeyPo userkeyPo = param.getUserkey().voToPo(UserkeyPo.class);
		if (userkeyPo == null) {
			userkeyPo = new UserkeyPo();
		}
		return service.pageUserkey(param.getPageinfo(), userkeyPo);
	}

	@PostMapping("/getUserRoleList")
	public ResultMessage getUserRoleList(@RequestBody SecurityParam param) throws Exception {
		UserkeyPo userkeyPo = param.getUserkey().voToPo(UserkeyPo.class);
		return service.getUserRoleList(userkeyPo);
	}

}
