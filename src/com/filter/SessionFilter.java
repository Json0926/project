package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.entity.Member;


/**
 * 判断用户是否过期
 */
public class SessionFilter implements Filter {

	private Logger logger = Logger.getLogger(SessionFilter.class);
	private static String[] exclude = { "/css/", "/img/", "/fonts/", "/js/",
			"/ajax/", "/attachFiles", 
			"/login.jsp", "/main.jsp","/user/login" };
//
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String url = request.getServletPath();
		if (StringUtils.startsWithAny(url, exclude)) {
			chain.doFilter(req, resp);
			return;
		}

		Member user = (Member) request.getSession().getAttribute("USER");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
