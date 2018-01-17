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
import com.wteam.superboot.security.view.po.UserkeyListItemPo;

/**
 * 授权列表条目视图数据值对象类.
 * 
 * @author 侯骏雄
 * @since 5.0.1
 */
public class UserkeyListItemVo extends BaseValueObject {
    /**
     * 用户验证信息编号.
     */
    private String userkeyid;

    /**
     * 用户编号.
     */
    private String userid;

    /**
     * 用户登录名.
     */
    private String loginmsg;

    /**
     * 该用户对应的角色列表.
     */
    private List<AuthitemVo> roleList;

    /**
     * @return 获取的userkeyid
     */
    public String getUserkeyid() {
        return userkeyid;
    }

    /**
     * 设置userkeyid的方法.
     * 
     * @param userkeyid
     *            赋值给userkeyid的值
     */
    public void setUserkeyid(String userkeyid) {
        this.userkeyid = userkeyid;
    }

    /**
     * @return 获取的userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置userid的方法.
     * 
     * @param userid
     *            赋值给userid的值
     */
    public void setUserid(String userid) {
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
    public List<AuthitemVo> getRoleList() {
        return roleList;
    }

    /**
     * 设置roleList的方法.
     * 
     * @param roleList
     *            赋值给roleList的值
     */
    public void setRoleList(List<AuthitemVo> roleList) {
        this.roleList = roleList;
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
    public final <T> void poViewToVo(final UserkeyListItemPo po) {
        if (po.getUserkeyid() != null) {
            userkeyid = String.valueOf(po.getUserkeyid());
        }
        if (po.getUserid() != null) {
            userid = String.valueOf(po.getUserid());
        }
        loginmsg = po.getLoginmsg();
        setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(po
                .getCreatetime()));
        roleList = new ArrayList<AuthitemVo>();
        AuthitemVo aVo = null;
        for (AuthitemPo aPo : po.getRoleList()) {
            aVo = new AuthitemVo();
            aVo.poToVo(aPo);
            roleList.add(aVo);
        }
    }
}
