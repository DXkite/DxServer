/**
 * DXkite
 * Server.java
 * 2016��11��21��
 */
package cn.atd3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import cn.atd3.request.RequestThread;
import cn.atd3.response.FileResponse;
import cn.atd3.server.Config;
import cn.atd3.server.Log;

/**
 * @author DXkite
 *
 */
public class Server {
	private int port;
	private ServerSocket serversocket;

	private boolean loadConfig() {
		port = Integer.parseInt(Config.get("Port","80"));
		return false;
	}

	private void listen() {
		try {
			serversocket = new ServerSocket(port);
			while (true) {
				Socket socket = serversocket.accept();
				new RequestThread(socket).addParser(new FileResponse()).run();
			}
		} catch (IOException e) {
			Log.e("Server", "Dead",e);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("DxServer v1.0");
		Server sv = new Server();
		sv.loadConfig();
		Log.i("Server","Listen:"+sv.port);
		Log.i("Server","Server Root:"+Config.get("ServerRoot"));
		sv.listen();
	}

}
