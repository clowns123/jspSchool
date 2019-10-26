package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardService;
import board.vo.BoardVO;

public class BoardViewConctrller implements Controller{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardService service = BoardService.getInstance();
		String num = request.getParameter("articleNo");
		System.out.println(num);
		BoardVO board = service.boardView(num);
		

		System.out.println("Àß³ª¿È");
		request.setAttribute("board", board);
		System.out.println(board.getImageFileName());
		HttpUtil.forward(request, response, "/boardView.jsp");
		
		
	}
}
