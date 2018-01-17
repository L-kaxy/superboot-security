/**
 * Copyright (c) 2007-2017 Wteam.  All rights reserved. 网维网络技术创业团队 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wteam.superboot.core.enums.ResultEnum;
import com.wteam.superboot.core.helper.ResultHelper;
import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.security.controller.Param.SecurityParam;
import com.wteam.superboot.security.entity.po.UserkeyPo;
import com.wteam.superboot.security.helper.ShiroHelper;

/**
 * 系統 Controller.
 * 
 * @authod 罗佳欣
 * 
 */
@RestController
public class SystemController {

	/**
	 * 登录.
	 * 
	 * @author 罗佳欣
	 */
	@PostMapping("/login")
	public ResultMessage login(@RequestBody SecurityParam param, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserkeyPo userkeyPo = param.getUserkey().voToPo(UserkeyPo.class);

		ResultMessage resultMessage = null;
		try {
			ShiroHelper.login(userkeyPo.getLoginmsg(), userkeyPo.getCredential(), request, response);
			resultMessage = ResultHelper.result(ResultEnum.LOGIN_SUCCESS);
		} catch (UnknownAccountException e) {
			resultMessage = ResultHelper.result(ResultEnum.USERNAME_NOT_EXIST);
		} catch (IncorrectCredentialsException e) {
			resultMessage = ResultHelper.result(ResultEnum.PASSWORD_ERROR);
		}

		return resultMessage;
	}

	/**
	 * 登出.
	 * 
	 * @author 罗佳欣
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
	 * @author 罗佳欣
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