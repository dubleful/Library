package com.hww.iframe;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import com.hww.JComPZ.MapPz;
import com.hww.dao.Dao;
import com.hww.model.BookInfo;
import com.hww.model.BookType;
import com.hww.model.ReaderInfo;
import com.hww.util.MyDocument;

public class BookBorrowIFrame extends JInternalFrame {

	private JTextField readerID;
	private JTextField readerName;
	private JTextField maxNumber;
	private JTextField keepMoney;
	private JTextField bookID;
	private JTextField bookName;
	private JTextField bookCategory;
	private JTextField bookPrice;
	private JTable table;
	DefaultTableModel model;
	JScrollPane scrollPane;
	String[] columnNames = { "书籍编号", "借书日期", "应还日期", "读者编号" };
	SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd");
	JButton borrowConfirm;
	JButton close;
	BookInfo bookInfo;
	ReaderInfo readerInfo;
	Map<String, BookType> map = MapPz.getMap();

	void add() {
		String[] row = new String[4];
		row[0] = bookID.getText();
		row[1] = String.valueOf(myfmt.format(new java.util.Date()));
		row[2] = myfmt.format(getBackDate()).toString();
		row[3] = readerID.getText();
		model.addRow(row);
		
	}

	private Date getBackDate() {
		java.util.Date nowDate = new java.util.Date();
		if ((bookInfo != null) && (readerInfo != null))
			nowDate.setDate(nowDate.getDay()
					+ map.get(bookInfo.getTypeID()).getDays());
		return nowDate;
	}

	public BookBorrowIFrame() {
		super();
		setTitle("图书借阅管理");
		setBounds(100, 100, 400, 260);
		setClosable(true);
		setMaximizable(true);
		JPanel panel = new JPanel();
		GridLayout gridlayout = new GridLayout(0, 1, 0, 0);
		panel.setLayout(gridlayout);
		getContentPane().add(panel);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setPreferredSize(new Dimension(400, 100));
		panel.add(splitPane);

		JPanel panel1 = new JPanel();
		panel.setPreferredSize(new Dimension(300, 100));
		splitPane.setLeftComponent(panel1);
		GridLayout girdlayout1 = new GridLayout(0, 2, 5, 5);
		panel1.setLayout(girdlayout1);

		JLabel label1 = new JLabel("读者编号：", SwingConstants.CENTER);
		panel1.add(label1);
		readerID = new JTextField();
		readerID.setDocument(new MyDocument(9));
		panel1.add(readerID);
		readerID.addKeyListener(new ReaderInfoKey());
		JLabel label2 = new JLabel("读者姓名：", SwingConstants.CENTER);
		panel1.add(label2);
		readerName = new JTextField();
		readerName.setEditable(false);
		panel1.add(readerName);

		JLabel label3 = new JLabel("可借数量：", SwingConstants.CENTER);
		panel1.add(label3);
		maxNumber = new JTextField();
		maxNumber.setEditable(false);
		panel1.add(maxNumber);

		JLabel label4 = new JLabel("押金：", SwingConstants.CENTER);
		panel1.add(label4);
		keepMoney = new JTextField();
		keepMoney.setEditable(false);
		panel1.add(keepMoney);
		// ///////////////////////////////////////////

		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(100, 100));

		GridLayout gridLayout2 = new GridLayout(0, 2, 5, 5);
		panel2.setLayout(gridLayout2);
		JLabel label5 = new JLabel("图书编号：", SwingConstants.CENTER);
		panel2.add(label5);
		bookID = new JTextField();
		bookID.setDocument(new MyDocument(13));
		panel2.add(bookID);
		bookID.addKeyListener(new BookInfoKey());

		JLabel label6 = new JLabel("图书名称：", SwingConstants.CENTER);
		panel2.add(label6);
		bookName = new JTextField();
		bookName.setEditable(false);
		panel2.add(bookName);

		JLabel label7 = new JLabel("图书类别：", SwingConstants.CENTER);
		panel2.add(label7);
		bookCategory = new JTextField();
		bookCategory.setEditable(false);
		panel2.add(bookCategory);

		JLabel label8 = new JLabel("图书价格：", SwingConstants.CENTER);
		panel2.add(label8);
		bookPrice = new JTextField();
		bookPrice.setEditable(false);
		panel2.add(bookPrice);
		splitPane.setRightComponent(panel2);

		JPanel panel3 = new JPanel();
		table = new JTable();
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();// 表格内容居中
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 100));
		scrollPane.setViewportView(table);
		panel3.add(scrollPane);
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		panel.add(panel3);

		JPanel panel4 = new JPanel();
		borrowConfirm = new JButton("借出当前图书");
		panel4.add(borrowConfirm);
		borrowConfirm.addActionListener(new BookBorrowAction());
		panel.add(panel4);
		setVisible(true);

	}

	class ReaderInfoKey extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == '\n') {
				readerInfo = Dao.selectReaderInfo(readerID.getText());
				if (readerInfo == null) {
					JOptionPane.showMessageDialog(null, "未找到此读者的信息");
				} else {
					readerName.setText(readerInfo.getReaderName());
					maxNumber.setText(readerInfo.getMaxNumb());
					keepMoney.setText(readerInfo.getKeepMoney());
				}
			}
		}
	}// end class ReaderInfoKey

	class BookInfoKey extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == '\n') {
				bookInfo = Dao.selectBookInfo(bookID.getText());
				if (bookInfo == null) {
					JOptionPane.showMessageDialog(null, "未找到此图书的信息");
				} else {
					bookName.setText(bookInfo.getBookName());
					bookCategory.setText(map.get(bookInfo.getTypeID())
							.getTypeName());
					bookPrice.setText(bookInfo.getPrice());
				}
				if (readerID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "请输入借书人号码");
					return;
				}
				add();
			}
		}
	}// end class BookInfoKey

	class BookBorrowAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			String sbookISBN = null;
			String sreaderId = null;
			String soperatorId = null;
			String sborrowDate = null;
			String sbackDate = null;
			if (!bookID.getText().isEmpty() && (!readerID.getText().isEmpty())){
				sbookISBN = bookID.getText();
				sreaderId = readerID.getText();
				soperatorId = "lm120000";
				sborrowDate = myfmt.format(new java.util.Date());
				sbackDate = myfmt.format(getBackDate());
				int i = Dao.insertBorrowBook(sbookISBN, soperatorId, sreaderId,
						java.sql.Date.valueOf(sborrowDate),
						java.sql.Date.valueOf(sbackDate));
				if (i == 1) {
					JOptionPane.showMessageDialog(null, "借书成功");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "借书失败");
					return;
				}
			}		
		}
	}// end class BookBorrowAction

}
