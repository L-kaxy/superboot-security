/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.controller.Param;

import java.util.List;

import com.wteam.superboot.core.controller.param.CoreParam;
import com.wteam.superboot.security.entity.po.UserauthitemmapPo;

/**
 * 返回参数.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
public class UserAuthitemParam extends CoreParam {

	private List<UserauthitemmapPo> userauthitemmapList;

	/**
	 * @return 设置 userauthitemmapList 的值.
	 */
	public List<UserauthitemmapPo> getUserauthitemmapList() {
		return userauthitemmapList;
	}

	/**
	 * 设置 userauthitemmapList 的值.
	 * 
	 * @param userauthitemmapList
	 *            赋值给 userauthitemmapList.
	 */
	public void setUserauthitemmapList(List<UserauthitemmapPo> userauthitemmapList) {
		this.userauthitemmapList = userauthitemmapList;
	}

}
