/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.view.vo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.wteam.superboot.core.entity.vo.BaseValueObject;
import com.wteam.superboot.security.entity.po.AuthitemPo;
import com.wteam.superboot.security.entity.vo.AuthitemVo;
import com.wteam.superboot.security.view.po.ActionListItemPo;

/**
 * 接口列表条目视图数据值对象类.
 * 
 * @author 侯骏雄
 * @since 5.0.1
 */
public class ActionListItemVo extends BaseValueObject {
    /**
     * 接口编号.
     */
    private String actionid;

    /**
     * 角色名.
     */
    private String actionname;

    /**
     * 该接口对应的行为列表.
     */
    private List<AuthitemVo> permissionList;

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

    /**
     * @return 获取的permissionList
     */
    public final List<AuthitemVo> getPermissionList() {
        return permissionList;
    }

    /**
     * 设置permissionList的方法.
     * 
     * @param permissionList
     *            赋值给permissionList的值
     */
    public final void setPermissionList(List<AuthitemVo> permissionList) {
        this.permissionList = permissionList;
    }

    /**
     * 持久层对象转换为值对象.
     * 
     * @param po
     *            数据持久层对象.
     * @param <T>
     *            数据持久层对象类型.
     * 
     * @author 侯骏雄
     * @since 5.0.1
     */
    public final <T> void poViewToVo(final ActionListItemPo po) {
        if (po.getActionid() != null) {
            actionid = String.valueOf(po.getActionid());
        }
        actionname = po.getActionname();
        setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(po
                .getCreatetime()));
        permissionList = new ArrayList<AuthitemVo>();
        AuthitemVo aVo = null;
        for (AuthitemPo aPo : po.getPermissionList()) {
            aVo = new AuthitemVo();
            aVo.poToVo(aPo);
            permissionList.add(aVo);
        }
    }
}
