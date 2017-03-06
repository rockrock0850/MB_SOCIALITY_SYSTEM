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
<jsp:include page="../constant.jsp" />
<script type="text/javascript">
	var token = JSON.stringify(${dataMap.token});

	$(function() {
		if(token == 'null'){
			window.location.href = '${requestScope.loginPage}';
		}
		
		$('#listGrid').datagrid({
		    width: '900',
		    url:"api/card_list",
		    rownumbers: true,
		    columns:[[
		        { field:'guid',checkbox:true, width:"10%"}, 
		        { field: 'companyName', title: '公司名稱', align:'center', width:"22%"},
		        { field: 'name', title: '姓名', align:'center', width:"18%"},
		        { field: 'nickname', title: '暱稱', align:'center', width:"18%"},
		        { field: 'phone', title: '手機號碼', align:'center', width:"18%"},
		        { field: 'contact', title: '人脈', align:'center', width:"14%",formatter:GenerateListGridColumnsToolbar}
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

	// 刪除
	function Delete(obj) {
		dgCls.deleted($('#listGrid'), obj);
	};

	// 新增
	function Create(obj) {
		dgCls.create($('#listGrid'), obj);
	}
	
	// 編修
	function Modify(obj) {
	    dgCls.modify($('#listGrid'), obj);
	}
	
	// 檢視
	function View(obj) {
	    dgCls.view($('#listGrid'), obj);
	}
	
</script>
</head>
<body>
------${dataMap.token}-------
<%-- 	<jsp:include page="${requestScope.menu}">
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
<!-- 	<hr class="divider1"> -->
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
	<c:choose>
	  <c:when test="${dataMap.msg == '執行成功'}">
	    <script language="javascript">
		    var selectedItems = parent.dgCls.getSelections(parent.$('.easyui-datagrid'));
		    var itemIndex = 0;
		    var self = false;
		    var nextItem;
		    for (var key in selectedItems.rows) {
		        itemIndex++;
		        if (self == true) {
		            nextItem = key;
		            break;
		        }
		        if (selectedItems.rows[key].guid == '$guid') {
		            self = true;
		        }
		    }
		    parent.msgCls.fadeMsg(displayMsg.alert, displayMsg.cacheSave.success, 'success');
		    if (nextItem == undefined) {
		        parent.$('#eventDialog').dialog('close');
		    } else {
		        window.location.href = '$url' +selectedItems.rows[nextItem].guid;
		    }
   	 	 </script>
	  </c:when>
	</c:choose>
	<table>
		<tr>
			<td>
			<a class="l-btn l-btn-small l-btn-plain"> <span
					class="l-btn-left"> <span class="l-btn-text">
							<button class="btn btn-danger" title="刪除"
								onclick="Delete($(this));">
								<i class="glyphicon glyphicon-trash"></i>刪除
							</button>
					</span>
				</span>
			</a>
			</td>
			<td>
			<a class="l-btn l-btn-small l-btn-plain"> <span
					class="l-btn-left"> <span class="l-btn-text">
							<button class="btn btn-success" title="檢視" onclick="View($(this));">
								<i class="glyphicon glyphicon-eye-open"></i>檢視
							</button>
					</span>
				</span>
			</a>
			</td>
			<td><a class="l-btn l-btn-small l-btn-plain"> <span
					class="l-btn-left"> <span class="l-btn-text">
							<button class="btn btn-primary" title="新增"
								onclick="Create($(this));">
								<i class="glyphicon glyphicon-file"></i>新增
							</button>
					</span>
				</span>
			</a></td>
			<td><a class="l-btn l-btn-small l-btn-plain"> <span
					class="l-btn-left"> <span class="l-btn-text">
							<button class="btn btn-warning" title="維護" onclick="Modify($(this));">
								<i class="glyphicon glyphicon-pencil"></i>維護
							</button>
					</span>
				</span>
			</a></td>
		</tr>
		</table>
			<hr class="divider1">
			<!-- <table class="easyui-datagrid" id="listGrid" idField="guid" fit="true" rownumbers="false" pagination="true" striped="true" pageList="[25,50,100]" pageSize="25" nowrap="false" multiHeader="" for="" fitColumns="true" multiSort="true" sortName="serial" sortOrder="desc" > --><!-- data-options="toolbar:onBeforeLoad:onBeforeLoad,onLoadSuccess:onLoadSuccess" -->
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