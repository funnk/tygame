package com.sanpeng.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 工程面板
 * @Package com.sanpeng.ui
 * @FileName ProjectPanel.java
 * @Author TyGame
 * @Date 2013-3-3
 */
public class ProjectPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1414800993173666412L;

	public ProjectPanel() {
		this.setBackground(Color.GRAY);
		JLabel label = new JLabel("工程列表");
		label.setFont(new Font("宋体", 0, 15));
		label.setForeground(Color.WHITE);
		this.add(label);
	}
}
