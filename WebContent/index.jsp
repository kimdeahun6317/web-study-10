<%@page import="web_study_10.ds.JndiDS"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데이터베이스 연동 테스트</title>
</head>
<body>
	<c:set var="con" value="<%=JndiDS.getConnection()%>"></c:set>
	<c:out value="${con }"/>
	<br>
	<a href="TitleListHandler">직책 목록</a>
	<br>
	<a href="DeptListHandler">부서 목록</a>
	<br>
	<a href="EmpListHandler">사원 목록</a>
</body>
</html>