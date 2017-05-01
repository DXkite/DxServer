/**
 * DXkite
 * RequestObserver.java
 * 2016Äê11ÔÂ22ÈÕ
 */
package cn.atd3.request;

import cn.atd3.response.Response;

/**
 * @author DXkite
 *
 */
public interface RequestParser {
	public Response parserRequest(Request rq);
	public int getStatus();
}
