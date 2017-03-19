package com.bbdj8.sys.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;

/**
 * 工具类
 * @author liwenjun
 *
 */
public class ConfigUtils {

	private static String CONFIG_FILE = "config.properties";

	public static Map<String, String> CONFIG_PARAM = new HashMap<>();

	private static Properties prop = new Properties();

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(ConfigUtils.class);

	private ConfigUtils() {

	}

	static {
		InputStream stream = null;
		try {
			stream = ConfigUtils.class.getResourceAsStream(CONFIG_FILE);
			prop.load(stream);
			Enumeration<Object> set = prop.keys();
			while (set.hasMoreElements()) {
				String key = set.nextElement().toString();
				String value = prop.getProperty(key);
				CONFIG_PARAM.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("加载config.properties文件数据失败", e);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					logger.error("加载config.properties文件数据失败", e);
					e.printStackTrace();
				}
			}
		}
	}
}
