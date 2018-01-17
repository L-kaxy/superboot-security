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
 * 用户权限条目映射持久层类.
 * 
 */
@Entity
@Table(name = "t_userauthitemmap")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserauthitemmapPo extends BasePersistentObject {

    /**
     * 用户权限条目映射编号.
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userauthitemmapid;

    /**
     * 用户编号.
     */
	@Column(nullable = false)
    private Long userid;

    /**
     * 权限条目编号.
     */
	@Column(nullable = false)
    private Long authitemid;

    /**
     * @return 获取的userauthitemmapid
     */
    public Long getUserauthitemmapid() {
        return userauthitemmapid;
    }

    /**
     * 设置userauthitemmapid的方法.
     * 
     * @param userauthitemmapid
     *            赋值给userauthitemmapid的值
     */
    public void setUserauthitemmapid(Long userauthitemmapid) {
        this.userauthitemmapid = userauthitemmapid;
    }

    /**
     * @return 获取的userid
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 设置userid的方法.
     * 
     * @param userid
     *            赋值给userid的值
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

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
}
