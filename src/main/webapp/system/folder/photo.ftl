<#assign menu="folder">
<#assign submenu="update_folder">
<#include "/system/head.ftl">
<style type="text/css">
</style>
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<!-- page start-->
		<div class="row">
				<div class="col-xs-12">
					<input type="hidden" class="form-control" name="folderId"
						value="${folder.folderId}">
					<section class="panel">
	                  <header class="panel-heading">
	                      上传图片
	                  </header>
	                  <div class="panel-body" id="attachment">
	                  	  <button id="file_upload"  class="btn btn-shadow btn-info" type="button"><i class="icon-cloud-upload"></i> 添加附件</button>
	                      <ul class="grid cs-style-3">
	                      	  <#list attachmentPage.list as attachment>
	                          <li>
	                              <figure>
	                                  <img src="${basePath}/${attachment.path}" alt="img04">
	                                  <figcaption>
	                                      <a class="js_link" href="javascript:void(0);" link="${attachment.link}"  title="链接为：${attachment.link}"  attachmentId="${attachment.attachmentId}">修改链接</a>
	                                      <a class="fancybox" rel="group" href="${basePath}/${attachment.path}">查看大图</a>
	                                      <a class="js_delete" href="javascript:void(0);" name="${attachment.name}" attachmentId="${attachment.attachmentId}">删除</a>
	                                  </figcaption>
	                              </figure>
	                          </li>
	                          </#list>
	                      </ul>
	                      ${attachmentPage.pageNumHtml}
	                  </div>
	              </section>
				</div>
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
		$('#file_upload').uploadify({
			'buttonText'  		: 	'请选择文件',
	        'swf'         		: 	'${basePath}/system/assets/uploadify/uploadify.swf',
	        'uploader'    		: 	'${basePath}/admin/attachment/upload.json;jsessionid=${JSESSIONID}',
	        'formData'  		: 	{'kindId':kindId,'kind':kind},
	        'fileObjName'		: 	'file',
	        'fileTypeExts' 		: 	'*.jpg; *.png; *.gif',
	        'method'			:	'post',
	        'onQueueComplete' 	: 	function(file, data, response) {
	        	window.location.reload();
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
			bootbox.prompt({title:"修改链接，当前链接为："+link, value:link,callback:function(result) {
				if (result === null) {
				}else{
					$.post("${basePath}/admin/attachment/update_link.json",{'attachmentId':attachmentId,'link':result},function(data){
						if(data.result){
							window.location.reload();
						}
					},"json");					
				} 
			}});			
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
