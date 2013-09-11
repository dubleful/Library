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
	public static PasswordModiAction MODIFY_PASSWORD; // �޸����봰�嶯��
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
			putValue(Action.NAME,"���Ŀ���");
			putValue(Action.LONG_DESCRIPTION, "�޸ĵ�ǰ�û�����");
			putValue(Action.SHORT_DESCRIPTION, "��������");//�ڡ����Ŀ����ʾ����ʾ������
			//putValue(Action.SMALL_ICON,CreatecdIcon.add("bookAddtb.jpg"));
			//��ͼ��洢������������
			//setEnabled(false);//ʹ��������
		}
		public void actionPerformed(ActionEvent e) {
		/*	if (!frames.containsKey("��������")||frames.get("��������").isClosed()) {
				GengGaiMiMa iframe=new GengGaiMiMa();
				frames.put("��������", iframe);
				Library.addIFame(frames.get("��������"));
			}*/
		}
	}
	
	private static class UserAddAction extends AbstractAction{
		public UserAddAction() {
			// TODO Auto-generated constructor stub
			super("�û����", null);
			putValue(Action.LONG_DESCRIPTION, "����µ��û�");
		}

		@Override
		public void actionPerformed(ActionEvent e) {	
		}		
	}//end class UserAddAction
	
	private static class ReaderAddAction extends AbstractAction{
		public ReaderAddAction(){
			super("�������", null);//��ʾ
			putValue(Action.SHORT_DESCRIPTION, "��Ӷ��������Ϣ");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!frames.containsKey("�������") || frames.get("�������").isClosed()){
				ReaderAddIFrame iFrame = new ReaderAddIFrame();
				frames.put("�������", iFrame);
				Library.AddIFrame(iFrame);
			}
		}
	}//end class ReaderAddAction
	
	
	private static class ReaderModifyAction extends AbstractAction{
		public ReaderModifyAction(){
			super("�޸Ķ�����Ϣ", null);
			putValue(Action.SHORT_DESCRIPTION, "�����޸�");
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!frames.containsKey("�����޸�") || frames.get("�����޸�").isClosed()){
				ReaderModifyIFrame iframe = new ReaderModifyIFrame();
				frames.put("�����޸�", iframe);
				Library.AddIFrame(iframe);
			}	
		}
	}//end class ReaderModifyAction
	
	private static class BookTypeAddAction extends AbstractAction{
		
		public BookTypeAddAction(){
			super("ͼ��������");
			putValue(Action.SHORT_DESCRIPTION, "���ͼ�����");
			}

		@Override
		public void actionPerformed(ActionEvent e) {

			if(!frames.containsKey("ͼ��������") || frames.get("ͼ��������").isClosed()){
				BookTypeAddIFrame iframe = new BookTypeAddIFrame();
				frames.put("ͼ��������", iframe);
				Library.AddIFrame(iframe);
			}
		}
	}//end class BookTypeAddAction
	
	private static class BookAddAction extends AbstractAction{
		public BookAddAction(){
			super("ͼ�����", null);
			putValue(Action.SHORT_DESCRIPTION, "ͼ����Ϣ���");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!frames.containsKey("ͼ�����") || frames.get("ͼ�����").isClosed()){
				BookAddIFrame iframe = new BookAddIFrame();
				frames.put("ͼ�����", iframe);
				Library.AddIFrame(iframe);
			}	
		}
	}//end class BookAddAction
	
	private static class BookModifyAndDelAction extends AbstractAction{
		
		public BookModifyAndDelAction(){
			super("ͼ���޸Ļ�ɾ��", null);
			putValue(Action.SHORT_DESCRIPTION, "ͼ���޸Ļ�ɾ��");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!frames.containsKey("ͼ���޸�/ɾ��") || frames.get("ͼ���޸�/ɾ��").isClosed()){
				BookModifyAndDelIFrame iframe = new BookModifyAndDelIFrame();
				frames.put("ͼ���޸�/ɾ��", iframe);
				Library.AddIFrame(iframe);
			}	
		}
	}//end class BookModifyAndDelAction
	
	public static class BookBorrowAction extends AbstractAction{
		public BookBorrowAction(){
			super("ͼ�����", null);
			putValue(Action.SHORT_DESCRIPTION, "ͼ����Ĺ���");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!frames.containsKey("ͼ�����") || frames.get("ͼ�����").isClosed()){
				BookBorrowIFrame iframe = new BookBorrowIFrame();
				frames.put("ͼ�����", iframe);
				Library.AddIFrame(iframe);
			}
		}//end 	
	}//end class BookBorrowAction
	
	public static class BookBackAction extends AbstractAction{
		public BookBackAction(){
			super("ͼ��黹", null);
			putValue(Action.SHORT_DESCRIPTION, "ͼ��黹����");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!frames.containsKey("ͼ��黹") || frames.get("ͼ��黹").isClosed()){
				BookBackIFrame iframe = new BookBackIFrame();
				frames.put("ͼ��黹", iframe);
				Library.AddIFrame(iframe);
			}
			
		}
	
	}//end class BookBackAction
	
	public static class BookSearchAction extends AbstractAction{

		public BookSearchAction(){
			super("ͼ���ѯ", null);
			putValue(Action.SHORT_DESCRIPTION, "ͼ���ѯ����");
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!frames.containsKey("ͼ���ѯ") || frames.get("ͼ���ѯ").isClosed()){
				BookSearchIFrame iframe = new BookSearchIFrame();
				frames.put("ͼ���ѯ", iframe);
				Library.AddIFrame(iframe);
			}		
		}//end 	
	}//end class BookSearchAction
}
