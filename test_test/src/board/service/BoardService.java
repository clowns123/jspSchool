package board.service;

import java.util.ArrayList;
import java.util.List;

import board.dao.BoardDAO;
import board.vo.BoardVO;
public class BoardService {
	private static BoardService service = new BoardService();
	public BoardDAO dao = BoardDAO.getInstance();
	private BoardService(){}
	public static BoardService getInstance() {
		return service;
	}
	//1. �쟾泥댁“�쉶 
	public ArrayList<BoardVO> boardList() {
		ArrayList<BoardVO> list = dao.boardList();
		return list;
	}
	//2. 寃뚯떆�뙋 �벑濡�
	public void boardInsert(BoardVO board) {
		dao.boardInsert(board);

		System.out.println("리턴");
	}
	
	public BoardVO boardView(String num) {
		BoardVO board = dao.boardView(num);
		
		return board;
		
	}
	
	public void boardUpdate(BoardVO board) {
		dao.boardUpdate(board);
	}
	public void boardDelete(String articleNo) {
		dao.boardDelete(articleNo);
	}

}
