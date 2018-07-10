package org.siqisource.jspplayer.proxy;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.mitre.dsmiley.httpproxy.ProxyServlet;

public class Proxy {

	private static Map<String, String> PROXY_MAP = new LinkedHashMap<String, String>();

	static {

	}

	public static void proxy(Context context) {
		for (Map.Entry<String, String> entry : PROXY_MAP.entrySet()) {
			String targetUrl = entry.getValue();
			String proxySuffix = entry.getKey();
			String proxyServletName = entry.getKey();
			ProxyServlet proxyServlet = new ProxyServlet();
			Wrapper wrapper = Tomcat.addServlet(context, proxyServletName, proxyServlet);
			wrapper.addInitParameter("targetUri", targetUrl);
			context.addServletMapping(proxySuffix, proxyServletName);
		}
	}

}
