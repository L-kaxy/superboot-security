/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.entity.vo;

import java.util.Date;
import java.util.List;

import com.wteam.superboot.security.entity.po.AuthitemPo;

/**
 * 接口列表条目视图数据值对象类.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
public class ActionListItemVo {
	/**
	 * 接口编号.
	 */
	private Long actionid;

	/**
	 * 角色名.
	 */
	private String actionname;

	/**
	 * 该接口对应的行为列表.
	 */
	private List<AuthitemPo> permissionList;

	/**
	 * 创建时间.
	 */
	private Date createtime;

	/**
	 * @return 设置 actionid 的值.
	 */
	public Long getActionid() {
		return actionid;
	}

	/**
	 * 设置 actionid 的值.
	 * 
	 * @param actionid
	 *            赋值给 actionid.
	 */
	public void setActionid(Long actionid) {
		this.actionid = actionid;
	}

	/**
	 * @return 设置 actionname 的值.
	 */
	public String getActionname() {
		return actionname;
	}

	/**
	 * 设置 actionname 的值.
	 * 
	 * @param actionname
	 *            赋值给 actionname.
	 */
	public void setActionname(String actionname) {
		this.actionname = actionname;
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
	 * @return 设置 createtime 的值.
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * 设置 createtime 的值.
	 * @param createtime 赋值给  createtime.
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	

}
