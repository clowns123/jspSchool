package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardService;

public class BoardDeleteController implements Controller{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");	
		String articleNo = request.getParameter("articleNo");
		
		System.out.println("articleNo : " + articleNo);
		
		BoardService service = BoardService.getInstance();
		service.boardDelete(articleNo);

		PrintWriter pw = response.getWriter();
		pw.print("<script>" 
		         +"  alert('삭제 완료');" 
				 +" location.href='"+request.getContextPath()+"/boardList.do';" //遺꾧린�븷 �봽濡쒓렇�옩 吏��젙
		         +"</script>");
		
	}

}
