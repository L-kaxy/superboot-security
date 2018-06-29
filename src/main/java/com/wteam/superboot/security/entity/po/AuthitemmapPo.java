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
 * 权限条目父子映射持久层类.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@Entity
@Table(name = "t_authitemmap")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AuthitemmapPo extends BasePersistentObject {

	/**
	 * 权限条目父子映射编号.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long authitemmapid;

	/**
	 * 父权限条目编号.
	 */
	@Column(nullable = false)
	private Long parentid;

	/**
	 * 子权限条目编号.
	 */
	@Column(nullable = false)
	private Long childid;

	/**
	 * @return 设置 authitemmapid 的值.
	 */
	public Long getAuthitemmapid() {
		return authitemmapid;
	}

	/**
	 * 设置 authitemmapid 的值.
	 * 
	 * @param authitemmapid
	 *            赋值给 authitemmapid.
	 */
	public void setAuthitemmapid(Long authitemmapid) {
		this.authitemmapid = authitemmapid;
	}

	/**
	 * @return 设置 parentid 的值.
	 */
	public Long getParentid() {
		return parentid;
	}

	/**
	 * 设置 parentid 的值.
	 * 
	 * @param parentid
	 *            赋值给 parentid.
	 */
	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	/**
	 * @return 设置 childid 的值.
	 */
	public Long getChildid() {
		return childid;
	}

	/**
	 * 设置 childid 的值.
	 * 
	 * @param childid
	 *            赋值给 childid.
	 */
	public void setChildid(Long childid) {
		this.childid = childid;
	}

}
