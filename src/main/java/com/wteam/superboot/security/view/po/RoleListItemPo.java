/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.view.po;

import java.util.List;

import com.wteam.superboot.core.entity.po.BasePersistentObject;
import com.wteam.superboot.security.entity.po.AuthitemPo;

/**
 * 角色列表条目视图类.
 * 
 * @author 侯骏雄
 * @since 5.0.1
 */
public class RoleListItemPo extends BasePersistentObject {
    /**
     * 角色编号.
     */
    private Long roleid;

    /**
     * 角色名.
     */
    private String authitemname;

    /**
     * 权限条目类型 true-权限 false-角色.
     */
    private Boolean authitemtype;

    /**
     * 该角色对应的行为列表.
     */
    private List<AuthitemPo> permissionList;

    /**
     * @return 获取的roleid
     */
    public final Long getRoleid() {
        return roleid;
    }

    /**
     * 设置roleid的方法.
     * 
     * @param roleid
     *            赋值给roleid的值
     */
    public final void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    /**
     * @return 获取的authitemname
     */
    public final String getAuthitemname() {
        return authitemname;
    }

    /**
     * 设置authitemname的方法.
     * 
     * @param authitemname
     *            赋值给authitemname的值
     */
    public final void setAuthitemname(String authitemname) {
        this.authitemname = authitemname;
    }

    /**
     * @return 获取的authitemtype
     */
    public final Boolean getAuthitemtype() {
        return authitemtype;
    }

    /**
     * 设置authitemtype的方法.
     * 
     * @param authitemtype
     *            赋值给authitemtype的值
     */
    public final void setAuthitemtype(Boolean authitemtype) {
        this.authitemtype = authitemtype;
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
