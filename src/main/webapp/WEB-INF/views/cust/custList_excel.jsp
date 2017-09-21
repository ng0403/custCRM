<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="org.springframework.web.util.UriUtils" %>
<%@ page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyyMMddHHmmss", Locale.KOREA );
	Date currentTime = new Date();
	String mTime = mSimpleDateFormat.format (currentTime);
	response.setHeader("Content-Disposition", "attachment; filename="+UriUtils.encodeFragment("고객목록","UTF-8")+mTime+".xls;'");
	response.setHeader("Content-Description", "JSP Generated Data");
	response.setContentType("application/vnd.ms-excel");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.taskExcelTbl {
	border: thin solid black;
}
.header{
	border: thin solid black;
	background-color: #f9ffff;
	text-align: center;
}
.body{
	border: thin solid black;
	text-align: center;
}
#no{
	mso-number-format: "@";
}
</style>
</head>
<body>
<table class="taskExcelTbl">
	<thead>
		<c:if test="${not empty custExcelExport}">
			<tr>
				<td class="header" style="width: 10%;">고객번호</td>
				<td class="header" style="width: 10%;">고객명</td>
				<td class="header" style="width: 10%;">차트번호</td>
				<td class="header" style="width: 10%;">내원경로</td>
				<td class="header" style="width: 10%;">내원경로상세</td>
				<td class="header" style="width: 10%;">소개자</td>
				<td class="header" style="width: 10%;">전화번호</td>
				<td class="header" style="width: 10%;">주소</td>
				<td class="header" style="width: 10%;">등록일시</td>
			</tr>
		</c:if>
		<c:if test="${empty custExcelExport}">
			<tr>
				<td class="header" style="width: 10%;">고객명</td>
				<td class="header" style="width: 10%;">주민번호</td>
				<td class="header" style="width: 10%;">차트번호</td>
				<td class="header" style="width: 10%;">전능고객ID</td>
				<td class="header" style="width: 10%;">내원경로</td>
				<td class="header" style="width: 10%;">내원경로상세</td>
				<td class="header" style="width: 10%;">내원경로내용</td>
				<td class="header" style="width: 10%;">소개자</td>
				<td class="header" style="width: 10%;">특이사항</td>
			</tr>
		</c:if>
	</thead>
	<tbody>
		<c:if test="${not empty custExcelExport}">			
			<c:forEach var="custExcelExport" items="${custExcelExport}" >
				<tr>
					<td class="body" id="no" ><c:out value="${custExcelExport.cust_no}"></c:out></td>
					<td class="body"><c:out value="${custExcelExport.cust_name}"></c:out></td>
					<td class="body" id="no" ><c:out value="${custExcelExport.chart_no}"></c:out></td>
					<td class="body"><c:out value="${custExcelExport.visit_cd}"></c:out></td>
					<td class="body"><c:out value="${custExcelExport.visit_dtl_cd}"></c:out></td>
					<td class="body"><c:out value="${custExcelExport.rec_per}"></c:out></td>
					<td class="body"><c:out value="${custExcelExport.phone_no}"></c:out></td>
					<td class="body"><c:out value="${custExcelExport.main_address}"></c:out></td>
					<td class="body"><c:out value="${custExcelExport.create_date}"></c:out></td>
				</tr>
			</c:forEach>
		</c:if>
			
		<c:if test="${fn:length(custExcelExport) == 0}">
			<tr style="cursor: default; background-color: white;">
				<td class="body"></td>
				<td class="body"></td>
				<td class="body"></td>
				<td class="body"></td>
				<td class="body"></td>
				<td class="body"></td>
				<td class="body"></td>
				<td class="body"></td>
				<td class="body"></td>
			</tr>
		</c:if>
	</tbody>
</table>
</body>
</html>