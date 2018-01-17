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
 * 权限条目父子映射持久层类.
 * 
 */
@Entity
@Table(name = "t_authitemmap")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AuthitemmapPo extends BasePersistentObject {

    /**
     * 权限条目父子映射编号.
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authitemmapid;

    /**
     * 父权限条目编号.
     */
	@Column(nullable = false)
    private Long parentid;

    /**
     * 子权限条目编号.
     */
    @Column(nullable = false)
    private Long childid;

    /**
     * @return 获取的authitemmapid
     */
    public Long getAuthitemmapid() {
        return authitemmapid;
    }

    /**
     * 设置authitemmapid的方法.
     * 
     * @param authitemmapid
     *            赋值给authitemmapid的值
     */
    public void setAuthitemmapid(Long authitemmapid) {
        this.authitemmapid = authitemmapid;
    }

    /**
     * @return 获取的parentid
     */
    public Long getParentid() {
        return parentid;
    }

    /**
     * 设置parentid的方法.
     * 
     * @param parentid
     *            赋值给parentid的值
     */
    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    /**
     * @return 获取的childid
     */
    public Long getChildid() {
        return childid;
    }

    /**
     * 设置childid的方法.
     * 
     * @param childid
     *            赋值给childid的值
     */
    public void setChildid(Long childid) {
        this.childid = childid;
    }
}
