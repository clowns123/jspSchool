package board.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;

public class BoardVO {

	private int level;
	private int articleNo;
	private int parentNo;
	private String title;
	private String content;
	private String imageFileName;
	private String id;
	private Date writeDate;

	public BoardVO() {
		
	}


	public BoardVO(int level, int articleNo, int parentNo, String title, String content, String imageFileName,
			String id) {
		super();
		this.level = level;
		this.articleNo = articleNo;
		this.parentNo = parentNo;
		this.title = title;
		this.content = content;
		this.imageFileName = imageFileName;
		this.id = id;
	}

	//getters/setters 	


	
	//setImageFileName留� �떎�떆�젙�쓽
	public void setImageFileName(String imageFileName) {
		try {
			//�뙆�씪�씠由꾩뿉 �듅�닔臾몄옄媛� �엳�쓣 寃쎌슦 �씤肄붾뵫�빀�땲�떎.
			if(imageFileName!=null && imageFileName.length()!=0) {
				this.imageFileName = URLEncoder.encode(imageFileName, "UTF-8");  //�뙆�씪�씠由꾩뿉 �듅�닔臾몄옄媛� �엳�쓣 寃쎌슦 �씤肄붾뵫�빀�땲�떎.
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public int getArticleNo() {
		return articleNo;
	}


	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}


	public int getParentNo() {
		return parentNo;
	}


	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Date getWriteDate() {
		return writeDate;
	}


	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}


	public String getImageFileName() {
		return imageFileName;
	}
	
	
	
}
