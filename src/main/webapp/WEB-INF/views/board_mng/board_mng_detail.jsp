<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@include file="../include/header.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <script type="text/javascript" src="${ctx}/resources/common/js/boardmng/boardmng_detail.js"></script>  

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
 
<body> 


<form role="form" name = "form_modify" method="post">
 <input type='hidden' id="CODE_TXT" name='CODE_TXT' value="${board_mng_list.CODE_TXT}"/> 
 <input type='hidden' id="ACTIVE_FLG" value="${board_mng_list.ACTIVE_FLG}"/> 
 <input type='hidden' id="REPLY_FLG" value="${board_mng_list.REPLY_FLG }"/>
 <input type='hidden' id="NOTICE_FLG" value="${board_mng_list.NOTICE_FLG }"/>
 <input type='hidden' id="FILE_ATTACH_FLG" value="${board_mng_list.FILE_ATTACH_FLG }"/>
 <input type='hidden' id="CODE_TXT" value="${board_mng_list.CODE_TXT }"/> 
</form>
 
<!-- <div id="title">
		<div class="caption">
		<h3 class="ui header" style="background: #fff;">■ 기준정보 > <a href="/boardmngInqr" style="font-size: 14pt; text-decoration:none; color: blue;">게시판 관리</a> > 게시판 관리 상세 </h3>
		</div>
</div> -->
	
 	<div class="detailBtn"> 
	<div id = "baseBtnDiv" class="listFootDiv" style="padding-bottom:0.6%">
	<input type="button" id="board_mng_modify_fbtn" class = "func_btn" value="편집" onclick="modify_fbtn();"/>
	<input type="button" class="func_btn" id="board_list_fbtn" value="취소" onclick="cancelBtn();"/>
	</div>
	
	<div id = "baseBtnDiv2" class="listFootDiv" style="display:none; padding-bottom:0.6%">
		<input type="button" class = "tr_btn" id="board_mng_add_fbtn"  value="저장" onclick="updateBoardMng();"/>
		<input type="button" class="func_btn" id="board_cancle_fbtn" value="취소" onclick="modify_cancel();"/>
	</div>
	
	<div id = "baseBtnDiv3" class="listFootDiv" style="display:none; padding-bottom:0.6%">
		<input type="button" class = "tr_btn" id="board_mng_add_fbtn"  value="저장" onclick="boardmngInsert();"/>
		<input type="button" class="func_btn" id="board_cancle_fbtn" value="취소" onclick="modify_cancel();"/>
	</div>
	</div>
	
 
	<div id="board_mng_detail"  > 
  <form role="form" name="board_mng_form"> 
   <input type='hidden' id="BOARD_MNG_NO" name='BOARD_MNG_NO' value="${board_mng_list.BOARD_MNG_NO}"/> 
 	<table class="commonDetailTable">
 	<tbody class="detailtbody">
	<tr> 
	<th>게시판이름</th>
 <td style="height:30px"><input type="text" class="int" id="BOARD_NM" name="BOARD_NM"  value="${board_mng_list.BOARD_NM}" style="width:50%" /></td> 
	<th>게시판분류</th>
   <td id="board_cate"> 
   <select class="form-control" id="sel1" onchange= "fn_SelectBox(this.value);"> 
    <option value="default">==선택==</option>
    <c:forEach items="${codelist}" var="list">
 		 <c:choose>
			 <c:when test="${board_mng_list.BOARD_MNG_CD eq list.CODE}">
			 <option value="${list.CODE}" selected="selected">${list.code_name}</option>
		     </c:when>
		     <c:otherwise>
		     <option value="${list.CODE}">${list.code_name}</option>							
			 </c:otherwise>
			 </c:choose>
		 </c:forEach> 
   </select>
   </td>
	</tr>
	<tr>
	<th>활성화</th>
	 <td>
	 <label class="radio-inline">
	<input type="radio" class="radio_class" id="active_flg_y" name="ACTIVE_FLG" value="Y" >Y
	</label> <label class="radio-inline">
	<input type="radio" class="radio_class" id="active_flg_n" name="ACTIVE_FLG" value="N">N
	</label>
	 </td>
	 <th>게시판코드</th>
	  <td style="height:30px;"> 
 	  <input type="text" class="form-control" id="BOARD_MNG_CD" name="BOARD_MNG_CD" value="${board_mng_list.BOARD_MNG_CD}" style="width:50%;" readonly="readonly" /> 
 	   </td>
	</tr>
	<tr>
	<th>
	댓글여부
	</th>
	<td style="height:30px">
	<label class="radio-inline">
	<input type="radio" class="radio_class" id="reply_flg_y" name="REPLY_FLG" value="Y" >Y
	</label> <label class="radio-inline">
	<input type="radio" class="radio_class" id="reply_flg_n" name="REPLY_FLG" value="N">N
	</label>
	</td>
	 <th>
	파일업로드
	</th>
	<td style="height:30px">
	<label class="radio-inline" >
	<input type="radio" class="radio_class" id="file_attach_flg_y" name="FILE_ATTACH_FLG" value="Y">Y
	</label>
	<label class="radio-inline">
	<input type="radio" class="radio_class" id="file_attach_flg_n" name="FILE_ATTACH_FLG" value="N">N
	</label>
	 </td>
	</tr>
	<tr>
	
	 
	</table> 
	</tbody>
	</form>
	</div>
	 

</body>
</html>
<%-- <%@include file="../include/footer.jsp"%> --%>
