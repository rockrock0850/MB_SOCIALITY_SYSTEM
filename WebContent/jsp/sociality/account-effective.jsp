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
<title>企業有效帳號</title>
<style type="text/css">
body {
	padding-top: 5px;
	/* 	background-color: rgba(199,237,204,1.00); */
}

.customer-btn{
	width: 80px;
}

.modal-style{
/* 	水平/垂直間距 */
 	border-spacing:20px 5px;
/*  告訴瀏覽器水平/垂直分開計算 */
	border-collapse:separate;
}

.red a {
	color: black;
}
</style>
<script type="text/javascript">
function sendSMS() {
	var string = "";
	var i = 0;
	$(":checkbox").each(function() {
		if ($(this).prop("checked")) {
			string += $(this).val() + ",";
			i++
		}
	});
	var jsonString = {
		"tempData" : string
	}
	var json = JSON.stringify(jsonString);
	$.ajax({
		type : "POST",
		dataType : 'json',
		url : 'sendMailToSelective',
		contentType : "application/json",
		data : json,
		success : function(response) {
		},
		error : function(response) {
			console.log(response);
		}
	});
};

function selectAll() {
	if ($("#selectAll").prop("checked") == false) {
		$(":checkbox").each(function() {
			$(this).prop("checked", false);
		});
		return;
	}
	
	$(":checkbox").each(function() {
		$(this).prop("checked", true);
	});
}

var count = 0;
var string = new Array;
var currentCompanyNumber;

function selectItems() {
	count = 0;
	string = new Array;
	$(":checkbox").each(function() {
		if ($(this).val() != "selectAll" && $(this).prop("checked")) {
			string[count] = $(this).val();
			count++;
		}
	});

	if (count == 0) {
		$('#msgModal h4').html("沒有選取資料");
		$('#msgModal').modal("show");
		return;
	}
	$("#editModal").modal('show');
	if (count > 1) {
		$("#now-count").text(1);
	} else {
		$("#now-count").text(count);
	}
	$("#total-count").text(count);
	
	$.ajax({
		type : "get",
		dataType : 'json',
		url : 'editEffetiveDetail/' + string[0],
		contentType : "application/json",
		success : function(res) {
			var enterprise = res.enterprise;

			var agents;
			if(res.general){
				agents = res.general;
				$('#selectGeneral').val(agents.guid);
			}else{
				agents = res.notGeneral;
				$('#selectNotGeneral').val(agents.guid);
			}

			var enterprisePasswordHistory = res.enterprisePasswordHistory;

			$('#storeNo').val(enterprise.storeNo);
			$('#account').val(enterprise.account);
			$('#selectIndustry').val(enterprise.bIndustryGuid);
			$('#password').val(enterprisePasswordHistory.passwd);
			$('#companyName').val(enterprise.companyName);
			$('#uniformNumber').val(enterprise.uniformNumber);
			$('#companyPhone').val(enterprise.companyPhone);
			$('#applicantName').val(enterprise.applicantName);
			$('#applicantMobile').val(enterprise.applicantMobile);
			$('#email').val(enterprise.email);
			$('#companyAddress').val(enterprise.companyAddress);
		},
		error : function(res) {
			console.log(res);
		}
	});
}

function next() {
	var now_count = $("#now-count").text();
	var total_count = $("#total-count").text();
	if (now_count < total_count) {
		now_count++
		$("#now-count").text(now_count);
	}

	if (count > 0 && now_count <= total_count) {
		var jsonString = {
			"companyNumber" : string[now_count - 1]
		}
		var json = JSON.stringify(jsonString);

		$.ajax({
			type : "POST",
			dataType : 'json',
			url : 'selectCustomerByNumber',
			contentType : "application/json",
			data : json,
			success : function(response) {
				$('#company_number').val(response.customer.company_number);
				$('#company_phone').val(response.customer.company_phone);
				$('#apply_email').val(response.customer.apply_email);
				$('#company_name').val(response.customer.company_name);
				$('#apply_name').val(response.customer.apply_name);
				$('#apply_phone').val(response.customer.apply_phone);
				$('#company_address')
						.val(response.customer.company_address);
				$('#store_id').val(response.customer.store_id);
				$('#business_guid').val(response.customer.business_guid);
				$('#password').val(response.customer.password);
				$('#main_account').val(response.customer.main_account);
			},
			error : function(response) {
				console.log(response);
			}
		});
	}
}

function editModalDone() {
	var data = {
		'guid' : string[0],
		'storeNo' : $('#storeNo').val(),
		'account' : $('#account').val(),
		'password' : $('#password').val(),
		'bIndustryGuid' : $('#selectIndustry').val(),
		'companyName' : $('#companyName').val(),
		'companyPhone' : $('#companyPhone').val(),
		'uniformNumber' : $('#uniformNumber').val(),
		'applicantName' : $('#applicantName').val(),
		'applicantMobile' : $('#applicantMobile').val(),
		'email' : $('#email').val(),
		'companyAddress' : $('#companyAddress').val()
	};
	
	$.ajax({
		type : "post",
		dataType : 'json',
		url : 'updateEffective',
		contentType : "application/json",
		data : JSON.stringify(data),
		success : function(res) {
			if(res.result == null || res.result == ""){
				$('#msgModal .modal-header').empty();
				$.each(res.message, function(index){
					$('#msgModal .modal-header').append("<center><h4 class='modal-title'>" +res.message[index].message+ "</h4></center>");
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
	$(":checkbox").each(function() {
		$(this).prop("checked", false);
	});
};
</script>
<%-- <jsp:include page="constant.jsp"/> --%>
</head>
<body>
	<div>
		<center>
			<jsp:include page="${requestScope.title}"/>
			<table class="table" style="width: 100%">
				<tr>
					<td style='width: 15%'>
						<jsp:include page="${requestScope.menu}"/>
					</td>
					<td>
						<table style="position: relative; width: 100%; top: 26px">
							<tr>
								<td>
									<button type="button" class="btn btn-default  btn-sm navbar-btn  customer-btn" onclick="selectItems()">編輯</button>
									<button type="button" class="btn btn-default  btn-sm navbar-btn  customer-btn">進階搜尋</button>
									<button type="button" class="btn btn-default  btn-sm navbar-btn  customer-btn">電子報發送</button>
									<button type="button" class="btn btn-default  btn-sm navbar-btn  customer-btn" onclick="sendSMS()">簡訊發送</button>
								</td>
							</tr>
							<tr>
								<td>
									<table class="table table-bordered">
										<thead>
											<tr>
												<th><input style="display: none" type="checkbox" id="selectAll" onclick="selectAll()"></th>
												<th>申請日期</th>
												<th>企業帳號</th>
												<th>業別</th>
												<th>公司名稱</th>
												<th>申請人</th>
												<th>行動電話</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${effectiveMap}" var="object">
												<c:if test="${object.key == 'effectiveList'}">
													<c:forEach items="${object.value}" var="enterprise">
														<tr>
															<td><input type="checkbox" id="check" name="check" value="${enterprise.guid}"></td>
															<td>${enterprise.createDate}</td>
															<td>${enterprise.account}</td>
															<td>${enterprise.industry}</td>
															<td>${enterprise.companyName}</td>
															<td>${enterprise.applicantName}</td>
															<td>${enterprise.applicantMobile}</td>
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
	
	<!-- editModal -->
	<div id="editModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">編輯詳細資料</h4>
					<label id="exist"></label>
				</div>
				<div class="modal-body">
					<div class="input-group">
						<form id="updateForm">
							<table class='modal-style'>
								<tr>
									<td colspan="2">
										<center>
											<div class="form-group">
												<label for="sel1">＊總代理</label> 
												<select class="form-control" id="selectGeneral">
													<option>請選擇</option>
													<c:forEach items="${effectiveMap}" var="obj">
														<c:if test="${obj.key == 'generalList'}">
															<c:forEach items="${obj.value}" var="general">
																<option value="${general.guid}">${general.companyName}</option>
															</c:forEach>
														</c:if>
													</c:forEach>
												</select>
											</div>
										</center>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<center>
											<div class="form-group">
												<label for="sel1">＊代理商</label> 
												<select class="form-control" id="selectNotGeneral">
													<option>請選擇</option>
													<c:forEach items="${effectiveMap}" var="obj">
														<c:if test="${obj.key == 'notGeneralList'}">
															<c:forEach items="${obj.value}" var="notGeneral">
																<option value="${notGeneral.guid}">${notGeneral.companyName}</option>
															</c:forEach>
														</c:if>
													</c:forEach>
												</select>
											</div>
										</center>
									</td>
								</tr>
								<tr>
									<td colspan="2"><center><label>＊特店編號 </label> <input id="storeNo" style='text-align:center;' type="text" class="form-control"></center></td>
								</tr>
								<tr>
									<td colspan="2"><center><label>＊企業帳號</label> <input id="account" style='text-align:center;' type="text" class="form-control"></center></td>
								</tr>
								<tr>
									<td>
										<div class="form-group">
											<label for="sel1">業別</label> 
											<select class="form-control" id="selectIndustry">
												<option>請選擇</option>
												<c:forEach items="${effectiveMap}" var="obj">
													<c:if test="${obj.key == 'industryList'}">
														<c:forEach items="${obj.value}" var="industry">
															<option value="${industry.guid}">${industry.name}</option>
														</c:forEach>
													</c:if>
												</c:forEach>
											</select>
										</div>
									<td>
										<label>密碼 : </label> 
										<input id="password" type="text" class="form-control">
									</td>
								</tr>
								<tr>
									<td><label>公司名稱 : </label> <input id="companyName" type="text" class="form-control"></td>
									<td><label>統一編號 : </label> <input id="uniformNumber" type="text" class="form-control"></td>
								</tr>
								<tr>
									<td><label>公司電話 : </label> <input id="companyPhone" type="text" class="form-control"></td>
									<td><label>申請人姓名 : </label> <input id="applicantName" type="text" class="form-control"></td>
								</tr>
								<tr>
									<td><label>申請人電話 : </label> <input id="applicantMobile" type="text" class="form-control"></td>
									<td><label>信箱 : </label> <input id="email" type="text" class="form-control"></td>
								</tr>
								<tr>
									<td><label>公司地址 : </label> <input id="companyAddress" type="text" class="form-control"></td>
								</tr>
							</table>
						</form>
					</div>
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