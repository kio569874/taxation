/**
 * @description：支付配置的Controller文件
 * @author：JustFresh
 * @time：2017-07-27 15:52
 */
myApp.controller('SmsConfigController',function ($http,$rootScope,$scope,smsConfigService,$location,$state) {

	$rootScope.activeMenu = translateUrl($location.path());
	
});