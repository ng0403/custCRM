<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <c:set var="SessionID" value="${sessionScope.user_id}" />

<%-- <%@include file="../include/header.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
 <script type="text/javascript" src="${ctx}/resources/common/js/standard/board/qna_list.js"></script>  
<script type="text/javascript" src="${ctx}/resources/common/js/standard/board/reply.js"></script>

 <link rel="stylesheet" href="${ctx}/resources/common/css/standard/common/sfa_common_list.css" type="text/css" /> 
 
<link rel="stylesheet" type="text/css" href="${ctx}/resources/common/Semantic/semantic.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="  crossorigin="anonymous"></script>
<script src="${ctx}/resources/common/Semantic/semantic.js"></script>
 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 
<div id="title">
		<div class="caption">
		<h3 class="ui header" style="background: #fff;">■ 영업정보 > <a href="/boardInqr?BOARD_MNG_NO=${boardlist.BOARD_MNG_NO}" style="font-size: 14pt; text-decoration:none; color: blue;">Q&A</a> > 상세보기 </h3>
		
		</div>
</div>


 <div id="baseBtnDiv" class="bt_position_authuser"> <!-- 버튼 div  -->
		<input type="button" id="board_modify_fbtn" class = "tiny ui blue button" value="편집" onClick="board_modify();"/> 
		<input type="button" id="board_remove_fbtn" class="tiny ui blue button" value="삭제" onClick="board_detail_remove();"/>  
		<input type="button" class="tiny ui button" id="board_list_fbtn" value="취소" onClick="go_list();"/>
 </div> 

	 
<form role="form" name = "form_modify" method="post">
 <input type='hidden' id="BOARD_NO" name='BOARD_NO' value="${boardlist.BOARD_NO}"> 
 <input type='hidden' id="BOARD_MNG_NO" name="BOARD_MNG_NO" value= "${boardlist.BOARD_MNG_NO}">
</form>

<table class="ui sortable celled table" style="table-layout:fixed" >
<tr>
 <th style="width:165px">제목</th> 
 <td colspan="6"><input type="text" placeholder="제목"  id="TITLE" name="TITLE" value="${boardlist.QUESTION_TITLE}" readonly="readonly" style="width:100%; height:35px"/></td>
</tr>
<tr>
<th> 조회수</th>
<td>${boardlist.VIEW_CNT}</td>
<th>작성자</th>
<td>${boardlist.CREATED_BY}</td>
<th> 작성일</th>
<td colspan="2">${boardlist.CREATED}</td>
</tr>
<tr>
<th>내 용</th>
<td colspan="6">
<textarea  rows="10" id="boardcontent"  readonly="readonly" style="width:100%" >${boardlist.CONTENT}</textarea>
</td> 
</tr>
</table>
	
<table class="ui sortable celled table" style="table-layout:fixed"> 
<tr >
<th style="width:165px">답변 내용</th>
<td>
<textarea id = "reply_content" class="form-control" rows="2" id="content" style="width:100%" ></textarea>
</td>
<td style="width:100px; text-align:center">
 <input type="button" id="reply_add_fbtn" class = "tiny ui blue button" value="저장" onclick="reply_add();"/>  
</td>
</tr> 

</table>	

<table id = "reply_table" class="ui sortable celled table">
<tbody class="reply_list" id="reply_list_tbody">
</tbody>
</table> 
 

			<!-- 페이징 처리 -->
			<div id="pageSpace" class="ui right floated pagination menu">
				<input type="hidden" id="endPageNum" value="${page.endPageNum}"/>
				<input type="hidden" id="ccPageNum" value="${ccPageNum}">
				<c:choose>
					<c:when test="${ccPageNum eq page.firstPageCount}">
						<a class="icon item">
	        				<i class="left chevron icon"></i>
	        			</a>	
		    		</c:when>
					<c:when test="${ccPageNum ne page.firstPageCount}">
		        		<a href="javascript:replyPaging(${page.prevPageNum})" class="icon item">
		        			<i class="left chevron icon"></i>
		        		</a>
		    		</c:when>
				</c:choose>
				<c:forEach var="i" begin="${page.startPageNum }" end="${page.endPageNum}" step="1">
					<c:choose>
						<c:when test="${i eq ccPageNum }">
							<b>
								<a  href="javascript:replyPaging('${i}');" id="pNum" class="item">${i}</a>
							</b>
						</c:when>
						<c:otherwise>
							<a  href="javascript:replyPaging('${i}');" class="item" >${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${ccPageNum eq page.totalPageCount}">
							<a class="icon item">
		        				<i class="right chevron icon"></i>
		        			</a>	
		    		</c:when>
					<c:when test="${ccPageNum ne page.totalPageCount}">
		       			<a href="javascript:replyPaging(${page.nextPageNum})" class="icon item">
		       				<i class="right chevron icon"></i>
		       			</a>
		    		</c:when>
				</c:choose>
			</div>


<%-- <div class="container"> <!-- 전체 div-->

<div> <!-- 제목 div-->
<label id="txt" >제  목</label>
 <input type="text" class="inputTxt" id="title" value= "${boardlist.QUESTION_TITLE}"  readonly="readonly" />
</div> 

<div>
<label for="created_by">${boardlist.CREATED_BY}</label> 
<label for="created"><fmt:formatDate pattern="yyyy-MM-dd HH:mm"	value="${boardlist.CREATED}" /></label>
<label for="created">${boardlist.CREATED}</label>
 <label for="view_cnt">조회 : ${boardlist.VIEW_CNT}</label>
</div> 

<div> <!-- 내용 div -->
 <label id="txt" >내  용</label> 
 <textarea class="form-control" rows="10" id="content"  readonly="readonly" >${boardlist.CONTENT}</textarea>
</div> 
 
 	<div id="btns"> <!-- 버튼 div  -->
		<input type="button" id="board_modify_fbtn" class = "tiny ui orange button" value="편집" onClick="board_modify();"/> 
		<input type="button" id="board_remove_fbtn" class="tiny ui orange button" value="삭제" onClick="board_detail_remove();"/>  
		<input type="button" class="tiny ui button" id="board_list_fbtn" value="취소" onClick="go_list();"/>
	</div>
 	
	<!-- 댓글div -->
	<c:choose>
	<c:when test= "${sessionID eq 'admin'}"> 
	<div id="reply_div" class="timeline-body" style ="height:50px; margin-top:10px; width:90% "> 
 
		<div class="col-sm" style=" height:40px" style="float:left; width:70%">
			<textarea id = "reply_content" class="form-control" rows="2" id="content" style="width:100%" ></textarea>
 
		</div> 
		<!-- 댓글 등록 버튼 -->
		 <div id="detail_btn_div" class="reply_div col-md-4" style="float:right; margin-top:-40px; margin-right:-380px;" >
	     <input type="button" id="reply_add_fbtn" class = "tiny ui orange button " value="저장" onclick="reply_add();"/> 
		</div>
 
	</div>
	</c:when>
	<c:otherwise>
	</c:otherwise>
	</c:choose>
 
	<div class="col-md-12" style="margin-top:10px">
		<table id = "reply_table" class="table">
		<thead class="reply_list" id="reply_list_tbody">
		 
		</thead>
		</table> 
	</div> 

</div> --%>
 

</body>
</html>
<%-- <%@include file="../include/footer.jsp"%> --%>
