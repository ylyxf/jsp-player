package org.siqisource.jspplayer.launch;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.jasper.servlet.JasperInitializer;
import org.siqisource.jspplayer.filewtach.FileWatcher;
import org.siqisource.jspplayer.mock.MockRegister;
import org.siqisource.jspplayer.proxy.Proxy;
import org.siqisource.jspplayer.utils.DatabaseUtils;
import org.siqisource.jspplayer.utils.PathUtils;

public class Main {

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.out.println("need two args: webapp path , prot");
		}

		String webRootPath = args[0];
		int port = Integer.parseInt(args[1]);

		// start h2 web server ,it's a web app,not database server
		DatabaseUtils.startH2WebServer();

		Tomcat tomcat = new Tomcat();
		File webRoot = new File(webRootPath);

		// add webapp
		Context context = tomcat.addWebapp("", webRoot.getAbsolutePath());
		context.addServletContainerInitializer(new JasperInitializer(), null);

		FileWatcher.watch(webRoot, context);
		Proxy.proxy(context);

		// add jsp-player webapp
		File jspPlayerWebRoot = new File(PathUtils.getCurrentPath() + "/webapp");
		if (!jspPlayerWebRoot.exists()) {
			// extra
		}
		Context jpContext = tomcat.addWebapp("/jsp-player", jspPlayerWebRoot.getAbsolutePath());
		jpContext.addServletContainerInitializer(new JasperInitializer(), null);
		MockRegister.register(jspPlayerWebRoot, jpContext);
		FileWatcher.watch(jspPlayerWebRoot, jpContext);

		tomcat.setPort(port);
		tomcat.start();
		tomcat.getServer().await();

	}

}
