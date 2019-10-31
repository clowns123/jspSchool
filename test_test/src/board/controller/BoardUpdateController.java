package board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import board.service.BoardService;
import board.vo.BoardVO;

public class BoardUpdateController implements Controller{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");		
		
		Map<String, String> boardMap = HttpUtil.upload(request, response);
		String title = boardMap.get("title");
		String content = boardMap.get("content");
		String imageFileName = boardMap.get("imageFileName");
		int articleNo = Integer.parseInt(boardMap.get("articleNo"));
		
		
		
		BoardVO board = new BoardVO();
		board.setParentNo(0);
		board.setId("hong");
		board.setTitle(title);
		board.setContent(content);
		board.setImageFileName(imageFileName);
		board.setArticleNo(articleNo);
		

		BoardService service = BoardService.getInstance();
		service.boardUpdate(board);

		if(imageFileName!=null && imageFileName.length()!=0) {
		    File srcFile = new 	File(HttpUtil.ARTICLE_IMAGE_REPO +"\\"+"temp"+"\\"+imageFileName);
			File destDir = new File(HttpUtil.ARTICLE_IMAGE_REPO +"\\"+articleNo);
			destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
			srcFile.delete();
		}
		PrintWriter pw = response.getWriter();
		pw.print("<script>" 
		         +"  alert('업뎃 완료');" 
				 +" location.href='"+request.getContextPath()+"/boardList.do';" //遺꾧린�븷 �봽濡쒓렇�옩 吏��젙
		         +"</script>");
		
	}

}
