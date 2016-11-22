/**
 * DXkite
 * Mime.java
 * 2016Äê11ÔÂ21ÈÕ
 */
package cn.atd3.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author DXkite
 *
 */
public class Mime {
	private static Map<String, String> map;
	private static Mime obj;
	private Mime() {
		try {
			BufferedReader isr = new BufferedReader(new InputStreamReader(new FileInputStream(new File(Config.get("Mime")))));
			String line;
			map = new HashMap<String, String>();
			while ((line = isr.readLine()) != null) {
				if (line.startsWith("#"))continue;
				StringTokenizer st = new StringTokenizer(line);
				String mime = null;
				while (st.hasMoreTokens()) {
					if (mime == null) {
						mime = st.nextToken();
					} else {
						map.put(st.nextToken(), mime);
					}
				}
			}
			isr.close();
		} catch (IOException e) {
			Log.e("ServerInit","Load Mime Error:"+Config.get("Mime"));
			e.printStackTrace();
		}
		Log.e("ServerInit","Load Mime");
	}

	public static String get(String type) {
		if (obj == null) {
			obj = new Mime();
		}
		return map.get(type);
	}
}
