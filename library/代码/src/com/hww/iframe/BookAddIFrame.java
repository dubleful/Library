package com.hww.iframe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

import com.hww.JComPZ.Item;
import com.hww.dao.Dao;
import com.hww.model.BookType;
import com.hww.util.MyDocument;

public class BookAddIFrame extends JInternalFrame {
	private JTextField ISBN;//ͼ����
	private JTextField bookName;//ͼ������
	private JTextField writer;//����
	private JTextField translator;//������
	private JTextField price;//�۸�
	private JComboBox bookType;//ͼ������
	private JComboBox publisher;//������
	private JFormattedTextField pubDate;//��������
	private JButton btnAddBook;//ȷ�����
	private JButton btnClose;//ȡ�����
	private DefaultComboBoxModel bookTypeModel;
	
	public BookAddIFrame(){
		super();
		setTitle("ͼ�����");
		setClosable(true);
		setBounds(100, 100, 396, 260);
		setMaximizable(true);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));
		GridLayout gridlayout = new GridLayout(0, 4, 5, 5);
		panel.setLayout(gridlayout);
		getContentPane().add(panel);
		
		JLabel label1 = new JLabel("��ţ�");
		panel.add(label1);
		
		ISBN = new JTextField();
		ISBN.setDocument(new MyDocument(4));
		ISBN.addFocusListener(new ISBNAction());
		panel.add(ISBN);
		
		JLabel label2 = new JLabel("���");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label2);	
		bookType = new JComboBox();
		bookTypeModel = (DefaultComboBoxModel) bookType.getModel();
		List list = Dao.selectBookCategory();
		Iterator<BookType> iter = list.iterator();		
		while(iter.hasNext()){
			BookType obj = iter.next();
			Item item = new Item();
			item.setId((String)obj.getTypeID());
			item.setName((String)obj.getTypeName());
			bookTypeModel.addElement(item);
		}
		panel.add(bookType);
		
		JLabel label3 = new JLabel("������");
		panel.add(label3);
		bookName = new JTextField();
		panel.add(bookName);
		
		JLabel label4 = new JLabel("���ߣ�");
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label4);
		writer = new JTextField();
		panel.add(writer);
		
		JLabel label5 = new JLabel("�����磺");
		panel.add(label5);
		publisher = new JComboBox();
		publisher.addItem("��е��ҵ������");
		panel.add(publisher);
		
		JLabel label6 = new JLabel("���ߣ�");
		label6.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label6);
		translator = new JTextField();
		panel.add(translator);
		
		JLabel label7 = new JLabel("�������ڣ�");
		panel.add(label7);
		SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-mm-dd");
		pubDate = new JFormattedTextField(myfmt.getDateInstance());
		pubDate.setValue(new java.util.Date());
		panel.add(pubDate);
		
		JLabel label8 = new JLabel("���ۣ�");
		label8.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label8);
		price = new JTextField();
		panel.add(price);
		
		panel.add(new JLabel());
		panel.add(new JLabel());
		
		btnAddBook = new JButton("ȷ��");
		btnAddBook.addActionListener(new addBookAction());
		panel.add(btnAddBook);
		
		btnClose = new JButton("�ر�");
		btnClose.addActionListener(new closeAction());
	
		panel.add(btnClose);
		
		setVisible(true);
	}
	
	class ISBNAction implements FocusListener{

		@Override
		public void focusGained(FocusEvent e) {	
		}

		@Override
		public void focusLost(FocusEvent e) {		
			if(Dao.isExistISBN(ISBN.getText().trim())){
				JOptionPane.showMessageDialog(null, "�������ظ�");
				return;
			}	
		}
		
	}
	
	/*
	 * �������鰴ť����
	 */
	class addBookAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(ISBN.getText().toString().length() == 0){//���
				JOptionPane.showMessageDialog(null, "ͼ���Ų���Ϊ�գ�");
				return;
			}
			if(ISBN.getText().toString().length() != 4){
				JOptionPane.showMessageDialog(null, "ͼ���Ų�Ϊ4λ");
				return;
			}
			
			if(bookType.getSelectedItem() == null){//���
				JOptionPane.showMessageDialog(null, "������Ʋ���Ϊ�գ�");
				return;
			}
			if(bookName.getText().toString().length() == 0){//����
				JOptionPane.showMessageDialog(null, "��������Ϊ�գ�");
				return;
			}
			if(writer.getText().toString().length() == 0){//����
				JOptionPane.showMessageDialog(null, "����������Ϊ�գ�");
				return;
			}
			
			if(publisher.getSelectedItem() == null){//������
			
				JOptionPane.showMessageDialog(null, "������������Ϊ�գ�");
				return;
			}
			if(translator.getText().toString().length() == 0){//����
				JOptionPane.showMessageDialog(null, "���߲���Ϊ�գ�");
				return;
			}
			if(pubDate.getText().toString().length() == 0){//��������
			
				JOptionPane.showMessageDialog(null, "�������ڲ���Ϊ�գ�");
				return;
			}
			if(price.getText().toString().length() == 0){//�۸�
			
				JOptionPane.showMessageDialog(null, "�۸���Ϊ��");
				return;
			}
			
			String sISBN = ISBN.getText().toString();
			String sbookName = bookName.getText().toString();
			Object obj = bookType.getSelectedItem();
			if(obj == null){
				return;
			}
			
			Item item = (Item)obj;
			String sbookType = item.getId();
			String swriter = writer.getText().toString();
			String spublisher = publisher.getSelectedItem().toString();
			String stranslator = translator.getText().toString();
			String spubDate = pubDate.getText().toString();
			Double sprice;
			try{
				sprice= Double.parseDouble(price.getText().toString());
			}catch(NumberFormatException ee){
				JOptionPane.showMessageDialog(null, "�۸����ΪС��");
				return;
			}
			int i = Dao.insertBookInfo(sISBN, sbookType, sbookName, swriter, stranslator, spublisher, java.sql.Date.valueOf(spubDate), sprice);
			if(i == 1)
				JOptionPane.showMessageDialog(null, "��ӳɹ�");
		}	
	}//end class addBookAction
	
	class closeAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}//end class closerAction
	
	public static void main(String[] args){
		new BookAddIFrame();
	}
}
