<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath }" />    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="/resources/common/js/lead/lead.js"></script> 
<script type="text/javascript" src="/resources/common/js/lead/leadItem.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script type="text/javascript">


$(document).ready(function(){
	$('#contact_day').datepicker();

 });	
</script>

<input type="hidden" id="ctx" value="${ctx}">
<input type="hidden" id="flg" value="${flg}">
<input type="hidden" id="cust_lead_no" value="${cust_lead_no}">

<!-- 가망고객 -->
<div id="coupon_detail">
	<div style="height:10px;"></div>
	
	<c:if test="${flg == 0 }">
 		<div class="titleDIV" id="lead_detail_title">
		<span class="titleText">
		    ■ 고객리드 > <a style="cursor: pointer;" onclick="leadlist('1');"> 고객리드관리</a> > <span id="coupon_form_title">고객리드 상세정보</span>
		</span>
	</div>   
	</c:if>
	<c:if test="${flg == 1 }">
 		<div class="titleDIV" id ="lead_insert_title">
			<span class="titleText">
			    ■ 고객리드 > <a style="cursor: pointer;" onclick="leadlist('1');"> 고객리드관리</a> > <span id="coupon_form_title">고객리드 추가</span>
			</span>
		</div>   
	</c:if> 
 		<div class="titleDIV" id="lead_update_title">
		<span class="titleText">
		    ■ 고객리드 > <a style="cursor: pointer;" onclick="leadlist('1');"> 고객리드관리</a> > <span id="coupon_form_title">고객리드 수정</span>
		</span>
	</div>   
	
	<c:if test="${flg == 007 }">
 		<div class="titleDIV" id ="lead_insert_title">
			<span class="titleText">
			    ■ 고객 > <a style="cursor: pointer;" onclick="lcustList('1');"> 고객관리</a> > <a style="cursor: pointer;" onclick="lcustDetail('${cust_lead_no}');"> 고객 상세정보 > </a> <a style="cursor: pointer;" onclick="leadCustList('${cust_lead_no}');"> 고객리드관리 > </a><span id="coupon_form_title">  고객리드 상세정보</span> 
			</span>
		</div>   
	</c:if>
 
	
	<div style="height:10px;"></div>
	<form role="form" name="lead_single_add" id="lead_single_add" method="post" action="${ctx}/lead_single_add" >	
	<div class="commonDetail">
	<table id="coupon_form_tbl" class="commonDetailTable">
		<tr>
 			<th id="impTh" style="text-align:right;" >*리드번호</th>
			<td>
			<c:if test= "${ leadNoIndex.lead_no != null }">
			<input type="text" id="lead_no" name="lead_no"  disabled="disabled" value="${leadNoIndex.lead_no}">
 			</c:if>
 			<c:if test= "${ leadNoIndex.lead_no == null }">
 			<input type="text" id="lead_no" name="lead_no"  readonly="readonly" value="${detail.lead_no}">
 			</c:if>
 			</td>
			<th id="impTh" style="text-align:right;">*리드명</th>
			<td>
 			 <input type="text" id="lead_name" name="lead_name"  value="${detail.lead_name}" >
  			</td>
		</tr>
		
		<tr>
			<th id="impTh" style="text-align:right;">*고객</th>
			<td>
			<input type="hidden" id="cust_no" name="cust_no"  value="${detail.cust_no}" >
			<input type="text" id="cust_name" readonly="readonly" name="cust_name" value="${detail.cust_name}">
			<input type="button" id="emp_list_pop" value="고객" onclick="custSchPopupOpen();"/>
 			</td>
			<th style="text-align:right;">담당자</th>
			<td> 
	       <input type="text" id="emp_name" name="user_nm" readonly="readonly"  value="${detail.user_nm}">
	       <input type="hidden" id="emp_no" name="emp_no" readonly="readonly" value="${detail.emp_no}">
 		 <input type="button" id="cust_list_pop" value="담당자" onclick="empSchPopupOpen();">
 			</td>
		</tr>
		<tr>
			<th id="impTh" class="discount_cost" style="text-align:right;">*접촉할일자
			</th>
			<td id="td_disc_type">	
				<input name="contact_day" id="contact_day" type="text" value="${detail.contact_day}" class="expt_fin_d" 
							 readonly="readonly" placeholder ="접촉일자" style="text-align: center; cursor: pointer;">
			</td>
			<th id="impTh" class="discount_cost" style="text-align:right;">*상태</th>
			<td> 
				   <select id="lead_status_cd_sel" name="lead_status_cd" 
									style="margin-left: 0; width: 70%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;">
								<option value="">선택해 주십시오</option>
									<c:forEach var="status" items="${ leadstatuscode }">
										<c:if test="${ detail.lead_status_cd == status.code }">
											<option selected="selected" value="${ status.code }">${ status.code_name }</option>
										</c:if>
										<c:if test="${ detail.lead_status_cd != status.code }">
											<option value="${ status.code }">${ status.code_name }</option>
										</c:if>
									</c:forEach>
				 </select>
				   
 			</td>
		</tr>
		<tr>
			<th style="text-align:right;">포기사유</th>
			<td>	
			<input type="text" id="reason_cd" name="reason_cd" value="${detail.reason_cd}">
 			</td>
 			<th>가능성</th>
 			<td>
 			     <select id="possibility_cd_sel" name="possibility_cd" 
									style="margin-left: 0; width: 70%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;">
								<option value="">선택해 주십시오</option>
									<c:forEach var="status" items="${ opptycd }">
										<c:if test="${ detail.possibility_cd == status.code }">
											<option selected="selected" value="${ status.code }">${ status.code_name }</option>
										</c:if>
										<c:if test="${ detail.possibility_cd != status.code }">
											<option value="${ status.code }">${ status.code_name }</option>
										</c:if>
									</c:forEach>
				 </select>
 			
 			</td>
 		</tr>
		<tr>
			<th  style="text-align:right;">특이 사항</th>
			<td colspan="3">
			<input type="text" id="remark_cn" name="remark_cn"  value="${detail.remark_cn}">
 			</td>
 		</tr>
 	</table>
 	
	<div class="listFootDiv">
	
	<c:if test="${flg == 0 }">
	 	 <div id="lead_detail_div"  style="width: 587px; float: left; display: -webkit-box; ">
	 	 	<div style="width: 26%; ">
		 	 	<input type="button" class="func_btn" id="lead_update" value="편집" onclick="lead_modify();">
		 	 	<input type="button" class="tr_btn" id="lead_delete" value="삭제" onclick="lead_remove();">
		 	 	<input type="button" class="func_btn" id="lead_single_cancel" value="취소" onclick="lead_cancel('${PageNum}');"> 
	 	 	</div>
	 	 	<div style=" border-left : 3px solid #131230; padding-left: 10px;">
		 	 	<input type="button" class="func_btn" id="lead_counsel" value="상담이력" style="border: 1px solid #006600;" onclick="lead_counsel_read('${detail.cust_no}', '${PageNum}');">
	 	 	</div>
	 	 </div> 
	 </c:if>
	
	<c:if test="${flg == 1 }">
	 	 <div id="lead_single_add_div">
	 	 	<input type="button" class="tr_btn" id="lead_single_add" value="저장" onclick="lead_single_save();">
	 	 	<input type="button" class="tr_btn" id="lead_reset_btn" value="초기화" onclick="lead_reset();">
	 	    <input type="button" class="func_btn" id="lead_single_cancel" value="취소" onclick="lead_cancel('${PageNum}');">
	 	 </div> 
	 </c:if>
	  
	 	  <div id="lead_update_div">
	 	 	<input type="button" class="tr_btn" id="lead_single_add" value="저장" onclick="lead_modify_save();">
	 	 	<input type="button" class="tr_btn" id="lead_delete" value="삭제" onclick="lead_remove();">
		    <input type="button" class="func_btn" id="lead_single_cancel" value="취소" onclick="lead_cancel('${PageNum}');">
	 	 <input type="button" class="func_btn" id="lead_counsel" value="상담이력" onclick="lead_counsel_read('${detail.cust_no}', '${PageNum}');">
	 	 </div>
 
    </div>
	</div>
  </form>
</div>	

 <br/>
	<div style="width:98%" >	
	<div class="titleDIV" id="lead_detail_title">
			<span class="titleText" > ■ <span id="coupon_form_title">관심 상품</span>
		    	  <span style="font-size: 8px;">(삭제할 상품을 체크한 후 삭제버튼을 누르고 저장을 눌러주세요.)</span>
			</span>
		</div>	
		<div style="float:right" >
			<c:if test="${ cust_lead_no == null }">
				<input type="button" class="func_btn" id="leadItem_add" onclick="leadItemAdd();" value="추가">
				<input type="button" class="tr_btn" id="leadItem_save" onclick="leadItemInsert();" value="저장" >
				<input type="button" class="tr_btn" id="leadItem_save" onclick="leadItemDelte();" value="삭제" >
			</c:if>
			<c:if test="${ cust_lead_no != null }">
<!-- 				<input type="button" class="func_btn" id="leadItem_add" onclick="leadItemAdd();" value="추가"> -->
<!-- 				<input type="button" class="tr_btn" id="leadItem_save" onclick="leadItemInsert();" value="저장" > -->
<!-- 				<input type="button" class="tr_btn" id="leadItem_save" onclick="leadItemDelte();" value="삭제" > -->
			</c:if>
		</div>		
		<div style="height:10px;"></div>
		<table class="commonTable" id="leadItemTable">
			<thead>
	 	 		<tr>
	 	 			<th rowspan="2" style="width: 2%; text-align: center;">
	 	 				<input id="leadItemChk" type="checkbox" onclick="actAllChk(this);" />
	 	 			</th>
	 	 			<th >대분류</th>
	 	 			<th >중분류</th>
	 	 			<th >소분류</th>
	 	 			<th >수량</th>
	 	 			<th >단가</th>
	 	 			<th >총금액</th> 
	 	 		</tr>
	 	 	</thead>
	 	 	
	 	 	<tbody id="lead_item_list_tbody">
	 	 		<c:forEach items="${ itemList }" var="itemList">
	 	 			<tr class="lead_item_list_tr">
	 	 				<td>
	 	 					<input type="checkbox" class="del_chk" name="del_chk">
	 	 				</td>
<!-- 	 	 				<td> -->
<%-- 	 	 					<input type="checkbox" class="del_chk" name="del_chk" onclick="actChkCancel();> --%>
<!-- 	 	 				</td> -->
		 	 			<td style="text-align: left;" >
		 	 				<input type="hidden" class="main_cate_cd" name="main_cate_cd" value="${ itemList.main_cate_cd }">
		 	 				<input type="text"  class="main_cate_name" name="main_cate_name" value="${ itemList.main_cate_name }" readonly="readonly">
		 	 			</td>
	 		 			<td style="text-align: left;" >
	 		 				<input type="hidden" class="mid_cate_cd" id="mid_cate_cd" name="mid_cate_cd" value="${ itemList.mid_cate_cd }">
	 		 				<input type="text" class="mid_cate_name" id="mid_cate_name" name="mid_cate_name" value="${ itemList.mid_cate_name }" readonly="readonly" onclick="midCatPopup();">
	 		 			</td>
	 		 			<td style="text-align: left;" >
							<input type="hidden" class="small_cate_cd" name="small_cate_cd" value="${ itemList.small_cate_cd }">	 		 			
	 		 				<input type="text" class="small_cate_name" name="small_cate_name" value="${ itemList.small_cate_name }" readonly="readonly" onclick="smallCatPopup();">
	 		 			</td>
	 		 			<td style="text-align: left;" >
	 		 				<input type="text" class="qty" name="qty" value="${ itemList.qty }">
	 		 			</td>
	 		 			<td style="text-align: right;" >
	 		 				<input type="text" class="list_price" name="list_price" value="${ itemList.list_price }">
	 		 			</td>
	 		 			<td style="text-align: right;" >
	 		 				<input type="text" class="total_price" name="total_price" value="${ itemList.total_price }" readonly="readonly"> <!-- 총금액 -->
	 		 			</td> 
	 	 			</tr>
	 	 		</c:forEach>
	 	 	</tbody>
		</table>
 </div>

<!-- 고객 모달창 시작 -->
<div id="custListModalDiv" style="display: none;">
    <div style="width: 100%; height:7%; background-color: #ececec;" align="right">
 		<input type="button" value="X" id="popupBoxClose" onclick="popClose();" >
 	</div>
	<form method='post' name='custListPopup' id='custListPopup'>
	<div id="custListModalContent" style="margin: 0 1.5% 0 1.5%;">
		<div class="titleDIV" style="text-align: left; width: 100%;">
			<span class="titleText">■ 담당자 리스트</span>
		</div>
		<div id="custModalList" class="commonList">
			 	<table id="custListModalTables" style="width: 100%;">
			 	 	<thead>
			 	 		<tr id="custListTableHeader">
			 	 			<th style="width: 20%; text-align: right; padding-right: 1%;">고객명 : </th>
			 	 			<td style="width: 40%;">
			 	 			    <input type="text" id="s_cust_name" name="s_cust_name" style="width: 70%;" maxlength="100"/>&nbsp;&nbsp;
							</td>
							<td style="width: 40%; text-align: right;">
								<input type="button" value="검색" class="back_btn" style="float: right;" onclick="viewCustList(1);"/> <!-- onclick="viewProdMenuList(1); -->
							</td>			
			 	 		</tr>
			 	 	</thead>
			 	</table>
			 	<br>
			 	<table class="commonTable">
			 		<thead>
			 			<tr id="empListTableHeader">
						  <th width="45%">고객번호</th>
						  <th width="45%">고객명</th>
						</tr>	
			 		</thead>
			 	 	<tbody id="custListTbody"></tbody>
				</table>
			<!-- 페이징 DIV -->
			<div class="pagingDiv" id="custPopupPagingDiv" style="width: 100%; text-align: center;"></div>
		</div>	
	</div>
	</form>
	<input type="hidden" id="h_nm_menu">
</div>



<!-- 담당자 모달창 시작 -->
<div id="empListModalDiv" style="display: none;">
    <div style="width: 100%; height:7%; background-color: #ececec;" align="right">
 		<input type="button" value="X" id="popupBoxClose" onclick="popClose();" >
 	</div>
	<form method='post' name='custListPopup' id='custListPopup'>
	<div id="empListModalContent" style="margin: 0 1.5% 0 1.5%;">
		<div class="titleDIV" style="text-align: left; width: 100%;">
			<span class="titleText">■ 담당자 리스트</span>
		</div>
		<div id="empModalList" class="commonList">
			 	<table id="empListModalTables" style="width: 100%;">
			 	 	<thead>
			 	 		<tr id="empListTableHeader">
			 	 			<th style="width: 20%; text-align: right; padding-right: 1%;">고객명 : </th>
			 	 			<td style="width: 40%;">
			 	 			    <input type="text" id="s_emp_name" name="s_emp_name" style="width: 70%;" maxlength="100"/>&nbsp;&nbsp;
							</td>
							<td style="width: 40%; text-align: right;">
								<input type="button" value="검색" class="back_btn" style="float: right;" onclick="viewEmpList(1);"/> <!-- onclick="viewProdMenuList(1); -->
							</td>			
			 	 		</tr>
			 	 	</thead>
			 	</table>
			 	<br>
			 	<table class="commonTable">
			 		<thead>
			 			<tr id="empListTableHeader">
						  <th width="45%">직원번호</th>
						  <th width="45%">직원명</th>
						</tr>	
			 		</thead>
			 	 	<tbody id="empListTbody"></tbody>
				</table>
			<!-- 페이징 DIV -->
			<div class="pagingDiv" id="empPopupPagingDiv" style="width: 100%; text-align: center;"></div>
		</div>	
	</div>
	</form>
	<input type="hidden" id="h_nm_menu">
</div>

<!-- 대분류 모달창 시작 -->
<div id="mainCateListModalDiv" style="display: none;">
    <div style="width: 100%; height:7%; background-color: #ececec;" align="right">
 		<input type="button" value="X" id="popupBoxClose" onclick="popupClose();" >
 	</div>
	<form method='post' name='custListPopup' id='custListPopup'>
	<div id="mainCateListModalContent" style="margin: 0 1.5% 0 1.5%;">
		<div class="titleDIV" style="text-align: left; width: 100%;">
			<span class="titleText">■ 대분류 리스트</span>
		</div>
		<div id="mainCateModalList" class="commonList">
			 	<table id="mainCateListModalTables" style="width: 100%;">
			 	 	<thead>
			 	 		<tr id="mainCateListTableHeader">
			 	 			<th style="width: 20%; text-align: right; padding-right: 1%;">대분류명 : </th>
			 	 			<td style="width: 40%;">
			 	 			    <input type="text" id="s_main_cate_name" name="s_main_cate_name" style="width: 70%;" maxlength="100"/>&nbsp;&nbsp;
							</td>
							<td style="width: 40%; text-align: right;">
								<input type="button" value="검색" class="back_btn" style="float: right;" onclick="viewMainCateList(1);"/>
							</td>			
			 	 		</tr>
			 	 	</thead>
			 	</table>
			 	<br>
			 	<table class="commonTable">
			 		<thead>
			 			<tr id="mainCateListTableHeader">
						  <th width="45%">대분류코드</th>
						  <th width="45%">대분류명</th>
						</tr>	
			 		</thead>
			 	 	<tbody id="mainCateListTbody"></tbody>
				</table>
			<!-- 페이징 DIV -->
			<div class="pagingDiv" id="mainCatePagingDiv" style="width: 100%; text-align: center;"></div>
		</div>	
	</div>
	</form>
	<input type="hidden" id="h_nm_menu">
</div>

<!-- 중분류 모달창 시작 -->
<div id="midCateListModalDiv" style="display: none;">
    <div style="width: 100%; height:7%; background-color: #ececec;" align="right">
 		<input type="button" value="X" id="popupBoxClose" onclick="popupClose();" >
 	</div>
	<form method='post' name='custListPopup' id='custListPopup'>
	<div id="midCateListModalContent" style="margin: 0 1.5% 0 1.5%;">
		<div class="titleDIV" style="text-align: left; width: 100%;">
			<span class="titleText">■ 중분류 리스트</span>
		</div>
		<div id="midCateModalList" class="commonList">
			 	<table id="midCateListModalTables" style="width: 100%;">
			 	 	<thead>
			 	 		<tr id="midCateListTableHeader">
			 	 			<th style="width: 20%; text-align: right; padding-right: 1%;">중분류명 : </th>
			 	 			<td style="width: 40%;">
			 	 			    <input type="text" id="s_mid_cate_name" name="s_mid_cate_name" style="width: 70%;" maxlength="100"/>&nbsp;&nbsp;
							</td>
							<td style="width: 40%; text-align: right;">
								<input type="button" value="검색" class="back_btn" style="float: right;" onclick="viewMidCateList(1);"/> <!-- onclick="viewProdMenuList(1); -->
							</td>			
			 	 		</tr>
			 	 	</thead>
			 	</table>
			 	<br>
			 	<table class="commonTable">
			 		<thead>
			 			<tr id="midCateListTableHeader">
						  <th width="45%">중분류코드</th>
						  <th width="45%">중분류명</th>
						</tr>	
			 		</thead>
			 	 	<tbody id="midCateListTbody"></tbody>
				</table>
			<!-- 페이징 DIV -->
			<div class="pagingDiv" id="midCatePagingDiv" style="width: 100%; text-align: center;"></div>
		</div>	
	</div>
	</form>
	<input type="hidden" id="h_nm_menu">
</div>

<!-- 소분류 모달창 시작 -->
<div id="smallCateListModalDiv" style="display: none;">
    <div style="width: 100%; height:7%; background-color: #ececec;" align="right">
 		<input type="button" value="X" id="popupBoxClose" onclick="popupClose();" >
 	</div>
	<form method='post' name='custListPopup' id='custListPopup'>
	<div id="smallCateListModalContent" style="margin: 0 1.5% 0 1.5%;">
		<div class="titleDIV" style="text-align: left; width: 100%;">
			<span class="titleText">■ 소분류 리스트</span>
		</div>
		<div id="smallCateModalList" class="commonList">
			 	<table id="smallCateListModalTables" style="width: 100%;">
			 	 	<thead>
			 	 		<tr id="smallCateListTableHeader">
			 	 			<th style="width: 20%; text-align: right; padding-right: 1%;">소분류명 : </th>
			 	 			<td style="width: 40%;">
			 	 			    <input type="text" id="s_small_cate_name" name="s_small_cate_name" style="width: 70%;" maxlength="100"/>&nbsp;&nbsp;
							</td>
							<td style="width: 40%; text-align: right;">
								<input type="button" value="검색" class="back_btn" style="float: right;" onclick="viewSmallCateList(1);"/> <!-- onclick="viewProdMenuList(1); -->
							</td>			
			 	 		</tr>
			 	 	</thead>
			 	</table>
			 	<br>
			 	<table class="commonTable">
			 		<thead>
			 			<tr id="smallCateListTableHeader">
						  <th width="45%">소분류코드</th>
						  <th width="45%">소분류명</th>
						</tr>	
			 		</thead>
			 	 	<tbody id="smallCateListTbody"></tbody>
				</table>
			<!-- 페이징 DIV -->
			<div class="pagingDiv" id="smallCatePagingDiv" style="width: 100%; text-align: center;"></div>
		</div>	
	</div>
	</form>
	<input type="hidden" id="h_nm_menu">
</div>

