<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
 <c:set var="SessionID" value="${session}" />
 <input type="hidden" id="session" value="${session}"/>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 

 <script type="text/javascript" src="${ctx}/resources/common/js/board/board_list.js"></script> 
<script type="text/javascript" src="${ctx}/resources/common/js/board/reply.js"></script>  
<script src="http://malsup.github.com/jquery.form.js"></script>

 

<title>Insert title here</title>
</head>
<body>
 
 
<form role="form" name = "form_modify" method="post">
 <input type='hidden' id="BOARD_NO" name='BOARD_NO' value="${boardlist.BOARD_NO}"/> 

 <input type='hidden' id="REPLY_FLG" name='REPLY_FLG' value="${boardlist.REPLY_FLG}"/>
</form>
   
<form role="form1">
 <input type='hidden' id="BOARD_NO" name='BOARD_NO' value="${boardlist.BOARD_NO}"/> 
 <input type='hidden' id="BOARD_MNG_NO" name="BOARD_MNG_NO" value="${boardlist.BOARD_MNG_NO}"/>
</form> 
 
<div id="title">
		<div class="caption">
	    <c:if test="${boardlist.BOARD_MNG_NO =='BMG001'}">
		<h3 class="ui header" style="background: #fff;"> ■ 영업정보 > <a href="/boardInqr?BOARD_MNG_NO=${boardlist.BOARD_MNG_NO}" style="font-size: 14pt; text-decoration:none; color: blue;">공지사항</a> >  게시글 상세 </h3>
		</c:if>
		<c:if test="${boardlist.BOARD_MNG_NO == 'BMG002'}">
	    <h3 class="ui header" style="background: #fff;"> ■ 영업정보 > <a href="/boardInqr?BOARD_MNG_NO=${boardlist.BOARD_MNG_NO}" style="font-size: 14pt; text-decoration:none; color: blue;">일반 게시판</a> >  게시글 상세 </h3>
		</c:if>		</div>
</div>


 <div class="commonDetail">
<table class="commonDetailTable" id="coupon_form_tbl" >
<tr>
 <th style="width:165px">제목</th> 
 <td colspan="5"><input type="text" readonly="readonly" placeholder="제목"  id="TITLE" name="TITLE" value="${boardlist.TITLE}" /></td>
</tr>
<tr>
<th> 조회수</th>
<td>${boardlist.VIEW_CNT}</td>
<th>작성자</th>
<td>${boardlist.CREATED_BY}</td>
<th>작성일</th>
<td>${boardlist.CREATED}</td>
</tr>
<tr>
<th>파일</th>
<td colspan="5">
 <c:choose>
 	<c:when  test="${boardmnglist.FILE_ATTACH_FLG == 'N'}">
 	   
    </c:when>
    <c:when test="${boardmnglist.FILE_ATTACH_FLG == 'Y'}">
    		<c:if test="${boardlist.FILE_NM == null}">
   		</c:if>
   		<c:if test="${boardlist.FILE_NM != null}">
   		<a href="/file_down?FILE_CD=${boardlist.FILE_CD}"><i class="file icon"></i>${boardlist.FILE_NM} 
   		</c:if> 
   	</c:when>
</c:choose> 	

 </td>

</tr>
<tr>
<th>내 용</th>
<td colspan="5">
<textarea  rows="15" id="boardcontent" readonly="readonly" style="width:100%" >${boardlist.CONTENT}</textarea>
</td> 
</tr>
</table>

  <div id="board_detail_div" class="bt_position_authuser"> <!-- 버튼 div  -->
		<input type="button" class = "func_btn" value="편집" onClick="board_modify();"/> 
		<input type="button" class="tr_btn" value="삭제" onClick="board_detail_remove();"/>  
		<input type="button" class="func_btn" value="취소" onClick="goboardList();"/>
 </div>  

<c:if test="${boardmnglist.REPLY_FLG =='N'}">
</c:if>
<c:if test="${boardmnglist.REPLY_FLG == 'Y'}">	
<div id = "reply_div">	
<!-- <table class="commonDetailTable"> 
<tr>
<th>댓글 내용</th>
<td>
<textarea id = "reply_content" class="form-control" rows="3"  cols="120" style="resize: none;" id="content" style="width:100%" ></textarea>
</td>
<td>
 <input type="button" id="reply_add_fbtn" class = "tr_btn" value="저장" onclick="reply_add();"/>  
</td>
</tr>  
</table> -->	

<table id = "reply_table" class="commonDetailTable">
<tbody class="reply_list" id="reply_list_tbody">
</tbody>
</table> 
</div>
</c:if>
</div>	
<%-- <div class="container"> <!-- 전체 div-->

	<div> <!-- 제목 div-->
		<label id="txt" >제  목</label>
		<input type="text" class="inputTxt" name= "TITLE" id="TITLE" value= "${boardlist.TITLE}"  readonly="readonly" />
		<input type="hidden" name="board_no" id = "board_no" value="${boardlist.BOARD_NO}">
	</div> 


	<div id="detail_con"> <!-- 내용 div -->
		 <label id="txt" >내  용</label>  
		 
  	<div id="created"> 
   		<label for="view_cnt">조회 : ${boardlist.VIEW_CNT}</label> 
   		<c:if test="${boardlist.FILE_NM == null}">
   		</c:if>
   		<c:if test="${boardlist.FILE_NM != null}">
  		<label> <a href="/file_down?FILE_CD=${boardlist.FILE_CD}"><i class="file icon"></i>${boardlist.FILE_NM}</a></label>	 
 		</c:if>
 		<br/> 
 	</div> 
		 <textarea  rows="10" id="boardcontent"  readonly="readonly" >${boardlist.CONTENT}</textarea>
	</div> 
 	 
	<div id="detail_btns"> <!-- 버튼 div  -->
		<input type="button" id="board_modify_fbtn" class = "tiny ui orange button" value="편집" onClick="board_modify();"/> 
		<input type="button" id="board_remove_fbtn" class="tiny ui orange button" value="삭제" onClick="board_detail_remove();"/>  
		<input type="button" class="tiny ui button" id="board_list_fbtn" value="취소" onClick="goboardList();"/>
	</div> 

	<!-- 댓글div -->
	<div id="reply_div" class="timeline-body" style ="height:50px; margin-top:10px; width:90% "> 
 
		<div class="col-sm" style=" height:40px" style="float:left; width:70%">
			<textarea id = "reply_content" class="form-control" rows="2" id="content" style="width:100%" ></textarea>
 
		</div> 
		<!-- 댓글 등록 버튼 -->
		 <div id="detail_btn_div" class="reply_div col-md-4" style="float:right; margin-top:-40px; margin-right:-380px;" >
	     <input type="button" id="reply_add_fbtn" class = "tiny ui orange button " value="저장" onclick="reply_add();"/> 
		</div>
 
	</div>
 
	<div class="col-md-12" style="margin-top:10px">
		<table id = "reply_table" class="table">
		<thead class="reply_list" id="reply_list_tbody">
		 
		</thead>
		</table> 
	</div> 

</div>	 --%>
    
			<!-- 페이징 처리 -->
			<div class="pagingDiv">
		 
			</div>
		
		<div class="right">
 		</div> 
	</div>  
  
</body>
</html>
 