package board.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import board.vo.BoardVO;
public class BoardDAO {
	private static BoardDAO dao = new BoardDAO();
	private BoardDAO(){}
	public static BoardDAO getInstance() {
		return dao;
	}
	
	public ArrayList<BoardVO> boardList() {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO board = null;
		try {
			conn = JdbcUtil.connectP();
			String sql = "	select level, articleNo, parentNo,\r\n" + 
					"		lpad('', 4*(level-1))||title title,\r\n" + 
					"		content, writeDate, id\r\n" + 
					"	from wboard\r\n" + 
					"	start with parentNo=0\r\n" + 
					"	connect by prior articleNo = parentNo\r\n" + 
					"	order siblings by articleNo desc";
			
			System.out.println("sql:"+sql);			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("rs:"+rs);				
				board = new BoardVO();
				board.setLevel(rs.getInt("level"));
				board.setArticleNo(rs.getInt("articleNo"));
				board.setParentNo(rs.getInt("parentNo"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriteDate(rs.getDate("writeDate"));
				board.setId(rs.getString("id"));				
				list.add(board);
			}
		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return list;
	}

	//2. 寃뚯궛�뙋 �벑濡앹슜 : 寃뚯떆�뙋 �벑濡앹떆 �쁽�옱 �벑濡앸맂 ID 理쒕�媛� �씫�뼱�삤湲�
	private int getNewArticleNO() {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		try {
			conn = JdbcUtil.connectP();
			String sql = "SELECT  max(articleNo) from wboard ";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			if (rs.next())
				return (rs.getInt(1) + 1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JdbcUtil.close(conn, pstmt, rs);
		}	
		System.out.println("번호 가져옴");
		return 0;
	}

	//2. 寃뚯떆�뙋 �벑濡앹슜 :寃뚯떆�뙋 �벑濡�
	public void boardInsert(BoardVO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtil.connectP();
			String sql = "insert into wboard values (?, ?, ?, ?, ?, sysdate, ?)";
			System.out.println(sql);
			int articleNo = getNewArticleNO();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  articleNo);
			pstmt.setInt(2,  board.getParentNo());
			pstmt.setString(3, board.getTitle());
			pstmt.setString(4,  board.getContent());
			pstmt.setString(5, board.getImageFileName());
			pstmt.setString(6, board.getId());
			pstmt.executeUpdate();
			System.out.println("pstmt 설정 끝");
		} catch (Exception ex) {
			System.out.println("�삤瑜� 諛쒖깮 : " + ex);
		} finally {
			JdbcUtil.close(conn, pstmt);
		}
		
	}	
	
	public BoardVO boardView(String num) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		BoardVO board = new BoardVO();
		int num1 = Integer.parseInt(num);
		try {
			conn = JdbcUtil.connectP();
			String sql = "SELECT  * from wboard where articleNo = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num1);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				board.setArticleNo(rs.getInt("articleNo"));
				board.setParentNo(rs.getInt("parentNo"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriteDate(rs.getDate("writeDate"));
				board.setId(rs.getString("id"));		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JdbcUtil.close(conn, pstmt, rs);
		}	
		return board;
		
	}
	
	public void boardUpdate(BoardVO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtil.connectP();
			String sql = "Update wboard set title = ?, content = ?, imagefilename = ?, writedate = sysdate where articleNo = ?";
			System.out.println(sql);
			System.out.println("title: " + board.getTitle());
			System.out.println("content: " + board.getContent());
			System.out.println("no: " + board.getArticleNo());
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2,  board.getContent());
			pstmt.setString(3, board.getImageFileName());
			pstmt.setInt(4, board.getArticleNo());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("�삤瑜� 諛쒖깮 : " + ex);
		} finally {
			JdbcUtil.close(conn, pstmt);
		}
	}
	
	public void boardDelete(String no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int articleNo = Integer.parseInt(no);
		try {
			conn = JdbcUtil.connectP();
			String sql = "delete from wboard\r\n" + 
					"	where articleNo in \r\n" + 
					"	(select articleNo from wboard\r\n" + 
					"	 start with articleNo = ?\r\n" + 
					"	 connect by prior articleNo = parentNo)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			pstmt.executeQuery();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	

}
