/**
 * Copyright (c) 2007-2015 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.helper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.ShiroHttpServletResponse;
import org.apache.shiro.web.subject.WebSubject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import com.wteam.superboot.core.entity.po.SystemconfigPo;
import com.wteam.superboot.core.repository.SystemconfigRepository;

/**
 * shiro权限认证工具类.
 * 
 */
@Component
public class ShiroHelper implements ApplicationContextAware {

	@Autowired
	private SystemconfigRepository repository;

	/**
	 * Spring 应用上下文环境
	 */
	private ApplicationContext applicationContext;

	/**
	 * 权限管理中心.
	 */
	private static WebSecurityManager securityManager;

	/**
	 * 初始化Shiro数据源.
	 */
	static {
	}

	/**
	 * shiro权限认证工具类的私有构造方法，其作用是为了防止用户显式生成工具类的实例对象.
	 * 
	 */
	public ShiroHelper() {
	}

	@PostConstruct
	public void init() {

		SystemconfigPo systemconfigPo = new SystemconfigPo();
		systemconfigPo.setConfigname("realm");
		List<SystemconfigPo> systemconfigList = repository.queryNonDeleteList(systemconfigPo);

		List<Realm> realms = new ArrayList<Realm>();
		if (systemconfigList != null) {
			for (SystemconfigPo po : systemconfigList) {
				Realm realm;
				try {
					realm = (Realm) applicationContext.getBean(Class.forName(po.getConfigvalue()));
					realms.add(realm);
				} catch (BeansException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager(realms);
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		authenticator.setAuthenticationStrategy(new AllSuccessfulStrategy());
		defaultSecurityManager.setAuthenticator(authenticator);
		authenticator.setRealms(realms);

		securityManager = defaultSecurityManager;
		SecurityUtils.setSecurityManager(securityManager);
	}

	/**
	 * 获取用户.
	 * 
	 * @param request
	 *            ServletRequest
	 * @param response
	 *            ServletResponse
	 * @return 当前用户
	 */
	public static Subject getSubject(final ServletRequest request, final ServletResponse response) {
		ServletRequest toUseRequest = request;
		if (request instanceof HttpServletRequest) {
			HttpServletRequest http = (HttpServletRequest) request;
			toUseRequest = new ShiroHttpServletRequest(http, request.getServletContext(),
					securityManager.isHttpSessionMode());
			http.getSession().setMaxInactiveInterval(30);
		}
		ServletResponse toUseResponse = response;
		if (!securityManager.isHttpSessionMode() && (request instanceof ShiroHttpServletRequest)
				&& (response instanceof HttpServletResponse)) {
			// ShiroHttpServletResponse是为了支持基于session的ids的URL重定向而设计的.
			// 只有在使用ShiroSessions(不是HttpSession)的时候才需要此类对象
			toUseResponse = new ShiroHttpServletResponse((HttpServletResponse) response,
					ContextLoader.getCurrentWebApplicationContext().getServletContext(),
					(ShiroHttpServletRequest) request);
		}

		Subject subject = null;
		subject = new WebSubject.Builder(securityManager, toUseRequest, toUseResponse).buildWebSubject();
		return subject;
	}

	/**
	 * 用户登录认证.
	 * 
	 * @param loginmsg
	 *            登录信息.
	 * @param credential
	 *            登录凭证.
	 * @param request
	 *            ServletRequest
	 * @param response
	 *            ServletResponse
	 */
	public static void login(final String loginmsg, final String credential, final ServletRequest request,
			final ServletResponse response) {
		Subject currentUser = getSubject(request, response);
		UsernamePasswordToken token = new UsernamePasswordToken(loginmsg, credential);
		token.setRememberMe(true);
		currentUser.login(token);
	}

	/**
	 * 用户登出.
	 * 
	 * @param request
	 *            ServletRequest
	 * @param response
	 *            ServletResponse
	 */
	public static void logout(final ServletRequest request, final ServletResponse response) {
		Subject currentUser = getSubject(request, response);
		currentUser.logout();
	}

	/**
	 * 是否记住.
	 * 
	 * @param request
	 *            ServletRequest
	 * @param response
	 *            ServletResponse
	 * 
	 * @return true-已记住 false-未记住.
	 */
	public static Boolean isRemembered(final ServletRequest request, final ServletResponse response) {
		Boolean result = false;
		Subject currentUser = getSubject(request, response);
		result = currentUser.isRemembered();
		return result;
	}

	/**
	 * 是否验证.
	 * 
	 * @param request
	 *            ServletRequest
	 * @param response
	 *            ServletResponse
	 * 
	 * @return true-已验证 false-未验证.
	 */
	public static Boolean isAuthenticated(final ServletRequest request, final ServletResponse response) {
		Boolean result = false;
		Subject currentUser = getSubject(request, response);
		result = currentUser.isAuthenticated();
		return result;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
