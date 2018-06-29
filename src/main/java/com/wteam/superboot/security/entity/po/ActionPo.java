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
 * 接口持久层类.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@Entity
@Table(name = "t_action")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ActionPo extends BasePersistentObject {

	/**
	 * 接口编号.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long actionid;

	/**
	 * 接口名.
	 */
	@Column(nullable = false)
	private String actionname;

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

}
