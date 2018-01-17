/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.view.po;

import java.util.List;

import com.wteam.superboot.core.entity.po.BasePersistentObject;
import com.wteam.superboot.security.entity.po.AuthitemPo;

/**
 * 授权列表条目视图类.
 * 
 * @author 侯骏雄
 * @since 5.0.1
 */
public class UserkeyListItemPo extends BasePersistentObject {
    /**
     * 用户验证信息编号.
     */
    private Long userkeyid;

    /**
     * 用户编号.
     */
    private Long userid;

    /**
     * 用户登录名.
     */
    private String loginmsg;

    /**
     * 该用户对应的角色列表.
     */
    private List<AuthitemPo> roleList;

    /**
     * @return 获取的userkeyid
     */
    public Long getUserkeyid() {
        return userkeyid;
    }

    /**
     * 设置userkeyid的方法.
     * 
     * @param userkeyid
     *            赋值给userkeyid的值
     */
    public void setUserkeyid(Long userkeyid) {
        this.userkeyid = userkeyid;
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
     * @return 获取的loginmsg
     */
    public String getLoginmsg() {
        return loginmsg;
    }

    /**
     * 设置loginmsg的方法.
     * 
     * @param loginmsg
     *            赋值给loginmsg的值
     */
    public void setLoginmsg(String loginmsg) {
        this.loginmsg = loginmsg;
    }

    /**
     * @return 获取的roleList
     */
    public List<AuthitemPo> getRoleList() {
        return roleList;
    }

    /**
     * 设置roleList的方法.
     * 
     * @param roleList
     *            赋值给roleList的值
     */
    public void setRoleList(List<AuthitemPo> roleList) {
        this.roleList = roleList;
    }

}
