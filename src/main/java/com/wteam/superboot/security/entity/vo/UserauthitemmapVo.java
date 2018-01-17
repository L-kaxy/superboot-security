/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.entity.vo;

import com.wteam.superboot.core.entity.vo.BaseValueObject;

/**
 * 用户权限条目映射数据值对象类.
 * 
 */
public class UserauthitemmapVo extends BaseValueObject {
    /**
     * 用户权限条目映射编号.
     */
    private String userauthitemmapid;

    /**
     * 用户编号.
     */
    private String userid;

    /**
     * 权限条目编号.
     */
    private String authitemid;

    /**
     * @return 获取的userauthitemmapid
     */
    public final String getUserauthitemmapid() {
        return userauthitemmapid;
    }

    /**
     * 设置userauthitemmapid的方法.
     * 
     * @param userauthitemmapid
     *            赋值给userauthitemmapid的值
     */
    public final void setUserauthitemmapid(String userauthitemmapid) {
        this.userauthitemmapid = userauthitemmapid;
    }

    /**
     * @return 获取的userid
     */
    public final String getUserid() {
        return userid;
    }

    /**
     * 设置userid的方法.
     * 
     * @param userid
     *            赋值给userid的值
     */
    public final void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return 获取的authitemid
     */
    public final String getAuthitemid() {
        return authitemid;
    }

    /**
     * 设置authitemid的方法.
     * 
     * @param authitemid
     *            赋值给authitemid的值
     */
    public final void setAuthitemid(String authitemid) {
        this.authitemid = authitemid;
    }

}
