<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@include file="../include/header.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script type="text/javascript" src="${ctx}/resources/common/js/board/board_list.js"></script> 
<link rel="stylesheet" href="${ctx}/resources/common/css/board/boardCSS.css" type="text/css" />
  
<script src="https://code.jquery.com/jquery-3.1.1.min.js"  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="  crossorigin="anonymous"></script>


<title>Insert title here</title>
</head>
<body>
 
<div id="title">
<div style="height:10px"></div>
		<div class="caption">
		 ■ 영업커뮤니케이션 > <a href="/boardInqr?BOARD_MNG_NO=${boardmnglist.BOARD_MNG_NO}" style="text-decoration:none; color: blue;">${boardmnglist.BOARD_NM} </a> >  게시글 추가
		</div>
</div> 
<div style="height:10px;"></div>
<div class="coupon_detail">
<form role="form" method="post" enctype="multipart/form-data">
   <input type='hidden' id="BOARD_MNG_NO" name='BOARD_MNG_NO' value="${boardmnglist.BOARD_MNG_NO}"/>    
<table class="commonDetailTable">
<tr>
 <th>제목</th> 
 <td colspan="2"><input type="text" placeholder="제목"  id="TITLE" name="TITLE" style="width:850px"/></td> 
</tr>
<tr>
<th >파일</th>
	 <td>
	 <c:if test="${boardmnglist.FILE_ATTACH_FLG == 'N'}">
	 </c:if>
	 <c:if test="${boardmnglist.FILE_ATTACH_FLG == 'Y'}">
	 <input type="file" multiple="multiple" name="filedata" id="filedata">
	 </c:if> 
	 </td> 
</tr>
<tr>
<th>내 용</th>
<td>
<textarea  id="CONTENT" name="CONTENT"  rows="25"  cols="120" style="resize: none;" style="width:100%" ></textarea>
</td>  
 
</tr>
 </table>
 </form>   
</div>
	<div id="baseBtnDiv" class="listFootDiv"> <!-- 버튼 div  -->
		 <input type="button" id ="board_add_fbtn" class = "tr_btn" value="저장" onclick="board_add_save();"/>
		 <input type="button" id="board_list_fbtn" class="func_btn" value="취소" onclick="goboardList();"/> 
	</div>

<%-- <div class="container" >  <!-- 전체 div-->

	<form role="form" method="post" enctype="multipart/form-data">
	     <input type='hidden' id="BOARD_MNG_NO" name='BOARD_MNG_NO' value="${board_mng}"/>  
	
		<div> <!-- 제목 div-->
			<label id="txt" >제  목</label>
		 	<input type="text" class="form-control" id="TITLE" name="TITLE" placeholder="제목을 입력해 주세요."  />
		</div> 
	  
		<div> <!-- 내용 div -->
			<label id="txt" >내  용</label>
			<textarea class="form-control" rows="10" id="CONTENT" name="CONTENT" placeholder="내용을 입력해 주세요." ></textarea>
		</div> 
	 
		<div id="file">
			<input type="file" multiple="multiple" name="filedata" id="filedata"> 
		</div> 
	</form>

	<div id="btns"> <!-- 버튼 div  -->
		 <input type="button" id ="board_add_fbtn" class = "tiny ui orange button" value="저장" onclick="board_add_save();"/>
		 <input type="button" id="board_list_fbtn" class="tiny ui button" value="취소" onclick="goboardList();"/> 
	</div>

</div>  --%>
 
 
 

</body>
</html>
<%-- <%@include file="../include/footer.jsp"%> --%>
