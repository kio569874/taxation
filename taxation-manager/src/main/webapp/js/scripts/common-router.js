/**
 * @desc:公共路由文件
 * @author:JustFresh
 * @time:2017-07-01 09:25
 */

myApp.config(['$stateProvider','$httpProvider','$urlRouterProvider',function($stateProvider,$httpProvider,$urlRouterProvider){

	//重定向不匹配的URL
	$urlRouterProvider.otherwise("/index.html");

	$stateProvider.state('index', {
        url:'/index.html',
        cache:false,
        views:{
            //这里必须要绝对定位
            'header@':{
                templateUrl: '/taxation-manager/views/common/header.html',
                controller: 'ModuleController'
            },"left_nav@": {
            	templateUrl: "/taxation-manager/views/common/left_nav.html",
                controller: "LeftNavController"
            },"home@": {
            	templateUrl: "/taxation-manager/views/home.html",
            	controller: "HomeController"
            }
        }
    }).state('index.system_user', {
        url:'/system_user.html',
        cache:false,
        views:{
        	//这里必须要绝对定位
			"home@": {
            	templateUrl: '/taxation-manager/views/system_user.html',
            	controller: "SystemUserController"
            }
        }
    }).state('index.system_user_mng', {
        url:'/system_user_mng.html?id',
        cache:false,
        views:{
			"home@": {
				params: {"id":null},
            	templateUrl: '/taxation-manager/views/system_user_mng.html',
            	controller: "SystemUserMngController"
            }
        }
    }).state('index.pay_config', {
        url:'/pay_config.html',
        cache:false,
        views:{
        	//这里必须要绝对定位
			"home@": {
            	templateUrl: '/taxation-manager/views/pay_config.html',
            	controller: "PayConfigController"
            }
        }
    }).state('index.sms_config', {
        url:'/sms_config.html',
        cache:false,
        views:{
        	//这里必须要绝对定位
			"home@": {
            	templateUrl: '/taxation-manager/views/sms_config.html',
            	controller: "SmsConfigController"
            }
        }
    }).state('index.city_area', {
        url:'/city_area.html',
        cache:false,
        views:{
        	//这里必须要绝对定位
			"home@": {
            	templateUrl: '/taxation-manager/views/city_area.html',
            	controller: "CityAreaController"
            }
        }
    }).state('index.city_area_mng', {
        url:'/city_area_mng.html?pid',
        cache:false,
        views:{
			"home@": {
				params: {"pid":null},
            	templateUrl: '/taxation-manager/views/city_area_mng.html',
            	controller: "CityAreaMngController"
            }
        }
    });

}]);