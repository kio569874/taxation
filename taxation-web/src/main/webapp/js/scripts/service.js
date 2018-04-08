/**
 * 
 */
$(document).ready(function($){

	var sliderLen = $("#main .baseinfo .imgs .big-img-list ul.service-content li").length;
	$("#main .baseinfo .imgs .small-img-list ul li").hover(function(){
		$(this).addClass("selected").siblings().removeClass("selected");
		$("#main .baseinfo .imgs .big-img-list ul.service-content li").eq($(this).index()).addClass("selected").siblings().removeClass("selected");
		$("#main .baseinfo .imgs .big-img-list ul.service-content").animate({left: -340 * $(this).index()});
	});
	$("#main .baseinfo .imgs .big-img-list .prev").click(function(){
		var act = $("#main .baseinfo .imgs .small-img-list ul li.selected").index();
		if(act == 0){
			act = sliderLen - 1;
		}else{
			act--;
		}
		$("#main .baseinfo .imgs .big-img-list ul.service-content li").eq(act).addClass("selected").siblings().removeClass("selected");
		$("#main .baseinfo .imgs .small-img-list ul li").eq(act).addClass("selected").siblings().removeClass("selected");
		$("#main .baseinfo .imgs .big-img-list ul.service-content").animate({left: -340 * act});
	});
	$("#main .baseinfo .imgs .big-img-list .next").click(function(){
		var act = $("#main .baseinfo .imgs .small-img-list ul li.selected").index();
		if(act == sliderLen - 1){
			act = 0;
		}else{
			act++;
		}
		$("#main .baseinfo .imgs .big-img-list ul.service-content li").eq(act).addClass("selected").siblings().removeClass("selected");
		$("#main .baseinfo .imgs .small-img-list ul li").eq(act).addClass("selected").siblings().removeClass("selected");
		$("#main .baseinfo .imgs .big-img-list ul.service-content").animate({left: -340 * act});
	});
	$("#main .baseinfo .imgs .small-img-list .prev").click(function(){
		var act = $("#main .baseinfo .imgs .small-img-list ul li.selected").index();
		if(act == 0){
			act = sliderLen - 1;
		}else{
			act--;
		}
		$("#main .baseinfo .imgs .big-img-list ul.service-content li").eq(act).addClass("selected").siblings().removeClass("selected");
		$("#main .baseinfo .imgs .small-img-list ul li").eq(act).addClass("selected").siblings().removeClass("selected");
		$("#main .baseinfo .imgs .big-img-list ul.service-content").animate({left: -340 * act});
	});
	$("#main .baseinfo .imgs .small-img-list .next").click(function(){
		var act = $("#main .baseinfo .imgs .small-img-list ul li.selected").index();
		if(act == sliderLen - 1){
			act = 0;
		}else{
			act++;
		}
		$("#main .baseinfo .imgs .big-img-list ul.service-content li").eq(act).addClass("selected").siblings().removeClass("selected");
		$("#main .baseinfo .imgs .small-img-list ul li").eq(act).addClass("selected").siblings().removeClass("selected");
		$("#main .baseinfo .imgs .big-img-list ul.service-content").animate({left: -340 * act});
	});
}(jQuery));

var addToCart = function(){
	$("#modalBg").fadeIn();
	//ajax添加到购物车
	$("#addToCartSuccessDialog").fadeIn();
}

var closeDialog = function(id){$("#modalBg").fadeOut();$(id).fadeOut();}

var buyNow = function(goods_id){
	//goods_id 产品ID
	var buy_num = $("#buyNum").val();//购买数量
	//判断当前是否有用户登录了
	var loginFlag = false;
	if(!loginFlag){
		$("#modalBg").fadeIn();
		//弹出登录对话框
		$("#loginDialog").fadeIn();
	}
}