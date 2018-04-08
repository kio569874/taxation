taxationApp.factory('taxationService_service',['$http', function ($http) {
    var service={};
    service.queryServiceProvider = function(pageIndex,pageSize) {
        return $http.post("/taxation-web/serviceInfo/queryServiceProvider.htm",{pageIndex:pageIndex,pageSize:pageSize}).then(function (response) {
            return response.data;
        });
    }
    service.queryServiceByProviderId = function(providerId) {
        return $http.post("/taxation-web/serviceInfo/queryServiceByProviderId.htm",{providerId:providerId}).then(function (response) {
            return response.data;
        });
    }
    service.queryServiceInfo = function(serviceId) {
        return $http.post("/taxation-web/serviceInfo/getServiceInfo.htm",{serviceId:serviceId}).then(function (response) {
            return response.data;
        });
    }
    service.queryProviderInfo = function(providerId) {
        return $http.post("/taxation-web/provider/queryProviderInfo.htm",{providerId:providerId}).then(function (response) {
            return response.data;
        });
    }
    service.addToCart = function(userId,serviceId) {
        return $http.post("/taxation-web/shoppingTrolley/addToCart.htm",{userId:userId,serviceId:serviceId}).then(function (response) {
            return response.data;
        });
    }
    service.queryUserAddress = function(userId) {
        return $http.post("/taxation-web/serviceInfo/queryUserAddress.htm",{userId:userId}).then(function (response) {
            return response.data;
        });
    }
    service.queryCityArea = function() {
        return $http.post("/taxation-web/serviceInfo/queryCityArea.htm").then(function (response) {
            return response.data;
        });
    }
    service.addNewAddress = function(newAddress) {
        return $http.post("/taxation-web/serviceInfo/addNewAddress.htm",{newAddress:newAddress}).then(function (response) {
            return response.data;
        });
    }
    service.queryAddressById = function(addressId) {
        return $http.post("/taxation-web/serviceInfo/queryAddressById.htm",{addressId:addressId}).then(function (response) {
            return response.data;
        });
    }
    service.updateOldAddress = function(updateAddress) {
        return $http.post("/taxation-web/serviceInfo/updateOldAddress.htm",{updateAddress:updateAddress}).then(function (response) {
            return response.data;
        });
    }
    service.buyServiceNow = function(orderParam) {
        return $http.post("/taxation-web/serviceInfo/buyServiceNow.htm",{orderParam:orderParam}).then(function (response) {
            return response.data;
        });
    }
    service.updatePayType = function(orderId,payType) {
        return $http.post("/taxation-web/serviceInfo/updatePayType.htm",{orderId:orderId,payType:payType}).then(function (response) {
            return response.data;
        });
    }
    return service;
}]);