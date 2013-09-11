package com.hww.iframe;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.hww.JComPZ.MapPz;
import com.hww.dao.Dao;
import com.hww.model.BookInfo;
import com.hww.model.BookType;

public class BookSearchIFrame extends JInternalFrame{

	JTabbedPane tabbedPane;
	JPanel panel_1;
	JPanel panel_2;
	JComboBox searchContent;
	JTextField searchName;
	String [] combbArray = {"图书名称", "作者"};
	JTable table;
	DefaultTableModel model;
	String[] columns = {"图书编号", "图书名称", "图书类型", "作者", "译者", "出版社", "出版日期", "价格"};
	JButton btnConfirm;
	JButton btnCancel;
	 JTable table2;
	DefaultTableModel model2;
	Map<String, BookType> map = MapPz.getMap(); 
	public void add(List<BookInfo> list){
		for(int i=0; i<list.size(); i++){
			String[] row = new String[8];
			row[0] = list.get(i).getISBN();
			row[1] = list.get(i).getBookName();
			row[2] = map.get(list.get(i).getTypeID()).getTypeName();
			row[3] = list.get(i).getWriter();
			row[4] = list.get(i).getTranslator();
			row[5] = list.get(i).getPublisher();
			row[6] = list.get(i).getDate();
			row[7] = list.get(i).getPrice();
			model.addRow(row);
		}
	}
	
	public void addAll(){
		List<BookInfo> list = Dao.selectBookInfo();
		if(list != null){
			for(int i=0; i<list.size(); i++){
				String[] row = new String[8];
				row[0] = list.get(i).getISBN();
				row[1] = list.get(i).getBookName();
				row[2] = map.get(list.get(i).getTypeID()).getTypeName();
				row[3] = list.get(i).getWriter();
				row[4] = list.get(i).getTranslator();
				row[5] = list.get(i).getPublisher();
				row[6] = list.get(i).getDate();
				row[7] = list.get(i).getPrice();
				model2.addRow(row);	
			}
		}
	}
	
	void clearTable(){
		int rows = model.getRowCount();
		for(int i=rows-1; i>=0; i--){
			model.removeRow(i);
		}
		table.removeAll();
	}
	public BookSearchIFrame(){
		super();
		setTitle("图书查询");
		setClosable(true);
		setBounds(100, 100, 550, 480);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.TOP);
		tabbedPane.setPreferredSize(new Dimension(0, 400));
		panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(3, 1));
		panel_2 = new JPanel();
		tabbedPane.addTab("条件查询", panel_1);
		tabbedPane.addTab("全部查询", panel_2);
	    getContentPane().add(tabbedPane);
	    
		JPanel panel2 = new JPanel();
		panel2.setBorder(new TitledBorder(null, "查询项目", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION));
        panel2.setLayout(new GridLayout(0, 2));   
        panel_1.add(panel2);
        
        searchContent = new JComboBox(combbArray);  
        searchContent.setPreferredSize(new Dimension(0, 20));
        panel2.add(searchContent);
        searchName = new JTextField();
        searchName.addKeyListener(new SearchKey());
        panel2.add(searchName);
      
        
        JScrollPane panel3 = new JScrollPane();
        panel_1.add(panel3);
        
        table = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        panel3.setViewportView(table);        
	    tabbedPane.addChangeListener(new TabChange());

	    JScrollPane scrollPane = new JScrollPane();
	    panel_2.add(scrollPane);
	    table2 = new JTable();
	    model2 = new DefaultTableModel();
	    model2.setColumnIdentifiers(columns);
	    table2.setModel(model2);
	    scrollPane.setViewportView(table2);
	    addAll(); 
		setVisible(true);
		
	}
	class TabChange implements ChangeListener{
		public TabChange(){
			
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			if(tabbedPane.getSelectedIndex() == 0){ //条件查询
		
			}		
		}
	}//end class TabChange
	
	class SearchKey extends KeyAdapter{
	    public void keyTyped(KeyEvent e) {
	    	
	    	if(e.getKeyChar() == '\n'){
	    		clearTable();
	    		if(searchName.getText().length() == 0){
	    			JOptionPane.showMessageDialog(null, "搜索内容不能为空");
	    			return;
	    		}
	    		
	    		if(searchContent.getSelectedIndex() == 0){//图书名称
	    			String bookName = searchName.getText().toString();
	    			List<BookInfo> list = Dao.selectBookInfo_bookName(bookName);
	    			if(list == null){
	    				JOptionPane.showMessageDialog(null, "查找数据库失败");
	    				return;
	    			}
	    			else{
	    				add(list);
	    			}//end else
	    		}//end if
	    		else{
	    			String author = searchName.getText().toString();
	    			List<BookInfo>list = Dao.selectBookInfo_author(author);
	    			if(list == null){
	    				JOptionPane.showMessageDialog(null, "查找数据库失败");
	    				return;
	    			}//end if
	    			else{
	    				add(list);
	    			}//end else
	    		}//end else
	    	}//end if
	    }
	}//end keyAdapter
}
