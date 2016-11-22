/**
 * DXkite
 * Config.java
 * 2016Äê11ÔÂ21ÈÕ
 */
package cn.atd3.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author DXkite
 *
 */
public class Config {
	private static Config config=null;
	private static Properties properties;
	private Config(String path) throws FileNotFoundException, IOException{
		Properties config = new Properties();
		FileInputStream fis=new FileInputStream(new File(path));
		config.load(fis);
		properties=config;
		fis.close();
	}
	public static String get(String name){
		getConfig();
		return properties.getProperty(name);
	}
	public static String get(String name,String  defaultValue){
		getConfig();
		return properties.getProperty(name, defaultValue);
	}
	private static Config getConfig(){
		if (config==null)
			try {
				config=new Config(".config");
			} catch (FileNotFoundException e) {
				Log.e("ServerInit","Config No Find");
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				Log.e("ServerInit","Config No Find");
				e.printStackTrace();
				return null;
			}
		return config;
	}
}
