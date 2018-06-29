/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wteam.superboot.core.entity.po.UserPo;
import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.security.controller.Param.UserAuthitemParam;
import com.wteam.superboot.security.service.UserAuthitemService;

/**
 * 用户权限条目Controller.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@RestController
public class UserAuthitemController {

	/**
	 * 注入 service.
	 */
	@Autowired
	private UserAuthitemService service;

	/**
	 * 批量添加用户权限条目映射.
	 * 
	 * @param param
	 *            请求数据.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/addUserAuthitemByList")
	public ResultMessage addUserAuthitemByList(@RequestBody UserAuthitemParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		return service.addUserAuthitemByList(param.getUserauthitemmapList(), currentUser);
	}

	/**
	 * 批量删除用户权限条目映射.
	 * 
	 * @param param
	 *            请求数据.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/deleteUserAuthitemByList")
	public ResultMessage deleteUserAuthitemByList(@RequestBody UserAuthitemParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		return service.deleteUserAuthitemByList(param.getUserauthitemmapList(), currentUser);
	}

}
