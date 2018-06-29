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
 * 行为资源映射持久层类.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@Entity
@Table(name = "t_permissionresourcemap")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PermissionresourcemapPo extends BasePersistentObject {

	/**
	 * 行为资源映射编号.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long permissionresourcemapid;

	/**
	 * 行为编号.
	 */
	@Column(nullable = false)
	private Long permissionid;

	/**
	 * 资源编号.
	 */
	@Column(nullable = false)
	private Long resourceid;

	/**
	 * @return 设置 permissionresourcemapid 的值.
	 */
	public Long getPermissionresourcemapid() {
		return permissionresourcemapid;
	}

	/**
	 * 设置 permissionresourcemapid 的值.
	 * 
	 * @param permissionresourcemapid
	 *            赋值给 permissionresourcemapid.
	 */
	public void setPermissionresourcemapid(Long permissionresourcemapid) {
		this.permissionresourcemapid = permissionresourcemapid;
	}

	/**
	 * @return 设置 permissionid 的值.
	 */
	public Long getPermissionid() {
		return permissionid;
	}

	/**
	 * 设置 permissionid 的值.
	 * 
	 * @param permissionid
	 *            赋值给 permissionid.
	 */
	public void setPermissionid(Long permissionid) {
		this.permissionid = permissionid;
	}

	/**
	 * @return 设置 resourceid 的值.
	 */
	public Long getResourceid() {
		return resourceid;
	}

	/**
	 * 设置 resourceid 的值.
	 * 
	 * @param resourceid
	 *            赋值给 resourceid.
	 */
	public void setResourceid(Long resourceid) {
		this.resourceid = resourceid;
	}

}
