/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.entity.vo;

import com.wteam.superboot.core.entity.vo.BaseValueObject;

/**
 * 用户验证信息数据值对象类.
 * 
 */
public class UserkeyVo extends BaseValueObject {
    /**
     * 用户验证信息编号.
     */
    private String userkeyid;

    /**
     * 用户编号.
     */
    private String userid;

    /**
     * 登录类型.
     */
    private String logintype;

    /**
     * 用户登录名.
     */
    private String loginmsg;

    /**
     * 用户登录凭证.
     */
    private String credential;

    /**
     * @return 获取的userkeyid
     */
    public String getUserkeyid() {
        return userkeyid;
    }

    /**
     * 设置userkeyid的方法.
     * 
     * @param userkeyid
     *            赋值给userkeyid的值
     */
    public void setUserkeyid(String userkeyid) {
        this.userkeyid = userkeyid;
    }

    /**
     * @return 获取的userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置userid的方法.
     * 
     * @param userid
     *            赋值给userid的值
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return 获取的logintype
     */
    public final String getLogintype() {
        return logintype;
    }

    /**
     * 设置logintype的方法.
     * 
     * @param logintype
     *            赋值给logintype的值
     */
    public final void setLogintype(String logintype) {
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
