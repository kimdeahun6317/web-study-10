<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 추가</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js">
	
</script>
<script type="text/javascript" src="script/employee.js"></script>
</head>
<body>
	<fieldset>
		<legend>사원 추가</legend>
		<ul>
			<li><label for="empNo">사원 번호</label><input id="empNo"
				type="number" name="empNo" value="${param.nextNo }"></li>
			<li><label for="empName">사원명</label><input id="empName"
				type="text" name="empName"></li>
			<li>직책&nbsp;&nbsp;&nbsp;<select name="title" id="selectTitle"></select></li>
			<li>직속상사&nbsp;&nbsp;&nbsp;<select name="manager" id="selectEmp"></select></li>
			<li><label for="salary">급여</label><input id="salary"
				type="number" name="salary"></li>
			<li>부서&nbsp;&nbsp;&nbsp;<select name="dept" id="selectDept"></select></li>
			<li><label for="email">이메일</label><input id="email" type="email"
				name="email"></li>
			<li><label for="passwd">비밀번호</label><input id="passwd"
				type="text" name="passwd"></li>
			<li><label for="repasswd">비밀번호 확인</label><input id="repasswd"
				type="text" name="repasswd"></li>
			<li><label for="regDate">입사 날짜</label><input id="regDate"
				type="date" name="regDate"></li>
			<li><label for="tel">전화번호</label><input id="tel" type="text"
				name="tel"></li>
			<li><label for="picUrl">프로필 사진</label><input id="picUrl"
				type="text" name="picUrl"></li>
			<li>
				<button id="add">추가</button>
				<button id="cancel">취소</button>
			</li>
		</ul>
	</fieldset>
</body>
</html>