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
	private JTextField ISBN;//图书编号
	private JTextField bookName;//图书名称
	private JTextField writer;//作者
	private JTextField translator;//翻译者
	private JTextField price;//价格
	private JComboBox bookType;//图书类型
	private JComboBox publisher;//出版社
	private JFormattedTextField pubDate;//出版日期
	private JButton btnAddBook;//确认添加
	private JButton btnClose;//取消添加
	private DefaultComboBoxModel bookTypeModel;
	
	public BookAddIFrame(){
		super();
		setTitle("图书添加");
		setClosable(true);
		setBounds(100, 100, 396, 260);
		setMaximizable(true);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));
		GridLayout gridlayout = new GridLayout(0, 4, 5, 5);
		panel.setLayout(gridlayout);
		getContentPane().add(panel);
		
		JLabel label1 = new JLabel("书号：");
		panel.add(label1);
		
		ISBN = new JTextField();
		ISBN.setDocument(new MyDocument(4));
		ISBN.addFocusListener(new ISBNAction());
		panel.add(ISBN);
		
		JLabel label2 = new JLabel("类别：");
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
		
		JLabel label3 = new JLabel("书名：");
		panel.add(label3);
		bookName = new JTextField();
		panel.add(bookName);
		
		JLabel label4 = new JLabel("作者：");
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label4);
		writer = new JTextField();
		panel.add(writer);
		
		JLabel label5 = new JLabel("出版社：");
		panel.add(label5);
		publisher = new JComboBox();
		publisher.addItem("机械工业出版社");
		panel.add(publisher);
		
		JLabel label6 = new JLabel("译者：");
		label6.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label6);
		translator = new JTextField();
		panel.add(translator);
		
		JLabel label7 = new JLabel("出版日期：");
		panel.add(label7);
		SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-mm-dd");
		pubDate = new JFormattedTextField(myfmt.getDateInstance());
		pubDate.setValue(new java.util.Date());
		panel.add(pubDate);
		
		JLabel label8 = new JLabel("单价：");
		label8.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label8);
		price = new JTextField();
		panel.add(price);
		
		panel.add(new JLabel());
		panel.add(new JLabel());
		
		btnAddBook = new JButton("确认");
		btnAddBook.addActionListener(new addBookAction());
		panel.add(btnAddBook);
		
		btnClose = new JButton("关闭");
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
				JOptionPane.showMessageDialog(null, "新书编号重复");
				return;
			}	
		}
		
	}
	
	/*
	 * 增加新书按钮监听
	 */
	class addBookAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(ISBN.getText().toString().length() == 0){//书号
				JOptionPane.showMessageDialog(null, "图书编号不能为空！");
				return;
			}
			if(ISBN.getText().toString().length() != 4){
				JOptionPane.showMessageDialog(null, "图书编号不为4位");
				return;
			}
			
			if(bookType.getSelectedItem() == null){//类别
				JOptionPane.showMessageDialog(null, "类别名称不能为空！");
				return;
			}
			if(bookName.getText().toString().length() == 0){//书名
				JOptionPane.showMessageDialog(null, "书名不能为空！");
				return;
			}
			if(writer.getText().toString().length() == 0){//作者
				JOptionPane.showMessageDialog(null, "作者名不能为空！");
				return;
			}
			
			if(publisher.getSelectedItem() == null){//出版社
			
				JOptionPane.showMessageDialog(null, "出版社名不能为空！");
				return;
			}
			if(translator.getText().toString().length() == 0){//译者
				JOptionPane.showMessageDialog(null, "译者不能为空！");
				return;
			}
			if(pubDate.getText().toString().length() == 0){//出版日期
			
				JOptionPane.showMessageDialog(null, "出版日期不能为空！");
				return;
			}
			if(price.getText().toString().length() == 0){//价格
			
				JOptionPane.showMessageDialog(null, "价格不能为空");
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
				JOptionPane.showMessageDialog(null, "价格必须为小数");
				return;
			}
			int i = Dao.insertBookInfo(sISBN, sbookType, sbookName, swriter, stranslator, spublisher, java.sql.Date.valueOf(spubDate), sprice);
			if(i == 1)
				JOptionPane.showMessageDialog(null, "添加成功");
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
