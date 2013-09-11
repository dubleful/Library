package com.hww;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;

import com.hww.iframe.BookAddIFrame;
import com.hww.iframe.BookBackIFrame;
import com.hww.iframe.BookBorrowIFrame;
import com.hww.iframe.BookModifyAndDelIFrame;
import com.hww.iframe.BookSearchIFrame;
import com.hww.iframe.BookTypeAddIFrame;
import com.hww.iframe.ReaderAddIFrame;
import com.hww.iframe.ReaderModifyIFrame;

public class MenuActions {

	private static Map<String, JInternalFrame> frames;
	public static PasswordModiAction MODIFY_PASSWORD; // 修改密码窗体动作
	public static UserAddAction ADD_USER;
	public static ReaderAddAction READER_ADD;
	public static ReaderModifyAction READER_MODIFY;
	public static BookTypeAddAction BOOK_TYPE_ADD;
	public static BookAddAction BOOK_ADD;
	public static BookModifyAndDelAction BOOK_MODIFY_DEL;
	public static BookBorrowAction BOOK_BORROW;
	public static BookBackAction BOOK_BACK;
	public static BookSearchAction BOOK_SEARCH;
	static{
		frames = new HashMap<String, JInternalFrame>();
		MODIFY_PASSWORD = new PasswordModiAction();
		ADD_USER = new UserAddAction();
		READER_ADD = new ReaderAddAction();
		READER_MODIFY = new ReaderModifyAction();
		BOOK_TYPE_ADD = new BookTypeAddAction();
		BOOK_ADD = new BookAddAction();
		BOOK_MODIFY_DEL = new BookModifyAndDelAction();
		BOOK_BACK = new BookBackAction();
		BOOK_BORROW = new BookBorrowAction();
		BOOK_SEARCH = new BookSearchAction();
	}
	private static class PasswordModiAction extends AbstractAction {
		PasswordModiAction() {
			putValue(Action.NAME,"更改口令");
			putValue(Action.LONG_DESCRIPTION, "修改当前用户密码");
			putValue(Action.SHORT_DESCRIPTION, "更换口令");//在“更改口令”提示中显示的文字
			//putValue(Action.SMALL_ICON,CreatecdIcon.add("bookAddtb.jpg"));
			//将图标存储到动作对象中
			//setEnabled(false);//使动作禁用
		}
		public void actionPerformed(ActionEvent e) {
		/*	if (!frames.containsKey("更改密码")||frames.get("更改密码").isClosed()) {
				GengGaiMiMa iframe=new GengGaiMiMa();
				frames.put("更改密码", iframe);
				Library.addIFame(frames.get("更改密码"));
			}*/
		}
	}
	
	private static class UserAddAction extends AbstractAction{
		public UserAddAction() {
			// TODO Auto-generated constructor stub
			super("用户添加", null);
			putValue(Action.LONG_DESCRIPTION, "添加新的用户");
		}

		@Override
		public void actionPerformed(ActionEvent e) {	
		}		
	}//end class UserAddAction
	
	private static class ReaderAddAction extends AbstractAction{
		public ReaderAddAction(){
			super("读者添加", null);//显示
			putValue(Action.SHORT_DESCRIPTION, "添加读者相关信息");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!frames.containsKey("读者添加") || frames.get("读者添加").isClosed()){
				ReaderAddIFrame iFrame = new ReaderAddIFrame();
				frames.put("读者添加", iFrame);
				Library.AddIFrame(iFrame);
			}
		}
	}//end class ReaderAddAction
	
	
	private static class ReaderModifyAction extends AbstractAction{
		public ReaderModifyAction(){
			super("修改读者信息", null);
			putValue(Action.SHORT_DESCRIPTION, "读者修改");
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!frames.containsKey("读者修改") || frames.get("读者修改").isClosed()){
				ReaderModifyIFrame iframe = new ReaderModifyIFrame();
				frames.put("读者修改", iframe);
				Library.AddIFrame(iframe);
			}	
		}
	}//end class ReaderModifyAction
	
	private static class BookTypeAddAction extends AbstractAction{
		
		public BookTypeAddAction(){
			super("图书类别添加");
			putValue(Action.SHORT_DESCRIPTION, "添加图书类别");
			}

		@Override
		public void actionPerformed(ActionEvent e) {

			if(!frames.containsKey("图书类别添加") || frames.get("图书类别添加").isClosed()){
				BookTypeAddIFrame iframe = new BookTypeAddIFrame();
				frames.put("图书类别添加", iframe);
				Library.AddIFrame(iframe);
			}
		}
	}//end class BookTypeAddAction
	
	private static class BookAddAction extends AbstractAction{
		public BookAddAction(){
			super("图书添加", null);
			putValue(Action.SHORT_DESCRIPTION, "图书信息添加");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!frames.containsKey("图书添加") || frames.get("图书添加").isClosed()){
				BookAddIFrame iframe = new BookAddIFrame();
				frames.put("图书添加", iframe);
				Library.AddIFrame(iframe);
			}	
		}
	}//end class BookAddAction
	
	private static class BookModifyAndDelAction extends AbstractAction{
		
		public BookModifyAndDelAction(){
			super("图书修改或删除", null);
			putValue(Action.SHORT_DESCRIPTION, "图书修改或删除");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!frames.containsKey("图书修改/删除") || frames.get("图书修改/删除").isClosed()){
				BookModifyAndDelIFrame iframe = new BookModifyAndDelIFrame();
				frames.put("图书修改/删除", iframe);
				Library.AddIFrame(iframe);
			}	
		}
	}//end class BookModifyAndDelAction
	
	public static class BookBorrowAction extends AbstractAction{
		public BookBorrowAction(){
			super("图书借阅", null);
			putValue(Action.SHORT_DESCRIPTION, "图书借阅管理");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!frames.containsKey("图书借阅") || frames.get("图书借阅").isClosed()){
				BookBorrowIFrame iframe = new BookBorrowIFrame();
				frames.put("图书借阅", iframe);
				Library.AddIFrame(iframe);
			}
		}//end 	
	}//end class BookBorrowAction
	
	public static class BookBackAction extends AbstractAction{
		public BookBackAction(){
			super("图书归还", null);
			putValue(Action.SHORT_DESCRIPTION, "图书归还管理");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!frames.containsKey("图书归还") || frames.get("图书归还").isClosed()){
				BookBackIFrame iframe = new BookBackIFrame();
				frames.put("图书归还", iframe);
				Library.AddIFrame(iframe);
			}
			
		}
	
	}//end class BookBackAction
	
	public static class BookSearchAction extends AbstractAction{

		public BookSearchAction(){
			super("图书查询", null);
			putValue(Action.SHORT_DESCRIPTION, "图书查询管理");
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!frames.containsKey("图书查询") || frames.get("图书查询").isClosed()){
				BookSearchIFrame iframe = new BookSearchIFrame();
				frames.put("图书查询", iframe);
				Library.AddIFrame(iframe);
			}		
		}//end 	
	}//end class BookSearchAction
}
