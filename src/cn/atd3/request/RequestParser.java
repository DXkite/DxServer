/**
 * DXkite
 * RequestObserver.java
 * 2016��11��22��
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
