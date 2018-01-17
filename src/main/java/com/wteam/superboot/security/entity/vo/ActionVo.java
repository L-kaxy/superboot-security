/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.entity.vo;

import com.wteam.superboot.core.entity.vo.BaseValueObject;

/**
 * 接口数据值对象类.
 * 
 */
public class ActionVo extends BaseValueObject {

    /**
     * 接口编号.
     */
    private String actionid;

    /**
     * 接口名.
     */
    private String actionname;

    /**
     * @return 获取的actionid
     */
    public final String getActionid() {
        return actionid;
    }

    /**
     * 设置actionid的方法.
     * 
     * @param actionid
     *            赋值给actionid的值
     */
    public final void setActionid(String actionid) {
        this.actionid = actionid;
    }

    /**
     * @return 获取的actionname
     */
    public final String getActionname() {
        return actionname;
    }

    /**
     * 设置actionname的方法.
     * 
     * @param actionname
     *            赋值给actionname的值
     */
    public final void setActionname(String actionname) {
        this.actionname = actionname;
    }

}
