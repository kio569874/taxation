taxationApp.factory('order_service',['$http', '$q', function ($http,$q) {
        var service={};
        service.queryOrderListByUserId = function(userId) {
            return $http.post("/taxation-web/order/queryOrderListByUserId.htm",{userId:userId}).then(function (response) {
                    return response.data;
                });
        }
        service.testPay = function(orderNo,orderAmount,orderSubject) {
            return $http.post("/taxation-web/pay/orderAliPay.htm",{orderNo:orderNo,orderAmount:orderAmount,orderSubject:orderSubject}).then(function (response) {
                return response.data;
            });
        }
        return service;
}]);