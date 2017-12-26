/**
 * DXkite
 * RequestThread.java
 * 2016��11��21��
 */
package cn.atd3.request;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import cn.atd3.response.Response;
import cn.atd3.server.Config;
import cn.atd3.server.Log;

/**
 * @author DXkite
 *
 */
public class RequestThread extends Thread {
	private Socket socket;
	private List<RequestParser> parsers;

	/**
	 * @param socket
	 */
	public RequestThread(Socket socket) {
		this.socket = socket;
		parsers = new ArrayList<RequestParser>();
	}

	public RequestThread addParser(RequestParser parser) {
		parsers.add(parser);
		return this;
	}

	public void run() {
		try {
			Log.i("Run", "Parser Request");
			Request rq = new Request(socket.getInputStream());
			Response rp = null;
			int status = 404;
			for (RequestParser parser : parsers) {
				rp = parser.parserRequest(rq);
				if (rp != null)
					break;
			}
			if (rp != null) {
				rp.writeOutput(socket.getOutputStream());
			} else {
				Log.i("Run", "File No Find " + rq.getUrl());
				if (Config.get("Error"+status) != null) {
					redirect(Config.get("Error"+status),status,socket.getOutputStream());
				}else {
					writeError(status, socket.getOutputStream());
				}
			}
			socket.close();
		} catch (IOException e1) {
			Log.e("Request", e1.getMessage());
		}
	}
	
	protected void writeError(int status,OutputStream out) {
		Response rp = new Response();
		rp.setStatus(status);
		rp.setType("txt");
		String body = new String("Error (" + status+") -- DXkite Server");
		rp.setBody(body.getBytes());
		rp.writeOutput(out);
	}
	
	protected void redirect(String url,int status,OutputStream out) {
		Response rp = new Response();
		rp.setStatus(302);
		rp.setType("html");
		rp.setHead("Location", url);
		String body = new String("Error (" + status+") -- DXkite Server");
		rp.setBody(body.getBytes());
		rp.writeOutput(out);
	}
}
