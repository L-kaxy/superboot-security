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
 * 接口持久层类.
 * 
 */
@Entity
@Table(name = "t_action")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ActionPo extends BasePersistentObject {

    /**
     * 接口编号.
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actionid;

    /**
     * 接口名.
     */
	@Column(nullable = false)
    private String actionname;

    /**
     * @return 获取的actionid
     */
    public Long getActionid() {
        return actionid;
    }

    /**
     * 设置actionid的方法.
     * 
     * @param actionid
     *            赋值给actionid的值
     */
    public void setActionid(Long actionid) {
        this.actionid = actionid;
    }

    /**
     * @return 获取的actionname
     */
    public String getActionname() {
        return actionname;
    }

    /**
     * 设置actionname的方法.
     * 
     * @param actionname
     *            赋值给actionname的值
     */
    public void setActionname(String actionname) {
        this.actionname = actionname;
    }
}
