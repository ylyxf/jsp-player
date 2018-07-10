package org.siqisource.jspplayer.mock;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JavascriptServlet extends HttpServlet {

	private static final long serialVersionUID = -5654108351465393375L;

	private File baseFolder = null;

	private String contextPath = null;

	ObjectMapper objectMapper = new ObjectMapper();

	public JavascriptServlet(File baseFolder, String contextPath) {
		this.baseFolder = baseFolder;
		this.contextPath = contextPath;
	}

	@Override
	protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
			throws ServletException, IOException {
		String jsonData = servletRequest.getHeader("jsp-play-json-data");
		jsonData = URLDecoder.decode(jsonData, "UTF-8");
		Map data = objectMapper.readValue(jsonData, Map.class);
		String paramData = objectMapper.writeValueAsString(data);
		System.out.println(paramData);

		servletResponse.setHeader("Content-Type", "text/javascript;charset=UTF-8");
		String uri = servletRequest.getRequestURI();
		uri = uri.substring(contextPath.length());
		uri = uri + ".js";
		File file = new File(baseFolder.getAbsolutePath() + uri);
		String content = FileUtils.readFileToString(file, "UTF-8");
		content = content.replace("$param$", paramData);
		PrintWriter printWriter = servletResponse.getWriter();
		printWriter.print(content);
		printWriter.close();
	}

}
