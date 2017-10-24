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
 <script type="text/javascript" src="/resources/common/js/board/board_list.js"></script> 
  <script src="http://malsup.github.com/jquery.form.js"></script>
  
 
<title>Insert title here</title>

</head>
<body> 

<form role="board_code" name = "form_modify" method="post">
<input type="hidden" id="board_mng_no" name="board_mng_no" value="${BOARD_MNG_NO}">
</form>


   
  <div id="title">
		<div class="caption">
		<h3 class="ui header" style="background: #fff;"> ■ 영업정보 > 게시판 </h3>
		 
		</div>
</div>
 
    <div class="commonList">
       <div class="searchDiv">
			<input type="text" placeholder="제목"  id="keyword" name="keyword"  onkeydown="boardSearchEnter(event);" style="width:20%">
			<i class="list icon"></i>
		    <input type="button" onclick="boardPaging(1);" value="조회" id="board_inqr_fbtn" class="func_btn" value="검색"">
			
		</div>	
    
           <form name="delAllForm" id ="delAllForm" method="post" action="/board_remove">  
           <input type='hidden' id="BOARD_MNG_NO" name='BOARD_MNG_NO' value="${BOARD_MNG_NO}"/> 
             <div id="tableline">
               <table class="commonTable" id="boardTable">
                  <thead>
                     <tr style="text-align:center">
                        <th style="width:5%"><input id="checkall" type="checkbox" onclick="checkAll();"/></th>
                        <th>번호</th>
                        <th>제목</th>
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
                           <td style='width:20%;'>${boardVO.CREATED}</td>
                           <td style='width:10%;'>${boardVO.VIEW_CNT}</td>   
                        </tr> 
                     </c:forEach>
                  </tbody>
               </table>
            </div>
       </form>
       
  <div class="bottom_div">
       <div class="functionBtn_div">
         <input type="button" id = "board_add_fbtn"  class = "func_btn" value="추가" onclick="board_add();"/> 
           <input type="button" id ="board_remove_fbtn" class="tr_btn" value="삭제"  onclick="deleteAction() "/>
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
					<a style="cursor: pointer;" onclick="boardPaging('${page.endPageNum}');" id="pNum" > / ${page.endPageNum}</a>
					<a style="cursor: pointer;" onclick="boardPaging('${PageNum+1}');" id="pNum"> ▶ </a>
				</c:when>
				<c:when test="${PageNum == page.endPageNum}">
					<a style="cursor: pointer;" onclick="boardPaging('${PageNum-1}');" id="pNum"> ◀ </a>
					<input type="text" id="pageInput"  value="${page.endPageNum}" onkeypress="leadPageNumInputEnter(event);"/> 
					<a style="cursor: pointer;" onclick="boardPaging('${page.endPageNum}');" id="pNum"> / ${page.endPageNum}</a>
					<a style="color: black; text-decoration: none;"> ▶ </a>
				</c:when>
				<c:otherwise>
					<a style="cursor: pointer;" onclick="boardPaging('${PageNum-1}');" id="pNum" > ◀ </a>
					<input type="text" id="pageInput"  value="${PageNum}" onkeypress="leadPageNumInputEnter(event);"/>  
					<a style="cursor: pointer;" onclick="boardPaging('${page.endPageNum}');" id="pNum"> / ${page.endPageNum}</a>
					<a style="cursor: pointer;" onclick="boardPaging('${PageNum+1}');" id="pNum"> ▶ </a>
				</c:otherwise>
				</c:choose>
			</div>
    	
		</div>
    </div>

</body>
</html>
 