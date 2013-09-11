package com.hww.dao;

import java.awt.event.MouseAdapter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hww.model.Back;
import com.hww.model.BookInfo;
import com.hww.model.BookType;
import com.hww.model.ReaderInfo;
public class Dao {

	protected String driver = "com.mysql.jdbc.Driver";
	protected String url = "jdbc:mysql://localhost:3306/db_library?useUnicode=true&characterEncoding=GBK";
	protected String user = "root";
	protected String password = "123";
	private static Connection conn  = null;
	public Dao() {
		try{
			if(conn == null){
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user, password);
			}else
				return;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/*
	 * 查询
	 */
	public static ResultSet executeQuery(String sql){
		try{
			if(conn == null)
				new Dao();
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}//end executeQuery()
	
	/*
	 * 数据更新操作成功返回1；失败返回-1
	 */
	public static int executeUpdate(String sql){
		try{
			if(conn == null)
				new Dao();
			return conn.createStatement().executeUpdate(sql);
		}catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally{
			close();
		}
		
	}
	
	public static void close(){
		try{
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			conn = null;
		}
	}

	/*
	 * 图书分类
	 */
	public static List selectBookCategory(){
		List list = new ArrayList();
		String sql = "select * from tb_booktype";
		ResultSet reset = executeQuery(sql);
		try {
			while(reset.next()){
				BookType booktype = new BookType();
				booktype.setTypeID(reset.getString("typeID"));
				booktype.setTypeName(reset.getString("typeName"));
				booktype.setDays(Integer.parseInt(reset.getString("days")));
				booktype.setFakuan(reset.getString("fakuan"));
				list.add(booktype);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return list;
	}
	
	/*
	 * 插入图书信息
	 */
	public static int insertBookInfo(String ISBN, String typeID, String bookName, String writer, String translator, String publisher, Date data, double price){
		String sql = "Insert into tb_book(ISBN, typeID, bookname, writer, translator, publisher, data, price)" +
				"values('"+ISBN+"', '"+typeID+"', '"+bookName+"', '"+writer+"', '"+translator+"', '"+publisher+"', '"+data+"', '"+price+"')";
		return executeUpdate(sql);
		
	}

	public static int insertBookType(String typeName, int borrow_days, double money_per_day){
		String sql = "insert into tb_booktype(typeName, days, fakuan)" +
				"values('"+typeName+"', '"+borrow_days+"', '"+money_per_day+"')";
		return executeUpdate(sql);
	}
	
	public static boolean isExistISBN(String bookNo){
		String sql = "select * from tb_book where ISBN = '"+bookNo+"'";
		ResultSet rs = executeQuery(sql);
		try {
			if(rs.next()){
				System.out.print(rs.getString(1));
				return true;
			}
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static int insertReader(String readerName, String sex, int age, String type, String ID, Date bzDate, int maxNumb, double keepMoney){
		String sql = "insert into tb_reader(name, sex, age, zjtype, identityCard, bztime, maxNumb, keepMoney)" +
				"values('"+readerName+"', '"+sex+"', '"+age+"', '"+type+"', '"+ID+"', '"+bzDate+"', '"+maxNumb+"', '"+keepMoney+"')";
		return executeUpdate(sql);
	}//end insertReader
	
	public static boolean isExistReaderID(String readerID){
		String sql = "select * from tb_reader where identityCard = '"+readerID+"'";
		ResultSet rs = executeQuery(sql);
		try{
			if(rs.next()){
				System.out.print(rs.getString("identityCard"));
				return true;
			}
			else
				return false;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}//end isExistReaderID
	
	/*
	 * 按读者编号查询信息
	 */
	public static ReaderInfo selectReaderInfo(String readerID){
		ReaderInfo readerInfo = new ReaderInfo();
		String sql = "select * from tb_reader where identityCard = '"+readerID+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try{
			if(rs.next()){
				readerInfo.setReaderName(rs.getString("name"));
				readerInfo.setSex(rs.getString("sex"));
				readerInfo.setAge(rs.getString("age"));
				readerInfo.setZjtype(rs.getString("zjtype"));
				readerInfo.setIdentityCard(rs.getString("identityCard"));
				readerInfo.setBjDate(rs.getString("bztime"));
				readerInfo.setMaxNumb(rs.getString("maxNumb"));
				readerInfo.setKeepMoney(rs.getString("keepMoney"));
				return readerInfo;
			}
			else
				return null; //没有找到
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("数据库查询异常！");
			return null;
		}
	}//end class ReaderInfo
	
	/*
	 * 查询图书信息
	 */
	public static BookInfo selectBookInfo(String bookID){
		BookInfo bookInfo = new BookInfo();
		String sql = "select * from tb_book where ISBN = '"+bookID+"'";
		ResultSet rs =  Dao.executeQuery(sql);
		try{
			if(rs.next()){
				bookInfo.setISBN(rs.getString("ISBN"));
				bookInfo.setTypeID(rs.getString("typeID"));
				try {
					bookInfo.setBookName(rs.getString("bookname"));
					bookInfo.setWriter(rs.getString("writer"));
					bookInfo.setTranslator(rs.getString("translator"));
					bookInfo.setPublisher(rs.getString("publisher"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				bookInfo.setDate(rs.getString("data"));
				bookInfo.setPrice(rs.getString("price"));
				return bookInfo;
			}
			else
				return null;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("数据库异常");
			return null;
		}
	}//end
	
	/*
	 * 向借书图书表中插入借书信息
	 */
	public static int insertBorrowBook(String bookISBN, String operatorID, String readerISBN, Date borrowDate, Date backDate){
		String sql = "insert into tb_borrow(bookISBN, operatorID, readerISBN, borrowDate, backDate, isBack)values" +
				"('"+bookISBN+"', '"+operatorID+"', '"+readerISBN+"', '"+borrowDate+"', '"+backDate+"', '0')";
		return executeUpdate(sql);
	}
	
	public static List selectBookBack(String readerISBN){
		List list = new ArrayList<Back>();
		String sql = "select a.name AS readerName, b.borrowDate, b.backDate, c.typeID, c.bookname, c.ISBN FROM tb_reader AS a INNER JOIN tb_borrow As b ON a.identityCard = b.readerISBN " +
				"INNER JOIN tb_book AS c ON b.bookISBN = c.ISBN where b.readerISBN = '"+readerISBN+"' AND b.isBack = 0";
		
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()){
				Back back = new Back();
				back.setBookISBN(rs.getString("ISBN"));
				back.setBackDate(rs.getString("backDate"));
				back.setBorrowDate(rs.getString("borrowDate"));
				back.setBookName(rs.getString("bookName"));
				back.setReaderISBN(readerISBN);
				back.setReaderName(rs.getString("readerName"));	
				back.setTypeID(rs.getString("typeID"));
				list.add(back);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}//end 
	
	/*
	 * 还书操作
	 */
	public static int deleteBackBook(String readerISBN, String bookISBN){
		String sql = "update tb_borrow set isBack = 1 where bookISBN = '"+bookISBN+"' AND readerISBN = '"+readerISBN+"'";
		return executeUpdate(sql);
	}
	
	/*
	 * 根据书名查找图书信息
	 */
	public static List<BookInfo> selectBookInfo_bookName(String bookName){
		List<BookInfo> list = new ArrayList<BookInfo>();
		String sql = "select * from tb_book where bookname LIKE '%"+bookName+"%'";
		ResultSet rs = executeQuery(sql);
		if(rs == null){
			return null;
		}
		else{
				try {
					while(rs.next()){
						BookInfo bookInfo = new BookInfo();
					    bookInfo.setBookName(rs.getString("bookname"));
					    bookInfo.setISBN(rs.getString("ISBN"));
					    bookInfo.setTypeID(rs.getString("typeID"));
					    bookInfo.setTranslator(rs.getString("translator"));
					    bookInfo.setWriter(rs.getString("writer"));
					    bookInfo.setDate(rs.getString("data"));
					    bookInfo.setPublisher(rs.getString("publisher"));
					    bookInfo.setPrice(rs.getString("price"));
					    list.add(bookInfo);
					}
					return list;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}//end try catch
		}//end else
	}
	
	public static List<BookInfo> selectBookInfo_author(String author){
		List<BookInfo> list = new ArrayList<BookInfo>();
		String sql = "select * from tb_book where writer LIKE '%"+author+"%'";
		ResultSet rs = executeQuery(sql);
		if(rs == null){
			return null;
		}
		else{
				try {
					while(rs.next()){
						BookInfo bookInfo = new BookInfo();
					    bookInfo.setBookName(rs.getString("bookname"));
					    bookInfo.setISBN(rs.getString("ISBN"));
					    bookInfo.setTypeID(rs.getString("typeID"));
					    bookInfo.setTranslator(rs.getString("translator"));
					    bookInfo.setWriter(rs.getString("writer"));
					    bookInfo.setDate(rs.getString("data"));
					    bookInfo.setPublisher(rs.getString("publisher"));
					    bookInfo.setPrice(rs.getString("price"));
					    list.add(bookInfo);
					}
					return list;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}//end try catch
		}//end else
	}
	
	/*
	 * 查询所有的BookInfo信息
	 */
	public static List<BookInfo> selectBookInfo(){
		List<BookInfo> list = new ArrayList<BookInfo>();
		String sql = "select * from tb_book";
		ResultSet rs = executeQuery(sql);
		if(rs == null){
			return null;
		}
		else{
				try {
					while(rs.next()){
						BookInfo bookInfo = new BookInfo();
					    bookInfo.setBookName(rs.getString("bookname"));
					    bookInfo.setISBN(rs.getString("ISBN"));
					    bookInfo.setTypeID(rs.getString("typeID"));
					    bookInfo.setTranslator(rs.getString("translator"));
					    bookInfo.setWriter(rs.getString("writer"));
					    bookInfo.setDate(rs.getString("data"));
					    bookInfo.setPublisher(rs.getString("publisher"));
					    bookInfo.setPrice(rs.getString("price"));
					    list.add(bookInfo);
					}
					return list;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}//end try catch
		}//end else
	}//end selectBookInfo
}
