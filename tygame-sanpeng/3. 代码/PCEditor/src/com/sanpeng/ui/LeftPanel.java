package com.sanpeng.ui;

import java.awt.Dimension;

import javax.swing.JSplitPane;

/**
 * 左侧面板，包括项目面板和属性面板
 * @Package com.sanpeng.ui
 * @FileName LeftPanel.java
 * @Author TyGame
 * @Date 2013-3-3
 */
public class LeftPanel extends JSplitPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5289565811639702809L;

	public LeftPanel() {
		this.setOrientation(HORIZONTAL_SPLIT);
		this.setLeftComponent(new ProjectPanel());
		this.setRightComponent(new PropertyPanel());
		this.setMinimumSize(new Dimension(300, 0));
	}
}
