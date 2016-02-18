<#include "header.ftl">
<style>
.bx-wrapper .bx-viewport {
	-moz-box-shadow: 0 0 0px #ccc;
	-webkit-box-shadow: 0 0 0px #ccc;
	box-shadow: 0 0 0px #ccc;
	border: solid #fff 5px;
	left: -5px;
	background: #fff;
}
</style>
    <!--container start-->
    <div class="container">
        <div class="row">
			<ul class="bxslider_ad">
			<@keecms_attachment_list kindId="11" kind="folder">
			<#list tag_attachment_list as attachment>
				<li>
					<#if attachment.link=="">
					<a href="javascript:void(0);"><img style="width:1440px;height:420px;" src="${basePath}/${attachment.path}"></a>
					<#else>
					<a href="${attachment.link}"><img style="width:1440px;height:420px;" src="${basePath}/${attachment.path}"></a>
					</#if>
				</li>
			</#list>
			</@keecms_attachment_list>
			</ul>        
            <!--feature start-->
            <div class="text-center feature-head">
                <h1>欢迎来到"师说CMS"</h1>
                <p>一款划时代的产品就这样静悄悄的诞生了。</p>
            </div>
            <div class="col-lg-4 col-sm-4">
                <section>
                    <div class="f-box">
                        <i class=" icon-desktop"></i>
                        <h2>Java语言</h2>
                    </div>
                    <p class="f-text">使用Java语言的CMS，能够以最低的成本、最少的人力投入在最短的时间内架设一个功能齐全、性能稳定、规模庞大并易于维护的网站平台。</p>
                </section>
            </div>
            <div class="col-lg-4 col-sm-4">
                <section>
                    <div class="f-box active">
                        <i class=" icon-code"></i>
                        <h2>免费开源</h2>
                    </div>
                    <p class="f-text">师说CMS是开源免费的一款国产的开源项目管理软件，软件本身我们是不收取任何费用的，也不限制用户进行商业用途，方便学习，以及第二次开发。</p>
                </section>
            </div>
            <div class="col-lg-4 col-sm-4">
                <section>
                    <div class="f-box">
                        <i class="icon-gears"></i>
                        <h2>用户自定义</h2>
                    </div>
                    <p class="f-text">具有用户自定义模板功能，用户可以选择系统默认的模板，也可以根据自己的喜好，制作出独属于自己的模板，秀出不一样的风采，亮出自己的特色。</p>
                </section>
            </div>
            <!--feature end-->
        </div>
        <div class="row">
            <!--quote start-->
            <div class="quote">
                <div class="col-lg-9 col-sm-9">
                    <div class="quote-info">
                        <h1>代码下载</h1>
                        <p>源代码毫无保留，自由下载，并且提供技术支持服务。(<a href="http://git.oschina.net/kee/CMS" style="color:#9EB3C4;" target="_blank">代码托管在开源中国</a>)</p>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-3">
                    <a href="http://git.oschina.net/kee/CMS/repository/archive?ref=master" target="_blank" class="btn btn-danger purchase-btn">下载(DOWNLOAD)</a>
                </div>
            </div>
            <!--quote end-->
        </div>
    </div>
     <!--container end-->
<script>
$(function(){
	 $('.bxslider_ad').bxSlider({
	 	auto:true,
	 	pager:false
	 });
});
</script>     
     <#include "footer.ftl">