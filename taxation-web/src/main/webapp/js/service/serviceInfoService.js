ServiceInfoApp.factory('serviceInfo_service',['$http', function ($http) {
    var service={};
    service.query = function(searchForm) {
        var	res = $http.post("/taxation-web/serviceInfo/serviceInfo.htm",{serviceType: '1'}).then(function (response) {
                return response.data;
            });
        return res;
    }
    return service;
}]);