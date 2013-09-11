package com.hww.iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.hww.JComPZ.MapPz;
import com.hww.dao.Dao;
import com.hww.model.Back;
import com.hww.model.BookType;

public class BookBackIFrame extends JInternalFrame {

	private JTextField readerISBN;
	JTable table;
	DefaultTableModel model;
	String[] columns = {"ͼ������", "ͼ����", "ͼ�����", "��������", "���߱��", "����ʱ��", "�黹ʱ��"};
	JTextField borrowDate;//��������
	JTextField days;//�涨����
	JTextField realDays;//ʵ������
	JTextField overDays;//��������
	JTextField fkMoney;//������
	JTextField nowDate; //��ǰʱ��
	JButton btnbackBook;//
	Map<String, BookType> map = MapPz.getMap();
	List<Back> list;
	SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM=dd");
	void add(){
		list = Dao.selectBookBack(readerISBN.getText());
		for(int i=0; i<list.size(); i++){
			String[] row = new String[7];
			row[0] = list.get(i).getBookName();
			row[1] = list.get(i).getBookISBN();
			row[2] = map.get(list.get(i).getTypeID()).getTypeName();
			row[3] = list.get(i).getReaderName();
			row[4] = list.get(i).getReaderISBN();
			row[5] =  myfmt.format(Timestamp.valueOf(list.get(i).getBorrowDate()));
			row[6] = myfmt.format(Timestamp.valueOf(list.get(i).getBackDate()));
			model.addRow(row);
		}
	}
	
	public BookBackIFrame(){
		super();
		setTitle("ͼ��黹����");
		setClosable(true);
		setBounds(100, 100, 550, 480);
		setMaximizable(true);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "������Ϣ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel.setPreferredSize(new Dimension(550, 180));
		getContentPane().add(panel,  BorderLayout.NORTH);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(400, 20));
		GridLayout gridLayout1 = new GridLayout(0, 2);
		panel_1.setLayout(gridLayout1);
		panel.add(panel_1);
		
		JLabel label1 = new JLabel("���߱�ţ�");
		panel_1.add(label1);
		readerISBN = new JTextField();
		panel_1.add(readerISBN);
		readerISBN.addKeyListener(new ReaderISBNKey());
		
		table = new JTable();
		model = new DefaultTableModel();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(500, 120));
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		model.setColumnIdentifiers(columns); //��ʾ����
		table.setModel(model);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();// ������ݾ���
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
	
		table.addMouseListener(new TableListener());
		
		JPanel panel2 = new JPanel();
		getContentPane().add(panel2);
	    /*������Ϣ*/
		JPanel panel2_1 =  new JPanel();
		panel2_1.setBorder(new TitledBorder(null, "�P����Ϣ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel2_1.setPreferredSize(new Dimension(250, 250));
		GridLayout gridLayout2_1 = new GridLayout(0, 2, 3, 3);
		panel2_1.setLayout(gridLayout2_1);
		panel2.add(panel2_1);
		
		JLabel label2 = new JLabel("��������:");
		panel2_1.add(label2);
		borrowDate = new JTextField();
		panel2_1.add(borrowDate);
		
		JLabel label3 = new JLabel("�涨������");
		panel2_1.add(label3);
		days = new JTextField();
		panel2_1.add(days);
		
		JLabel label4 = new JLabel("ʵ��������");
		panel2_1.add(label4);
		realDays = new JTextField();
		panel2_1.add(realDays);
		
		JLabel label5 = new JLabel("����������");
		panel2_1.add(label5);
		overDays = new JTextField();
		panel2_1.add(overDays);
		
		JLabel label6 = new JLabel("�����");
		panel2_1.add(label6);
		fkMoney = new JTextField();
		panel2_1.add(fkMoney);
		
		JPanel panel2_2 = new JPanel();
		panel2_2.setLayout(new GridLayout(0, 1, 5, 5));
		panel2_2.setBorder(new TitledBorder(null, "ϵͳ��Ϣ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel2_2.setPreferredSize(new Dimension(280, 250));
		panel2.add(panel2_2);
		
		JPanel panel2_2_1 = new JPanel();
		panel2_2_1.setLayout(new GridLayout(0, 2));
		panel2_2_1.setPreferredSize(new Dimension(150, 20));	
		panel2_2.add(panel2_2_1);
		
		JLabel label7 = new JLabel("��ǰʱ�䣺", SwingConstants.CENTER);
		panel2_2_1.add(label7);
		nowDate = new JTextField();
		java.util.Date date = new java.util.Date();
		panel2_2_1.add(nowDate);
		
		JPanel panel2_2_2 = new JPanel();
		panel2_2_2.setPreferredSize(new Dimension(200, 20));
		panel2_2.add(panel2_2_2);
		btnbackBook = new JButton("ȷ���˻�");
		btnbackBook.setPreferredSize(new Dimension(180, 30));
		panel2_2_2.add(btnbackBook);
		btnbackBook.addActionListener(new BorrowBookAction());
		setVisible(true);
	}
	
	class BorrowBookAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(table.getSelectedRow() != -1){//ѡ��һ��
				int selRow = table.getSelectedRow();
				String sbookISBN = table.getValueAt(selRow, 1).toString();
				String sreaderISBN = table.getValueAt(selRow, 4).toString();
				int i = Dao.deleteBackBook(sreaderISBN, sbookISBN);
				if(i == -1){
					JOptionPane.showMessageDialog(null, "����ʧ��");
					return;
				}
				else{
					model.removeRow(selRow);
					list.remove(selRow);
					JOptionPane.showMessageDialog(null, "����ɹ�");
					return;
				}
			}
		}
	}//end class BorrowBookAction
	
	/*
	 * ͼ���ŵİ����¼�
	 */
	class ReaderISBNKey extends KeyAdapter{
		 public void keyTyped(KeyEvent e) {
			 if(e.getKeyChar() == '\n'){
				 if(readerISBN.getText().length() == 0){
					 JOptionPane.showMessageDialog(null, "���߱�Ų���Ϊ�գ�");
					 return;
				 }
				 add();
			 }
		 }
	}//end class ReaderISBNKey
	
	class TableListener extends MouseAdapter{
		 public void mouseClicked(MouseEvent e) {
			 int selRow = table.getSelectedRow();
			 java.util.Date date_now = new java.util.Date();
			 int idays, irealdays, ioverdays, iborrowdays = 0;
			 String typeID = list.get(selRow).getTypeID();
			 String sborrowDate;//���������ַ���
			 Double fkday;
			
			 sborrowDate = table.getValueAt(selRow, 5).toString();//�ӱ���л�ý������
			 idays = map.get(typeID).getDays(); //�涨����			
	
			 try {
				iborrowdays = myfmt.parse(sborrowDate).getDate();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				System.out.println("����ʱ��תΪ��������");
			} //�����ݿ��е�ʱ��תΪ����
			 irealdays = date_now.getDate()- iborrowdays;//ʵ����������
			 if(irealdays > idays){
				 JOptionPane.showMessageDialog(nowDate, "����");
				 ioverdays = irealdays - idays;
			 }
			 else
				 ioverdays = 0;
			 
			 fkday = Double.parseDouble(map.get(typeID).getFakuan());
			 borrowDate.setText(sborrowDate);
			 days.setText(String.valueOf(map.get(typeID).getDays()));
			 realDays.setText(String.valueOf(irealdays));
			 overDays.setText(String.valueOf(ioverdays));
			 fkMoney.setText(String.valueOf(fkday * ioverdays));	 
		 }
	}
}
