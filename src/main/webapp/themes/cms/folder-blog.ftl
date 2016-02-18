<#include "header.ftl">
	<#include "topbar.ftl">
    <!--container start-->
    <div class="container">
        <div class="row">
            <!--blog start-->
            <div class="col-lg-9 ">
            	<@keecms_article_page folderId="${folder.folderId}" p="${p}" rows="12">
				<#list tag_article_page.list as article>
                <div class="blog-item">
                    <div class="row">
                        <div class="col-lg-2 col-sm-2">
	                            <div class="date-wrap">
	                                <span class="date">${article.createTime?string("MM")}月</span>
	                                <span class="month">${article.createTime?string("dd")}日</span>
	                            </div>
                        </div>
                        <div class="col-lg-10 col-sm-10">
                            <div class="blog-img">
								<#list article.attachmentList as attachment>
									<#if attachment.type == "photo" && attachment.status="display">
										<img src="${basePath}/${attachment.path}">
									<#break> 
									</#if> 
								</#list>
                            </div>
	                        <div >
	                            <h1>${article.name}</h1>
	                            <p>${article.content}</p>
	                            <!-- a href="blog_detail.html" class="btn btn-danger">Continue Reading</a -->
	                        </div>
                        </div>
                    </div>
                </div>
                </#list>
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