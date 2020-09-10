<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 추가</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js">
	
</script>
<script>
	$(function() {
		$('#add').on("click", function() {
			var newDepartment = {
				deptNo : $('#deptNo').val(),
				deptName : $('#deptName').val(),
				floor : $('#floor').val()
			};
			alert(newDepartment)
			$.ajax({
				type : "post",
				url : "DeptAddHandler",
				cache : false,
				data : JSON.stringify(newDepartment),
				complete : function(data) {
					alert("추가되었습니다." + data);
					window.location.href = "DeptListHandler";
				}
			});
		});
	});
</script>
</head>
<body>
	<fieldset>
		<legend>부서 추가</legend>
		<ul>
			<li><label for="deptNo">부서 번호</label> <input id="deptNo"
				type="number" name="deptNo" value="${param.nextNo }" readonly>
			</li>
			<li><label for="deptName">부서</label> <input id="deptName"
				type="text" name="deptName"></li>
			<li><label for="floor">층</label> <input id="floor" type="number"
				name="floor"></li>
			<li>
				<button id="add">추가</button>
				<button id="cancel">취소</button>
			</li>
		</ul>
	</fieldset>
</body>
</html>