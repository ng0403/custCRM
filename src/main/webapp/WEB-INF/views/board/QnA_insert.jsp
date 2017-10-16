<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@include file="../include/header.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

 <link rel="stylesheet" href="${ctx}/resources/common/css/standard/common/sfa_common_list.css" type="text/css" /> 
 <script type="text/javascript" src="${ctx}/resources/common/js/standard/board/qna_list.js"></script> 
 
<link rel="stylesheet" type="text/css" href="${ctx}/resources/common/Semantic/semantic.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="  crossorigin="anonymous"></script>
<script src="${ctx}/resources/common/Semantic/semantic.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body> 

<div id="title">
		<div class="caption"> 
	 <h3 class="ui header" style="background: #fff;"> ■ 영업정보 > <a href="/boardInqr?BOARD_MNG_NO=${board_mng}" style="font-size: 14pt; text-decoration:none; color: blue;">Q&A</a> > 추가 </h3>
		</div>
</div>

<form role="form" method="post" enctype="multipart/form-data">
	 <input type='hidden' id ="BOARD_MNG_NO" name="BOARD_MNG_NO" value="${board_mng}">	
  
 	<div id="baseBtnDiv" class="bt_position_authuser"> <!-- 버튼 div  -->
		 <input type="button" id ="board_add_fbtn" class = "tiny ui blue button" value="저장" onclick="board_add_save();"/>
		 <input type="button" id="board_list_fbtn" class="tiny ui button" value="취소" onclick="go_list();"/> 
	</div>
  
  
<table class="ui sortable celled table" style="table-layout:fixed" >
<tr>
 <th style="width:165px">제목</th> 
 <td colspan="6"><input type="text" placeholder="제목"  id="TITLE" name="TITLE" value="${boardVO.QUESTION_TITLE}" style="width:100%; height:35px"/></td>
</tr>
<tr>
<th> 질문유형</th>
<td colspan="6">
  	 <select class="form-control" id="QUESTION_TITLE" name = "QUESTION_TITLE" style="width:100%; height:40px">
     <option value="인적서비스">인적서비스</option>
     <option value="점포서비스">점포서비스</option>
     <option value="이벤트">이벤트</option>
     <option value="제휴카드">제휴카드</option>
     <option value="쿠폰">쿠폰</option>
     <option value="제휴서비스">제휴서비스</option>
   </select>
</td>
 <tr>
<th>내 용</th>
<td colspan="6">
<textarea  rows="10" id="CONTENT" name="CONTENT" style="width:100%" >${boardVO.CONTENT}</textarea>
</td> 
</tr>
</table>
</form>



<%-- <div class="container" >  <!-- 전체 div-->

	<form role="form" method="post" enctype="multipart/form-data">
		<div> <!-- 제목 div-->
			<label id="txt" >제  목</label>
 		</div>  
		<div>  
	 <input type='hidden' id ="BOARD_MNG_NO" name="BOARD_MNG_NO" value="${board_mng}">	
	 <input type="text" class="inputTxt" id="TITLE" name="TITLE" placeholder="제목을 입력해 주세요."  />
	 <label id="txt" >질문 유형</label> 
 	 <select class="form-control" id="sel1" name = "QUESTION_TITLE">
     <option value="인적서비스">인적서비스</option>
     <option value="점포서비스">점포서비스</option>
     <option value="제휴카드">제휴카드</option>
     <option value="쿠폰">쿠폰</option>
     <option value="제휴서비스">제휴서비스</option>
   </select>
		
		</div>
	  
		<div> <!-- 내용 div -->
			<label id="txt" >내  용</label>
			<textarea class="form-control" rows="10" id="CONTENT" name="CONTENT" placeholder="내용을 입력해 주세요." ></textarea>
		</div> 
	  
	</form>

	<div id="btns"> <!-- 버튼 div  -->
		 <input type="button" id ="board_add_fbtn" class = "tiny ui orange button" value="저장" onclick="board_add_save();"/>
		 <input type="button" id="board_list_fbtn" class="tiny ui button" value="취소" onclick="go_list();"/> 
	</div>

</div> 
  --%>
 
 

</body>
</html>
<%-- <%@include file="../include/footer.jsp"%> --%>
