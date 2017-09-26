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
<input type="hidden" id="codePageNum" value="${codePageNum}">
<!-- 코드 -->
<div id="code_detail">
	<div style="height: 10px;"></div>

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


	<div style="height: 10px;"></div>
	<form role="form" name="code_single_add" id="code_single_add"method="post" action="${ctx}/code_single_add">
		<div class="twoCommonDetail">
			<table id="code_form_tbl" class="commonDetailTable">

				<tr>
					<th style="text-align: right; readonly: true">코드번호</th>
					<td>
						<c:if test="${ codeDetail.code_no != null }">
							<input name="code_no" id="code_no" type="text"value="${codeDetail.code_no}" style="width: 60%;" disabled="disabled">
						</c:if> 
						<c:if test="${ codeDetail.code_no == null }">
							<input name="code_no" id="code_no" type="text" value="${codeDetail.code_no}" style="width: 60%; background-color: white" disabled="disabled">
						</c:if>
					</td>
				</tr>
				<tr>	
					<th  style="text-align: right;">코드</th>
					<td>
							<input type="text" id="code" name="code" value="${codeDetail.code }">
						<c:if test="${flg == 1 }">
						</c:if> 
						<c:if test="${flg == 2 }">
							<input type="text" id="code" name="code" value="${codeDetail.code }" readonly="readonly">
						</c:if>
					</td>
				</tr>

				<tr>
					<th  style="text-align: right;">코드명</th>
					<td>
							<input type="text" id="code_name" name="code_name" value="${codeDetail.code_name }">
						<c:if test="${flg == 1 }">
						</c:if> 
						<c:if test="${flg == 2 }">
							<input type="text" id="code_name" name="code_name" value="${codeDetail.code_name }" readonly="readonly">
						</c:if>
					</td>
				</tr>
				<tr>
					<th  style="text-align: right;">표시여부</th>
					<td>
<%-- 						<c:if test="${flg == 1 }"> --%>
							<select id="display_yn" name="display_yn" style="margin-left: 0; width: 62%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;">
								<option value="">선택해 주십시오</option>
								<c:forEach var="displayYN" items="${ displayYN }">
									<c:if test="${ displayYN.code == codeDetail.display_yn }">
										<option value="${ displayYN.code }" selected="selected">${ dtypeCd.display_yn }</option>
									</c:if>
									<c:if test="${ displayYN.code != codeDetail.display_yn }">
										<option value="${ displayYN.code }">${ displayYN.display_yn }</option>
									</c:if>
								</c:forEach>
							</select>
<%-- 						</c:if>  --%>
						<c:if test="${flg == 2 }">
							<select id="display_yn" name="display_yn"style="margin-left: 0; width: 62%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em; background-color: white"disabled="disabled">
								<option value="">선택해 주십시오</option>
								<c:forEach var="displayYN" items="${ displayYN }">
									<c:if test="${ displayYN.code == codeDetail.display_yn }">
										<option value="${ displayYN.code }" selected="selected">${ dtypeCd.display_yn }</option>
									</c:if>
									<c:if test="${ displayYN.code != codeDetail.display_yn }">
										<option value="${ displayYN.code }">${ displayYN.display_yn }</option>
									</c:if>
								</c:forEach>
							</select>
						</c:if>	
					</td>
				</tr>
				<tr>
					<th  style="text-align: right;">상위코드번호</th>
					<td>
							<input type="text" id="par_code_no" name="par_code_no" value="${codeDetail.par_code_no }">
						<c:if test="${flg == 1 }">
						</c:if> 
						<c:if test="${flg == 2 }">
							<input type="text" id="par_code_no" name="par_code_no" value="${codeDetail.par_code_no }" readonly="readonly">
						</c:if>
					</td>
				</tr>
			</table>
		
		</div>
	</form>

	<input type="hidden" id="h_nm_menu">
</div>
