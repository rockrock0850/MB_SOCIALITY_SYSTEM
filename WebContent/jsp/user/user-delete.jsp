<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="width=device-width; charset=UTF-8">
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
<title>刪除</title>
<style type="text/css">
	.form-center {
		margin: 0 auto;
		width: 20% /* value of your choice which suits your alignment */
	}
</style>
<script type="text/javascript">
	$(function() {});
</script>
<jsp:include page="../constant.jsp"/>
</head>
<body>
<jsp:include page="${requestScope.menu}">
	<jsp:param value="user" name="module"/>
	<jsp:param value="清單" name="item"/>
	<jsp:param value="delete" name="action1"/>
	<jsp:param value="刪除" name="item1"/>
	<jsp:param value="update" name="action2"/>
	<jsp:param value="更新" name="item2"/>
	<jsp:param value="create" name="action3"/>
	<jsp:param value="註冊" name="item3"/>
	<jsp:param value="login" name="action4"/>
	<jsp:param value="登入" name="item4"/>
</jsp:include>
<hr class="divider1">
<form class="form-center" action="delete" method="post">
	<div class="form-group">
		<center>
			<label>${msg}</label>
		</center>
	</div>
	<div class="form-group">
		<label for="account">帳號:</label> 
		<jsp:include page="${requestScope.inputForm}">
			<jsp:param value="account" name="field"/>
			<jsp:param value="text" name="type"/>
		</jsp:include>
	</div>
	<center>
		<button type="submit" class="btn btn-default">確定</button>
	</center>
</form>
<!-- editModal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" 
     aria-labelledby="editModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Body -->
            <div class="modal-body"></div>
            <!-- Modal Footer -->
            <div class="modal-footer"></div>
        </div>
    </div>
</div>
</body>
</html>