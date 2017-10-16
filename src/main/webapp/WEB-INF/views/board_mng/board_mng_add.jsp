<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@include file="../include/header.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


 <script type="text/javascript" src="${ctx}/resources/common/js/standard/boardmng/boardmng.js"></script>  
  
<link rel="stylesheet" type="text/css" href="${ctx}/resources/common/Semantic/semantic.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="  crossorigin="anonymous"></script>
<script src="${ctx}/resources/common/Semantic/semantic.js"></script>
<link rel="stylesheet" href="${ctx}/resources/common/css/standard/common/sfa_common_detail.css" type="text/css" />

<title>Insert title here</title>
</head>
<body>

  <div id="title">
		<div class="caption">
		<h3 class="ui header" style="background: #fff;">■ 기준정보 > <a href="/boardmngInqr" style="font-size: 14pt; text-decoration:none; color: blue;">게시판 관리</a> > 게시판 관리 추가 </h3>
		</div>
	</div>
 
	<div id = "board_mng_add" style="margin-top:60px">
	<form role="form" name="board_mng_form"> 
  
	<table class="ui celled table">
	<tbody class="detailtbody">
	<tr> 
	<th>게시판이름</th>
 <td><input type="text" class="int" id="BOARD_NM" name="BOARD_NM" style="width:50%" /></td> 
	<th>게시판분류</th>
   <td style="height:30px"> 
   <select class="form-control" id="sel1" onchange= "fn_SelectBox(this.value);">
     <option value="0" style="text-align: center;">==구분==</option>
     <c:forEach items="${codelist}" var="list">
  		     <option value="${list.CODE}">${list.CD_NM}</option>							
 		 </c:forEach>  
   </select>
   </td>
	</tr>
	<tr>
	<th>관리자</th> <td></td>
	 <th>게시판코드</th> 
	<td style="height:30px">
	<input type="text" class="form-control" id="BOARD_MNG_CD" name="BOARD_MNG_CD" value="${board_mng_list.BOARD_MNG_CD}" style="width:50%;" readonly="readonly" />
	</td>
	</tr>
	<tr>
	<th>
	댓글여부
	</th>
	<td>
	<label class="radio-inline">
	<input type="radio" class="radio_class" id="reply_flg_y" name="REPLY_FLG" value="Y">Y
	</label> <label class="radio-inline">
	<input type="radio" class="radio_class" id="reply_flg_n" name="REPLY_FLG" value="N">N
	</label>
	</td>
	<th>
	게시판활성여부
	</th>
	<td style="height:30px">
	<label class="radio-inline">
	<input type="radio" class="radio_class" value="Y" id="active_flg_y" name="ACTIVE_FLG" value="Y">Y
	</label> 
	<label class="radio-inline">
	<input type="radio" class="radio_class" value="N" id="active_flg_n"  name="ACTIVE_FLG" value="N">N
	</label>
	</td>
	</tr>
	<tr>
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
	 <th>
	 공지활성화
	 </th>
	 <td>
	 <label  class="radio-inline">
	 <input type="radio" class="radio_class" id="notice_flg_y" name="NOTICE_FLG" value="Y">Y
	 </label> 
	 <label class="radio-inline">
	 <input type="radio" class="radio_class" id="notice_flg_n" name="NOTICE_FLG" value="N">N
	 </label>
	 </td>
	</tr>
	</tbody>
	</table> 
	</form>
	</div>
	
	<div>
	<input type="button" id="board_mng_add_fbtn" class = "tiny ui blue button" value="저장" onclick="board_mng_add_save();"/>
     <input type="button" class="tiny ui button" id="board_list_fbtn" value="취소" onclick="go_list();"/>
	</div>
 
 

</body>
</html>
<%-- <%@include file="../include/footer.jsp"%> --%>
