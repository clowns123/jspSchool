package board.controller;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import board.service.BoardService;
import board.vo.BoardVO;
public class BoardInsertController implements Controller {
	//private static String ARTICLE_IMAGE_REPO = "d:\\JWebFP\\article_image";	
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String nextPage = "";
		int articleNo = 0; 
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");		
		
		Map<String, String> boardMap = HttpUtil.upload(request, response);
		String title = boardMap.get("title");
		String content = boardMap.get("content");
		String imageFileName = boardMap.get("imageFileName");
	
		
		BoardVO board = new BoardVO();
		board.setParentNo(0);
		board.setId("hong");
		board.setTitle(title);
		board.setContent(content);
		board.setImageFileName(imageFileName);

		BoardService service = BoardService.getInstance();
		service.boardInsert(board);


		if(imageFileName!=null && imageFileName.length()!=0) {
		    File srcFile = new 	File(HttpUtil.ARTICLE_IMAGE_REPO +"\\"+"temp"+"\\"+imageFileName);
			File destDir = new File(HttpUtil.ARTICLE_IMAGE_REPO +"\\"+articleNo);
			destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
			srcFile.delete();
		}
		
		
		PrintWriter pw = response.getWriter();
		pw.print("<script>" 
		         +"  alert('글 작성 완료');" 
				 +" location.href='"+request.getContextPath()+"/boardList.do';" //遺꾧린�븷 �봽濡쒓렇�옩 吏��젙
		         +"</script>");
		
		//nextPage = "/boardList.do";				
		// Output View �럹�씠吏�濡� �씠�룞		
		//HttpUtil.forward(request, response, nextPage);
	}
	
}
