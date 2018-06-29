/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.security.controller.Param.UserkeyParam;
import com.wteam.superboot.security.service.UserkeyService;

/**
 * 授权Controller.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@RestController
public class UserkeyController {

	/**
	 * 注入 service.
	 */
	@Autowired
	private UserkeyService service;

	/**
	 * 批量添加用户权限条目映射.
	 * 
	 * @param param
	 *            请求数据.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/pageUserkey")
	public ResultMessage pageUserkey(@RequestBody UserkeyParam param) throws Exception {
		return service.pageUserkey(param.getPageinfo(), param.getUserkey());
	}

	/**
	 * 批量添加用户权限条目映射.
	 * 
	 * @param param
	 *            请求数据.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/getUserRoleList")
	public ResultMessage getUserRoleList(@RequestBody UserkeyParam param) throws Exception {
		return service.getUserRoleList(param.getUserkey());
	}

}
