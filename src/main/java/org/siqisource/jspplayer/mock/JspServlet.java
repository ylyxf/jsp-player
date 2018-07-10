package org.siqisource.jspplayer.mock;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspServlet extends HttpServlet {

	private static final long serialVersionUID = -5654108351465393375L;

	private String contextPath = null;

	public JspServlet(File baseFolder, String contextPath) {
		this.contextPath = contextPath;
	}

	@Override
	protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
			throws ServletException, IOException {
		servletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
		String uri = servletRequest.getRequestURI();
		uri = MockRegister.MOCK_DIR + uri.substring(contextPath.length());
		uri = uri + ".jsp";
		RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(uri);
		dispatcher.forward(servletRequest, servletResponse);
	}

}
