/**
 * DXkite
 * FileParser.java
 * 2016��11��22��
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
	private int status = 200;
	
	public FileResponse() {
		response = new Response();
	}

	@Override
	public Response parserRequest(Request rq) {
		request = rq;
		url = rq.getUrl();
		try {
			String root = new File(Config.get("ServerRoot", "webapp")).getCanonicalPath();
			File file = new File(root + File.separator + url);
			if (file.isDirectory()) {
				url = url + Config.get("AutoIndex", "index.html");
				file = new File(root + File.separator + url);
			}
			if (file.getCanonicalPath().startsWith(root)) {
				if (file.exists() && file.isFile()) {
					return writeFile(file);
				}else {
					status=404;
				}
			} else {
				status = 400;
				Log.e("Safe", "Evil Path:" + file.getAbsolutePath());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
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

	@Override
	public int getStatus() {
		return this.status;
	}
}
