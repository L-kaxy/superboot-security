/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
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
import com.wteam.superboot.security.entity.po.PermissionresourcemapPo;
import com.wteam.superboot.security.entity.po.ResourcePo;
import com.wteam.superboot.security.entity.po.ResourcetypePo;
import com.wteam.superboot.security.entity.po.UserkeyPo;
import com.wteam.superboot.security.repository.ActionRepository;
import com.wteam.superboot.security.repository.AuthitemRepository;
import com.wteam.superboot.security.repository.PermissionresourcemapRepository;
import com.wteam.superboot.security.repository.ResourceRepository;
import com.wteam.superboot.security.repository.ResourcetypeRepository;
import com.wteam.superboot.security.repository.UserkeyRepository;

/**
 * 登录后Realm类.
 * 
 */
@Component
public class LoggingRealm extends AuthorizingRealm {

	@Autowired
	private UserkeyRepository userkeyRepository;

	@Autowired
	private ResourcetypeRepository resourcetypeRepository;

	@Autowired
	private AuthitemRepository authitemRepository;

	@Autowired
	private PermissionresourcemapRepository permissionresourcemapRepository;

	@Autowired
	private ResourceRepository resourceRepository;

	@Autowired
	private ActionRepository actionRepository;

	/**
	 * 初始化登录后Realm.
	 * 
	 */
	public LoggingRealm() {
		// 这个名字必须匹配那个在用户类的getPrincipals()方法中的名字
		setName("LoggingRealm");
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

		Boolean hasAccount = false;
		Boolean auth = false;
		UserkeyPo userkey = new UserkeyPo();

		userkey.setLoginmsg(loginmsg);
		if (userkeyRepository.queryNonDeleteNonLockupCount(userkey) > 0) {
			hasAccount = true;
			userkey.setCredential(password);
			if (userkeyRepository.queryNonDeleteNonLockupCount(userkey) > 0) {
				auth = true;
				userkey = userkeyRepository.queryEntity(userkey);
			}
		}

		if (!hasAccount) {
			throw new UnknownAccountException();
		}
		if (!auth) {
			throw new IncorrectCredentialsException();
		}

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

		AuthitemPo permission = new AuthitemPo();
		permission.setAuthitemtype(true);
		permission.setAuthitemname("登录后");
		permission = authitemRepository.queryEntity(permission);

		PermissionresourcemapPo map = new PermissionresourcemapPo();
		map.setPermissionid(permission.getAuthitemid());

		ResourcePo resource = null;
		ActionPo action = null;
		for (PermissionresourcemapPo po : permissionresourcemapRepository.queryNonDeleteNonLockupList(map)) {
			resource = resourceRepository.getEntityById(ResourcePo.class, po.getResourceid());
			if (resource.getResourcetypeid() == type.getResourcetypeid()) {
				action = actionRepository.getEntityById(ActionPo.class, resource.getRealid());
				result.addStringPermission(action.getActionname());
			}
		}

		return result;
	}

}
