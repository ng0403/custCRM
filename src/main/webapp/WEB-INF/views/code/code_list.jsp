<%@ page   language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<c:set var="ctx" value="${pageContext.request.contextPath }" />

<script type="text/javascript" src="${ctx}/resources/common/js/code/code.js"></script> 
<%-- <script type="text/javascript" src="${ctx}/resources/common/js/code/code_detail.js"></script>  --%>
<script src="http://malsup.github.com/jquery.form.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$('#next_day_srch').datepicker();
 	});
</script>

<input type="hidden" id="ctx" value="${ctx}">
<input type="hidden" id="flg" value="${flg}">
  
<!-- code : code조회 -->
<div id="cupnManager">
	<div style="height:10px;"></div>
	<div class="titleDIV">
		<span class="titleText">
			 ■ 코드 > 코드 관리 
		</span>
	</div>
	<div style="height:10px;"></div>
	<div class="twoCommonList">
	    <form name="codeListForm" id="codeListForm" method="post" action="${ctx}/couponManager" >
		 	<div id="searchDiv">
	        	<table id="cupnSearchTable" class="commonTable">
					<tr style="background-color: white; cursor:default; border:0;">
						 <th style="width:5%;">코드번호</th>
						 <td style="width:15%;">
						    <input type="text" id="code_no_srch" name="code_no_srch" value="" style="width:80%" onkeypress="codeenterSearch(event);">
						 </td> 
						 <th style="width:5%;">코드</th>
						 <td style="width:15%;">
						    <input type="text" id="code_srch" name="code_srch" value="" style="width:80%" onkeypress="codeenterSearch(event);">
						 </td>
						  <th style="width:5%;">코드명</th>
						 <td style="width:15%;">
						    <input type="text" id="code_name_srch" name="code_name_srch" value="" style="width:80%" onkeypress="codeenterSearch(event);">
						 </td>
 		                 <td style="width: 12%;">
 		                 <div style="float:right">
		                 	<input type="button" value="조회" id="code_list_srch" onclick="codeSchList(); "    class="tr_btn" >
		                 	<input type="button" value="검색 초기화" id="code_cancel_srch" onclick="codeCancelList(); " class="tr_btn" >
		                 </div>	
		                 </td>
					</tr>
				</table>
			</div>
		</form>
		
		<!-- 엑셀 출력 -->
		<form id="codeExcelForm" name="codeExcelForm" method="post" style="width: 98%;">
			<table class="commonTable" id="codeManagerTabl">
		 	 	<thead>
		 	 		<tr>
	 	 	 			<th style="width: 7%;">코드번호</th> 
		 	 			<th style="width: 7%;">코드</th>
	 	 	 			<th style="width: 20%;">코드명</th>
		 	 			<th style="width: 10%;">등록일시</th> 
	 	 	 		</tr>
		 	 	</thead>
		 	 	<tbody id="code_list_tbody"> 
		 	 		<c:forEach items="${codeList}" var="list">
		 	 		<tr>
		 	 			<td style="text-align: left;" onclick="codeDetail('${list.code_no}','${list.code}');">${list.code_no}</td>
		 	 			<td style="text-align: left;" onclick="codeDetail('${list.code_no}','${list.code}');">${list.code}</td>
		 	 			<td style="text-align: left;" >
		 	 				<a href="#" onclick="codeDetail('${list.code_no}','${list.code}', '${codePageNum}');">${list.code_name}</a>
		 	 			</td>
		 	 			<td style="text-align: left;" onclick="codeDetail('${list.code_no}','${list.code}');">${list.create_date}</td>
		 	 			</tr>
		 	 		</c:forEach>
		 	 	</tbody>
		 	 </table>
   		</form>	
<!-- 		<div class="listFootDiv"> -->
<!--  		 	 <input type="button" class="func_btn" id="code_add"       value="단건등록" onclick="code_add();"> -->
<!-- 		 	 <input type="button" class="func_btn" id="code_add_multi" value="다건등록" onclick="codeExcelImportOpen();"> -->
<!-- 		 	 <input type="button" class="func_btn" id="exportBtn"      value="엑셀출력"  onclick="download_list_Excel('codeListForm','0');" > -->
<!-- 		 	 <input type="button" class="func_btn" id="excel_form_down" value="엑셀템플릿 출력"  onclick="download_list_Excel('codeListForm','1');" >	 -->
<!-- 		</div> -->
		
		<!-- code_detail -->
		
		
		<!-- 페이징 부분 -->
		<div class="pagingDiv" style="width: 95%;">
			<input type="hidden" id="endPageNum" value="${page.endPageNum}"/>
			<input type="hidden" id="startPageNum" value="${page.startPageNum}"/>
			<input type="hidden" id="codePageNum" value="${codePageNum}"/>
			<c:choose>
				<c:when test="${page.endPageNum == 0 || page.endPageNum == 1}">
					<a style="color: black; text-decoration: none;"> ◀ </a><input type="text" id="pageInput" value="${page.startPageNum}" readonly="readonly"/>  
					<a style="color: black; text-decoration: none;"> / 1</a>
					<a style="color: black; text-decoration: none;"> ▶ </a>
				</c:when>
				<c:when test="${codePageNum == page.startPageNum}">
					 ◀ <input type="text" id="pageInput" value="${page.startPageNum}" onkeypress="codePageNumInputEnter(event);"/>  
					<a style="cursor: pointer;" onclick="codeSchList('${page.endPageNum}');" id="pNum" > / ${page.endPageNum}</a>
					<a style="cursor: pointer;" onclick="codeSchList('${taskPageNum+1}');" id="pNum"> ▶ </a>
				</c:when>
				<c:when test="${codePageNum == page.endPageNum}">
					<a style="cursor: pointer;" onclick="codeSchList('${codePageNum-1}');" id="pNum"> ◀ </a>
					<input type="text" id="pageInput"  value="${page.endPageNum}" onkeypress="codePageNumInputEnter(event);"/> 
					<a style="cursor: pointer;" onclick="codeSchList('${page.endPageNum}');" id="pNum"> / ${page.endPageNum}</a>
					<a style="color: black; text-decoration: none;"> ▶ </a>
				</c:when>
				<c:otherwise>
					<a style="cursor: pointer;" onclick="codeSchList('${codePageNum-1}');" id="pNum" > ◀ </a>
					<input type="text" id="pageInput"  value="${codePageNum}" onkeypress="codePageNumInputEnter(event);"/>  
					<a style="cursor: pointer;" onclick="codeSchList('${page.endPageNum}');" id="pNum"> / ${page.endPageNum}</a>
					<a style="cursor: pointer;" onclick="codeSchList('${codePageNum+1}');" id="pNum"> ▶ </a>
				</c:otherwise>
			</c:choose>
		</div>
		
   </div>
</div>

<div style="height: 10px;"></div>
	<form action="${ctx}/codeInsert" method="post" id="codeInsertForm" name="codeInsertForm">
		<div class="twoCommonDetail">
			<table id="code_form_tbl" class="commonDetailTable">

				<tr>
					<th style="text-align: right; readonly: true">코드번호</th>
					<td>
						<input type="text" id="code_no" name="code_no" value="" style="width: 60%;" readonly="readonly">
					</td>
				</tr>
				<tr>	
					<th  style="text-align: right;">코드</th>
					<td>
						<input type="text" id="code" name="code" value="" readonly="readonly">
					</td>
				</tr>

				<tr>
					<th  style="text-align: right;">코드명</th>
					<td>
						<input type="text" id="code_name" name="code_name" value="" readonly="readonly">
					</td>
				</tr>
				<tr>
					<th  style="text-align: right;">표시여부</th>
					<td>
						<input type="radio" id="display_yn" name="display_yn" value="Y" disabled="disabled">Y
		   	 			<input type="radio" id="display_yn" name="display_yn" value="N" disabled="disabled"> N
					</td>
				</tr>
				<tr>
					<th  style="text-align: right;">상위코드번호</th>
					<td>
						<input type="text" id="par_code_no" name="par_code_no" value="" readonly="readonly" >
					</td>
				</tr>
			</table>
			
			<div style="height: 10px;"></div>
			
			<div class="listFootDiv" style="width: 100%;">
		 	 	<div id="code_btn_div" >
					<input type="button" class="func_btn" id="code_add"  value="추가" >
					<input type="button" class="func_btn" id="code_mdfy" value="편집" >
					<input type="button" class="tr_btn"   id="code_del"  value="삭제" onclick="code_del_save();">
				</div>
				<div id="code_add_btn_div" >
					<input type="button" class="tr_btn"   id="code_save"   value="저장" onclick="code_add_save();" >
					<input type="button" class="func_btn" id="code_cancel" value="취소">
				</div>
				<div id="code_update_btn_div" >
					<input type="button" class="tr_btn"   id="code_update_save"   value="저장" onclick="code_modify_save();">
					<input type="button" class="func_btn" id="code_update_cancel" value="취소">
				</div>
		     </div>
		</div>
	</form>
	
	
<!-- 다건등록 모달창 테스트 -->
<div id="multiInsertModalDiv" style="display: none;">
    <div style="width: 100%; height:7%; background-color: #ececec;" align="right">
 		<input type="button" value="X" id="popupBoxClose" onclick="popupClose();" >
 	</div>
 	
 	<form id="excelUploadForm" name="excelUploadForm" enctype="multipart/form-data" method="post" action="${ctx}/codeExcelUpload">
<!-- 	<form method='post' name='custListPopup' id='custListPopup'> -->
	<div id="multiInsertModalContent" style="margin: 0 1.5% 0 1.5%;">
		<div class="titleDIV" style="text-align: left; width: 100%;">
			<span class="titleText">■ 다건 등록(Excel Import)</span>
		</div>
		<div style="height:25px;"></div>
		<div id="multiInsertModalList" class="commonList">
			 	<table id="multiInsertModalTables" style="width: 100%;">
			 	 	<thead>
			 	 		<tr id="multiInsertTableHeader">
							<td style="width: 40%; text-align: right;">
								<input id="excelFile" type="file" name="excelFile" class="btn btn-default" style="float: center;"/>
							</td>
							<td>
								<input type="button" value="업로드" class="back_btn" style="float: right;" onclick="codeExcelCheck();"/> <!-- onclick="viewProdMenuList(1); -->
							</td>
			 	 		</tr>
			 	 	</thead>
			 	</table>
		</div>	
	</div>
	</form>
	<input type="hidden" id="h_nm_menu">
</div>
