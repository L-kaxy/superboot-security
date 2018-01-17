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
 *
 */
@Configuration
public class MyWebAppConfigurer 
        extends WebMvcConfigurerAdapter {

	@Autowired
	private ShiroInterceptor shiroInterceptor;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(shiroInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
