<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" CONTENT="0">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Pragma" CONTENT="no-cache">
<meta charset="utf-8">
<title>登陆</title>
<script type="text/javascript" src="${ctx }/static/js/jquery/jquery-1.11.0.min.js"></script>
</head>
<body>
<div id="particles">
	<div class="intro">
		<div class="login_main">
			<div class="form_title">
			<div><a  class="login_logo" href="${ctx }/index" title="去首页"></a></div>
			<span>用户登陆</span>
			</div>
			<div class="login_form">
				<form action="${ctx }/login" method="POST" id="form_login">
					<span>登陆名：</span>
					<input type="text" name="username" id="username" /> 
					<span>密码：</span>
					<input type="password" name="password" id="password" /> 
					<div style="width:100px; height:40px;">
						<img src="${ctx }/imageCode" class="imageCode" onclick="this.src='${ctx }/imageCode'" />
					</div>
					<button type="submit" class="button" id="login_submit">登 陆</button>
				</form>
				<c:if test="${not empty error}">
					<div style="color:#FF9A0A;height:30px;margin-left:400px;margin-top:8px;" id="err_msg">${error}</div>
				</c:if>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

</script>
</body>
</html>