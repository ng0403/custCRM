<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input type="hidden" id="session" value="${session}">
<div id="main_header">
    <span id="header_banner">CRM (<span id="point">C</span>ustomer <span id="point">R</span>elationship <span id="point">M</span>anagement)
	
    <span id="headerTXT" style="float: right; margin-right: 2%; font-size: 12px; font-style: initial;">님</span> 
    <span id="headerUserID" style="float: right;margin-right: 0.4%;  font-style: initial; font-family: initial; font-size: 13pt;"><%=session.getAttribute("user") %></span> 
</div>
   
