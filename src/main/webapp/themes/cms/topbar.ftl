	<!-- BEGIN BREADCRUMBS -->
	<div class="breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-sm-4">
					<h1>${folder.name}</h1>
				</div>
				<div class="col-lg-8 col-sm-8">
					<ol class="breadcrumb pull-right">
						<#assign folderPath = "">
						<li><a href="${basePath}/index.htm">首页</a>
						</li>
						<#list folderPathList as fold> 
							<#assign folderPath = folderPath + fold.ename+"/">
							<#if fold.folderId ==folder.folderId>
							<li class="active">${fold.name}</li>
							<#else>
							<li><a href="${basePath}/${folderPath}index.htm">${fold.name}</a></li>
							</#if>
						</#list>
					</ol>
				</div>
			</div>
		</div>
	</div>
	<!-- END BREADCRUMBS -->
