<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- %
  request.setCharacterEncoding("UTF-8");
%--> 
<!DOCTYPE html>
<html>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  /> 
<head>
<title>글쓰기창</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
   function readURL(input) {
      if (input.files && input.files[0]) {
	      var reader = new FileReader();
	      reader.onload = function (e) {
	        $('#preview').attr('src', e.target.result);
          }
         reader.readAsDataURL(input.files[0]);
      }
  }  
   

  function backToList(obj){
    obj.action="${contextPath}/boardList.do";
    obj.submit();
  }

</script>
 <title>업데이트 창</title>
</head>
<body>


<h1 style="text-align:center">새글 쓰기</h1>
  <form name="articleForm" method="post"   action="${contextPath}/boardUpdate.do"   enctype="multipart/form-data"> 
     <table border="1" align="center">
     <tr>
	   <td align="right">글제목: </td>
	   <td colspan="2"><input type="text" size="67"  maxlength="500" name="title" value="${board.title}" /></td>
	 </tr>
	 <tr>
		<td align="right" valign="top"><br>글내용: </td>
		<td colspan=2><textarea name="content" rows="10" cols="65" maxlength="4000"> ${board.content} </textarea> </td>
     </tr>
     <tr>
        <td align="right">이미지파일 첨부:  </td>
	     <div id="change"><td> <input type="file" name="imageFileName"  onchange="readURL(this);" /></td></div>
         <td><img  id="preview" src="#"   width=200 height=200/></td>
         
	 </tr>
	 <tr>
	    <td align="right"> </td>
	    <td colspan="2">
	       <input type="submit" value="수정하기" />
	       <input type="hidden" value="${board.articleNo}" name = "articleNo">
	       <input type=button value="목록보기"onClick="backToList(this.form)" />
	      <a href="${contextPath}/boardDelete.do?articleNo=${board.articleNo}"><input type="button" value="지우기"/></a>
	    </td>
     </tr>
    </table>
  </form>
  
  
  
</body>
</html>
