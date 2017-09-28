<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript" src="/resources/common/js/lead/lead.js"></script> 
<script src="http://malsup.github.com/jquery.form.js"></script>
<script type="text/javascript">
 $(document).ready(function(){
	$('#contact_day_srch').datepicker();

 });
</script>

<input type="hidden" id="ctx" value="${ctx}">
<input type="hidden" id="flg" value="${flg}">
<input type="hidden" id="cust_lead_no" value="${cust_lead_no}">
<input type="hidden" id="session" value="${session}">
<!-- 쿠폰관리 : 쿠폰목록조회 -->
<div id="cupnManager">
	<div style="height:10px;"></div>
	<div class="titleDIV">
		<c:if test="${cust_lead_no != null}">
			<span class="titleText"> ■ 고객 > <a style="cursor: pointer;" onclick="lcustList('1');"> 고객관리 </a> > <a style="cursor: pointer;" onclick="lcustDetail('${cust_lead_no}');"> 고객 상세정보 </a> > 고객리드이력 </span>
		</c:if>
		<c:if test="${cust_lead_no == null}">
			<span class="titleText">
				 ■ 고객리드 > 고객리드 관리 
			</span>
		</c:if>
	</div>
	<div style="height:10px;"></div>
	<div class="commonList">
 
	    <form name="leadListForm" id="leadListForm" method="post" action="${ctx}/couponManager" >
		 	
		 	<div id="searchDiv">
	        	<table id="cupnSearchTable" class="commonTable">
					<tr style="background-color: white; cursor:default; border:0;">
						 <th style="width:5%;">리드번호</th>
						 <td style="width:15%;">
						    <input type="text" id="lead_no_srch" name="lead_no_srch" value="" style="width:80%" onkeypress="leadEnterSearch(event);">
						 </td>
						  <th style="width:5%;">리드명</th>
						 <td style="width:15%;">
						    <input type="text" id="lead_name_srch" name="lead_name_srch" value="" style="width:80%" onkeypress="leadEnterSearch(event);">
						 </td>
						 <th style="width:5%;">고객명</th>
						 <td style="width:15%;">
						    <input type="text" id="cust_name" name="cust_name" value="" style="width:80%" onkeypress="leadEnterSearch(event);">
						 </td> 
					  
 		                 <td style="width: 12%;">
 		                 <div style="float:right">
		                 	<input type="button" value="조회" id="lead_list_srch" onclick="searchKeyword();" class="tr_btn" style="margin-left: 0;">
		                    <input type="button" value="검색 초기화" id="lead_srch_reset" onclick="srch_reset();" class="tr_btn" style="margin-left: 0;"> 
		                 </div>
		                 </td>
					</tr>
					<tr>
					 			 
						 <th style="width:5%;">담당자명</th>
						 <td style="width:15%;">
						    <input type="text" id="emp_name" name="emp_name" value="" style="width:80%" onkeypress="leadEnterSearch(event);">
						 </td>
					
						 <th style="width:5%;">접촉할 일자</th>
						 <td style="width:25%;">
							<input type="text" name="contact_day_srch" placeholder ="접촉일자" id="contact_day_srch" value="" class="expt_fin_d" 
							 readonly="readonly" style="width : 35%; text-align: center; cursor: pointer;">
 						 </td>
						 
						  <th style="width:5%;">순위</th>
						 <td style="width:15%;">
						    <input type="text" id="rank_cd" name="rank_cd" value="" style="width:80%" onkeypress="leadEnterSearch(event);">
						 </td>
					
					</tr>
				</table>
			</div>
		</form>
		<!-- 엑셀 출력 -->
		<form id="leadExcelForm" name="leadExcelForm" method="post"></form>
	 	 <table class="commonTable" id="cupnManagerTabl">
	 	 	<thead>
	 	 		<tr>
	 	 			<th style="width: 10%;">리드번호</th>
 	 	 			<th style="width: 10%;">리드명</th>
 	 	 			<th style="width: 10%;">고객번호</th> 
	 	 			<th style="width: 10%;">고객명</th> 
	 	 			<th style="width: 10%;">전화번호</th>
	 	 			<th style="width: 10%;">담당자명</th>
	 	 			<th style="width: 15%;">접촉할일자</th>
	 	 			<th style="width: 10%;">순위</th>
	 	 			<th style="width: 15%;">등록일시</th> 
 	 	 		</tr>
	 	 	</thead>
	 	 	<tbody id="lead_list_tbody"> 
	 	 		<c:forEach items="${lead_list}" var="list">
	 	 		<tr>
	 	 			<td style="text-align: left;" >${list.lead_no}</td>
	 	 			<td style="text-align: left;" >
	 	 			<a href="#" onclick="leadDetail('${list.lead_no}', '${pageNum}');" id="${list.lead_no}">${list.lead_name}</a>
	 	 			</td>
	 	 			<td style="text-align: left;" >${list.cust_no}</td>
	 	 			<td style="text-align: left;" >${list.cust_name}</td>
	 	 			<td style="text-align: left;" >${list.phone}</td>
	 	 			<td style="text-align: left;" >${list.user_nm}</td>
	 	 			<td style="text-align: left;" >${list.contact_day}</td>
	 	 			<td style="text-align: left;" >${list.rank_cd}</td>
	 	 			<td style="text-align: left;" >${list.create_date}</td>
	 	 			</tr>
	 	 		</c:forEach>
 
	 	 	</tbody>
	 	 </table>
   	
		<div class="listFootDiv">
			<c:if test="${ cust_lead_no == null }">
	 		 	<input type="button" class="func_btn" id="lead_add" value="단건등록" onclick="lead_add();">
			 	<input type="button" class="func_btn" id="lead_add_multi" value="다건등록" onclick="leadExcelImportOpen();">
			 	<input type="button" class="func_btn" id="exportBtn"      value="엑셀출력"  onclick="download_list_Excel('leadListForm','0');" >	
				<input type="button" class="func_btn" id="excel_form_down" value="엑셀템플릿출력"  onclick="download_list_Excel('leadListForm','1');" >
			</c:if>
			<c:if test="${ cust_lead_no != null }">
			 	<input type="button" class="func_btn" id="exportBtn"      value="뒤로"  onclick="lcustDetail('${cust_lead_no}');" >	
			 	<input type="button" class="func_btn" id="exportBtn"      value="엑셀출력"  onclick="download_list_Excel('leadListForm','0');" >	
				<input type="button" class="func_btn" id="excel_form_down" value="엑셀템플릿출력"  onclick="download_list_Excel('leadListForm','1');" >
			</c:if>
		</div>
	 	<div class="pagingDiv">
			<input type="hidden" id="endPageNum" value="${page.endPageNum}"/>
			<input type="hidden" id="startPageNum" value="${page.startPageNum}"/>
			<input type="hidden" id="pageNum" value="${pageNum}"/>
			<c:choose>
				<c:when test="${page.endPageNum == 0 || page.endPageNum == 1}">
					<a style="color: black; text-decoration: none;"> ◀ </a><input type="text" id="pageInput" value="${page.startPageNum}" readonly="readonly"/>  
					<a style="color: black; text-decoration: none;"> / 1</a>
					<a style="color: black; text-decoration: none;"> ▶ </a>
				</c:when>
				<c:when test="${pageNum == page.startPageNum}">
					 ◀ <input type="text" id="pageInput" value="${page.startPageNum}"  onkeypress="leadPageNumInputEnter(event);"/>  
					<a style="cursor: pointer;" onclick="searchKeyword('${page.endPageNum}');" id="pNum" > / ${page.endPageNum}</a>
					<a style="cursor: pointer;" onclick="searchKeyword('${pageNum+1}');" id="pNum"> ▶ </a>
				</c:when>
				<c:when test="${pageNum == page.endPageNum}">
					<a style="cursor: pointer;" onclick="searchKeyword('${pageNum-1}');" id="pNum"> ◀ </a>
					<input type="text" id="pageInput"  value="${page.endPageNum}" onkeypress="leadPageNumInputEnter(event);"/> 
					<a style="cursor: pointer;" onclick="searchKeyword('${page.endPageNum}');" id="pNum"> / ${page.endPageNum}</a>
					<a style="color: black; text-decoration: none;"> ▶ </a>
				</c:when>
				<c:otherwise>
					<a style="cursor: pointer;" onclick="searchKeyword('${pageNum-1}');" id="pNum" > ◀ </a>
					<input type="text" id="pageInput"  value="${pageNum}" onkeypress="leadPageNumInputEnter(event);"/>  
					<a style="cursor: pointer;" onclick="searchKeyword('${page.endPageNum}');" id="pNum"> / ${page.endPageNum}</a>
					<a style="cursor: pointer;" onclick="searchKeyword('${pageNum+1}');" id="pNum"> ▶ </a>
				</c:otherwise>
			</c:choose>
		</div>
   </div>
</div>


<!-- 다건등록 모달창 테스트 -->
<div id="multiInsertModalDiv" style="display: none;">
    <div style="width: 100%; height:7%; background-color: #ececec;" align="right">
 		<input type="button" value="X" id="popupBoxClose" onclick="popupClose();" >
 	</div>
 	
 	<form id="excelUploadForm" name="excelUploadForm" enctype="multipart/form-data" method="post" action="${ctx}/opptyExcelUpload">
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
								<input type="button" value="업로드" class="back_btn" style="float: right;" onclick="leadExcelCheck();"/> <!-- onclick="viewProdMenuList(1); -->
							</td>
			 	 		</tr>
			 	 	</thead>
			 	</table>
		</div>	
	</div>
	</form>
	<input type="hidden" id="h_nm_menu">
</div>