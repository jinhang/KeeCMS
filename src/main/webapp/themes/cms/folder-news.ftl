<#include "header.ftl">
	<#include "topbar.ftl">
    <!--container start-->
    <div class="container">
        <div class="row">
            <!--blog start-->
            <div class="col-xs-9 ">
			<div class="panel panel-default">
				  <div class="panel-heading">${folder.name}</div>
				  <div class="panel-body">            
	            	<table class="table table-striped">
				      <thead>
				        <tr>
				          <th width="8"></th>
				          <th></th>
				          <th width="200"></th>
				        </tr>
				      </thead>
				      <tbody>
	            	<@keecms_article_page folderId="${folder.folderId}" p="${p}" rows="12">
					<#list tag_article_page.list as article>
				        <tr>
				          <td><i class="icon-star"></i></td>
				          <td><a href="${basePath}/<#list article.folderPathList as folder>${folder.ename}/</#list>${article.articleId}.htm">${article.name}</a></td>
				          <td>${article.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
				        </tr>
	                </#list>
	                </tbody> 
	                </table>
                  </div>
				</div>
                <div class="text-center">
					${tag_article_page.pageNumHtml}
                </div>
				</@keecms_article_page>
            </div>

			<#include "sidebar.ftl">
            <!--blog end-->
        </div>

    </div>
    <!--container end-->
<#include "footer.ftl">