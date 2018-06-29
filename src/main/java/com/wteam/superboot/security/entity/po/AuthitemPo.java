/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.wteam.superboot.core.entity.po.BasePersistentObject;

/**
 * 权限条目持久层类.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@Entity
@Table(name = "t_authitem")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AuthitemPo extends BasePersistentObject {

	/**
	 * 权限条目编号.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long authitemid;

	/**
	 * 权限条目名.
	 */
	@Column(nullable = false)
	private String authitemname;

	/**
	 * 权限条目类型 true-权限 false-角色.
	 */
	@Column(nullable = false)
	private Boolean authitemtype;

	/**
	 * @return 设置 authitemid 的值.
	 */
	public Long getAuthitemid() {
		return authitemid;
	}

	/**
	 * 设置 authitemid 的值.
	 * 
	 * @param authitemid
	 *            赋值给 authitemid.
	 */
	public void setAuthitemid(Long authitemid) {
		this.authitemid = authitemid;
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

}
