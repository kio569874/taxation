/**
 *
 */
myApp.factory('systemUserService',['$http', '$q', function ($http,$q) {

    var service={};
    
    service.querySysTemUserList = function(pageIndex,pageSize,totalCount) {
    	return $http.post("/taxation-manager/systemUserCon/querySystemUser.htm",{pageIndex:pageIndex,pageSize:pageSize,totalCount:totalCount}).then(function (response) {
       	 		return response.data;
   	 		});
	}
	
	service.getOneSystemUser = function(params){
		console.info(params.id);
		return $http.post("/taxation-manager/systemUserCon/getOne.htm",{id:params.id}).then(function (response) {
   	 		return response.data;
 		});
	};
	
	service.addSystemUser = function(systemUser){
		return $http.post("/taxation-manager/systemUserCon/saveSystemUser.htm",{systemUser:systemUser}).then(function (response) {
   	 		return response.data;
 		});
	};
	
	service.editSystemUser = function(systemUser){
		return $http.post("/taxation-manager/systemUserCon/updateSystemUser.htm",{systemUser:systemUser}).then(function (response) {
   	 		return response.data;
 		});
	};
	
	service.remove = function(id){
		//执行删除
    	return $http.post("/taxation-manager/systemUserCon/batchRemove.htm",{systemUserIds:id}).then(function (response) {
   	 		return response.data;
 		});
	};
	
	service.changeStatus = function(id,status){
		return $http.post("/taxation-manager/systemUserCon/changeStatus.htm",{id:id,userStatus:status}).then(function (response) {
   	 		return response.data;
 		});
	};
	
    return service;
        
}]);