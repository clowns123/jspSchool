SELECT  * from wboard;
commit;

Update wboard set title = 'a1sd', content = 'asd', imagefilename = 'asd.jpg', writedate = sysdate where articleNo = 4;