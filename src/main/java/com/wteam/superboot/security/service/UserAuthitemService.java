/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
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
 * @version 1.2.0
 */
@Service
@Transactional
public class UserAuthitemService {

	/**
	 * 注入 Repository.
	 */
	@Autowired
	private UserauthitemmapRepository userauthitemmapRepository;

	/**
	 * 批量添加用户权限条目映射.
	 * 
	 * @param list
	 *            用户权限列表.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             抛出异常.
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
	 * @param list
	 *            用户权限列表.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             抛出异常.
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
