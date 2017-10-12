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
	response.setHeader("Content-Disposition", "attachment; filename="+UriUtils.encodeFragment("상담목록","UTF-8")+mTime+".xls;'");
// 	response.setHeader("Content-Description", "JSP Generated Data");
	response.setContentType("application/vnd.ms-excel");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.taskExcelTbl {
	border: thin solid black;2
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
		<c:if test="${not empty taskExcelExport}">		
			<tr>
				<td class="header" style="width: 10%;">상담번호</td>
				<td class="header" style="width: 10%;">제목</td>
				<td class="header" style="width: 10%;">고객번호</td>
				<td class="header" style="width: 10%;">고객명</td>
				<td class="header" style="width: 10%;">전화번호</td>
				<td class="header" style="width: 10%;">담당자명</td>
				<td class="header" style="width: 10%;">다음일자</td>
				<td class="header" style="width: 10%;">분류</td>
				<td class="header" style="width: 10%;">등록일시</td>
			</tr>
		</c:if>
		<c:if test="${fn:length(taskExcelExport) == 0}" >
			<tr>
				<td class="header" style="width: 10%;">제목</td>
				<td class="header" style="width: 10%;">고객번호</td>
				<td class="header" style="width: 10%;">담당자번호</td>
				<td class="header" style="width: 10%;">다음일자</td>
				<td class="header" style="width: 10%;">분류코드</td>
				<td class="header" style="width: 10%;">가망고객번호</td>
				<td class="header" style="width: 10%;">영업기회번호</td>
				<td class="header" style="width: 10%;">상담유형</td>
				<td class="header" style="width: 10%;">상대가치점수</td>
				<td class="header" style="width: 10%;">상담구분</td>
				<td class="header" style="width: 10%;">진행장소</td>
				<td class="header" style="width: 10%;">특이사항</td>
			</tr>
		</c:if>
	</thead>
	<tbody>
		<c:if test="${not empty taskExcelExport}">			
			<c:forEach var="taskExcelExport" items="${taskExcelExport}" >
				<tr>
					<td class="body" id="no" ><c:out value="${taskExcelExport.task_no}"></c:out></td>
					<td class="body"><c:out value="${taskExcelExport.subject}"></c:out></td>
					<td class="body" id="no"><c:out value="${taskExcelExport.cust_no}"></c:out></td>
					<td class="body"><c:out value="${taskExcelExport.cust_name}"></c:out></td>
					<td class="body"><c:out value="${taskExcelExport.phone_no}"></c:out></td>
					<td class="body"><c:out value="${taskExcelExport.emp_no}"></c:out></td>
					<td class="body"><c:out value="${taskExcelExport.next_day}"></c:out></td>
					<td class="body"><c:out value="${taskExcelExport.dtype_cd}"></c:out></td>
					<td class="body"><c:out value="${taskExcelExport.create_date}"></c:out></td>
				</tr>
			</c:forEach>
		</c:if>
			
		<c:if test="${fn:length(taskExcelExport) == 0}" >
			<tr style="cursor: default; background-color: white;">
				<td class="body"></td>
				<td class="body" id="no"></td>
				<td class="body" id="no"></td>
				<td class="body"></td>
				<td class="body"></td>
				<td class="body" id="no"></td>
				<td class="body" id="no"></td>
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