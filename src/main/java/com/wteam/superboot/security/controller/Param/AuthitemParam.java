/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.controller.Param;

import java.util.List;

import com.wteam.superboot.core.controller.param.CoreParam;
import com.wteam.superboot.core.entity.po.PageinfoPo;
import com.wteam.superboot.security.entity.po.AuthitemPo;

/**
 * 返回参数.
 * 
 * @authod 罗佳欣
 * @version 1.2.0
 */
public class AuthitemParam extends CoreParam {

	private AuthitemPo authitem;

	private AuthitemPo authitem2;

	private List<AuthitemPo> authitemList;

	private List<AuthitemPo> authitemList2;

	private PageinfoPo pageinfo;

	/**
	 * @return 设置 authitem 的值.
	 */
	public AuthitemPo getAuthitem() {
		return authitem;
	}

	/**
	 * 设置 authitem 的值.
	 * 
	 * @param authitem
	 *            赋值给 authitem.
	 */
	public void setAuthitem(AuthitemPo authitem) {
		this.authitem = authitem;
	}

	/**
	 * @return 设置 authitem2 的值.
	 */
	public AuthitemPo getAuthitem2() {
		return authitem2;
	}

	/**
	 * 设置 authitem2 的值.
	 * 
	 * @param authitem2
	 *            赋值给 authitem2.
	 */
	public void setAuthitem2(AuthitemPo authitem2) {
		this.authitem2 = authitem2;
	}

	/**
	 * @return 设置 authitemList 的值.
	 */
	public List<AuthitemPo> getAuthitemList() {
		return authitemList;
	}

	/**
	 * 设置 authitemList 的值.
	 * 
	 * @param authitemList
	 *            赋值给 authitemList.
	 */
	public void setAuthitemList(List<AuthitemPo> authitemList) {
		this.authitemList = authitemList;
	}

	/**
	 * @return 设置 authitemList2 的值.
	 */
	public List<AuthitemPo> getAuthitemList2() {
		return authitemList2;
	}

	/**
	 * 设置 authitemList2 的值.
	 * 
	 * @param authitemList2
	 *            赋值给 authitemList2.
	 */
	public void setAuthitemList2(List<AuthitemPo> authitemList2) {
		this.authitemList2 = authitemList2;
	}

	/**
	 * @return 设置 pageinfo 的值.
	 */
	public PageinfoPo getPageinfo() {
		return pageinfo;
	}

	/**
	 * 设置 pageinfo 的值.
	 * 
	 * @param pageinfo
	 *            赋值给 pageinfo.
	 */
	public void setPageinfo(PageinfoPo pageinfo) {
		this.pageinfo = pageinfo;
	}

}
