/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.realm;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wteam.superboot.security.entity.po.ActionPo;
import com.wteam.superboot.security.entity.po.AuthitemPo;
import com.wteam.superboot.security.entity.po.AuthitemmapPo;
import com.wteam.superboot.security.entity.po.PermissionresourcemapPo;
import com.wteam.superboot.security.entity.po.ResourcePo;
import com.wteam.superboot.security.entity.po.ResourcetypePo;
import com.wteam.superboot.security.entity.po.UserauthitemmapPo;
import com.wteam.superboot.security.entity.po.UserkeyPo;
import com.wteam.superboot.security.repository.ActionRepository;
import com.wteam.superboot.security.repository.AuthitemRepository;
import com.wteam.superboot.security.repository.AuthitemmapRepository;
import com.wteam.superboot.security.repository.PermissionresourcemapRepository;
import com.wteam.superboot.security.repository.ResourceRepository;
import com.wteam.superboot.security.repository.ResourcetypeRepository;
import com.wteam.superboot.security.repository.UserauthitemmapRepository;
import com.wteam.superboot.security.repository.UserkeyRepository;

/**
 * 接口 Realm 类.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@Component
public class ActionRealm extends AuthorizingRealm {

	/**
	 * 注入 Repository.
	 */
	@Autowired
	private UserkeyRepository userkeyRepository;
	@Autowired
	private ResourcetypeRepository resourcetypeRepository;
	@Autowired
	private UserauthitemmapRepository userauthitemmapRepository;
	@Autowired
	private AuthitemRepository authitemRepository;
	@Autowired
	private AuthitemmapRepository authitemmapRepository;
	@Autowired
	private PermissionresourcemapRepository permissionresourcemapRepository;
	@Autowired
	private ResourceRepository resourceRepository;
	@Autowired
	private ActionRepository actionRepository;

	/**
	 * 初始化接口Realm.
	 */
	public ActionRealm() {
		// 这个名字必须匹配那个在用户类的getPrincipals()方法中的名字
		setName("ActionRealm");
	}

	/**
	 * 验证用户.
	 * 
	 * @param token
	 *            用户登录验证信息
	 * @return 验证结果信息
	 */
	protected final AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token) {

		AuthenticationInfo result = null;

		String loginmsg = (String) token.getPrincipal(); // 得到登录信息
		String password = new String((char[]) token.getCredentials()); // 得到登录凭证

		UserkeyPo userkey = new UserkeyPo();

		userkey.setLoginmsg(loginmsg);
		if (userkeyRepository.queryNonDeleteNonLockupCount(userkey) == 0) {
			throw new UnknownAccountException();
		}
		userkey = userkeyRepository.queryEntity(userkey);

		result = new SimpleAuthenticationInfo(userkey.getUserid(), password, getName());

		return result;
	}

	/**
	 * 获取登录后权限接口验证.
	 * 
	 * @param principals
	 *            权限
	 * @return 验证结果信息
	 */
	protected final AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
		SimpleAuthorizationInfo result = new SimpleAuthorizationInfo();

		ResourcetypePo type = new ResourcetypePo();
		type.setResourcetypename("action");
		type = resourcetypeRepository.queryEntity(type);

		UserauthitemmapPo userauthitem = new UserauthitemmapPo();
		userauthitem.setUserid((Long) principals.getPrimaryPrincipal());
		AuthitemmapPo authitemmap = null;
		AuthitemPo auth = null;
		Set<AuthitemPo> permissionSet = new LinkedHashSet<AuthitemPo>();
		for (UserauthitemmapPo po : userauthitemmapRepository.queryNonDeleteNonLockupList(userauthitem)) {
			auth = authitemRepository.getEntityById(AuthitemPo.class, po.getAuthitemid());

			if (auth.getAuthitemtype()) {
				permissionSet.add(auth);
			} else {
				authitemmap = new AuthitemmapPo();
				authitemmap.setParentid(po.getAuthitemid());
				for (AuthitemmapPo po2 : authitemmapRepository.queryNonDeleteNonLockupList(authitemmap)) {
					permissionSet.add(authitemRepository.getEntityById(AuthitemPo.class, po2.getChildid()));
				}
			}
		}

		PermissionresourcemapPo permissionresource = null;
		ResourcePo resource = null;
		ActionPo action = null;
		for (AuthitemPo permissionTemp : permissionSet) {
			permissionresource = new PermissionresourcemapPo();
			permissionresource.setPermissionid(permissionTemp.getAuthitemid());
			for (PermissionresourcemapPo po : permissionresourcemapRepository
					.queryNonDeleteNonLockupList(permissionresource)) {
				resource = resourceRepository.getEntityById(ResourcePo.class, po.getResourceid());
				if (resource.getResourcetypeid() == type.getResourcetypeid()) {
					action = actionRepository.getEntityById(ActionPo.class, resource.getRealid());
					result.addStringPermission(action.getActionname());
				}
			}
		}

		return result;
	}

}
