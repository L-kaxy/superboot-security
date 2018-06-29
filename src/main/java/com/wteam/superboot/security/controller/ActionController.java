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
import com.wteam.superboot.security.controller.Param.ActionParam;
import com.wteam.superboot.security.service.ActionService;

/**
 * 接口Controller.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@RestController
public class ActionController {

	/**
	 * 注入 service.
	 */
	@Autowired
	private ActionService service;

	/**
	 * 接口分页附对应行为列表.
	 * 
	 * @param param
	 *            请求数据.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/pageAction")
	public ResultMessage pageAction(@RequestBody ActionParam param) throws Exception {
		return service.pageAction(param.getPageinfo(), param.getAction());
	}

	/**
	 * 添加接口.
	 * 
	 * @param param
	 *            请求数据.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/addAction")
	public ResultMessage addAction(@RequestBody ActionParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		return service.addAction(param.getAction(), param.getPermissionList(), currentUser);
	}

	/**
	 * 获取指定接口所属行为.
	 * 
	 * @param param
	 *            请求数据.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/getActionPermissionList")
	public ResultMessage getActionPermissionList(@RequestBody ActionParam param) throws Exception {
		return service.getActionPermissionList(param.getAction());
	}

	/**
	 * 编辑接口.
	 * 
	 * @param param
	 *            请求数据.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/editAction")
	public ResultMessage editAction(@RequestBody ActionParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		return service.editAction(param.getAction(), param.getPermissionList(), param.getPermissionList2(),
				currentUser);
	}

	/**
	 * 批量删除接口.
	 * 
	 * @param param
	 *            请求数据.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/deleteActionByList")
	public ResultMessage deleteActionByList(@RequestBody ActionParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		return service.deleteActionByList(param.getActionList());
	}

}
