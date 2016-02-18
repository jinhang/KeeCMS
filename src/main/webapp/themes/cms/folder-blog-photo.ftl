<#include "header.ftl">
	<#include "topbar.ftl">
    <!--container start-->
    <div class="container">
        <div class="row">
            <!--blog start-->
            <div class="col-lg-9">
				          	<@keecms_attachment_page  kindId="${folder.folderId}" kind="folder" p="${p}" rows="9">
				          	<#list tag_attachment_page.list as attachment>
				            <div class="col-xs-4 gallery-item">
				                <img class="img-responsive" src="${basePath}/${attachment.path}" alt="">
				            </div>
				            
				            <#if (attachment_index+1)%3==0><hr><div class="clearfix"></div></#if>
				            </#list>
				            </@keecms_attachment_page>			
            </div>

			<#include "sidebar.ftl">
            <!--blog end-->
        </div>

    </div>
    <!--container end-->
<#include "footer.ftl">