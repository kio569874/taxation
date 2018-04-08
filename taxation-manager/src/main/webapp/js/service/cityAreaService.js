/**
 *
 */
myApp.factory('cityAreaService',['$http', '$q', function ($http,$q) {

	var service={};
	
	service.getPaging = function(paginationConf) {
    	return $http.post("/taxation-manager/cityArea/page.htm",{cityArea:paginationConf}).then(function (response) {
   	 		return response.data;
 		});
	}
	
	service.addCityArea = function(cityArea){
		return $http.post("/taxation-manager/cityArea/addCityArea.htm",{cityArea:cityArea}).then(function (response) {
   	 		return response.data;
 		});
	};
	
	service.editCityArea = function(cityArea){
		return $http.post("/taxation-manager/cityArea/editCityArea.htm",{cityArea:cityArea}).then(function (response) {
   	 		return response.data;
 		});
	};
	
	service.remove = function(pid){
		var cityArea = {};
		cityArea.pid = pid;
		//执行删除
    	return $http.post("/taxation-manager/cityArea/removeCityArea.htm",{cityArea:cityArea}).then(function (response) {
   	 		return response.data;
 		});
	};
	
	service.batchRemove = function(ids){
		//执行删除
    	return $http.post("/taxation-manager/cityArea/batchRemove.htm",{cityAreaPids:ids}).then(function (response) {
   	 		return response.data;
 		});
	};
	
	service.getOne = function(param){
		return $http.post("/taxation-manager/cityArea/getOne.htm",{cityArea:param}).then(function (response) {
   	 		return response.data;
 		});
	};
	
	service.getList = function(param){
		return $http.post("/taxation-manager/cityArea/getList.htm",{cityArea:param}).then(function (response) {
   	 		return response.data;
 		});
	};
	
	service.showPage = function(order,sort,page,rows){
		var cityArea = {};
		cityArea.order = order;
		cityArea.sort = sort;
		cityArea.page = page;
		cityArea.rows = rows;
		return $http.post("/taxation-manager/cityArea/page.htm",{cityArea:cityArea}).then(function (response) {
   	 		return response.data;
 		});
	};

	return service;
        
}]);