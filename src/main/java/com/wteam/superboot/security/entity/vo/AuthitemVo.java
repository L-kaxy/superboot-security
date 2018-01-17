/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.entity.vo;

import com.wteam.superboot.core.entity.vo.BaseValueObject;

/**
 * 权限条目数据值对象类.
 * 
 */
public class AuthitemVo extends BaseValueObject {
    /**
     * 权限条目编号.
     */
    private String authitemid;

    /**
     * 权限条目名.
     */
    private String authitemname;

    /**
     * 权限条目类型 true-权限 false-角色.
     */
    private String authitemtype;

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

    /**
     * @return 获取的authitemname
     */
    public final String getAuthitemname() {
        return authitemname;
    }

    /**
     * 设置authitemname的方法.
     * 
     * @param authitemname
     *            赋值给authitemname的值
     */
    public final void setAuthitemname(String authitemname) {
        this.authitemname = authitemname;
    }

    /**
     * @return 获取的authitemtype
     */
    public final String getAuthitemtype() {
        return authitemtype;
    }

    /**
     * 设置authitemtype的方法 true-权限 false-角色.
     * 
     * @param authitemtype
     *            赋值给authitemtype的值
     */
    public final void setAuthitemtype(String authitemtype) {
        this.authitemtype = authitemtype;
    }

}
