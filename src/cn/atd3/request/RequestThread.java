/**
 * DXkite
 * RequestThread.java
 * 2016��11��21��
 */
package cn.atd3.request;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import cn.atd3.response.Response;
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
		parsers=new ArrayList<RequestParser>();
	}
	public RequestThread addParser(RequestParser parser){
		parsers.add(parser);
		return this;
	}
	public void run() {
		try {
			Log.i("Run","Parser Request");
			Request rq=new Request(socket.getInputStream());
			Response rp=null;
			for (RequestParser parser:parsers){
				rp=parser.parserRequest(rq);
				if (rp!=null) break;
			}
			if (rp!=null){
				rp.writeOutput(socket.getOutputStream());
			}
			else{
				rp=new Response();
				Log.i("Run", "File No Find "+rq.getUrl());
				rp.setStatus(404);
				rp.setType("txt");
				String body=new String("404");
				rp.setBody(body.getBytes());
				rp.writeOutput(socket.getOutputStream());
			}
			socket.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
