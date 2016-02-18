<#assign menu="folder">
<#assign submenu="folder_list">
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
					<li><a href="${basePath}/admin/folder/page.htm?folderId=0"><i
							class="icon-home"></i> Home</a></li> <#list pathList as pathFolder>
					<li><a
						href="${basePath}/admin/folder/page.htm?folderId=${pathFolder.folderId}">${pathFolder.name}</a></li>
					</#list>
				</ul>
				<!--breadcrumbs end -->
			</div>
		</div>
		<div class="row">
			<div class="col-lg-4">
				<section class="panel">
					<header class="panel-heading"> 添加目录 </header>
					<div class="panel-body">
						<form id="addFolder_form" method="post" class="form-horizontal"
							autocomplete="off" action="${basePath}/admin/folder/add.json">
							<fieldset>
								<div class="form-group">
									<label class="col-xs-3 control-label">目录名称</label>
									<div class="col-xs-9">
										<input type="text" style="font-size: 15px; width: 255px;"
											class="form-control" name="folderName" placeholder="目录名称"
											id="folderName"> </input>
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-3 control-label">英文名称</label>
									<div class="col-xs-9">
										<input style="font-size: 15px; width: 255px;" class="form-control" name="folderEname" placeholder="英文名称">
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-3 control-label">父级标签</label>
									<div class="col-xs-9">
										<select class="form-control input-lg m-bot15" style="font-size: 15px; width: 255px;" name="fatherId">
											<option value="0" <#if folder.folderId==0>selected</#if>>├─顶级目录</option> 
											<#list folderAll as firstFolder>
											<option value="${firstFolder.folderId}" <#if folder.folderId==firstFolder.folderId>selected</#if>>
												├─┬─${firstFolder.name}
											</option> 
												<#list firstFolder.folderList as secondFolder>
												<option value="${secondFolder.folderId}" <#if folder.folderId==secondFolder.folderId>selected</#if>>
													│&nbsp;&nbsp;&nbsp;└──${secondFolder.name}
												</option>
													<#list secondFolder.folderList as thirdFolder>
													<option  value="${thirdFolder.folderId}" <#if folder.folderId==thirdFolder.folderId>selected</#if>>
														│&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;└──${thirdFolder.name}
													</option>
													</#list>
												</#list>
											</#list>
										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="col-xs-3 control-label">目录状态</label>
									<div class="col-xs-9">
										<label class="radio-inline"> <input type="radio"
											name="status" value="display" checked /> 显示
										</label> <label class="radio-inline"> <input type="radio"
											name="status" value="hidden" /> 隐藏
										</label>
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-offset-23 col-xs-9">
										<button class="btn btn-danger" type="submit">保存</button>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
				</section>
			</div>
			<div class="col-lg-8">
				<section class="panel">
					<header class="panel-heading"> ${folder.name}的子目录 </header>
					<div class="panel-body">
						<div class="adv-table">
							<div role="grid" class="dataTables_wrapper"
								id="hidden-table-info_wrapper">
								<table class="table table-striped table-advance table-hover">
									<thead>
										<tr>
											<th>顺序</th>
											<th>名称</th>
											<th>英文名称</th>
											<th>状态</th>
											<th>类型</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody role="alert" aria-live="polite" aria-relevant="all">
										<#list list as e>
										<tr class="gradeA_firstFolder">
											<td class="folderSort"><input type="text"
												folderId="${e.folderId}" value="${e.sort}" name="sort"
												class="js_folder_sort" style="width: 40px;"></td>
											<td><a
												href="${basePath}/admin/folder/page.htm?folderId=${e.folderId}">${e.name}</a></td>
											<td>${e.ename}</td>
											<td>
												<select class="js_folder_status" folderId="${e.folderId}">
													<option value="display" <#if e.type=="display">selected</#if>>显示</option>
													<option value="hidden" <#if e.type=="hidden">selected</#if>>隐藏</option>
												</select>
											</td>
											<td>
												<select class="js_folder_type" folderId="${e.folderId}">
													<option value="folder" <#if e.type=="folder">selected</#if>>目录</option>
													<option value="photo" <#if e.type=="photo">selected</#if>>相册</option>
													<option value="article" <#if e.type=="article">selected</#if>>文章</option>
												</select>											
											</td>
											<td>
												<!-- Icons -->
												<a class="js_folder_delete" folderId="${e.folderId}" href="javascript:void(0);" title="删除${e.name}">
													删除
												</a>
												 | 
												<a href="${basePath}/admin/folder/update.htm?folderId=${e.folderId}" title="修改">
													修改描述
												</a>
												<#if e.type="photo" >
												 | 
												<a href="${basePath}/admin/folder/photo.htm?folderId=${e.folderId}" title="修改">
													上传图片
												</a>
												</#if>
												<#if e.type="article" >
												 | 
												<a href="${basePath}/admin/article/add.htm?folderId=${e.folderId}"  folderId="${e.folderId}" href="javascript:void(0);">
													增加文章
												</a>
												</#if>
											</td>
										</tr>
										</#list>
									</tbody>
								</table>
							</div>
							<div>
								<button class="btn btn-info js_update_sort" type="button">
									<i class="icon-refresh"></i> 更新排序
								</button>
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
var pageFolderId = ${folder.folderId};
$(function() {
    $('.js_update_sort').click(function() {
        var folderSort = new Array();
        $('.js_folder_sort').each(function(i, element) {
            var folder = {};
            folder.folderId = $(element).attr('folderId');
            folder.sort = $(element).val();
            folderSort.push(folder);
        });
        $.post("${basePath}/admin/folder/sort.json", {
            "sortJson": $.toJSON(folderSort)
        },
        function(data) {
            if (data.result) {
                bootbox.alert("更新成功",
                function() {
                    window.location.href = "${basePath}/admin/folder/page.htm?folderId=" + pageFolderId;
                });
            } else {
                bootbox.alert(data.msg,
                function() {
          		});
            }
        },
        "json");
    });
    $('.js_folder_delete').click(function() {
        var folderId = $(this).attr('folderId');
        bootbox.dialog({
            message: "是否" + $(this).attr('title') + "文件夹",
            title: "提示",
            buttons: {
                "delete": {
                    label: "删除",
                    className: "btn-success",
                    callback: function() {
                        $.post("${basePath}/admin/folder/delete.json", {
                            "folderId": folderId
                        },
                        function(data) {
                            if (data.result) {
                                bootbox.alert("删除成功",
                                function() {
                                    window.location.href = "${basePath}/admin/folder/page.htm?folderId=" + pageFolderId;
                                });
                            } else {
                                bootbox.alert(data.msg,
                                function() {});
                            }
                        },
                        "json");
                    }
                },
                "cancel": {
                    label: "取消",
                    className: "btn-primary",
                    callback: function() {}
                }
            }
        });
    });
    $('#addFolder_form').ajaxForm({
        dataType: 'json',
        success: function(data) {
            if (data.result) {
                bootbox.dialog({
                    message: "保存成功",
                    title: "提示",
                    buttons: {
                        ok: {
                            label: "确定",
                            className: "btn-success",
                            callback: function() {
                                window.location.reload();
                            }
                        }
                    }
                });
            } else {
                showErrors($('#addFolder_form'), data.errors);
            }
        }
    });
    $(".js_folder_type").change(function(){
		$.post("${basePath}/admin/folder/type.json", {"folderId": $(this).attr("folderId"),type:$(this).val()},function(){
			window.location.reload();
        },"json");  	
    });
    $(".js_folder_status").change(function(){
		$.post("${basePath}/admin/folder/status.json", {"folderId": $(this).attr("folderId"),status:$(this).val()},function(){
			window.location.reload();
        },"json");  	
    });
});
</script>
<#include "/system/foot.ftl">
