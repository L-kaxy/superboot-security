/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.controller.Param;

import java.util.List;

import com.wteam.superboot.core.controller.param.CoreParam;
import com.wteam.superboot.core.entity.po.PageinfoPo;
import com.wteam.superboot.security.entity.po.ActionPo;
import com.wteam.superboot.security.entity.po.AuthitemPo;

/**
 * 返回参数.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
public class ActionParam extends CoreParam {

	private ActionPo action;

	private List<ActionPo> actionList;

	private List<AuthitemPo> permissionList;

	private List<AuthitemPo> permissionList2;

	private PageinfoPo pageinfo;

	/**
	 * @return 设置 action 的值.
	 */
	public ActionPo getAction() {
		return action;
	}

	/**
	 * 设置 action 的值.
	 * 
	 * @param action
	 *            赋值给 action.
	 */
	public void setAction(ActionPo action) {
		this.action = action;
	}

	/**
	 * @return 设置 actionList 的值.
	 */
	public List<ActionPo> getActionList() {
		return actionList;
	}

	/**
	 * 设置 actionList 的值.
	 * 
	 * @param actionList
	 *            赋值给 actionList.
	 */
	public void setActionList(List<ActionPo> actionList) {
		this.actionList = actionList;
	}

	/**
	 * @return 设置 permissionList 的值.
	 */
	public List<AuthitemPo> getPermissionList() {
		return permissionList;
	}

	/**
	 * 设置 permissionList 的值.
	 * 
	 * @param permissionList
	 *            赋值给 permissionList.
	 */
	public void setPermissionList(List<AuthitemPo> permissionList) {
		this.permissionList = permissionList;
	}

	/**
	 * @return 设置 permissionList2 的值.
	 */
	public List<AuthitemPo> getPermissionList2() {
		return permissionList2;
	}

	/**
	 * 设置 permissionList2 的值.
	 * 
	 * @param permissionList2
	 *            赋值给 permissionList2.
	 */
	public void setPermissionList2(List<AuthitemPo> permissionList2) {
		this.permissionList2 = permissionList2;
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
