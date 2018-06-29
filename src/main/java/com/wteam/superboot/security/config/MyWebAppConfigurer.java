/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.wteam.superboot.security.interceptor.ShiroInterceptor;

/**
 * 拦截器链配置.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@Configuration
public class MyWebAppConfigurer 
        extends WebMvcConfigurerAdapter {

	/**
	 * shiro 拦截器.
	 */
	@Autowired
	private ShiroInterceptor shiroInterceptor;
	
	/**
	 * 添加拦截器.
	 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(shiroInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
