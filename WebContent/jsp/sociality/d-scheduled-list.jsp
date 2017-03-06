<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link href="resources/publicTemplates/style.css" rel="stylesheet">
<link href="resources/publicTemplates/normalize.css" rel="stylesheet">
<link href="resources/publicTemplates/rwd.css" rel="stylesheet"> -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-datetimepicker.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/locales/bootstrap-datetimepicker.zh-TW.js"></script>
<title>行程管理</title>
<style type="text/css">
/ Modal加入scrollbar /
.modal-dialog{
    overflow-y: initial !important
}

.modal-body{
    height: 500px;
    overflow-y: auto;
}
</style>
<jsp:include page="../constant.jsp" />
<script type="text/javascript">
	var token = JSON.stringify(${dataMap.token});

	$(function() {
		if(token == 'null'){
			window.location.href = '${requestScope.loginPage}';
		}
		
		$('.form_date').datetimepicker({
	        language:  'zh-TW',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
	    });
		$('.form_time').datetimepicker({
	        language:  'zh-TW',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 1,
			minView: 0,
			maxView: 1,
			forceParse: 0
	    });
	});
	
	//用來記錄目前所勾選的checkbox
	var currentSelected = "";
	//chooseOne()函式，參數為觸發該函式的元素本身
	function chooseOne(cb) {
		//先判斷是否有點選過任何的checkbox
		//若有的話，把原先的變為未勾選
		if(currentSelected!="")	currentSelected.checked = false;
		//變更目前勾選的checkbox
		if(cb.checked)	currentSelected = cb;
		else	currentSelected="";
	}

	function edit() {
		$("#editModal").modal('show');
	};
	
	function editClose() {
		$("#editModal").modal('hide');
		location.reload();
	};
	
	function deleteClose() {
		$("#deleteModal").modal('hide');
		location.reload();
	};
	
	function deleteModal() {
		var guid = "";
		$(":checkbox").each(function() {
			if ($(this).prop("checked") && $(this).attr('id') == "scheduledGuid") {
				guid = guid.concat($(this).val());;
			}
		});
		
		if(guid == "" || guid == null){
			showAlert("請選擇要刪除的資料");
			return;
		}
		
		$("#deleteModal").modal('show');
	}
	
	function view(){
		var guid = "";
		$(":checkbox").each(function() {
			if ($(this).prop("checked") && $(this).attr('id') == "scheduledGuid") {
				guid = guid.concat($(this).val());;
			}
		});
		
		if(guid == ""){
			showAlert("請選擇要檢視的資料");
			return;
		}
		
		var json = {
			guid:guid
		}
		
		$.ajax({
            url: "../../scheduled/api/scheduled_fetchData",
            data: JSON.stringify(json),
            type:"POST",
            dataType : 'json',
	        contentType: "application/json",
            success: function(msg){
                if(msg.result != null){
                	showAlert(msg.result);
                	return;
                }
                //alert(msg.data.guid);
                $("#viewTitle").val(msg.data.title);
                $("#viewProject").val(msg.project.name);
                $("#viewCompanyName").val(msg.data.companyName);
                $("#viewNick").val(msg.data.nickname);
                $("#viewLocation").val(msg.data.destination);
                $("#viewTextArea").html(msg.data.recordOfMeeting);
                $("#viewSouvenir").val(msg.data.souvenir);
                $("#viewCardName").val(msg.data.name);
                if(msg.data.status == 1){
                	 $("#viewOptradio").val("待處理");
                }else if(msg.data.status == 2){
                	$("#viewOptradio").val("改期");
                }else if(msg.data.status == 3){
                	$("#viewOptradio").val("取消");
                }else if(msg.data.status == 4){
                	$("#viewOptradio").val("完成");
                }
                
                var date
                if(msg.startTime != ""){
                	date = new Date(msg.startTime);
                	var month = date.getMonth()+1;
                	var year = date.getFullYear();
                	var d = date.getDate();
                	var houres = date.getHours();
                	var minutes = date.getMinutes();
                	var sec = date.getSeconds();
                	$("#viewStrartDate").val(year+"/"+month+"/"+d);
                	$("#viewStrartTime").val(houres+":"+minutes+":"+sec);
                }

				if (msg.endTime != "") {
					date = new Date(msg.endTime);
                	var month = date.getMonth()+1;
                	var year = date.getFullYear();
                	var d = date.getDate();
                	var houres = date.getHours();
                	var minutes = date.getMinutes();
                	var sec = date.getSeconds();
                	$("#viewEndDate").val(year+"/"+month+"/"+d);
                	$("#viewEndTime").val(houres+":"+minutes+":"+sec);
				}
				if (msg.mailTime != "") {
					date = new Date(msg.mailTime);
                	var month = date.getMonth()+1;
                	var year = date.getFullYear();
                	var d = date.getDate();
                	var houres = date.getHours();
                	var minutes = date.getMinutes();
                	var sec = date.getSeconds();
                	$("#viewMailTime").val(year+"/"+month+"/"+d);
                	$("#viewMailDate").val(houres+":"+minutes+":"+sec);

				}
				if (msg.alarmTime != "") {
					date = new Date(msg.alarmTime);
                	var month = date.getMonth()+1;
                	var year = date.getFullYear();
                	var d = date.getDate();
                	var houres = date.getHours();
                	var minutes = date.getMinutes();
                	var sec = date.getSeconds();
                	$("#viewAlarmTime").val(year+"/"+month+"/"+d);
                	$("#viewAlarmDate").val(houres+":"+minutes+":"+sec);

				}

				$("#viewModal").modal('show');
			},
			error : function(msg) {
				console.log(msg);
			}
		});
	}
	
	function update(){
		var guid = "";
		$(":checkbox").each(function() {
			if ($(this).prop("checked") && $(this).attr('id') == "scheduledGuid") {
				guid = guid.concat($(this).val());;
			}
		});
		
		if(guid == ""){
			showAlert("請選擇要編輯的資料");
			return;
		}
		
		var json = {
			guid:guid
		}
		
		$.ajax({
            url: "../../scheduled/api/scheduled_fetchData",
            data: JSON.stringify(json),
            type:"POST",
            dataType : 'json',
	        contentType: "application/json",
            success: function(msg){
                if(msg.result != null){
                	showAlert(msg.result);
                	return;
                }
                //alert(msg.data.guid);
                $("#updateGuid").val(msg.data.guid);
                $("#updateTitle").val(msg.data.title);
                $("#updateProject").val(msg.project.guid);
                $("#updateCompanyName").val(msg.data.companyName);
                $("#updateNick").val(msg.data.nickname);
                $("#updateLocation").val(msg.data.destination);
                $("#updateTextArea").html(msg.data.recordOfMeeting);
                $("#updateSouvenir").val(msg.data.souvenir);
                $("#updateCardName").val(msg.data.name);
                $("input[name=updateOptradio][value="+msg.data.status+"]").attr("checked",true);
               
                var date
                if(msg.startTime != ""){
                	date = new Date(msg.startTime);
                	var month = date.getMonth()+1;
                	var year = date.getFullYear();
                	var d = date.getDate();
                	var houres = date.getHours();
                	var minutes = date.getMinutes();
                	var sec = date.getSeconds();
                	$("#updateStrartDate").val(year+"/"+month+"/"+d);
                	$("#updateStrartTime").val(houres+":"+minutes+":"+sec);
                }

				if (msg.endTime != "") {
					date = new Date(msg.endTime);
                	var month = date.getMonth()+1;
                	var year = date.getFullYear();
                	var d = date.getDate();
                	var houres = date.getHours();
                	var minutes = date.getMinutes();
                	var sec = date.getSeconds();
                	$("#updateEndDate").val(year+"/"+month+"/"+d);
                	$("#updateEndTime").val(houres+":"+minutes+":"+sec);
				}
				if (msg.mailTime != "") {
					date = new Date(msg.mailTime);
                	var month = date.getMonth()+1;
                	var year = date.getFullYear();
                	var d = date.getDate();
                	var houres = date.getHours();
                	var minutes = date.getMinutes();
                	var sec = date.getSeconds();
                	$("#updateMailDate").val(year+"/"+month+"/"+d);
                	$("#updateMailTime").val(houres+":"+minutes+":"+sec);

				}
				if (msg.alarmTime != "") {
					date = new Date(msg.alarmTime);
                	var month = date.getMonth()+1;
                	var year = date.getFullYear();
                	var d = date.getDate();
                	var houres = date.getHours();
                	var minutes = date.getMinutes();
                	var sec = date.getSeconds();
                	$("#updateAlarmDate").val(year+"/"+month+"/"+d);
                	$("#updateAlarmTime").val(houres+":"+minutes+":"+sec);

				}

				$("#updateModal").modal('show');
			},
			error : function(msg) {
				console.log(msg);
			}
		});
	}

	function updateClose() {
		$("#updateModal").modal('hide');
		location.reload();
	}

	function updateSubmit() {
		var radioValue = $('input[name="updateOptradio"]:checked').val();
		var checkBoxValue = $('select[name=updateProject]').val();

		var json = {
			guid:$("#updateGuid").val(),
			title : $("#updateTitle").val(),
			bScheduledProjectGuid : checkBoxValue,
			companyName : $("#updateCompanyName").val(),
			name : $("#updateCardName").val(),
			dBussinessCardGuid : $("#updateCardGuid").val(),
			nickname : $("#updateNick").val(),
			updateStrartDate : $("#updateStrartDate").val(),
			updateStrartTime : $("#updateStrartTime").val(),
			updateEndDate : $("#updateEndDate").val(),
			updateEndTime : $("#updateEndTime").val(),
			updateMailTime : $("#updateMailTime").val(),
			updateMailDate : $("#updateMailDate").val(),
			updateAlarmTime : $("#updateAlarmTime").val(),
			updateAlarmDate : $("#updateAlarmDate").val(),
			destination : $("#updateLocation").val(),
			souvenir : $("#updateSouvenir").val(),
			optradio : radioValue,
			recordOfMeeting : $("#updateTextArea").val(),
			file : $("#file").val(),
		}

		//alert(JSON.stringify(json));

		$.ajax({
			url : "../../scheduled/api/scheduled_update",
			data : JSON.stringify(json),
			type : "POST",
			dataType : 'json',
			contentType : "application/json",
			success : function(msg) {
				if (msg.result != null) {
					showAlert(msg.result);
					return;
				}
				updateClose();
			},
			error : function(msg) {
				console.log(msg);
			}
		});
	}

	function deleteModalSubmit() {
		var guid = [];
		var i = 0;
		$(":checkbox").each(
				function() {
					if ($(this).prop("checked")
							&& $(this).attr('id') == "scheduledGuid") {
						guid[i] = $(this).val();
						i++;
					}
				});

		var json = {
			guidArray : guid
		};

		$.ajax({
			url : "../../scheduled/api/scheduled_delete",
			data : JSON.stringify(json),
			type : "POST",
			dataType : 'json',
			contentType : "application/json",
			success : function(msg) {
				if (msg.result != null) {
					showAlert(msg.result);
					return;
				}
				deleteClose();
			},
			error : function(msg) {
				console.log(msg);
			}
		});
	};

	function editSubmit() {
		var radioValue = $('input[name="optradio"]:checked').val();
		var checkBoxValue = $('select[name=editCardProject]').val();

		var json = {
			title : $("#editCardTitle").val(),
			bScheduledProjectGuid : checkBoxValue,
			companyName : $("#editCardCompanyName").val(),
			name : $("#editCardName").val(),
			dBussinessCardGuid : $("#editCardGuid").val(),
			nickname : $("#editCardNick").val(),
			editCardBeginDate : $("#editCardBeginDate").val(),
			editCardBeginTime : $("#editCardBeginTime").val(),
			editCardEndDate : $("#editCardEndDate").val(),
			editCardEndTime : $("#editCardEndTime").val(),
			editCardMailDate : $("#editCardMailDate").val(),
			editCardMailTime : $("#editCardMailTime").val(),
			editCardAlarmDate : $("#editCardAlarmDate").val(),
			editCardAlarmTime : $("#editCardAlarmTime").val(),
			destination : $("#editCardLocation").val(),
			souvenir : $("#editCardSouvenir").val(),
			optradio : radioValue,
			recordOfMeeting : $("#editCardTextArea").val(),
			file : $("#file").val(),
		}

		//alert(JSON.stringify(json));

		$.ajax({
			url : "../../scheduled/api/scheduled_create",
			data : JSON.stringify(json),
			type : "POST",
			dataType : 'json',
			contentType : "application/json",
			success : function(msg) {
				if (msg.result != null) {
					showAlert(msg.result);
					return;
				}
				editClose();
			},
			error : function(msg) {
				console.log(msg);
			}
		});
	};

	function showAlert(msg) {
		$("#alertTitle").text(msg);
		$("#alertModal").modal('show');
	};

	function alertClose() {

	};

	function selectCard() {
		$("#selectCardModal").modal('show');
	};

	var modalName ="";
	function newName(modal) {
		if(modal == "create"){
			modalName = modal;
			$("#editModal").modal('hide');
		}else{
			modalName = modal;
			$("#updateModal").modal('hide');
		}
		$("#newNameModal").modal('show');
	};

	function newNameClose() {
		if(modalName == "create"){
			$("#editModal").modal('show');
		}else{
			$("#updateModal").modal('show');
		}
	};

	function sameAddress() {
		//alert($("#editCardAddress").val());
		$("#editCardLocation").val($("#editCardAddress").val());
		$("#upateLocation").val($("#upateAddress").val());
	};

	function newNameSubmit() {
		var string = "";
		var name = "";
		var address = "";
		$(":checkbox").each(function() {
			if ($(this).prop("checked") && $(this).attr('id') == "cardGuid") {
				string = string.concat($(this).val());
				name = $(this).attr('name');
				address = $(this).attr('data-id');
			}
		});

		if(modalName == "create"){
			$("#editCardAddress").val($('#' + string).val());
			$("#editCardName").val(name);
			$("#editCardGuid").val(string);
			$("#editModal").modal('show');
		}else if(modalName == "update"){
			$("#updateAddress").val($('#' + string).val());
			$("#updateCardName").val(name);
			$("#updateCardGuid").val(string);
			$("#updateModal").modal('show');
			
		}
		$("#newNameModal").modal('hide');
	};

	function change(url) {
		window.location.href = url;
	}
</script>
</head>
<body>
	<table class="table" style="width: 100%">
		<tr>
			<td>
				<table style="position: relative; width: 100%; top: 35px;">
					<tr>
						<th>
							<button type="button" class="btn btn-danger" data-toggle="modal"
								onclick="deleteModal();">刪除</button>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
							<button type="button" class="btn btn-success" data-toggle="modal"
								onclick="view();">檢視</button>
							<button type="button" class="btn btn-primary" data-toggle="modal"
								onclick="edit()">新增</button>
							<button type="button" class="btn btn-warning" data-toggle="modal"
								onclick="update();">維護</button>
							
						</th>
					</tr>
					<tr>
						<td><table class="table table-bordered"></table></td>
					</tr>
					<tr>
						<td>
							<table class="table table-bordered">
								<thead>
									<tr>
										<th><input style="display: none" ></th>
										<th>預定日期</th>
										<th>行程標題</th>
										<th>預定項目</th>
										<th>姓名/暱稱</th>
										<th>地點位置</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${dataMap.dataList}" var="list">
										<tr>
											<td width="5%"><input type="checkbox" id="scheduledGuid" value="${list.guid}"></td>
											<td width="25%">
												起：<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${list.startTime}" /> <br/>
												迄：<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${list.endTime}" />
											</td>
											<td width="10%"> ${list.title}</td>
											<c:forEach items="${dataMap.projectList}" var="project">
												<c:if test="${project.guid == list.bScheduledProjectGuid}">
													<td width="10%">${project.name}</td>
												</c:if>
											</c:forEach>
											<td width="10%">${list.name}/${list.nickname}</td>
											<td width="40%">${list.destination}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

<!-- editModal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" 
     aria-labelledby="editModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
         	<div class="modal-header">
        		<button type="button" class="btn btn-primary" onclick="editSubmit();">儲存</button>
		        <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button> 
      		</div>
            <!-- Modal Body -->
            <div class="modal-body">
				<form class="form-horizontal" id='editForm' role="form" style='margin-top: 15px;'>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2" for="email">行程標題:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" placeholder="行程標題" id="editCardTitle">
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2" for="email">預定項目:</label>
						<div class="col-sm-10">
							<select class="form-control selectpicker" name="editCardProject" id="editCardProject">	
								<option value="" selected="selected">請選擇預定項目</option>
 								<c:forEach var="list" items="${dataMap.projectList}">
								   <option value="${list.guid}">${list.name}</option>
								</c:forEach> 
							</select>
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2" for="email">公司名稱</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" placeholder="公司名稱" id="editCardCompanyName">
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" style="text-align:left" class="control-label col-sm-2">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="editCardName" placeholder="姓名(必填)">
						</div>
						<div class="col-sm-4">
							<button type="button" class="btn btn-primary" data-toggle="modal" onclick="newName('create');">新增</button>
						</div>
						<input type="hidden" id="editCardGuid" name="editCardGuid"/>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">暱稱</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" placeholder="暱稱" id="editCardNick">
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">起</label>
						<div class="col-sm-4">
							<div class="input-group date form_date" data-date="" data-date-format="yyyy/mm/dd" data-link-field="date1" data-link-format="yyyy/mm/dd">
								<input class="form-control" type="text" placeholder="開始日期" readonly id="editCardBeginDate"> 
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<input type="text" id="date1" value="" style='display: none;' />
						</div>
						<div class="col-sm-4">
							<div class="input-group date form_time" data-date="" data-date-format="hh:ii" data-link-field="time1" data-link-format="hh:ii">
			                    <input class="form-control" type="text" placeholder="開始時間" readonly id="editCardBeginTime">
								<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
							</div>
							<input type="text" id="time1" value="" style='display: none;' />
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">迄</label>
						<div class="col-sm-4">
							<div class="input-group date form_date" data-date="" data-date-format="yyyy/mm/dd" data-link-field="date2" data-link-format="yyyy/mm/dd">
								<input class="form-control" type="text" placeholder="結束日期" readonly id="editCardEndDate"> 
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<input type="text" id="date2" value="" style='display: none;' />
						</div>
						<div class="col-sm-4">
							<div class="input-group date form_time" data-date="" data-date-format="hh:ii" data-link-field="time2" data-link-format="hh:ii">
			                    <input class="form-control" type="text" placeholder="結束時間" readonly id="editCardEndTime">
								<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
							</div>
							<input type="text" id="time2" value="" style='display: none;' />
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="col-sm-2" style="text-align:left">Mail通知</label>
						<div class="col-sm-4">
							<div class="input-group date form_date" data-date="" data-date-format="yyyy/mm/dd" data-link-field="date2" data-link-format="yyyy/mm/dd">
								<input class="form-control" type="text" placeholder="結束日期" readonly id="editCardMailDate"> 
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<input type="text" id="date2" value="" style='display: none;' />
						</div>
						<div class="col-sm-4">
							<div class="input-group date form_time" data-date="" data-date-format="hh:ii" data-link-field="time2" data-link-format="hh:ii">
			                    <input class="form-control" type="text" placeholder="結束時間" readonly id="editCardMailTime">
								<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
							</div>
							<input type="text" id="time2" value="" style='display: none;' />
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">提醒</label>
						<div class="col-sm-4">
							<div class="input-group date form_date" data-date="" data-date-format="yyyy/mm/dd" data-link-field="date2" data-link-format="yyyy/mm/dd">
								<input class="form-control" type="text" placeholder="結束日期" readonly id="editCardAlarmDate"> 
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<input type="text" id="date2" value="" style='display: none;' />
						</div>
						<div class="col-sm-4">
							<div class="input-group date form_time" data-date="" data-date-format="hh:ii" data-link-field="time2" data-link-format="hh:ii">
			                    <input class="form-control" type="text" placeholder="結束時間" readonly id="editCardAlarmTime">
								<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
							</div>
							<input type="text" id="time2" value="" style='display: none;' />
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">地點</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" placeholder="地點" id="editCardLocation">
							<input type="hidden" id="editCardAddress">
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-primary" data-toggle="modal" onclick="sameAddress();">同公司地址</button>
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2" for="email">禮品</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" placeholder="禮品" id="editCardSouvenir">
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">訪問紀錄</label>
						<div class="col-sm-10 radio">
							<label><input type="radio" name="optradio" value="4">完成</label>
							<label><input type="radio" name="optradio" value="2">改期</label>
							<label><input type="radio" name="optradio" value="1">待處理</label>
							<label><input type="radio" name="optradio" value="3">取消</label>
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">訪談內容</label>
						<div class="col-sm-10">
							<textarea id="editCardTextArea" class="form-control" rows="5" style="margin-left: 0px; margin-right: 0px; width: 350px;"></textarea>
						</div>
					</div>
					<!-- <div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">文件上傳<br/>(最多2M)</label>
						<div class="col-sm-6">
							<input class="form-control-file form-control" type="file" id="file" name="file" />
						</div>
					</div> -->
				</form>
			</div>
            <!-- Modal Footer -->
<%--             <div class="modal-footer">
            	<center>
	                <button type="button" class="btn btn-primary">儲存</button>
	                <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
                </center>
            </div> --%>
        </div>
    </div>
</div>

<!-- updateModal -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" 
     aria-labelledby="updateModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
         	<div class="modal-header">
        		<button type="button" class="btn btn-primary" onclick="updateSubmit();">儲存</button>
		        <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button> 
      		</div>
            <!-- Modal Body -->
            <input type="hidden" id="updateGuid">
            <div class="modal-body">
				<form class="form-horizontal" id='updateForm' role="form" style='margin-top: 15px;'>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2" for="email">行程標題:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" placeholder="行程標題" id="updateTitle">
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2" for="email">預定項目:</label>
						<div class="col-sm-10">
							<select class="form-control" name="updateProject" id="updateProject">	
								<option value="">請選擇預定項目</option>
 								<c:forEach var="list" items="${dataMap.projectList}">
								   <option value="${list.guid}">${list.name}</option>
								</c:forEach> 
							</select>
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2" for="email">公司名稱</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" placeholder="公司名稱" id="updateCompanyName">
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" style="text-align:left" class="control-label col-sm-2">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="updateCardName" placeholder="姓名(必填)">
						</div>
						<div class="col-sm-4">
							<button type="button" class="btn btn-primary" data-toggle="modal" onclick="newName('update');">新增</button>
						</div>
						<input type="hidden" id="updateCardGuid" name="updateCardGuid"/>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">暱稱</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" placeholder="暱稱" id="updateNick">
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">起</label>
						<div class="col-sm-4">
							<div class="input-group date form_date" data-date="" data-date-format="yyyy/mm/dd" data-link-field="date1" data-link-format="yyyy/mm/dd">
								<input class="form-control" type="text" placeholder="開始日期" readonly id="updateStrartDate"> 
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<input type="text" id="date1" value="" style='display: none;' />
						</div>
						<div class="col-sm-4">
							<div class="input-group date form_time" data-date="" data-date-format="hh:ii" data-link-field="time1" data-link-format="hh:ii">
			                    <input class="form-control" type="text" placeholder="開始時間" readonly id="updateStrartTime">
								<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
							</div>
							<input type="text" id="time1" value="" style='display: none;' />
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">迄</label>
						<div class="col-sm-4">
							<div class="input-group date form_date" data-date="" data-date-format="yyyy/mm/dd" data-link-field="date2" data-link-format="yyyy/mm/dd">
								<input class="form-control" type="text" placeholder="結束日期" readonly id="updateEndDate"> 
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<input type="text" id="date2" value="" style='display: none;' />
						</div>
						<div class="col-sm-4">
							<div class="input-group date form_time" data-date="" data-date-format="hh:ii" data-link-field="time2" data-link-format="hh:ii">
			                    <input class="form-control" type="text" placeholder="結束時間" readonly id="updateEndTime">
								<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
							</div>
							<input type="text" id="time2" value="" style='display: none;' />
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="col-sm-2" style="text-align:left">Mail通知</label>
						<div class="col-sm-4">
							<div class="input-group date form_date" data-date="" data-date-format="yyyy/mm/dd" data-link-field="date2" data-link-format="yyyy/mm/dd">
								<input class="form-control" type="text" placeholder="結束日期" readonly id="updateMailDate"> 
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<input type="text" id="date2" value="" style='display: none;' />
						</div>
						<div class="col-sm-4">
							<div class="input-group date form_time" data-date="" data-date-format="hh:ii" data-link-field="time2" data-link-format="hh:ii">
			                    <input class="form-control" type="text" placeholder="結束時間" readonly id="updateMailTime">
								<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
							</div>
							<input type="text" id="time2" value="" style='display: none;' />
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">提醒</label>
						<div class="col-sm-4">
							<div class="input-group date form_date" data-date="" data-date-format="yyyy/mm/dd" data-link-field="date2" data-link-format="yyyy/mm/dd">
								<input class="form-control" type="text" placeholder="結束日期" readonly id="updateAlarmDate"> 
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<input type="text" id="date2" value="" style='display: none;' />
						</div>
						<div class="col-sm-4">
							<div class="input-group date form_time" data-date="" data-date-format="hh:ii" data-link-field="time2" data-link-format="hh:ii">
			                    <input class="form-control" type="text" placeholder="結束時間" readonly id="updateAlarmTime">
								<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
							</div>
							<input type="text" id="time2" value="" style='display: none;' />
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">地點</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" placeholder="地點" id="updateLocation">
							<input type="hidden" id="updateAddress">
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-primary" data-toggle="modal" onclick="sameAddress();">同公司地址</button>
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2" for="email">禮品</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" placeholder="禮品" id="updateSouvenir">
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">訪問紀錄</label>
						<div class="col-sm-10 radio">
							<label><input type="radio" name="updateOptradio" value="4">完成</label>
							<label><input type="radio" name="updateOptradio" value="2">改期</label>
							<label><input type="radio" name="updateOptradio" value="1">待處理</label>
							<label><input type="radio" name="updateOptradio" value="3">取消</label>
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">訪談內容</label>
						<div class="col-sm-10">
							<textarea id="updateTextArea" class="form-control" rows="5" style="margin-left: 0px; margin-right: 0px; width: 350px;"></textarea>
						</div>
					</div>
<!-- 					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">文件上傳<br/>(最多2M)</label>
						<div class="col-sm-6">
							<input class="form-control-file form-control" type="file" id="file" name="file" />
						</div>
					</div> -->
				</form>
			</div>
        </div>
    </div>
</div>

<!-- viewModal -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" 
     aria-labelledby="viewModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
         	<div class="modal-header">
        		<!-- <button type="button" class="btn btn-primary" onclick="updateSubmit();">儲存</button> -->
		        <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button> 
      		</div>
            <!-- Modal Body -->
            <input type="hidden" id="updateGuid">
            <div class="modal-body">
				<form class="form-horizontal" id='updateForm' role="form" style='margin-top: 15px;'>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2" for="email">行程標題:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" placeholder="行程標題" id="viewTitle" readonly>
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2" for="email">預定項目:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="viewProject" readonly>
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2" for="email">公司名稱</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" placeholder="公司名稱" id="viewCompanyName" readonly>
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" style="text-align:left" class="control-label col-sm-2">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="viewCardName" readonly>
						</div>
						<input type="hidden" id="updateCardGuid" name="viewCardGuid"/>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">暱稱</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="viewNick" readonly>
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">起</label>
						<div class="col-sm-4">
							<input class="form-control" type="text" placeholder="開始日期" readonly id="viewStrartDate"> 
						</div>
						<div class="col-sm-4">
							<input class="form-control" type="text" placeholder="開始時間" readonly id="viewStrartTime">
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">迄</label>
						<div class="col-sm-4">
							<input class="form-control" type="text" placeholder="結束日期" readonly id="viewEndDate"> 
						</div>
						<div class="col-sm-4">
							<input class="form-control" type="text" placeholder="結束時間" readonly id="viewEndTime">
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="col-sm-2" style="text-align:left">Mail通知</label>
						<div class="col-sm-4">
							<input class="form-control" type="text" placeholder="結束日期" readonly id="viewMailDate"> 
						</div>
						<div class="col-sm-4">
							<input class="form-control" type="text" placeholder="結束時間" readonly id="viewMailTime">
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">提醒</label>
						<div class="col-sm-4">
							<input class="form-control" type="text" placeholder="結束日期" readonly id="viewAlarmDate"> 
						</div>
						<div class="col-sm-4">
							<input class="form-control" type="text" placeholder="結束時間" readonly id="viewAlarmTime">
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">地點</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="viewLocation" readonly>
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2" for="email">禮品</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" placeholder="禮品" id="viewSouvenir" readonly>
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">訪問紀錄</label>
						<div class="col-sm-10 radio">
							<input type="text" class="form-control" placeholder="訪問紀錄" id="viewOptradio" readonly>
						</div>
					</div>
					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">訪談內容</label>
						<div class="col-sm-10">
							<textarea readonly id="viewTextArea" class="form-control" rows="5" style="margin-left: 0px; margin-right: 0px; width: 350px;"></textarea>
						</div>
					</div>
<!-- 					<div class="form-group">
						<label style="text-align:left" class="control-label col-sm-2">文件上傳<br/>(最多2M)</label>
						<div class="col-sm-6">
							<input class="form-control-file form-control" type="file" id="file" name="file" />
						</div>
					</div> -->
				</form>
			</div>
        </div>
    </div>
</div>

<!-- newNameModal -->
<div class="modal fade" id="newNameModal" tabindex="-1" role="dialog" 
     aria-labelledby="nameModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
       	   <div class="modal-header">
        		<button type="button" class="btn btn-primary" onclick="newNameSubmit();">儲存</button>
		        <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="newNameClose();">取消</button> 
      		</div>
            <!-- Modal Body -->
            <div class="modal-body">
				<form id="cardCheckBox" class="form-horizontal" id='nameModal' role="form" style='margin-top: 15px;'>
						<table class="table table-bordered">
							<thead>
								<tr>
									<th><input style="display: none"></th>
									<th>姓名</th>
								</tr>
							</thead>
							<tbody>
									<c:forEach items="${dataMap.cardList}" var="list">
										<tr>
											<td width="5%"><input type="checkbox" name="${list.name}" id="cardGuid" value="${list.guid}" onclick="chooseOne(this);"></td>
											<td>
												${list.name}
												<input type="hidden" id="${list.guid}" value="${list.companyAddress}"/>
											</td>
										</tr>
									</c:forEach>
							</tbody>
						</table>
					</form>
			</div>
        </div>
    </div>
</div>

<!-- deleteModal -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" 
     aria-labelledby="deleteModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Body -->
            <div class="modal-body">
				<form class="form-horizontal" id='deleteModal' role="form" style='margin-top: 15px;'>
					<label id="">確定要刪除已選取資料?</label>
				</form>
			</div>
            <!-- Modal Footer -->
            <div class="modal-footer form-group">
            	<button type="button" class="btn btn-danger pull-right " data-dismiss="modal">取消</button>
            	<button type="button" class="btn btn-primary pull-right " onclick="deleteModalSubmit();">確定</button>
            </div>
        </div>
    </div>
</div>


<!-- alertModal -->
<div class="modal fade" id="alertModal" tabindex="-1" role="dialog" 
     aria-labelledby="alertModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Body -->
            <div class="modal-body">
				<form class="form-horizontal" id='alertModal' role="form" style='margin-top: 15px;'>
					<label id="alertTitle"></label>
				</form>
			</div>
            <!-- Modal Footer -->
            <div class="modal-footer">
	                <button type="button" class="btn btn-danger pull-right" data-dismiss="modal" onclick="alertClose();">取消</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>