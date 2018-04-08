myApp.factory('admin_service',['$http', '$q', function ($http,$q) {
        var service={};
         service.querySysTemUserList = function(pageIndex,pageSize,totalCount) {
        	return $http.post("/taxation-manager/systemUserCon/querySystemUser.htm",{pageIndex:pageIndex,pageSize:pageSize,totalCount:totalCount}).then(function (response) {
           	 		return response.data;
       	 		});
    	}
        return service;
}]);