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
 * 资源类型持久层类.
 * 
 */
@Entity
@Table(name = "t_resourcetype")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ResourcetypePo extends BasePersistentObject {

    /**
     * 资源类型编号.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourcetypeid;

    /**
     * 资源类型名称.
     */
    @Column(nullable = false)
    private String resourcetypename;

    /**
     * @return 获取的resourcetypeid
     */
    public Long getResourcetypeid() {
        return resourcetypeid;
    }

    /**
     * 设置resourcetypeid的方法.
     * 
     * @param resourcetypeid
     *            赋值给resourcetypeid的值
     */
    public void setResourcetypeid(Long resourcetypeid) {
        this.resourcetypeid = resourcetypeid;
    }

    /**
     * @return 获取的resourcetypename
     */
    public String getResourcetypename() {
        return resourcetypename;
    }

    /**
     * 设置resourcetypename的方法.
     * 
     * @param resourcetypename
     *            赋值给resourcetypename的值
     */
    public void setResourcetypename(String resourcetypename) {
        this.resourcetypename = resourcetypename;
    }
}
