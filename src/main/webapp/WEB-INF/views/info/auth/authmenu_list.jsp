<!-- 
업 무 명 : authmenu_list 권한메뉴 화면
작 성 자 : 정은지 (wjdmwi@coreplus.co.kr)
작 성 일 : 2015/07/29
수 정 자 : 정은지 (wjdmwi@coreplus.co.kr)
수 정 일 : 2015/07/29
내 용 : 권한메뉴에 대한 목록을 보여준다.
*참고사항 : 
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript" src="${ctx}/resources/common/js/jquery.blockUI.js"></script>
<script type="text/javascript">
	if('${authmenuChkDel_result}' != 0){
		$("#authmenu_listDiv").children().remove();
		alert("메뉴권한이 삭제되었습니다.");
		goAuthMenuList();
	}
	
	if('${authmenu_result1}' != 0){
		alert("메뉴권한이 추가 되었습니다.");
		goAuthMenuList();
	}
</script>
<input type="hidden" id="hauth_id" name="hauth_id" value="${auth_id}">
<!-- 권한 메뉴 리스트 -->
<div>
	<div class="titleDIV" style="margin-top:0.5%;">
		<span class="titleText">■ <a href="#" id="back_auth"> 권한관리 </a>
		    > 상세정보 (<a href="#" id="back_authDetail">${auth_id}</a>)
			> 메뉴권한</span>
	</div>
	<div style = "height:10px;"></div>
	<div class="commonList" style="padding-bottom: 4%;">
	<form action="${ctx}/authmenuChkDelete" method="post" id="authmenu_chk_list">
	 	 <table id="authmenuTable" class="commonTable">
	 	 	<thead>
	 	 		<tr>
	 	 			<th style="width: 2.9%;"><input type="checkbox" id="authmenuListCheck" name="authmenuListCheck" class="am_chkbox"></th>
	 	 			<th style="width: 19.35%;">메뉴ID</th>
	 	 			<th style="width: 35.05%;">메뉴명</th>
	 	 			<th style="width: 18.4%;">상위메뉴명</th>
	 	 			<th style="width: 6.3%;">생성여부</th>
	 	 			<th style="width: 6.28%;">수정여부</th>
	 	 			<th style="width: 6.35%;">삭제여부</th>
	 	 			<th style="width: 6.1%;">조회여부</th>
	 	 		</tr>
	 	 	</thead>
	 	 </table>
	 	 <div id="authmenuTableDBDiv">
	 	 <table id="authmenuTableDB" class="commonTable">
	 	 	<tbody id="authmenuTbody">
	 	 		<c:forEach var="authmenu" items="${authmenuList}">
		 	 		<tr>
		 	 			<td style="width: 2.9%;">
		 	 				<input type="checkbox" id="authmenu_chk" name="authmenu_chk" value="${authmenu.menu_id}" onclick="chkCancel();">
		 	 			</td>
		 	 			<td style="width: 19.35%;">${authmenu.menu_id}</td>
		 	 			<td style="width: 35.05%;">${authmenu.menu_nm}</td>
		 	 			<td style="width: 18.4%;">${authmenu.up_menu_id}</td>
		 	 			<c:if test="${authmenu.crt_yn == 'Y'}">
			 	 			<td style="width: 6.3%;">
			 	 				<input type="checkbox" name="crt_yn" id="crt_yn" value="Y" checked="checked" disabled="disabled">
			 	 			</td>
		 	 			</c:if>
		 	 			<c:if test="${authmenu.crt_yn == 'N'}">
			 	 			<td style="width: 6.3%;">
			 	 				<input type="checkbox" name="crt_yn" id="crt_yn" value="N" disabled="disabled">
			 	 			</td>
		 	 			</c:if>
		 	 			<c:if test="${authmenu.mdfy_yn == 'Y'}">
			 	 			<td style="width: 6.28%;">
			 	 				<input type="checkbox" name="mdfy_yn" id="mdfy_yn" value="Y" checked="checked" disabled="disabled">
			 	 			</td>
		 	 			</c:if>
		 	 			<c:if test="${authmenu.mdfy_yn == 'N'}">
			 	 			<td style="width: 6.28%;">
			 	 				<input type="checkbox" name="mdfy_yn" id="mdfy_yn" value="N" disabled="disabled">
			 	 			</td>
		 	 			</c:if>
		 	 			<c:if test="${authmenu.del_yn == 'Y'}">
			 	 			<td style="width: 6.35%;">
			 	 				<input type="checkbox" name="del_yn" id="del_yn" value="Y" checked="checked" disabled="disabled">
			 	 			</td>
		 	 			</c:if>
		 	 			<c:if test="${authmenu.del_yn == 'N'}">
			 	 			<td style="width: 6.35%;">
			 	 				<input type="checkbox" name="del_yn" id="del_yn" value="N" disabled="disabled">
			 	 			</td>
		 	 			</c:if>
		 	 			<c:if test="${authmenu.rtrv_yn == 'Y'}">
			 	 			<td style="width: 6.1%;">
			 	 				<input type="checkbox" name="rtrv_yn" id="rtrv_yn" value="Y" checked="checked" disabled="disabled">
			 	 			</td>
		 	 			</c:if>
		 	 			<c:if test="${authmenu.rtrv_yn == 'N'}">
			 	 			<td style="width: 6.1%;">
			 	 				<input type="checkbox" name="rtrv_yn" id="rtrv_yn" value="N" disabled="disabled">
			 	 			</td>
		 	 			</c:if>
		 	 		</tr>
	 	 		</c:forEach>
	 	 	</tbody>
	 	 </table>
	 </div>
	 	<div class="authmenu_listFootDiv">
	 	 	<div id="authmenu_am_btn_div">
				<input type="button" class="func_btn" id="authmenu_add_btn" value="추가">
				<input type="button" class="tr_btn"   id="authmenu_chk_del" value="삭제">
			</div>
	 	 	<div id="authmenu_add_btn_div" style="display: none">
				<input type="button" class="func_btn" id="authmenu_save_btn" value="저장">
				<input type="button" class="tr_btn"   id="authmenu_cancel_btn" value="취소">
			</div>
     	</div>
	 </form>
   </div>
</div>

<!-- 메뉴권한 추가 modal 창 -->
<div id="authmenu_srh_modal" align="center" style="display: none; width: 100%; height: 100%; cursor: default;">
	<div style="width: 100%; background-color: #ececec;" align="right">
		<input type="button" value="X" style="cursor: pointer;" onclick="authmenu_Mclose();">
	</div>
	<div class="authmenu_srh_modal_titleDIV" style="text-align: left; margin-bottom: 5px; padding-left: 12px;">
		<span class="titleText">■ 메뉴 검색 </span>
	</div>
	<div id="authmenu_srh_modal_Tbl_Div">
	<form name="authmenu_srh_form" id="authmenu_form" method="post" action="" >
		<table class="amModalTable">
			<thead>
				<tr>
					<th style="width: 7%; height: 30px;"><input type="checkbox" id="chk"></th>
					<th style="width: 20.25%;">메뉴ID</th>
					<th style="width: 19.5%;">메뉴명</th>
					<th style="width: 25.7%;">메뉴URL</th>
					<th>상위메뉴명</th>
				</tr>
			</thead>
		</table>
		<div id="amModalTableDBDiv">
		<table id="amModalTableDB">
			<tbody>
				<c:forEach var="menumodalList" items="${menumodalList}">
					<tr height="30px">
						<td style="width: 7.2%;">
			 	 			<input type="checkbox" id="chk_am" name="chk_am" class="authmenuCHK" value="${menumodalList.menu_id}">
			 	 		</td>
						<td style="width: 21%" id="m_menu_id">${menumodalList.menu_id}
							<input type="hidden" class="Modalmenu_id"  name="${menumodalList.menu_id}" value="${menumodalList.menu_id}">
							<input type="hidden" class="Modalmenu_name"name="${menumodalList.menu_nm}" value="${menumodalList.menu_nm}">
						</td>
						<td style="width: 20.3%" id="m_menu_nm">${menumodalList.menu_nm}</td>
						<td style="width: 26.6%">${menumodalList.menu_url}</td>
						<td id="m_up_menu_id">${menumodalList.up_menu_id}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</form>
	</div>
	<div class="Modal_FootDiv">
		<div style="padding: 2% 5%;" align="right">
			<div>
				<input type="button" value="확인" class="modal_btn" id="cod_save">
				<input type="button" value="닫기" class="modal_btn" id="authmenu_Mclose" onclick="authmenu_Mclose()">
			</div>
		</div>
	</div>
</div> <!-- 메뉴권한 추가 modal 창 끝 -->