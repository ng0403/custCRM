<!-- 
업 무 명 : authuser_list 사용자권한 화면 및 사용자권한 
작 성 자 : 정은지 (wjdmwi@coreplus.co.kr)
작 성 일 : 2015/07/29
수 정 자 : 정은지 (wjdmwi@coreplus.co.kr)
수 정 일 : 2015/07/29
내 용 : 사용자권한에 대한 목록을 보여준다.
*참고사항 : 
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript" src="${ctx}/resources/common/js/jquery.blockUI.js"></script>
<script type="text/javascript">
	if('${authuserChkDel_result}' != 0){
		$(".authmenuList").children().remove();
		alert("사용자권한이 삭제되었습니다.");
		goAuthUserList();
	}
</script>
<input type="hidden" id="hauth_id" name="hauth_id" value="${auth_id}">
<!-- 사용자권한 리스트 -->
<div class="authuserList">
	<div class="auth_titleDIV">
		<span class="titleText">■ <a href="#" id="back_auth"> 권한관리 </a>
		    > 상세정보 (<a href="#" id="back_authDetail">${auth_id}</a>)
			> 사용자권한</span>
	</div>
	<div style = "height:10px;"></div>
	<div id="authuser_listDiv">
	<form action="${ctx}/authuserChkDelete" method="post" id="authuser_chk_list">
	 	 <table id="authuserTable">
	 	 	<thead>
	 	 		<tr>
	 	 			<th style="width: 3%;"><input type="checkbox" id="authmenuListCheck"></th>
	 	 			<th style="width: 7%;">사용자ID</th>
	 	 			<th style="width: 9%;">사용자명</th>
	 	 			<th style="width: 9%;">권한</th>
	 	 			<th style="width: 11%;">부서명</th>
	 	 			<th style="width: 19%;">이메일</th>
	 	 			<th style="width: 16%;">연락처</th>
	 	 			<th style="width: 7%;">최종수정자</th>
	 	 			<th>등록일자</th>
	 	 		</tr>
	 	 	</thead>
	 	 </table>
	 	 <div id="authuserTableDBDiv">
	 	 <table id="authuserTableDB">
	 	 	<tbody>
	 	 		<c:forEach var="authuser" items="${authuserList}">
		 	 		<tr>
		 	 			<td style="width: 3%;">
		 	 				<input type="checkbox" id="authuser_chk" name="authuser_chk" value="${authuser.user_id}" onclick="chkCancel();">
		 	 			</td>
		 	 			<td style="width: 7%;">${authuser.user_id}</td>
		 	 			<td style="width: 9%;">${authuser.user_nm}</td>
		 	 			<td style="width: 10%;">${authuser.auth_nm}</td>
		 	 			<td style="width: 10%;">${authuser.org_id}</td>
		 	 			<td style="width: 21%;">${authuser.email}</td>
		 	 			<td style="width: 14%;">${authuser.cell_ph}</td>
		 	 			<td style="width: 8%;">${authuser.fin_mdfy_id}</td>
		 	 			<td>${authuser.create_date}</td>
		 	 		</tr>
		 	 	</c:forEach>
	 	 	</tbody>
	 	 </table>
	 	 </div>
	 </form>
	 	<div class="auth_userdetailFootDiv">
	 	 	<div id="authuser_am_btn_div">
				<input type="button" class="tr_btn"   id="authuser_chk_del" value="삭제">
			</div>
     </div>
   </div>
</div>
