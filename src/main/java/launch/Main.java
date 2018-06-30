package launch;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Main {

	public static void main(String[] args) throws ServletException, LifecycleException {
		if (args.length != 2) {
			System.out.println("need two args: webapp path , prot");
		}
		System.out.println("webapp path is:" + args[0]);
		System.out.println("webapp path is:" + args[1]);

		Tomcat tomcat = new Tomcat();
		tomcat.addWebapp("/", new File(args[0]).getAbsolutePath());
		tomcat.setPort(Integer.parseInt(args[1]));
		tomcat.start();
		tomcat.getServer().await();

	}

}
