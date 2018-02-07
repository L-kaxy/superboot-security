/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wteam.superboot.core.enums.ResultEnum;
import com.wteam.superboot.core.exception.SuperException;
import com.wteam.superboot.core.helper.ResultHelper;
import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.security.entity.po.UserkeyPo;
import com.wteam.superboot.security.helper.ShiroHelper;
import com.wteam.superboot.security.repository.UserkeyRepository;

/**
 * 二级管理员登录模块Servcie类(单例).
 * 
 * @author 罗佳欣
 */
@Service
@Transactional
public class SystemService {

	/**
	 * 注入 repository.
	 */
	@Autowired
	private UserkeyRepository userkeyRepository;

	/**
	 * 密码登录.
	 * 
	 * @return
	 */
	public ResultMessage login(final UserkeyPo userkey, final HttpServletRequest request,
			HttpServletResponse response) {
		if (userkey == null || userkey.getLoginmsg() == null || userkey.getCredential() == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		ResultMessage resultMessage = null;

		UserkeyPo userkeyPo = new UserkeyPo();
		userkeyPo.setLoginmsg(userkey.getLoginmsg());

		UserkeyPo tempUserkeyPo = userkeyRepository.queryEntity(userkeyPo);

		if (tempUserkeyPo == null) {
			// 用户不存在
			throw new SuperException(ResultEnum.USERNAME_NOT_EXIST);
		} else {
			userkeyPo.setCredential(userkey.getCredential());
			tempUserkeyPo = userkeyRepository.queryEntity(userkeyPo);
			if (tempUserkeyPo == null) {
				throw new SuperException(ResultEnum.PASSWORD_ERROR);
			}
		}

		ShiroHelper.login(userkey.getLoginmsg(), userkey.getCredential(), request, response);

		resultMessage = ResultHelper.result(ResultEnum.LOGIN_SUCCESS);
		return resultMessage;
	}

}