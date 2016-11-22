/**
 * DXkite
 * FileParser.java
 * 2016Äê11ÔÂ22ÈÕ
 */
package cn.atd3.response;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import cn.atd3.request.Request;
import cn.atd3.request.RequestParser;
import cn.atd3.server.Config;
import cn.atd3.server.Log;

/**
 * @author DXkite
 *
 */
public class FileResponse implements RequestParser {
	private Request request;
	private Response response;
	private String url;

	public FileResponse() {
		response = new Response();
	}

	@Override
	public Response parserRequest(Request rq) {
		request = rq;
		url = rq.getUrl();
		File file = new File(Config.get("ServerRoot") + File.separator + url);
		if (file.isDirectory()) {
			url = url + Config.get("AutoIndex", "index.html");
			file = new File(Config.get("ServerRoot") + File.separator + url);
		}
		if (file.exists() && file.isFile()) {
			try {
				return writeFile(file);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	public Response writeFile(File file) throws IOException {
		String ex = url.substring(url.lastIndexOf(".") + 1);
		response.setStatus(200);
		response.setType(ex);
		FileInputStream is = new FileInputStream(file);
		byte[] body = new byte[is.available()];
		is.read(body);
		response.setBody(body);
		response.setHead("Connection", "close");
		is.close();
		Log.i("Run", request.getMethod() + ":" + request.getUrl());
		return response;
	}
}
