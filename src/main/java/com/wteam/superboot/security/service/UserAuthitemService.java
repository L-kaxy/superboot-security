/**
 * Copyright (c) 2007-2017 Wteam.  All rights reserved. 网维网络技术创业团队 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wteam.superboot.core.entity.po.UserPo;
import com.wteam.superboot.core.enums.ResultEnum;
import com.wteam.superboot.core.exception.SuperException;
import com.wteam.superboot.core.helper.ResultHelper;
import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.security.entity.po.UserauthitemmapPo;
import com.wteam.superboot.security.repository.UserauthitemmapRepository;

/**
 * 用户权限条目Servcie类.
 * 
 * @author 罗佳欣
 *
 */
@Service
@Transactional
public class UserAuthitemService {

	/**
	 * 注入userauthitemmapRepository.
	 */
	@Autowired
	private UserauthitemmapRepository userauthitemmapRepository;

	/**
	 * 批量添加用户权限条目映射.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ResultMessage addUserAuthitemByList(final List<UserauthitemmapPo> list, final UserPo currentUser)
			throws Exception {
		if (list == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		// 获取用户对应的角色列表
		UserauthitemmapPo valid = null;
		for (UserauthitemmapPo po : list) {
			valid = new UserauthitemmapPo();
			valid.setUserid(po.getUserid());
			valid.setAuthitemid(po.getAuthitemid());
			if (!userauthitemmapRepository.hasNonDeleteEntity(valid)) {
				userauthitemmapRepository.addEntity(po, currentUser);
			}
		}

		ResultMessage rs = ResultHelper.result(ResultEnum.ADD_SUCCESS);

		return rs;
	}

	/**
	 * 批量删除用户权限条目映射.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ResultMessage deleteUserAuthitemByList(final List<UserauthitemmapPo> list, final UserPo currentUser)
			throws Exception {
		if (list == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		for (UserauthitemmapPo po : list) {
			userauthitemmapRepository.deleteEntity(po);
		}

		ResultMessage rs = ResultHelper.result(ResultEnum.DEL_SUCCESS);

		return rs;
	}

}
