taxationApp.factory('user_service',['$http', '$q', function ($http,$q) {
        var service={};
        service.register = function(user) {
            return $http.post("/taxation-web/user/register.htm",{user:user}).then(function (response) {
                    return response.data;
                });
        }
        service.checkUserAccount = function(userPhone) {
            return $http.post("/taxation-web/user/checkAccount.htm",{userAccount:userPhone,checkCode:"USER_REGISTER"}).then(function (response) {
                    return response.data;
                });
        }
        service.getSmsCheckCode = function(userPhone) {
            return $http.post("/taxation-web/sms/sendSmsCheckCode.htm",{userPhone:userPhone,checkCode:"USER_REGISTER"}).then(function (response) {
                    return response.data;
                });
        }
        service.toLogin = function(user) {
        	return $http.post("/taxation-web/user/userLogin.htm",{userAccount:user.userAccount,userPassword:user.userPassword,verifyCode:user.verifyCode}).then(function (response) {
           	 		return response.data;
       	 		});
    	}
        service.queryUserInfo = function(userAccount) {
        	return $http.post("/taxation-web/user/queryUserInfo.htm",{userAccount:userAccount}).then(function (response) {
           	 		return response.data;
       	 		});
    	}
        service.checkFindPwUserAccount = function(userPhone) {
            return $http.post("/taxation-web/user/checkAccount.htm",{userAccount:userPhone,checkCode:"USER_UPDATE_PWD"}).then(function (response) {
                return response.data;
            });
        }
        service.updatePw = function(userAccount,userPassword) {
            return $http.post("/taxation-web/user/updatePassWord.htm",{userAccount:userAccount,userPassword:userPassword}).then(function (response) {
                return response.data;
            });
        }
        service.securityUserInfo = function(user) {
            return $http.post("/taxation-web/user/securityUserInfo.htm",{userAccount:user.userAccount}).then(function (response) {
                return response.data;
            });
        }
        service.bindEmail = function(userAccount,email) {
            return $http.post("/taxation-web/user/bindUserEmail.htm",{userAccount:userAccount,userEmail:email}).then(function (response) {
                return response.data;
            });
        }
        service.updateUserBaseInfo = function(user) {
            return $http.post("/taxation-web/user/updateUserInfo.htm",{user:user}).then(function (response) {
                return response.data;
            });
        }
        return service;
}]);