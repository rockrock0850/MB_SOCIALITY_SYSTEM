<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="width=device-width; charset=UTF-8">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap/easyui.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/color.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/icon.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.easyui.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/easyui-lang-zh_TW.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/easyui.js"></script>
<title>註冊</title>
<style type="text/css">
.form-center {
	margin: 5px;
	width: 40%; /* value of your choice which suits your alignment */
}

</style>
<script type="text/javascript">

//資料載入完成動作
function onLoadSuccess(data) {
    dgCls.onFormLoadSuccess(this, data);
}

function Cancel() {
    dialogCls.cancel();
}

function Close() {
    dialogCls.close();
}

function Reload() {
    dialogCls.reload();
}

function Save() {
    dialogCls.save();
}
	var dgCls = dataGridClass.getNew();
	//easyui dialog相關操作物件
	var dialogCls = dialogClass.getNew();

	function onBeforeOpen() {
		return dialogCls.onBeforeOpen();
	}

	function onOpened() {
		dialogCls.onOpened();
	}

	function onClosed() {
		dialogCls.onClosed();
	}
	
	// 錯誤訊息
	var displayMsg = {
		loading : '資料載入中，請稍後。。。',
		abort : '未儲存的資料將會遺失，點擊「確定」確認放棄資料',
		notice : '注意事項!',
		alert : '系統提示',
		empty : {
			modify : '請先選擇您要維護的資料',
			view : '請先選擇您要檢視的資料',
			invalid : '請先選擇您要作廢的資料',
			deleted : '請先選擇您要刪除的資料',
		},
		deleted : {
			title : '資料刪除確認',
			confirm : '是否刪除下列資料?',
			button : {
				yes : '確認',
				no : '取消'
			},
			success : '資料已成功刪除！'
		},
		cacheSave : {
			success : '資料已成功儲存！'
		},
		configSave : {
			success : '資料已成功設定儲存！'
		}
	};
	
	function WgLoadSingleField41Click(){
        parent.$('.dialog-toolbar').hide();
        $('#eventDialog').dialog({
            fit: false,
            title: '載入',
            modal: true,
            closed: false,
            closable: false,
            left: 0,
            top: $(document).scrollTop(),
            width: document.documentElement.clientWidth,
            height: document.documentElement.clientHeight
        });
        $('#eventDialogFrame').attr('src','single');
        //$('#eventDialogFrame').attr('data-PopSelectFunction','WgLoadSingleField41Search');
    }
		
	function WgLoadMultipleField1Click(){
        
        parent.$('.dialog-toolbar').hide();
        $('#eventDialog').dialog({
            fit: false,
            title: '載入',
            modal: true,
            closed: false,
            closable: false,
            left: 0,
            top: $(document).scrollTop(),
            width: document.documentElement.clientWidth,
            height: document.documentElement.clientHeight
        });
        $('#eventDialogFrame').attr('src','multiple');
        //$('#eventDialogFrame').attr('data-PopSelectFunction','WgLoadMultipleField1Search');
    }
	
	var wgLoadMultipleFieldItemsGridData = [];
	
	$(function(){
	    if (parent.$("#eventDialog").size()) {
	        parent.$("#eventDialog").dialog({toolbar:[{text:'<button class="btn btn-danger" title="關閉" data-blank="false" style="">&nbsp;<i class="glyphicon glyphicon-remove-circle" />&nbsp;關閉</button>',handler:function(){Close($(this))}},{text:'<button class="btn btn-primary" title="上一筆" data-blank="true" style="">&nbsp;<i class="glyphicon glyphicon-backward" />&nbsp;上一筆</button>',handler:function(){Prev($(this))}},{text:'<button class="btn btn-primary" title="下一筆" data-blank="false" style="">&nbsp;<i class="glyphicon glyphicon-forward" />&nbsp;下一筆</button>',handler:function(){Next($(this))}}]});
	    } else {
	        var pageBtns = [{text:'<button class="btn btn-danger" title="關閉" data-blank="false" style="">&nbsp;<i class="glyphicon glyphicon-remove-circle" />&nbsp;關閉</button>',handler:function(){Close($(this))}},{text:'<button class="btn btn-primary" title="上一筆" data-blank="true" style="">&nbsp;<i class="glyphicon glyphicon-backward" />&nbsp;上一筆</button>',handler:function(){Prev($(this))}},{text:'<button class="btn btn-primary" title="下一筆" data-blank="false" style="">&nbsp;<i class="glyphicon glyphicon-forward" />&nbsp;下一筆</button>',handler:function(){Next($(this))}}];
	        var btnDiv = $('<div></div>');
	        $.each(pageBtns, function() {
	            btnDiv.append($(this.text).css('margin', '5px').on('click', this.handler));
	        });
	        btnDiv.insertBefore('form');
	    }
	});
</script>
<jsp:include page="../constant.jsp" />
</head>
<body>
<div id="errorDialog" style="-webkit-overflow-scrolling:touch; overflow:auto;height:0px;" class="easyui-dialog" closed="true"></div><div id="eventDialog" style="-webkit-overflow-scrolling:touch; overflow:auto;height:0px;" class="easyui-dialog" closed="true" data-options="onBeforeOpen:onBeforeOpen, onOpen:onOpened, onClose:onClosed"><iframe id="eventDialogFrame" style="width: 100%; height: 100%" border="0" frameborder="0"></iframe></div> 
<%-- <jsp:include page="${requestScope.menu}">
	<jsp:param value="user" name="module" />
	<jsp:param value="清單" name="item" />
	<jsp:param value="delete" name="action1" />
	<jsp:param value="刪除" name="item1" />
	<jsp:param value="update" name="action2" />
	<jsp:param value="更新" name="item2" />
	<jsp:param value="create" name="action3" />
	<jsp:param value="註冊" name="item3" />
	<jsp:param value="login" name="action4" />
	<jsp:param value="登入" name="item4" />
	<jsp:param value="logout" name="action5" />
	<jsp:param value="登出" name="item5" />
</jsp:include> --%>
<hr class="divider1">
<form id="_form" class="form-center" method="post" enctype="multipart/form-data">
	<img style='display: none' id="blah" src="${responseVO.imagePath}" alt="名片" /><br/>
	上傳名片
	<input class="form-control-file" type="file" id="file" name="file" onchange="readURL(this)" />
	<input type="hidden" id="imagePath" name="imagePath" value="${responseVO.imagePath}"/>
	<br/>
	<table cellpadding="10">
		<tr>
			<th>
				<div class="formField" data-attr="sampleDropDownList">
					<label for="BoSample_sampleDropDownList" class="required"> 類別</label>
					<div class="fieldBlock" fullWidth="false">
						<div style="display:inline" id="BoSample_sampleDropDownList_field">
							<c:forEach var="list" items="${responseVO.category}">
	 								<c:choose>
									  <c:when test="${list.guid == responseVO.bBussinessCardCategoryGuid}">
										   <input readonly type="text" name="name" id="name" value="${list.name}">
									  </c:when>
									</c:choose>
							</c:forEach> 
						</div>
					</div>
				</div>
			</th>
			<td>
				<div class="formField" data-attr="sampleDropDownList">
					<label for="BoSample_sampleDropDownList" class="required">屬性</label>
					<div class="fieldBlock" fullWidth="false">
						<div style="display:inline" id="BoSample_sampleDropDownList_field">
 								<c:forEach var="list" items="${responseVO.attribute}">
								   <c:choose>
									  <c:when test="${list.guid == responseVO.bBussinessCardAttributeGuid}">
										   <input readonly type="text" name="name" id="name" value="${list.name}">
									  </c:when>
									</c:choose>
								</c:forEach> 
						</div>
					</div>
				</div>
			</td>
			<td>
				<div class="formField" data-attr="sampleDropDownList">
					<label for="BoSample_sampleDropDownList" class="required">狀態</label>
					<div class="fieldBlock" fullWidth="false">
						<div style="display:inline" id="BoSample_sampleDropDownList_field">
 								<c:forEach var="list" items="${responseVO.status}">
								   <c:choose>
									  <c:when test="${list.guid == responseVO.bBussinessCardStatusGuid}">
										   <input readonly type="text" name="name" id="name" value="${list.name}">
									  </c:when>
									</c:choose>
								</c:forEach> 
						</div>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td >
				<label for="account">承辦業務:  </label>
				<input type="hidden" name="bEnterpriseEmployeeGuid" value="${responseVO.bEnterpriseEmployeeGuid }" data-WgLoadSingleField="WgLoadSingleField41_guid" >
				<input type="text" name="employeeName" value="${responseVO.employeeName}" data-WgLoadSingleField="WgLoadSingleField41_title" readonly/>
			</td>
		</tr>
		<tr>
			<th>
				<div class="form-group">
					<span class="required" style="color:red;">*</span> <label for="account">姓名:</label> 
					<input readonly type="text" name="name" id="name" value="${responseVO.name}">
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">抬頭:</label> 
					<input readonly type="text" name="title" id="title" value="${responseVO.title}">
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">暱稱:</label> 
					<input readonly type="text" name="nickname" id="nickname" value="${responseVO.nickname}">
				</div>
			</th>
		</tr>
		<tr>
			<th>
				<div class="form-group">
					<label for="account">手機號碼:</label> 
					<input readonly type="text" name="cellphoneNumber" id="cellphone" value="${responseVO.cellphoneNumber}">
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">電子郵件:</label> 
					<input readonly type="text" name="email" id="email" value="${responseVO.email}">
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">公司名稱:</label> 
					<input readonly type="text" name="companyName" id="companyName" value="${responseVO.companyName}">
				</div>
			</th>
		</tr>
		<tr>
			<th>
				<div class="form-group">
					<label for="address1">公司/聯絡地址:</label> 
					<input readonly type="text" name="companyAddress" id="address" value="${responseVO.companyAddress}">
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="account">公司電話:</label> 
					<input readonly type="text" name="companyPhoneNumber" id="companyPhone" value="${responseVO.companyPhoneNumber}">
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">特徵:</label> 
					<input readonly type="text" name="feature" id="feature" value="${responseVO.feature}">
				</div>
			</th>
		</tr>
		<tr>
			<th>
				<div class="form-group">
					<label for="account">生日:(請輸入西元年/月/日)</label>
					<input readonly type="text" name="birthday" id="birthday" value="${responseVO.birthday}">
				</div> 
			</th>
			<th>
				<div class="form-group">
					<label for="account">婚姻:</label> 
					<input readonly type="text" name="marrage" id="married" value="${responseVO.marrage}">
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">初識時間:</label> 
					<input readonly type="text" name="firstMeetTimeString" id="firstMeetTimeString" value="${responseVO.firstMeetTimeString}">
				</div>
			</th>
		</tr>
		<tr>
			<th>
				<div class="form-group">
					<label for="password">初識地點:</label> 
					<input readonly type="text" name="firstMeetLocation" id="firstMeetLocation" value="${responseVO.firstMeetLocation}">
				</div>
			</th>
			<td>
				<div class="form-group">
					<label for="account">備註:</label>
					<input readonly type="text" name="remark" id="remark" value="${responseVO.remark}"> 
				</div>
			</td>
		</tr>
	</table>
	<input type="hidden" id="guid" name="guid" value="${responseVO.guid}"/>
</form>
</body>
</html>