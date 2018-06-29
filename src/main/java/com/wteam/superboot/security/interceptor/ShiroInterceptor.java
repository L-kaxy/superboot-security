/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.interceptor;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wteam.superboot.core.entity.po.UserPo;
import com.wteam.superboot.core.enums.ResultEnum;
import com.wteam.superboot.core.helper.ResultHelper;
import com.wteam.superboot.core.repository.UserRepository;
import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.security.entity.po.ActionPo;
import com.wteam.superboot.security.entity.po.PermissionresourcemapPo;
import com.wteam.superboot.security.entity.po.ResourcePo;
import com.wteam.superboot.security.entity.po.ResourcetypePo;
import com.wteam.superboot.security.helper.ShiroHelper;
import com.wteam.superboot.security.repository.ActionRepository;
import com.wteam.superboot.security.repository.PermissionresourcemapRepository;
import com.wteam.superboot.security.repository.ResourceRepository;
import com.wteam.superboot.security.repository.ResourcetypeRepository;

/**
 * shiro 拦截器.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@Component
public class ShiroInterceptor implements HandlerInterceptor {

	/**
	 * 注入 Repository.
	 */
	@Autowired
	private ResourcetypeRepository resourcetypeRepository;
	@Autowired
	private ActionRepository actionRepository;
	@Autowired
	private ResourceRepository resourceRepository;
	@Autowired
	private PermissionresourcemapRepository permissionresourcemapRepository;
	@Autowired
	private UserRepository userRepository;

	/**
	 * 执行请求前.
	 * 
	 * @param request
	 *            ServletRequest.
	 * @param response
	 *            ServletResponse.
	 * @param handler
	 *            执行的控制方法.
	 * @throws Exception
	 *             抛出异常.
	 * 
	 * @return 是否继续执行.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean keepOn = true;

		String requestUri = request.getRequestURI();
		String permissionName = requestUri.substring(requestUri.indexOf('/', 1));

		boolean isMisspelt = false;
		boolean isVisitor = false;
		boolean hasPermission = false;

		ResourcetypePo type = new ResourcetypePo();
		type.setResourcetypename("action");
		type = resourcetypeRepository.queryEntity(type);

		// 首先判断是否需要权限验证，如果是访客权限则数据库中无记录，无需验证
		ActionPo actionEntity = new ActionPo();
		actionEntity.setActionname(permissionName);
		if (actionRepository.hasNonDeleteEntity(actionEntity)) {
			actionEntity = actionRepository.queryEntity(actionEntity);

			ResourcePo resource = new ResourcePo();
			resource.setRealid(actionEntity.getActionid());
			resource.setResourcetypeid(type.getResourcetypeid());
			resource = resourceRepository.queryEntity(resource);

			PermissionresourcemapPo permissionresourcemap = new PermissionresourcemapPo();
			permissionresourcemap.setResourceid(resource.getResourceid());
			if (!permissionresourcemapRepository.hasNonDeleteEntity(permissionresourcemap)) {
				// 无需权限验证
				isVisitor = true;
			}
		} else { // 如果找不到则说明action名拼写错误
			isMisspelt = true;
		}

		// 接口拼写错误
		if (isMisspelt) {
			// throw new SuperException(ResultEnum.REQUEST_INTERFACE_ERROR);
			ResultMessage rs = ResultHelper.result(ResultEnum.REQUEST_INTERFACE_ERROR);
			response.setContentType("text/html;charset=UTF8");
			PrintWriter out = null;
			out = response.getWriter();
			out.println(rs.toJson());
			out.flush();
			out.close();
			keepOn = false;
		} else if (!isVisitor) {
			Subject currentUser = ShiroHelper.getSubject(request, response);
			// 当currentUser.isAuthenticated()为true时说明当前用户已验证登录
			if (currentUser.isAuthenticated()) {
				hasPermission = currentUser.isPermitted(permissionName);
			}

			if (hasPermission) {
				// 有操作权限
				@SuppressWarnings("unchecked")
				List<Object> principals = currentUser.getPrincipals().asList();
				Object obj = principals.get(0);
				Long userid = (Long) obj;

				UserPo uPo = userRepository.getEntityById(UserPo.class, userid);
				// 插入
				request.setAttribute("currentUser", uPo);
			} else {
				// 没有该操作权限
				ResultMessage rs = ResultHelper.result(ResultEnum.NO_ACCESS_AUTH);
				response.setContentType("text/html;charset=UTF8");
				PrintWriter out = null;
				out = response.getWriter();
				out.println(rs.toJson());
				out.flush();
				out.close();
				keepOn = false;
			}
		}

		return keepOn;
	}

	/**
	 * 执行请求后.
	 * 
	 * @param request
	 *            ServletRequest.
	 * @param response
	 *            ServletResponse.
	 * @param handler
	 *            执行的控制方法.
	 * @param modelAndView
	 *            模型或视图.
	 * @throws Exception
	 *             抛出异常.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 请求完成.
	 * 
	 * @param request
	 *            ServletRequest.
	 * @param response
	 *            ServletResponse.
	 * @param handler
	 *            执行的控制方法.
	 * @param ex
	 *            异常.
	 * @throws Exception
	 *             抛出异常.
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
