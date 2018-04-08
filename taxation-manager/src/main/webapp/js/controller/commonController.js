/**
 * @desc: 公共头部控制器文件
 * @time: 2017-06-29 10::57
 * @author: JustFresh
 * @version: v1.0.0
 */

myApp.factory('common_data_service',['$http', '$q', function ($http,$q) {	
	var service={};
    service.getModuleList = function(){
        return $http.post("/taxation-manager/json/module.json",{}).then(function (response) { 
            return response.data;
        });
    };
    service.getAdminList = function(){
        return $http.post("/taxation-manager/json/admin.json",{}).then(function (response) { 
            return response.data;
        });
    };
    service.getCmsList = function(){
        return $http.post("/taxation-manager/json/cms.json",{}).then(function (response) { 
            return response.data;
        });
    };
    return service;
	
}]);

myApp.controller("ModuleController",function($http,$log,$rootScope,$scope,common_data_service,$location){
	
	$scope.moduleList = {};
    $scope.getModuleList = common_data_service.getModuleList().then(function (data) {
        if(data.success){
            $scope.moduleList = data.obj;
        }
    });
	
});

myApp.controller("LeftNavController",function($http,$log,$rootScope,$scope,common_data_service,$location){
	
	$scope.adminList = {};
    $scope.cmsList = {};
    $scope.getAdminList = common_data_service.getAdminList().then(function (data) {
        if(data.success){
            $scope.adminList = data.obj;
        }
    });
    $scope.getCmsList = common_data_service.getCmsList().then(function (data) {
        if(data.success){
            $scope.cmsList = data.obj;
        }
    });
	
});

myApp.controller("HomeController",function($http,$log,$rootScope,$scope,common_data_service,$location){
	$rootScope.activeMenu = translateUrl($location.path());
});