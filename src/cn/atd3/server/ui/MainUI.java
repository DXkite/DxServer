/**
 * DXkite
 * Option.java
 * 2016Äê12ÔÂ21ÈÕ
 */
package cn.atd3.server.ui;

import javax.swing.*;

/**
 * @author DXkite
 *
 */
public class MainUI {
	JFrame window;
	JList<String> taskList;

	public MainUI() {
		window = new JFrame("DXServer Controll Panel");
		taskList = new JList<String>();
		String[] data = { "one", "two", "three", "four" };
		taskList.setListData(data);
		window.add(taskList);
		window.setVisible(true);
	}
}
