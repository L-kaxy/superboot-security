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
 * 用户权限条目映射持久层类.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@Entity
@Table(name = "t_userauthitemmap")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserauthitemmapPo extends BasePersistentObject {

	/**
	 * 用户权限条目映射编号.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userauthitemmapid;

	/**
	 * 用户编号.
	 */
	@Column(nullable = false)
	private Long userid;

	/**
	 * 权限条目编号.
	 */
	@Column(nullable = false)
	private Long authitemid;

	/**
	 * @return 设置 userauthitemmapid 的值.
	 */
	public Long getUserauthitemmapid() {
		return userauthitemmapid;
	}

	/**
	 * 设置 userauthitemmapid 的值.
	 * 
	 * @param userauthitemmapid
	 *            赋值给 userauthitemmapid.
	 */
	public void setUserauthitemmapid(Long userauthitemmapid) {
		this.userauthitemmapid = userauthitemmapid;
	}

	/**
	 * @return 设置 userid 的值.
	 */
	public Long getUserid() {
		return userid;
	}

	/**
	 * 设置 userid 的值.
	 * 
	 * @param userid
	 *            赋值给 userid.
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}

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

}
