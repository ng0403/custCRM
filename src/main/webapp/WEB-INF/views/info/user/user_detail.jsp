<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<script type="text/javascript" src="resources/common/js/jquery.blockUI.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var mode = "${mode}";
	// mode에 따라 화면구성
	if(mode=="detail"){
		user_detail_set();
	}else if(mode=="insert"){
		user_insert_set();
	}
	$("#scs_btn_div").delegate('#user_save','click',function(){
	 if(mode=="insert"){
		if($("#user_id").val()==""){
				alert("아이디를 입력해주세요.");
				$("#user_id").focus();
		}else if($("#user_nm").val()==""){
			alert("사용자명을 입력해주세요.");
			$("#user_nm").focus();
		}else if($("#pwd").val()==""){
			alert("패스워드를 입력해주세요.");
			$("#pwd").focus();
		}else if($("#user_type_cd").val()==""){
			alert("사용자 구분을 선택해주세요.");
			$("#user_type_cd").focus();
		}else if($("#cell_ph1").val()==""){
			alert("핸드폰 번호를 입력해주세요.");
			$("#cell_ph1").focus();
		}else if($("#cell_ph2").val()==""){
			alert("핸드폰 번호를 입력해주세요.");
			$("#cell_ph2").focus();
		}else if($("#cell_ph3").val()==""){
			alert("핸드폰 번호를 입력해주세요.");
			$("#cell_ph3").focus();
		}else if($("#org_id").val()==""){
			alert("부서를 선택해주세요.");
			$("#user_id").focus();
		}else if($("#auth_id_1").val()=="" && $("#auth_id_2").val()=="" && $("#auth_id_3").val()=="" && $("#auth_id_4").val()==""){
			alert("하나 이상의 권한을 선택 하셔야합니다.");
		}else{
			var chk = confirm("저장 하시겠습니까?");
			if(chk == true){
			var main_menu_id = $("#main_menu_id").val();
		    var Mainmenu_idInput = $('<input type="hidden" value="'+main_menu_id+'" name="menu_id">');
		    var mode_input = $('<input type="hidden" value="insert" name="modes">');

		    $("#userform").append(Mainmenu_idInput).append(mode_input);
			$("#userform").submit();
			}
		}
	 }else if($("#mode").val()=="update"){
			var chk = confirm("수정 하시겠습니까?");
			if(chk == true){
			var main_menu_id = $("#main_menu_id").val();
		 
			$("#user_id").prop("disabled",false);
		     var Mainmenu_idInput = $('<input type="hidden" value="'+main_menu_id+'" name="menu_id">');		
		     var mode_input = $('<input type="hidden" value="update" name="modes">');
			     	     
		    $("#userform").append(Mainmenu_idInput).append(mode_input);		 
			$("#userform").submit();
			}
	 }
	});

	$("#user_mdfy").click(function(){
		var chk = confirm("편집 하시겠습니까?");
		if(chk){
			user_update();
		}
	});	
});
</script>
<!-- 메뉴상세정보  -->
<div id="menuDetail">
	<div class="titleDIV" style="margin-top:0.5%;">
		<span class="titleText">
			■ 기준정보 > <a style="cursor: pointer;" onclick="montgtActiveFormSubmit('/user','');"> 사용자관리</a> > <span id="user_form_title">상세정보(<span style="color: blue;">${userVO.user_nm}</span>)</span>
		</span>
	</div>
	<div style = "height:10px;"></div>
	<div class="commonDetail">
		<form action="${ctx}/userinsert" method="post" id="userform">
	   	 <table class="commonDetailTable">
	   	 	<tr>
	   	 		<th style="color: red;">* 사용자ID</th>
	   	 		<td>
	   	 			<input type="text" id="user_id" name="user_id" value="${userVO.user_id}">	
	   	 		</td>
	   	 		<th style="color: red;">* 사용자명</th>
	   	 		<td><input type="text" id="user_nm" name="user_nm" value="${userVO.user_nm}"></td>
	   	 	</tr>
	   	 	<tr>
	   	 		<th style="color: red;">* 패스워드</th>
	   	 		<td><input type="password" id="pwd" name="pwd" value="${userVO.pwd}"></td>
	   	 		<th style="color: red;">* 사용자구분</th>
	   	 		<td>
	   	 			<select id="user_type_cd" name="user_type_cd" style="font-size:10.5px; padding:0.4em 0.4em;"">	   	 			
	   	 			<c:if test="${userVO.user_type_cd!=null}" var="user_type_cd">
   	 					<option value="${userVO.user_type_cd}">${userVO.user_type_nm}</option>	   	 				
   	 				</c:if>
   	 					<option value="">===선택===</option>
   	 				<c:forEach var="userType" items="${userType}">
   	 					<option value="${userType.user_type_cd}">${userType.user_type_nm}</option>	   	 				
   	 				</c:forEach>   	 					
	   	 			</select>	   	 			
	   	 		</td>
	   	 	</tr>
	   	 	<tr>	   	 		
	   	 		<th style="color: red;">* 핸드폰</th>
	   	 		<td>
	   	 			<select id="cell_ph1" name="cell_ph1" style="font-size:10.5px; padding:0.4em 0.4em;">
	   	 			<c:if test="${userVO.cell_ph1!=null}" var="cell_ph1">
   	 					<option value="${userVO.cell_ph1}">${userVO.cell_ph1}</option>	   	 				
   	 				</c:if>
   	 					<option value="">==선택==</option>
	   	 				<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
						<option value="012">012</option>
	   	 			</select>	   	 			
	   	 			<input id="cell_ph2" type="text" name="cell_ph2" style="width : 25%;" value="${userVO.cell_ph2}" maxlength="4" onkeyup="numChk(this);">-
	   	 			<input id="cell_ph3" type="text" name="cell_ph3" style="width : 25%;" value="${userVO.cell_ph3}" maxlength="4" onkeyup="numChk(this);">
	   	 		</td>
	   	 		<th> 집전화</th>
	   	 		<td>
	   	 			<select id="home_ph1" name="home_ph1" style="font-size:10.5px; padding:0.4em 0.4em;">
	   	 			<c:if test="${userVO.home_ph1!=null}" var="home_ph1">
   	 					<option value="${userVO.home_ph1}">${userVO.home_ph1}</option>	   	 				
   	 				</c:if>
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
	   	 			</select>	   	 			
	   	 			<input id="phinput" style="width: 25%" type="text" name="home_ph2" value="${userVO.home_ph2}" maxlength="4" onkeyup="numChk(this);">-
	   	 			<input id="phinput" style="width: 25%" type="text" name="home_ph3"  value="${userVO.home_ph3}" maxlength="4" onkeyup="numChk(this);">
	   	 		</td>
	   	 	</tr>
	   	 	<tr>
	   	 		<th> 회사전화</th>
	   	 		<td>
	   	 			<select id="com_ph1" name="com_ph1" style="font-size:10.5px; padding:0.4em 0.4em;">
	   	 			<c:if test="${userVO.com_ph1!=null}" var="com_ph1">
   	 					<option value="${userVO.com_ph1}">${userVO.com_ph1}</option>	   	 				
   	 				</c:if>
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
	   	 			</select>	   	 			
	   	 			<input id="phinput" style="width: 25%" type="text" name="com_ph2" value="${userVO.com_ph2}"  maxlength="4" onkeyup="numChk(this);">-
	   	 			<input id="phinput" style="width: 25%" type="text" name="com_ph3" value="${userVO.com_ph3}"  maxlength="4" onkeyup="numChk(this);">
	   	 		</td>
	   	 		<th> 이메일 </th>	   	 		
	   	 		<td>
	   	 			<div id="emailDiv" style="float : left;">
	   	 			<input id="phinput" type="text" name="email_id" value="${userVO.email_id}" style="width : 37%">@	   	 			
	   	 			<input id="phinput" type="text" name="email_dm" value="${userVO.email_dm}" style="width : 50%;">
	   	 			</div>
	   	 			<div class="userDetail" style="float : left;">
	   	 			</div>		
	   	 		</td>
	   	 	</tr>
	   	 	<tr>
	   	 		<th style="color: red;">* 부서</th>
	   	 		<td>
	   	 		<div style="float : left;">
		   	 		<input type="text" id="org_ids" name="org_ids" value="${userVO.org_id}" style="width: 37%;">
		   	 		<input type="text" id="org_nms" name="org_nms" value="${userVO.org_nm}" style="width: 50%;">
		   	 		<input type="hidden" id="org_id" name="org_id" value="${userVO.org_id}" style="width: 37%;">
		   	 		<input type="hidden" id="org_nm" name="org_nm" value="${userVO.org_nm}" style="width: 50%;">
	   	 		</div>
	   	 		<div class="userDetail"  style="float : left;">
				<input class="back_btn" type="button" id="orgSchBtn" value="부서검색">
				</div>	   	 		
	   	 		</td>	   	 		
	   	 		<th> 활성화여부</th>
	   	 		<td>
		   	 		<input type="text" id="phinput" name="act_yn1" value="${userVO.act_yn}" disabled="disabled">	   	 			
	   	 			<div class="userDetail">
	   	 			<input type="radio" id="act_y" name="act_yn" value="Y" checked="checked">Y
	   	 			<input type="radio" id="act_n" name="act_yn" value="N">N
	   	 			</div>
	   	 		</td>
	   	 	</tr>
	   	 	<tr>
	   	 		<th style="color: red;">* 권한명</th>
	   	 		<td colspan="3">
	   	 		<div style="float : left;width : 28.2%">
	   	 			<input type="hidden" id="auth_id" name="auth_id" value="${userVO.auth_id}">
	   	 			<input type="text" class="userAuthDetailList" readonly="readonly" id="auth_nm" name="auth_nm" style="width: 140%;background: white;"
	   	 			 value="${userVO.auth_nm}">		   	 			
   	 			</div>
   	 			<div class="userDetail" style="float : left;width : 28.2%;">
				<input class="sch_btn" type="button" style="padding : 7px;margin-left : 48%" id="auth_sch_btn" value="권한검색">
				</div>	  
	   	 		</td>
	   	 	</tr>   	 	
	   	 </table>
	   	 </form>
	   	 <div class="detailFootDiv">
	 	 	<div id="ams_btn_div">
				<input type="button" class="func_btn" id="user_add" onclick="user_insert()" value="추가">
				<input type="button" class="func_btn" id="user_mdfy" value="편집">
				<input type="button" class="tr_btn" id="user_detail_del" value="삭제">
			</div>
			<div id="scs_btn_div">
				<input type="button" class="tr_btn"   id="user_save" value="저장">
				<input type="button" class="func_btn" id="userCancelBtn" value="취소">
			</div>
			<div id="custtab_btn_div">
				<input type="hidden" id="mode" value="${mode}">
			</div>
    	</div>
   	 </div>
   	 
   	 
     
     <!-- 모달 준비 -->
     <div id="repUserOrgList" align="center" 
     	style=" cursor: default; width: 100%; height: 100%;">
 		<div style="width: 100%; background-color: #ececec;" align="right">
	 		<input type="button" value="X" style="cursor: pointer;" onclick="modal_close();">
	 	</div>
		<div class="titleDIV" style="text-align: left; margin-bottom: 5px; padding-left: 12px;">
			<span class="titleText">■ 부서 검색</span>
		</div>
		
		<div id="orgduserList">
			<div class="sms_searchDiv">
				<input type="hidden" id="sch_flg" name="sch_flg" value="${sch_flg}">
					<span class="searchText"> 부서ID </span>
						<input type="text" id="s_org_id" name="s_org_id" style="width: 20%;" onkeydown="orgEnterSearch(event);">
					<span class="searchText"> 부서명 </span>
						<input type="text" id="s_org_nm" name="s_org_nm" style="width: 20%;" onkeydown="orgEnterSearch(event);">
					<input type="button" value="검색" class="tr_btn" style="margin-left: 0;" onclick="schOrgList(1, 1);">
			</div>			
			<div id="userOrgList">
			 	 <table class="commonTable" id="orgMTable">
			 	 	<thead>
			 	 		<tr>			 	 		
			 	 			<th>부서ID</th>
			 	 			<th>부서명</th>			 	 			
			 	 		</tr>
			 	 	</thead>
			 	 	<tbody id="modOrgTbody">
<%-- 			 	 	<c:forEach items="${orgList}" var="orgList"> --%>
<!-- 						 	 		<tr id="m_org_list"> -->
<%-- 						 	 		    <td style="width : 245px;"><a href="#" class="mOrg_id">${orgList.org_id} --%>
<%-- 						 	 		    	<input type="hidden" class="modOrg_id" name="${orgList.org_id}" value="${orgList.org_id}"> --%>
<%-- 						 	 				<input type="hidden" class="modOrg_nm" name="${orgList.org_nm}" value="${orgList.org_nm}"> --%>
<!-- 						 	 		    </a></td> -->
<%-- 						 	 			<td class="muser_nm" style="text-align : left;width : 49%;">${orgList.org_nm}</td>						 	 			 --%>
<!-- 						 	 		</tr> -->
<%-- 					</c:forEach>			 	 	 --%>
			 	 	</tbody>
			 	 </table>
			 	 <div id="orgMPagingDiv" style="margin-top : 1%;">			 	 	
			 	 </div>
			 	 <input type="hidden" id="h_s_org_id">
		  	    <input type="hidden" id="h_s_org_nm">
				 </div>
			   </div>
		</div>
     <!-- 권한 모달 준비 -->
     <div id="userAuthList" align="center"
 		style=" cursor: default; width: 100%; height: 100%;">
 		<div style="width: 100%; background-color: #ececec;" align="right">
	 		<input type="button" value="X" style="cursor: pointer;" onclick="modal_close();">
	 	</div>
	 	<div id="authLeft" style="float:left; width: 43%;">
		<div class="titleDIV" style="text-align: left;margin-bottom: 5px; padding-left: 12px;">
			<span class="titleText">■ 전체 권한  리스트</span>
		</div>		
		<div id="allAuthList">		
			<div>
			 	 <table class="commonTable" id="modAuthLists">
			 	 	<thead>
			 	 		<tr>
			 	 			<th>권한ID</th>
			 	 			<th>권한명</th>			 	 			
			 	 		</tr>
			 	 	</thead>
			 	 </table>
			 	 <table class="commonTable" id="modAuthList" style="height:100px;">
			 	 	<tbody>
				 	 	<c:forEach items="${authList}" var="authList">
				 	 		<tr id="m_auth_list">
				 	 		    <td style="width: 149px;">
				 	 		    <a href="#" class="mAuth_id">${authList.auth_id}
				 	 		    	<input type="hidden" class="modAuth_id" name="${authList.auth_id}" value="${authList.auth_id}">
				 	 				<input type="hidden" class="modAuth_nm" name="${authList.auth_nm}" value="${authList.auth_nm}">
				 	 		    </a></td>
				 	 			<td class="mauth_nm" style="text-align : left;width : 149px;">${authList.auth_nm}</td>						 	 			
				 	 		</tr>
						</c:forEach>			 	 	
			 	 	</tbody>
			 	 </table>		 	 
			   </div>
		</div>
		</div>
		<div id="authCenter" style="float:left; width: 10%;">
			<br>
			<br>
			<br>
		</div>
		<div id="authRight" style="float:left; width: 43%;">
		<div class="titleDIV" style="text-align: left;margin-bottom: 5px; padding-left: 12px;">
			<c:if test="${userVO.user_id!=null}">
				<span class="titleText">■ '${userVO.user_id}'권한 리스트</span>
			</c:if>
			<c:if test="${userVO.user_id==null}">
				<span class="titleText">■ 추가 권한 리스트</span>
			</c:if>			
		</div>		
		<div id="authList">		
			<div>
				<form id="modAuth4">
			 	 <table class="commonTable" id="modUserAuthList">
			 	 	<thead>
			 	 		<tr>
			 	 			<th>권한ID</th>
			 	 			<th>권한명</th>
			 	 			<th style="width : 10%;">&nbsp;</th>		 	 			
			 	 		</tr>
			 	 	</thead>
			 	 	<tbody>
	 	 		    <c:forEach var="userAuthList" items="${userAuthList}">
						<tr id="m_auth_list">
			 	 		    <td>
			 	 		    	<a href="#" class="mUserAuth_id" style="width : 33%;">
			 	 		    	<input type="text" id="m_auth_id" name="m_auth_id"  readonly="readonly" style="border: 1px solid white;" value="${userAuthList.auth_id}">			 	 		    	
<%-- 			 	 		    	<input type="hidden" class="modAuth_id" id="auth_id" name="${userAuthList.auth_id}" value="${userAuthList.auth_id}"> --%>
<%-- 			 	 				<input type="hidden" class="modAuth_nm" id="auth_nm" name="${userAuthList.auth_nm}" value="${userAuthList.auth_nm}"> --%>
			 	 		    	</a>
			 	 		    </td>			 	 		    
			 	 			<td class="mauth_nm" style="width : 33%;">
			 	 				<input type="text" class="modAuth_nm" readonly="readonly" id="m_auth_nm" style="border: 1px solid white;" name="m_auth_nm" value="${userAuthList.auth_nm}">
			 	 			</td>
			 	 			<td>
			 	 				<span class="cancels" onclick="cancels(this);">x</span>
			 	 			</td>
						</tr>
			 	 	</c:forEach>
			 	 	</tbody>
			 	 </table>
			 	 </form>
				 <div>
				 <br>
					<div id="auth_btn_div" style="widht : 50%;float : right;">
						<input type="button" class="tr_btn"   onclick="modal_add()" id="authAdd" value="확인">
						<input type="button" class="func_btn" onclick="modal_close()" id="authEnd" value="취소">
					</div>
			    </div>
			   </div>
		   </div>
		</div>   
	</div>     
 </div>