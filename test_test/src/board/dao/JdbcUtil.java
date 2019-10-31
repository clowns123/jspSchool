package board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {
	public static Connection connect() {
		Connection conn = null;
		String server = "jdbc:oracle:thin:@localhost:1521:ORCL";
		String did = "javaDB2";
		String dpw = "javaDB2";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(server, did, dpw);
		} catch (ClassNotFoundException e) {
			System.out.println("연결 드라이버 없음");
	    } catch (SQLException e) {
	    	System.out.print("연결실패 : ");
	        if(e.getMessage().indexOf("ORA-28009") > -1)
	        	System.out.println("허용되지 않는 접속 권한 없음");    
	        else if(e.getMessage().indexOf("ORA-01017") > -1)
	        	System.out.println("유저/패스워드 확인");    
	        else if(e.getMessage().indexOf("IO") > -1)
	        	System.out.println("IO - 연결확인!");
	        else
	        	System.out.println("기본 연결확인!");
	        
	    }
		return conn;
	}
	public static Connection connectP() {
		Connection conn = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/memberDB" );
			conn = ds.getConnection();			
			
		} catch (Exception ex) {
			System.out.println("오류 : " + ex);
		}
		return conn;
	}	
	public static  void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception ex) {
				System.out.println("오류 : " + ex);				
			}
		}
		close(conn, ps);
	} // close
	public static void close(Connection conn, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception ex) {
				System.out.println("오류 : " + ex);		
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception ex) {
				System.out.println("오류 : " + ex);				
			}
		}
	} // close
}
