<#assign menu="article">
<#assign submenu="add_article">
<#include "/system/head.ftl">
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<!-- page start-->
		<form id="fileForm" action="${basePath}/admin/article/update.json"  autocomplete="off"  method="post">
		<div class="row">
			<input type="hidden" name="articleId" value="${article.articleId}">
			<div class="col-lg-9">
				<section class="panel">
					<header class="panel-heading"> 标题 </header>
					<div class="panel-body">
						<input type="text" class="form-control" name="name" value="${article.name}">
					</div>
				</section>			
				<section class="panel">
					<header class="panel-heading"> 内容 </header>
					<div class="panel-body">
						<script id="content" name="content" type="text/plain"
							style="width: 100%; height: 260px;">${article.content!}</script>
						<script type="text/javascript">
							var contentEditor;
							$(function() {
								contentEditor = UE.getEditor('content');
							});
						</script>
					</div>
				</section>
				<section class="panel">
					<header class="panel-heading"> 附件 </header>
					<div class="panel-body">
						<div id="attachment"></div>
						<button id="file_upload"  class="btn btn-shadow btn-info" type="button"><i class="icon-cloud-upload"></i> 添加附件</button>
					</div>
				</section>					
				<section class="panel">
					<header class="panel-heading"> 描述 </header>
					<div class="panel-body">
						<script id="description" name="description" type="text/plain"
							style="width: 100%; height: 260px;">${article.description!}</script>
						<script type="text/javascript">
							var descriptionEditor;
							$(function() {
								descriptionEditor = UE.getEditor('description');
							});
						</script>
					</div>
				</section>				
			</div>
			<!-- 右侧开始 -->
			<div class="col-lg-3">		
				<section class="panel">
					<header class="panel-heading"> 发布 </header>
					<div class="panel-body">
						<div class="form-group">
							<label for="exampleInputEmail1">所属目录</label>
								<select class="form-control" name="folderId">
			                   		<#list folderAll as firstFolder>
			                        	<option value="${firstFolder.folderId}"  <#if article.folderId==firstFolder.folderId>selected</#if> <#if firstFolder.type!="list">disabled style="background-color:#ccc;"</#if> >
			                        	├─┬─${firstFolder.name}<#if firstFolder.type!="article">（不可选）</#if>
			                        	</option>
				                        	<#list firstFolder.folderList as secondFolder>
				                        	<option  value="${secondFolder.folderId}"  <#if article.folderId==secondFolder.folderId>selected</#if> <#if secondFolder.type!="list">disabled style="background-color:#ccc;"</#if>>
				                        	│&nbsp;&nbsp;&nbsp;└──${secondFolder.name}<#if secondFolder.type!="article">（不可选）</#if>
				                        	</option>
												<#list secondFolder.folderList as thirdFolder>
					                        	<option  value="${thirdFolder.folderId}"  <#if article.folderId==thirdFolder.folderId>selected</#if> <#if thirdFolder.type!="list">disabled style="background-color:#ccc;"</#if>>
					                        	│&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;└──${thirdFolder.name}<#if thirdFolder.type!="article">（不可选）</#if>
					                        	</option>
						                        	<#list thirdFolder.folderList as fourthFolder>
						                        	<option  value="${fourthFolder.folderId}"  <#if article.folderId==fourthFolder.folderId>selected</#if> <#if fourthFolder.type!="list">disabled style="background-color:#ccc;"</#if>>
						                        	│&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;│&nbsp;&nbsp;└──${fourthFolder.name}<#if fourthFolder.type!="article">（不可选）</#if>
						                        	</option>
						                        	</#list>
					                        	</#list>			                        	
				                        	</#list>
			                        </#list>							
	                            </select>							
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">状态</label>
							<div>
								<input name="status" value="display" type="radio" <#if article.status=="display" || article.status=="init">checked</#if>> 显示
								<input name="status" value="hidden" type="radio" <#if article.status=="hidden">checked</#if>> 隐藏
							</div>
						</div>
						<div class="form-group">
							<button class="btn btn-shadow btn-primary" type="submit">发布</button>
						</div>
					</div>
				</section>
				<section class="panel">
					<header class="panel-heading"> 小标题 </header>
					<div class="panel-body">
						<input type="text" class="form-control" name="title" value="${article.title!}">
					</div>
				</section>									
			</div>
		</div>
		</form>
		<!-- page end-->
	</section>
</section>
<!--main content end-->
<script type="text/javascript">
var kindId = ${article.articleId};
var kind = "article";
$.extend({
	getAttachment:function(){
		$.getJSON("${basePath}/admin/attachment/list.json?kindId="+kindId+"&v="+Math.random(),function(data){
			$('#attachment').html("");
			$.addAttachment(data.attachmentList);
		});
	},
	addAttachment:function(list){
		var html = '<table class="table"><thead><tr><th>文件名</th><th>链接</th><th>大小</th><th>操作</th></tr></thead><tbody>';
		for(i=0;i<list.length;i++){
			var attachment = list[i];
			html += '<tr>';
			if(attachment.type=="photo"){
				html += '<td><img src="${basePath}/'+attachment.path+'" width="200"/></td>';
			}else{
				html += '<td>'+attachment.name+'</td>';
			}
			html += '<td>'+attachment.link+'</td><td>'+attachment.size+'</td><td>';
			html += '<a href="javascript:void(0);" title="删除" name="'+attachment.name+'" class="btn btn-danger btn-xs js_delete" attachmentId="'+attachment.attachmentId+'">删除</a> ';
			html += '<a href="javascript:void(0);" title="链接" name="'+attachment.name+'" class="btn btn-primary btn-xs js_link" attachmentId="'+attachment.attachmentId+'"  link="'+attachment.link+'">修改链接</a> ';
			html += '</td></tr>';
		}
		html += '</tbody></table>';
		$('#attachment').prepend(html);
		$('#attachment .js_delete').click(function(){
			var file = $(this);
			bootbox.confirm("是否要删除【"+$(this).attr("name")+"】文件？", function(result) {
				if (result) {
					$.post("${basePath}/admin/attachment/delete.json",{'attachmentId':file.attr("attachmentId")},function(data){
						if(data.result){
							$.getAttachment();
						}
					},"json");
				}
			});		
		});
		$('#attachment .js_status').click(function(){
			$.post("${basePath}/admin/attachment/update_status.json",{'attachmentId':$(this).attr("attachmentId"),'status':$(this).attr("status")},function(data){
				if(data.result){
					$.getAttachment();
				}
			},"json");
		});
		$('#attachment .js_link').click(function(){
			var attachmentId = $(this).attr("attachmentId");
			bootbox.prompt("为此附件增加链接", function(result) {
				if (result !="") {
					$.post("${basePath}/admin/attachment/update_link.json",{'attachmentId':attachmentId,'link':result},function(data){
						if(data.result){
							$.getAttachment();
						}
					},"json");					
				} 
			});			
		});
	}
});
$(function(){
	$('#fileForm').ajaxForm({
		beforeSerialize: function($form, options) {
			contentEditor.sync();
			descriptionEditor.sync();	
		},
		success:function(data){
		   	if(data.result){
		   		bootbox.alert("保存成功");
		   	}
		}
	});
	$('#file_upload').uploadify({
		'buttonText'  		: 	'请选择文件',
        'swf'         		: 	'${basePath}/system/assets/uploadify/uploadify.swf',
        'uploader'    		: 	'${basePath}/admin/attachment/upload.json;jsessionid=${JSESSIONID}',
        'formData'  		: 	{'kindId':kindId,'kind':kind},
        'fileObjName'		: 	'file',
        'fileTypeExts' 		: 	'*.*',
        'method'			:	'post',
        'onUploadSuccess' 	: 	function(file, data, response) {
        	$.getAttachment();
        }
	});
	$.getAttachment();
});
</script>
<#include "/system/foot.ftl">
