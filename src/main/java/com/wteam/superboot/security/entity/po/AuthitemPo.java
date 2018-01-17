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
 * 权限条目持久层类.
 * 
 */
@Entity
@Table(name = "t_authitem")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AuthitemPo extends BasePersistentObject {

    /**
     * 权限条目编号.
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authitemid;

    /**
     * 权限条目名.
     */
	@Column(nullable = false)
    private String authitemname;

    /**
     * 权限条目类型 true-权限 false-角色.
     */
	@Column(nullable = false)
    private Boolean authitemtype;

    /**
     * @return 获取的authitemid
     */
    public Long getAuthitemid() {
        return authitemid;
    }

    /**
     * 设置authitemid的方法.
     * 
     * @param authitemid
     *            赋值给authitemid的值
     */
    public void setAuthitemid(Long authitemid) {
        this.authitemid = authitemid;
    }

    /**
     * @return 获取的authitemname
     */
    public String getAuthitemname() {
        return authitemname;
    }

    /**
     * 设置authitemname的方法.
     * 
     * @param authitemname
     *            赋值给authitemname的值
     */
    public void setAuthitemname(String authitemname) {
        this.authitemname = authitemname;
    }

    /**
     * @return 获取的authitemtype
     */
    @Column(name = "authitemtype", nullable = false)
    public Boolean getAuthitemtype() {
        return authitemtype;
    }

    /**
     * 设置authitemtype的方法 true-权限 false-角色.
     * 
     * @param authitemtype
     *            赋值给authitemtype的值
     */
    public void setAuthitemtype(Boolean authitemtype) {
        this.authitemtype = authitemtype;
    }
}
