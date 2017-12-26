/**
 * DXkite
 * Log.java
 * 2016��11��22��
 */
package cn.atd3.server;

/**
 * @author DXkite
 *
 */
public class Log {
	public static void i(String name,String info)
	{
		System.out.println("[Info]\t"+name+"\t"+info);
	}
	public static void e(String name,String info)
	{
		System.err.println("[Error]\t"+name+"\t"+info);
	}
	public static void e(String name,String info,Exception e)
	{
		System.err.println("[Error]\t"+name+"\t"+info+" Exception "+e.getClass().getName() + "- "+e.getMessage());
	}
}
