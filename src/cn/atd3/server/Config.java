/**
 * DXkite
 * Config.java
 * 2016��11��21��
 */
package cn.atd3.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author DXkite
 *
 */
public class Config {
	private static Config config = null;
	private static Properties properties;

	private Config() {

	}

	private Config(String path) throws FileNotFoundException, IOException {
		Properties config = new Properties();
		FileInputStream fis = new FileInputStream(new File(path));
		config.load(fis);
		properties = config;
		fis.close();
	}

	public static String get(String name) {
		getConfig();
		return properties.getProperty(name);
	}

	public static String get(String name, String defaultValue) {
		getConfig();
		return properties.getProperty(name, defaultValue);
	}

	private static Config getConfig() {
		if (config == null) {
			try {
				config = new Config("config.properties");
			} catch (IOException e) {
				Log.e("ServerInit", "Config No Find，use default config");
				Properties properties = new Properties();
				properties.setProperty("ServerRoot", "." + File.separator + "webapp");
				properties.setProperty("Port", "8080");
				properties.setProperty("State", "status");
				properties.setProperty("Mime", "mime.types");
				properties.setProperty("AutoIndex", "index.html");
				properties.setProperty("Error404", "404.html");
				Config.config = new Config();
				Config.properties = properties;
				try {
					properties.store(new FileOutputStream(new File("config.properties")), "DxServer Config");
				} catch (Exception e1) {
					Log.e("Config", "Can't create config file", e1);
				}
			}
		}
		return config;
	}
}
