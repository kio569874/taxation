/**
 * @描述：首页产品列表的推荐部分图片轮播JS文件
 * @作者：彭建红
 * @时间：2015-11-12 23:05 pm
 * @版本：beta 1.0
 */
 $(function(){
 	/*PJH 1112 begin 10类产品的轮播*/
 	var temp_width = 567;
	var r_slider1 = $("#r_slider1");
	var r_slider2 = $("#r_slider2");
	var r_slider3 = $("#r_slider3");
	var r_slider4 = $("#r_slider4");
	var r_slider5 = $("#r_slider5");
	var r_slider6 = $("#r_slider6");
	var r_slider7 = $("#r_slider7");
	var r_slider8 = $("#r_slider8");
	var r_slider9 = $("#r_slider9");
	var r_slider10 = $("#r_slider10");
	var r_slider1_time_length = 2000 + Math.random() * 4000;
	var r_slider2_time_length = 2000 + Math.random() * 4000;
	var r_slider3_time_length = 2000 + Math.random() * 4000;
	var r_slider4_time_length = 2000 + Math.random() * 4000;
	var r_slider5_time_length = 2000 + Math.random() * 4000;
	var r_slider6_time_length = 2000 + Math.random() * 4000;
	var r_slider7_time_length = 2000 + Math.random() * 4000;
	var r_slider8_time_length = 2000 + Math.random() * 4000;
	var r_slider9_time_length = 2000 + Math.random() * 4000;
	var r_slider10_time_length = 2000 + Math.random() * 4000;
	sliderOpt(r_slider1);
	sliderOpt(r_slider2);
	sliderOpt(r_slider3);
	sliderOpt(r_slider4);
	sliderOpt(r_slider5);
	sliderOpt(r_slider6);
	sliderOpt(r_slider7);
	sliderOpt(r_slider8);
	sliderOpt(r_slider9);
	sliderOpt(r_slider10);
	var r_slider1_timer = setInterval(function(){
		var temp_num = r_slider1.find(".slider-num > li.selected").index();
		if(temp_num == r_slider1.find(".slider-num > li").length - 1){
			temp_num = 0;
		}else{
			temp_num++;
		}
		r_slider1.find(".slider-num >li").removeClass("selected");
		r_slider1.find(".slider-num >li").eq(temp_num).addClass("selected");
		r_slider1.find("> .slider").css("left",-temp_width * temp_num);
	},r_slider1_time_length);
	var r_slider2_timer = setInterval(function(){
		var temp_num = r_slider2.find(".slider-num > li.selected").index();
		if(temp_num == r_slider2.find(".slider-num > li").length - 1){
			temp_num = 0;
		}else{
			temp_num++;
		}
		r_slider2.find(".slider-num >li").removeClass("selected");
		r_slider2.find(".slider-num >li").eq(temp_num).addClass("selected");
		r_slider2.find("> .slider").css("left",-temp_width * temp_num);
	},r_slider2_time_length);
	var r_slider3_timer = setInterval(function(){
		var temp_num = r_slider3.find(".slider-num > li.selected").index();
		if(temp_num == r_slider3.find(".slider-num > li").length - 1){
			temp_num = 0;
		}else{
			temp_num++;
		}
		r_slider3.find(".slider-num >li").removeClass("selected");
		r_slider3.find(".slider-num >li").eq(temp_num).addClass("selected");
		r_slider3.find("> .slider").css("left",-temp_width * temp_num);
	},r_slider3_time_length);
	var r_slider4_timer = setInterval(function(){
		var temp_num = r_slider4.find(".slider-num > li.selected").index();
		if(temp_num == r_slider4.find(".slider-num > li").length - 1){
			temp_num = 0;
		}else{
			temp_num++;
		}
		r_slider4.find(".slider-num >li").removeClass("selected");
		r_slider4.find(".slider-num >li").eq(temp_num).addClass("selected");
		r_slider4.find("> .slider").css("left",-temp_width * temp_num);
	},r_slider4_time_length);
	var r_slider5_timer = setInterval(function(){
		var temp_num = r_slider5.find(".slider-num > li.selected").index();
		if(temp_num == r_slider5.find(".slider-num > li").length - 1){
			temp_num = 0;
		}else{
			temp_num++;
		}
		r_slider5.find(".slider-num >li").removeClass("selected");
		r_slider5.find(".slider-num >li").eq(temp_num).addClass("selected");
		r_slider5.find("> .slider").css("left",-temp_width * temp_num);
	},r_slider5_time_length);
	var r_slider6_timer = setInterval(function(){
		var temp_num = r_slider6.find(".slider-num > li.selected").index();
		if(temp_num == r_slider6.find(".slider-num > li").length - 1){
			temp_num = 0;
		}else{
			temp_num++;
		}
		r_slider6.find(".slider-num >li").removeClass("selected");
		r_slider6.find(".slider-num >li").eq(temp_num).addClass("selected");
		r_slider6.find("> .slider").css("left",-temp_width * temp_num);
	},r_slider6_time_length);
	var r_slider7_timer = setInterval(function(){
		var temp_num = r_slider7.find(".slider-num > li.selected").index();
		if(temp_num == r_slider7.find(".slider-num > li").length - 1){
			temp_num = 0;
		}else{
			temp_num++;
		}
		r_slider7.find(".slider-num >li").removeClass("selected");
		r_slider7.find(".slider-num >li").eq(temp_num).addClass("selected");
		r_slider7.find("> .slider").css("left",-temp_width * temp_num);
	},r_slider7_time_length);
	var r_slider8_timer = setInterval(function(){
		var temp_num = r_slider8.find(".slider-num > li.selected").index();
		if(temp_num == r_slider8.find(".slider-num > li").length - 1){
			temp_num = 0;
		}else{
			temp_num++;
		}
		r_slider8.find(".slider-num >li").removeClass("selected");
		r_slider8.find(".slider-num >li").eq(temp_num).addClass("selected");
		r_slider8.find("> .slider").css("left",-temp_width * temp_num);
	},r_slider8_time_length);
	var r_slider9_timer = setInterval(function(){
		var temp_num = r_slider9.find(".slider-num > li.selected").index();
		if(temp_num == r_slider9.find(".slider-num > li").length - 1){
			temp_num = 0;
		}else{
			temp_num++;
		}
		r_slider9.find(".slider-num >li").removeClass("selected");
		r_slider9.find(".slider-num >li").eq(temp_num).addClass("selected");
		r_slider9.find("> .slider").css("left",-temp_width * temp_num);
	},r_slider9_time_length);
	var r_slider10_timer = setInterval(function(){
		var temp_num = r_slider10.find(".slider-num > li.selected").index();
		if(temp_num == r_slider10.find(".slider-num > li").length - 1){
			temp_num = 0;
		}else{
			temp_num++;
		}
		r_slider10.find(".slider-num >li").removeClass("selected");
		r_slider10.find(".slider-num >li").eq(temp_num).addClass("selected");
		r_slider10.find("> .slider").css("left",-temp_width * temp_num);
	},r_slider10_time_length);

	r_slider1.mouseenter(function(){clearInterval(r_slider1_timer)});
	r_slider2.mouseenter(function(){clearInterval(r_slider2_timer)});
	r_slider3.mouseenter(function(){clearInterval(r_slider3_timer)});
	r_slider4.mouseenter(function(){clearInterval(r_slider4_timer)});
	r_slider5.mouseenter(function(){clearInterval(r_slider5_timer)});
	r_slider6.mouseenter(function(){clearInterval(r_slider6_timer)});
	r_slider7.mouseenter(function(){clearInterval(r_slider7_timer)});
	r_slider8.mouseenter(function(){clearInterval(r_slider8_timer)});
	r_slider9.mouseenter(function(){clearInterval(r_slider9_timer)});
	r_slider10.mouseenter(function(){clearInterval(r_slider10_timer)});

	r_slider1.mouseleave(function(){
		r_slider1_timer = setInterval(function(){
			var temp_num = r_slider1.find(".slider-num > li.selected").index();
			if(temp_num == r_slider1.find(".slider-num > li").length - 1){
				temp_num = 0;
			}else{
				temp_num++;
			}
			r_slider1.find(".slider-num >li").removeClass("selected");
			r_slider1.find(".slider-num >li").eq(temp_num).addClass("selected");
			r_slider1.find("> .slider").css("left",-temp_width * temp_num);
		},r_slider1_time_length);
	});
	r_slider2.mouseleave(function(){
		r_slider2_timer = setInterval(function(){
			var temp_num = r_slider2.find(".slider-num > li.selected").index();
			if(temp_num == r_slider2.find(".slider-num > li").length - 1){
				temp_num = 0;
			}else{
				temp_num++;
			}
			r_slider2.find(".slider-num >li").removeClass("selected");
			r_slider2.find(".slider-num >li").eq(temp_num).addClass("selected");
			r_slider2.find("> .slider").css("left",-temp_width * temp_num);
		},r_slider2_time_length);
	});
	r_slider3.mouseleave(function(){
		r_slider3_timer = setInterval(function(){
			var temp_num = r_slider3.find(".slider-num > li.selected").index();
			if(temp_num == r_slider3.find(".slider-num > li").length - 1){
				temp_num = 0;
			}else{
				temp_num++;
			}
			r_slider3.find(".slider-num >li").removeClass("selected");
			r_slider3.find(".slider-num >li").eq(temp_num).addClass("selected");
			r_slider3.find("> .slider").css("left",-temp_width * temp_num);
		},r_slider3_time_length);
	});
	r_slider4.mouseleave(function(){
		r_slider4_timer = setInterval(function(){
			var temp_num = r_slider4.find(".slider-num > li.selected").index();
			if(temp_num == r_slider4.find(".slider-num > li").length - 1){
				temp_num = 0;
			}else{
				temp_num++;
			}
			r_slider4.find(".slider-num >li").removeClass("selected");
			r_slider4.find(".slider-num >li").eq(temp_num).addClass("selected");
			r_slider4.find("> .slider").css("left",-temp_width * temp_num);
		},r_slider4_time_length);
	});
	r_slider5.mouseleave(function(){
		r_slider5_timer = setInterval(function(){
			var temp_num = r_slider5.find(".slider-num > li.selected").index();
			if(temp_num == r_slider5.find(".slider-num > li").length - 1){
				temp_num = 0;
			}else{
				temp_num++;
			}
			r_slider5.find(".slider-num >li").removeClass("selected");
			r_slider5.find(".slider-num >li").eq(temp_num).addClass("selected");
			r_slider5.find("> .slider").css("left",-temp_width * temp_num);
		},r_slider5_time_length);
	});
	r_slider6.mouseleave(function(){
		r_slider6_timer = setInterval(function(){
			var temp_num = r_slider6.find(".slider-num > li.selected").index();
			if(temp_num == r_slider6.find(".slider-num > li").length - 1){
				temp_num = 0;
			}else{
				temp_num++;
			}
			r_slider6.find(".slider-num >li").removeClass("selected");
			r_slider6.find(".slider-num >li").eq(temp_num).addClass("selected");
			r_slider6.find("> .slider").css("left",-temp_width * temp_num);
		},r_slider6_time_length);
	});
	r_slider7.mouseleave(function(){
		r_slider7_timer = setInterval(function(){
			var temp_num = r_slider7.find(".slider-num > li.selected").index();
			if(temp_num == r_slider7.find(".slider-num > li").length - 1){
				temp_num = 0;
			}else{
				temp_num++;
			}
			r_slider7.find(".slider-num >li").removeClass("selected");
			r_slider7.find(".slider-num >li").eq(temp_num).addClass("selected");
			r_slider7.find("> .slider").css("left",-temp_width * temp_num);
		},r_slider7_time_length);
	});
	r_slider8.mouseleave(function(){
		r_slider8_timer = setInterval(function(){
			var temp_num = r_slider8.find(".slider-num > li.selected").index();
			if(temp_num == r_slider8.find(".slider-num > li").length - 1){
				temp_num = 0;
			}else{
				temp_num++;
			}
			r_slider8.find(".slider-num >li").removeClass("selected");
			r_slider8.find(".slider-num >li").eq(temp_num).addClass("selected");
			r_slider8.find("> .slider").css("left",-temp_width * temp_num);
		},r_slider8_time_length);
	});
	r_slider9.mouseleave(function(){
		r_slider9_timer = setInterval(function(){
			var temp_num = r_slider9.find(".slider-num > li.selected").index();
			if(temp_num == r_slider9.find(".slider-num > li").length - 1){
				temp_num = 0;
			}else{
				temp_num++;
			}
			r_slider9.find(".slider-num >li").removeClass("selected");
			r_slider9.find(".slider-num >li").eq(temp_num).addClass("selected");
			r_slider9.find("> .slider").css("left",-temp_width * temp_num);
		},r_slider9_time_length);
	});
	r_slider10.mouseleave(function(){
		r_slider10_timer = setInterval(function(){
			var temp_num = r_slider10.find(".slider-num > li.selected").index();
			if(temp_num == r_slider10.find(".slider-num > li").length - 1){
				temp_num = 0;
			}else{
				temp_num++;
			}
			r_slider10.find(".slider-num >li").removeClass("selected");
			r_slider10.find(".slider-num >li").eq(temp_num).addClass("selected");
			r_slider10.find("> .slider").css("left",-temp_width * temp_num);
		},r_slider10_time_length);
	});
	/*封装方法部分*/
	function sliderOpt(opt){
		opt.find(".slider-num > li").mouseover(function(){
			$(this).parent().find("li").removeClass("selected");
			$(this).addClass("selected");
			//opt.find("> .slider").animate({"left":-temp_width * $(this).index()},"fast");
			opt.find("> .slider").css("left",-temp_width * $(this).index());
		});
		opt.find("> .prev").click(function(){
			var temp_num = $(this).parent().find(".slider-num > li.selected").index();
			if(temp_num == 0){
				temp_num = $(this).parent().find(".slider-num > li").length - 1;
			}else{
				temp_num--;
			}
			opt.find(".slider-num >li").removeClass("selected");
			opt.find(".slider-num >li").eq(temp_num).addClass("selected");
			//opt.find("> .slider").animate({"left":-temp_width * $(this).index()},"fast");
			opt.find("> .slider").css("left",-temp_width * temp_num);
		});
		opt.find("> .next").click(function(){
			var temp_num = $(this).parent().find(".slider-num > li.selected").index();
			if(temp_num == $(this).parent().find(".slider-num > li").length - 1){
				temp_num = 0;
			}else{
				temp_num++;
			}
			opt.find(".slider-num >li").removeClass("selected");
			opt.find(".slider-num >li").eq(temp_num).addClass("selected");
			//opt.find("> .slider").animate({"left":-temp_width * $(this).index()},"fast");
			opt.find("> .slider").css("left",-temp_width * temp_num);
		});
	}
	/*PJH 1112 end 10类产品的轮播*/
 });