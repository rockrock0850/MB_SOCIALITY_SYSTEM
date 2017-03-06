<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="width=device-width; charset=UTF-8">
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

<title>名片夾</title>
<script type="text/javascript">
	$(function() {
		$('#listGrid').datagrid({
		    width: '900',
		    url:"api/card_contact_list",
		    rownumbers: true,
		    columns:[[
		        { field:'guid',checkbox:true, width:"10%"}, 
		        { field: 'companyName', title: '公司名稱', align:'center', width:"22%"},
		        { field: 'name', title: '姓名', align:'center', width:"18%"},
		        { field: 'nickname', title: '暱稱', align:'center', width:"18%"},
		        { field: 'phone', title: '手機號碼', align:'center', width:"18%"},
		    ]],
		    idField:'guid',
		    singleSelect: false,
		    selectOnCheck: true,
		    checkOnSelect: true,
		    fitColumns:true
		});
	});
	
	function ajaxRequest(config) {
	    $.ajax({
	        url: config.url,
	        data: config.data,
	        type: (config.type != undefined)?config.type:'POST',
	        async: (config.async != undefined)?config.async:true,
	        dataType: (config.dataType != undefined)?config.dataType:'text',
	        beforeSend: function(xhr, settings) {
	            if (config.modal != false) {
	                $('body').css('overflow','hidden');
	                $("<div for=\"ajax\" class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo($('body'));
	                $("<div for=\"ajax\" class=\"datagrid-mask-msg\" style=\"padding-top: 3px;height: 30px;\"></div>").html('資料載入中，請稍後。。。').appendTo($('body')).css({display:"block",left:($('body').width() / 2)-40,top:(document.body.clientHeight/2)+$(document).scrollTop()});
	            }
	            if (config.beforeSend != undefined) {
	        	        return config.beforeSend(xhr, settings);
	            }
	        }
	    }).done(function(response){
	        if (config.done != undefined) {
	            config.done(response);
	        }
	    }).fail(function(jqXHR, textStatus){
	        if (config.fail != undefined) {
	            config.fail(jqXHR, textStatus);
	        } else {
	            if(textStatus !== 'canceled') {
	        	        $.messager.alert('ajax error', jqXHR.responseText, 'error');
	            }
	        }
	    }).always(function(jqXHR, textStatus){
	        $('body').css('overflow','auto');
	        $('.datagrid-mask[for=ajax]').remove();
	        $('.datagrid-mask-msg[for=ajax]').remove();
	        if (config.always != undefined) {
	            config.always(jqXHR, textStatus);
	        }
	    });
	}

	var listGridColumnsToolbar = [{text:'<button class="btn btn-primary" title="人脈" ColumnsBtn="人脈" data-blank="false" style="padding:1px 2px;">人脈',handler:'ColumnsCategories($(this))'}];
	
	// 依listGridColumnsToolbar 產生單列操作按鈕
	function GenerateListGridColumnsToolbar(value,row,rowIndex){
		/* return '<button type="button" class="btn btn-primary btn-sm" id="columnsButton">人脈</button>'; */
		var toolbar = $('<div/>');
		$.each(listGridColumnsToolbar,function(index,columns){
			var btn = $('<div/>').attr('style','display:table-cell').attr('rowIndex',rowIndex).attr('onclick',columns.handler+'; return false;').append(columns.text);
			toolbar.append(btn);
		});
		return '<center><div class="opt-btn" >'+toolbar.html()+'</div></center>';
	}
	
	$('#columnsButton').on('click', function(event) {
		var row = dgCls.getClicked($('#listGrid'), buttonObj);
	    alert(row.guid);  
	});
	
	/**
	 * 設定人脈
	 */
	function ColumnsCategories(buttonObj) {
	    var row = dgCls.getClicked($('#listGrid'), buttonObj);
		if (row === false) {
	        $.messager.alert(displayMsg.notice, displayMsg.empty.modify, 'warning');
	    } else {
/* 	        var tabPanel = parent.$('#contentTabs');
	        var selTab = tabPanel.tabs('getSelected'); */
	        $('#eventDialog').dialog({
	            fit : true,
	            title : '人脈',
	            modal : true,
	            closed : false,
	            closable : false,
	            buttons : false,
	            toolbar : false,
	            left : 0,
	            top : 0
	        });
	        $('#eventDialogFrame').attr('src', 'card_contact/'+row.guid);
	    }
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

	var dgCls = dataGridClass.getNew();

	// easyui dialog相關操作物件
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
	// easyui messager 相關操作物件
	var msgCls = messagerClass.getNew();

	// easyui datagrid載入資料前動作
	function onBeforeLoad(param) {
		return dgCls.onBeforeLoad(param);
	}

	// 資料載入完成動作
	function onLoadSuccess(data) {
		dgCls.onListLoadSuccess(this, data);
	}
	
	//Pop關閉
	function PopClose() {
	dialogCls.close();
	}
	
	function WgLoadMultipleField1Click(){
		var checkedItems = $('#listGrid').datagrid('getChecked');
	    var guidList = [];
	    $.each(checkedItems, function(index, item){
	    	guidList.push(item.guid);
	    }); 
	    
	    var json = {
	    		guidList:guidList
	    };
	    
	    //alert(guidList);
	    $.ajax({
	        url : 'api/contact_create',
	        type : 'POST',
	        data : JSON.stringify(json),
	        cache : false,
	        dataType : 'json',
	        processData : false,
	        contentType: "application/json",
	        success : function(response) {
	        	parent.$('#listGrid').datagrid('reload');
	        	PopClose();
	        }
	    });
	}
	
</script>
<jsp:include page="../constant.jsp" />
</head>
<body>
	<div id="errorDialog"
		style="-webkit-overflow-scrolling: touch; overflow: auto; height: 0px;"
		class="easyui-dialog" closed="true"></div>
	<div id="eventDialog"
		style="-webkit-overflow-scrolling: touch; overflow: auto; height: 0px;"
		class="easyui-dialog" closed="true"
		data-options="onBeforeOpen:onBeforeOpen, onOpen:onOpened, onClose:onClosed">
		<iframe id="eventDialogFrame" name="eventDialogFrame"
			style="width: 100%; height: 100%" border="0" frameborder="0"></iframe>
	</div>
	<input type="hidden" id="msg" value="${msg}"/>
<button type="button" class="btn btn-primary  btn-sm navbar-btn  customer-btn" onclick="WgLoadMultipleField1Click()">選擇</button>
<button type="button" class="btn btn-warning  btn-sm navbar-btn  customer-btn" onclick="PopClose()">關閉</button>
	
			<table class="easyui-datagrid" id="listGrid"></table>
			<!-- toolbar:listGridToolBar, -->
			<!-- url="/st/EW1/backend.php/zh_tw/Sample/sample/fetchListViaAjax" -->
			<input type="hidden" value="" name="" id="" />
			
	 		<div style="display: inline" id="YII_CSRF_TOKEN_field">
				<input id="csrfToken" class="form-control" type="hidden"
					value="0eb679f5a3b24294ea57162e7b10ca7c0a70194b"
					name="YII_CSRF_TOKEN" />
			</div> 
			<div style="display: inline" id="eventPrefix_field">
				<input class="form-control" type="hidden" value="card"
					name="eventPrefix" id="eventPrefix" />
			</div>
			<div style="display: inline" id="distinguishProperty_field">
				<input data-label="範例文字輸入" class="form-control" type="hidden" value="name" name="distinguishProperty" id="distinguishProperty" />
			</div>
			<input type="hidden" value="" name="queryParams" id="queryParams" />
</body>
</html>