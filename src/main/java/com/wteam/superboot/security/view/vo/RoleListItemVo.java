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
import com.wteam.superboot.security.view.po.RoleListItemPo;

/**
 * 角色列表条目视图数据值对象类.
 * 
 * @author 侯骏雄
 * @since 5.0.1
 */
public class RoleListItemVo extends BaseValueObject {
    /**
     * 角色编号.
     */
    private String roleid;

    /**
     * 角色名.
     */
    private String authitemname;

    /**
     * 权限条目类型 true-权限 false-角色.
     */
    private String authitemtype;

    /**
     * 该角色对应的行为列表.
     */
    private List<AuthitemVo> permissionList;

    /**
     * @return 获取的roleid
     */
    public final String getRoleid() {
        return roleid;
    }

    /**
     * 设置roleid的方法.
     * 
     * @param roleid
     *            赋值给roleid的值
     */
    public final void setRoleid(String roleid) {
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
    public final String getAuthitemtype() {
        return authitemtype;
    }

    /**
     * 设置authitemtype的方法.
     * 
     * @param authitemtype
     *            赋值给authitemtype的值
     */
    public final void setAuthitemtype(String authitemtype) {
        this.authitemtype = authitemtype;
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
    public final <T> void poViewToVo(final RoleListItemPo po) {
        if (po.getRoleid() != null) {
            roleid = String.valueOf(po.getRoleid());
        }
        authitemname = po.getAuthitemname();
        if (po.getAuthitemtype() != null) {
            authitemtype = String.valueOf(po.getAuthitemtype());
        }
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
