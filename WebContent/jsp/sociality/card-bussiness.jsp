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

$(function(){
    $('.datagrid-toolbar table').wrap('<div style="display:inline-block;float:left;"></div>');
    $('.datagrid-toolbar').append('<div style="display:inline-block;float:right;width:275px;margin-right:5px;padding: 2px;" align="right" id="searchBlock"></div>');
    $('.datagrid-toolbar').append('<div style="clear:both"></div>');
    $('#searchBlock').append('');
});
//easyui datagrid載入資料前動作
function onBeforeLoad(param){
return dgCls.onBeforeLoad(param);
}

/**
* disable資料清單
*/
var stripData = [];

/**
* 使用者選擇資料前動作
*/
function onBeforeCheck(index, row) {
return checkRowIsDiabled(row.guid)?false:true;
}

/**
* 使用者選擇資料前動作
*/
function onBeforeSelect(index, row) {
return checkRowIsDiabled(row.guid)?false:true;
}

/**
*
*/
function disableRowStyler(index, row) {
if(checkRowIsDiabled(row.guid)) {
    return 'color: #AAA; background-color: #CCC; CURSOR:not-allowed;';
}
return '';
}

function checkRowIsDiabled(pk) {
if (stripData.indexOf(pk) == -1) {
    return false;
} else {
    return true;
}
}


//資料載入完成動作
function onLoadSuccess(data){
dgCls.onListLoadSuccess(this, data);
var dg = $('#listGrid');
var opts = dg.datagrid('options');
var tr = opts.finder.getTr(dg[0], 0);
for(var i=0;i<stripData.length;i++){
    var idx = dg.datagrid('getRowIndex', stripData[i]);
    var tr = opts.finder.getTr(dg[0], idx);
    tr.find('input[type=checkbox]').attr('disabled','disabled').attr('checked', 'checked');
}
}

//Pop選擇
function PopSelect(obj){
var selectedList = dgCls.getSelectedPks($('#listGrid'));
var ajaxOnSendAfterChooseData = $('#eventDialogFrame',parent.document).attr('data-PopSelectFunction');
eval("parent."+ajaxOnSendAfterChooseData+"('"+selectedList+"')");
dialogCls.close();
}


//Pop關閉
function PopClose() {
dialogCls.close();
}

//查詢
function Search(obj) {
dgCls.search($('#listGrid'), obj);
}

//進階搜尋
function AdvSearch(obj) {
dgCls.advSearch($('#listGrid'), obj);
}

function getSearchParams(param) {
var jsonData = JSON.parse($('#searchParams').val());
param['YII_CSRF_TOKEN'] = 'e52d04e392f405029f99b6adf0c0eb9bb9bfe293';
param = $.extend(param, jsonData);
return param;
}

</script>
<style type="text/css">

.panel-title {
font-size: 14px;
}
</style>
<script type="text/javascript">

if (parent.location.href == self.location.href) {
top.window.location.href = 'card_list';
}

//錯誤訊息
var displayMsg = {
loading: '資料載入中，請稍後。。。',
abort: '未儲存的資料將會遺失，點擊「確定」確認放棄資料',
notice: '注意事項!',
alert: '系統提示',
empty: {
    modify: '請先選擇您要維護的資料',
    view: '請先選擇您要檢視的資料',
    invalid: '請先選擇您要作廢的資料',
    deleted: '請先選擇您要刪除的資料',
},
deleted: {
    title: '資料刪除確認',
    confirm: '是否刪除下列資料?',
    button: {
        yes: '確認',
        no: '取消'
    },
    success: '資料已成功刪除！'
},
cacheSave:{
	success: '資料已成功儲存！'
},
configSave:{
	success: '資料已成功設定儲存！'
}
};

$(window).load(function(){
// 插入空白按鈕間距
$.each($('button[data-blank=true]', $('.datagrid-toolbar table')), function(idx, btn){
    var prevTd = $(btn).closest('td').prev();
    $('a', prevTd).css('marginRight','35px');
});

$.each($('button[data-blank=true]', parent.$('.dialog-toolbar table')), function(idx, btn){
    var prevTd = $(btn).closest('td').prev();
    $('a', prevTd).css('marginRight','35px');
});
});

/**
* 取得頁籤標題
*/
function fetchTabTitle() {
if(parent.$('#contentTabs').size()){
    tab = parent.$('#contentTabs').tabs('getSelected');
    return tab.panel('options').title;
} else {
    var option = parent.$('#eventDialog').panel('options');
    return option.title;
}
}

//easyui datagrid相關操作物件
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

//easyui messager 相關操作物件
var msgCls = messagerClass.getNew();

/**
* 共用ajax，自動鎖定畫面
*
* @param object config ajax設定
* @param string config.url ajax連線位置
* @param object config.data 傳送資料
* @param string config.type 連線型態[GET|POST]
* @param string config.dataType 回傳資料解析型態[text|json]
* @param function config.done 連線完成動作
* @param function config.fail 連線失敗動作
*/
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

/**
* 推入CSRF參數
*/
function pushCSRFParams(params){
params['YII_CSRF_TOKEN'] = 'e52d04e392f405029f99b6adf0c0eb9bb9bfe293';
return params;
}

/**
* 刪除CSRF參數
*/
function deleteCSRFParams(params){
delete params['YII_CSRF_TOKEN'];
return params;
}

/**
* 處理搜尋參數
* @param object target 指定目標
*/
function handleSearchParam(target){
var queryParam = {};
    $.each($(':input',target), function(){
        if ($(this).attr('name') != undefined) {
            switch($(this).attr('type')) {
            case 'radio':
                queryParam[$(this).attr('name')] = $('input[name="'+$(this).attr('name')+'"]:checked').val();
                break;
            case 'checkbox':
                if(queryParam[$(this).attr('name')] == undefined) {
                    queryParam[$(this).attr('name')] = [];
                }
                if($(this).prop('checked') == true) {
                    if(queryParam[$(this).attr('name')].indexOf($(this).val()) == -1) {
                        queryParam[$(this).attr('name')].push($(this).val());
                    }
                } else {
                    var itemIdx = queryParam[$(this).attr('name')].indexOf($(this).val());
                    if(itemIdx != -1) {
                        queryParam[$(this).attr('name')].splice(itemIdx,1);
            	    }
                }
                break;
            default:
                queryParam[$(this).attr('name')] = $(this).val();
                break;
            }
        }
    });
    return queryParam;
}

/**
* 推入SearchBlock固定參數
*/
function pushSearchBlockParams(params){
var searchParam = handleSearchParam($('#searchBlock'));
$.each(searchParam,function(sk,sv){
	if(typeof(params[sk]) == 'undefined'){
		params[sk] = sv;
	}
})
return params;

}

//依資訊顯示 列舉資訊
function DisplayListGridEnum(value,row,rowIndex){
return this.editor.options.data[value]
}

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
/**
* 全選或全不選
*/
function CheckAllOrUncheckAllClick(obj,sf,property){
$(':checkbox[id^='+sf+'_'+property+'_]',$(obj).parent()).prop('checked',$(obj).prop('checked'));
}

/**
* 顯示列舉翻譯值
*/
function DisplayEnum(value,row,rowIndex){
return this.editor.options.data[value]
}

function getCsrfToken() {
var csrf = new Object();
csrf['YII_CSRF_TOKEN'] = 'e52d04e392f405029f99b6adf0c0eb9bb9bfe293';
return csrf;
}

$(function(){
$('body').on('keydown',"input",function(e){
    if(e.keyCode==13){
        return false;
    }
})

$('#listGrid').datagrid({
    width: '700',
    url:"api/card_bussiness",
    rownumbers: true,
    columns:[[
        { field:'guid',checkbox:true, width:"10%"}, 
        { field: 'name', title: '業務名稱', align:'center', width:"90%"},
    ]],
    idField:'guid',
    singleSelect: true,
    selectOnCheck: true,
    checkOnSelect: true,
    toolbar: listGridToolBar,
    fitColumns:true
});

})
</script>
<jsp:include page="../constant.jsp" />
</head>
<body>
<div id="errorDialog" style="-webkit-overflow-scrolling:touch; overflow:auto;height:0px;" class="easyui-dialog" closed="true"></div><body><div id="eventDialog" style="-webkit-overflow-scrolling:touch; overflow:auto;height:0px;" class="easyui-dialog" closed="true" data-options="toolbar:listGridToolBar,onBeforeOpen:onBeforeOpen, onOpen:onOpened, onClose:onClosed"><iframe id="eventDialogFrame" style="width: 100%; height: 100%" border="0" frameborder="0"></iframe></div>

<script type="text/javascript">
/*<![CDATA[*/
var listGridToolBar = [{text:'<button class="btn btn-primary" title="選擇" data-blank="false" style="">&nbsp;<i class="glyphicon glyphicon-plus-sign" />&nbsp;選擇</button>',handler:function(){PopSelect($(this))}},{text:'<button class="btn btn-danger" title="關閉" data-blank="true" style="">&nbsp;<i class="glyphicon glyphicon-remove-circle" />&nbsp;關閉</button>',handler:function(){PopClose($(this))}}];
/*]]>*/
</script><script type="text/javascript">
/*<![CDATA[*/
var listGridGridData = []
/*]]>*/
</script>

<input type="hidden" value="" name="" id="" />
<table class="easyui-datagrid" id="listGrid"></table>

<div style="display: inline" id="eventPrefix_field">
	<input class="form-control" type="hidden" value="card" name="eventPrefix" id="eventPrefix" />
</div>
<div style="display: inline" id="distinguishProperty_field">
	<input data-label="範例文字輸入" class="form-control" type="hidden" value="name" name="distinguishProperty" id="distinguishProperty" />
</div>
</body>
</html>