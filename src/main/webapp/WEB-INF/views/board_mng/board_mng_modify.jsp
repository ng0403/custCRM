<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@include file="../include/header.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${ctx}/resources/common/js/jquery-1.11.1.js"></script> 
<link rel="stylesheet" href="${ctx}/resources/common/css/common.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> 
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<form role="form" name = "form_modify" method="post">
 <input type='hidden' id="BOARD_NO" name='BOARD_NO' value="${board_mng_list.BOARD_MNG_NO}"> 
</form>
 
 <div class="detailBtn">

<div class="container" style="width:90%">

	<div>
	<table class="table">
	<tr>
	<td>게시판이름</td> <td>${board_mng_list.BOARD_NM}</td> <td>게시판분류</td> <td></td>
	</tr>
	<tr>
	<td>관리자</td> <td></td> <td>게시판코드11</td> <td>${board_mng_list.BOARD_MNG_CD} </td>
	</tr>
	<tr>
	<td>댓글여부</td><td><label class="radio-inline"><input type="radio" name="reply_flg">Y</label> <label class="radio-inline"><input type="radio" name="reply_flg">N</label></td><td>게시판활성여부</td><td><label class="radio-inline"><input type="radio" name="active_flg">Y</label> <label class="radio-inline"><input type="radio" name="active_flg">N</label></td>
	</tr>
	<tr>
	<td>파일업로드</td><td><label class="radio-inline"><input type="radio" name="file_attach_flg">Y</label> <label class="radio-inline"><input type="radio" name="file_attach_flg">N</label></td><td>공지활성화</td><td><label class="radio-inline"><input type="radio" name="notice_flg">Y</label> <label class="radio-inline"><input type="radio" name="notice_flg">N</label></td>
	</tr>
	</table> 
	</div>
	
	<div>
	<input type="button" id="board_mng_modify_fbtn" class = "btn btn-default" value="편집"/> <input type="button" class="btn btn-default" id="board_list_fbtn" value="목록"/>
	</div>

</div>


<script> 

$("#board_list_fbtn").on("click", function(){  
    	location.href = "/board_mng/boardmngInqr";
 	})
 	
 $("#board_mng_modify_fbtn").on("click", function() {
	
	 location.href = "/board_mng/board_mng_modify";
 })	 
 	 
 
$(document).ready(function(){ 
	  
	 var BOARD_NO = $("#BOARD_NO").val();
	 var liststr = "";
	 var liststr1 ="";
	 var liststr2 = ""; 
     var formObj = $("form[role='form']");
	 console.log(formObj);
	
 $("#board_modify_fbtn").on("click", function(){
	 	formObj.attr("action", "/board/board_modify");
		formObj.attr("method", "get");		
		formObj.submit();
	 /* $("form[name='form_modify']").attr("action", "${ctx}/board/board_read?BOARD_NO=?").submit();  */
	 
 })
 
 $("#board_remove_fbtn").on("click", function(){
	 formObj.attr("action", "/board/board_remove");
	 formObj.attr("method", "post");
	 formObj.submit();
 })
   

})
 
 
</script>

 

</body>
</html>
<%-- <%@include file="../include/footer.jsp"%> --%>
