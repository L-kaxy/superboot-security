/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.entity.vo;

import java.util.Date;
import java.util.List;

import com.wteam.superboot.security.entity.po.AuthitemPo;

/**
 * 授权列表条目视图数据值对象类.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
public class UserkeyListItemVo {
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
	 * 创建时间.
	 */
	private Date createtime;

	/**
	 * @return 设置 userkeyid 的值.
	 */
	public Long getUserkeyid() {
		return userkeyid;
	}

	/**
	 * 设置 userkeyid 的值.
	 * 
	 * @param userkeyid
	 *            赋值给 userkeyid.
	 */
	public void setUserkeyid(Long userkeyid) {
		this.userkeyid = userkeyid;
	}

	/**
	 * @return 设置 userid 的值.
	 */
	public Long getUserid() {
		return userid;
	}

	/**
	 * 设置 userid 的值.
	 * 
	 * @param userid
	 *            赋值给 userid.
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}

	/**
	 * @return 设置 loginmsg 的值.
	 */
	public String getLoginmsg() {
		return loginmsg;
	}

	/**
	 * 设置 loginmsg 的值.
	 * 
	 * @param loginmsg
	 *            赋值给 loginmsg.
	 */
	public void setLoginmsg(String loginmsg) {
		this.loginmsg = loginmsg;
	}

	/**
	 * @return 设置 roleList 的值.
	 */
	public List<AuthitemPo> getRoleList() {
		return roleList;
	}

	/**
	 * 设置 roleList 的值.
	 * 
	 * @param roleList
	 *            赋值给 roleList.
	 */
	public void setRoleList(List<AuthitemPo> roleList) {
		this.roleList = roleList;
	}

	/**
	 * @return 设置 createtime 的值.
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * 设置 createtime 的值.
	 * 
	 * @param createtime
	 *            赋值给 createtime.
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
