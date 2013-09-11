package com.hww;


import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;

import com.hww.util.CreatecdIcon;

public class Library extends JFrame{

	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
	
	public Library(){
		super();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setSize(800, 600);
		setTitle("图书管理系统");
		
		JMenuBar menubar = createMenu();
		setJMenuBar(menubar);
		
		final JLabel label = new JLabel();
		label.setBounds(0, 0, 0, 0);
		label.setIcon(null); // 窗体背景

		DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				Dimension size = e.getComponent().getSize();
				label.setSize(e.getComponent().getSize());
				label.setText("<html><img width=" + size.width + " height="
						+ size.height + " src='"
						+ this.getClass().getResource("/backImg.jpg")
						+ "'></html>");
			}
		});
		DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE));
		getContentPane().add(DESKTOP_PANE);
	}
	
	/*
	 * 添加子窗体的方法
	 */
	public static void AddIFrame(JInternalFrame iFrame){
		DESKTOP_PANE.add(iFrame);
	}
	
	public JMenuBar createMenu(){
		JMenuBar menuBar  = new JMenuBar();
		
		//基础数据维护
		JMenu baseMenu = new JMenu();
		baseMenu.setIcon(CreatecdIcon.add("jcsjcd.jpg"));
		{
			
			JMenu readerMenu = new JMenu("读者信息管理");
			readerMenu.add(MenuActions.READER_ADD);
			readerMenu.add(MenuActions.READER_MODIFY);
			
			JMenu bookTypeMenu = new JMenu("图书类别信息管理");
			bookTypeMenu.add(MenuActions.BOOK_TYPE_ADD);
			
			JMenu bookInfoMenu = new JMenu("图书信息管理");
			bookInfoMenu.add(MenuActions.BOOK_ADD);
			bookInfoMenu.add(MenuActions.BOOK_MODIFY_DEL);
			baseMenu.add(readerMenu);
			baseMenu.add(bookTypeMenu);
			baseMenu.add(bookInfoMenu);
			baseMenu.addSeparator();	
		}	
		JMenu newbookOrderMenu = new JMenu();
		newbookOrderMenu.setIcon(CreatecdIcon.add("xsdgcd.jpg"));
		
		JMenu borrowMenu = new JMenu();
		borrowMenu.setIcon(CreatecdIcon.add("jyglcd.jpg"));
		borrowMenu.add(MenuActions.BOOK_BORROW);
		borrowMenu.add(MenuActions.BOOK_BACK);
		borrowMenu.add(MenuActions.BOOK_SEARCH);
		JMenu sysManagerMenu = new JMenu();
		sysManagerMenu.setIcon(CreatecdIcon.add("jcwhcd.jpg"));
		
		menuBar.add(baseMenu);
		menuBar.add(newbookOrderMenu);
		menuBar.add(borrowMenu);
		menuBar.add(sysManagerMenu);		
		return menuBar;
	}
	public static void main(String[] args){
		new Library().setVisible(true);
	}
}
