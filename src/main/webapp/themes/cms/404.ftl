<#include "header.ftl">
<style>
.page-404 {
    padding-bottom: 108px;
    padding-top: 100px;
    text-align: center;
}
.page-404 .number {
    color: #0DA3E2;
    display: inline-block;
    font-size: 128px;
    font-weight: 300;
    letter-spacing: -10px;
    line-height: 128px;
    margin-bottom: 10px;
    margin-top: 0;
    position: relative;
    text-align: right;
    top: 35px;
}
.page-404 .details {
    display: inline-block;
    margin-left: 40px;
    padding-top: 0;
    text-align: left;
}

</style>
   <!-- BEGIN PAGE CONTAINER -->  
    <div class="page-container">
  
        <!-- BEGIN BREADCRUMBS -->   
        <div class="row breadcrumbs">
            <div class="container">
                <div class="col-md-4 col-sm-4">
                    <h1>Page Not Founded</h1>
                </div>
                <div class="col-md-8 col-sm-8">
                    <ul class="pull-right breadcrumb">
                        <li><a href="${basePath}/index.htm">Home</a></li>
                        <li class="active"><a href="${basePath}/404.htm">404</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- END BREADCRUMBS -->

        <!-- BEGIN CONTAINER -->   
        <div class="container margin-bottom-40">
          <div class="row">
            <div class="col-md-12 page-404">
               <div class="number">
                  404
               </div>
               <div class="details">
                  <h3>Oops!  You're lost.</h3>
                  <p>
                     We can not find the page you're looking for.<br>
                     <a href="index.html">Return home</a> or try the search bar below.
                  </p>
                  <form action="#">
                     <div class="input-group input-medium">
                        <input type="text" placeholder="keyword..." class="form-control">
                        <span class="input-group-btn">                   
                        <button class="btn blue" type="submit"><i class="fa fa-search"></i></button>
                        </span>
                     </div>
                     <!-- /input-group -->
                  </form>
               </div>
            </div>
          </div>
        </div>
        <!-- END CONTAINER -->

  </div>
    <!-- END PAGE CONTAINER -->  
<#include "footer.ftl">
