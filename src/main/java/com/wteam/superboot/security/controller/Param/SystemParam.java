/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.controller.Param;

import com.wteam.superboot.core.controller.param.CoreParam;
import com.wteam.superboot.security.entity.po.UserkeyPo;

/**
 * 返回参数.
 * 
 * @authod 罗佳欣
 * @version 1.2.0
 */
public class SystemParam extends CoreParam {

	private UserkeyPo userkey;

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

}
