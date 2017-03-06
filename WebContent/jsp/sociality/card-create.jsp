<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="width=device-width; charset=UTF-8">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap/easyui.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/color.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/icon.css" rel="stylesheet">

<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/locales/bootstrap-datetimepicker.zh-TW.js" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.easyui.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/easyui-lang-zh_TW.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/easyui.js"></script>

<title>註冊</title>
<style type="text/css">
.form-center {
	margin: 5px;
	width: 60%; /* value of your choice which suits your alignment */
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
        $('#eventDialogFrame').attr('src','card_bussiness');
        $('#eventDialogFrame').attr('data-PopSelectFunction','WgLoadSingleField41Search');
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
        $('#eventDialogFrame').attr('data-PopSelectFunction','WgLoadMultipleField1Search');
    }
	
	function WgLoadSingleField41Search(dataList){
		var json = {
				guid:dataList
		};
		var toJson = JSON.stringify(json);
		//alert()
 		$.ajax({
			type:'post',
			contentType: "application/json",
            url: '../../api/sociality/card/card_bussiness_data',
            data: toJson,
        	modal: false,
        	success: function(responseText) {
                //eval("WgLoadSingleField41Display('"+responseText.json+"')");
        		WgLoadSingleField41Display(responseText.json);
        	}
        }); 
    }
	
	function WgLoadSingleField41Display(dataList){
        /* dataList = JSON.parse(JSON.stringify(dataList)); */
        if(dataList.array.length >=1){
            var data = dataList.array[0];
        
            $('[data-WgLoadSingleField=WgLoadSingleField41_guid]').val(data['guid']);
           // $('[data-WgLoadSingleField=WgLoadSingleField41_serial]').val(data['serial']);
            $('[data-WgLoadSingleField=WgLoadSingleField41_title]').val(data['name']);
            
        }
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
	
	});
	
	function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();

	        reader.onload = function (e) {
	            $('#blah')
	                .attr('src', e.target.result)
	                .width(240)
	                .height(320);
	        };

	        reader.readAsDataURL(input.files[0]);

	        $('#blah').removeAttr('style');
	    }
	};
	
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
<form id="_form" class="form-center form-group" action="card_create" method="post" enctype="multipart/form-data">
	<input type="hidden" id="msg" value="${responseVO.result}"/>
	<c:choose>
	  <c:when test="${responseVO.result != null}">
	    <script language="javascript">
        	MessageSimple($('#msg').val());
   	 	 </script>
	  </c:when>
	</c:choose>
	<img style='display: none' id="blah" src="#" alt="名片" /><br/>
	上傳名片
	<input class="form-control-file" type="file" id="file" name="file" onchange="readURL(this)" />
	<br/>
	<table cellpadding="10">
		<tr>
			<th>
				<div class="formField" data-attr="sampleDropDownList">
					<label for="BoSample_sampleDropDownList" class="required"> 類別</label>
					<div class="fieldBlock" fullWidth="false">
						<div style="display:inline" id="BoSample_sampleDropDownList_field">
							<select separator="&amp;nbsp;&amp;nbsp;" class="form-control" name="bBussinessCardCategoryGuid" id="">	
								<!-- <option value="" selected="selected">請選擇類別</option> -->
 								<c:forEach var="list" items="${responseVO.category}">
								   <option value="${list.guid}">${list.name}</option>
								</c:forEach> 
							</select>
						</div>
					</div>
				</div>
			</th>
			<td>
				<div class="formField" data-attr="sampleDropDownList">
					<label for="BoSample_sampleDropDownList" class="required">屬性</label>
					<div class="fieldBlock" fullWidth="false">
						<div style="display:inline" id="BoSample_sampleDropDownList_field">
							<select separator="&amp;nbsp;&amp;nbsp;" class="form-control" name="bBussinessCardAttributeGuid" id="">	
								<!-- <option value="" selected="selected">請選擇屬性</option> -->
 								<c:forEach var="list" items="${responseVO.attribute}">
								   <option value="${list.guid}">${list.name}</option>
								</c:forEach> 
							</select>
						</div>
					</div>
				</div>
			</td>
			<td>
				<div class="formField" data-attr="sampleDropDownList">
					<label for="BoSample_sampleDropDownList" class="required">狀態</label>
					<div class="fieldBlock" fullWidth="false">
						<div style="display:inline" id="BoSample_sampleDropDownList_field">
							<select separator="&amp;nbsp;&amp;nbsp;" class="form-control" name="bBussinessCardStatusGuid" id="">	
<!-- 								<option value="" selected="selected">請選擇狀態</option> -->
 								<c:forEach var="list" items="${responseVO.status}">
								   <option value="${list.guid}">${list.name}</option>
								</c:forEach> 
							</select>
						</div>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td >
				<label for="account">承辦業務:  </label>
				<input type="hidden" value="${responseVO.bEnterpriseEmployeeGuid}" name="bEnterpriseEmployeeGuid" data-WgLoadSingleField="WgLoadSingleField41_guid" value="">
				<input type="text" name="sales" value="${responseVO.employeeName}" data-WgLoadSingleField="WgLoadSingleField41_title" readonly/>
				<a href="javascript:void(0);" class="btn btn-primary" onClick="js:WgLoadSingleField41Click()"><i class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;載入</a>
			</td>
		</tr>
		<tr>
			<th>
				<div class="form-group">
					<span class="required" style="color:red;">*</span> <label for="account">姓名:</label> 
					<input  type="text" name="name" id="name" value="">
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">抬頭:</label> 
					<input  type="text" name="title" id="title" value="">
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">暱稱:</label> 
					<input  type="text" name="nickname" id="nickname" value="">
				</div>
			</th>
		</tr>
		<tr>
			<th>
				<div class="form-group">
					<label for="account">手機號碼:</label> 
					<input  type="text" name="cellphoneNumber" id="cellphone" value="">
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">電子郵件:</label> 
					<input type="text" name="email" id="email" value="">
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">公司名稱:</label> 
					<input  type="text" name="companyName" id="companyName" value="">
				</div>
			</th>
		</tr>
		<tr>
			<th>
				<div class="form-group">
					<label for="address1">公司/聯絡地址:</label> 
					<input  type="text" name="companyAddress" id="address" value="">
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="account">公司電話:</label> 
					<input  type="text" name="companyPhoneNumber" id="companyPhone" value="">
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">特徵:</label> 
					<input  type="text" name="feature" id="feature" value="">
				</div>
			</th>
		</tr>
		<tr>
			<th>
				<div class="form-group">
					<label for="account">生日:(請輸入西元年/月/日)</label>
					<input  type="text" name="birthday" id="birthday" value="">
				</div> 
			</th>
			<th>
				<div class="form-group">
					<label for="account">婚姻:</label> 
					<input  type="text" name="marrage" id="married" value="">
				</div>
			</th>
			<th>
				<div class="form-group">
					<label for="password">初識時間:</label> 
					<input  type="text" name="firstMeetTimeString" id="acquaintanceTime" value="">
				</div>
			</th>
		</tr>
		<tr>
			<th>
				<div class="form-group">
					<label for="password">初識地點:</label> 
					<input  type="text" name="firstMeetLocation" id="acquaintanceLocation" value="">
				</div>
			</th>
			<td>
				<div class="form-group">
					<label for="account">備註:</label>
					<input  type="text" name="remark" id="remark" value=""> 
				</div>
			</td>
		</tr>
	</table>
</form>
</body>
</html>