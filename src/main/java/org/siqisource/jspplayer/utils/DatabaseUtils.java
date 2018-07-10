package org.siqisource.jspplayer.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.h2.server.web.WebServer;
import org.h2.tools.Server;
import org.h2.util.JdbcUtils;

public class DatabaseUtils {

	private static final String DRIVER = "org.h2.Driver";

	private static final String URL = "jdbc:h2:" + PathUtils.getCurrentPath() + "/jsp-player.db";

	private static final String USER = "sa";

	private static final String PASSWORD = "sa";

	private static WebServer WEB_SERVER = new WebServer();

	public static void startH2WebServer() throws SQLException {
		String[] args;
		args = new String[] { "-webPort", "0" };
		Server web = new Server(WEB_SERVER, args);
		web.start();
		WEB_SERVER.setShutdownHandler(web);
	}

	public static String loginH2WebServer() throws SQLException {
		String url = WEB_SERVER.addSession(getConnection());
		return url;
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = JdbcUtils.getConnection(DRIVER, URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
