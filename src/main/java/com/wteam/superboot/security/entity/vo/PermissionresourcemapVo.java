/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.entity.vo;

import com.wteam.superboot.core.entity.vo.BaseValueObject;

/**
 * 行为资源映射数据值对象类.
 * 
 */
public class PermissionresourcemapVo extends BaseValueObject {
    /**
     * 行为资源映射编号.
     */
    private String permissionresourcemapid;

    /**
     * 行为编号.
     */
    private String permissionid;

    /**
     * 资源编号.
     */
    private String resourceid;

    /**
     * @return 获取的permissionresourcemapid
     */
    public final String getPermissionresourcemapid() {
        return permissionresourcemapid;
    }

    /**
     * 设置permissionresourcemapid的方法.
     * 
     * @param permissionresourcemapid
     *            赋值给permissionresourcemapid的值
     */
    public final void setPermissionresourcemapid(String permissionresourcemapid) {
        this.permissionresourcemapid = permissionresourcemapid;
    }

    /**
     * @return 获取的permissionid
     */
    public final String getPermissionid() {
        return permissionid;
    }

    /**
     * 设置permissionid的方法.
     * 
     * @param permissionid
     *            赋值给permissionid的值
     */
    public final void setPermissionid(String permissionid) {
        this.permissionid = permissionid;
    }

    /**
     * @return 获取的resourceid
     */
    public final String getResourceid() {
        return resourceid;
    }

    /**
     * 设置resourceid的方法.
     * 
     * @param resourceid
     *            赋值给resourceid的值
     */
    public final void setResourceid(String resourceid) {
        this.resourceid = resourceid;
    }

}
