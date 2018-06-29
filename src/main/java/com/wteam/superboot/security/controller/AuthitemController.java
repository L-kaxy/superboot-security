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
import com.wteam.superboot.core.enums.ResultEnum;
import com.wteam.superboot.core.helper.ResultHelper;
import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.security.controller.Param.AuthitemParam;
import com.wteam.superboot.security.entity.po.AuthitemPo;
import com.wteam.superboot.security.service.AuthitemService;

/**
 * 权限Controller.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@RestController
public class AuthitemController {

	/**
	 * 注入 service.
	 */
	@Autowired
	private AuthitemService service;

	/**
	 * 批量添加权限条目.
	 * 
	 * @param param
	 *            请求数据.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/addAuthitemByList")
	public ResultMessage addAuthitemByList(@RequestBody AuthitemParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		return service.addAuthitemByList(param.getAuthitemList(), currentUser);
	}

	/**
	 * 批量编辑权限条目.
	 * 
	 * @param param
	 *            请求数据.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/editAuthitemByList")
	public ResultMessage editAuthitemByList(@RequestBody AuthitemParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		return service.editAuthitemByList(param.getAuthitemList(), currentUser);
	}

	/**
	 * 接口分页附对应行为列表.
	 * 
	 * @param param
	 *            请求数据.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/deleteAuthitemByList")
	public ResultMessage deleteAuthitemByList(@RequestBody AuthitemParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		ResultMessage resultMessage = null;

		boolean hasPermission = false;
		boolean hasRole = false;
		for (AuthitemPo po : param.getAuthitemList()) {
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
				resultMessage = service.deletePermissionByList(param.getAuthitemList(), currentUser);
			} else if (hasRole) {
				resultMessage = service.deleteRoleByList(param.getAuthitemList(), currentUser);
			}
		}

		return resultMessage;
	}

	/**
	 * 查询非删除权限条目分页列表.
	 * 
	 * @param param
	 *            请求数据.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/pageAuthitem")
	public ResultMessage pageAuthitem(@RequestBody AuthitemParam param) throws Exception {
		return service.pageAuthitem(param.getPageinfo(), param.getAuthitem(), param.getAuthitem2());
	}

	/**
	 * 行为分页.
	 * 
	 * @param param
	 *            请求数据.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/pagePermission")
	public ResultMessage pagePermission(@RequestBody AuthitemParam param) throws Exception {
		return service.pagePermission(param.getPageinfo(), param.getAuthitem());
	}

	/**
	 * 角色分页附对应行为列表.
	 * 
	 * @param param
	 *            请求数据.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/pageRole")
	public ResultMessage pageRole(@RequestBody AuthitemParam param) throws Exception {
		return service.pageRole(param.getPageinfo(), param.getAuthitem());
	}

	/**
	 * 添加角色.
	 * 
	 * @param param
	 *            请求数据.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/addRole")
	public ResultMessage addRole(@RequestBody AuthitemParam param, @RequestAttribute("currentUser") UserPo currentUser)
			throws Exception {
		return service.addRole(param.getAuthitem(), param.getAuthitemList(), currentUser);
	}

	/**
	 * 获取指定角色所属行为.
	 * 
	 * @param param
	 *            请求数据.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/getRolePermissionList")
	public ResultMessage getRolePermissionList(@RequestBody AuthitemParam param) throws Exception {
		return service.getRolePermissionList(param.getAuthitem());
	}

	/**
	 * 编辑角色.
	 * 
	 * @param param
	 *            请求数据.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/editRole")
	public ResultMessage editRole(@RequestBody AuthitemParam param, @RequestAttribute("currentUser") UserPo currentUser)
			throws Exception {
		return service.editRole(param.getAuthitem(), param.getAuthitemList(), param.getAuthitemList2(), currentUser);
	}

}
