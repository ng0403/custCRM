<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@include file="../include/header.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script type="text/javascript" src="${ctx}/resources/common/js/board/board_modify.js"></script>  
   <link rel="stylesheet" href="${ctx}/resources/common/css/board/boardCSS.css" type="text/css" />
   
<title>Insert title here</title>
</head>
<body>
 

<div id="title" >
<div style="height:10px"></div>
		<div class="caption">
 		 ■ 영업정보 > <a href="/boardInqr?BOARD_MNG_NO=${boardVO.BOARD_MNG_NO}"  style="cursor: pointer;">게시판</a> >  게시글 수정
 		</div>
 </div>

 <div style="height:10px;"></div>
 <div class="coupon_detail"> 
 <form role="form" name="modifyForm" action="/board_modify " method="post" enctype="multipart/form-data">
 	 <input type='hidden' id="BOARD_NO" name='BOARD_NO' value="${boardVO.BOARD_NO}"> 
	 <input type='hidden' id="BOARD_MNG_NO" name='BOARD_MNG_NO' value="${boardVO.BOARD_MNG_NO}">   
<table class="commonDetailTable">
<tr>
 <th>제목</th> 
 <td colspan="3"><input type="text" placeholder="제목"  id="TITLE" name="TITLE" value= "${boardVO.TITLE}" style="width:850px"/></td>
</tr>
<tr>
 <th>파일</th>
<td colsapn="3"> 
<div id="file_div">
	<c:choose>
		<c:when test="${boardmnglist.FILE_ATTACH_FLG == 'N'}"> 
		   
		</c:when>
	   <c:when test="${boardmnglist.FILE_ATTACH_FLG == 'Y'}"> 
   		<c:if test="${boardVO.FILE_NM == null}">
   		<input type="file" multiple="multiple" name="filedata" id="filedata">
   		</c:if>
   		<c:if test="${boardVO.FILE_NM != null}">
   		<a href="/file_down?FILE_CD=${boardVO.FILE_CD}"><i class="file icon"></i>${boardVO.FILE_NM}</a> 
   		<i class="large trash icon" onclick="deleteFile('${boardVO.FILE_CD}');"></i>
   		</c:if>
   		</c:when>
   		</c:choose>
</div>
</td>
<tr>
<th>내 용</th>
<td colspan="4">
<textarea id="CONTENT" name="CONTENT" rows="25"  cols="120" style="resize: none;"  style="width:100%" >${boardVO.CONTENT}</textarea>
</td> 
</tr>
</table>
 </form>   
</div>
 <div id="baseBtnDiv" class="listFootDiv"> <!-- 버튼 div  -->
 <input type="button" id = "board_modify_fbtn" onClick="board_save();" class="tr_btn" value="저장"/>
  <input type="button" class="func_btn" id="board_list_fbtn" onClick="board_list();" value="취소"/>
 </div> 
 
<%-- <div class="container"> <!-- 전체 div-->

	<div> <!-- 제목 div-->
		<label id="txt" >제  목</label>
		<input type="text" class="inputTxt" name= "TITLE" id="TITLE" value= "${boardVO.TITLE}" />
	</div> 

<div> 
 
</div><!-- 파일 업로드  -->
<div> <!-- 내용 div -->
 <label id="txt" >내  용</label> 

 <textarea class="form-control" rows="10" name="CONTENT" >${boardVO.CONTENT}</textarea>
</div> 	


<div>   <!-- 댓글div -->
</div>
<div id="btns"> <!-- 버튼 div  -->
<!-- <input type="button" class = "btn btn-default" value="저장"/> -->
 <button type="submit" id = "board_modify_fbtn" onClick="board_save();" class="tiny ui orange button">저장</button>
  <input type="button" class="tiny ui button" id="board_list_fbtn" onClick="board_list();" value="취소"/>
</div>



</div>



<div class="modal fade" id="myModal" >
  <div class="modal-dialog">
    <div class="modal-content">
      <!-- header -->
      <div class="modal-header">
        <!-- 닫기(x) 버튼 -->
        <button type="button" class="close" data-dismiss="modal">×</button>
        <!-- header title -->
        <h4 class="modal-title">Header</h4>
      </div>
      <!-- body -->
      <div class="modal-body">
            Body
      </div>
      <!-- Footer -->
      <div class="modal-footer">
        Footer
        <button type="button" class="tiny ui orange button" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>
  --%>
  

</body>
</html>
 