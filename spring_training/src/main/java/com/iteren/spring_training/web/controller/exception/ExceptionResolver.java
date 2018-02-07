package com.iteren.spring_training.web.controller.exception;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class ExceptionResolver extends AbstractHandlerExceptionResolver {

	public static final String EXCEPTIONS = "exceptions";
	private static final String EXCEPTION = "exception";

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object arg2,
			Exception ex) {
		log.error("Exception just happened. Message: " + ex.getMessage());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(EXCEPTION, ex);
		modelAndView.addObject("url", request.getRequestURL());

		modelAndView.setViewName(EXCEPTION);
		ex.printStackTrace();
		storeException(request.getSession(), ex);
		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	private void storeException(HttpSession session, Exception ex) {
		if(session.getAttribute(EXCEPTIONS) == null) {
			session.setAttribute(EXCEPTIONS, new ArrayList<>());
		}
		((ArrayList<Exception>)session.getAttribute(EXCEPTIONS)).add(ex);
	}

}
