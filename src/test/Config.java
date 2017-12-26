/**
 * DXkite
 * Config.java
 * 2016��11��21��
 */
package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 默认配置文件成
 * @author DXkite
 *
 */
public class Config {
	public static void main(String[] args) {
		Properties config=new Properties();
		try {
			config.setProperty("ServerRoot", "."+File.separator+"webapp");
			config.setProperty("Port", "8080");
			config.setProperty("State", "status");
			config.setProperty("Mime", "mime.types");
			config.setProperty("AutoIndex", "index.html");
			config.setProperty("Error404", "404.html");
			config.store(new FileOutputStream(new File("config.properties")), "DxServer Config");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
