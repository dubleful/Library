package com.hww.iframe;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.hww.dao.Dao;
import com.hww.util.MyDocument;

public class ReaderAddIFrame extends JInternalFrame {
	private JTextField readerName;
	private JTextField age;
	private JTextField tel;
	private JTextField identityCard;//证件编码
	private JComboBox typeID;
	private JTextField maxNumb;
	private JTextField readerNumber; //读者编号
	private JTextField keepMoney;
	private JButton addReader;
	private JButton close;
	private JFormattedTextField bzDate;
	JRadioButton boy;
	JRadioButton girl;

	public ReaderAddIFrame(){
		super();
		setTitle("添加读者信息");
		setClosable(true);
		setBounds(100, 100, 500, 350);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));
		GridLayout gridlayout = new GridLayout(0, 4, 5, 5);
		panel.setLayout(gridlayout);
		getContentPane().add(panel);
		
		JLabel label1 = new JLabel("姓名：", SwingConstants.CENTER);
		panel.add(label1);
		readerName = new JTextField();
		panel.add(readerName);
		
		JLabel label2 = new JLabel("性别：", SwingConstants.CENTER);
		panel.add(label2);
		ButtonGroup bg = new ButtonGroup();
		JPanel panel2 = new JPanel();
		boy = new JRadioButton("男");
		girl = new JRadioButton("女");
		bg.add(boy); bg.add(girl);
		panel2.add(boy); panel2.add(girl);
		panel.add(panel2);
		
		JLabel label3 = new JLabel("年龄：", SwingConstants.CENTER);
		panel.add(label3);
		age =  new JTextField();
		panel.add(age);
		
		JLabel label4 = new JLabel("电话：", SwingConstants.CENTER);
		panel.add(label4);
		tel = new JTextField();
		tel.setDocument(new MyDocument(11));
		panel.add(tel);
		
		JLabel label5 = new JLabel("有效证件：", SwingConstants.CENTER);
		panel.add(label5);
		String[] stypeID = {"学生证", "教师证"};
		typeID = new JComboBox(stypeID);
		panel.add(typeID);
		
		JLabel label6 = new JLabel("证件号码：", SwingConstants.CENTER);
		panel.add(label6);
		identityCard = new JTextField();
		identityCard.addFocusListener(new readerIDAction());
		panel.add(identityCard);
		
		JLabel label7 = new JLabel("读者编号:", SwingConstants.CENTER);
		panel.add(label7);
		readerNumber = new JTextField();
		panel.add(readerNumber);
		
		JLabel label8 =  new JLabel("可借阅最大图书：", SwingConstants.CENTER);
		panel.add(label8);
		maxNumb = new JTextField();
		panel.add(maxNumb);
		
		JLabel label9 = new JLabel("办证时间：", SwingConstants.CENTER);
		panel.add(label9);
		SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-mm-dd");
		bzDate = new JFormattedTextField(myfmt.getDateInstance());
		bzDate.setValue(new java.util.Date());
		panel.add(bzDate);
		
		JLabel label10 = new JLabel("剩余余款：", SwingConstants.CENTER);
		panel.add(label10);
		keepMoney = new JTextField();
		panel.add(keepMoney);
		
		panel.add(new JLabel());
		panel.add(new JLabel());
		addReader = new JButton("确认");
		panel.add(addReader);
		close = new JButton("取消");

		panel.add(close);
		
		addReader.addActionListener(new addReaderAction());
		close.addActionListener(new closeAction());
		setVisible(true);
	}
	class readerIDAction implements FocusListener{

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent e) {
			if(Dao.isExistReaderID(identityCard.getText())){
				JOptionPane.showMessageDialog(null, "证件号码已经存在");
				return;
			}
			
		}
		
	}
	
	class addReaderAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(readerName.getText().length() == 0){
				JOptionPane.showMessageDialog(null, "读者姓名不能为空");
				return;
			}
			if(!boy.isSelected() && !girl.isSelected()){
				JOptionPane.showMessageDialog(null, "请选择读者性别");
				return;
			}			
			String sreaderName = readerName.getText();
			String sex;
			if(boy.isSelected())
				sex = "男";
			else
				sex = "女";
			int sage;
			try{
				sage = Integer.parseInt(age.getText());
			}catch(NumberFormatException ee){
				JOptionPane.showMessageDialog(null, "年龄必须是数字");
				return;
			}
			
			if(tel.getText().length() != 11){
				JOptionPane.showMessageDialog(null, "电话号码必须是11位");
				return;
			}
			
			String stypeID = typeID.getSelectedItem().toString();
			String sidentityID = identityCard.getText();
			int smaxNumb;
			try{
				smaxNumb = Integer.parseInt(maxNumb.getText());
			}catch(NumberFormatException ee){
				JOptionPane.showMessageDialog(null, "最大借阅量必须是数字");
				return;
			}
			
			String sbzDate = bzDate.getText();
			double skeepMoney;
			try{
				skeepMoney = Double.parseDouble(keepMoney.getText());
			}catch(NumberFormatException ee){
				JOptionPane.showMessageDialog(null, "账户余额金钱必须是小数");
				return;
			}
			
			int i= Dao.insertReader(sreaderName, sex, sage, stypeID, sidentityID, java.sql.Date.valueOf(sbzDate)
					, smaxNumb, skeepMoney);
			if(i == 1)
				JOptionPane.showMessageDialog(null, "添加正确！");
			else
				JOptionPane.showMessageDialog(null, "添加失败");
		}	
	}//end addReaderAction
	
	class closeAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);	
		}
		
	}
}
