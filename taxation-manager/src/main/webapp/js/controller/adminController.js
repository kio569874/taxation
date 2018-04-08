myApp.controller("AdminController",function($rootScope,$scope,$state,admin_service){
	var list = {};
	$scope.list = list ;
 	$scope.verifyCodeUrl = '/taxation-manager/checkCode/verifyCode.htm';
    $scope.changeImg = function () {
		$scope.verifyCodeUrl = '/taxation-manager/checkCode/verifyCode.htm?date=' + new Date();
  	}
  	
    $scope.querySysTemUserList = function () {
		admin_service.querySysTemUserList(1,10,0).then(function (data) {
            if (data.retcode == 'true') {
            	alert(data.message);
               $scope.list = data.message;
            } else {
            	
            }
        });
  	}
});