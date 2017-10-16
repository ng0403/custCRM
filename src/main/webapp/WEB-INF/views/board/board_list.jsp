<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
 <c:set var="SessionID" value="${sessionScope.user_id}" />
    
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${ctx}/resources/common/js/jquery-1.11.1.js"></script>
<script src="${ctx}/resources/common/js/standard/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/common/js/standard/board/board_list.js"></script> 

<link rel="stylesheet" type="text/css" href="${ctx}/resources/common/Semantic/semantic.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="  crossorigin="anonymous"></script>
<script src="${ctx}/resources/common/Semantic/semantic.js"></script>

<link rel="stylesheet" href="${ctx}/resources/common/css/standard/common/sfa_common_list.css" type="text/css" />
 
<link rel="stylesheet" type="text/css" href="${ctx}/resources/common/Semantic/semantic.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="  crossorigin="anonymous"></script>
 
 
<title>Insert title here</title>

</head>
<body> 
   
  <div id="title">
		<div class="caption">
		<h3 class="ui header" style="background: #fff;"> ■ 영업정보 > 게시판 </h3>
		 
		</div>
</div>
 
   <!-- Q&A 리스트, 조회화면 -->
   <div class="search_div" id="search_div">
       <div class="ui left icon input">
			<input type="text" placeholder="제목"  id="keyword" name="keyword"  onkeydown="boardSearchEnter(event);">
			<i class="list icon"></i>
		</div>	
              <input type="button" onclick="boardPaging(1);" value="조회" id="board_inqr_fbtn" class="tiny ui blue button" value="검색"">
     </div>

           <form name="delAllForm" id ="delAllForm" method="post" action="/board_remove">  
           <input type='hidden' id="BOARD_MNG_NO" name='BOARD_MNG_NO' value="${BOARD_MNG_NO}"/> 
             <div id="tableline">
               <table  class="ui sortable celled table" style="table-layout:fixed;" >
                  <thead>
                     <tr style="text-align:center">
                        <th style="width:5%"><input id="checkall" type="checkbox" onclick="checkAll();"/></th>
                        <th>번호</th>
                        <th style="width:50%">제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>조회수</th> 
                     </tr>
                     
                   </thead>  
                             
                  <tbody class="board_list" id="board_list_tbody">
                     <c:forEach items="${boardlist}" var="boardVO">  
                        <tr class="open_list">
                           <td scope="row" style='width:10%; text-align:center'><input type="checkbox" id="del_code" name="del_code" value="${boardVO.BOARD_NO}"></td>
                           <td style='width:10%;'>${boardVO.BOARD_NO}</td> 
                           <td style='width:40%;'>
                           <c:if test="${boardVO.FILE_CD == null}">
                           <a href="#" onclick="boardDetail('${boardVO.BOARD_NO}');"    id="${boardVO.BOARD_NO}">${boardVO.TITLE}</a>
                           </c:if>
                           <c:if test="${boardVO.FILE_CD != null}">
                           <a href="#" onclick="boardDetail('${boardVO.BOARD_NO}');"  id="${boardVO.BOARD_NO}">${boardVO.TITLE}</a>    <i class="file icon"></i>
                           </c:if>
                           </td>                           
                           <td style='width:10%;'>${boardVO.CREATED_BY} </td>
                           <td style='width:20%;'><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.CREATED}" /></td>
                           <td style='width:10%;'>${boardVO.VIEW_CNT}</td>   
                        </tr> 
                     </c:forEach>
                  </tbody>
               </table>
            </div>
       </form>
       
  <div class="bottom_div">
       <div class="functionBtn_div">
         <input type="button" id = "board_add_fbtn"  class = "tiny ui button" value="추가" onclick="board_add();"/> 
           <input type="button" id ="board_remove_fbtn" class="tiny ui blue button" value="삭제"  onclick="deleteAction() "/>
 </div> 
    
   <!-- 페이징 처리 -->
		<div class="pagingDiv">
			<input type="hidden" id="endPageNum" value="${page.endPageNum}"/>
			<input type="hidden" id="startPageNum" value="${page.startPageNum}"/>
			<input type="hidden" id="pageNum" value="${pageNum}"/>
			<c:choose>
				<c:when test="${page.endPageNum == 0 || page.endPageNum == 1}">
					<a style="color: black; text-decoration: none;"> ◀ </a><input type="text" id="pageInput" value="${page.startPageNum}" readonly="readonly"/>  
					<a style="color: black; text-decoration: none;"> / 1</a>
					<a style="color: black; text-decoration: none;"> ▶ </a>
				</c:when>
				<c:when test="${pageNum == page.startPageNum}">
					 ◀ <input type="text" id="pageInput" value="${page.startPageNum}"  onkeypress="leadPageNumInputEnter(event);"/>  
					<a style="cursor: pointer;" onclick="searchKeyword('${page.endPageNum}');" id="pNum" > / ${page.endPageNum}</a>
					<a style="cursor: pointer;" onclick="searchKeyword('${pageNum+1}');" id="pNum"> ▶ </a>
				</c:when>
				<c:when test="${pageNum == page.endPageNum}">
					<a style="cursor: pointer;" onclick="searchKeyword('${pageNum-1}');" id="pNum"> ◀ </a>
					<input type="text" id="pageInput"  value="${page.endPageNum}" onkeypress="leadPageNumInputEnter(event);"/> 
					<a style="cursor: pointer;" onclick="searchKeyword('${page.endPageNum}');" id="pNum"> / ${page.endPageNum}</a>
					<a style="color: black; text-decoration: none;"> ▶ </a>
				</c:when>
				<c:otherwise>
					<a style="cursor: pointer;" onclick="searchKeyword('${pageNum-1}');" id="pNum" > ◀ </a>
					<input type="text" id="pageInput"  value="${pageNum}" onkeypress="leadPageNumInputEnter(event);"/>  
					<a style="cursor: pointer;" onclick="searchKeyword('${page.endPageNum}');" id="pNum"> / ${page.endPageNum}</a>
					<a style="cursor: pointer;" onclick="searchKeyword('${pageNum+1}');" id="pNum"> ▶ </a>
				</c:otherwise>
			</c:choose>
		</div>
    
</div>
  

</body>
</html>
 