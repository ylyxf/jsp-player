package org.siqisource.jspplayer.utils;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

public class PathUtils {

	public static String getCurrentPath() {
		URL url = PathUtils.class.getProtectionDomain().getCodeSource().getLocation();
		String filePath = null;
		try {
			filePath = URLDecoder.decode(url.getPath(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (filePath.endsWith(".jar")) {
			filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
		}

		File file = new File(filePath);

		filePath = file.getAbsolutePath();
		return filePath;
	}

}
