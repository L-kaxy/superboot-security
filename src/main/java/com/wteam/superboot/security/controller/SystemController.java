/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wteam.superboot.core.enums.ResultEnum;
import com.wteam.superboot.core.helper.ResultHelper;
import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.security.controller.Param.SystemParam;
import com.wteam.superboot.security.helper.ShiroHelper;
import com.wteam.superboot.security.service.SystemService;

/**
 * 系統 Controller.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@RestController
public class SystemController {
	
	/**
	 * 注入 service.
	 */
	@Autowired
	private SystemService service;

	/**
	 * 登录.
	 * 
	 * @param param
	 *            请求参数.
	 * @param request
	 *            HttpServletRequest.
	 * @param response
	 *            HttpServletResponse.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/login")
	public ResultMessage login(@RequestBody SystemParam param, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return service.login(param.getUserkey(), request, response);
	}

	/**
	 * 登出.
	 * 
	 * @param request
	 *            HttpServletRequest.
	 * @param response
	 *            HttpServletResponse.
	 * @return 结果集.
	 */
	@PostMapping("/logout")
	public ResultMessage logout(HttpServletRequest request, HttpServletResponse response) {
		ShiroHelper.logout(request, response);
		ResultMessage resultMessage = ResultHelper.result(ResultEnum.LOGOUT_SUCCESS);
		return resultMessage;
	}

	/**
	 * 是否已登录.
	 * 
	 * @param request
	 *            HttpServletRequest.
	 * @param response
	 *            HttpServletResponse.
	 * @return 结果集.
	 */
	@PostMapping("/isLogin")
	public ResultMessage isLogin(HttpServletRequest request, HttpServletResponse response) {
		boolean result = false;
		result = ShiroHelper.isAuthenticated(request, response);
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("isLogin", result);

		ResultMessage resultMessage = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);
		return resultMessage;
	}

}
