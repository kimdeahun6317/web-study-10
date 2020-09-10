<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js">
	
</script>
<script type="text/javascript" src="script/employee.js"></script>
<link rel="stylesheet" href="css/emplist.css">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/locale/ko.js"></script>
</head>
<body>
	<header>
		<h1>사원 목록</h1>
		<select name="title" id="selectTitle"></select> <select name="dept"
			id="selectDept"></select>
	</header>
	<table border=1>
		<thead>
			<tr>
				<td>사원번호</td>
				<td>사원명</td>
				<td>직책</td>
				<td>직속상사</td>
				<td>급여</td>
				<td>부서</td>
				<td>입사일</td>
				<td>이메일</td>
				<td>연락처</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="emp" items="${list }">
				<tr>
					<td>${emp.empNo}</td>
					<td><a href="EmpGetHandler?empNo=${emp.empNo}">${emp.empName}</a></td>
					<td>${emp.title.titleNo}(${ emp.title.titleName})</td>
					<td>${emp.manager.empNo}(${ emp.manager.empName})</td>
					<td>${emp.salary}</td>
					<td>${emp.dept.deptNo}(${ emp.dept.deptName})</td>
					<td>${emp.regDate}</td>
					<td>${emp.email}</td>
					<td>${emp.tel}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<hr>
	<h2>사원목록</h2>
	<table>
		<thead>
			<tr>
				<td>사원번호</td>
				<td>사원명</td>
				<td>직책</td>
				<td>직속상사</td>
				<td>급여</td>
				<td>부서</td>
				<td>입사일</td>
				<td>이메일</td>
				<td>연락처</td>
			</tr>
		</thead>
		<tbody id="load">
		</tbody>
	</table>
	<button id="addEmp">사원 추가</button>
</body>
</html>