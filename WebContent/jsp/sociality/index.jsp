<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/resources/css/base.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-switch.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js" /></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" /></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-switch.min.js" /></script>
<title>個人帳號管理</title>
<style type="text/css">
body {
	padding-top: 5px;
}

.modal-style{
/* 	水平/垂直間距 */
 	border-spacing:20px 5px;
/*  告訴瀏覽器水平/垂直分開計算 */
	border-collapse:separate;
}
</style>
<script type="text/javascript">
var guid;
	
$(function() {
	$("body").on("click", ".btn-info", function() {
		guid = $(this).attr('data-id'); //抓取當前點擊元件的data-id
	});

	$("body").on("click", "#update", function() {
		guid = $(this).attr('data-id');
		$.ajax({
			type : "get",
			dataType : 'json',
			url : 'employeeEditDetail/' + guid,
			success : function(res) {
				if(res.result == ""){
					$('#msgModal h4').html(res.message);
					$('#failModal').modal('hide');
					$('#msgModal').modal("show");
				}
				
				var employee = res.result;

				$('#editAccount').val(employee.account);
				$('#editPassword').val(employee.password);
				$('#editEmail').val(employee.email);
			},
			error : function(res) {
				console.log(res);
			}
		});
	});
});

function newModalDone() {
	var data = {
			'enterpriseGuid' : $('#enterpriseGuid').val(),
			'account' : $('#account').val(),
			'password' : $('#password').val(),
			'email' : $('#email').val(),
	};
	
	$.ajax({
		type : "post",
		dataType : 'json',
		contentType : "application/json",
		url : 'newEmployee',
		data : JSON.stringify(data),
		success : function(res) {
			console.log(res);
			
			if(res.result == null || res.result == ""){
				$('#msgModal .modal-header').empty();
				$.each(res.message, function(index){
					$('#msgModal .modal-header').append("<center><h4 class='modal-title'>" 
							+res.message[index].message+ "</h4></center>");
				});
				$('#newModal').modal('hide');
				$('#msgModal').modal("show");
				return;
			}

			location.reload(true);
			$('#deleteModal').modal('hide');
		},
		error : function(res) {
			console.log(res);
		}
	});
};

function deleteModalDone() {
	var data = {
		'guid' : guid,
	};
	
	$.ajax({
		type : "post",
		dataType : 'json',
		contentType : "application/json",
		url : 'deleteEmployee',
		data : JSON.stringify(data),
		success : function(res) {
			if(res.result == null || res.result == ""){
				$('#msgModal h4').html(res.message);
				$('#deleteModal').modal('hide');
				$('#msgModal').modal("show");
				return;
			}

			location.reload(true);
			$('#deleteModal').modal('hide');
		},
		error : function(res) {
			console.log(res);
		}
	});
}

function editModalDone() {
	var data = {
		'guid' : guid,
		'account' : $('#editAccount').val(),
		'password' : $('#editPassword').val(),
		'email' : $('#editEmail').val(),
	};
	
	$.ajax({
		type : "post",
		dataType : 'json',
		url : 'updateEmployee',
		contentType : "application/json",
		data : JSON.stringify(data),
		success : function(res) {
			console.log(res);
			if(res.result == "" || res.result == null){
				$('#msgModal .modal-header').empty();
				$.each(res.message, function(index){
					$('#msgModal .modal-header').append("<center><h4 class='modal-title'>" 
							+res.message[index].message+ "</h4></center>");
				});
				
				$('#editModal').modal('hide');
				$('#msgModal').modal("show");
				return;
			}
			
			$('#editModal').modal('hide');
			location.reload(true);
		},
		error : function(res) {
			console.log(res);
		}
	});
}

function cancel(){
	location.reload(true);
};
</script>
<jsp:include page="../constant.jsp"/>
</head>
<body>
	<div>
		<center>
			<h3>
				<span class="label label-info">個人帳號管理系統</span>
			</h3>
			<table class="table" style="width: 100%">
				<tr>
					<td>
						<table style="position: relative; width: 100%; top: 35px;">
							<tr>
								<th>
									<button type="button" class="btn btn-info" data-toggle="modal" data-target="#newModal">新增</button>
								</th>
							</tr>
							<tr><td><table class="table table-bordered"></table></td></tr>
							<tr>
								<td>
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>管理</th>
												<th>帳號</th>
												<th>密碼</th>
												<th>郵件</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${employeeMap}" var="object">
												<c:if test="${object.key == 'employeeList'}">
													<c:forEach items="${object.value}" var="employee">
														<tr>
															<td>
																<button type="button" class="btn btn-info" data-id="${employee.guid}" data-toggle="modal" data-target="#deleteModal">刪除</button> /
																<button type="button" class="btn btn-info" data-id="${employee.guid}" id="update" data-toggle="modal" data-target="#editModal">編輯</button>
															</td>
															<td>${employee.account}</td>
															<td>${employee.password}</td>
															<td>${employee.email}</td>
														</tr>
													</c:forEach>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</center>
	</div>
	
	<!-- newModal -->
	<div id="newModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">新增個人帳號</h4>
					<label id="exist"></label>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">
					
						<c:forEach items="${employeeMap}" var="object">
							<c:if test="${object.key == 'enterpriseGuid'}">
								<input style="display: none" id="enterpriseGuid" value="${object.value}">
							</c:if>
						</c:forEach>
					
						<div class="form-group">
							<label class="control-label col-sm-2">帳號:</label>
							<div class="col-sm-8">
								<input id="account" type="text" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">密碼:</label>
							<div class="col-sm-8">
								<input id="password" type="text" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">郵件:</label>
							<div class="col-sm-8">
								<input id="email" type="text" class="form-control">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<center>
						<button type="button" class="btn btn-default" onclick="newModalDone();">確定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</center>
				</div>
			</div>
		</div>
	</div>
	
	<!-- editModal -->
	<div id="editModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">編輯個人資料</h4>
					<label id="exist"></label>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label class="control-label col-sm-2">帳號:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="editAccount">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">密碼:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="editPassword">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">郵件:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="editEmail">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<center>
						<button type="button" class="btn btn-default" onclick="editModalDone();">確定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</center>
				</div>
			</div>
		</div>
	</div>
	
	<!-- deleteModal -->
	<div id="deleteModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">確認刪除帳號!?</h4>
				</div>
				<div class="modal-body">
					<center>
						<button type="button" class="btn btn-default" onclick="deleteModalDone();">確定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</center>
				</div>
			</div>
		</div>
	</div>
	
	<!-- msgModal -->
	<div id="msgModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<center><h4 class="modal-title"></h4></center>
				</div>
				<div class="modal-body">
					<center><button type="button" class="btn btn-default" data-dismiss="modal" onclick="cancel()">取消</button></center>
				</div>
			</div>
		</div>
	</div>
</body>
</html>