package org.siqisource.jspplayer.mock;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

public class MockRegister {

	public static String MOCK_DIR = "/WEB-INF/mock";

	public static void register(File webRoot, final Context context) throws IOException {
		final File mockFolder = new File(webRoot.getAbsolutePath() + MOCK_DIR);
		if (!mockFolder.exists()) {
			mockFolder.mkdirs();
		}
		Path mockPath = mockFolder.toPath();

		SimpleFileVisitor<Path> finder = new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
				System.out.println("file::::" + path.toFile().getAbsolutePath());

				File file = path.toFile();
				String fileName = file.getName();
				String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
				if ("json".equals(suffix)) {
					JsonServlet jsonServlet = new JsonServlet(mockFolder, context.getPath());
					String url = mapToUrl(mockFolder, file);
					Tomcat.addServlet(context, url, jsonServlet);
					context.addServletMapping(url, url);
				} else if ("jsp".equals(suffix)) {
					JspServlet jspServlet = new JspServlet(mockFolder, context.getPath());
					String url = mapToUrl(mockFolder, file);
					Tomcat.addServlet(context, url, jspServlet);
					context.addServletMapping(url, url);
				} else if ("js".equals(suffix)) {
					JavascriptServlet javascriptServlet = new JavascriptServlet(mockFolder, context.getPath());
					String url = mapToUrl(mockFolder, file);
					Tomcat.addServlet(context, url, javascriptServlet);
					context.addServletMapping(url, url);
				}

				return super.visitFile(path, attrs);
			}
		};

		Files.walkFileTree(mockPath, finder);

	}

	public static String mapToUrl(File mockFolder, File file) {
		String url = file.getAbsolutePath().substring(mockFolder.getAbsolutePath().length());
		url = url.replaceAll("\\\\", "/");
		url = url.substring(0, url.lastIndexOf("."));
		return url;
	}

}
