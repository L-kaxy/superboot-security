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
 * 用户验证信息持久层类.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@Entity
@Table(name = "t_userkey")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserkeyPo extends BasePersistentObject {

	/**
	 * 用户验证信息编号.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userkeyid;

	/**
	 * 用户编号.
	 */
	@Column(nullable = false)
	private Long userid;

	/**
	 * 登录类型.
	 */
	@Column(nullable = false)
	private Integer logintype;

	/**
	 * 用户登录名.
	 */
	@Column(nullable = false)
	private String loginmsg;

	/**
	 * 用户登录凭证.
	 */
	@Column(nullable = true)
	private String credential;

	/**
	 * @return 设置 userkeyid 的值.
	 */
	public Long getUserkeyid() {
		return userkeyid;
	}

	/**
	 * 设置 userkeyid 的值.
	 * 
	 * @param userkeyid
	 *            赋值给 userkeyid.
	 */
	public void setUserkeyid(Long userkeyid) {
		this.userkeyid = userkeyid;
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
	 * @return 设置 logintype 的值.
	 */
	public Integer getLogintype() {
		return logintype;
	}

	/**
	 * 设置 logintype 的值.
	 * 
	 * @param logintype
	 *            赋值给 logintype.
	 */
	public void setLogintype(Integer logintype) {
		this.logintype = logintype;
	}

	/**
	 * @return 设置 loginmsg 的值.
	 */
	public String getLoginmsg() {
		return loginmsg;
	}

	/**
	 * 设置 loginmsg 的值.
	 * 
	 * @param loginmsg
	 *            赋值给 loginmsg.
	 */
	public void setLoginmsg(String loginmsg) {
		this.loginmsg = loginmsg;
	}

	/**
	 * @return 设置 credential 的值.
	 */
	public String getCredential() {
		return credential;
	}

	/**
	 * 设置 credential 的值.
	 * 
	 * @param credential
	 *            赋值给 credential.
	 */
	public void setCredential(String credential) {
		this.credential = credential;
	}

}
