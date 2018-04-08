var taxationApp = angular.module("taxationApp",
    [
        "ui.router",
        "toastr",
        "oc.lazyLoad",
        "tm.pagination",
        "angularFileUpload"
    ]);
taxationApp.config(function(toastrConfig) {
    angular.extend(toastrConfig, {
        autoDismiss: false,
        timeOut:2000,
        containerId: 'toast-container',
        maxOpened: 0,
        newestOnTop: true,
        positionClass: 'toast-top-center',
        preventDuplicates: false,
        preventOpenDuplicates: false,
        target: 'body'
    });
});
taxationApp.controller('HeaderController',['$scope','$state',function($scope,$state) {
    var user = {};
    user = angular.fromJson(sessionStorage.getItem("user"));
	$scope.user = user;
	
    $scope.$on('$viewContentLoaded', function() {
        /* PJH 1105 begin 顶部效果JS*/
        $("#tr-site-nav > li").mouseenter(function(){$(this).find(".top-nav-bg").show();$(this).css("padding","0px 15px 0px 7px");});
        $("#tr-site-nav > li").mouseleave(function(){$(this).find(".top-nav-bg").hide();$(this)});
        $("#tr-site-nav > li .top-nav-bg").mouseenter(function(){$(this).show();});
        $("#tr-site-nav > li .top-nav-bg").mouseleave(function(){$(this).hide();});
        /* PJH 1105 end 顶部效果JS*/
    });
    $scope.gotoIndex = function(){
   		$state.go("index",{},{reload:true});
        //window.location = "/taxation-web/views/index.html";
    }
    $scope.gotoUcenter = function(){
        $state.go("index.ucenter",{},{reload:true});
    }
    $scope.loginOut = function(){
    	sessionStorage.removeItem("user");
        $state.go("index.login",{},{reload:true});
    }
}]);

taxationApp.controller('SearchController', ['$scope', function($scope) {
    $scope.$on('$viewContentLoaded', function() {
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
    });
}]);
taxationApp.controller('MenuController', ['$scope', function($scope) {

    $scope.$on('$viewContentLoaded', function() {
        /* PJH 1105 begin 鼠标移动到导航菜单上之后效果*/
        //$("#categorys .site-list > li").mouseenter(function(){$(this).find(".bg").show();});
        //$("#categorys .site-list > li").mouseleave(function(){$(this).find(".bg").hide();});
        $("#categorys .site-list > li").mouseenter(function(){$(this).find(".bg").show();});
        $("#categorys .site-list > li").mouseleave(function(){$(this).find(".bg").hide();});

        $("#categorys > dt").mouseenter(function(){$(this).parent().find("> .site-list").slideDown();});
        $("#categorys > dt").mouseleave(function(){$(this).parent().find("> .site-list").slideUp();});
        $("#categorys > .site-list").mouseenter(function(){$(this).slideDown();});
        $("#categorys > .site-list").mouseleave(function(){$(this).slideUp();});

        var winWidth = $(window).width();
        $("#providerSlider").width(winWidth);
        $("#providerSlider").height(winWidth * 48 / 192);
        $("#providerSlider .slider-box li img").css("width",winWidth);
        var sliderLen = $("#providerSlider .slider-box li").length;
        $("#providerSlider .slider-num").css("margin-left",-20*sliderLen);
        $("#providerSlider .slider-num li").hover(function(){
            $(this).addClass("selected").siblings().removeClass("selected");
            $("#providerSlider .slider-box").animate({left: -winWidth*$(this).index()});
            $("#providerSlider .slider-box li").eq($(this).index()).addClass("selected").siblings().removeClass("selected");
        });
        var sliderInterval = setInterval(function(){
            var act = $("#providerSlider .slider-num li.selected").index();
            if(act == sliderLen - 1){
                act = 0;
            }else{
                act++;
            }
            $("#providerSlider .slider-num li").eq(act).addClass("selected").siblings().removeClass("selected");
            $("#providerSlider .slider-box").animate({left: -winWidth*act});
            $("#providerSlider .slider-box li").eq(act).addClass("selected").siblings().removeClass("selected");
        },3000);
        $("#providerSlider").mouseenter(function(){
            clearInterval(sliderInterval);
        });
        $("#providerSlider").mouseleave(function(){
            sliderInterval = setInterval(function(){
                var act = $("#providerSlider .slider-num li.selected").index();
                if(act == sliderLen - 1){
                    act = 0;
                }else{
                    act++;
                }
                $("#providerSlider .slider-num li").eq(act).addClass("selected").siblings().removeClass("selected");
                $("#providerSlider .slider-box").animate({left: -winWidth*act});
                $("#providerSlider .slider-box li").eq(act).addClass("selected").siblings().removeClass("selected");
            },3000);
        });
        /* PJH 1105 end 鼠标移动到导航菜单上之后效果*/
    });
}]);
taxationApp.controller('LfloorbarController', ['$scope', function($scope) {

}]);

taxationApp.controller('RtoolbarController', ['$scope', function($scope) {
	 $scope.$on('$viewContentLoaded', function() {
        /* JustFresh 2017-04-19 begin 右侧浮动窗口JS*/
        $("#rtoolbar .rtoolbar-top li").mouseenter(function(){$(this).find(".list-text-intro").show();});
        $("#rtoolbar .rtoolbar-top li").mouseleave(function(){$(this).find(".list-text-intro").hide();});

        $("#rtoolbar .rtoolbar-bottom .rtoolbar-list").mouseenter(function(){$(this).find(".codes-poptip").show();});
        $("#rtoolbar .rtoolbar-bottom .rtoolbar-list").mouseleave(function(){$(this).find(".codes-poptip").hide();});
        /* JustFresh 2017-04-19 begin 右侧浮动窗口JS*/
    });
}]);

taxationApp.controller('HcarouselController', ['$scope', function($scope) {

}]);

taxationApp.controller('RfloatController', ['$scope', function($scope) {

}]);


taxationApp.controller('BeltadController', ['$scope', function($scope) {

}]);

taxationApp.controller('TorecommendController', ['$scope', function($scope) {

}]);

taxationApp.controller('FloornaviController', ['$scope', function($scope) {

}]);

taxationApp.controller('GuesslikeController', ['$scope', function($scope) {

}]);

taxationApp.controller('RecommendservicerController', ['$scope', function($scope) {

}]);

taxationApp.controller('FooterController', ['$scope', function($scope) {

}]);
/* 设置所有页面路由 */
taxationApp.config(['$stateProvider','$httpProvider', '$urlRouterProvider', function($stateProvider,$httpProvider, $urlRouterProvider) {
    // 重定向不匹配的url
    $urlRouterProvider.otherwise("/index.html");
    $stateProvider
        .state('index', {
            url:'/index.html',
            cache:false,
            views:{
                //这里必须要绝对定位
                'header@':{
                    templateUrl: '/taxation-web/views/common/header.html',
                    controller: 'HeaderController'
                },
                "footer@":{
                    templateUrl: '/taxation-web/views/common/footer.html',
                    controller: 'FooterController'
                },
                "home@":{
                    templateUrl:"/taxation-web/views/home.html"
                },
                "rtoolbar@index":{
                    templateUrl: '/taxation-web/views/common/rtoolbar.html',
                    controller: 'RtoolbarController'
                },
                "logo@index":{
                    templateUrl:"/taxation-web/views/common/logo.html"
                },
                "searach@index":{
                    templateUrl: '/taxation-web/views/common/search.html',
                    controller: 'SearchController'
                },
                "menu@index":{
                    templateUrl: '/taxation-web/views/common/menu.html',
                    controller: 'MenuController'
                },
                "lfloorbar@index":{
                    templateUrl: '/taxation-web/views/common/lfloorbar.html',
                    controller: 'LfloorbarController'
                },
                "hcarousel@index":{
                    templateUrl: '/taxation-web/views/common/hcarousel.html',
                    controller: 'HcarouselController'
                },
                "rfloat@index":{
                    templateUrl: '/taxation-web/views/common/rfloat.html',
                    controller: 'RfloatController'
                },
                "torecommend@index":{
                    templateUrl: '/taxation-web/views/common/torecommend.html',
                    controller: 'TorecommendController'
                },
                "floornavi@index":{
                    templateUrl: '/taxation-web/views/common/floornavi.html',
                    controller: 'FloornaviController'
                },
                "guesslike@index":{
                    templateUrl: '/taxation-web/views/common/guesslike.html',
                    controller: 'GuesslikeController'
                },
                "recommendservicer@index":{
                    templateUrl: '/taxation-web/views/common/recommendservicer.html',
                    controller: 'RecommendservicerController'
                }
            }
        })
        .state("index.login", {
            url: '/login.html/:from',
            cache:false,
            views:{
                'header@':{
                    templateUrl: '/taxation-web/views/common/header.html',
                    controller: 'HeaderController'
                },
                "footer@":{
                    templateUrl: '/taxation-web/views/common/footer.html',
                    controller: 'FooterController'
                },
                'home@':{
                    templateUrl: '/taxation-web/views/login.html',
                    controller: 'UserLoginController',
                    resolve:{
                        loadMyCtrl:function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                name:'taxationApp',
                                files:["/taxation-web/css/pagecss/login.css"]
                            })
                        }
                    }
                }
            }
        })
        .state("index.busregister", {
            url: '/chooseService.html',
            views:{
                'home@':{
                    templateUrl: '/taxation-web/views/businessregister/chooseService.html',
                    controller: 'ChooseServiceController',
                    resolve:{
                        loadMyCtrl:function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                name:'taxationApp',
                                files:[
                                    "/taxation-web/css/pagecss/business.css"
                                ]
                            })
                        }
                    }
                }
            }
        })
        .state("index.baseInfo", {
            url: '/baseInfo.html/:params',
            views:{
                'home@':{
                    templateUrl: '/taxation-web/views/businessregister/baseInfo.html',
                    controller: 'BaseInfoController',
                    resolve:{
                        loadMyCtrl:function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                name:'taxationApp',
                                files:[
                                    "/taxation-web/css/pagecss/business.css"
                                ]
                            })
                        }
                    }
                }
            }
        })
        .state("index.productionSite", {
            url: '/productionSite.html/:params',
            views:{
                'home@':{
                    templateUrl: '/taxation-web/views/businessregister/productionSite.html',
                    controller: 'ProductionSiteController',
                    resolve:{
                        loadMyCtrl:function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                name:'taxationApp',
                                files:[
                                    "/taxation-web/css/pagecss/business.css"
                                ]
                            })
                        }
                    }
                }
            }
        })
        .state("index.addressAndPay", {
            url: '/addressAndPay.html/:params',
            views:{
                'home@':{
                    templateUrl: '/taxation-web/views/businessregister/addressAndPay.html',
                    controller: 'AddressAndPayController',
                    resolve:{
                        loadMyCtrl:function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                name:'taxationApp',
                                files:[
                                    "/taxation-web/css/pagecss/business.css"
                                ]
                            })
                        }
                    }
                }
            }
        })
        .state("index.enclosure", {
            url: '/enclosure.html/:params',
            views:{
                'home@':{
                    templateUrl: '/taxation-web/views/businessregister/enclosure.html',
                    controller: 'EnclosureController',
                    resolve:{
                        loadMyCtrl:function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                name:'taxationApp',
                                files:[
                                    "/taxation-web/css/pagecss/business.css"
                                ]
                            })
                        }
                    }
                }
            }
        })
        .state("index.ensure", {
            url: '/ensure.html/:params',
            views:{
                'home@':{
                    templateUrl: '/taxation-web/views/businessregister/ensure.html',
                    controller: 'EnsureController',
                    resolve:{
                        loadMyCtrl:function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                name:'taxationApp',
                                files:[
                                    "/taxation-web/css/pagecss/business.css"
                                ]
                            })
                        }
                    }
                }
            }
        })
        .state("index.businessStatus", {
            url: '/businessStatus.html',
            views:{
                'home@':{
                    templateUrl: '/taxation-web/views/businessregister/businessStatus.html',
                    resolve:{
                        loadMyCtrl:function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                name:'taxationApp',
                                files:[
                                    "/taxation-web/css/pagecss/business.css"
                                ]
                            })
                        }
                    }
                }
            }
        })
        .state("index.taxservice", {
            url: '/taxationService.html',
            views:{
                'home@':{
                    templateUrl: '/taxation-web/views/taxationservice/taxationService.html',
                    controller: 'TaxserviceController',
                    resolve: {
                        deps: ['$ocLazyLoad', function($ocLazyLoad) {
                            return $ocLazyLoad.load([{
                                name: 'taxationApp',
                                files: [
                                    '/taxation-web/css/pagecss/provider_home.css'
                                ]
                            }]);
                        }]
                    }
                }
            }
        })
        .state("index.serviceDetail", {
            url: '/provider_goods_all.html/:params',
            views:{
                'home@':{
                    templateUrl: '/taxation-web/views/taxationservice/provider_goods_all.html',
                    controller: 'ServiceDetailController',
                    resolve: {
                        deps: ['$ocLazyLoad', function($ocLazyLoad) {
                            return $ocLazyLoad.load([{
                                name: 'taxationApp',
                                files: [
                                    '/taxation-web/css/pagecss/provider_goods_all.css'
                                ]
                            }]);
                        }]
                    }
                }
            }
        })
        .state("index.serviceInfo", {
            url: '/service.html/:params',
            views:{
                'home@':{
                    templateUrl: '/taxation-web/views/taxationservice/service.html',
                    controller: 'ServiceInfoController',
                    resolve: {
                        deps: ['$ocLazyLoad', function($ocLazyLoad) {
                            return $ocLazyLoad.load([{
                                name: 'taxationApp',
                                files: [
                                    '/taxation-web/css/pagecss/service.css'
                                ]
                            }]);
                        }]
                    }
                }
            }
        })
        .state("index.buyNow", {
            url: '/buy_step2.html/:params',
            views:{
                'home@':{
                    templateUrl: '/taxation-web/views/taxationservice/buy_step2.html',
                    controller: 'BuyNowController',
                    resolve: {
                        deps: ['$ocLazyLoad', function($ocLazyLoad) {
                            return $ocLazyLoad.load([{
                                name: 'taxationApp',
                                files: [
                                    '/taxation-web/css/pagecss/buy_step.css'
                                ]
                            }]);
                        }]
                    }
                }
            }
        })
        .state("index.paying", {
            url: '/buy_step3.html/:params',
            views:{
                'home@':{
                    templateUrl: '/taxation-web/views/taxationservice/buy_step3.html',
                    controller: 'PayingController',
                    resolve: {
                        deps: ['$ocLazyLoad', function($ocLazyLoad) {
                            return $ocLazyLoad.load([{
                                name: 'taxationApp',
                                files: [
                                    '/taxation-web/css/pagecss/buy_step.css'
                                ]
                            }]);
                        }]
                    }
                }
            }
        })
        .state("index.userregstep1", {
            url: '/reg_step1.html',
            views:{
                'home@':{
                    templateUrl: '/taxation-web/views/home/reg_step1.html',
                    controller: 'UserRegisterController',
                     resolve: {
		                deps: ['$ocLazyLoad', function($ocLazyLoad) {
		                    return $ocLazyLoad.load([{
		                        name: 'taxationApp',
		                        files: [
		                         	'/taxation-web/css/pagecss/reg.css'
		                        ]
		                    }]);
		                }]
		            }
                }
            }
        })
        .state("index.userregstep2", {
            url: '/reg_step2.html/:userPhone',
            views:{
                'home@':{
                    templateUrl: '/taxation-web/views/home/reg_step2.html',
                    controller: 'UserRegisterController2',
                     resolve: {
		                deps: ['$ocLazyLoad', function($ocLazyLoad) {
		                    return $ocLazyLoad.load([{
		                        name: 'taxationApp',
		                        files: [
		                         	'/taxation-web/css/pagecss/reg.css'
		                        ]
		                    }]);
		                }]
		            }
                }
            }
        })
        .state("index.userregstep4", {
            url: '/reg_step4.html/:regResult',
            views:{
                'home@':{
                    templateUrl: '/taxation-web/views/home/reg_step4.html',
                    controller: 'UserRegisterController4',
                     resolve: {
		                deps: ['$ocLazyLoad', function($ocLazyLoad) {
		                    return $ocLazyLoad.load([{
		                        name: 'taxationApp',
		                        files: [
		                         	'/taxation-web/css/pagecss/reg.css'
		                        ]
		                    }]);
		                }]
		            }
                }
            }
        })
        .state("index.ucenter", {
            url: '/ucenter.html',
            views:{
                'home@':{
                    templateUrl: '/taxation-web/views/member/ucenter.html',
                    controller: 'UcenterController',
                     resolve: {
		                deps: ['$ocLazyLoad', function($ocLazyLoad) {
		                    return $ocLazyLoad.load([{
		                        name: 'taxationApp',
		                        files: [
		                         	'/taxation-web/css/pagecss/member.css',
		                         	'/taxation-web/css/pagecss/member/userinfo.css'
		                        ]
		                    }]);
		                }]
		            }
                }
            }
        })
       .state("index.pay", {
            url: '/pay.html',
            views:{
                'home@':{
                    templateUrl: '/taxation-web/views/home/pay.html',
                    controller: 'UserPayController'
                }
            }
        })
       .state("index.payBlankPage", {
            url: '/payBlankPage.html',
            views:{
                'home@':{
                    templateUrl: '/taxation-web/views/home/payBlankPage.html',
                    controller: 'UserPayBlankPageController'
                }
            }
        })
}]);
//路由事件
taxationApp.run(function($rootScope,$state,toastr){
    $rootScope.$on('$stateChangeStart',function(event, toState, toParams, fromState, fromParams){
        //alert(toState.name);
        if(toState.name=='index.login'||toState.name=='index.userregstep1' ||toState.name=='index.userregstep2' ||toState.name=='index.ucenter' ||toState.name=='index.userregstep4' || toState.name=='index.busregister' || toState.name == 'index.taxservice' || toState.name == 'index.pay' || toState.name == 'index.payBlankPage'){
            return;// 如果是进入登录界面则允许
        }

        //alert(sessionStorage.getItem("user"));
        // 如果用户不存在并且不是访问首页
        if(sessionStorage.getItem("user")== null&&toState.name!='index'){
            event.preventDefault();// 取消默认跳转行为
            toastr.info('请先登录',{timeOut:2000});
            setTimeout(function(){
                $state.go("index.login",{from:fromState.name,w:'notLogin'},{reload:true});//跳转到登录界面
            },2000);

        }
    });
});
taxationApp .value('operateSiteNatureArr',[{"code":"1","name":"商业租用"},{"code":"2","name":"商业自有"},{"code":"3","name":"住房租用"},{"code":"4","name":"住房自有"}]);
taxationApp .value('produceOperateTypeArr',[{"code":"1","name":"餐饮业"},{"code":"2","name":"商业"},{"code":"3","name":"服务业"},{"code":"4","name":"娱乐业"},{"code":"0","name":"其他"}]);
taxationApp .value('userSexArr',[{"code":"0","name":"男"},{"code":"1","name":"女"}]);