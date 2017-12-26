/**
 * DXkite
 * Request.java
 * 2016��11��21��
 */
package cn.atd3.request;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import cn.atd3.server.Log;

/**
 * @author DXkite
 *
 */
public class Request {
	String method="GET";
	String url="/";
	public Request(InputStream is){
		BufferedReader head=new BufferedReader(new InputStreamReader(is));
		String str=null;
		try {
			str=head.readLine();
			String[] rq=str.split("\\s",3);
			method=rq[0];
			url=rq[1];
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("RunError", "Unknown Request:"+str);
		}
	}
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
