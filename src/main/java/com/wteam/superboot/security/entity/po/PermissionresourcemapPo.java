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
 * 行为资源映射持久层类.
 * 
 */
@Entity
@Table(name = "t_permissionresourcemap")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PermissionresourcemapPo extends BasePersistentObject {

    /**
     * 行为资源映射编号.
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionresourcemapid;

    /**
     * 行为编号.
     */
    @Column(nullable = false)
    private Long permissionid;

    /**
     * 资源编号.
     */
    @Column(nullable = false)
    private Long resourceid;

    /**
     * @return 获取的permissionresourcemapid
     */
    public Long getPermissionresourcemapid() {
        return permissionresourcemapid;
    }

    /**
     * 设置permissionresourcemapid的方法.
     * 
     * @param permissionresourcemapid
     *            赋值给permissionresourcemapid的值
     */
    public final void setPermissionresourcemapid(Long permissionresourcemapid) {
        this.permissionresourcemapid = permissionresourcemapid;
    }

    /**
     * @return 获取的permissionid
     */
    public final Long getPermissionid() {
        return permissionid;
    }

    /**
     * 设置permissionid的方法.
     * 
     * @param permissionid
     *            赋值给permissionid的值
     */
    public final void setPermissionid(Long permissionid) {
        this.permissionid = permissionid;
    }

    /**
     * @return 获取的resourceid
     */
    public final Long getResourceid() {
        return resourceid;
    }

    /**
     * 设置resourceid的方法.
     * 
     * @param resourceid
     *            赋值给resourceid的值
     */
    public final void setResourceid(Long resourceid) {
        this.resourceid = resourceid;
    }

}
