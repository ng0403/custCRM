<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath }" />
<c:set var="taskDetail" value="${ taskDetail }" />
<c:set var="flg" value="${ flg }" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="http://malsup.github.com/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/resources/common/js/task/task.js"></script>
<script type="text/javascript" src="${ctx}/resources/common/js/task/task_detail.js"></script>
<script type="text/javascript" src="${ctx}/resources/common/js/task/task_popup.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$('#next_day').datepicker();
	});
</script>

<input type="hidden" id="ctx" value="${ctx}">
<input type="hidden" id="flg" value="${flg}">
<input type="hidden" id="taskPageNum" value="${taskPageNum}">
 <!-- 상담 -->
<div id="task_detail">
	<div style="height: 10px;"></div>
	<c:if test="${lead_no != null}"> 
	<div class="titleDIV">
			<span class="titleText">■ 고객리드 > <a style="cursor: pointer;" onclick="leadlist();"> 고객리드관리</a> > <a style="cursor: pointer;" onclick="leadDetail('${lead_no}', '${PageNum}');">고객리드 상세정보</a> >
			<a onclick="lead_counsel_read('${cust_no}', '${PageNum}');"> 고객상담 이력 </a> >
			<span id="task_form_title">고객상담 상세정보 </span>
			</span>
		</div> 
	</c:if>
   <c:if test="${lead_no == null}">
	<c:if test="${flg == 2 }">
		<div class="titleDIV">
			<span class="titleText"> ■ 상담 > <a style="cursor: pointer;"
				onclick="taskList();"> 상담 관리</a> > <span id="task_form_title">상담
					상세정보</span>
			</span>
		</div>
	</c:if>

	<c:if test="${flg == 1 }">
		<div class="titleDIV">
			<span class="titleText"> ■ 상담 > <a style="cursor: pointer;"
				onclick="taskList();"> 상담 관리</a> > <span id="task_form_title">상담
					추가</span>
			</span>
		</div>
	</c:if>
	
	<c:if test="${flg == 3 }">
		<div class="titleDIV">
			<span class="titleText"> 
				■ 가망고객 > <a style="cursor: pointer;" onclick="leadlist();"> 가망고객관리</a> > <span id="coupon_form_title">가망고객 상세정보</span> > 고객상담 이력 > 고객 상담 상세정보
			</span>
		</div>
	</c:if>
 </c:if>
	<%-- 	<c:if test="${flg == 2}"> --%>
	<!--  		<div class="titleDIV"> -->
	<!-- 			<span class="titleText"> -->
	<!-- 			    ■  <a style="cursor: pointer;" onclick="taskList();"> 상담</a> > <span id="cust_form_title">상담 수정</span> -->
	<!-- 			</span> -->
	<!-- 		</div>    -->
	<%-- 	</c:if> --%>

	<div style="height: 10px;"></div>
	<form role="form" name="task_single_add" id="task_single_add"method="post" action="${ctx}/task_single_add">
		<div class="commonDetail">
			<table id="task_form_tbl" class="commonDetailTable">

				<tr>
					<th id="impTh" style="text-align: right; readonly: true">*상담번호</th>
					<td>
						<c:if test="${ taskNoIndex.task_no != null }">
							<input name="task_no" id="task_no" type="text"value="${taskNoIndex.task_no}" style="width: 60%;" disabled="disabled">
						</c:if> 
						<c:if test="${ taskNoIndex.task_no == null }">
							<input name="task_no" id="task_no" type="text" value="${taskDetail.task_no}" style="width: 60%; background-color: white" disabled="disabled">
						</c:if>
					</td>
					<th id="impTh" style="text-align: right;">* 제목</th>
					<td>
						<c:if test="${flg == 1 }">
							<input type="text" id="subject" name="subject" value="${taskDetail.subject }">
						</c:if> 
						<c:if test="${flg == 2 }">
							<input type="text" id="subject" name="subject" value="${taskDetail.subject }" readonly="readonly">
						</c:if>
					</td>
				</tr>

				<tr>
					<th id="impTh" style="text-align: right;">* 고객</th>
					<td>
						<c:if test="${flg == 1 }">
							<input type="hidden" id="cust_no" name="cust_no" value="${taskDetail.cust_no}">
							<input type="text" name="cust_name" id="cust_name" maxlength="50" value="${taskDetail.cust_name}"style="width: 60%; background-color: white;" readonly="readonly">
							<input type="button" class="back_btn" id="custSchBtn" value="고객" onclick="custSchPopupOpen();">
						</c:if> 
						<c:if test="${flg == 2 }">
							<input type="hidden" id="cust_no" name="cust_no"value="${taskDetail.cust_no}">
							<input type="text" name="cust_name" id="cust_name" maxlength="50"value="${taskDetail.cust_name}" style="width: 60%;"readonly="readonly" readonly="readonly">
							<input type="button" class="back_btn" id="custSchBtn" value="고객"onclick="custSchPopupOpen();" disabled="disabled">
						</c:if>
					</td>
					<th id="impTh" style="text-align: right;">* 담당자</th>
					<td>
						<c:if test="${flg == 1 }">
							<input type="hidden" name="emp_no" id="emp_no"value="${taskDetail.emp_no}">
							<input name="emp_name" id="emp_name" type="text" maxlength="50"value="${taskDetail.emp_name}"style="width: 60%; background-color: white;" readonly="readonly">
							<input type="button" class="back_btn" id="empSchBtn" value="담담자"onclick="empSchPopupOpen();">
						</c:if> 
						<c:if test="${flg == 2 }">
							<input type="hidden" name="emp_no" id="emp_no"value="${taskDetail.emp_no}">
							<input name="emp_name" id="emp_name" type="text" maxlength="50"value="${taskDetail.emp_name}" style="width: 60%;"readonly="readonly">
							<input type="button" class="back_btn" id="empSchBtn" value="담담자"onclick="empSchPopupOpen();" disabled="disabled">
						</c:if>
					</td>
				</tr>

				<tr>
					<th id="impTh" class="discount_cost" style="text-align: right;">*다음일자</th>
					<td id="td_disc_type"><c:if test="${flg == 1 }">
							<input type="text" name="next_day" id="next_day"value="${taskDetail.next_day}" maxlength="10"style="width: 30%; text-align: center; cursor: pointer;"readonly="readonly">
						</c:if> 
						<c:if test="${flg == 2 }">
							<input type="text" name="next_day" id="next_day"value="${taskDetail.next_day}" maxlength="10"style="width: 30%; text-align: center; background-color: white; cursor: pointer;"disabled="disabled">
						</c:if>
					</td>
					<th id="impTh" style="text-align: right;">* 분류</th>
					<td><c:if test="${flg == 1 }">
							<select id="dtype_cd" name="dtype_cd"style="margin-left: 0; width: 62%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;">
								<option value="">선택해 주십시오</option>
								<c:forEach var="dtypeCd" items="${ dtypeCd }">
									<c:if test="${ dtypeCd.code == taskDetail.dtype_cd }">
										<option value="${ dtypeCd.code }" selected="selected">${ dtypeCd.code_name }</option>
									</c:if>
									<c:if test="${ dtypeCd.code != taskDetail.dtype_cd }">
										<option value="${ dtypeCd.code }">${ dtypeCd.code_name }</option>
									</c:if>
								</c:forEach>
							</select>
						</c:if> <c:if test="${flg == 2 }">
							<select id="dtype_cd" name="dtype_cd"style="margin-left: 0; width: 62%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em; background-color: white"disabled="disabled">
								<option value="">선택해 주십시오</option>
								<c:forEach var="dtypeCd" items="${ dtypeCd }">
									<c:if test="${ dtypeCd.code == taskDetail.dtype_cd }">
										<option value="${ dtypeCd.code }" selected="selected">${ dtypeCd.code_name }</option>
									</c:if>
									<c:if test="${ dtypeCd.code != taskDetail.dtype_cd }">
										<option value="${ dtypeCd.code }">${ dtypeCd.code_name }</option>
									</c:if>
								</c:forEach>
							</select>
						</c:if></td>
				</tr>
				<tr>
					<th style="text-align: right;">가망고객</th>
					<td><c:if test="${flg == 1 }">
							<input type="hidden" id="lead_no" name="lead_no"value="${taskDetail.lead_no}">
							<input type="text" name="lead_name" id="lead_name" maxlength="50"value="${taskDetail.lead_name}"style="width: 60%; background-color: white;" readonly="readonly">
							<input type="button" class="back_btn" id="leadSchBtn"value="가망고객" onclick="leadSchPopupOpen();">
						</c:if> <c:if test="${flg == 2 }">
							<input type="hidden" id="lead_no" name="lead_no"value="${taskDetail.lead_no}">
							<input type="text" name="lead_name" id="lead_name" maxlength="50"value="${taskDetail.lead_name}" style="width: 60%;"readonly="readonly">
							<input type="button" class="back_btn" id="leadSchBtn"value="가망고객" onclick="leadSchPopupOpen();" disabled="disabled">
						</c:if></td>
					<th style="text-align: right;">영업기회</th>
					<td><c:if test="${flg == 1 }">
							<input type="hidden" name="oppty_no" id="oppty_no"value="${taskDetail.oppty_no}">
							<input name="oppty_name" id="oppty_name" type="text"maxlength="50" value="${taskDetail.oppty_name}"style="width: 60%; background-color: white;" readonly="readonly">
							<input type="button" class="back_btn" id="opptySchBtn"value="영업기회" onclick="opptySchPopupOpen(); ">
						</c:if> <c:if test="${flg == 2 }">
							<input type="hidden" name="oppty_no" id="oppty_no"value="${taskDetail.oppty_no}">
							<input name="oppty_name" id="oppty_name" type="text"maxlength="50" value="${taskDetail.oppty_name}"style="width: 60%;" readonly="readonly">
							<input type="button" class="back_btn" id="opptySchBtn"value="영업기회" onclick="opptySchPopupOpen(); " disabled="disabled">
						</c:if></td>
				</tr>
				<tr>
					<th id="impTh" style="text-align: right;">*상담유형</th>
					<td id="td_disc_type">
						<c:if test="${flg == 1 }">
							<select id="ttype_cd" name="ttype_cd"style="margin-left: 0; width: 62%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;">
								<option value="">선택해 주십시오</option>
								<c:forEach var="ttypeCd" items="${ ttypeCd }">
									<c:if test="${ ttypeCd.code eq taskDetail.ttype_cd }">
										<option value="${ ttypeCd.code }" selected="selected">${ ttypeCd.code_name }</option>
									</c:if>
									<c:if test="${ ttypeCd.code ne taskDetail.ttype_cd }">
										<option value="${ ttypeCd.code }">${ ttypeCd.code_name }</option>
									</c:if>
								</c:forEach>
							</select>
						</c:if> 
						<c:if test="${flg == 2 }">
							<select id="ttype_cd" name="ttype_cd"style="margin-left: 0; width: 62%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em; background-color: white"disabled="disabled">
								<option value="">선택해 주십시오</option>
								<c:forEach var="ttypeCd" items="${ ttypeCd }">
									<c:if test="${ ttypeCd.code eq taskDetail.ttype_cd }">
										<option value="${ ttypeCd.code }" selected="selected">${ ttypeCd.code_name }</option>
									</c:if>
									<c:if test="${ ttypeCd.code ne taskDetail.ttype_cd }">
										<option value="${ ttypeCd.code }">${ ttypeCd.code_name }</option>
									</c:if>
								</c:forEach>
							</select>
						</c:if>
					</td>
					<th id="impTh" class="discount_cost" style="text-align: right;">*상대가치점수</th>
					<td id="td_disc_type"><c:if test="${flg == 1 }">
							<select id="score_cd" name="score_cd"style="margin-left: 0; width: 62%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;">
								<option value="">선택해 주십시오</option>
								<c:forEach var="scoreCd" items="${ scoreCd }">
									<c:if test="${ scoreCd.code eq taskDetail.score_cd }">
										<option value="${ scoreCd.code }" selected="selected">${ scoreCd.code_name }</option>
									</c:if>
									<c:if test="${ scoreCd.code ne taskDetail.score_cd }">
										<option value="${ scoreCd.code }">${ scoreCd.code_name }</option>
									</c:if>
								</c:forEach>
							</select>
						</c:if> <c:if test="${flg == 2 }">
							<select id="score_cd" name="score_cd"style="margin-left: 0; width: 62%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em; background-color: white"disabled="disabled">
								<option value="">선택해 주십시오</option>
								<c:forEach var="scoreCd" items="${ scoreCd }">
									<c:if test="${ scoreCd.code eq taskDetail.score_cd }">
										<option value="${ scoreCd.code }" selected="selected">${ scoreCd.code_name }</option>
									</c:if>
									<c:if test="${ scoreCd.code ne taskDetail.score_cd }">
										<option value="${ scoreCd.code }">${ scoreCd.code_name }</option>
									</c:if>
								</c:forEach>
							</select>
						</c:if>
					</td>
					
				</tr>
				<tr>
					<th id="impTh" style="text-align: right;" >*상담구분</th>
					<td id="td_disc_type">
						<c:if test="${flg == 1 }">
							<select id="divis_cd" name="divis_cd"style="margin-left: 0; width: 62%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;">
								<option value="">선택해 주십시오</option>
								<c:forEach var="divisCd" items="${ divisCd }">
									<c:if test="${ divisCd.code eq taskDetail.divis_cd }">
										<option value="${ divisCd.code }" selected="selected">${ divisCd.code_name }</option>
									</c:if>
									<c:if test="${ divisCd.code ne taskDetail.divis_cd }">
										<option value="${ divisCd.code }">${ divisCd.code_name }</option>
									</c:if>
								</c:forEach>
							</select>
						</c:if> 
						<c:if test="${flg == 2 }">
							<select id="divis_cd" name="divis_cd"style="margin-left: 0; width: 62%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em; background-color: white"disabled="disabled">
								<option value="">선택해 주십시오</option>
								<c:forEach var="divisCd" items="${ divisCd }">
									<c:if test="${ divisCd.code eq taskDetail.divis_cd }">
										<option value="${ divisCd.code }" selected="selected">${ divisCd.code_name }</option>
									</c:if>
									<c:if test="${ divisCd.code ne taskDetail.divis_cd }">
										<option value="${ divisCd.code }">${ divisCd.code_name }</option>
									</c:if>
								</c:forEach>
							</select>
						</c:if>
					</td>
					<th id="impTh" style="text-align: right;">* 진행장소</th>
					<td>
						<c:if test="${flg == 1 }">
							<input type="text" id="location" name="location" value="${taskDetail.location}">
						</c:if> 
						<c:if test="${flg == 2 }">
							<input type="text" id="location" name="location" value="${taskDetail.location}" readonly="readonly">
						</c:if>
					</td>
					
				</tr>
				<tr >
					<th style="text-align: right;">특이사항</th>
					<td colspan='3'>
						<c:if test="${flg == 1 }">
							<input type="text" id="remark_cn" name="remark_cn"
								value="${taskDetail.remark_cn}">
						</c:if> 
						<c:if test="${flg == 2 }">
							<input type="text" id="remark_cn" name="remark_cn"
								value="${taskDetail.remark_cn}" readonly="readonly">
						</c:if>
					</td>
				</tr>

			</table>
			<div class="listFootDiv">
				<c:if test="${flg == 0 }">
					<div id="cust_detail_div">
						<input type="button" class="func_btn" id="task_update" value="편집"
							onclick="task_modify(${taskDetail.task_no});"> <input
							type="button" class="func_btn" id="task_delete" value="삭제"
							onclick="task_remove();"> 
							
							<c:if test="${lead_no == null}">
						     <input type="button" class="func_btn" id="task_detail_cancel" value="취소" onclick="task_cancel('${taskPageNum}');">						
						    </c:if>
						    <c:if test="${lead_no != null}">
						     <input type="button" class="func_btn" id="task_detail_cancel" value="취소" onclick="lead_cancel('${taskPageNum}','${lead_no}');">						
						    </c:if>
					</div>
				</c:if>

				<c:if test="${flg == 1 }">
					<div id="cust_single_add_div">
						<input type="button" class="tr_btn" id="task_single_add"
							value="저장" onclick="task_add_save();"> <input
							type="button" class="tr_btn" id="task_single_del" value="초기화"
							onclick="task_reset();">
							
							<c:if test="${lead_no == null}">
						     <input type="button" class="func_btn" id="task_detail_cancel" value="취소" onclick="task_cancel('${taskPageNum}');">						
						    </c:if>
						    <c:if test="${lead_no != null}">
						     <input type="button" class="func_btn" id="task_detail_cancel" value="취소" onclick="lead_cancel('${taskPageNum}','${lead_no}');">						
						    </c:if>
					</div>
				</c:if>
				<c:if test="${flg == 2 }">
					<div id="cust_update_div">
						<input type="button" class="func_btn" id="task_single_modify"
							value="편집" onclick="task_modify_btn();"> <input
							type="button" class="tr_btn" id="task_single_del" value="삭제"
							onclick="task_del_save();">
							
							<c:if test="${lead_no == null}">
						     <input type="button" class="func_btn" id="task_detail_cancel" value="취소" onclick="task_cancel('${taskPageNum}');">						
						    </c:if>
						    <c:if test="${lead_no != null}">
						     <input type="button" class="func_btn" id="task_detail_cancel" value="취소" onclick="lead_cancel('${taskPageNum}','${lead_no}');">						
						    </c:if>
					</div>
				</c:if>
			</div>
		</div>
	</form>
</div>


<!-- 고객 모달창 시작 -->
<div id="custListModalDiv" style="display: none;">
	<div style="width: 100%; height: 7%; background-color: #ececec;"
		align="right">
		<input type="button" value="X" id="popupBoxClose"
			onclick="popupClose();">
	</div>
	<form method='post' name='custListPopup' id='custListPopup'>
		<div id="custListModalContent" style="margin: 0 1.5% 0 1.5%;">
			<div class="titleDIV" style="text-align: left; width: 100%;">
				<span class="titleText">■ 고객 리스트</span>
			</div>
			<div id="custModalList" class="commonList">
				<table id="custListModalTables" style="width: 100%;">
					<thead>
						<tr id="custListTableHeader">
							<th style="width: 20%; text-align: right; padding-right: 1%;">고객명
								:</th>
							<td style="width: 40%;"><input type="text" id="s_cust_name"
								name="s_cust_name" style="width: 70%;" maxlength="100" />&nbsp;&nbsp;
							</td>
							<td style="width: 40%; text-align: right;"><input
								type="button" value="검색" class="back_btn" style="float: right;"
								onclick="viewCustList();" /> <!-- onclick="viewProdMenuList(1); -->
							</td>
						</tr>
					</thead>
				</table>
				<br>
				<table class="commonTable">
					<thead>
						<tr id="custListTableHeader">
							<th width="45%">고객번호</th>
							<th width="45%">고객명</th>
						</tr>
					</thead>
					<tbody id="custListTbody"></tbody>
				</table>
				<!-- 페이징 DIV -->
				<div class="pagingDiv" id="custPopupPagingDiv"
					style="width: 100%; text-align: center;"></div>
			</div>
		</div>
	</form>
	<input type="hidden" id="h_nm_menu">
</div>


<!-- 담당자 모달창 시작 -->
<div id="empListModalDiv" style="display: none;">
	<div style="width: 100%; height: 7%; background-color: #ececec;"
		align="right">
		<input type="button" value="X" id="popupBoxClose"
			onclick="popupClose();">
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
							<th style="width: 20%; text-align: right; padding-right: 1%;">고객명
								:</th>
							<td style="width: 40%;"><input type="text" id="s_emp_name"
								name="s_emp_name" style="width: 70%;" maxlength="100" />&nbsp;&nbsp;
							</td>
							<td style="width: 40%; text-align: right;"><input
								type="button" value="검색" class="back_btn" style="float: right;"
								onclick="viewEmpList();" /> <!-- onclick="viewProdMenuList(1); -->
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
				<div class="pagingDiv" id="empPopupPagingDiv"
					style="width: 100%; text-align: center;"></div>
			</div>
		</div>
	</form>
	<input type="hidden" id="h_nm_menu">
</div>


<!-- 가망고객 모달창 시작 -->
<div id="leadListModalDiv" style="display: none;">
	<div style="width: 100%; height: 7%; background-color: #ececec;"
		align="right">
		<input type="button" value="X" id="popupBoxClose"
			onclick="popupClose();">
	</div>
	<form method='post' name='leadListPopup' id='leadListPopup'>
		<div id="leadListModalContent" style="margin: 0 1.5% 0 1.5%;">
			<div class="titleDIV" style="text-align: left; width: 100%;">
				<span class="titleText">■ 가망고객 리스트</span>
			</div>
			<div id="leadModalList" class="commonList">
				<table id="leadListModalTables" style="width: 100%;">
					<thead>
						<tr id="leadListTableHeader">
							<th style="width: 20%; text-align: right; padding-right: 1%;">가망고객명
								:</th>
							<td style="width: 40%;"><input type="text" id="s_lead_name"
								name="s_lead_name" style="width: 70%;" maxlength="100" />&nbsp;&nbsp;
							</td>
							<td style="width: 40%; text-align: right;"><input
								type="button" value="검색" class="back_btn" style="float: right;"
								onclick="viewLeadList();" /> <!-- onclick="viewProdMenuList(1); -->
							</td>
						</tr>
					</thead>
				</table>
				<br>
				<table class="commonTable">
					<thead>
						<tr id="leadListTableHeader">
							<th width="45%">가망고객번호</th>
							<th width="45%">가망고객명</th>
						</tr>
					</thead>
					<tbody id="leadListTbody"></tbody>
				</table>
				<!-- 페이징 DIV -->
				<div class="pagingDiv" id="leadPopupPagingDiv"
					style="width: 100%; text-align: center;"></div>
			</div>
		</div>
	</form>
	<input type="hidden" id="h_nm_menu">
</div>


<!-- 영업기회 모달창 시작 -->
<div id="opptyListModalDiv" style="display: none;">
	<div style="width: 100%; height: 7%; background-color: #ececec;"
		align="right">
		<input type="button" value="X" id="popupBoxClose"
			onclick="popupClose();">
	</div>
	<form method='post' name='opptyListPopup' id='opptyListPopup'>
		<div id="opptyListModalContent" style="margin: 0 1.5% 0 1.5%;">
			<div class="titleDIV" style="text-align: left; width: 100%;">
				<span class="titleText">■ 영업기회 리스트</span>
			</div>
			<div id="opptyModalList" class="commonList">
				<table id="opptyListModalTables" style="width: 100%;">
					<thead>
						<tr id="opptyListTableHeader">
							<th style="width: 20%; text-align: right; padding-right: 1%;">영업기회명
								:</th>
							<td style="width: 40%;"><input type="text" id="s_oppty_name"
								name="s_oppty_name" style="width: 70%;" maxlength="100" />&nbsp;&nbsp;
							</td>
							<td style="width: 40%; text-align: right;"><input
								type="button" value="검색" class="back_btn" style="float: right;"
								onclick="viewOpptyList();" /> <!-- onclick="viewProdMenuList(1); -->
							</td>
						</tr>
					</thead>
				</table>
				<br>
				<table class="commonTable">
					<thead>
						<tr id="opptyListTableHeader">
							<th width="45%">영업기회번호</th>
							<th width="45%">영업기회명</th>
						</tr>
					</thead>
					<tbody id="opptyListTbody"></tbody>
				</table>
				<!-- 페이징 DIV -->
				<div class="pagingDiv" id="opptyPopupPagingDiv"
					style="width: 100%; text-align: center;"></div>
			</div>
		</div>
	</form>
	<input type="hidden" id="h_nm_menu">
</div>
