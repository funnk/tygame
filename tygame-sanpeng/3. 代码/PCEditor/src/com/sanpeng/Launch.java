package com.sanpeng;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel;

import com.sanpeng.ui.LeftPanel;
import com.sanpeng.ui.ProjectPanel;
import com.sanpeng.ui.PropertyPanel;
import com.sanpeng.ui.WorkPanel;
import com.sanpeng.utils.ResourceUtils;
import com.sanpeng.utils.SystemUtils;

/**
 * 启动器
 * 
 * @Package com.sanpeng
 * @FileName Launch.java
 * @Author APKBUS-manyou
 * @Date 2013-2-28
 */
public class Launch {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new SubstanceOfficeSilver2007LookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			SystemUtils.e(Launch.class, e.getMessage());
		}

		ResourceUtils.load();

		JFrame frame = new JFrame("PC Editor");
		frame.setJMenuBar(getMenuBar());
		frame.setIconImage(ResourceUtils.getImage("icon"));
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(getToolbar(), BorderLayout.NORTH);
		JSplitPane mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		mainPanel.setLeftComponent(new LeftPanel());
		mainPanel.setRightComponent(new WorkPanel());
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.add(new JLabel(""), BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	/**
	 * 返回工具栏
	 */
	private static Component getToolbar() {
		JToolBar toolbar = new JToolBar(JToolBar.HORIZONTAL);
		// 关于
		JButton about = new JButton(ResourceUtils.getImageIcon("about"));
		// 插入按键
		JButton button = new JButton(ResourceUtils.getImageIcon("button"));
		// 删除
		JButton delete = new JButton(ResourceUtils.getImageIcon("delete"));
		// 退出
		JButton exit = new JButton(ResourceUtils.getImageIcon("exit"));
		// 页面
		JButton newPage = new JButton(ResourceUtils.getImageIcon("newPage"));
		// 工程
		JButton newProject = new JButton(ResourceUtils.getImageIcon("newProject"));
		// 子页面
		JButton newSubPage = new JButton(ResourceUtils.getImageIcon("newSubPage"));
		// 打开
		JButton open = new JButton(ResourceUtils.getImageIcon("open"));
		// 保存
		JButton save = new JButton(ResourceUtils.getImageIcon("save"));
		// 选择
		JButton select = new JButton(ResourceUtils.getImageIcon("select"));
		// 插入文本
		JButton text = new JButton(ResourceUtils.getImageIcon("text"));
		// 插入日期时钟
		JButton time = new JButton(ResourceUtils.getImageIcon("time"));
		// 插入定时按键
		JButton timerButton = new JButton(ResourceUtils.getImageIcon("timerButton"));
		// 插入标题
		JButton title = new JButton(ResourceUtils.getImageIcon("title"));
		// 传输工程到控制端
		JButton transmit = new JButton(ResourceUtils.getImageIcon("transmit"));

		toolbar.add(newProject);
		toolbar.add(open);
		toolbar.add(save);
		toolbar.add(delete);
		toolbar.add(exit);
		toolbar.add(Box.createHorizontalStrut(10));
		toolbar.addSeparator();
		toolbar.add(Box.createHorizontalStrut(10));
		toolbar.add(newPage);
		toolbar.add(newSubPage);
		toolbar.add(Box.createHorizontalStrut(10));
		toolbar.addSeparator();
		toolbar.add(Box.createHorizontalStrut(10));
		toolbar.add(select);
		toolbar.add(title);
		toolbar.add(text);
		toolbar.add(button);
		toolbar.add(timerButton);
		toolbar.add(time);
		toolbar.add(Box.createHorizontalStrut(10));
		toolbar.addSeparator();
		toolbar.add(Box.createHorizontalStrut(10));
		toolbar.add(transmit);
		toolbar.add(Box.createHorizontalStrut(10));
		toolbar.addSeparator();
		toolbar.add(Box.createHorizontalStrut(10));
		toolbar.add(about);
		
		return toolbar;
	}

	/**
	 * 获取菜单栏
	 * 
	 * @return
	 */
	private static JMenuBar getMenuBar() {
		JMenuBar menubar = new JMenuBar();

		// 文件
		menubar.add(getFileMenu());
		// 绘画
		menubar.add(getDrawMenu());
		// 选项
		menubar.add(getOptionsMenu());
		// 工程
		menubar.add(getProjectMenu());
		// 帮助
		menubar.add(getHelpMenu());

		return menubar;
	}

	// 文件
	private static JMenu getFileMenu() {
		JMenu fileMenu = new JMenu("文件");
		JMenu newMenu = new JMenu("新建");
		JMenuItem newProjectMenu = new JMenuItem("工程");
		JMenuItem newPageMenu = new JMenuItem("页面");
		JMenuItem newSubPageMenu = new JMenuItem("子页面");
		newMenu.add(newProjectMenu);
		newMenu.add(newPageMenu);
		newMenu.add(newSubPageMenu);
		JMenuItem openMenu = new JMenuItem("打开");
		JMenuItem saveMenu = new JMenuItem("保存");
		JMenuItem saveAsMenu = new JMenuItem("另存为");
		JMenuItem saveAllMenu = new JMenuItem("保存所有工程");
		JMenuItem deletePro = new JMenuItem("删除工程");
		JMenuItem exitMenu = new JMenuItem("退出");
		fileMenu.add(newMenu);
		fileMenu.add(openMenu);
		fileMenu.addSeparator();
		fileMenu.add(saveMenu);
		fileMenu.add(saveAsMenu);
		fileMenu.add(saveAllMenu);
		fileMenu.addSeparator();
		fileMenu.add(deletePro);
		fileMenu.addSeparator();
		fileMenu.add(exitMenu);
		return fileMenu;
	}

	// 绘画
	private static JMenu getDrawMenu() {
		JMenu drawMenu = new JMenu("绘画");
		JRadioButtonMenuItem selectMenu = new JRadioButtonMenuItem("选择");
		selectMenu.setSelected(true);
		JRadioButtonMenuItem titleMenu = new JRadioButtonMenuItem("标题");
		JRadioButtonMenuItem buttonMenu = new JRadioButtonMenuItem("按键");
		JRadioButtonMenuItem timerButtonMenu = new JRadioButtonMenuItem("定时按键");
		JRadioButtonMenuItem textMenu = new JRadioButtonMenuItem("文本");
		JRadioButtonMenuItem clockMenu = new JRadioButtonMenuItem("日期时钟");
		drawMenu.add(selectMenu);
		drawMenu.add(titleMenu);
		drawMenu.add(buttonMenu);
		drawMenu.add(timerButtonMenu);
		drawMenu.add(textMenu);
		drawMenu.add(clockMenu);
		return drawMenu;
	}

	// 选项
	private static JMenu getOptionsMenu() {
		JMenu optionsMenu = new JMenu("选项");
		JMenuItem toolbar = new JCheckBoxMenuItem("工具栏", true);
		JMenuItem toolsOptions = new JMenuItem("软件设置");
		optionsMenu.add(toolbar);
		optionsMenu.addSeparator();
		optionsMenu.add(toolsOptions);
		return optionsMenu;
	}

	// 工程
	private static JMenu getProjectMenu() {
		JMenu projectMenu = new JMenu("工程");
		JMenuItem packProject = new JMenuItem("打包工程");
		JMenuItem openProjectPath = new JMenuItem("打开工程路径");
		JMenuItem transmitProject = new JMenuItem("传输工程到控制端");
		projectMenu.add(packProject);
		projectMenu.add(openProjectPath);
		projectMenu.add(transmitProject);
		return projectMenu;
	}

	// 帮助
	private static JMenu getHelpMenu() {
		JMenu helpMenu = new JMenu("帮助");
		JMenuItem about = new JMenuItem("关于");
		JRadioButtonMenuItem scLang = new JRadioButtonMenuItem("简体中文");
		scLang.setSelected(true);
		JRadioButtonMenuItem tcLang = new JRadioButtonMenuItem("繁体中文");
		JRadioButtonMenuItem enLang = new JRadioButtonMenuItem("英文");
		ButtonGroup group = new ButtonGroup();
		group.add(scLang);
		group.add(tcLang);
		group.add(enLang);
		helpMenu.add(about);
		helpMenu.addSeparator();
		helpMenu.add(scLang);
		helpMenu.add(tcLang);
		helpMenu.add(enLang);
		return helpMenu;
	}
}
