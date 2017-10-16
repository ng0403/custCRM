<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.2.1.js"/>"></script>
 <script type="text/javascript" src="${ctx}/resources/common/js/standard/board/board_list.js"></script> 

<title>Insert title here</title>
<style type="text/css">
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
</head>
<body>
	<label style="font-weight: bold;">▶ 표준 제안서 상세</label>
	<br><br>
	<form action="/pptxDown" method="post">
	<input type="text" id="FILE_CD" name="FILE_CD" value="${FILE_CD}"/>
	<div>
		<table class="gridtable">
			<tr>
				<th colspan="2">[CJ-1001] 법인고객 통합 물류 대행 서비스 제안서</th>
			</tr>
			<c:forEach  items="${slide}" varStatus="status" >
				<tr>
					<td rowspan="2"><input type="checkbox" name="checkSelect" class="checkSelect" value="${status.index}"></td>
					<td>Slide-${status.index+1}</td>
				</tr>
				<tr>
					<td><img src="resources/image/pptx/AsposeThumbnail_Out${status.index}.jpg" style="width:300px; height:300px;"></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div style="float: right; margin: 10px 10px 10px 0;">
		<input type="submit" id="pptDownBtn" value="다운로드">
	</div>
	</form>
</body>
</html>