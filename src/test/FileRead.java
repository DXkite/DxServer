/**
 * DXkite
 * FilreRead.java
 * 2016Äê11ÔÂ21ÈÕ
 */
package test;

import cn.atd3.server.Mime;
import cn.atd3.server.Status;

/**
 * @author DXkite
 *
 */
public class FileRead {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 System.out.println(Status.get(403));
		 System.out.println(Mime.get("mp3"));
	}

}
