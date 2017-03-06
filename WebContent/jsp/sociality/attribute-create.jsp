<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="width=device-width; charset=UTF-8">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap/easyui.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/color.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/icon.css" rel="stylesheet">

<script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.min.js"></script>
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
	
	/**
	 * 簡易訊息
	 */
	function MessageSimple(wording,style){
		if(typeof(style) == 'undefined'){
			style = 'danger';
		}
		var msg = '<div class="alert alert-'+style+'"><p>注意事項:</p><ul><li>'+wording+'</li></ul></div>';
		FormMessageOpen(msg);
	}
	/**
	 * 簡易訊息：沒有找到資料
	 */
	function MessageNoDataFound(){
		var wording = 'No data found';
		MessageSimple(wording,'warning');
	}
	/**
	 * 開啟錯誤視窗
	 */
	function FormMessageOpen(msg,modal){
		$('#errorDialog').html('');
	    $('#errorDialog').append(msg);
	    $('#errorDialog').parent().end().dialog({
	        fit : false,
	        title : '提示訊息',
	        modal : modal,
	        closed : false,
	        closable : true,
	        draggable: false,
	        buttons : false,
	        toolbar: false,
	        maximizable:true,
	        collapsible:true,
	        width: 350,
	        height:'auto',
	        left:'auto',
	        right:0,
	        top:0,
	        shadow: false
	    });
	    $('#errorDialog').css({width: 350, height:'auto'});
		$('#errorDialog').parent().css({position : "fixed", width: 350, height:'auto',left:'auto',right:0,top:0});
		$('.window-mask').css({position:"fixed"});
	}
	
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
	
	$(function() {
		if (parent.$("#eventDialog").size()) {
			parent
					.$("#eventDialog")
					.dialog(
							{
								toolbar : [
										{
											text : '<button class="btn btn-danger" title="取消" data-blank="false" style="">&nbsp;<i class="glyphicon glyphicon-ban-circle" />&nbsp;取消</button>',
											handler : function() {
												Cancel($(this))
											}
										},
										{
											text : '<button class="btn btn-warning" title="重新填寫" data-blank="false" style="">&nbsp;<i class="glyphicon glyphicon-refresh" />&nbsp;重新填寫</button>',
											handler : function() {
												Reload($(this))
											}
										},
										{
											text : '<button class="btn btn-success" title="儲存" data-blank="true" style="">&nbsp;<i class="glyphicon glyphicon-floppy-disk" />&nbsp;儲存</button>',
											handler : function() {
												Save($(this))
											}
										} ]
							});
		} else {
			var pageBtns = [
					{
						text : '<button class="btn btn-danger" title="取消" data-blank="false" style="">&nbsp;<i class="glyphicon glyphicon-ban-circle" />&nbsp;取消</button>',
						handler : function() {
							Cancel($(this))
						}
					},
					{
						text : '<button class="btn btn-warning" title="重新填寫" data-blank="false" style="">&nbsp;<i class="glyphicon glyphicon-refresh" />&nbsp;重新填寫</button>',
						handler : function() {
							Reload($(this))
						}
					},
					{
						text : '<button class="btn btn-success" title="儲存" data-blank="true" style="">&nbsp;<i class="glyphicon glyphicon-floppy-disk" />&nbsp;儲存</button>',
						handler : function() {
							Save($(this))
						}
					} ];
			var btnDiv = $('<div></div>');
			$.each(pageBtns, function() {
				btnDiv.append($(this.text).css('margin', '5px').on('click',
						this.handler));
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
<form id="_form" class="form-center" action="attribute_create" method="post" enctype="multipart/form-data">
	<input type="hidden" id="msg" value="${errorMsg.result}"/>
	<c:choose>
	  <c:when test="${errorMsg != null}">
	    <script language="javascript">
        	MessageSimple($('#msg').val());
   	 	 </script>
	  </c:when>
	</c:choose>
	<table cellpadding="10">
		<th>
			<div class="form-group">
				<span class="required" style="color:red;">*</span><label for="account">屬性名稱:</label> 
				<jsp:include page="${requestScope.inputForm}">
					<jsp:param value="name" name="field"/>
					<jsp:param value="text" name="type"/>
				</jsp:include>
			</div>
		</th>
	</table>
</form>
</body>
</html>