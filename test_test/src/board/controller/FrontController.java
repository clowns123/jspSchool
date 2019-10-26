package board.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FrontController")
public class FrontController extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	String charset = null;
	HashMap<String, Controller> list = null;       
	public void init(ServletConfig config) throws ServletException {
		charset = config.getInitParameter("charset");
		list = new HashMap<String, Controller>();
		list.put("/boardList.do", new BoardListController());
		list.put("/boardInsert.do", new BoardInsertController());
		list.put("/boardView.do", new BoardViewConctrller());
		list.put("/boardUpdate.do", new BoardUpdateController());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	public void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(charset);
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = url.substring(contextPath.length());
		System.out.println("path:"+path);		
		Controller subController = list.get(path);
		
		subController.execute(request, response);
	}

}
