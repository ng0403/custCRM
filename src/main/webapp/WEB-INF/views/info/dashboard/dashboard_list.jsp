<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!-- Load D3 -->
<script src="https://d3js.org/d3.v4.min.js"></script>

<!-- Load billboard.js with base style -->
<link rel="stylesheet" href="${ctx}/resources/common/css/billboard.css">
<script src="${ctx}/resources/common/js/billboard.js"></script>

<script type="text/javascript">
$(function(){
	cust_group();
	cust_variation();
	new_cust_increase();
});

function cust_group(){
	var cust_group = bb.generate({
	    bindto: "#cust_group",
	    data: {
	        columns: [
	            ["고객", "${count.cust_count}"],
	            ["잠재고객", "${count.latent_cust_count}"]
// 	            ,["고객유도", "${count.induce_cust_count}"]
	        ],
	        type: "pie",
	        colors: {
	          "고객": "#CEF76E",
	          "잠재고객": "#61DBF0"
// 	          ,"고객유도": "#FF85FF"
	        },
		    "size": {
		        "width": 600
		    }
	    }
	});
}

function cust_variation(){
		var x_data="${count.date}";
		var c_data="${count.cust_change}";
		x_data = x_data.replace(/\[/g, "").replace(/\]/g, "");
		c_data = c_data.replace(/\[/g, "").replace(/\]/g, "");
		var x_dataArr = x_data.split(', ');
		var c_dataArr = c_data.split(', ');
		
	var cust_variation = bb.generate({
		bindto: "#cust_variation",
		"data": {
	        "json":{"x":x_dataArr,"고객":c_dataArr},
	        "x": "x",
	        "type": "area"
	    },
	    "axis": {
	        "x": {
	            "type": "timeseries",
	            "tick": {format: "%Y-%m-%d"},
	            "min": x_dataArr[1]
	        }
	    },
	    "grid": {
	        "y": {
	            "show": true,
	            "ticks":3
	        }
	    },
// 	    "size": {
// 	        "width": 600
// 	    }
	})
}

function new_cust_increase(){
		var x_data="${count.date}";
		var c_data="${count.cust_increase}";
		x_data = x_data.replace(/\[/g, "").replace(/\]/g, "");
		c_data = c_data.replace(/\[/g, "").replace(/\]/g, "");
		var x_dataArr = x_data.split(', ');
		var c_dataArr = c_data.split(', ');
		
	var cust_variation = bb.generate({
		bindto: "#new_cust_increase",
		"data": {
	        "json":{"x":x_dataArr,"고객":c_dataArr},
	        "x": "x",
	        "type": "bar"
	    },
	    "axis": {
	        "x": {
	            "type": "timeseries",
	            "tick": {format: "%Y-%m-%d"},
	            "min": x_dataArr[1]
	        },
	        "y":{
	        	"min": 1
	        }
	    },
	    "grid": {
	        "x": {
	            "show": true
	        },
	        "y": {
	            "show": true
	        }
	    }
	})
}

</script>
<style type="text/css">
.dbChart{
	width:48%;
	float:left;
}
</style>
<div class="commonList">
	<span class="titleText">
				 ■ 비교표
	</span>
	<table class="commonTable" id="cupnManagerTabl">
 	 	<thead>
 	 		<tr>
 	 			<th style="width: 4%;">일</th> 
 	 			<th style="width: 10%;">신규 고객수</th>
 	 			<th style="width: 10%;">신규 잠재고객수</th>
 	 			<th style="width: 9%;">해지수</th>
 	 			<th style="width: 10%;">신규 고객리드발굴 수</th> 
 	 			<th style="width: 10%;">성공한 고객리드 수</th>
 	 			<th style="width: 10%;">실패한 고객리드 수</th>
 	 			<th style="width: 8%;">상담 수</th>
 	 			<th style="width: 9%;">신규 영업기회 수</th>
 	 			<th style="width: 10%;">성공한 영업기회 수</th>
 	 			<th style="width: 10%;">실패한 영업기회 수</th> 
	 	 		</tr>
 	 	</thead>
 	 	<tbody id="cust_list_tbody">
 	 		<c:if test="${ countList != null}">
	 	 		<c:forEach items="${countList}" var="list" varStatus="status">
	 	 		<tr>
	 	 			<c:choose>
	 	 				<c:when test="${status.count eq 1}">
	 	 					<td>어제</td>
	 	 				</c:when>
	 	 				<c:when test="${status.count eq 2}">
	 	 					<td>오늘</td>
	 	 				</c:when>
	 	 				<c:otherwise>
	 	 					<td></td>
	 	 				</c:otherwise>
	 	 			</c:choose>
	 	 			<td>${list.new_cust}</td>
	 	 			<td>${list.new_latent_cust}</td>
	 	 			<td>${list.leave_cust}</td>
	 	 			<td>${list.new_lead}</td>
	 	 			<td>${list.lead_success}</td>
	 	 			<td>${list.lead_fail}</td>
	 	 			<td>${list.task_count}</td>
	 	 			<td>${list.new_oppty}</td>
	 	 			<td>${list.oppty_success}</td>
	 	 			<td>${list.oppty_fail}</td>
<%-- 	 	 			<td style="text-align: left;" >${list.create_date}</td> --%>
	 	 			</tr>
	 	 		</c:forEach>
 	 		</c:if>
 	 		<c:if test="${ avgList != null}">
	 	 		<tr>
	 	 			<td>평균</td>
	 	 			<td>${avgList.new_cust_avg}</td>
	 	 			<td>${avgList.new_latent_cust_avg}</td>
	 	 			<td>${avgList.leave_cust_avg}</td>
	 	 			<td>${avgList.new_lead_avg}</td>
	 	 			<td>${avgList.lead_success_avg}</td>
	 	 			<td>${avgList.lead_fail_avg}</td>
	 	 			<td>${avgList.task_count_avg}</td>
	 	 			<td>${avgList.new_oppty_avg}</td>
	 	 			<td>${avgList.oppty_success_avg}</td>
	 	 			<td>${avgList.oppty_fail_avg}</td>
<%-- 	 	 			<td style="text-align: left;" >${list.create_date}</td> --%>
	 	 			</tr>
 	 		</c:if>
 	 		<c:if test="${ empty countList }">
 	 			<tr>
	 		 		<td style="text-align: center;" colspan="10">리스트가 존재하지 않습니다.</td>
	 		 		
	 	 		</tr>
 	 		</c:if>
 	 	
 	 	</tbody>
 	 </table>
</div>
<div class="dbChart">
	<span class="titleText">
				 ■ 고객 분류별 통계
	</span>
	<div id="cust_group">
	</div>
</div>
<div class="dbChart">
	<span class="titleText">
				 ■ 고객증감
	</span>
	<div id="cust_variation">
	</div>
</div>
<div class="dbChart">
	<span class="titleText">
				 ■ 신규고객 증가수
	</span>
	<div id="new_cust_increase">
	</div>
</div>