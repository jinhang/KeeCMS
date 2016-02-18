
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
	            	${article.content}
                  </div>
				</div>
            </div>
			<#include "sidebar.ftl">
            <!--blog end-->
        </div>

    </div>
    <!--container end-->
<#include "footer.ftl">
