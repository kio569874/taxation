taxationApp.factory('companyService_service',['$http', function ($http) {
    var service={};
    service.queryProvider = function(pageIndex,pageSize,totalCount) {
        return $http.post("/taxation-web/provider/queryProvider.htm",{pageIndex:pageIndex,pageSize:pageSize,totalCount:totalCount}).then(function (response) {
            return response.data;
        });
    }
    service.saveCompanyServiceInfo = function(params) {
        return $http.post("/taxation-web/enterpriseEnroll/saveEnterpriseEnrollInfo.htm",{"params":params}).then(function (response) {
            return response.data;
        });
    }
    return service;
}]);