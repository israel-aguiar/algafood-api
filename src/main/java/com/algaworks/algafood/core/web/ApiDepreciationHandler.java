package com.algaworks.algafood.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class ApiDepreciationHandler extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(request.getRequestURI().startsWith("/v1")) {
			response.addHeader("X-AlgaFoood-Deprecated",
					"Essa versão da API está depreciada e deixará de existir em 31/12/2020."
					+ " Use a versão mais atual da API.");
		}
		
		return true;
	}
}
