/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.entity.vo;

import com.wteam.superboot.core.entity.vo.BaseValueObject;

/**
 * 资源数据值对象类.
 * 
 */
public class ResourceVo extends BaseValueObject {
    /**
     * 资源编号.
     */
    private String resourceid;

    /**
     * 资源类型.
     */
    private String resourcetypeid;

    /**
     * 资源实际实体编号.
     */
    private String realid;

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

    /**
     * @return 获取的resourcetype
     */
    public final String getResourcetypeid() {
        return resourcetypeid;
    }

    /**
     * 设置resourcetype的方法.
     * 
     * @param resourcetype
     *            赋值给resourcetype的值
     */
    public final void setResourcetypeid(String resourcetypeid) {
        this.resourcetypeid = resourcetypeid;
    }

    /**
     * @return 获取的realid
     */
    public final String getRealid() {
        return realid;
    }

    /**
     * 设置realid的方法.
     * 
     * @param realid
     *            赋值给realid的值
     */
    public final void setRealid(String realid) {
        this.realid = realid;
    }

}
