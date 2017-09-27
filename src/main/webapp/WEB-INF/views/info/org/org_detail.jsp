<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />	
<!DOCTYPE html>
<script type="text/javascript" src="resources/common/js/jquery.blockUI.js"></script>
<script type="text/javascript">	
$(document).ready(function(){
	 if('${orgMap.form_flag}' == 1){
		org_insert_set();
	} else if('${orgMap.form_flag}' == 2){
		if('${orgDetail.org_ph1}' != null && '${orgDetail.org_ph1}' != ''){
			$("#org_ph1 option[value=${orgDetail.org_ph1}]").prop("selected", true);
		}
		if('${orgDetail.fax_no1}' != null && '${orgDetail.fax_no1}' != ''){
			$("#fax_no1 option[value=${orgDetail.fax_no1}]").prop("selected", true);
		}
	} else if('${orgMap.form_flag}' == 3){
		if('${orgDetail.org_ph1}' != null && '${orgDetail.org_ph1}' != ''){
			$("#org_ph1 option[value=${orgDetail.org_ph1}]").prop("selected", true);
		}
		if('${orgDetail.fax_no1}' != null && '${orgDetail.fax_no1}' != ''){
			$("#fax_no1 option[value=${orgDetail.fax_no1}]").prop("selected", true);
		}
		alert("저장 되었습니다.");
		org_detail('${orgDetail.org_id}');
	} else if('${orgMap.form_flag}' == 4){
		if('${orgDetail.org_ph1}' != null && '${orgDetail.org_ph1}' != ''){
			$("#org_ph1 option[value=${orgDetail.org_ph1}]").prop("selected", true);
		}
			if('${orgDetail.fax_no1}' != null && '${orgDetail.fax_no1}' != ''){
		}
		$("#fax_no1 option[value=${orgDetail.fax_no1}]").prop("selected", true);
		alert("수정 되었습니다.");
		org_detail('${orgDetail.org_id}');
	}
});	
</script>
<input type="hidden" id="h_org_id" value="${orgMap.org_id}">
<!-- 메뉴상세정보  -->
<div id="menuDetail">
	<div class="titleDIV" style="margin-top:0.5%;">
		<span class="titleText">
			■ 기준정보 > <a style="cursor: pointer;" onclick="montgtActiveFormSubmit('/org','');"> 부서관리
			</a> > <span id="org_form_title">상세정보 (<span style="color: blue;">${orgDetail.org_nm}</span>)</span>
		</span>
	</div>
	<div style = "height:10px;"></div>
	<div class="commonDetail">
		<form action="${ctx}/orgForm" method="post" id="orgform">
	   	 <table class="commonDetailTable">
	   	 	<tr>
	   	 		<th style="color: red;">* 부서명</th>
	   	 		<td>
	   	 			<input type="text" id="org_nm" name="org_nm" value="${orgDetail.org_nm}" readonly="readonly">
	   	 			<input type="hidden" name="org_id" id="org_id" value="${orgDetail.org_id}">
	   	 		</td>	   	 
	   	 		<th> 대표명</th>
	   	 		<td>
		   	 		<input type="hidden" id="rep_emp_id" name="rep_emp_id" value="${orgDetail.rep_emp_id}">
		   	 		<input type="text" id="rep_emp_nm" name="rep_emp_nm" value="${orgDetail.rep_emp_nm}" readonly="readonly" style="background: white; width: 40%;">
					<input class="back_btn" id="repEmpSchBtn" type="button" value="대표검색" disabled="disabled">
	   	 		</td>
	   	 	</tr>
	   	 	<tr>
	   	 		<th> 전화번호</th>
	   	 		<td>
	   	 			<select id="org_ph1" name="org_ph1" disabled="disabled" style="font-size:10.5px;padding:0.4em 0.4em">
   	 					<option value="">==선택==</option>
	   	 				<option value="02">02</option>
						<option value="031">031</option>
						<option value="032">032</option>
						<option value="033">033</option>
						<option value="041">041</option>
						<option value="042">042</option>
						<option value="043">043</option>
						<option value="044">044</option>
						<option value="051">051</option>
						<option value="052">052</option>
						<option value="053">053</option>
						<option value="054">054</option>
						<option value="055">055</option>
						<option value="061">061</option>
						<option value="062">062</option>
						<option value="063">063</option>
						<option value="064">064</option>
						<option value="070">070</option>
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="019">019</option>
	   	 			</select>	   	 			
	   	 			<input id="org_ph2" type="text" name="org_ph2" value="${orgDetail.org_ph2}" maxlength="4" style="text-align: center;" readonly="readonly" onkeyup="numChk(this);"> -
	   	 			<input id="org_ph3" type="text" name="org_ph3" value="${orgDetail.org_ph3}" maxlength="4" style="text-align: center;" readonly="readonly" onkeyup="numChk(this);">
	   	 		</td>
	   	 		<th> 팩스번호</th>
	   	 		<td>	   	
	   	 			<select id="fax_no1" name="fax_no1" disabled="disabled" style="font-size:10.5px;padding:0.4em 0.4em">
   	 					<option value="">==선택==</option>
	   	 				<option value="02">02</option>
						<option value="031">031</option>
						<option value="032">032</option>
						<option value="033">033</option>
						<option value="041">041</option>
						<option value="042">042</option>
						<option value="043">043</option>
						<option value="044">044</option>
						<option value="051">051</option>
						<option value="052">052</option>
						<option value="053">053</option>
						<option value="054">054</option>
						<option value="055">055</option>
						<option value="061">061</option>
						<option value="062">062</option>
						<option value="063">063</option>
						<option value="064">064</option>
						<option value="070">070</option>
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="019">019</option>
	   	 			</select>	   	 			
	   	 			<input id="fax_no2" type="text" name="fax_no2" value="${orgDetail.fax_no2}" maxlength="4" style="text-align: center;" readonly="readonly" onkeyup="numChk(this);"> -
	   	 			<input id="fax_no3" type="text" name="fax_no3" value="${orgDetail.fax_no3}" maxlength="4" style="text-align: center;" readonly="readonly" onkeyup="numChk(this);">
	   	 		</td>
	   	 	</tr>
	   	 	<tr>
	   	 		<th> 활성화여부</th>
	   	 		<td colspan="3">
	   	 			<input type="radio" id="act_y" name="act_yn" value="Y" disabled="disabled"
	   	 				<c:if test="${orgDetail.act_yn == 'Y'}">
				   	 		checked="checked"
	   	 				</c:if>
	   	 			 >Y&nbsp;&nbsp;&nbsp;
	   	 			<input type="radio" id="act_n" name="act_yn" value="N" disabled="disabled"
	   	 				<c:if test="${orgDetail.act_yn == 'N'}">
				   	 		checked="checked"
	   	 				</c:if>
	   	 			>N
	   	 		</td>
	   	 	</tr>	   	 	
	   	 </table>
	   	 </form>
	   	 
	   	 <div class="listFootDiv" style="float: none; width: 100%;">
	   	 	<div id="org_am_btn_div">
				<input type="button" class="func_btn" id="org_add" onclick="org_insert('${orgMap.org_id}');"	value="추가">
				<input type="button" class="func_btn" id="org_mdfy" onclick="org_update();"	value="편집">			
				<input type="button" class="tr_btn" id="org_del" value="삭제">
			</div>
			<div id="org_asc_btn_div" style="display: none;">
				<input type="button" class="tr_btn" id="org_save" value="저장">
				<input type="button" class="func_btn" id="org_cancel"	value="취소">
			</div>
			<div id="org_msc_btn_div" style="display: none;">
				<input type="button" class="tr_btn" id="org_mdfy_save" value="저장">
				<input type="button" class="func_btn" id="org_mdfy_cancel" value="취소">
			</div>
	     </div>
   	 </div>
  </div>
     <!-- 모달 준비 -->
     <div id="repOrgUserList" align="center">
 		<div style="width: 100%; background-color: #ececec;" align="right">
	 		<input type="button" value="X" style="cursor: pointer;" onclick="modal_close();">
	 	</div>
		<div class="titleDIV" align="left">
			<span class="titleText">■ 사용자 검색</span>
		</div>
				<div class="sms_searchDiv">
					<input type="hidden" id="sch_flg" name="sch_flg" value="${sch_flg}">
					<span class="searchText"> 사용자ID </span>
						<input type="text" id="rep_user_id" name="rep_user_id" style="width: 20%;" onkeydown="repEnterSearch(event);">
					<span class="searchText"> 사용자명 </span>
						<input type="text" id="rep_user_nm" name="rep_user_nm" style="width: 20%;" onkeydown="repEnterSearch(event);">
					<input type="button" value="검색" class="tr_btn" style="margin-left: 0;" onclick="schRepEmpMoList(1, 1);">
				</div>
				<div id="smsModalList">
			 	 <table id="repUserTable" class="commonTable" style="width: 100%;">
			 	 	<thead>
			 	 		<tr>
			 	 			<th>사용자ID</th>
			 	 			<th>사용자명</th>
			 	 			<th>사용자구분</th>
			 	 		</tr>
			 	 	</thead>
			 	 	<tbody id="repEmpTbody">
			 	 	</tbody>
			 	 </table>
			 	 <div id="repUserPagingDiv" style="margin-top: 1%;">
		  	    </div>
		  	    <input type="hidden" id="h_rep_user_id">
		  	    <input type="hidden" id="h_rep_user_nm">
	   </div>
	</div>
