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
	margin: 0 auto;
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
<form class="form-center" action="create" method="post" enctype="multipart/form-data">
	<div class="form-group">
		<center>
			<label>${msg}</label>
		</center>
	</div>
	<table cellpadding="10">
		<tr>
			<th>
				<div class="formField" data-attr="sampleDropDownList">
					<label for="BoSample_sampleDropDownList" class="required"><span class="required" style="color:red;">*</span> 類別</label>
					<div class="fieldBlock" fullWidth="false">
						<div style="display:inline" id="BoSample_sampleDropDownList_field">
							<select separator="&amp;nbsp;&amp;nbsp;" class="form-control" name="" id="" disabled="true">	
								<option value="" selected="selected">類別3</option>
								<option value="">類別1</option>
								<option value="">類別2</option>
								<option value="">類別3</option>
							</select>
						</div>
					</div>
				</div>
			</th>
			<td>
				<div class="formField" data-attr="sampleDropDownList">
					<label for="BoSample_sampleDropDownList" class="required"><span class="required" style="color:red;">*</span> 屬性</label>
					<div class="fieldBlock" fullWidth="false">
						<div style="display:inline" id="BoSample_sampleDropDownList_field">
							<select separator="&amp;nbsp;&amp;nbsp;" class="form-control" name="" id="" disabled="true">	
								<option value="" selected="selected">屬性2</option>
								<option value="">屬性1</option>
								<option value="">屬性2</option>
								<option value="">屬性3</option>
							</select>
						</div>
					</div>
				</div>
			</td>
			<td>
				<div class="formField" data-attr="sampleDropDownList">
					<label for="BoSample_sampleDropDownList" class="required"><span class="required" style="color:red;">*</span> 狀態</label>
					<div class="fieldBlock" fullWidth="false">
						<div style="display:inline" id="BoSample_sampleDropDownList_field">
							<select separator="&amp;nbsp;&amp;nbsp;" class="form-control" name="" id="" disabled="true">	
								<option value="" selected="selected">狀態1</option>
								<option value="">狀態1</option>
								<option value="">狀態2</option>
								<option value="">狀態3</option>
							</select>
						</div>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td COLSPAN=2>
				<label for="account">承辦業務:  </label>
				<input type="text" name="sales" value="admin" readonly="readonly">
			</td>
		</tr>
		<tr>
			<th>
				<div class="form-group">
					<label for="account">帳號:</label> 
					<input type="text" value="admin" readonly="readonly">
					<%-- <jsp:include page="${requestScope.inputForm}">
						<jsp:param value="account" name="field"/>
						<jsp:param value="text" name="type"/>
					</jsp:include> --%>
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">抬頭:</label> 
					<input type="text" value="test" readonly="readonly">
					<%-- <jsp:include page="${requestScope.inputForm}">
						<jsp:param value="title" name="field"/>
						<jsp:param value="text" name="type"/>
					</jsp:include> --%>
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">匿稱:</label> 
					<input type="text" value="nickname" readonly="readonly">
					<%-- <jsp:include page="${requestScope.inputForm}">
						<jsp:param value="nickname" name="field"/>
						<jsp:param value="text" name="type"/>
					</jsp:include> --%>
				</div>
			</th>
		</tr>
		<tr>
			<th>
				<div class="form-group">
					<label for="account">手機號碼:</label>
					<input type="text" value="0000000" readonly="readonly"> 
					<%-- <jsp:include page="${requestScope.inputForm}">
						<jsp:param value="phone" name="field"/>
						<jsp:param value="text" name="type"/>
					</jsp:include> --%>
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">電子郵件:</label> 
					<input type="text" value="test001@1234" readonly="readonly">
					<%-- <jsp:include page="${requestScope.inputForm}">
						<jsp:param value="mail" name="field"/>
						<jsp:param value="text" name="type"/>
					</jsp:include> --%>
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">公司名稱1:</label> 
					<input type="text" value="companyName" readonly="readonly">
					<%-- <jsp:include page="${requestScope.inputForm}">
						<jsp:param value="companyName1" name="field"/>
						<jsp:param value="text" name="type"/>
					</jsp:include> --%>
				</div>
			</th>
		</tr>
		<tr>
			<th>
				<div class="form-group">
					<label for="account">公司名稱2:</label> 
					<input type="text" value="companyName" readonly="readonly">
					<%-- <jsp:include page="${requestScope.inputForm}">
						<jsp:param value="companyName2" name="field"/>
						<jsp:param value="text" name="type"/>
					</jsp:include> --%>
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="address1">公司/聯絡地址1:</label> 
					<input type="text" value="address" readonly="readonly">
					<%-- <jsp:include page="${requestScope.inputForm}">
						<jsp:param value="address1" name="field"/>
						<jsp:param value="text" name="type"/>
					</jsp:include> --%>
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">公司/聯絡地址2:</label>
					<input type="text" value="address2" readonly="readonly"> 
					<%-- <jsp:include page="${requestScope.inputForm}">
						<jsp:param value="address2" name="field"/>
						<jsp:param value="text" name="type"/>
					</jsp:include> --%>
				</div>
			</th>
		</tr>
		<tr>
			<th>
				<div class="form-group">
					<label for="account">公司電話:</label> 
					<input type="text" value="00001234" readonly="readonly">
					<%-- <jsp:include page="${requestScope.inputForm}">
						<jsp:param value="companyPhone" name="field"/>
						<jsp:param value="text" name="type"/>
					</jsp:include> --%>
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">特徵:</label>
					<input type="text" value="00001234" readonly="readonly"> 
					<%-- <jsp:include page="${requestScope.inputForm}">
						<jsp:param value="feature" name="field"/>
						<jsp:param value="text" name="type"/>
					</jsp:include> --%>
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">生日:</label> 
					<input type="text" value="00001234" readonly="readonly">
					<%-- <jsp:include page="${requestScope.inputForm}">
						<jsp:param value="birthday" name="field"/>
						<jsp:param value="text" name="type"/>
					</jsp:include> --%>
				</div>
			</th>
		</tr>
		<tr>
			<th>
				<div class="form-group">
					<label for="account">婚姻:</label> 
					<input type="text" value="y" readonly="readonly">
					<%-- <jsp:include page="${requestScope.inputForm}">
						<jsp:param value="married" name="field"/>
						<jsp:param value="text" name="type"/>
					</jsp:include> --%>
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">初識時間:</label> 
					<input type="text" value="00001234" readonly="readonly">
					<%-- <jsp:include page="${requestScope.inputForm}">
						<jsp:param value="acquaintanceTime" name="field"/>
						<jsp:param value="text" name="type"/>
					</jsp:include> --%>
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">初識地點:</label> 
					<input type="text" value="00001234" readonly="readonly">
					<%-- <jsp:include page="${requestScope.inputForm}">
						<jsp:param value="acquaintanceLocation" name="field"/>
						<jsp:param value="text" name="type"/>
					</jsp:include> --%>
				</div>
			</th>
		</tr>
		<tr>
			<th>
				<div class="form-group">
					<label for="account">備註:</label> 
					<input type="text" value="heeloo world" readonly="readonly">
					<%-- <jsp:include page="${requestScope.inputForm}">
						<jsp:param value="remark" name="field"/>
						<jsp:param value="text" name="type"/>
					</jsp:include> --%>
				</div>
			</th>
		</tr>
	</table>
<%-- 	<div class="form-group" >
		<label for="file">個人照片:</label> 
		<jsp:include page="${requestScope.fileForm}">
			<jsp:param value="file" name="field"/>
		</jsp:include>
	</div> --%>
	
<%-- 	<div class="form-group">
		<label for="replyPassword">確認密碼:</label> 
		<jsp:include page="${requestScope.inputForm}">
			<jsp:param value="replyPassword" name="field"/>
			<jsp:param value="password" name="type"/>
		</jsp:include>
	</div>
	<center>
		<button type="submit" class="btn btn-default">確定</button>
	</center> --%>
</form>

<div title="Sample Load Multiple Field" style="text-align:center;">人脈關係</div>
	<input type="hidden" value="" name="BoSample[sampleLoadMultipleField]" id="BoSample_sampleLoadMultipleField" />
	<div align="center">
		<table class="easyui-datagrid" id="wgLoadMultipleFieldItems" idField="guid" fit="false" rownumbers="false" pagination="false" striped="true" pageList="[25,50,100]" pageSize="25" nowrap="false" onBeforeLoad="false" multiHeader="" for="BoSample[sampleLoadMultipleField]" width="50%" height="auto" data-options="onLoadSuccess:onLoadSuccess" disable="true">
			<thead data-options="frozen:true">
				<tr>
					<th checkbox="true" field="guid" data-options="">
						<label for="BoSampleOption_guid">Guid</label>
					</th>
					<th halign="center" width="30%" field="serial" data-options="">
						<label for="BoSampleOption_serial" class="required">
							<span class="required" style="color:red;">*</span> 編號
						</label>
					</th>
				</tr>
			</thead>
			<thead>
				<tr>
					<th halign="center" width="30%" field="title" data-options="">
						<label style="margin:0;" for="BoSampleOption_title" class="required">
							<span class="required" style="color:red;">*</span> 標題
						</label>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td></td>
					<td>001</td>
					<td>name1</td>
				</tr>
				<tr>
					<td></td>
					<td>002</td>
					<td>name2</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>