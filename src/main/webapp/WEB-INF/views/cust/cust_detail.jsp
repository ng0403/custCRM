<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath }" />    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="${ctx}/resources/common/js/cust/cust.js"></script> 
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
<%-- <script type="text/javascript" src="${ctx}/resources/common/js/common/search.min.js"></script> --%>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>

<script type="text/javascript">
//연락처타입코드리스트 저장
var phoneTypeCdList = new Array();
	<c:forEach var='phoneTypeCdList' items='${phoneTypeCdList}' varStatus="status">
	phoneTypeCdList.push("${phoneTypeCdList.code}");
	phoneTypeCdList.push("${phoneTypeCdList.code_name}");	
	</c:forEach>
//국가번호코드리스트 저장
var phoneCountryCdList = new Array();
	<c:forEach var='phoneCountryCdList' items='${phoneCountryCdList}' varStatus="status">
	phoneCountryCdList.push("${phoneCountryCdList.code}");
	phoneCountryCdList.push("${phoneCountryCdList.code_name}");	
	</c:forEach>
//주소타입코드리스트 저장
var addrTypeCdList = new Array();
	<c:forEach var='addrTypeCdList' items='${addrTypeCdList}' varStatus="status">
	addrTypeCdList.push("${addrTypeCdList.code}");
	addrTypeCdList.push("${addrTypeCdList.code_name}");	
	</c:forEach>
$(document).ready(function(){
	$('#contact_day').datepicker();
 });
</script>

<input type="hidden" id="ctx" value="${ctx}">
<input type="hidden" id="flg" value="${flg}">
<input type="hidden" id="cust_no" value="${custDlist.cust_no}">
<input type="hidden" id="page_type" value="${page_type}">

<!-- 고객 -->
<div id="cust_detail">
	<div style="height:10px;"></div>
	
	<c:if test="${flg == 0 }">
 		<div class="titleDIV">
		<span class="titleText">
		    ■  고객 > <a style="cursor: pointer;" onclick="custList('1');"> 고객관리</a> > <span id="cust_form_title">고객 상세정보</span>
		</span>
	</div>   
	</c:if>
	
	<c:if test="${flg == 1 }">
 		<div class="titleDIV">
		<span class="titleText">
		    ■  고객 > <a style="cursor: pointer;" onclick="custList('1');"> 고객관리</a> > <span id="cust_form_title">고객 추가</span>
		</span>
	</div>   
	</c:if>
	
	<c:if test="${flg == 2}">
		<c:if test="${page_type == '0' }">
	 		<div class="titleDIV">
				<span class="titleText">
			    	■  고객 > <a style="cursor: pointer;" onclick="custList('1');"> 고객관리</a> > <span id="cust_form_title">고객 상세정보</span>
				</span>
			</div>   
		</c:if>
		<c:if test="${page_type == '1' }">
	 		<div class="titleDIV">
				<span class="titleText">
			    	■  고객 > <a style="cursor: pointer;" onclick="custList('1');"> 내 담당 고객</a> > <span id="cust_form_title">고객 상세정보</span>
				</span>
			</div>   
		</c:if>
<!--  		<div class="titleDIV"> -->
<!-- 			<span class="titleText"> -->
<!-- 			    ■  고객 > <a style="cursor: pointer;" onclick="custList('1');"> 고객관리</a> > <span id="cust_form_title">고객 수정</span> -->
<!-- 			</span> -->
<!-- 		</div>    -->
	</c:if>
	
	<div style="height:10px;"></div>
	<form role="form" name="cust_single_add" id="cust_single_add" method="post" action="${ctx}/cust_single_add" >	
	<div class="commonDetail">
	<table id="cust_form_tbl" class="commonDetailTable">
		
		<tr>
 			<th id="impTh" style="text-align:right; readonly:true">* 고객명</th>
			<td>
				<c:if test="${ flg == 2 }">
					<input type="text" id="cust_name" name="cust_name" value="${custDlist.cust_name}" readonly="readonly">
				</c:if>
				<c:if test="${ flg == 1 }">
					<input type="text" id="cust_name" name="cust_name" value="${custDlist.cust_name}">
				</c:if>
 			</td>
			<th  style="text-align:right;">주민번호</th>
			<td>
				<c:if test="${ flg == 2 }">
	 			 	<input type="text" id="resident_no" name="resident_no" value="${custDlist.resident_no }" readonly="readonly" maxlength="13">
				</c:if>
				<c:if test="${ flg == 1 }">
	 			 	<input type="text" id="resident_no" name="resident_no" value="${custDlist.resident_no }" maxlength="13">
				</c:if>
  			</td>
		</tr>
		
		<tr>
			<th style="text-align:right;">차트번호</th>
			<td>
				<c:if test="${ flg == 2 }">
					<input type="text" id="chart_no" name="chart_no" value="${custDlist.chart_no}" readonly="readonly">
				</c:if>
				<c:if test="${ flg == 1 }">
					<input type="text" id="chart_no" name="chart_no" value="${custDlist.chart_no}" >
				</c:if>
 			</td>
			<th style="text-align:right;">전능고객ID</th>
			<td>
				<c:if test="${ flg == 2 }">
		       		<input type="text" id="cust_id" name="cust_id" value="${custDlist.cust_id}" readonly="readonly"> 
				</c:if>
				<c:if test="${ flg == 1 }">
		       		<input type="text" id="cust_id" name="cust_id" value="${custDlist.cust_id}"> 
				</c:if>
 			</td>
		</tr>
		<tr>
			<th id="impTh" class="discount_cost" style="text-align:right;">고객등급</th>
			<td>
				<c:if test="${ flg == 2 }">
					<select id="cust_rank" name="cust_rank" disabled="disabled" 
							style="margin-left: 0; width: 70%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;">
						<option value="">선택해 주십시오</option>
						<c:forEach var="custRankCdList" items="${ custRankCdList }">
							<c:if test= "${ custRankCdList.code eq custDlist.cust_rank }">
								<option value="${ custRankCdList.code }" selected="selected">${ custRankCdList.code_name }</option>
							</c:if>
							<c:if test= "${ custRankCdList.code ne custDlist.cust_rank }">
								<option value="${ custRankCdList.code }">${ custRankCdList.code_name }</option>
							</c:if>
						</c:forEach>
					</select>
				</c:if>
				<c:if test="${ flg == 1 }">
					<select id="cust_rank" name="cust_rank" 
							style="margin-left: 0; width: 70%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;">
						<option value="">선택해 주십시오</option>
						<c:forEach var="custRankCdList" items="${ custRankCdList }">
<%-- 							<c:if test= "${ custRankCdList.code eq custDlist.cust_rank }"> --%>
<%-- 								<option value="${ custRankCdList.code }" selected="selected">${ custRankCdList.code_name }</option> --%>
<%-- 							</c:if> --%>
<%-- 							<c:if test= "${ custRankCdList.code ne custDlist.cust_rank }"> --%>
								<option value="${ custRankCdList.code }">${ custRankCdList.code_name }</option>
<%-- 							</c:if> --%>
						</c:forEach>
					</select>
				</c:if>
			</td>
			
			<th id="impTh" class="discount_cost" style="text-align:right;">고객유형</th>
			<td>
				<c:if test="${ flg == 2 }">
					<select id="cust_type" name="cust_type" disabled="disabled" 
							style="margin-left: 0; width: 70%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;">
						<option value="">선택해 주십시오</option>
						<c:forEach var="custTypeCdList" items="${ custTypeCdList }">
							<c:if test= "${ custTypeCdList.code eq custDlist.cust_type }">
								<option value="${ custTypeCdList.code }" selected="selected">${ custTypeCdList.code_name }</option>
							</c:if>
							<c:if test= "${ custTypeCdList.code ne custDlist.cust_type }">
								<option value="${ custTypeCdList.code }">${ custTypeCdList.code_name }</option>
							</c:if>
						</c:forEach>
					</select>
				</c:if>
				<c:if test="${ flg == 1 }">
					<select id="cust_type" name="cust_type" 
							style="margin-left: 0; width: 70%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;">
						<option value="">선택해 주십시오</option>
						<c:forEach var="custTypeCdList" items="${ custTypeCdList }">
<%-- 							<c:if test= "${ custTypeCdList.code eq custDlist.cust_type }"> --%>
<%-- 								<option value="${ custTypeCdList.code }" selected="selected">${ custTypeCdList.code_name }</option> --%>
<%-- 							</c:if> --%>
<%-- 							<c:if test= "${ vititCdList.code ne custDlist.cust_type }"> --%>
								<option value="${ custTypeCdList.code }">${ custTypeCdList.code_name }</option>
<%-- 							</c:if> --%>
						</c:forEach>
					</select>
				</c:if>
			</td>
		</tr>
		<tr>
			<th id="impTh" style="text-align: right;">*담당자</th>
			<td>
				<c:if test="${ flg == 1 }">	<!-- 신규 -->
					<input type="hidden" name="emp_no" id="emp_no" value="${custDlist.emp_no}"> 
					<input name="emp_name" id="emp_name" type="text" maxlength="50" value="${custDlist.emp_name}" style="width: 70%;" readonly="readonly">
					<input type="button" class="back_btn" id="empSchBtn" value="담담자" onclick="custEmpSchPopupOpen();">
				</c:if>
				<c:if test="${ flg == 2 }">	<!-- 상세 -->
					<input type="hidden" name="emp_no" id="emp_no" value="${custDlist.emp_no}"> 
					<input name="emp_name" id="emp_name" type="text" maxlength="50" value="${custDlist.emp_name}" style="width: 70%;" readonly="readonly">
					<input type="button" class="back_btn" id="empSchBtn" value="담담자" onclick="custEmpSchPopupOpen();" disabled="disabled">
				</c:if>
			</td>
			<th class="discount_cost" style="text-align:right;">소개자</th>
			<td>
				<c:if test="${ flg == 2 }">
					<input type="text" id="rec_per" name="rec_per" value="${custDlist.cust_id}" readonly="readonly">
				</c:if>
				<c:if test="${ flg == 1 }">
					<input type="text" id="rec_per" name="rec_per" value="${custDlist.cust_id}">
				</c:if>
			</td>
		</tr>
		
		<tr>
			<th id="impTh" class="discount_cost" style="text-align:right;">* 내원경로</th>
			<td id="td_disc_type">
				<c:if test="${ flg == 2 }">
					<select id="visit_cd" name="visit_cd" disabled="disabled" 
							style="margin-left: 0; width: 70%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;">
						<option value="">선택해 주십시오</option>
						<c:forEach var="vititCdList" items="${ vititCdList }">
							<c:if test= "${ vititCdList.code eq custDlist.visit_cd }">
								<option value="${ vititCdList.code }" selected="selected">${ vititCdList.code_name }</option>
							</c:if>
							<c:if test= "${ vititCdList.code ne custDlist.visit_cd }">
								<option value="${ vititCdList.code }">${ vititCdList.code_name }</option>
							</c:if>
						</c:forEach>
					</select>
				</c:if>
				<c:if test="${ flg == 1 }">
					<select id="visit_cd" name="visit_cd" 
							style="margin-left: 0; width: 70%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;">
						<option value="">선택해 주십시오</option>
						<c:forEach var="vititCdList" items="${ vititCdList }">
							<c:if test= "${ vititCdList.code eq custDlist.visit_cd }">
								<option value="${ vititCdList.code }" selected="selected">${ vititCdList.code_name }</option>
							</c:if>
							<c:if test= "${ vititCdList.code ne custDlist.visit_cd }">
								<option value="${ vititCdList.code }">${ vititCdList.code_name }</option>
							</c:if>
						</c:forEach>
					</select>
				</c:if>
				
			</td>
			<th id="impTh"  style="text-align:right;">* 내원경로상세</th>
			<td>
				<c:if test="${ flg == 2 }">
					<select id="visit_dtl_cd" name="visit_dtl_cd" disabled="disabled" 
							style="margin-left: 0; width: 70%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;">
						<option value="">선택해 주십시오</option>
						<c:forEach var="vititDtlCdList" items="${ vititDtlCdList }">
							<c:if test= "${ vititDtlCdList.code eq custDlist.visit_dtl_cd }">
								<option value="${ vititDtlCdList.code }" selected="selected">${ vititDtlCdList.code_name }</option>
							</c:if>
							<c:if test= "${ vititDtlCdList.code ne custDlist.visit_dtl_cd }">
								<option value="${ vititDtlCdList.code }">${ vititDtlCdList.code_name }</option>
							</c:if>
						</c:forEach>
					</select>
				</c:if>
				<c:if test="${ flg == 1 }">
					<select id="visit_dtl_cd" name="visit_dtl_cd"
							style="margin-left: 0; width: 70%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;">
						<option value="">선택해 주십시오</option>
						<c:forEach var="vititDtlCdList" items="${ vititDtlCdList }">
							<c:if test= "${ vititDtlCdList.code eq custDlist.visit_dtl_cd }">
								<option value="${ vititDtlCdList.code }" selected="selected">${ vititDtlCdList.code_name }</option>
							</c:if>
							<c:if test= "${ vititDtlCdList.code ne custDlist.visit_dtl_cd }">
								<option value="${ vititDtlCdList.code }">${ vititDtlCdList.code_name }</option>
							</c:if>
						</c:forEach>
					</select>
				</c:if>
 			</td>
		</tr>
		<tr>
			<th style="text-align:right;">이메일</th>
			<td>
				<c:if test="${ flg == 2 }">
					<input type="text" id="email_id" name="email_id" value="${custDlist.email_id}" readonly="readonly">@
					<input type="text" id="email_dm" name="email_dm" value="${custDlist.email_dm}" readonly="readonly">
				</c:if>
				<c:if test="${ flg == 1 }">
					<input type="text" id="email_id" name="email_id" value="${custDlist.email_dm}">@
					<input type="text" id="email_dm" name="email_dm" value="${custDlist.email_dm}">
				</c:if>	
			</td>
		</tr>
		<tr>
			<th style="text-align:right;">내원경로세부</th>
			<td colspan="3">
				<c:if test="${ flg == 2 }">
					<input type="text" id="visit_cn" name="visit_cn" value="${custDlist.visit_cn}" readonly="readonly">
				</c:if>
				<c:if test="${ flg == 1 }">
					<input type="text" id="visit_cn" name="visit_cn" value="${custDlist.visit_cn}">
				</c:if>		
			</td>
 		</tr>
		<tr>
			<th  style="text-align:right;">특이사항</th>
			<td colspan="3">
				<c:if test="${ flg == 2 }">
					<input type="text" id="remark_cn" name="remark_cn" readonly="readonly" value="${custDlist.remark_cn}" style="height: 50px;">
				</c:if>
				<c:if test="${ flg == 1 }">
					<input type="text" id="remark_cn" name="remark_cn" value="${custDlist.remark_cn}" style="height: 50px;">
				</c:if>		
 			</td>
 		</tr>
 	</table>
	<div class="listFootDiv">
	
	<c:if test="${flg == 0 }">
	 	 <div id="cust_detail_div">
	 	 	<input type="button" class="func_btn" id="cust_update" value="편집" onclick="cust_modify(${custDlist.cust_no});">
	 	 	<input type="button" class="tr_btn" id="cust_delete" value="삭제" onclick="cust_delete(${custDlist.cust_no});">
	 	 	<input type="button" class="func_btn" id="cust_detail_cancel" value="취소" onclick="cust_cancel('${custPageNum}');">
	 	 </div> 
	 </c:if>
	
	<c:if test="${flg == 1 }">
	 	 <div id="cust_single_add_div">
	 	 	<input type="button" class="tr_btn" id="cust_single_add" value="저장" onclick="cust_add_save();">
	 	 	<input type="button" class="tr_btn" id="cust_reset_btn" onclick="cust_reset();" value="초기화">
	 	 	<input type="button" class="func_btn" id="cust_detail_cancel" value="취소" onclick="cust_cancel('${custPageNum}');">
<%-- 	 	 	<input type="button" class="func_btn" id="cust_list" onclick="custList('${custPageNum}');" value="취소"> --%>
<!-- 	 	 	<input type="button" class="func_btn" id="cust_single_cancel" value="취소" onclick="cust_cancel();"> -->
	 	 </div> 
	 </c:if>
	 <c:if test="${flg == 2 }">	 
	 	  <div id="cust_update_div" style="width: 587px; float: left; display: -webkit-box; ">
	 	  	<div style="width: 26%; ">
		 		<input type="button" class="func_btn" id="cust_single_modify" value="편집" onclick="cust_modify();">
		 		<input type="button" class="tr_btn" id="cust_delete" value="삭제" onclick="custDelete();">
			 	<input type="button" class="func_btn" id="cust_list" onclick="cust_cancel('${custPageNum}');" value="취소">
	 	  	</div>
			<div style=" border-left : 3px solid #131230; padding-left: 10px;"> 
			 	<input type="button" class="func_btn" id="cust_task_list" style="border: 1px solid #006600;" onclick="cust_task_btn('${custDlist.cust_no}')" value="상담이력">
			 	<input type="button" class="func_btn" id="cust_lead_list" style="border: 1px solid Tomato ;" onclick="cust_lead_btn('${custDlist.cust_no}')" value="고객리드">
			 	<input type="button" class="func_btn" id="cust_opty_list" style="border: 1px solid Crimson ;" onclick="cust_opty_btn('${custDlist.cust_no}')" value="영업기회">
			 	<input type="button" class="func_btn" id="cust_opty_list" style="border: 1px solid Crimson ;" onclick="cust_sales_btn('${custDlist.cust_no}')" value="청구/수금">
	<%-- 		 	<input type="button" class="func_btn" id="cust_opty_list" onclick="cust_mail_btn('${custDlist.cust_no}')" value="E-Mail"> --%>
			</div>
	 	 </div> 
	 </c:if>
    </div>
	
	<div style="height:100px;"></div>
	<c:if test="${ flg == 2 }">
	    <div id="cust_phone">
				<div class="titleDIV">
					<span class="titleText">
		    			■ <span id="coupon_form_title">전화번호</span>
						<span style="font-size: 8px;">(삭제할 전화번호를 체크한 후 삭제버튼을 누르고 저장을 눌러주세요.)</span>
					</span>
				</div>
				<div id="cust_phone_div" style="float: right;">
			 	 	<input type="button" class="func_btn" id="cust_update" value="추가" onclick="cust_phone_add();">
			 	 	<input type="button" class="tr_btn" id="cust_detail_cancel" value="저장" onclick="cust_phone_save();">
			 	 	<input type="button" class="tr_btn" id="cust_delete" value="삭제" onclick="cust_remove();">
				</div>
			<div style="height:25px;"></div>	
	    	<table id="custP_form_tbl" class="commonDetailTable">
		    	<thead>
					<tr>
						<td rowspan="2" style="width: 2%; text-align: center;">
		 	 				<input id="custPhoneChk" class="custPhoneChk" type="checkbox" onclick="custPhoneAllChk(this);" />
		 	 			</td>
			 			<th style="text-align:center;">구분</th>
						<th style="text-align:center;">국가코드</th>
						<th style="text-align:center;">국번호</th>
						<th style="text-align:center;">고유번호</th>
						<th style="text-align: center;">메인여부</th>
					</tr>
				</thead>
				<tbody id="table_tbody">
					<c:forEach items="${custPList}" var="custPList" >
					<tr>
						<td style="width: 2%; text-align: center;">
		 	 				<input type="checkbox" class="del_chk" name="del_chk">
		 	 			</td>
						<td>
							<select id="phone_type_cd" name="phone_type_cd" 
									style="margin-left: 0; width: 70%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;">
								<option value="">선택</option>
								<c:forEach var="phoneTypeCdList" items="${ phoneTypeCdList }">
									<c:if test= "${ phoneTypeCdList.code eq custPList.phone_type_cd }">
										<option value="${ phoneTypeCdList.code }" selected="selected">${ phoneTypeCdList.code_name }</option>
									</c:if>
									<c:if test= "${ phoneTypeCdList.code ne custPList.phone_type_cd }">
										<option value="${ phoneTypeCdList.code }">${ phoneTypeCdList.code_name }</option>
									</c:if>
								</c:forEach>
							</select>
			 			</td>
						<td>
				 			 <select id="phone_country_cd" name="phone_country_cd" 
										style="margin-left: 0; width: 90%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;">
									<option value="">선택</option>
									<c:forEach var="phoneCountryCdList" items="${ phoneCountryCdList }">
										<c:if test= "${ phoneCountryCdList.code eq custPList.phone_country_cd }">
											<option value="${ phoneCountryCdList.code }" selected="selected">${ phoneCountryCdList.code_name }</option>
										</c:if>
										<c:if test= "${ phoneCountryCdList.code ne custPList.phone_country_cd }">
											<option value="${ phoneCountryCdList.code }">${ phoneCountryCdList.code_name }</option>
										</c:if>
									</c:forEach>
								</select>
			  			</td>
						<td>
							<input type="text" id="phone_area_no" name="phone_area_no" style="width: 90%;" value="${custPList.phone_area_no}" >
			 			</td>
						<td> 
				       		<input type="text" id="phone_no" name="phone_no" style="width: 90%;" value="${custPList.phone_no}"> 
			 			</td>
			 			<c:if test= "${ custPList.primary_yn eq 'Y' }">
		 					<td style="text-align: center;">
		 	 					<input id="custPhoneChk" class="phone_primary_yn" type="checkbox" checked="checked" />
		 	 				</td>
		 				</c:if>
		 				<c:if test= "${ custPList.primary_yn eq 'N' }">
		 					<td style="text-align: center;">
	 	 						<input id="custPhoneChk" class="phone_primary_yn" type="checkbox" />
		 					</td>
	 					</c:if>
					</tr>	
					</c:forEach>
				</tbody>
	    	</table>
	    </div>
	        
		<div style="height:25px;"></div>
	    <div id="cust_address">
			<div class="titleDIV">
				<span class="titleText">
			    	■ <span id="coupon_form_title">우편번호</span>
			    	  <span style="font-size: 8px;">(삭제할 우편번호를 체크한 후 삭제버튼을 누르고 저장을 눌러주세요.)</span>
				</span>
			</div>
			<div id="cust_address_div" style="float: right;">
		 	 	<input type="button" class="func_btn" id="cust_update" value="추가" onclick="cust_address_add();">
		 	 	<input type="button" class="tr_btn" id="cust_detail_cancel" value="저장" onclick="cust_addr_save();">
		 	 	<input type="button" class="tr_btn" id="cust_delete" value="삭제" onclick="custAddr_remove();">
			</div>
	    	<table id="custA_form_tbl" class="commonDetailTable">
	    		<thead>
					<tr>
						<td style="width: 2%; text-align: center;">
		 	 				<input id="custAddrChk" class="custAddrChk" type="checkbox" onclick="custAddrAllChk(this);" />
		 	 			</td>
		 				<th style="text-align:center;">구분</th>
						<th style="text-align:center;">우편번호</th>
						<th style="text-align:center;">기본주소</th>
						<th style="text-align:center;">상세주소</th>
						<th style="text-align: center;">메인여부</th>
					</tr>
				</thead>
				<tbody id="tableAddr_tbody">
					<c:forEach items="${custAList}" var="custAList" >
						<tr>
							<td style="width: 2%; text-align: center;">
		 	 					<input id="custAddrChk" class="custAddrChk" type="checkbox" />
		 	 				</td>
							<td>
								<select id="addr_type_cd" name="addr_type_cd" 
										style="margin-left: 0; width: 90%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;">
									<option value="">선택</option>
									<c:forEach var="addrTypeCdList" items="${ addrTypeCdList }">
										<c:if test= "${ addrTypeCdList.code eq custAList.addr_type_cd }">
											<option value="${ addrTypeCdList.code }" selected="selected">${ addrTypeCdList.code_name }</option>
										</c:if>
										<c:if test= "${ addrTypeCdList.code ne custAList.addr_type_cd }">
											<option value="${ addrTypeCdList.code }">${ addrTypeCdList.code_name }</option>
										</c:if>
									</c:forEach>
								</select>
			 				</td>
							<td>
								<input type="text" id="postcodify_search" class='postcodify postcodify_postcode5 postcodify_search_button' name="zip_no" style="width: 90%;" value="${custAList.zip_no }" >
		  					</td>
							<td>
								<input type="text" id="main_address" name="main_address" style="width: 90%;"  value="${custAList.main_address}" >
				 			</td>
							<td> 
				    	   		<input type="text" id="detail_address" name="detail_address" style="width: 90%;" value="${custAList.detail_address}"> 
		 					</td>
		 					<c:if test= "${ custAList.primary_yn eq 'Y' }">
		 						<td style="text-align: center;">
		 	 						<input id="custAddrChk" class="addr_primary_yn" type="checkbox" checked="checked" />
		 	 					</td>
		 					</c:if>
		 					<c:if test= "${ custAList.primary_yn eq 'N' }">
		 						<td style="text-align: center;">
		 	 						<input id="custAddrChk" class="addr_primary_yn" type="checkbox" />
		 	 					</td>
		 					</c:if>
		 					
						</tr>	
					</c:forEach>
				</tbody>
	    	</table>
	    </div>
    </c:if>
	</div>
  </form>
</div>


<!-- 담당자 모달창 시작 -->
<div id="empListModalDiv" style="display: none;">
    <div style="width: 100%; height:7%; background-color: #ececec;" align="right">
 		<input type="button" value="X" id="popupBoxClose" onclick="popupClose();" >
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
								<input type="button" value="검색" class="back_btn" style="float: right;" onclick="viewEmpList();"/> <!-- onclick="viewProdMenuList(1); -->
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


<!-- 청구/수금 모달창 시작 -->
<div id="amountModalDiv" style="display: none;">
    <div style="width: 100%; height:7%; background-color: #ececec;" align="right">
 		<input type="button" value="X" id="popupBoxClose" onclick="popupClose();" >
 	</div>
	<form method='post' name='custListPopup' id='custListPopup'>
	<div id="amountContent" style="margin: 0 1.5% 0 1.5%;">
		<div class="titleDIV" style="text-align: left; width: 100%;">
			<span class="titleText">■ 청구/수금</span>
		</div>
		<div id="amountModalList" class="commonList">
			 	<table id="amountModalTables" style="width: 100%;">
			 	 	<thead>
			 	 		<tr id="amountTableHeader">
			 	 			<th style="width: 20%; text-align: right; padding-right: 1%;">수금액 : </th>
			 	 			<td style="width: 40%;">
			 	 			    <input type="text" id="amount" name="amount" style="width: 70%;" maxlength="100"/>&nbsp;&nbsp;
							</td>
							<td style="width: 40%; text-align: right;">
								<input type="button" value="납부" class="back_btn" style="float: right;" onclick="paymentBtn();"/> <!-- onclick="viewProdMenuList(1); -->
							</td>			
			 	 		</tr>
			 	 	</thead>
			 	</table>
			 	<br>
			 	<table class="commonTable">
			 		<thead>
			 			<tr id="amountTableHeader">
						  <th width="45%">직원번호</th>
						  <th width="45%">직원명</th>
						</tr>	
			 		</thead>
			 	 	<tbody id="amountTbody"></tbody>
				</table>
			<!-- 페이징 DIV -->
			<div class="pagingDiv" id="empPopupPagingDiv" style="width: 100%; text-align: center;"></div>
		</div>	
	</div>
	</form>
	<input type="hidden" id="h_nm_menu">
</div>