package board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {
	public static Connection connect() {
		Connection conn = null;
		String server = "jdbc:oracle:thin:@localhost:1521:orcl";
		String did = "javaDB2";
		String dpw = "javaDB2";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(server, did, dpw);
		} catch (Exception ex) {
			System.out.println("오류 : " + ex);
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
