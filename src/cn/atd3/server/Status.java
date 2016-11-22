/**
 * DXkite
 * Status.java
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


/**
 * @author DXkite
 *
 */
public class Status {
	private static Map<Integer,String> status;
	private static Status obj;
	private Status() {
		try {
			BufferedReader isr=new BufferedReader(new InputStreamReader(new FileInputStream(new File(Config.get("State")))));
			String line;
			status=new HashMap<Integer,String>();
			while ((line=isr.readLine())!=null){
				line=line.trim();
				int len=line.indexOf(' ');
				status.put(Integer.parseInt(line.substring(0,len)),line.substring(len+1).trim());
			}
			isr.close();
			// System.out.println(status);
		} catch (IOException e) {
			e.printStackTrace();
			Log.e("ServerInit","Load Status Error:"+Config.get("State"));
		}
		Log.e("ServerInit","Load Status");
	}
	public static String get(Integer state){
		if (obj==null) { 
			obj =new Status();
		}
		return status.get(state);
	}
}
