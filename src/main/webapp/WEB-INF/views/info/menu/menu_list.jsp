<!-- 
업 무 명 : menu_list 메뉴관리 화면
작 성 자 : 정은지 (wjdmwi@coreplus.co.kr)
작 성 일 : 2015/07/28
수 정 자 : 정은지 (wjdmwi@coreplus.co.kr)
수 정 일 : 2015/07/28
내 용 : 메뉴에 대한 목록을 보여준다.
*참고사항 : 
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript" src="${ctx}/resources/common/js/jquery.blockUI.js"></script>
<script type="text/javascript">
	if('${menuDel_result}' == 1){
		$("#menuList").children().remove();
		alert("메뉴가 삭제되었습니다.");
		//location.href = '${ctx}/menu';
		goMenuList();
	}
	if('${menuChkDel_result}' != 0){
		$("#menuList").children().remove();
		alert("메뉴가 삭제되었습니다.");
		goMenuList();
	}
	if('${result}'== 1){
		alert("메뉴가 추가 되었습니다.");
		goMenuList();
	}
	
	if('${menuMdf_result}'== 1){
		alert("메뉴가 수정 되었습니다.");
		goMenuList();
	}
</script>
<script>
$(document).ready(function() {
	if('${menuVo.act_yn}' == 'Y'){  //활성화한여부
		$("#act_y").prop("checked", true);
		$("#act_n").prop("checked", false);
	} else if('${menuVo.act_yn}' == 'N'){
		$("#act_n").prop("checked", true);
		$("#act_y").prop("checked", false);
	}
	
	if('${menuVo.retrv_auth_yn}' == 'Y'){  //조회권한여부
		$("#rtrv_y").prop("checked", true);
		$("#rtrv_n").prop("checked", false);
	} else if('${menuVo.retrv_auth_yn}' == 'N'){
		$("#rtrv_n").prop("checked", true);
		$("#rtrv_y").prop("checked", false);
	}
	
	if('${menuVo.create_auth_yn}' == 'Y'){  //생성권한여부
		$("#crt_y").prop("checked", true);
		$("#crt_n").prop("checked", false);
	} else if('${menuVo.create_auth_yn}' == 'N'){
		$("#crt_n").prop("checked", true);
		$("#crt_y").prop("checked", false);
	}
	
	if('${menuVo.mdfy_auth_yn}' == 'Y'){  //수정권한여부
		$("#mdfy_y").prop("checked", true);
		$("#mdfy_n").prop("checked", false);
	} else if('${menuVo.mdfy_auth_yn}' == 'N'){
		$("#mdfy_n").prop("checked", true);
		$("#mdfy_y").prop("checked", false);
	}
	
	if('${menuVo.del_auth_yn}' == 'Y'){  //삭제권한여부
		$("#del_y").prop("checked", true);
		$("#del_n").prop("checked", false);
	} else if('${menuVo.del_auth_yn}' == 'N'){
		$("#del_n").prop("checked", true);
		$("#del_y").prop("checked", false);
	}
	
	//메뉴레벨 코드
	$("#menu_lev > option[value='${menuVo.menu_lev}']").prop("selected", true);
	
	/* 상위메뉴 select 값으로 조건주기 */
    if($("#menu_lev > option:selected").val() == '0001'){   
		$("#up_menu_id").css("background-color", "#EAEAEA");
	}
	
 	//메뉴리스트 추가
	if('${menuList_add}' == 1){
		menuListAdd();
	}
});
</script>
<input type="hidden" id="ctx" value="${ctx}">
<input type="hidden" id="menuSearch" value="${menuSearch}">
<input type="hidden" id="menuList_add" value="${menuList_add}">
<!-- 메뉴관리 리스트 -->
<div class="menuList" style="width: 100%;">
<!-- 상위메뉴  -->
<div style="width: 30%; float: left;">
	<div class="titleDIV" style="width: 94%; padding: 4% 4% 4% 0;"> 
		<span class="titleText">■ 기준정보 > 메뉴관리 > 상위메뉴</span> 
	</div>
	<div style = "height:10px;"></div>
	<div class="commonList" style="margin-top: 3%;">
		<form action="${ctx}/menuChkDelete" method="post" id="menu_chk_list">
	 	 <table id="menuListTable" class="commonTable">
	 	 	<thead>
	 	 		<tr style="border: 2px solid #eaeaea; border-bottom: 0px;">
	 	 			<th style="width: 10%; padding-top:1.5%;"><input type="checkbox" id="menuListCheck"></th>
	 	 			<th style="width: 24%;">메뉴ID</th>
	 	 			<th>메뉴명</th>
	 	 			<th>메뉴URL</th>
	 	 		</tr>
	 	 	</thead>
	 	 </table>
	 	 <div id="menuTableDBDiv" style="border: 1px solid #eaeaea;">
	 	 <table id="menuTableDB" class="commonTable">
	 	 	<tbody id="menuTbody">
	 	 		<c:forEach var="menu" items="${menuList}">
	 	 		<tr
	 	 			<c:if test="${menu.menu_id == menuVo.menu_id}"> 
 	 			 		style="background-color: #eaeaea;"
 	 				</c:if>
	 	 		>
	 	 			<td style="width: 10%;">
	 	 				<input type="checkbox" id="menu_chk" name="menu_chk" value="${menu.menu_id}" onclick="chkCancel();">
						<input type="hidden" id="seq_no" name="seq_no" value="${menu.seq_no}">
	 	 			</td>
	 	 			<td style="width: 25%; text-align:left;" onclick="menuDetail('${menu.menu_id}', '${menu.seq_no}');">
	 	 				${menu.menu_id}
	 	 				<input type="hidden" id="menu_id" name="menu_id" value="${menu.menu_id}">
	 	 			</td>
	 	 			<td style="width: 33%;text-align:left;" onclick="menuDetail('${menu.menu_id}', '${menu.seq_no}');">${menu.menu_nm}</td>
	 	 			<td style="text-align:left;" onclick="menuDetail('${menu.menu_id}', '${menu.seq_no}');">${menu.menu_url}</td>
	 	 		</tr>
	 	 		</c:forEach>
	 	 	</tbody>
	 	 </table>
	 	 </div>
	 	 </form>
	 	 <div class="listFootDiv" style="float: none; width: 100%;">
			 <div id="menuList_am_btn_div">
<!-- 		 	 	<input type="button" class="func_btn" id="menulist_add" value="추가"> -->
<!-- 		 	 	<input type="button" class="tr_btn"   id="menu_chk_del" value="삭제">&nbsp;&nbsp;&nbsp;&nbsp; -->
		 	 	<input type="button" class="func_btn" id="up_update" value="편집">
		 	 	
		 	 	<input type="button" value="▲" id="menu_col_up" class="func_btn" style="font-weight: bold; display: none;">
				<input type="button" value="▼" id="menu_col_down" class="func_btn" style="font-weight: bold; display: none;">
		 	 	<input type="button" class="tr_btn" id="up_save" value="저장" style="display: none;">
		 	 	<input type="button" class="func_btn" id="up_update_cancel" value="취소" style="display: none;">
<!-- 		 	 	<input type="button" class="tr_btn"   id="menu_chk_del" value="삭제"> -->
			 </div>
   		 </div>
	</div>
</div>
<div style="float: left; margin-left: 0.2%; margin-top: 18%;">
  <h1> &nbsp;▶</h1>
</div>
<!-- 하위메뉴  -->
<div style="width: 30%; float: left; margin-left: 0.5%;">
	<div class="titleDIV" style="width: 94%; padding: 4% 4% 4% 0%;"> 
		<span class="titleText">■ 기준정보 > 메뉴관리 > 하위메뉴</span> 
	</div>
	<div class="commonList" style="margin-top: 3%;">
		<form action="${ctx}/menuChkDelete" method="post" id="menudown_chk_list">
	 	 <table id="menuDownListTable" class="commonTable">
	 	 	<thead>
	 	 		<tr style="border: 2px solid #eaeaea; border-bottom: 0px;">
	 	 			<th style="width: 8%; padding-top:1.5%;"><input type="checkbox" id="menuListCheck"></th>
	 	 			<th style="width: 20%;">메뉴ID</th>
	 	 			<th style="width: 35%;">메뉴명</th>
	 	 			<th style="width: 37%;">메뉴URL</th>
	 	 		</tr>
	 	 	</thead>
	 	 </table>
	 	 <div id="menuDownTableDBDiv" style="border: 1px solid #eaeaea;">
	 	 <table id="menuDownTableDB" class="commonTable">
	 	 	<tbody id="menudownTbody">
	 	 		<c:forEach var="menudown" items="${menudownList}">
	 	 		<tr id="${menudown.menu_id}">
	 	 			<td>
	 	 				<input type="checkbox" id="menudown_chk" name="menudown_chk" value="${menudown.menu_id}" onclick="chkCancel();">
	 	 				<input type="hidden" id="menudown_seq_no" name="menudown_seq_no" value="${menudown.seq_no}">
	 	 			</td>
	 	 			<td style="width: 20%;text-align:left;" onclick="menudownDetail('${menudown.menu_id}');">
	 	 				${menudown.menu_id}
	 	 				<input type="hidden" id="menu_id" name="menu_id" value="${menudown.menu_id}">
	 	 			</td>
	 	 			<td style="text-align:left;" onclick="menudownDetail('${menudown.menu_id}');">${menudown.menu_nm}</td>
	 	 			<td style="text-align:left;" onclick="menudownDetail('${menudown.menu_id}');">${menudown.menu_url}</td>
	 	 		</tr>
	 	 		</c:forEach>
	 	 	</tbody>
	 	 </table>
	 	 </div>
	 	 </form>
	 	 <div class="listFootDiv" style="float: none; width: 100%;">
			 <div id="menuList_am_btn_div">
		 	 	<input type="button" class="func_btn" id="down_update" value="편집">
		 	 	
		 	 	<input type="button" value="▲" id="menudown_col_up" class="func_btn" style="font-weight: bold; display: none;">
				<input type="button" value="▼" id="menudown_col_down" class="func_btn" style="font-weight: bold; display: none;">
				<input type="button" class="tr_btn" id="down_save" value="저장" style="display: none;"> 
				<input type="button" class="func_btn" id="down_update_cancel" value="취소" style="display: none;">
<!-- 		 	 	<input type="button" class="tr_btn"   id="menudown_chk_del" value="삭제"> -->
			 </div>
   		 </div>
	</div>
</div>
<div style="float: left; margin-left: 0.2%; margin-top: 18%;">
  <h1> &nbsp;▶</h1>
</div>
<!-- 메뉴 상세정보  -->
<div id="menuDetailDiv" style="width: 31%; float: right; margin-right: 2%;">
	<div class="titleDIV" style="width: 94%; padding: 3.5% 3.5% 3.5% 0;"> 
		<span class="titleText">■ 메뉴관리 > 상세정보 </span>
	</div>
	<div class="commonDetail" style="margin-top: 3%;">
		<form action="${ctx}/menuInsert" method="post" id="menuInsertForm" name="menuInsertForm">
		   	 <table class="commonDetailTable" id="menuDetailTable" style="margin-bottom: 3%;">
		   	 	<tr>
		   	 		<th id="impTh">* 메뉴명&nbsp;</th>
		   	 		<td style="padding: 2%;">
		   	 			<input type="text" id="menu_nm" name="menu_nm" value="${menuVo.menu_nm}" readonly="readonly" style="width: 95%;">
		   	 			<input type="hidden" id="hmenu_nm" name="hmenu_nm" value="${menuVo.menu_nm}">
		   	 			<input type="hidden" id="hmenu_id" name="hmenu_id" value="${menuVo.menu_id}">
		   	 			<input type="hidden" id="hauth_id" name="hauth_id" value="${menuVo.auth_id}">
		   	 			<input type="hidden" id="mi" name="mi" value="">
		   	 		</td>
		   	 	</tr>
		   	 	<tr>
		   	 		<th>메뉴레벨&nbsp;</th>
		   	 		<td style="padding: 2%;">
		   	 			<select name="menu_lev" id="menu_lev" class="select" disabled="disabled">
	   	 					<c:forEach var="code" items="${menuLevCode}">
			     	 			<option value="${code.menu_lev}">${code.menu_lev_cd_nm}</option>
							</c:forEach>
		     	 		</select>
		   	 		</td>
		   	 	</tr>
		   	 	<tr>
		   	 		<th>메뉴URL&nbsp;</th>
		   	 		<td style="padding: 2%;">
		   	 			<input type="text" id="menu_url" name="menu_url" value="${menuVo.menu_url}" readonly="readonly" style="width: 95%;">
		   	 			<input type="hidden" id="hmenu_url" value="${menuVo.menu_url}">
		   	 		</td>
		   	 	</tr>
		   	 	<tr>
		   	 		<th>상위메뉴명&nbsp;</th>
		   	 		<td style="padding: 2%;">
		   	 			<input type="text" id="up_menu_id" name="up_menu_id" value="${menuVo.up_menu_id}" readonly="readonly">
		   	 			<input type="button" id="up_menu_srh" class="func_btn" value="검색" disabled="disabled">
		   	 			<input type="hidden" id="hup_menu_id" value="${menuVo.up_menu_id}">
		   	 		</td>
		   	 	</tr>
		   	 	<tr>
		   	 		<th style="padding: 2%;">활성화여부&nbsp;</th>
		   	 		<td style="padding: 1.5%;">
		   	 			<input type="radio" id="act_y" name="act_yn" value="Y" disabled="disabled" checked="checked"> Y
		   	 			<input type="radio" id="act_n" name="act_yn" value="N" disabled="disabled"> N
		   	 		</td>
		   	 	</tr>
		   	 </table>
	   	 </form>
	 	 <div class="listFootDiv" style="width: 100%;">
	 	 	<div id="menu_am_btn_div">
				<input type="button" class="func_btn" id="menu_add"  value="추가">
				<input type="button" class="func_btn" id="menu_mdfy" value="편집">
				<input type="button" class="tr_btn"   id="menu_del"  value="삭제">
			</div>
			<div id="menu_add_btn_div" disabled="disabled">
				<input type="button" class="tr_btn"   id="menu_save"   onclick="menu_save('${ctx}');" value="저장">
				<input type="button" class="func_btn" id="menu_cancel" value="취소">
			</div>
			<div id="menu_update_btn_div" disabled="disabled">
				<input type="button" class="tr_btn"   id="menu_update_save"   value="저장">
				<input type="button" class="func_btn" id="menu_update_cancel" value="취소">
			</div>
	     </div>
	</div>
</div>
</div>

<!-- 상위메뉴 검색 modal 창 -->
<div id="up_menu_srh_modal" align="center" style="display: none; cursor: default; width: 100%; height: 100%;">
	<div style="width: 100%; background-color: #ececec;" align="right">
		<input type="button" value="X" style="cursor: pointer;" onclick="modal_close();">
	</div>
<div class="up_menu_srh_modal_titleDIV" style="text-align: left; margin-bottom: 5px; padding-left: 12px;">
	<span class="titleText">■ 상위메뉴 검색 </span>
</div>
<div id="up_menu_srh_modal_Tbl_Div">
<form name="up_menu_srh_form" id="up_menu_form" method="post" action="" >
	<table class="ModaldetailTable">
		<thead>
			<tr>
				<th height="30px">메뉴ID</th>
				<th>메뉴명</th>
				<th>메뉴URL</th>
			</tr>
		</thead>
	</table>
	<div id="ModaldetailTableDBDiv">
	<table id="ModaldetailTableDB">
		<tbody>
			<c:forEach var="upmenuList" items="${upmenuList}">
				<tr height="30px" onMouseOver="this.style.backgroundColor='#ebe7e7'" onMouseOut="this.style.backgroundColor=''">
					<td style="width:20%;">${upmenuList.menu_id}
						<input type="hidden" class="Modmenu_id"  name="${upmenuList.menu_id}" value="${upmenuList.menu_id}">
						<input type="hidden" class="Modmenu_name"name="${upmenuList.menu_nm}" value="${upmenuList.menu_nm}">
					</td>
					<td style="width:20.5%;" id="Modmenu_name">${upmenuList.menu_nm}</td>
					<td style="width:16%;">${upmenuList.menu_url}</td>
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
			<input type="button" value="닫기" class="modal_btn" id="cod_cancel" onclick="modal_close()">
		</div>
	</div>
</div>
</div> <!-- 상위메뉴 검색 modal 창 끝 -->