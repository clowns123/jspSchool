	--drop table WBOARD;
	/*
	create table wmember(
		id varchar2(10) primary key, 
		passwd varchar2(10),
		name varchar2(10),	
		mail varchar2(20)
	);
	*/
	//creation문장
	??? WBOARD(
		articleNo NUMBER(10) primary key, --게시판글 ID
		parentNo number(20) default 0,    --부모 게시판글 ID
		title VARCHAR2(500) NOT NULL,     --게시글 제목
		content VARCHAR2(4000),           --글 내용
		imageFileName VARCHAR2(50),       --첨부파일명
		writeDate DATE default sysdate not null,  --작성일자
		id varchar2(10),				  --작성자 ID(WMEMBER에 있는 ID) 								
		Constraint fk_id FOREIGN KEY(id)  -- id항목에 WMEMBER에 ID에 외부키 설정
		references wmember(id)
	);
	 
	--select articleNo, parentNo,title,content, imageFileName, id, writeDate 
	--from wboard where articleNo = 12
	
	select * from WMEMBER;
	select * from wboard; 
	
	--계층형 조회: 자신의 글의 부모글에 해당하는 것까지 계층형으로 조회
	select level, articleNo, parentNo,
		lpad('', 4*(level-1))||title title,
		content, writeDate, id
	from wboard
	start with parentNo=0
	connect by prior articleNo = parentNo
	order siblings by articleNo desc;
	
	--추가
	??
	--수정
	??
	
	--삭제
	--계층형 삭제 : 자신의 글에 해당하는 댓글까지 삭제
	delete from wboard
	where articleNo in 
	(select articleNo from wboard
	 start with articleNo = 12
	 connect by prior articleNo = parentNo);
	
