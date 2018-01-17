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
 * 资源持久层类.
 * 
 */
@Entity
@Table(name = "t_resource")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ResourcePo extends BasePersistentObject {

    /**
     * 资源编号.
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceid;

    /**
     * 资源类型.
     */
	@Column(nullable = false)
    private Long resourcetypeid;

    /**
     * 资源实际实体编号.
     */
	@Column(nullable = false)
    private Long realid;

    /**
     * @return 获取的resourceid
     */
    public Long getResourceid() {
        return resourceid;
    }

    /**
     * 设置resourceid的方法.
     * 
     * @param resourceid
     *            赋值给resourceid的值
     */
    public void setResourceid(Long resourceid) {
        this.resourceid = resourceid;
    }

    /**
     * @return 获取的resourcetype
     */
    public Long getResourcetypeid() {
        return resourcetypeid;
    }

    /**
     * 设置resourcetype的方法.
     * 
     * @param resourcetype
     *            赋值给resourcetype的值
     */
    public void setResourcetypeid(Long resourcetypeid) {
        this.resourcetypeid = resourcetypeid;
    }

    /**
     * @return 获取的realid
     */
    public Long getRealid() {
        return realid;
    }

    /**
     * 设置realid的方法.
     * 
     * @param realid
     *            赋值给realid的值
     */
    public void setRealid(Long realid) {
        this.realid = realid;
    }
}
