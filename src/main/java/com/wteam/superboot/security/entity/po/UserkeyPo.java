/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
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
     * @return 获取的userkeyid
     */
    public Long getUserkeyid() {
        return userkeyid;
    }

    /**
     * 设置userkeyid的方法.
     * 
     * @param userkeyid
     *            赋值给userkeyid的值
     */
    public void setUserkeyid(Long userkeyid) {
        this.userkeyid = userkeyid;
    }

    /**
     * @return 获取的userid
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 设置userid的方法.
     * 
     * @param userid
     *            赋值给userid的值
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * @return 获取的logintype
     */
    public final Integer getLogintype() {
        return logintype;
    }

    /**
     * 设置logintype的方法.
     * 
     * @param logintype
     *            赋值给logintype的值
     */
    public final void setLogintype(Integer logintype) {
        this.logintype = logintype;
    }

    /**
     * @return 获取的loginmsg
     */
    public String getLoginmsg() {
        return loginmsg;
    }

    /**
     * 设置loginmsg的方法.
     * 
     * @param loginmsg
     *            赋值给loginmsg的值
     */
    public void setLoginmsg(String loginmsg) {
        this.loginmsg = loginmsg;
    }

    /**
     * @return 获取的credential
     */
    public String getCredential() {
        return credential;
    }

    /**
     * 设置credential的方法.
     * 
     * @param credential
     *            赋值给credential的值
     */
    public void setCredential(String credential) {
        this.credential = credential;
    }

}
