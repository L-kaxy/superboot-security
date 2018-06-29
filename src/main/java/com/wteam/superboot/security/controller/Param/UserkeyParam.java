/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.controller.Param;

import com.wteam.superboot.core.controller.param.CoreParam;
import com.wteam.superboot.core.entity.po.PageinfoPo;
import com.wteam.superboot.security.entity.po.UserkeyPo;

/**
 * 返回参数.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
public class UserkeyParam extends CoreParam {

	private UserkeyPo userkey;

	private PageinfoPo pageinfo;

	/**
	 * @return 设置 userkey 的值.
	 */
	public UserkeyPo getUserkey() {
		return userkey;
	}

	/**
	 * 设置 userkey 的值.
	 * 
	 * @param userkey
	 *            赋值给 userkey.
	 */
	public void setUserkey(UserkeyPo userkey) {
		this.userkey = userkey;
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
