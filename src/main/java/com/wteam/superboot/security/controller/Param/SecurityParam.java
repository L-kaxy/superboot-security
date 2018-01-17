/**
 * Copyright (c) 2007-2017 Wteam.  All rights reserved. 网维网络技术创业团队 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.controller.Param;

import com.wteam.superboot.core.controller.param.CoreParam;
import com.wteam.superboot.core.entity.po.PageinfoPo;
import com.wteam.superboot.security.entity.vo.ActionVo;
import com.wteam.superboot.security.entity.vo.AuthitemVo;
import com.wteam.superboot.security.entity.vo.UserkeyVo;

/**
 * 返回参数.
 * 
 * @authod 罗佳欣
 * 
 */
public class SecurityParam extends CoreParam {

	private ActionVo action;

	private AuthitemVo authitem;

	private AuthitemVo authitem2;

	private String actionList;

	private String permissionList;

	private String permissionList2;

	private UserkeyVo userkey;

	private String authitemList;

	private String authitemList2;

	private String userauthitemmapList;

	private PageinfoPo pageinfo;

	public ActionVo getAction() {
		return action;
	}

	public void setAction(ActionVo action) {
		this.action = action;
	}

	public AuthitemVo getAuthitem() {
		return authitem;
	}

	public void setAuthitem(AuthitemVo authitem) {
		this.authitem = authitem;
	}

	public AuthitemVo getAuthitem2() {
		return authitem2;
	}

	public void setAuthitem2(AuthitemVo authitem2) {
		this.authitem2 = authitem2;
	}

	public String getActionList() {
		return actionList;
	}

	public void setActionList(String actionList) {
		this.actionList = actionList;
	}

	public UserkeyVo getUserkey() {
		return userkey;
	}

	public void setUserkey(UserkeyVo userkey) {
		this.userkey = userkey;
	}

	public String getAuthitemList() {
		return authitemList;
	}

	public void setAuthitemList(String authitemList) {
		this.authitemList = authitemList;
	}

	public String getAuthitemList2() {
		return authitemList2;
	}

	public void setAuthitemList2(String authitemList2) {
		this.authitemList2 = authitemList2;
	}

	public PageinfoPo getPageinfo() {
		return pageinfo;
	}

	public void setPageinfo(PageinfoPo pageinfo) {
		this.pageinfo = pageinfo;
	}

	public String getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(String permissionList) {
		this.permissionList = permissionList;
	}

	public String getUserauthitemmapList() {
		return userauthitemmapList;
	}

	public void setUserauthitemmapList(String userauthitemmapList) {
		this.userauthitemmapList = userauthitemmapList;
	}

	public String getPermissionList2() {
		return permissionList2;
	}

	public void setPermissionList2(String permissionList2) {
		this.permissionList2 = permissionList2;
	}

}
