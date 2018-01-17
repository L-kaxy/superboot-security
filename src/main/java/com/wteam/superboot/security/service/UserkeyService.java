/**
 * Copyright (c) 2007-2017 Wteam.  All rights reserved. 网维网络技术创业团队 版权所有.
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
import com.wteam.superboot.security.entity.vo.AuthitemVo;
import com.wteam.superboot.security.repository.AuthitemRepository;
import com.wteam.superboot.security.repository.UserauthitemmapRepository;
import com.wteam.superboot.security.repository.UserkeyRepository;
import com.wteam.superboot.security.view.po.UserkeyListItemPo;
import com.wteam.superboot.security.view.vo.UserkeyListItemVo;

/**
 * 授权Service类.
 * 
 * @author 罗佳欣
 *
 */
@Service
@Transactional
public class UserkeyService {

	/**
	 * 注入authitemRepository.
	 */
	@Autowired
	private AuthitemRepository authitemRepository;

	/**
	 * 注入userkeyRepository.
	 */
	@Autowired
	private UserkeyRepository userkeyRepository;

	/**
	 * 注入userauthitemmapRepository.
	 */
	@Autowired
	private UserauthitemmapRepository userauthitemmapRepository;

	/**
	 * 查询非删除用户验证信息分页列表.
	 * 
	 * @param pageinfo
	 *            分页信息，不得为null.
	 * @param likePo
	 *            接口实体模糊查询信息，不得为null.
	 * @return
	 * @throws Exception
	 */
	public ResultMessage pageUserkey(final PageinfoPo pageinfo, final UserkeyPo userkey) throws Exception {
		if (pageinfo == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (userkey == null) {
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
		UserkeyListItemPo uItemPo = null;
		List<AuthitemPo> roleList = null;
		List<UserkeyListItemPo> itemPoList = new ArrayList<UserkeyListItemPo>();
		for (SuperPersistentObject po : pageResult.getContent()) {
			uPo = (UserkeyPo) po;
			uItemPo = new UserkeyListItemPo();
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
			itemPoList.add(uItemPo);
		}

		Map<String, Object> parm = new HashMap<String, Object>();

		List<UserkeyListItemVo> resultList = new ArrayList<UserkeyListItemVo>();
		UserkeyListItemVo tempVo = null;
		for (UserkeyListItemPo po : itemPoList) {
			tempVo = new UserkeyListItemVo();
			tempVo.poViewToVo(po);
			resultList.add(tempVo);
		}

		parm.put("pageList", resultList);
		parm.put("totalCount", pageResult.getTotalElements());
		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);

		return rs;
	}

	/**
	 * 获取指定用户所属角色.
	 * 
	 * @return
	 * @throws Exception
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

		List<AuthitemVo> resultList = new ArrayList<AuthitemVo>();
		AuthitemVo tempVo = null;
		for (AuthitemPo po : roleList) {
			tempVo = new AuthitemVo();
			tempVo.poToVo(po);
			resultList.add(tempVo);
		}

		parm.put("roleList", resultList);
		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);

		return rs;
	}

}
