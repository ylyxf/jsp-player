package org.siqisource.jspplayer.filewtach;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.Context;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import org.siqisource.jspplayer.filter.AddScriptFilter;

public class FileWatcher extends FileAlterationListenerAdaptor {

	private static Map<String, String> FILE_HASH = new HashMap<String, String>();

	private static String WEB_ROOT_PATH = null;

	public static void watch(File webRoot, Context context) throws Exception {
		WEB_ROOT_PATH = webRoot.getAbsolutePath();
		FileAlterationObserver observer = new FileAlterationObserver(webRoot);
		observer.addListener(new FileWatcher());
		// 创建文件变化监听器
		long interval = TimeUnit.SECONDS.toMillis(1);
		FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
		monitor.start();

		//
		FilterDef filterDef = new FilterDef();
		filterDef.setFilterClass(AddScriptFilter.class.getName());
		filterDef.setFilterName("addScript");
		context.addFilterDef(filterDef);
		FilterMap filterMap = new FilterMap();
		filterMap.setFilterName("addScript");
		filterMap.addURLPattern("*.jsp");
		context.addFilterMap(filterMap);
	}

	public void onFileChange(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			String filePath = file.getAbsolutePath();
			String md5 = DigestUtils.md5Hex(fis);
			fis.close();
			String oldMd5 = FILE_HASH.get(filePath);
			if (!md5.equals(oldMd5)) {
				String modifyName = filePath.replace(WEB_ROOT_PATH, "").replaceAll("\\\\", "/");
				WebSocket.notify(modifyName);
				FILE_HASH.put(file.getAbsolutePath(), md5);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
