/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.entity.vo;

import com.wteam.superboot.core.entity.vo.BaseValueObject;

/**
 * 资源类型数据值对象类.
 * 
 */
public class ResourcetypeVo extends BaseValueObject {

    /**
     * 资源类型编号.
     */
    private String resourcetypeid;

    /**
     * 资源类型名称.
     */
    private String resourcetypename;

    /**
     * @return 获取的resourcetypeid
     */
    public final String getResourcetypeid() {
        return resourcetypeid;
    }

    /**
     * 设置resourcetypeid的方法.
     * 
     * @param resourcetypeid
     *            赋值给resourcetypeid的值
     */
    public final void setResourcetypeid(final String resourcetypeid) {
        this.resourcetypeid = resourcetypeid;
    }

    /**
     * @return 获取的resourcetypename
     */
    public final String getResourcetypename() {
        return resourcetypename;
    }

    /**
     * 设置resourcetypename的方法.
     * 
     * @param resourcetypename
     *            赋值给resourcetypename的值
     */
    public final void setResourcetypename(final String resourcetypename) {
        this.resourcetypename = resourcetypename;
    }

}
