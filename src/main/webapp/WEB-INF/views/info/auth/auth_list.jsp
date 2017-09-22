<!-- 
업 무 명 : auth_list 권한관리 화면
작 성 자 : 정은지 (wjdmwi@coreplus.co.kr)
작 성 일 : 2015/07/28
수 정 자 : 정은지 (wjdmwi@coreplus.co.kr)
수 정 일 : 2015/08/04
내 용 : 권한에 대한 목록과 상세정보를 보여준다.
*참고사항 : 
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript">
	if('${result}' == 1){
		alert("권한이 추가되었습니다.");
		//location.href = '${ctx}/menu';
		goAuthList();
	}
	
	if('${authMdf_result}' == 1){
		alert("권한이 수정되었습니다.");
		//location.href = '${ctx}/menu';
		goAuthList();
	}
	
	if('${authDel_result}' == 1){
		$("#auth").children().remove();
		alert("권한이 삭제되었습니다.");
		//location.href = '${ctx}/menu';
		goAuthList();
	}
	
	if('${authChkDel_result}' != 0){
		$("#auth").children().remove();
		alert("권한이 삭제되었습니다.");
		//location.href = '${ctx}/menu';
		goAuthList();
	}
</script>
<script>
$(document).ready(function() {
	if('${authVO.act_yn}' == 'Y'){  //활성화한여부
		$("#act_y").prop("checked", true);
		$("#act_n").prop("checked", false);
	} else if('${authVO.act_yn}' == 'N'){
		$("#act_n").prop("checked", true);
		$("#act_y").prop("checked", false);
	}
});
</script>
<input type="hidden" id="ctx" value="${ctx}">
<!-- 권한관리 목록 -->
<div id="auth" style="width: 100%; position: absolute;" >
<div style="width: 30%; float:left;">
	<div class="authlist_titleDIV" style="margin-top:0.5%;">
		<span class="titleText">■ 기준정보 > 권한관리</span>
	</div>
	<div id="auth_list" style="margin-top:1%;">
	<form action="${ctx}/authChkDelete" method="post" id="auth_chk_list">
 	 <table id="authTable" class="commonTable">
 	 	<thead>
 	 		<tr>
 	 			<th style="width: 25px;"><input type="checkbox" id="authListCheck"></th>
 	 			<th>권한ID</th>
 	 			<th>권한명</th>
 	 			<th>상태</th>
 	 		</tr>
 	 	</thead>
 	 	<tbody>
 	 		<c:forEach var="auth" items="${authList}">
            <!-- 리스트의 auth_id와 선택하여 상세정보로 출력되는 auth_id가 같을 때   -->
 	 		<tr 
 	 			<c:if test="${auth.auth_id == authVO.auth_id}"> 
 	 			 	style="background-color: #eaeaea;"
 	 			</c:if>
 	 		>
 	 			<td><input type="checkbox" id="auth_chk" name="auth_chk" value="${auth.auth_id}" onclick="chkCancel();"></td>
 	 			<td onclick="authDetail('${auth.auth_id}', '${submenu.up_menu_id}');">${auth.auth_id}</td>
 	 			<td onclick="authDetail('${auth.auth_id}', '${submenu.up_menu_id}');">${auth.auth_nm}</td>
 	 			<td onclick="authDetail('${auth.auth_id}', '${submenu.up_menu_id}');">
 	 				<c:if test="${auth.act_yn=='Y'}">Y</c:if>
					<c:if test="${auth.act_yn=='N'}">N</c:if>
 	 			</td>
 	 		</tr>
 	 		</c:forEach>
 	 	</tbody>
 	 </table>
 	</form>
	 	 <div class="auth_listFootDiv">
	 	 	<input type="button" class="tr_btn" id="auth_chk_del" value="삭제">
	     </div>
   </div>
</div> <!-- auth_list DIV 끝 --> 

<div style="float: right; margin-right: 2.5%; width: 50%">  
   <div class="authdetail_titleDIV" style="margin-top:0.5%;">
		<span class="titleText">■ 권한관리 > 상세정보</span>
	</div>
    <div id="commonDetail" style="margin-top:1%;">
    <form action="" method="post" id="AuthDetailForm">
    	<table class="auth_detailTable">
 	   	 	<tr>
 	   	 		<th id="impTh">* 권한ID</th>
 	   	 		<td>
 	   	 			<input type="text"   id="auth_id" name="auth_id" value="${authVO.auth_id}" readonly="readonly"/>
 	   	 			<input type="hidden" id="hauth_id" value="${authVO.auth_id}"/>
 	   	 		</td>
 	   	 	</tr>
 	   	 	<tr> 
 	   	 		<th id="impTh">* 권한명</th> 
 	   	 		<td>
 	   	 			<input type="text"   id="auth_nm" name="auth_nm" value="${authVO.auth_nm}" readonly="readonly"/>
 	   	 			<input type="hidden" id="hauth_nm" value="${authVO.auth_id}"/>
 	   	 		</td> 
 	   	 	</tr> 
 	   	 	<tr> 
 	   	 		<th height="40px">활성화여부</th> 
 	   	 		<td> 
 	   	 			<input type="radio" id="act_y" name="act_yn" value="Y" disabled="disabled" checked="checked"> Y 
 	   	 			<input type="radio" id="act_n" name="act_yn" value="N" disabled="disabled"> N 
 	   	 		</td> 
 	   	 	</tr> 
 	   	 </table> 
 	</form>
 	   	 
 	   	<div class="detailFootDiv">
	 	 	<div id="auth_am_btn_div">
				<input type="button" class="func_btn" id="auth_add" value="추가">
				<input type="button" class="func_btn" id="auth_mdfy" value="편집">
				<input type="button" class="tr_btn"   id="auth_del" value="삭제">
			</div>
			<div id="Authsc_btn_div">
				<input type="button" class="tr_btn"   id="auth_save" value="저장">
				<input type="button" class="func_btn" id="auth_cancel" value="취소">
			</div>
			<div id="Authupdate_btn_div">
				<input type="button" class="tr_btn"   id="auth_update_save"   value="저장">
				<input type="button" class="func_btn" id="auth_update_cancel" value="취소">
			</div>
			<div id="Authtab_btn_div">
				<input type="button" class="back_btn" id="go_menu_auth" value="메뉴권한">
				<input type="button" class="back_btn" id="go_user_auth" value="사용자권한">
			</div>
     	</div>
    </div>
</div> <!-- auth_detail DIV 끝 --> 
</div>