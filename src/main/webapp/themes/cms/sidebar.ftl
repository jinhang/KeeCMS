			<!-- BEGIN RIGHT SIDEBAR -->            
            <div class="col-lg-3">
                <div class="blog-side-item">
                    <div class="category">
                        <h3>分类</h3>
                        <@keecms_folder_list fatherId="${firstFolder.folderId}">
                        <ul class="list-unstyled">
                        	<#list tag_folder_list as secondLevelFolder>
                            <li><a href="${basePath}/${firstFolder.ename}/${secondLevelFolder.ename}/index.htm"><i class="  icon-angle-right"></i> ${secondLevelFolder.name}</a></li>
                            </#list>
                        </ul>
                        </@keecms_folder_list>
                    </div>
                </div>
            </div>
			<!-- END RIGHT SIDEBAR --> 
