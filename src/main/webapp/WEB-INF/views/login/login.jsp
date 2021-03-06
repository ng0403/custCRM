<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<title>CorePlus</title>
 
<!-- 새 버전 CSS -->
<link rel="stylesheet" href="${ctx}/resources/common/css/login/loginMain.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/resources/common/css/login/login02.css" type="text/css" />

<script type="text/javascript" src="${ctx}/resources/common/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${ctx}/resources/common/js/login/login.js"></script>

</head>
<body>
<input type="hidden" id="ctx" value="${ctx}"/>
<div id="backgroundImg">
<%-- 	<img id="backImg" src="${ctx}/resources/image/backgroundImg04.jpg" alt="CorePlus" /> --%>
	
</div>
<div id="wrap" style=" border-radius: 50px;">
	
<!-- 	<div class="bar"></div> -->
	<div class="img">
		<img id="mainImg" src="${ctx}/resources/images/main01.png" alt="CorePlus" />
	</div> 
    <div class="loginWrap">
    	
        <h1 class="h1Logo"><img id="logo" src="${ctx}/resources/images/coreplus.jpg" alt="CorePlus" /></h1>
        <p id="mainLogo">Customer Relationship Management</p>
        <p class="h1Ttx" id="mainContK" style="display:block">CorePlus는 마케팅/영업/서비스/성과관리의 통합 관리를 통한<br />고객 Single View 제공 및 고객 대응력과 서비스 만족도 향상에 기여하는<br />고객을 위한, 고객중심의 4C(Consumer, Cost, Convenience, Communication)지향 시스템입니다.</p>
       <c:if test="${not empty sessionScope.user}"> 
       
        <div id="loginalign">
       		로그인 중입니다.<br><br>
       	<a href="${ctx}/cust" class="loging" style="background-color: #f9fafb;">메인</a>
       	</div>
       </c:if>  
       <c:if test="${empty sessionScope.user}">
       
        <p class="h1Ttx" id="mainContE" style="display:none">As true partner, one that raises the value of its customers through SIBEL CRM<br />
        <form role="form"  id="loginForm" action="${ctx}/login" method="POST">       
        <fieldset>
            <legend>로그인</legend>
            <div>
            	<c:if test="${not empty sessionScope.user}">
            	<p>세션:${sessionScope.user}</p>
            	</c:if>
                <p>
                    <input type="text" name="user_id" id="user" placeholder="Your ID" autocomplete="off"></input> <!-- tabIndex=1 --> 
                </p>
                <p>
                    <input type="password" name="pwd" id="pass" placeholder="Your PW"></input>
                </p>
                
                <span><a href="login/findID.jsp">아이디</a></span>
			    <span>&nbsp;&nbsp;</span>
			    <span><a href="login/findPW.jsp">비밀번호 찾기</a></span>
			    <span>&nbsp;&nbsp;</span>
                </div>
                
			    <input type="button" id="loginBtn" value="로그인">
			   
       	</fieldset>
        
        </form>
        
        </c:if>
        
    </div><!--//loginWrap  -->
    <hr />

    <!-- //footer -->
    <div id="footer">
    <div class="footMenu">
             <p>서울특별시 관악구 조원동 1668-12 코어빌딩 2F / 대표전화 : 02-761-2223 / FAX : 02-761-2225</p>
             <p>(c) <font color="blue">CorePlus</font> COMPANY. ALL RIGHT RESERVED.</p>
             </div>
    </div>
    
    
</div>
    
    
    
    
 <!--//wrap  -->
</body>
</html>