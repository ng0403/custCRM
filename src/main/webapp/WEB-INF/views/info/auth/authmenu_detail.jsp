<!-- 
업 무 명 : authmenu_detail 메뉴권한관리 상세정보 화면
작 성 자 : 정은지 (wjdmwi@coreplus.co.kr)
작 성 일 : 2015/08/26
수 정 자 : 정은지 (wjdmwi@coreplus.co.kr)
수 정 일 : 2015/08/26
내 용 : 메뉴권한에 대한 상세정보를 보여준다.
*참고사항 : 
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript" src="${ctx}/resources/common/js/jquery.blockUI.js"></script>
<script type="text/javascript">
	if('${authmenu_result}'== 1){
		alert(auth_id);
		alert("메뉴권한이 추가 되었습니다.");
		backAuthSubmit('/authmenu');
	}
</script>
<script>
$(document).ready(function() {
	
	if('${menuVo.rtrv_yn}' == 'Y'){  //조회권한여부
		$("#rtrv_y").prop("checked", true);
		$("#rtrv_n").prop("checked", false);
	} else if('${menuVo.rtrv_yn}' == 'N'){
		$("#rtrv_n").prop("checked", true);
		$("#rtrv_y").prop("checked", false);
	}
	
	if('${menuVo.crt_yn}' == 'Y'){  //생성권한여부
		$("#crt_y").prop("checked", true);
		$("#crt_n").prop("checked", false);
	} else if('${menuVo.crt_yn}' == 'N'){
		$("#crt_n").prop("checked", true);
		$("#crt_y").prop("checked", false);
	}
	
	if('${menuVo.mdfy_yn}' == 'Y'){  //수정권한여부
		$("#mdfy_y").prop("checked", true);
		$("#mdfy_n").prop("checked", false);
	} else if('${menuVo.mdfy_yn}' == 'N'){
		$("#mdfy_n").prop("checked", true);
		$("#mdfy_y").prop("checked", false);
	}
	
	if('${menuVo.del_yn}' == 'Y'){  //삭제권한여부
		$("#del_y").prop("checked", true);
		$("#del_n").prop("checked", false);
	} else if('${menuVo.del_yn}' == 'N'){
		$("#del_n").prop("checked", true);
		$("#del_y").prop("checked", false);
	}
});
</script>
<input type="hidden" id="ctx" value="${ctx}">
<input type="hidden" id="authmenuList_add" value="${authmenuList_add}">
<input type="hidden" id="hauth_id" name="hauth_id" value="${auth_id}">
<!-- 메뉴상세정보  -->
<div id="menuDetailDiv">
	<div class="titleDIV">
		<span class="titleText">■ <a href="#" id="back_auth"> 권한관리 </a>
		    > <a href="#" id="back_authDetail"> 권한관리 상세정보 </a>
		    > 메뉴권한(<a href="#" id="back_authmenu">${auth_id}</a>) > 메뉴권한 추가 </span>
	</div>
	<div style = "height:10px;"></div>
	<div class="commonDetail" style="padding-bottom: 3%; margin-top:1%;">
	<form action="${ctx}/authmenuInsert" method="post" id="authmenuInsertForm">
	   	 <table class="commonDetailTable">
	   	 	<tr>
	   	 		<th id="impTh">* 메뉴명</th>
	   	 		<td>
	   	 			<input type="text"   id="menu_nm" name="menu_nm" value="${menuVo.menu_nm}" readonly="readonly">
	   	 			<input type="button" id="menu_srh" class="func_btn" value="검색">
	   	 			<input type="hidden" id="hmenu_id" name="hmenu_id" value="${menuVo.menu_id}">
	   	 			<input type="hidden" id="hmenu_nm" value="${menuVo.menu_nm}">
	   	 		</td>
	   	 		<th id="impTh">* 조회권한여부</th>
	   	 		<td>
	   	 			<input type="radio" id="rtrv_y" name="rtrv_yn" value="Y" checked="checked"> Y
	   	 			<input type="radio" id="rtrv_n" name="rtrv_yn" value="N"> N
	   	 		</td>
	   	 	</tr>
	   	 	<tr>
	   	 		<th id="impTh">* 생성권한여부</th>
	   	 		<td>
	   	 			<input type="radio" id="crt_y" name="crt_yn" value="Y" checked="checked"> Y
	   	 			<input type="radio" id="crt_n" name="crt_yn" value="N"> N
	   	 		</td>
	   	 		<th id="impTh">* 수정권한여부</th>
	   	 		<td>
	   	 			<input type="radio" id="mdfy_y" name="mdfy_yn" value="Y" checked="checked"> Y
	   	 			<input type="radio" id="mdfy_n" name="mdfy_yn" value="N"> N
	   	 		</td>
	   	 	</tr>
	   	 	<tr>
	   	 		<th id="impTh">* 삭제권한여부</th>
	   	 		<td>
	   	 			<input type="radio" id="del_y" name="del_yn" value="Y" checked="checked"> Y
	   	 			<input type="radio" id="del_n" name="del_yn" value="N"> N
	   	 		</td>
	   	 		<th></th>
	   	 		<td></td>
	   	 	</tr> 
	   	 </table>
	   	 </form>
   	 
	   	 <div class="listFootDiv">
			<div id="authmenu_add_btn_div">
				<input type="button" class="tr_btn"   id="authmenu_save"   value="저장">
				<input type="button" class="func_btn" id="authmenu_cancel" value="취소">
			</div>
	     </div>
   	 </div>
 </div>