package com.hww.iframe;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.hww.dao.Dao;

public class BookTypeAddIFrame extends JInternalFrame {

	private JTextField typeName;
	private JTextField borrow_days;//�ɽ�����  
	private JTextField money_per_day;//ÿ�츶����
	private JButton addBookType;
	private JButton close;
	public BookTypeAddIFrame(){
		super();
		setTitle("ͼ��������");
		setClosable(true);
		setBounds(100, 100, 500, 200);
		setMaximizable(true);
		
		JPanel panel = new JPanel();
		GridLayout gridlayout = new GridLayout(0, 2);
		gridlayout.setHgap(5);
		gridlayout.setVgap(5);
		panel.setLayout(gridlayout);
		getContentPane().add(panel);
		
		
		JLabel label1 = new JLabel("��������", SwingConstants.CENTER);
		panel.add(label1);
		typeName = new JTextField(SwingConstants.CENTER);
		panel.add(typeName);
		
		JLabel label2 = new JLabel("�ɽ�������", SwingConstants.CENTER);
		panel.add(label2);
		borrow_days = new JTextField(SwingConstants.CENTER);
		panel.add(borrow_days);
		
		JLabel label3 = new JLabel("ÿ�췣���", SwingConstants.CENTER);
		panel.add(label3);
		money_per_day = new JTextField(SwingConstants.CENTER);
		panel.add(money_per_day);
		
		panel.add(new Label());
		JPanel panel2 = new JPanel(new GridLayout(0, 2, 5, 5));
		addBookType = new JButton("ȷ��");
		addBookType.addActionListener(new addBookTypeAction());
		panel2.add(addBookType);
		close = new JButton("�ر�");
		panel2.add(close);
		panel.add(panel2);
		setVisible(true);
	}
	
	class addBookTypeAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(typeName.getText().length() == 0){
				JOptionPane.showMessageDialog(null, "����������Ϊ��");
				return;
			}
			
			if(borrow_days.getText().length() == 0){
				JOptionPane.showMessageDialog(null, "�ɽ���������Ϊ��");
				return;
			}
			if(money_per_day.getText().length() == 0){
			
				JOptionPane.showMessageDialog(null, "����������Ϊ��");
				return;
			}
			
			String stypeName = typeName.getText();
			int sborrow_days = 0;
			double smoney_per_day = 0;
			try{
				sborrow_days = Integer.parseInt(borrow_days.getText().toString());
			}catch(NumberFormatException ee){
				ee.printStackTrace();
				JOptionPane.showMessageDialog(null, "�ɽ���������������");
				return;
			}
			try{
				smoney_per_day = Double.parseDouble(money_per_day.getText());
			}catch(NumberFormatException ee){
				ee.printStackTrace();
				JOptionPane.showMessageDialog(null, "ÿ�շ������������");
				return;
			}
			int i = Dao.insertBookType(stypeName, sborrow_days, smoney_per_day);
			if(i == 1){
				JOptionPane.showMessageDialog(null, "��ӳɹ�");
			}
			else
				JOptionPane.showMessageDialog(null, "���ʧ��");
		}
	}//end class addBookTypeAction
	
	class closeAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);		
		}
	}
}
