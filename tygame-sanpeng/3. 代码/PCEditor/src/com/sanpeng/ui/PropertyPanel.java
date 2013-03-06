package com.sanpeng.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 属性面板
 * @Package com.sanpeng.ui
 * @FileName PropertyPanel.java
 * @Author TyGame
 * @Date 2013-3-3
 */
public class PropertyPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5265531651946749054L;

	public PropertyPanel() {
		this.setBackground(Color.GRAY);
		JLabel label = new JLabel("属性面板");
		label.setFont(new Font("宋体", 0, 15));
		label.setForeground(Color.WHITE);
		this.add(label);
	}
}
