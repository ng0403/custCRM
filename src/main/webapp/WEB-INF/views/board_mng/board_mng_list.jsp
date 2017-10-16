<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${ctx}/resources/common/js/jquery-1.11.1.js"></script>
<script src="${ctx}/resources/common/js/common.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/common/js/standard/boardmng/boardmng.js"></script>
<link rel="stylesheet" href="${ctx}/resources/common/css/common.css"
	type="text/css" />

<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/common/Semantic/semantic.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>
<script src="${ctx}/resources/common/Semantic/semantic.js"></script>

 <link rel="stylesheet" href="${ctx}/resources/common/css/common.css"
		type="text/css" />
<link rel="stylesheet" href="${ctx}/resources/common/css/standard/common/sfa_common_list.css" type="text/css" />

<script src="${ctx}/resources/common/js/standard/common/tablesort.js"></script>
<script>
$(function() {

   $('table').tablesort();

});  
</script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<c:set var="ctx" value="${pageContext.request.contextPath }" />
	

<div id="title">
	<div class="caption">
	  <h3 class="ui header" style="background: #fff;">■ 기준정보 > 게시판관리 </h3>	
	 </div> 
</div>

	 			<div style="height:323px">
<!--<form name="delAllForm" id="delAllForm" method="post" action="/board/board_remove"> -->
					<table class="ui sortable celled table">
						<thead>
							<tr style="text-align:center">
								<th ><input id="checkall" type="checkbox"
									onclick="checkAll();" /></th>
								<th>게시판관리번호</th>
								<th>게시판구분</th>
								<th>게시판이름</th>
								<th>생성일</th>
								<th>사용여부</th>
							</tr>
						</thead>
						<tbody id="board_list_tbody" class="tbody">
							<c:forEach items="${boardmnglist}" var="boardMngVO">
								<tr>
									<td scope="row" style=" text-align:center"><input type="checkbox" id="del_code"
										name="del_code" value="${boardMngVO.BOARD_MNG_NO}"></td>
									<td style=" text-align:center">${boardMngVO.BOARD_MNG_NO}</td>
									<td style=" text-align:center">${boardMngVO.BOARD_MNG_CD}</td>
									<td>
									<%-- <a href="/board_mng_detail?BOARD_MNG_NO=${boardMngVO.BOARD_MNG_NO}"
										style="color: black">${boardMngVO.BOARD_NM}</a> --%>
									<a href="" style="color:black" onclick="boardmngDetailClick('${boardMngVO.BOARD_MNG_NO}')">${boardMngVO.BOARD_NM}</a>	
										
								   </td>
									<td style=" text-align:center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
											value="${boardMngVO.CREATED}" /></td>
									<td style=" text-align:center"><c:if test="${boardMngVO.ACTIVE_FLG eq 'Y'}">활성화</c:if>
										<c:if test="${boardMngVO.ACTIVE_FLG eq 'N'}">비활성화</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				<!-- </form> -->

			</div>
			 <div class="bottom_div">
     	<div class="functionBtn_div" id="btn_1">
					<input type="button" id="board_add_fbtn"
						class="tiny ui blue button" value="추가" onclick="boardMngAddp();" />
					<input type="button" id="board_remove_fbtn"
						class="tiny ui blue button" value="삭제" onclick="deleteAction() " />
				</div>
				
				<div class="functionBtn_div" id="btn_2" style="display:none">
				<input type="button" id="board_add_save" class="tiny ui blue button" value="저장" onclick="boardmngInsert();"/>
				<input type="button" id="board_remove_fbtn"	class="tiny ui blue button" value="취소" onclick="cancelBtn() " />
				
				</div>
  
				<!-- 페이징 처리 -->
				<div id="pageSpace" class="ui right floated pagination menu">
					<input type="hidden" id="endPageNum" value="${page.endPageNum}" />
					<input type="hidden" id="ccPageNum" value="${ccPageNum}">
					<c:choose>
						<c:when test="${ccPageNum eq page.firstPageCount}">
							<a class="icon item"> <i class="left chevron icon"></i>
							</a>
						</c:when>
						<c:when test="${ccPageNum ne page.firstPageCount}">
							<a href="javascript:boardPaging(${page.prevPageNum})"
								class="icon item"> <i class="left chevron icon"></i>
							</a>
						</c:when>
					</c:choose>
					<c:forEach var="i" begin="${page.startPageNum }"
						end="${page.endPageNum}" step="1">
						<c:choose>
							<c:when test="${i eq ccPageNum }">
								<b> <a href="javascript:boardPaging('${i}');" id="pNum"
									class="item">${i}</a>
								</b>
							</c:when>
							<c:otherwise>
								<a href="javascript:boardPaging('${i}');" class="item">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${ccPageNum eq page.totalPageCount}">
							<a class="icon item"> <i class="right chevron icon"></i>
							</a>
						</c:when>
						<c:when test="${ccPageNum ne page.totalPageCount}">
							<a href="javascript:boardPaging(${page.nextPageNum})"
								class="icon item"> <i class="right chevron icon"></i>
							</a>
						</c:when>
					</c:choose>
				</div>

			</div> 
	 
</body>
</html>
