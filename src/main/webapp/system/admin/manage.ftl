<#assign menu="system">
<#assign submenu="admin_list">
<#include "/system/head.ftl">		
<style type="text/css">
.pagination {
	border-radius: 4px;
	display: inline-block;
	margin: 0;
	padding-left: 0;
}
</style>
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<!-- page start-->
		<div class="row">
			<div class="col-lg-12">
				<!--breadcrumbs start -->
				<ul class="breadcrumb">
					<li><a href="http://localhost:8080/byvision/admin/folder/page.htm?folderId=0"><i
							class="icon-home"></i>管理员管理</a></li> 				</ul>
				<!--breadcrumbs end -->
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-4">
				<section class="panel">
					<header class="panel-heading"> 添加管理员 </header>
					<div class="panel-body">
						 <form id="add_admin_form" method="post" class="form-horizontal" autocomplete="off" action="${basePath}/admin/admin/addNew.json">
							<fieldset>
								
								<div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">名称</label>
                                      <div class="col-sm-10">
                                          <input type="text" class="form-control" name="adminName"
                                            placeholder="管理员名称" id="adminName" >
                                      </div>
                                  </div>
                                  <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">邮箱</label>
                                      <div class="col-sm-10">
                                          <input type="text" class="form-control" name="email"
                                            placeholder="管理员邮箱" id="email" >
                                      </div>
                                  </div>
                                  <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">密码</label>
                                      <div class="col-sm-10">
                                          <input type="password" class="form-control" name="password"
                                            placeholder="管理员密码">
                                      </div>
                                  </div>
                                  <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label"></label>
                                      <button class="btn btn-danger" type="submit">增加</button>
                                  </div>		
							</fieldset>
						</form>
					</div>
				</section>
			</div>
			<div class="col-lg-8">
				<section class="panel">
					<header class="panel-heading"> 所有管理员列表 </header>
					<div class="panel-body">
						<div class="adv-table">
							<div role="grid" class="dataTables_wrapper"
								id="hidden-table-info_wrapper">
								<table class="table table-striped table-advance table-hover">
                              <thead>
                                  <tr>
                            <th>管理员名称</th>
                            <th>电子邮箱</th>
                            <th>状态</th>
                            <th>时间</th>
                            <th>操作</th>
                          </tr>
                                </thead>
                              <tbody role="alert" aria-live="polite" aria-relevant="all">
                                <#list pageVo.list as e>
                                <tr class="gradeA odd">
                                      <td>${e.name}</td>
                                      <td>${e.email}</td>
                                      <td>${e.status}</td>
                                      <td>${e.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                                      <td>
                                <!-- Icons -->
                               <a href="${basePath}/admin/admin/delete.htm?adminId=${e.adminId}" title="删除">
                                  
                                                                                                      删除
                                </a>
                            </td>
                                  </tr>
                                  </#list>
                                </tbody>
                              </table>
                              <div style="height: 30px;">
                              <div class="pagination">${pageVo.pageNumHtml}</div>
                              </div>
                           </div>
						</div>
					</div>
				</section>
			</div>
			<!-- page end-->
	</section>
</section>
		<!--main content end-->
<script type="text/javascript">
	$(function() {
		$('#add_admin_form').ajaxForm({
			dataType : 'json',
			success : function(data) {
				if (data.result) {
					bootbox.alert("保存成功，将刷新页面", function() {
						window.location.reload();
					});
				}else{
					showErrors($('#add_admin_form'),data.errors);
				}
			}
		});
	});	
</script>
<#include "/system/foot.ftl">
