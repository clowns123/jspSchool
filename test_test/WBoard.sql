	--drop table WBOARD;
	/*
	create table wmember(
		id varchar2(10) primary key, 
		passwd varchar2(10),
		name varchar2(10),	
		mail varchar2(20)
	);
	*/
	//creation����
	??? WBOARD(
		articleNo NUMBER(10) primary key, --�Խ��Ǳ� ID
		parentNo number(20) default 0,    --�θ� �Խ��Ǳ� ID
		title VARCHAR2(500) NOT NULL,     --�Խñ� ����
		content VARCHAR2(4000),           --�� ����
		imageFileName VARCHAR2(50),       --÷�����ϸ�
		writeDate DATE default sysdate not null,  --�ۼ�����
		id varchar2(10),				  --�ۼ��� ID(WMEMBER�� �ִ� ID) 								
		Constraint fk_id FOREIGN KEY(id)  -- id�׸� WMEMBER�� ID�� �ܺ�Ű ����
		references wmember(id)
	);
	 
	--select articleNo, parentNo,title,content, imageFileName, id, writeDate 
	--from wboard where articleNo = 12
	
	select * from WMEMBER;
	select * from wboard; 
	
	--������ ��ȸ: �ڽ��� ���� �θ�ۿ� �ش��ϴ� �ͱ��� ���������� ��ȸ
	select level, articleNo, parentNo,
		lpad('', 4*(level-1))||title title,
		content, writeDate, id
	from wboard
	start with parentNo=0
	connect by prior articleNo = parentNo
	order siblings by articleNo desc;
	
	--�߰�
	??
	--����
	??
	
	--����
	--������ ���� : �ڽ��� �ۿ� �ش��ϴ� ��۱��� ����
	delete from wboard
	where articleNo in 
	(select articleNo from wboard
	 start with articleNo = 12
	 connect by prior articleNo = parentNo);
	
