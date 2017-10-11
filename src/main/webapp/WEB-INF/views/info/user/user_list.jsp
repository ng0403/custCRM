<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript">
	
$(document).ready(function() {
	var me = '${message}';
	// 사용자 추가가 성공했을시 팝업
	if('${userInResult}' == 1){
// 		$("#userList").children().remove();
		if(me != ''){
			alert(me);
		}
// 		montgtActiveFormSubmit("/user",'');
	}

	if('${userInResult}' == 2){
// 		$("#userList").children().remove();
		if(me != ''){
			alert(me);
		}
		montgtActiveFormSubmit("/user",'');
	}
	
	//사용자리스트 삭제(여러개)
	if('${userDel_result}' != 0){
//	 	$("#userList").children().remove();
		alert("사용자가 삭제되었습니다.");
		goUserList();
	}	
});	
</script>
<input type="hidden" id="ctx" value="${ctx}">
<!-- 사용자권한 리스트 -->
<div id="userList">
	<div class="titleDIV" style="margin-top:0.5%;">
		<span class="titleText">
			■ 기준정보&nbsp;&nbsp;>&nbsp;&nbsp;사용자관리
		</span>
	</div>
	<div style = "height:10px;"></div>
	<div class="commonList">
	<form action="${ctx}/user" method="post" id="userform">			
		<div class="searchDiv">
			<span class="searchText"> 사용자ID </span>
			<input type="text" id="infoSearchInput" name="user_id" value="${user_id}" onkeydown="userSearchEnter(event);" style="margin-left: 0;" autofocus="autofocus">
						
			<span class="searchText"> 사용자명 </span>
			<input type="text" id="infoSearchInput" name="user_nm" value="${user_nms}" onkeydown="userSearchEnter(event);" style="margin-left: 0;">
			
			<span class="searchText"> 부서명 </span>
			<input type="text" id="infoSearchInput" name="org_nm" value="${org_nm}" onkeydown="userSearchEnter(event);" style="margin-left: 0;">
			<input type="button" value="검색 초기화" onclick="user_reset()" class="tr_btn" style="margin-left: 15px;float:right;">
			<input type="button" value="조회" onclick="user_search()" class="tr_btn" style="margin-left: 15px;float:right;">
		</div>
	</form>
	<form action="${ctx}/user" method="post" id="user_chk_list">
	 	 <table class="commonTable" style="border-bottom : 1px solid white;">
	 	 	<thead>
	 	 		<tr>
	 	 			<th style="width: 3%;"><input type="checkbox" id="userListCheck"></th>
	 	 			<th style="width: 10%;">사용자ID</th>
	 	 			<th style="width: 10%;">사용자명</th>
	 	 			<th style="width: 10%;">부서명</th>
	 	 			<th style="width: 23%;">이메일</th>
	 	 			<th style="width: 10%;">연락처</th>
	 	 			<th style="width: 10%;">권한</th>
	 	 			<th style="width: 9%;">사용자구분</th>
	 	 			<th style="width: 10%;">PW입력에러</th>
	 	 			<th style="width: 5%;">상태</th>
	 	 		</tr>
	 	 	</thead>
	 	 	<tbody>
	 	 	<c:if test="${userList.size() != 0}">
		 	 	<c:forEach var="user" items="${userList}">
	 	 		<tr>
	 	 			<td><input type="checkbox" id="user_chk" name="user_chk" value="${user.user_id}" onclick="chkCancel();"></td>
	 	 			<td style="text-align:left;" id="user_id" onclick="user_detail('${user.user_id}');">
	 	 				&nbsp;${user.user_id}
	 	 			</td>
	 	 			<td style="text-align:left;" onclick="user_detail('${user.user_id}');">&nbsp;${user.user_nm}</td>
	 	 			<td style="text-align:left;" onclick="user_detail('${user.user_id}');">&nbsp;${user.org_nm}</td>
	 	 			<td style="text-align:left;" onclick="user_detail('${user.user_id}');">
	 	 			&nbsp;${user.email_id}<c:if test="${user.email_id!=''}" var="email_id">@</c:if>${user.email_dm}
	 	 			</td>
	 	 			<td style="text-align:right;" onclick="user_detail('${user.user_id}');">
	 	 			<c:if test="${user.cell_ph1!=''}" var="cell_ph">${user.cell_ph}</c:if>&nbsp;
	 	 			</td>
	 	 			<td style="text-align:left;" onclick="user_detail('${user.user_id}');">&nbsp;${user.auth_nm}</td>
	 	 			<td style="text-align:left;" onclick="user_detail('${user.user_id}');">&nbsp;${user.user_type_nm}</td>
	 	 			<td onclick="user_detail('${user.user_id}');">${user.pwd_err_cnt}</td>
	 	 			<td onclick="user_detail('${user.user_id}');">${user.act_yn}</td>
	 	 		</tr>
	 	 		</c:forEach>	 	 		
			</c:if>
			<c:if test="${userList.size() == 0}">
					<tr>
						<td style="height: 130px;background : white;" colspan="9"><b>데이터가 존재하지 않습니다.</b></td>					
					</tr>
			</c:if>			
	 	 	</tbody>
	 	 </table>
	 </form>
	 	<div class="listFootDiv">
	 	 	<div>
				<input type="button" class="func_btn" id="user_add" onclick="userList_insert()" value="추가">
				<input type="button" class="tr_btn" id="user_chk_del" value="삭제">
			</div>
     	</div>
     	<div class="pagingDiv">
			<input type="hidden" id="endPageNum" value="${page.endPageNum}"/>
			<input type="hidden" id="startPageNum" value="${page.startPageNum}"/>
			<input type="hidden" id="PageNum" value="${pageNum}"/>
			<c:choose>
				<c:when test="${page.endPageNum == 1}">
					<a style="color: black; text-decoration: none;"> ◀ </a><input type="text" id="pageInput" class="monPageInput" value="${page.startPageNum}" onkeypress="pageNumInputEnter(event, '/user');"/>  
					<a style="color: black; text-decoration: none;"> / ${page.endPageNum}</a>
					<a style="color: black; text-decoration: none;"> ▶ </a>
				</c:when>
				<c:when test="${pageNum == page.startPageNum}">
					◀ <input type="text" id="pageInput" value="${page.startPageNum}" onkeypress="pageNumInputEnter(event, '/user');"/> /&nbsp;
					<a href="#" onclick="userPaging('${page.endPageNum}', '/user');" id="pNum" >${page.endPageNum}</a>
					<a href="#" onclick="userPaging('${pageNum+1}', '/user');" id="pNum"> ▶ </a>
				</c:when>
				<c:when test="${pageNum == page.endPageNum}">
					<a href="#" onclick="userPaging('${pageNum-1}', '/user');" id="pNum"> ◀ </a>
					<input type="text" id="pageInput" value="${page.endPageNum}" onkeypress="pageNumInputEnter(event, '/user');"/> /&nbsp;
					<a href="#" onclick="userPaging('${page.endPageNum}', '/user');" id="pNum">${page.endPageNum}</a> ▶
				</c:when>
				<c:otherwise>
					<a href="#" onclick="userPaging('${pageNum-1}', '/user');" id="pNum" > ◀ </a>
					<input type="text" id="pageInput" value="${pageNum}" onkeypress="pageNumInputEnter(event, '/user');"/> /&nbsp;
					<a href="#" onclick="userPaging('${page.endPageNum}', '/user');" id="pNum">${page.endPageNum}</a>
					<a href="#" onclick="userPaging('${pageNum+1}', '/user');" id="pNum"> ▶ </a>
				</c:otherwise>
			</c:choose>
		</div>    	
   </div>
</div>