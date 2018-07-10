package org.siqisource.jspplayer.mock;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

public class JsonServlet extends HttpServlet {

	private static final long serialVersionUID = -5654108351465393375L;

	private File baseFolder = null;

	private String contextPath = null;

	public JsonServlet(File baseFolder, String contextPath) {
		this.baseFolder = baseFolder;
		this.contextPath = contextPath;
	}

	@Override
	protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
			throws ServletException, IOException {
		servletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
		String uri = servletRequest.getRequestURI();
		uri = uri.substring(contextPath.length());
		uri = uri + ".json";
		File file = new File(baseFolder.getAbsolutePath() + uri);

		String content = FileUtils.readFileToString(file, "UTF-8");
		servletResponse.getOutputStream().print(content);
	}

}
