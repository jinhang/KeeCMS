var Script = function () {


//    tool tips

    $('.tooltips').tooltip();

//    popovers

    $('.popovers').popover();

//    bxslider

    $('.bxslider').show();
    $('.bxslider').bxSlider({
        minSlides: 4,
        maxSlides: 4,
        slideWidth: 276,
        slideMargin: 20
    });

}();

/**
 * 显示表单的错误提示
 * @param id 表单ID
 * @param errors 错误列表
 */
function showErrors(id,errors){
	id.find('p[class=help-block]').hide();
	id.find('input,select,textarea').parent().parent().removeClass("has-error");
	for(var name in errors){
		var e = id.find('p[for='+name+']');
		id.find('input[name='+name+'],select[name='+name+'],textarea[name='+name+']').parent().parent().addClass("has-error");
		if(e.length==0){
			id.find('input[name='+name+'],select[name='+name+'],textarea[name='+name+']').after('<p for="'+name+'" class="help-block"></p>');
			e = id.find('p[for='+name+']');
		}
		if(errors[name]!=""){
			e.html(errors[name]);
			e.show();
		}
	}
} 