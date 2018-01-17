/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.entity.vo;

import com.wteam.superboot.core.entity.vo.BaseValueObject;

/**
 * 权限条目父子映射数据值对象类.
 * 
 */
public class AuthitemmapVo extends BaseValueObject {
    /**
     * 权限条目父子映射编号.
     */
    private String authitemmapid;

    /**
     * 父权限条目编号.
     */
    private String parentid;

    /**
     * 子权限条目编号.
     */
    private String childid;

    /**
     * @return 获取的authitemmapid
     */
    public final String getAuthitemmapid() {
        return authitemmapid;
    }

    /**
     * 设置authitemmapid的方法.
     * 
     * @param authitemmapid
     *            赋值给authitemmapid的值
     */
    public final void setAuthitemmapid(String authitemmapid) {
        this.authitemmapid = authitemmapid;
    }

    /**
     * @return 获取的parentid
     */
    public final String getParentid() {
        return parentid;
    }

    /**
     * 设置parentid的方法.
     * 
     * @param parentid
     *            赋值给parentid的值
     */
    public final void setParentid(String parentid) {
        this.parentid = parentid;
    }

    /**
     * @return 获取的childid
     */
    public final String getChildid() {
        return childid;
    }

    /**
     * 设置childid的方法.
     * 
     * @param childid
     *            赋值给childid的值
     */
    public final void setChildid(String childid) {
        this.childid = childid;
    }

}
