$(function() {
	$('#list').on("click", function() {
		location.href = "EmpListHandler";
	});
});

$(function() {
	$('#delete').on("click", function() {
		alert("삭제");
		var delEmp = {
			empNo : $('#empNo').val()
		}
		alert(delEmp);
		$.ajax({
			type : "get",
			url : "EmpDeleteHandler",
			data : delEmp,
			success : function(data) {
				alert(data);
				if (data == 1) {
					alert("삭제를 성공하였습니다.");
					window.location.href = "EmpListHandler";
				}
			}
		});
	});
});

$(function() {
	$('#modify').on("click", function() {
		if ($('#passwd').val() != $('#repasswd').val()) {
			alert("비밀번호가 일치하지 않습니다.");
			return;
		}
		var updateEmp = {
			empNo : $('#empNo').val(),
			empName : $('#empName').val(),
			title : {
				titleNo : $('#selectTitle').val(),
				titleName : $('#selectTitle').text()
			},
			manager : {
				empNo : $("#selectEmp").val(),
				empName : $("#selectEmp").text()
			},
			salary : $('#salary').val(),
			dept : {
				deptNo : $('#selectDept').val(),
				deptName : $('#selectDept').text(),
			},
			email : $('#email').val(),
			passwd : $('#passwd').val(),
			regDate : $('#regDate').val(),
			tel : $('#tel').val(),
			picUrl : $('#picUrl').val
		};
		alert(updateEmp);
		alert("ajax시작전");
		$.ajax({
			type : "post",
			url : "EmpModifyHandler",
			data : JSON.stringify(updateEmp),
			success : function(data) {
				alert("ajax성공");
				if (data == 1) {
					alert("수정되었습니다." + data);
					window.location.href = "EmpListHandler";
				}
			}
		});
	});
});
$(function() {
	$('#add').on("click", function() {
		if ($('#passwd').val() != $('#repasswd').val()) {
			alert("비밀번호가 일치하지 않습니다.");
			return;
		}
		alert($('#selectTitle').val());
		var newEmp = {
			empNo : $('#empNo').val(),
			empName : $('#empName').val(),
			title : {
				titleNo : $('#selectTitle').val(),
				titleName : $('#selectTitle').text()
			},
			manager : {
				empNo : $("#selectEmp").val(),
				empName : $("#selectEmp").text()
			},
			salary : $('#salary').val(),
			dept : {
				deptNo : $('#selectDept').val(),
				deptName : $('#selectDept').text(),
			},
			email : $('#email').val(),
			passwd : $('#passwd').val(),
			regDate : $('#regDate').val(),
			tel : $('#tel').val(),
			picUrl : $('#picUrl').val
		};
		alert($('#selectTitle').text());
		alert(newEmp);
		$.ajax({
			type : "post",
			url : "EmpAddHandler",
			cache : false,
			data : JSON.stringify(newEmp),
			success : function(data) {
				alert("추가되었습니다." + data);
				window.location.href = "EmpListHandler";
			}
		});
	});
});
$(function() {
	$('#addEmp').on("click", function() {
		alert("사원 추가버튼 클릭")
		$.ajax({
			type : "get",
			url : "EmpAddHandler",
			success : function(data) {
				alert("data : " + data);
				window.location.href = "empAdd.jsp?nextNo=" + data;
			}
		});

	});
});
$(function() {
	$.ajax({
		type : "post",
		url : "TitleListHandler",
		success : function(json) {
			if (json.length >= 1) {
				for (var i = 0; i < json.length; i++) {
					$('#selectTitle').append(
							"<option value=" + json[i].titleNo + ">"
									+ json[i].titleName + "</option>")
				}
			}

		}
	});

	$.ajax({
		type : "post",
		url : "DeptListHandler",
		success : function(json) {
			if (json.length >= 1) {
				for (var i = 0; i < json.length; i++) {
					$('#selectDept').append(
							"<option value=" + json[i].deptNo + ">"
									+ json[i].deptName + "</option>")
				}
			}

		}

	});
	$.ajax({
		type : "post",
		url : "EmpListHandler",
		success : function(json) {
			if (json.length >= 1) {
				for (var i = 0; i < json.length; i++) {
					$('#selectEmp').append(
							"<option value=" + json[i].empNo + ">"
									+ json[i].empName + "</option>")
				}
			}

		}
	});

	$.post("EmpListHandler",
			function(json) {
				var dataLength = json.length;
				alert(json);
				if (dataLength >= 1) {
					var sCont = "";
					alert("스크립트작동한다~");
					alert(dataLength);
					alert(json[1].empNo);
					for (i = 0; i < dataLength; i++) {
						sCont += "<tr>";
						sCont += "<td>" + json[i].empNo + "</td>";
						sCont += "<td><a href='EmpGetHandler?empNo="
								+ json[i].empNo + "'>" + json[i].empName
								+ "</a></td>";
						sCont += "<td>" + json[i].title.titleName + "("
								+ json[i].title.titleNo + ")</td>";
						if (json[i].manager.empNo != 0) {
							sCont += "<td>" + json[i].manager.empName + "("
									+ json[i].manager.empNo + ")</td>";
						} else {
							sCont += "<td></td>";
						}
						sCont += "<td>" + json[i].salary.toLocaleString("ko")
								+ "</td>";
						sCont += "<td>" + json[i].dept.deptName + "("
								+ json[i].dept.deptNo + ")</td>";
						sCont += "<td>" + moment(json[i].regDate).format('LL')
								+ "</td>";
						sCont += "<td>" + json[i].email + "</td>";
						sCont += "<td>" + json[i].tel + "</td>";
						sCont += "</tr>";
					}
					//$("table > tbody:last-child").append(sCont);
					alert("반복문끝");
					$("#load:last-child").append(sCont);
				}
			});

});
