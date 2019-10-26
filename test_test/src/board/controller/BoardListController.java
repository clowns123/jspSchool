package board.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import board.service.BoardService;
import board.vo.BoardVO;

public class BoardListController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardService service = BoardService.getInstance();
		Map<String, String> boardMap = HttpUtil.upload(request, response);

		ArrayList<BoardVO> list = service.boardList();
		System.out.println("잘나옴");
		request.setAttribute("list", list);
		HttpUtil.forward(request, response, "/boardList.jsp");
		
	}
}
