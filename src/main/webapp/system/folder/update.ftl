<#assign menu="folder"> <#assign submenu="update_folder"> <#include
"/system/head.ftl">
<style type="text/css">
</style>
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<!-- page start-->
		<div class="row">
			<form id="update_folder_form" method="post"
				class="form-horizontal tasi-form" autocomplete="off"
				action="${basePath}/admin/folder/update.json">
				<div class="col-lg-9">
					<input type="hidden" class="form-control" name="folderId"
						value="${folder.folderId}">						
					<section class="panel">
						<header class="panel-heading">【${folder.name}】目录的描述 </header>
						<div class="panel-body">
							<script id="content" name="content" type="text/plain" style="width: 100%; height: 260px;">${folder.content!}</script>
							<p class="help-block" for="content"></p>
							<script type="text/javascript">
									var contentEditor;
									$(function() {
										contentEditor = UE.getEditor('content');
									});
							</script>
						</div>
					</section>
				</div>
				<div class="col-lg-3">
					<section class="panel">
						<header class="panel-heading">更新</header>
						<div class="panel-body">						
							<div style="margin: 5px 0px;">
								<label for="exampleInputEmail1">中文名</label> <input type="text"
									class="form-control" name="name" value="${folder.name}"
									placeholder="中文名">
							</div>
							<div style="margin: 5px 0px;">
								<label for="exampleInputEmail1">英文名</label> <input type="text"
									class="form-control" name="ename" value="${folder.ename}"
									placeholder="英文名">
							</div>
							<div style="margin: 5px 0px;">
								<button class="btn btn-shadow btn-primary" type="submit">更新【${folder.name}】目录</button>
							</div>
						</div>
					</section>
				</div>
			</form>
		</div>

		<!-- page end-->
	</section>
</section>
<!--main content end-->
<script type="text/javascript">
 	var fatherId = ${folder.fatherId};
 	var kindId = ${folder.folderId};
	var kind = "folder";	
	$(function() {
		$('#update_folder_form').ajaxForm({
			dataType : 'json',
			beforeSerialize: function($form, options) {
				contentEditor.sync();
			},			
			success : function(data) {
				if (data.result) {
					bootbox.alert("保存成功，将刷新页面", function() {
						window.location.reload();
					});
				}else{
					showErrors($('#update_folder_form'),data.errors);
				}
			}
		});
		$('#file_upload').uploadify({
			'buttonText'  		: 	'请选择文件',
	        'swf'         		: 	'${basePath}/system/assets/uploadify/uploadify.swf',
	        'uploader'    		: 	'${basePath}/admin/attachment/upload.json;jsessionid=${JSESSIONID}',
	        'formData'  		: 	{'kindId':kindId,'kind':kind},
	        'fileObjName'		: 	'file',
	        'fileTypeExts' 		: 	'*.jpg; *.png; *.gif',
	        'method'			:	'post',
	        'onUploadSuccess' 	: 	function(file, data, response) {
	        }
		});
		$('#folderId').change(function(){
			window.location.href = "${basePath}/admin/attachment/page.htm?folderId="+$('#folderId').val();
		});
		$('#btn_reflash').click(function(){
			window.location.reload();
		});
		jQuery(".fancybox").fancybox();
		$('#attachment .js_link').click(function(){
			var attachmentId = $(this).attr("attachmentId");
			var link = $(this).attr("link");
			bootbox.prompt("修改链接，当前链接为："+link, function(result) {
				if (result != null && result != "") {
					$.post("${basePath}/admin/attachment/update_link.json",{'attachmentId':attachmentId,'link':result},function(data){
						if(data.result){
							window.location.reload();
						}
					},"json");					
				} 
			});			
		});
		$('#attachment .js_delete').click(function(){
			var file = $(this);
			bootbox.confirm("是否要删除【"+$(this).attr("name")+"】文件？", function(result) {
				if (result) {
					$.post("${basePath}/admin/attachment/delete.json",{'attachmentId':file.attr("attachmentId")},function(data){
						if(data.result){
							window.location.reload();
						}
					},"json");
				}
			});		
		});				
	});	
</script>
<#include "/system/foot.ftl">
