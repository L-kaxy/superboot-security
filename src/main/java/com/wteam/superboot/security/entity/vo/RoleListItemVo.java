/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.entity.vo;

import java.util.Date;
import java.util.List;

import com.wteam.superboot.security.entity.po.AuthitemPo;

/**
 * 角色列表条目视图数据值对象类.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
public class RoleListItemVo {
	/**
	 * 角色编号.
	 */
	private Long roleid;

	/**
	 * 角色名.
	 */
	private String authitemname;

	/**
	 * 权限条目类型 true-权限 false-角色.
	 */
	private Boolean authitemtype;

	/**
	 * 该角色对应的行为列表.
	 */
	private List<AuthitemPo> permissionList;

	/**
	 * 创建时间.
	 */
	private Date createtime;

	/**
	 * @return 设置 roleid 的值.
	 */
	public Long getRoleid() {
		return roleid;
	}

	/**
	 * 设置 roleid 的值.
	 * 
	 * @param roleid
	 *            赋值给 roleid.
	 */
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	/**
	 * @return 设置 authitemname 的值.
	 */
	public String getAuthitemname() {
		return authitemname;
	}

	/**
	 * 设置 authitemname 的值.
	 * 
	 * @param authitemname
	 *            赋值给 authitemname.
	 */
	public void setAuthitemname(String authitemname) {
		this.authitemname = authitemname;
	}

	/**
	 * @return 设置 authitemtype 的值.
	 */
	public Boolean getAuthitemtype() {
		return authitemtype;
	}

	/**
	 * 设置 authitemtype 的值.
	 * 
	 * @param authitemtype
	 *            赋值给 authitemtype.
	 */
	public void setAuthitemtype(Boolean authitemtype) {
		this.authitemtype = authitemtype;
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
	 * 
	 * @param createtime
	 *            赋值给 createtime.
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
