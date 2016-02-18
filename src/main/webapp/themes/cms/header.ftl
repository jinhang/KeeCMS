<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="${SYS_SITEDESC}">
    <meta name="author" content="kee.com">
    <meta name="keyword" content="Java, CMS, Spring, MyBatis, MySQL">
    <link rel="shortcut icon" href="${TEMPLATE_PATH}/img/favicon.png">

    <title>${SYS_SITENAME}</title>

    <!-- Bootstrap core CSS -->
    <link href="${TEMPLATE_PATH}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${TEMPLATE_PATH}/css/theme.css" rel="stylesheet">
    <link href="${TEMPLATE_PATH}/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="${TEMPLATE_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" href="${TEMPLATE_PATH}/css/flexslider.css"/>
    <link href="${TEMPLATE_PATH}/assets/bxslider/jquery.bxslider.css" rel="stylesheet" />
    <link href="${TEMPLATE_PATH}/assets/fancybox/source/jquery.fancybox.css" rel="stylesheet" />
    <script src="${TEMPLATE_PATH}/js/jquery.js"></script>  
    <!-- Custom styles for this template -->
    <link href="${TEMPLATE_PATH}/css/style.css" rel="stylesheet">
    <link href="${TEMPLATE_PATH}/css/style-responsive.css" rel="stylesheet" />

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
      <script src="${TEMPLATE_PATH}/js/html5shiv.js"></script>
      <script src="${TEMPLATE_PATH}/js/respond.min.js"></script>
    <![endif]-->
    <style>
    .breadcrumb > li + li:before {
	    color: #CCCCCC;
	    content: "/ ";
	    padding: 0 5px;
	}
    </style>
  </head>
  <body>
  	<header class="header-frontend">
        <div class="navbar navbar-default navbar-static-top">
            <div class="container">
                <div class="navbar-header">
 					<a href="${basePath}/index.htm" class="navbar-brand">${SYS_SITENAME}<span>${SYS_SITEDESC}</span></a>               
                </div>
                <div class="navbar-collapse collapse ">
                    <ul class="nav navbar-nav">
                    	<li class="<#if !firstFolder?exists>active</#if>"><a href="${basePath}/index.htm">首页</a></li>
	                    <@keecms_folder_list fatherId="0" >
						<#list tag_folder_list as f>
						<li class="<#if firstFolder?exists&&f.folderId == firstFolder.folderId>active</#if>">
							<a href="${basePath}/${f.ename}/index.htm">${f.name}</a>
	                    </li>
						</#list>
	                    </@keecms_folder_list>
                    </ul>
                </div>
            </div>
        </div>
    </header>