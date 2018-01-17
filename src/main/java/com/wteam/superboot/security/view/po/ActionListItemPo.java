/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.view.po;

import java.util.List;

import com.wteam.superboot.core.entity.po.BasePersistentObject;
import com.wteam.superboot.security.entity.po.AuthitemPo;

/**
 * 接口列表条目视图类.
 * 
 * @author 侯骏雄
 * @since 5.0.1
 */
public class ActionListItemPo extends BasePersistentObject {
    /**
     * 接口编号.
     */
    private Long actionid;

    /**
     * 接口名.
     */
    private String actionname;

    /**
     * 该接口对应的行为列表.
     */
    private List<AuthitemPo> permissionList;

    /**
     * @return 获取的actionid
     */
    public final Long getActionid() {
        return actionid;
    }

    /**
     * 设置actionid的方法.
     * 
     * @param actionid
     *            赋值给actionid的值
     */
    public final void setActionid(Long actionid) {
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

    /**
     * @return 获取的permissionList
     */
    public final List<AuthitemPo> getPermissionList() {
        return permissionList;
    }

    /**
     * 设置permissionList的方法.
     * 
     * @param permissionList
     *            赋值给permissionList的值
     */
    public final void setPermissionList(List<AuthitemPo> permissionList) {
        this.permissionList = permissionList;
    }

}
