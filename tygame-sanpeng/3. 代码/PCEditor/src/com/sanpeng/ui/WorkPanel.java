package com.sanpeng.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 工作界面
 * @Package com.sanpeng.ui
 * @FileName WorkPanel.java
 * @Author TyGame
 * @Date 2013-3-3
 */
public class WorkPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7792004243145538647L;

	public WorkPanel() {
		this.setBackground(Color.GRAY);
		JLabel label = new JLabel("工作面板");
		label.setFont(new Font("宋体", 0, 15));
		label.setForeground(Color.WHITE);
		this.add(label);
	}
}
