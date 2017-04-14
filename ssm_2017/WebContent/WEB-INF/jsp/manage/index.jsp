﻿<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>投融界客户记录管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="include/css-js.jsp" />
<jsp:include page="include/header-js.jsp" />
</head>
<body>
<div class="navbar navbar-default navbar-fixed-top" id="navbar">
	<script type="text/javascript">
		try{ace.settings.check('navbar' , 'fixed')}catch(e){}
	</script>

	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left">
			<a href="#" class="navbar-brand">
				<small>
					<i class="icon-leaf"></i>
					投融界客户记录管理系统
				</small>
			</a><!-- /.brand -->
		</div><!-- /.navbar-header -->
		<div class="navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<!-- <li class="grey">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">
						<i class="icon-tasks"></i>
						<span class="badge badge-grey">4</span>
					</a>

					<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-header">
							<i class="icon-ok"></i>
							还有4个任务完成
						</li>

						<li>
							<a href="#">
								<div class="clearfix">
									<span class="pull-left">软件更新</span>
									<span class="pull-right">65%</span>
								</div>

								<div class="progress progress-mini ">
									<div style="width:65%" class="progress-bar "></div>
								</div>
							</a>
						</li>

						<li>
							<a href="#">
								<div class="clearfix">
									<span class="pull-left">硬件更新</span>
									<span class="pull-right">35%</span>
								</div>

								<div class="progress progress-mini ">
									<div style="width:35%" class="progress-bar progress-bar-danger"></div>
								</div>
							</a>
						</li>

						<li>
							<a href="#">
								<div class="clearfix">
									<span class="pull-left">单元测试</span>
									<span class="pull-right">15%</span>
								</div>

								<div class="progress progress-mini ">
									<div style="width:15%" class="progress-bar progress-bar-warning"></div>
								</div>
							</a>
						</li>

						<li>
							<a href="#">
								<div class="clearfix">
									<span class="pull-left">错误修复</span>
									<span class="pull-right">90%</span>
								</div>

								<div class="progress progress-mini progress-striped active">
									<div style="width:90%" class="progress-bar progress-bar-success"></div>
								</div>
							</a>
						</li>

						<li>
							<a href="#">
								查看任务详情
								<i class="icon-arrow-right"></i>
							</a>
						</li>
					</ul>
				</li>  -->

				<!-- <li class="purple">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">
						<i class="icon-bell-alt icon-animated-bell"></i>
						<span class="badge badge-important">8</span>
					</a>

					<ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-header">
							<i class="icon-warning-sign"></i>
							8条通知
						</li>

						<li>
							<a href="#">
								<div class="clearfix">
									<span class="pull-left">
										<i class="btn btn-xs no-hover btn-pink icon-comment"></i>
										新闻评论
									</span>
									<span class="pull-right badge badge-info">+12</span>
								</div>
							</a>
						</li>

						<li>
							<a href="#">
								<i class="btn btn-xs btn-primary icon-user"></i>
								切换为编辑登录..
							</a>
						</li>

						<li>
							<a href="#">
								<div class="clearfix">
									<span class="pull-left">
										<i class="btn btn-xs no-hover btn-success icon-shopping-cart"></i>
										新订单
									</span>
									<span class="pull-right badge badge-success">+8</span>
								</div>
							</a>
						</li>

						<li>
							<a href="#">
								<div class="clearfix">
									<span class="pull-left">
										<i class="btn btn-xs no-hover btn-info icon-twitter"></i>
										粉丝
									</span>
									<span class="pull-right badge badge-info">+11</span>
								</div>
							</a>
						</li>

						<li>
							<a href="#">
								查看所有通知
								<i class="icon-arrow-right"></i>
							</a>
						</li>
					</ul>
				</li>  -->

				<%-- <li class="green">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">
						<i class="icon-envelope icon-animated-vertical"></i>
						<span class="badge badge-success">5</span>
					</a>

					<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-header">
							<i class="icon-envelope-alt"></i>
							5条消息
						</li>
						<li>
							<a href="#">
								<img src="${ctx}/static/ace/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar" />
								<span class="msg-body">
									<span class="msg-title">
										<span class="blue">Alex:</span>
										不知道写啥 ...
									</span>

									<span class="msg-time">
										<i class="icon-time"></i>
										<span>1分钟以前</span>
									</span>
								</span>
							</a>
						</li>

						<li>
							<a href="#">
								<img src="${ctx}/static/ace/avatars/avatar3.png" class="msg-photo" alt="Susan's Avatar" />
								<span class="msg-body">
									<span class="msg-title">
										<span class="blue">Susan:</span>
										不知道翻译...
									</span>

									<span class="msg-time">
										<i class="icon-time"></i>
										<span>20分钟以前</span>
									</span>
								</span>
							</a>
						</li>

						<li>
							<a href="#">
								<img src="${ctx}/static/ace/avatars/avatar4.png" class="msg-photo" alt="Bob's Avatar" />
								<span class="msg-body">
									<span class="msg-title">
										<span class="blue">Bob:</span>
										到底是不是英文 ...
									</span>

									<span class="msg-time">
										<i class="icon-time"></i>
										<span>下午3:15</span>
									</span>
								</span>
							</a>
						</li>

						<li>
							<a href="inbox.html">
								查看所有消息
								<i class="icon-arrow-right"></i>
							</a>
						</li>
					</ul>
				</li> --%>
				<li class="light-blue">
					<a data-toggle="dropdown" href="#" class="dropdown-toggle">
						<img class="nav-user-photo" src="${ctx}/static/ace/avatars/user.jpg" />
						<span class="user-info">
							<small>欢迎光临</small>
							${user.name }
						</span>

						<i class="icon-caret-down"></i>
					</a>

					<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<li>
							<a href="#">
								<i class="icon-cog"></i>
								设置
							</a>
						</li>

						<li>
							<a href="#">
								<i class="icon-user"></i>
								个人资料
							</a>
						</li>

						<li class="divider"></li>
						<li>
							<a href="${ctx }/logout">
								<i class="icon-off"></i>
								退出
							</a>
						</li>
					</ul>
				</li>
			</ul><!-- /.ace-nav -->
		</div><!-- /.navbar-header -->
	</div><!-- /.container -->
</div>

<div class="main-container">
	<script type="text/javascript">
		try{ace.settings.check('main-container' , 'fixed')}catch(e){}
	</script>

	<div class="main-container-inner"  id="main-container">
		<a class="menu-toggler" id="menu-toggler" href="#">
			<span class="menu-text"></span>
		</a>
		<div class="sidebar sidebar-fixed" id="sidebar">
			<script type="text/javascript">
				try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
			</script>
			<ul class="nav nav-list">
				<li class="active">
					<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx }/manage/main/load">
						<i class="icon-dashboard"></i>
						<span class="menu-text"> 控制台 </span>
					</a>
				</li>
				<li class="">
					<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx }/info/query/load">
						<i class="icon-group"></i>
						<span class="menu-text"> 客户管理 </span>
					</a>
				</li>
				<shiro:hasPermission name="sys">
				<%-- <li class="">
					<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx }/user/query/load">
						<i class="icon-group"></i>
						<span class="menu-text"> 用户管理 </span>
					</a>
				</li> --%>
				<li class="">
					<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx }/user/query2/load">
						<i class="icon-group"></i>
						<span class="menu-text"> 用户管理 </span>
					</a>
				</li>
				</shiro:hasPermission>
				<%-- <li>
					<a href="#" class="dropdown-toggle">
						<i class="icon-group"></i>
						<span class="menu-text">系统配置</span>
						<b class="arrow icon-angle-down"></b>
					</a>
					<ul class="submenu">
					<shiro:hasPermission name="sys">
						<li>
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx }/manage/elements/load">
								<i class="icon-double-angle-right"></i>
								角色管理
							</a>
						</li>
						<li>
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx }/manage/elements/load">
								<i class="icon-double-angle-right"></i>
								权限管理
							</a>
						</li>
						<li>
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx }/user/query/load">
								<i class="icon-double-angle-right"></i>
								用户管理
							</a>
						</li>
					</shiro:hasPermission>
						<li>
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx }/manage/buttons/load">
								<i class="icon-double-angle-right"></i>
								按钮 &amp; 图表
							</a>
						</li>
					</ul>
				</li> --%>
			</ul><!-- /.nav-list -->
			<div class="sidebar-collapse" id="sidebar-collapse">
				<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
			</div>
			<script type="text/javascript">
				try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
			</script>
		</div>
		<div class="main-content" id="main-content">
		</div><!-- /.main-content -->
	</div><!-- /.main-container-inner -->
	<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
		<i class="icon-double-angle-up icon-only bigger-110"></i>
	</a>
</div><!-- /.main-container -->
<jsp:include page="include/footer-js.jsp" />
<script type="text/javascript">
	jQuery(function($) {
		var url = $(".nav-list").find("a.menu-text:first-child").attr("data-url");
		LoadMainPage(url, 'main-content');
		$('a.menu-text').click(function(){
			$('.nav-list').find('.active').removeClass('active');
			$(this).parent().addClass('active');
		});		
	});
</script>
</body>
</html>

