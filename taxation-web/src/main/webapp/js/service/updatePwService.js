taxationApp.factory('updatepw_service',['$http', '$q', function ($http,$q) {
    var service={};
    service.checkUserAccount = function(userAccount) {
        return $http.post("/taxation-web/user/checkAccount.htm",{userAccount:userAccount,checkCode:"USER_UPDATE_PWD"}).then(function (response) {
                return response.data;
            });
    }
    service.checkPhone = function(userAccount,userPhone) {
        return $http.post("/taxation-web/user/checkPhone.htm",{userAccount:userAccount,userPhone:userPhone}).then(function (response) {
            return response.data;
        });
    }
    service.getSmsCheckCode = function(userPhone) {
        return $http.post("/taxation-web/sms/sendSmsCheckCode.htm",{userPhone:userPhone,checkCode:"USER_UPDATE_PWD"}).then(function (response) {
                return response.data;
            });
    }
    service.updatePw = function(user) {
        return $http.post("/taxation-web/user/updatePassWord.htm",{userAccount:userAccount,userPhone:userPhone}).then(function (response) {
            return response.data;
        });
    }
        return service;
}]);