package smu.shuttle.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	public static final String URL = "jdbc:mysql://localhost:3306/shuttle??useUnicode=true&characterEncoding=utf8"; 
	public static final String ID = "seo"; 
	public static final String PASS = "0401"; 
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Mysql 드라이버 로딩 완료..");	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(URL,ID,PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void close(ResultSet rs){
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public static void close(Connection con){
		if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public static void close(Statement st){
		if(st!=null)
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
