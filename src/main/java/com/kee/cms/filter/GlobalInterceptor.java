/*
 *	Copyright © 2013 Changsha kee Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.kee.com
 */

package com.kee.cms.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kee.cms.constant.ConfigConstant;
import com.kee.cms.constant.SystemConstant;
import com.kee.cms.entity.Admin;
import com.kee.cms.service.ConfigService;
import com.kee.cms.util.HttpUtils;

/**
 * @author Herbert
 * 
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor {

	@Autowired
	private ConfigService configService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (null == modelAndView) {
			return;
		}
		Admin admin = (Admin) request.getSession().getAttribute(
				SystemConstant.SESSION_ADMIN);
		if (admin == null) {
			modelAndView.addObject("isAdmin", false);
		} else {
			modelAndView.addObject("isAdmin", true);
		}
		// 系统配置参数
		String basePath = HttpUtils.getBasePath(request);
		String contextPath = HttpUtils.getContextPath(request);
		modelAndView.addObject("basePath", basePath);
		modelAndView.addObject("contextPath", contextPath);
		modelAndView.addObject("SYS_SITEDESC",
				configService.getConfigByKey(ConfigConstant.SYS_SITEDESC));
		modelAndView.addObject("SYS_SITENAME",
				configService.getConfigByKey(ConfigConstant.SYS_SITENAME));
		modelAndView.addObject("SYS_TEMPLATE",
				configService.getConfigByKey(ConfigConstant.SYS_THEME));
		modelAndView.addObject("TEMPLATE_PATH", basePath + "/themes/"
				+ configService.getConfigByKey(ConfigConstant.SYS_THEME));
		MDC.put("ip", HttpUtils.getIp(request));
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
