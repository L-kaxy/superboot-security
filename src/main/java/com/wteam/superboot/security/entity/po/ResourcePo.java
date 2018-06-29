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
 * 资源持久层类.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@Entity
@Table(name = "t_resource")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ResourcePo extends BasePersistentObject {

	/**
	 * 资源编号.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long resourceid;

	/**
	 * 资源类型.
	 */
	@Column(nullable = false)
	private Long resourcetypeid;

	/**
	 * 资源实际实体编号.
	 */
	@Column(nullable = false)
	private Long realid;

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

	/**
	 * @return 设置 resourcetypeid 的值.
	 */
	public Long getResourcetypeid() {
		return resourcetypeid;
	}

	/**
	 * 设置 resourcetypeid 的值.
	 * 
	 * @param resourcetypeid
	 *            赋值给 resourcetypeid.
	 */
	public void setResourcetypeid(Long resourcetypeid) {
		this.resourcetypeid = resourcetypeid;
	}

	/**
	 * @return 设置 realid 的值.
	 */
	public Long getRealid() {
		return realid;
	}

	/**
	 * 设置 realid 的值.
	 * 
	 * @param realid
	 *            赋值给 realid.
	 */
	public void setRealid(Long realid) {
		this.realid = realid;
	}

}
