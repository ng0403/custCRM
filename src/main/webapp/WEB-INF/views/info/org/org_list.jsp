<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript">
$(document).ready(function() {
	if('${orgChkDel_result}' == 1){
		alert("부서가 삭제되었습니다.");
		goOrgSubmit('/org', '');
	}
});
</script>
<input type="hidden" id="ctx" value="${ctx}">
<!-- 부서관리 리스트 -->
<div id="orgList">
	<div class="titleDIV" style="margin-top:0.5%;">
		<span class="titleText"> ■ 기준정보 > 부서관리</span> 
	</div>
	<div style = "height:10px;"></div>
	<div class="commonList">		
		<form action="${ctx}/org" method="post" id="orgform">	
		<div class="searchDiv">
			<span class="searchText"> 부서명 </span>
			<input type="text" id="infoSearchInput" name="org_nm" value="${org_nm}" style="margin-left: 0;" autofocus="autofocus">
			<input type="button" id="org_reset" value="검색 초기화" class="tr_btn" style="margin-left: 15px;float:right;">
			<input type="submit" value="조회" class="tr_btn" style="margin-left: 15px;float:right;">
		</div>
	 	</form>
	 	<form action="${ctx}/org" method="post" id="org_chk_list">
	 	 <table class="commonTable" style="border-bottom : 1px solid white;">
	 	 	<thead>
	 	 		<tr>
	 	 			<th style="width: 5%;"><input type="checkbox" id="orgListCheck"></th>
	 	 			<th style="width: 20%;">부서번호</th>
	 	 			<th style="width: 30%;">부서명</th>
	 	 			<th style="width: 15%;">대표명</th>
	 	 			<th style="width: 15%;">연락처</th>
	 	 			<th style="width: 15%;">팩스번호</th>
	 	 		</tr>
	 	 	</thead>
	 	 	<tbody>
	 	 	<c:if test="${orgList.size() != 0}">
	 	 		<c:forEach var="org" items="${orgList}">
	   	 		<tr>
	 	 			<td><input type="checkbox" id="org_chk" name="org_id" value="${org.org_id}" onclick="chkCancel();"></td>
	 	 			<td id="org_id" onclick="org_detail('${org.org_id}');" >
	 	 			 ${org.org_id}
	 	 			</td>
	 	 			<td style="text-align: left;" onclick="org_detail('${org.org_id}');">&nbsp;${org.org_nm}</td>
	 	 			<td style="text-align: left;" onclick="org_detail('${org.org_id}');">&nbsp;${org.rep_emp_nm}</td>
	 	 			<td style="text-align: right;" onclick="org_detail('${org.org_id}');">
	 	 			<c:if test="${org.org_ph1!=''}" var="org_ph1">
	 	 				${org.org_ph}
	 	 			</c:if>&nbsp;
	 	 			</td>
	 	 			<td style="text-align: right;" onclick="org_detail('${org.org_id}');">
	 	 			<c:if test="${org.fax_no1!=''}" var="fax_no1">
	 	 				${org.fax_no}
	 	 			</c:if>&nbsp;
	 	 			</td>
	 	 		</tr> 				
	   	 		</c:forEach>
	   	 	</c:if>   	 		
				<c:if test="${orgList.size() == 0}">
					<tr>
						<td style="height: 130px;background : white;" colspan="6"><b>데이터가 존재하지 않습니다.</b></td>					
					</tr>
				</c:if>
	 	 	</tbody>
	 	 </table>
	 	 </form>
	 	<div class="listFootDiv">
	 	 	<div>
				<input type="button" class="func_btn" id="org_add" onclick="org_insert('');" value="추가">
				<input type="button" class="tr_btn" id="org_chk_del" value="삭제">
			</div>
     	</div>
    	<div class="pagingDiv">
			<input type="hidden" id="endPageNum" value="${page.endPageNum}"/>
			<input type="hidden" id="startPageNum" value="${page.startPageNum}"/>
			<input type="hidden" id="PageNum" value="${pageNum}"/>
			<c:choose>
				<c:when test="${page.endPageNum == 1}">
					<a style="color: black;"> ◀ </a><input type="text" id="pageInput" class="monPageInput" value="${page.startPageNum}" onkeypress="pageNumInputEnter(event, '/org');"/>  
					<a style="color: black;"> / ${page.endPageNum}</a>
					<a style="color: black;"> ▶ </a>
				</c:when>
				<c:when test="${pageNum == page.startPageNum}">
					◀ <input type="text" id="pageInput" value="${page.startPageNum}" onkeypress="pageNumInputEnter(event, '/org');"/> /&nbsp;
					<a href="#" onclick="paging('${page.endPageNum}', '/org');" id="pNum" >${page.endPageNum}</a>
					<a href="#" onclick="paging('${pageNum+1}', '/org');" id="pNum"> ▶ </a>
				</c:when>
				<c:when test="${pageNum == page.endPageNum}">
					<a href="#" onclick="paging('${pageNum-1}', '/org');" id="pNum"> ◀ </a>
					<input type="text" id="pageInput" value="${page.endPageNum}" onkeypress="pageNumInputEnter(event, '/org');"/> /&nbsp;
					<a href="#" onclick="paging('${page.endPageNum}', '/org');" id="pNum">${page.endPageNum}</a> ▶
				</c:when>
				<c:otherwise>
					<a href="#" onclick="paging('${pageNum-1}', '/org');" id="pNum" > ◀ </a>
					<input type="text" id="pageInput" value="${pageNum}" onkeypress="pageNumInputEnter(event, '/org');"/> /&nbsp;
					<a href="#" onclick="paging('${page.endPageNum}', '/org');" id="pNum">${page.endPageNum}</a>
					<a href="#" onclick="paging('${pageNum+1}', '/org');" id="pNum"> ▶ </a>
				</c:otherwise>
			</c:choose>
		</div> 
   </div>
</div>