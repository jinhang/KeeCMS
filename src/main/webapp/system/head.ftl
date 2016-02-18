<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Mosaddek">
<meta name="keyword"
	content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
<link rel="shortcut icon" href="img/favicon.png">
<title>${SYS_SITENAME} - 后台</title>
<!-- Bootstrap core CSS -->
<link href="${basePath}/system/css/bootstrap.min.css" rel="stylesheet">
<link href="${basePath}/system/css/bootstrap-reset.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${basePath}/system/css/gallery.css" />	
<!--external css-->
<link
	href="${basePath}/system/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
<link href="${basePath}/system/assets/fancybox/source/jquery.fancybox.css" rel="stylesheet" />	
<link href="${basePath}/system/assets/ueditor1/third-party/SyntaxHighlighter/shCoreDefault.css" rel="stylesheet" />	
<!-- Custom styles for this template -->
<link href="${basePath}/system/css/style.css" rel="stylesheet">
<link href="${basePath}/system/css/style-responsive.css" rel="stylesheet" />
<link href="${basePath}/system/assets/uploadify/uploadify.css" rel="stylesheet" />

<!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
<!--[if lt IE 9]>
      <script src="${basePath}/system/js/html5shiv.js"></script>
      <script src="${basePath}/system/js/respond.min.js"></script>
    <![endif]-->
	<script type="text/javascript">
		window.BasePath = "${basePath}";
		window.ContextPath = "${contextPath}";
		kindId = 0;
		kind = "article";
	</script>
<script src="${basePath}/system/js/jquery.js"></script>
</head>
<body>
	<section id="container" class="">
		<!--header start-->
		<header class="header white-bg">
			<!--logo start-->
			<a href="${basePath}/index.htm" class="logo" title="访问前台页面">
				<img src="${basePath}/system/images/logo.png" style="height: 38px;" />
			</a>
			<!--logo end-->
			<div class="nav notify-row" id="top_menu">
				<!--  notification goes here -->
			</div>
			<div class="top-nav ">

				<ul class="nav pull-right top-menu">
                  <!-- user login dropdown start-->
                  <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                          <span class="username">${SESSION_ADMIN.email}</span>
                          <b class="caret"></b>
                      </a>
                      <ul class="dropdown-menu extended logout">
                          <div class="log-arrow-up"></div>
                          <li><a href="${basePath}/admin/admin/update.htm"><i class="icon-cog"></i> 设置</a></li>
                          <li><a href="${basePath}/auth/admin/logout.htm"><i class="icon-key"></i> 安全退出</a></li>
                      </ul>
                  </li>
                  <!-- user login dropdown end -->
              </ul>
          
			</div>
		</header>
		<!--header end-->
		<!--sidebar start-->
		<aside>
			<div id="sidebar" class="nav-collapse ">
				<!-- sidebar menu goes here-->
				<ul class="sidebar-menu" id="nav-accordion">
					<li class="">
						<a <#if menu="default">class="active"</#if> href="${basePath}/admin/index.htm"> <i class="icon-home"></i> <span>首页</span></a>
					</li>
					<li class="sub-menu">
						<a href="${basePath}/admin/folder/page.htm" <#if menu="folder">class="active"</#if>> <i class="icon-folder-open"></i> <span>目录</span></a>						
					</li>
					<li class="sub-menu">
						<a href="javascript:;" <#if menu="article">class="active"</#if>> <i class="icon-book"></i> <span>文章</span></a>
						<ul class="sub">
							<li <#if submenu="add_article">class="active"</#if>><a href="${basePath}/admin/article/add.htm">增加文章</a></li>
							<li <#if submenu="article_list">class="active"</#if>><a href="${basePath}/admin/article/page.htm">文章列表</a></li>
						</ul>
					</li>
					<li class="sub-menu">
						<a href="${basePath}/admin/comment/page.htm?status=hidden" <#if menu="comment">class="active"</#if>> <i class="icon-comments"></i> <span>评论</span></a>
					</li>									
					<li class="sub-menu">
						<a href="javascript:;" <#if menu="sdgb">class="active"</#if>> <i class="icon-desktop"></i> <span>主题</span></a>
						<ul class="sub">
						</ul>
					</li>
<!--					<li class="sub-menu ">
						<a href="javascript:;" <#if menu="user">class="active"</#if>> <i class=" icon-group"></i> <span>用户</span></a>
						<ul class="sub">
							<li <#if submenu="add_user">class="active"</#if>><a href="${basePath}/admin/user/add.htm">添加用户</a></li>
							<li <#if submenu="user_list">class="active"</#if>><a href="${basePath}/admin/user/page.htm">用户列表</a></li>
						</ul>
					</li>	-->
					<li class="sub-menu ">
						<a href="javascript:;" <#if menu="system">class="active"</#if>> <i class="icon-cogs"></i> <span>设置</span></a>
						<ul class="sub">
							<li <#if submenu="system_basic">class="active"</#if>><a href="${basePath}/admin/config/basic.htm">基本设置</a></li>
							<li><a href="${basePath}/admin/admin/manage.htm">管理员管理</a></li>
							<!---
							<li <#if submenu="add_admin">class="active"</#if>><a href="${basePath}/admin/admin/add.htm">添加管理员</a></li>
							<li <#if submenu="admin_list">class="active"</#if>><a href="${basePath}/admin/admin/page.htm">管理员列表</a></li>
							-->
							<li <#if submenu="update_admin">class="active"</#if>><a href="${basePath}/admin/admin/update.htm">修改个人信息</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</aside>
		<!--sidebar end-->		
