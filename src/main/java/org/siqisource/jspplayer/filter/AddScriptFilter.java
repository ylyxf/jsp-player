package org.siqisource.jspplayer.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class AddScriptFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);

		chain.doFilter(request, responseWrapper);

		if (response.getContentType() != null && response.getContentType().contains("text/html")) {

			String content = responseWrapper.toString();
			PrintWriter printWriter = response.getWriter();
			printWriter.write(content);

			printWriter.write("<script src='/jsp-player/devlib/websocket.js'></script>");
			printWriter.write("<script src='/jsp-player/devlib/jquery.ba-bbq.min.js'></script>");
			printWriter.write("<script src='/jsp-player/devlib/ajaxconfig.js'></script>");

		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}