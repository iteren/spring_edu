package com.iteren.spring_training.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.log4j.Log4j;

@Log4j
public class LogFilter extends HandlerInterceptorAdapter {
	private static final String ACCEPT_HEADER = "Accept";
	private final static String LOG_TEMPLATE = "Request url: <%s> Method: <%s> Return type: <%s> Query params: <%s>";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info(String.format(LOG_TEMPLATE, request.getRequestURL(), request.getMethod(),
				request.getHeader(ACCEPT_HEADER), request.getQueryString()));
		return super.preHandle(request, response, handler);
	}
}
