<#include "header.ftl">
	<#include "topbar.ftl">
    <!--container start-->
    <div class="container">

        <div class="row">
            <div class="col-lg-5 col-sm-5 address">
                <h4>长沙市师说网络科技有限公司</h4>
                <p>
                    湖南长沙芙蓉区 <br/>
                    建湘路479号，曼哈顿大厦，1702<br/>
                </p>
                <br>
                <br>
                <br>
                <p>
                    电话：<span class="muted">0731 - 89601598</span><br/>
                    邮件：<span class="muted">cms@kee.com</span><br/>
                    QQ群：<span class="muted">7343505</span>
                </p>
            </div>
            <div class="col-lg-7 col-sm-7 address">
                <h4>留言</h4>
                <div class="contact-form">
                    <form role="form" id="addFeedBack" action="${basePath}/comment/add.json" method="post" autocomplete="off">
                    	<input type="hidden" name="kindId" value="${folder.folderId}">
						<input type="hidden" name="kind" value="folder">
                        <div class="form-group">
                            <label for="name">姓名</label>
                            <input type="text" placeholder="" name="name" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="email">邮箱</label>
                            <input type="text" placeholder="" name="email" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="phone">电话</label>
                            <input type="text" name="phone" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="phone">内容</label>
                            <textarea name="content" placeholder="" rows="5" class="form-control"></textarea>
                        </div>
                        <button class="btn btn-danger" type="submit">提交</button>
                    </form>

                </div>
            </div>
        </div>
    </div>
    <!--container end-->
<script type="text/javascript">
	$(function(){
		$('#addFeedBack').ajaxForm(function(data){
			showErrors($('#addFeedBack'),data.errors);
			if(data.result){
				bootbox.alert("保存成功，将刷新页面", function() {
						window.location.reload();
					});
			}
		});
	});
</script>    
<#include "footer.ftl">