/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wteam.superboot.core.entity.po.PageinfoPo;
import com.wteam.superboot.core.entity.po.SuperPersistentObject;
import com.wteam.superboot.core.enums.ResultEnum;
import com.wteam.superboot.core.exception.SuperException;
import com.wteam.superboot.core.helper.ResultHelper;
import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.security.entity.po.AuthitemPo;
import com.wteam.superboot.security.entity.po.UserauthitemmapPo;
import com.wteam.superboot.security.entity.po.UserkeyPo;
import com.wteam.superboot.security.entity.vo.UserkeyListItemVo;
import com.wteam.superboot.security.repository.AuthitemRepository;
import com.wteam.superboot.security.repository.UserauthitemmapRepository;
import com.wteam.superboot.security.repository.UserkeyRepository;

/**
 * 授权Service类.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@Service
@Transactional
public class UserkeyService {

	/**
	 * 注入 Repository.
	 */
	@Autowired
	private AuthitemRepository authitemRepository;
	@Autowired
	private UserkeyRepository userkeyRepository;
	@Autowired
	private UserauthitemmapRepository userauthitemmapRepository;

	/**
	 * 查询非删除用户验证信息分页列表.
	 * 
	 * @param pageinfo
	 *            分页信息，不得为null.
	 * @param userkey
	 *            接口实体模糊查询信息，不得为null.
	 * @return 结果集.
	 * @throws Exception
	 *             抛出异常.
	 */
	public ResultMessage pageUserkey(final PageinfoPo pageinfo, final UserkeyPo userkey) throws Exception {
		if (pageinfo == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (pageinfo.getSortFieldNames() == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (pageinfo.getOrder() == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		Page<UserkeyPo> pageResult = userkeyRepository.pageNonDeleteEntity(pageinfo, new UserkeyPo(), userkey);

		// 获取用户对应的角色列表
		UserkeyPo uPo = null;
		UserauthitemmapPo uamapPo = null;
		AuthitemPo aPo = null;
		UserkeyListItemVo uItemPo = null;
		List<AuthitemPo> roleList = null;
		List<UserkeyListItemVo> itemVoList = new ArrayList<>();
		for (SuperPersistentObject po : pageResult.getContent()) {
			uPo = (UserkeyPo) po;
			uItemPo = new UserkeyListItemVo();
			uItemPo.setUserid(uPo.getUserid());
			uItemPo.setUserkeyid(uPo.getUserkeyid());
			uItemPo.setLoginmsg(uPo.getLoginmsg());
			uItemPo.setCreatetime(uPo.getCreatetime());

			uamapPo = new UserauthitemmapPo();
			uamapPo.setUserid(uPo.getUserid());
			roleList = new ArrayList<AuthitemPo>();
			for (UserauthitemmapPo tampUamapPo : userauthitemmapRepository.queryNonDeleteList(uamapPo)) {
				aPo = authitemRepository.getEntityById(AuthitemPo.class, tampUamapPo.getAuthitemid());
				roleList.add(aPo);
			}
			uItemPo.setRoleList(roleList);
			itemVoList.add(uItemPo);
		}

		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("pageList", itemVoList);
		parm.put("totalCount", pageResult.getTotalElements());
		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);

		return rs;
	}

	/**
	 * 获取指定用户所属角色.
	 * 
	 * @param userkey
	 *            用户验证信息实体.
	 * @return 结果集.
	 * @throws Exception
	 *             抛出异常.
	 */
	public ResultMessage getUserRoleList(final UserkeyPo userkey) throws Exception {
		if (userkey == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		// 获取用户对应的角色列表
		UserkeyPo uPo = userkey;
		UserauthitemmapPo uamapPo = new UserauthitemmapPo();
		uamapPo.setUserid(uPo.getUserid());
		List<AuthitemPo> roleList = new ArrayList<AuthitemPo>();
		AuthitemPo aPo = null;
		for (UserauthitemmapPo tampUamapPo : userauthitemmapRepository.queryNonDeleteList(uamapPo)) {
			aPo = authitemRepository.getEntityById(AuthitemPo.class, tampUamapPo.getAuthitemid());
			roleList.add(aPo);
		}

		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("roleList", roleList);
		
		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);
		return rs;
	}

}
