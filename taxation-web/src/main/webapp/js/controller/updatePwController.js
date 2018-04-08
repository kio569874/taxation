var updatePwApp =  angular.module("updatePwApp", ['toastr']);
updatePwApp.config(function(toastrConfig) {
    angular.extend(toastrConfig, {
        autoDismiss: false,
        timeOut:2000,
        containerId: 'toast-container',
        maxOpened: 0,
        newestOnTop: true,
        positionClass: 'toast-top-center',
        preventDuplicates: false,
        preventOpenDuplicates: false,
        target: 'body'
    });
});
updatePwApp.controller('UpdatePwController',function($scope,$location,updatepw_service,toastr) {
    var user = {};
    $scope.user = user;
    $scope.updatePw = function () {
        updatepw_service.updatePw(user.userAccount,user.userPhone).then(function (data) {
            if (data.retcode == 'true') {
                $scope.result =  data.message;
            } else {
                $scope.result =  data.message;
            }
        });
    }

    $scope.checkUserAccount = function () {
        updatepw_service.checkUserAccount(user.userAccount).then(function (data) {
            if (data.retcode == 'true') {
                $scope.result =  data.message;
            } else {
                $scope.result =  data.message;
            }
        });
    }

    $scope.checkPhone = function () {
        updatepw_service.checkUserAccount(user.userAccount,user.userPhone).then(function (data) {
            if (data.retcode == 'true') {
                $scope.result =  data.message;
            } else {
                $scope.result =  data.message;
            }
        });
    }

    $scope.getSmsCheckCode = function () {
        $scope.checksendsec = 60
        $scope.checksend = true;
        var timer = $interval(function(){
            if($scope.checksendsec<1){
                $scope.checksend=false;
                $interval.cancel(timer);
                $scope.checksendsec = 60;
            }else{
                $scope.checksendsec=$scope.checksendsec-1
            }
        },1000)
        updatepw_service.getSmsCheckCode(user.userPhone).then(function (data) {
            if (data.sendReturncode == '0') {
                toastr.success('发送成功',{timeOut:2000});
            }else{
                toastr.error('发送失败',{timeOut:2000});
            }
        });
    }

});