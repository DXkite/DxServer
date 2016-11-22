/**
 * DXkite
 * Responded.java
 * 2016Äê11ÔÂ21ÈÕ
 */
package cn.atd3.response;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import cn.atd3.server.Mime;
import cn.atd3.server.Status;

/**
 * @author DXkite
 *
 */
public class Response {
	int status = 200;
	String type;
	byte[] body;

	Map<String, String> map;

	/**
	 * @param status
	 */
	public Response() {
		map = new HashMap<String, String>();
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setHead(String name, String value) {
		map.put(name, value);
	}

	public void writeOutput(OutputStream os) {
		BufferedOutputStream out = new BufferedOutputStream(os);
		try {
			out.write(("HTTP/1.1 " + status + " " + Status.get(status) + "\r\n").getBytes());
			map.put("Content-Length", "" + body.length);
			map.put("Content-Type", Mime.get(type));
			for (Map.Entry<String, String> entry : map.entrySet()) {
				out.write((entry.getKey() + ":" + entry.getValue() + "\r\n").getBytes());
			}
			out.write("\r\n".getBytes());
			out.write(body);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the body
	 */
	public byte[] getBody() {
		return body;
	}

	/**
	 * @param body
	 *            the body to set
	 */
	public void setBody(byte[] body) {
		this.body = body;
	}

}
