/**
 * @描述：首页JS文件
 * @作者：彭建红
 * @时间：2015-11-17 15:56 pm
 * @版本：beta 1.0
 */
var siteUrl = '';
var isFold = false;
var actType = null;
$(document).ready(function($){
	/* PJH 1105 begin 顶部效果JS*/
	$("#tr-site-nav > li").mouseenter(function(){$(this).find(".top-nav-bg").show();$(this).css("padding","0px 15px 0px 7px");});
	$("#tr-site-nav > li").mouseleave(function(){$(this).find(".top-nav-bg").hide();$(this)});
	$("#tr-site-nav > li .top-nav-bg").mouseenter(function(){$(this).show();});
	$("#tr-site-nav > li .top-nav-bg").mouseleave(function(){$(this).hide();});
	/* PJH 1105 end 顶部效果JS*/

	/* PJH 1105 begin 鼠标移动到导航菜单上之后效果*/
	$("#categorys .site-list > li").mouseenter(function(){$(this).find(".bg").show();});
	$("#categorys .site-list > li").mouseleave(function(){$(this).find(".bg").hide();});
	/* PJH 1105 end 鼠标移动到导航菜单上之后效果*/

	/* PJH 1105 begin 鼠标移动到搜索选择切换块之后效果*/
	$("#top-search .search-form > .header-search-tab").mouseenter(function(){
		var temp_number = $(this).find("ul li").length;
		$(this).css("height",temp_number * 33).css("border-bottom","1px solid #eee");
	});
	$("#top-search .search-form > .header-search-tab").mouseleave(function(){$(this).css("height",33);});
	$("#top-search .search-form > .header-search-tab").mouseleave(function(){$(this).find(".bg").hide();});
	/* PJH 1105 end 鼠标移动到搜索选择切换块之后效果*/

	/*PJH 1113 begin 鼠标移动到购物车块部弹出购物车下拉*/
	$("#top-cart .cart-bd").mouseenter(function(){$(this).css("background-color","#FFF").css("border-bottom","none");$("#top-cart .cart-hd").show();});
	$("#top-cart .cart-bd").mouseleave(function(){$(this).css("background-color","#F9F9F9").css("border-bottom","1px solid #ddd");$("#top-cart .cart-hd").hide();});
	$("#top-cart .cart-hd").mouseenter(function(){$("#top-cart .cart-bd").css("background-color","#FFF").css("border-bottom","none");$(this).show();});
	$("#top-cart .cart-hd").mouseleave(function(){$("#top-cart .cart-bd").css("background-color","#F9F9F9").css("border-bottom","1px solid #ddd");$(this).hide();});
	/*PJH 1113 end 鼠标移动到购物车块部弹出购物车下拉*/

	/*PJH 1117 begin 鼠标移动到分类导航名称上面弹出对应的分类菜单下拉*/
	$("#categorys > dt").mouseenter(function(){$(this).parent().find("> .site-list").slideDown();});
	$("#categorys > dt").mouseleave(function(){$(this).parent().find("> .site-list").slideUp();});
	$("#categorys > .site-list").mouseenter(function(){$(this).slideDown();});
	$("#categorys > .site-list").mouseleave(function(){$(this).slideUp();});
	/*PJH 1117 end 鼠标移动到分类导航名称上面弹出对应的分类菜单下拉*/

	/* JustFresh 2017-04-19 begin 右侧浮动窗口JS*/
	$("#rtoolbar .rtoolbar-top li").mouseenter(function(){$(this).find(".list-text-intro").show();});
	$("#rtoolbar .rtoolbar-top li").mouseleave(function(){$(this).find(".list-text-intro").hide();});

	$("#rtoolbar .rtoolbar-bottom .rtoolbar-list").mouseenter(function(){$(this).find(".codes-poptip").show();});
	$("#rtoolbar .rtoolbar-bottom .rtoolbar-list").mouseleave(function(){$(this).find(".codes-poptip").hide();});
	/* JustFresh 2017-04-19 begin 右侧浮动窗口JS*/

	$("#recommends ul.tab-nav li").hover(function(){
		$(this).addClass("selected").siblings().removeClass("selected");
		$("#recommends ul.tab-panel li").eq($(this).index()).addClass("selected").siblings().removeClass("selected");
	});

	$(window).scroll(function(event){
		if($(window).scrollTop() > 700){
			$("#lfloorbar").fadeIn();
		}else{
			$("#lfloorbar").fadeOut();
		}
	});

}(jQuery));

/**
 * @desc:处理浮动于右侧的导航折叠/展开
 */
function foldToolbar(object,type){
	if(isFold){

		$("#rtoolbar .rtoolbar-top li").each(function(){$(this).removeClass("selected");});
		$("#rtoolbar .rtoolbar-bottom .rtoolbar-list").each(function(){$(this).removeClass("selected");});
		if(actType == null || actType == type){
			//折叠
			$("#rtoolbar").animate({right: "-250px"},"fast");
			actType = null;
			isFold = false;
		}else{
			$(object).addClass("selected");
			appendToolbarContent(type);
			actType = type;
			isFold = true;
		}

	}else{
		$("#rtoolbar").animate({right: "0px"},"fast");
		appendToolbarContent(type);

		$("#rtoolbar .rtoolbar-top li").each(function(){$(this).removeClass("selected");});
		$("#rtoolbar .rtoolbar-bottom .rtoolbar-list").each(function(){$(this).removeClass("selected");});
		$(object).addClass("selected");
		actType = type;
		isFold = true;
	}
}

/**
 * 展开并根据传入的类型参数查询对应的信息展示
 */
function appendToolbarContent(type){
	/*
	 * type值:
	 * 	seller：查询十个推荐商家
	 * 	service：查询十个推荐服务
	 * 	collect：查询用户的收藏服务（前十个），若未登录提示登录
	 * 	consult：弹出咨询对话框信息
	 * 	openSeller：弹出服务商入驻信息及链接
	 * 	app：扫码下载APP
	 */
	var tempStr = "";
	//1.十个推荐商家html
	if(type == "seller"){
		tempStr += "<ul class='seller-list'>";
		tempStr += "<li><a href='seller.html' title='捷迅财税服务网'><img src='/taxation-web/images/services/11.jpg' /></a></li>";
		tempStr += "<li><a href='seller.html' title='捷迅财税服务网'><img src='/taxation-web/images/services/12.jpg' /></a></li>";
		tempStr += "<li><a href='seller.html' title='捷迅财税服务网'><img src='/taxation-web/images/services/13.jpg' /></a></li>";
		tempStr += "<li><a href='seller.html' title='捷迅财税服务网'><img src='/taxation-web/images/services/14.jpg' /></a></li>";
		tempStr += "<li><a href='seller.html' title='捷迅财税服务网'><img src='/taxation-web/images/services/15.jpg' /></a></li>";
		tempStr += "</ul>";
	}else if(type == "service"){
		tempStr += "<ul class='service-list'>";
		tempStr += "<li><a href='seller.html' title='捷迅财税服务网'><img src='/taxation-web/images/services/16.jpg' /></a></li>";
		tempStr += "<li><a href='seller.html' title='捷迅财税服务网'><img src='/taxation-web/images/services/17.jpg' /></a></li>";
		tempStr += "<li><a href='seller.html' title='捷迅财税服务网'><img src='/taxation-web/images/services/18.jpg' /></a></li>";
		tempStr += "<li><a href='seller.html' title='捷迅财税服务网'><img src='/taxation-web/images/services/19.jpg' /></a></li>";
		tempStr += "<li><a href='seller.html' title='捷迅财税服务网'><img src='/taxation-web/images/services/20.jpg' /></a></li>";
		tempStr += "<li><a href='seller.html' title='捷迅财税服务网'><img src='/taxation-web/images/services/21.jpg' /></a></li>";
		tempStr += "</ul>";
	}else if(type == "collect"){
		//如果未登录
		tempStr += "<p class='no-login'>请您 <a href='login.html'>登录</a> 后查看</p>";
	}else if(type == "consult"){
		tempStr += "<ul class='consult-list'>";
		tempStr += "<a href='javascript: void(0)' onclick='appointService()'><li><i class='fa fa-male'></i><h3>预约服务</h3>";
		tempStr += "<p>留下您的联系方式<br/>我们会尽快与您联系</p></li></a>";
		tempStr += "<a href='suggest.html'><li><i class='fa fa-edit'></i><h3>意见反馈</h3>";
		tempStr += "<p>您的满意<br/>是我们前进的动力</p></li></a>";
		tempStr += "</ul>";
	}else if(type == "openSeller"){
		tempStr += "<div class='attract'><img src='/taxation-web/images/attract-info.png' title='捷迅财税服务网' /><br/>";
		tempStr += "<a href='shop/apply.html'>立即入驻</a></div>";
	}else if(type = "app"){
		tempStr += "<ul class='app-list'>";
		tempStr += "<li><div class='info pull-left'><h3>安卓版</h3><p>扫描下载安装</p></div>";
		tempStr += "<img src='/taxation-web/images/android.png' class='pull-left'/><div class='clearfix'></div></li>";
		tempStr += "<li><div class='info pull-left'><h3>苹果版</h3><p>扫描下载安装</p></div>";
		tempStr += "<img src='/taxation-web/images/ios.png' class='pull-left'/><div class='clearfix'></div></li>";
		tempStr += "<li><div class='info pull-left'><h3>公众号</h3><p>关注公众平台</p></div>";
		tempStr += "<img src='/taxation-web/images/qr-code.png' class='pull-left'/><div class='clearfix'></div></li>";
		tempStr += "</ul>";
	}

	$("#rtoolbar .rtoolbar-content").empty();
	$("#rtoolbar .rtoolbar-content").append(tempStr);
}

function goTop(){
	$('body,html').animate({ scrollTop: 0 },"normal");
}
function closeDialog(id){
	$("#dialogBg").fadeOut();
	$(id).fadeOut();
}
function openDialog(id){
	$("#dialogBg").fadeIn();
	$(id).fadeIn();
}
function goNextStep(){
	//进行同意并继续的操作
	$("input[name='agree']").attr("checked","true");
	$("input[name='agree']").attr("ng-checked","true");  
	//关闭弹框
	closeDialog("#agreementDialog");
}
function next(){
	//进行同意并继续的操作
	if($("input[name='agree']").is(":checked")){
		
	}else{
		alert("请勾选同意用户注册协议");
		return;
	}
	
}