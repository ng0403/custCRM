<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <script type="text/javascript"src="${ctx}/resources/common/js/boardmng/boardmng.js"></script>
  <script type="text/javascript" src="${ctx}/resources/common/js/boardmng/boardmng_detail.js"></script>  
 
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

	 			<div >
<!--<form name="delAllForm" id="delAllForm" method="post" action="/board/board_remove"> -->
					<table class="commonTable">
						<thead>
							<tr style="text-align:center">
								<th ><input id="checkall" type="checkbox"
									onclick="checkAll();" /></th>
								<th>게시판관리번호</th>
								<th>게시판구분</th>
								<th>게시판이름</th>
								<th>수정일</th>
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
									<td style=" text-align:center">${boardMngVO.UPDATED}</td>
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
						class="func_btn" value="추가" onclick="boardMngAddp();" />
					<input type="button" id="board_remove_fbtn"
						class="tr_btn" value="삭제" onclick="deleteAction() " />
				</div>
				
				<div class="functionBtn_div" id="btn_2" style="display:none">
				<input type="button" id="board_add_save" class="tiny ui blue button" value="저장" onclick="boardmngInsert();"/>
				<input type="button" id="board_remove_fbtn"	class="tiny ui blue button" value="취소" onclick="cancelBtn() " />
				</div> 
				
  
				<!-- 페이징 처리 -->
		<div class="pagingDiv">
			<input type="hidden" id="endPageNum" value="${page.endPageNum}"/>
			<input type="hidden" id="startPageNum" value="${page.startPageNum}"/>
			<input type="hidden" id="PageNum" value="${PageNum}"/>
			<c:choose>
				<c:when test="${page.endPageNum == 0 || page.endPageNum == 1}">
					<a style="color: black; text-decoration: none;"> ◀ </a><input type="text" id="pageInput" value="${page.startPageNum}" readonly="readonly"/>  
					<a style="color: black; text-decoration: none;"> / 1</a>
					<a style="color: black; text-decoration: none;"> ▶ </a>
				</c:when>
				<c:when test="${PageNum == page.startPageNum}">
					 ◀ <input type="text" id="pageInput" value="${page.startPageNum}"  onkeypress="leadPageNumInputEnter(event);"/>  
					<a style="cursor: pointer;" onclick="boardMngPaging('${page.endPageNum}');" id="pNum" > / ${page.endPageNum}</a>
					<a style="cursor: pointer;" onclick="boardMngPaging('${PageNum+1}');" id="pNum"> ▶ </a>
				</c:when>
				<c:when test="${PageNum == page.endPageNum}">
					<a style="cursor: pointer;" onclick="boardMngPaging('${PageNum-1}');" id="pNum"> ◀ </a>
					<input type="text" id="pageInput"  value="${page.endPageNum}" onkeypress="leadPageNumInputEnter(event);"/> 
					<a style="cursor: pointer;" onclick="boardMngPaging('${page.endPageNum}');" id="pNum"> / ${page.endPageNum}</a>
					<a style="color: black; text-decoration: none;"> ▶ </a>
				</c:when>
				<c:otherwise>
					<a style="cursor: pointer;" onclick="boardMngPaging('${PageNum-1}');" id="pNum" > ◀ </a>
					<input type="text" id="pageInput"  value="${PageNum}" onkeypress="leadPageNumInputEnter(event);"/>  
					<a style="cursor: pointer;" onclick="boardMngPaging('${page.endPageNum}');" id="pNum"> / ${page.endPageNum}</a>
					<a style="cursor: pointer;" onclick="boardMngPaging('${PageNum+1}');" id="pNum"> ▶ </a>
				</c:otherwise>
				</c:choose>
			</div>

			</div> 
	 
</body>
</html>
