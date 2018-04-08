taxationApp.controller('UserRegisterController',function ($rootScope,$scope,$state,$interval,toastr,user_service,order_service) {
	    var user = {};
	    var agree = 'true';
	    $scope.agree = agree;
	    var smsCheckCodeEntity = {};
	    $scope.user = user;
        $scope.verifyCodeUrl = '/taxation-web/user/verifyCode.htm';
        $scope.checkUserAccount = function () {
			var reg = /^1[3|4|5|7|8][0-9]{9}$/;
		    var flag = reg.test(user.userPhone);
		    if(!flag){
		    	 $scope.pass = '3';
		    	 $scope.result = '手机号码错误';
		    	 return;
		    }
            user_service.checkUserAccount(user.userPhone).then(function (data) {
                if (data.retcode == 'true') {
                    $scope.pass = '2';
                } else {
                	$scope.pass = '1';
                }
            });
        }
        
        $scope.gotoLogin = function () {
        	$state.go("index.login",{token:123});
        }
        
        $scope.getSmsCheckCode = function () {
        	var reg = /^1[3|4|5|7|8][0-9]{9}$/;
		    var flag = reg.test(user.userPhone);
		    if(!flag){
		    	toastr.error('手机号码错误', {timeOut:2000});
		    	//alert('手机号码错误');
		    	 $scope.pass = '3';
		    	 return;
		    }
         	$scope.checksendsec = 60
            $scope.checksend = true;
            user_service.getSmsCheckCode(user.userPhone).then(function (data) {
             if (data.sendReturncode == '0') {
             	smsCheckCodeEntity = data;
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
             	toastr.success('发送成功',{timeOut:2000});
             }else{
            	 $scope.checksend = false;
            	 toastr.error('发送失败', {timeOut:2000});
             }
            });
        }
        
        $scope.changeImg = function () {
        	$scope.verifyCodeUrl = '/taxation-web/user/verifyCode.htm?date=' + new Date();
  	  	}
  	  	
  	  	$scope.checkPhoneCheckCode = function () {
         	if($scope.user.phoneCheckCode!=null && $scope.user.phoneCheckCode == smsCheckCodeEntity.smsCheckCode){
         		 $scope.checkResult = 'true';
         	}else{
         		$scope.checkResult = 'false';
         	}
        }
  	  	$scope.nextStp2 = function () {
  	  		if($scope.pass != '2'){
  	  			toastr.error('请输入正确的手机号', {timeOut:2000});
  	  			return;
  	  		}
  	  		if($scope.checkResult != 'true'){
  	  			toastr.error('请输入正确的验证码', {timeOut:2000});
  	  			return;
  	  		}
  	  		if(!$scope.agree || 'false' == $scope.agree){
  	  			toastr.error('请勾选同意用户注册协议', {timeOut:2000});
  	  			return;
  	  		}
  	  		$state.go("index.userregstep2",{userPhone:user.userPhone});
        }
    });
	taxationApp.controller('UserRegisterController2',function ($rootScope,$scope,$state,$stateParams,$interval,toastr,user_service) {
		var userPhone = $stateParams.userPhone;
		var user = {};
     	$scope.user = user;
     	$scope.user.userPhone = userPhone;
     	
        $scope.checkPassword1 = function () {
	        if(user.userPassword.length <6){
	        	$scope.passwordResult1 ='密码太短';
	        }else if(user.userPassword.length >30){
	        	$scope.passwordResult1 ='密码太长';
	        }else{
	        	$scope.passwordResult1 ='true';
	        }
        }
        $scope.checkPassword2 = function () {
         	if(user.userPassword != user.userPassword1){
         		 $scope.passwordResult2 ='两次密码输入不一致';
         	}else{
         		 $scope.passwordResult2 ='true';
         	}
        }
        $scope.checkAccountName = function () {
       		if(user.userMemberName.length >30){
	        	$scope.checkAccountNameResult ='会员名长度不能大于30';
	        }else{
	        	$scope.checkAccountNameResult ='true';
	        }
        }
        
        $scope.nextStp3 = function () {
        	if($scope.passwordResult1 != 'true'){
  	  			toastr.error('请输入正确的密码', {timeOut:2000});
  	  			return;
  	  		}
  	  		if($scope.passwordResult2 != 'true'){
  	  			toastr.error('请确认密码', {timeOut:2000});
  	  			return;
  	  		}
  	  		
  	  		if($scope.passwordResult1 != 'true'){
  	  			toastr.error('请设置正确的会员名', {timeOut:2000});
  	  			return;
  	  		}
       	 	user.userAccount = user.userPhone;
         	user_service.register(user).then(function (data) {
                if (data.retcode == 'true') {
               		sessionStorage.setItem("user",JSON.stringify(user));
                	$state.go("index.userregstep4",{regResult:'注册成功'});
                } else {
                	$state.go("index.userregstep4",{regResult:'注册失败'});             
                }
            });
        }
        
    });
	taxationApp.controller('UserRegisterController4',function ($rootScope,$scope,$state,$stateParams,$interval,user_service) {
		var regResult = $stateParams.regResult;
		$scope.regResult = regResult;
       	 $scope.gotoIndex = function () {
       	 	$state.go("index",{},{reload:true});
        }
    });
    
 taxationApp.controller('UserLoginController',function($scope,user_service,$stateParams,toastr,$state) {
    var user = {};
    $scope.user = user;
    $scope.verifyCodeUrl = "/taxation-web/user/verifyCode.htm";
    $scope.changeImg = function(){
        $scope.verifyCodeUrl = "/taxation-web/user/verifyCode.htm?date=" + new Date();
    }
    $scope.submitted = false;
    $scope.userLogin = function () {
        if($scope.loginForm.$valid){
            //正常提交表单
            user_service.toLogin(user).then(function (data) {
                if(data.retcode == '1'){
                    user.userId = (angular.fromJson(data.data)).userId;
                    sessionStorage.setItem("user",data.data);//data.data是返回的user的json字符串
                    //toastr.success('正在登录中...',{timeOut:1000,progressBar:true});
                    var from = $stateParams["from"];
                    $state.go(from && from != "index.login" ? from : "index",{reload:true});
                }else if(data.retcode == '0'){
                    toastr.warning(data.message,{timeOut:2000});
                    $scope.verifyCodeUrl = "/taxation-web/user/verifyCode.htm?date=" + new Date();
                }else{
                    toastr.error(data.message, {timeOut:2000});
                }
            });
        }else{
            $scope.submitted = true;
        }

    }
});




taxationApp.controller('UserBaseInfoController',function($scope,user_service,toastr) {
  	var user = {};
  	user = angular.fromJson(sessionStorage.user);
  	$scope.user = user;
	user_service.queryUserInfo(user.userAccount).then(function (data) {
    	if (data.status !=null && data.status == 'error') {
     		//alert("查询用户信息失败");
     	}else{
    		$scope.user = data;
     	}
    });
     $scope.updateUserBaseInfo = function (user) {
	     user_service.updateUserBaseInfo(user).then(function (data) {
	    	if (data.status !=null && data.status == 'error') {
	     		alert("修改失败");
	     	}else{
	    		$scope.user = data;
	    		sessionStorage.threeFlag = JSON.stringify('true');
				sessionStorage.threeUrl = JSON.stringify('baseinfo.html');
				sessionStorage.threeName = JSON.stringify('基本信息');
				window.location = "/taxation/view/jxshop/home/member/baseinfo.html";
	     	}
	    });
     }
     
});


taxationApp.controller('FindPwController1',function ($rootScope,$scope,$state,$interval,user_service,toastr) {
    var user = {};
    var smsCheckCodeEntity = {};
    $scope.user = user;

    $scope.getSmsCheckCode = function () {
        var reg = /^1[3|4|5|7|8][0-9]{9}$/;
        var flag = reg.test(user.userPhone);
        if(!flag){
            alert('手机号码错误');
            $scope.pass = '3';
            return;
        }
        $scope.checksendsec = 60
        $scope.checksend = true;
        user_service.getSmsCheckCode(user.userPhone).then(function (data) {
            if (data.sendReturncode == '0') {
                smsCheckCodeEntity = data;
                $scope.checksend = true;
                var sendTimer = $interval(function(){
                    if($scope.checksendsec<1){
                        $scope.checksend=false;
                        $interval.cancel(sendTimer);
                        $scope.checksendsec = 60;
                    }else{
                        $scope.checksendsec=$scope.checksendsec-1
                    }
                },1000);

                toastr.success('短信发送成功', {timeOut: 2000});
                sessionStorage.removeItem("smsCheckCode");
                sessionStorage.setItem("smsCheckCode",data.smsCheckCode);
                var timeSession = 15*60;
                var checkTimer = $interval(function () {
                    if (timeSession < 1) {
                        $interval.cancel(checkTimer);
                        timeSession= 15*60;
                        sessionStorage.removeItem("smsCheckCode");
                    } else {
                        timeSession = timeSession - 1
                    }
                }, 1000);
            }else if (data.sendReturncode == '33'||data.sendReturncode =='22'){
                $scope.checksend = false;
                toastr.warning(data.sendReturnmessage, {timeOut: 2000});
            }else{
                $scope.checksend = false;
                toastr.error('发送失败', {timeOut: 2000});
            }
        });
    }

    $scope.checkFindPwUserAccount = function () {
        var reg = /^1[3|4|5|7|8][0-9]{9}$/;
        var flag = reg.test(user.userPhone);
        if(!flag){
            $scope.pass = '3';
            $scope.result = '手机号码错误';
            return;
        }
        user_service.checkFindPwUserAccount(user.userPhone).then(function (data) {
            if (data.retcode == 'true') {
                $scope.pass = '2';
            } else {
                $scope.pass = '1';
            }
        });
    }

    $scope.gotoRegister = function () {
        window.location = "/taxation/view/jxshop/home/login.html";
    }

    $scope.checkPhoneCheckCode = function () {
        //sessionStorage.setItem("smsCheckCode","111111");
        if($scope.user.phoneCheckCode!=null && $scope.user.phoneCheckCode == sessionStorage.getItem("smsCheckCode")){
            $scope.checkResult = 'true';
        }else{
            $scope.checkResult = 'false';
        }
    }
    $scope.nextStp2 = function () {
        sessionStorage.user = JSON.stringify(user);
        window.location = "/taxation/view/jxshop/home/findpw_step2.html";
    }
});

taxationApp.controller('FindPwController2',function ($rootScope,$scope,$state,$interval,user_service) {
    var user = {};
    user = angular.fromJson(sessionStorage.user);
    $scope.user = user;

    $scope.checkPassword1 = function () {
        if (user.userPassword.length>=6&&user.userPassword.length<=12){
            $scope.passwordResult1 ='密码可用';
        }else{
            $scope.passwordResult1 ='密码长度应在6-12位之间';
        }

    }
    $scope.checkPassword2 = function () {
        if(user.userPassword != user.userPassword1){
            $scope.passwordResult2 ='两次输入的密码不相同,请重新输入';
        }else{
            $scope.passwordResult2 ='密码验证通过';
            $scope.checkPwResult = 'true';
        }
    }
    $scope.nextStp3 = function () {
        user.userAccount = user.userPhone;
        user_service.updatePw(user.userAccount,user.userPassword).then(function (data) {
            if (data.retcode == 'true') {
                sessionStorage.data = JSON.stringify(data);
                sessionStorage.user = JSON.stringify(user);
                window.location = "/taxation/view/jxshop/home/findpw_step3.html";
            } else {
                sessionStorage.user = JSON.stringify(user);
                window.location = "/taxation/view/jxshop/home/findpw_step3.html";
            }
        });
    }

});

taxationApp.controller('FindPwController3',function ($rootScope,$scope,$state,$interval,user_service) {
    var user = {};
    var data = {};
    user = angular.fromJson(sessionStorage.user);
    data = angular.fromJson(sessionStorage.data);
    $scope.user = user;
    $scope.data = data;
    if(data != null){
        if (data.retcode == 'true') {
            $scope.regResult = '密码修改成功';
        }else{
            $scope.regResult = '密码修改失败';
        }
    }
    $scope.gotoIndex = function () {
        window.location = "/taxation/view/jxshop/home/index.html";
    }
});

taxationApp.controller('UserTopNavController',function($scope,$location,user_service,toastr) {
	var threeFlag = {};
	var threeUrl = {};
	var threeName = {};
  	threeFlag = angular.fromJson(sessionStorage.threeFlag);
  	threeUrl = angular.fromJson(sessionStorage.threeUrl);
  	threeName = angular.fromJson(sessionStorage.threeName);
    $scope.threeFlag = threeFlag;
    $scope.threeUrl = threeUrl;
    $scope.threeName = threeName;
    
     $scope.gotoUcenter = function(){
     	 sessionStorage.removeItem("threeFlag");
         sessionStorage.removeItem("threeUrl");
         sessionStorage.removeItem("threeName");
		 window.location = "/taxation/view/jxshop/home/member/ucenter.html";
	 }
    
});

taxationApp.controller('UserUcenterLeftController',function($scope,$location,user_service,toastr) {

	$scope.gotoBaseInfo = function(){
		sessionStorage.threeFlag = JSON.stringify('true');
		sessionStorage.threeUrl = JSON.stringify('baseinfo.html');
		sessionStorage.threeName = JSON.stringify('基本信息');
		window.location = "/taxation/view/jxshop/home/member/baseinfo.html";
	}
	$scope.gotoAccountSecurity = function(){
		sessionStorage.threeFlag = JSON.stringify('true');
		sessionStorage.threeUrl = JSON.stringify('accountSecurity.html');
		sessionStorage.threeName = JSON.stringify('账号安全');
		window.location = "/taxation/view/jxshop/home/member/accountSecurity.html";
	}
	$scope.gotoOrderList = function(){
		sessionStorage.threeFlag = JSON.stringify('true');
		sessionStorage.threeUrl = JSON.stringify('orderlist.html');
		sessionStorage.threeName = JSON.stringify('我的订单');
		window.location = "/taxation/view/jxshop/home/member/orderlist.html";
	}
	$scope.gotoMyJxComment = function(){
		sessionStorage.threeFlag = JSON.stringify('true');
		sessionStorage.threeUrl = JSON.stringify('myJxComment.html');
		sessionStorage.threeName = JSON.stringify('评价晒单');
		window.location = "/taxation/view/jxshop/home/member/myJxComment.html";
	}
	$scope.gotoCollectGoods = function(){
		sessionStorage.threeFlag = JSON.stringify('true');
		sessionStorage.threeUrl = JSON.stringify('collectGoods.html');
		sessionStorage.threeName = JSON.stringify('关注的商品');
		window.location = "/taxation/view/jxshop/home/member/collectGoods.html";
	}
	$scope.gotoCollectStore = function(){
		sessionStorage.threeFlag = JSON.stringify('true');
		sessionStorage.threeUrl = JSON.stringify('collectStore.html');
		sessionStorage.threeName = JSON.stringify('关注的店铺');
		window.location = "/taxation/view/jxshop/home/member/collectStore.html";
	}
	$scope.gotoWatchHistory = function(){
		sessionStorage.threeFlag = JSON.stringify('true');
		sessionStorage.threeUrl = JSON.stringify('watchHistory.html');
		sessionStorage.threeName = JSON.stringify('浏览历史');
		window.location = "/taxation/view/jxshop/home/member/watchHistory.html";
	}
	$scope.gotoNoPage = function(){
		sessionStorage.threeFlag = JSON.stringify('true');
		sessionStorage.threeUrl = JSON.stringify('noPage.html');
		sessionStorage.threeName = JSON.stringify('敬请期待');
		window.location = "/taxation/view/jxshop/home/member/noPage.html";
	}
		 
});

 taxationApp.controller('UcenterController',function($scope,user_service,order_service,toastr) {
 	var user = {};
    user = angular.fromJson(sessionStorage.user);
    $scope.user = user;
    if(user.lastLoginTime != undefined && user.lastLoginTime != null){
    	var year = user.lastLoginTime.year+1900;
    	var month = user.lastLoginTime.month+1;
    	var date = user.lastLoginTime.date;
    	var hours = user.lastLoginTime.hours;
    	var minutes = user.lastLoginTime.minutes;
    	var seconds = user.lastLoginTime.seconds;
    	var lastTime = year+"-"+month+"-"+date+" "+hours+":"+minutes+":"+seconds;
    	$scope.lastTime = lastTime;
    }
    
    var orderList = {};
    var resultInfo = {};
    $scope.orderList = orderList;
    $scope.resultInfo = resultInfo;
    order_service.queryOrderListByUserId($scope.user.userId).then(function (data){
    	if(data.message == '查询用户订单信息异常'){
    		$scope.resultInfo = data.message;
    	}else{
    		$scope.orderList = angular.fromJson(data.message);
    	}
    });
    
    
});
taxationApp.controller('UserUpdatePwController',function($scope,user_service,toastr,$interval) {
    var user = {};
    var smsCheckCodeEntity = {};
    $scope.user = angular.fromJson(sessionStorage.user);
    var userPhone = $scope.user.userAccount;
    $scope.getSmsCheckCode = function () {

        $scope.checksendsec = 60
        $scope.checksend = true;
        user_service.getSmsCheckCode(userPhone).then(function (data) {
            if (data.sendReturncode == '0') {
                smsCheckCodeEntity = data;
                $scope.checksend = true;
                var sendTimer = $interval(function () {
                    if ($scope.checksendsec < 1) {
                        $scope.checksend = false;
                        $interval.cancel(sendTimer);
                        $scope.checksendsec = 60;
                    } else {
                        $scope.checksendsec = $scope.checksendsec - 1
                    }
                }, 1000);

                toastr.success('短信发送成功', {timeOut: 2000});
                sessionStorage.removeItem("smsCheckCode");
                sessionStorage.setItem("smsCheckCode", data.smsCheckCode);
                var timeSession = 15 * 60;
                var checkTimer = $interval(function () {
                    if (timeSession < 1) {
                        $interval.cancel(checkTimer);
                        timeSession = 15 * 60;
                        sessionStorage.removeItem("smsCheckCode");
                    } else {
                        timeSession = timeSession - 1
                    }
                }, 1000);
            } else if (data.sendReturncode == '33' || data.sendReturncode == '22') {
                $scope.checksend = false;
                toastr.warning(data.sendReturnmessage, {timeOut: 2000});
            } else {
                $scope.checksend = false;
                toastr.error('发送失败', {timeOut: 2000});
            }
        });
    };
    //校验验证码
    $scope.checkPhoneCheckCode = function () {
        if($scope.user.phoneCheckCode!=null && $scope.user.phoneCheckCode == sessionStorage.getItem("smsCheckCode")){
            $scope.checkResult = 'true';
            $scope.checkCodeResult = 'true';
        }else{
            $scope.checkResult = 'false';
        }
    }
    //校验旧密码输入是否正确
    $scope.checkOldPassword = function(){
        if ($scope.user.userPassword == $scope.user.oldPassword){
            $scope.oldPasswordResult ='1';
            $scope.checkOldPwResult = 'true';
        }else{
            $scope.oldPasswordResult ='2';
        }
    }
    $scope.checkPassword1 = function () {
        if ($scope.user.newPassword.length>=6&&$scope.user.newPassword.length<=12){
            if($scope.user.newPassword ==$scope.user.userPassword && $scope.user.newPassword ==$scope.user.oldPassword){
                $scope.passwordResult1 ='3';
            }else{
                $scope.passwordResult1 ='1';
            }

        }else{
            $scope.passwordResult1 ='2';
        }

    }
    $scope.checkPassword2 = function () {
        if($scope.user.newPassword != $scope.user.newPassword1){
            $scope.passwordResult2 ='2';
        }else{
            $scope.passwordResult2 ='1';
            $scope.checkNewPwResult = 'true';
        }
    }
    $scope.doUpdatePassword = function(){
        if ($scope.checkCodeResult == 'true' && $scope.checkOldPwResult == 'true'&& $scope.checkNewPwResult == 'true'){

            user_service.updatePw($scope.user.userAccount,$scope.user.newPassword).then(function (data) {
                if (data.retcode == 'true') {
                    sessionStorage.clear();
                    window.location = "/taxation/view/jxshop/home/login.html";
                } else {
                    toastr.error("密码修改失败", {timeOut: 2000});
                }
            });
        }else{
            toastr.warning("验证信息填写错误，请修改确认无误后再保存提交", {timeOut: 2000});
        }
    }
});
taxationApp.controller('UserAccountSecurityController',function($scope,user_service) {
    var user = {};
    var securityUserInfo = {};
    user = angular.fromJson(sessionStorage.user);
    //查询用户信息
    user_service.securityUserInfo(user).then(function (data) {
        $scope.securityUserInfo = angular.fromJson(data);
        //账号、手机密码、邮箱加密
        var reg = /1(\d{2})\d{4}(\d{4})/g;
        var reg1 = /(\d{3})\d{3}(\.*)/g;
        $scope.securityUserInfo.userAccount = $scope.securityUserInfo.userAccount.replace(reg,"1$1****$2");
        $scope.securityUserInfo.userPhone = $scope.securityUserInfo.userPhone.replace(reg,"1$1****$2");
        $scope.securityUserInfo.userEmail = $scope.securityUserInfo.userEmail.replace(reg1,"$1***$2");
    });

    //跳转到修改密码页面
    $scope.gotoUpPwd = function(){
        window.location = "/taxation/view/jxshop/home/member/upPwd.html";
    }
    $scope.gotoUpdateEmail = function(){
        window.location = "/taxation/view/jxshop/home/member/updateEmail.html";
    }

    $scope.gotoBindEmail = function(){
        window.location = "/taxation/view/jxshop/home/member/bindEmail.html";
    }
});

taxationApp.controller('BindEmailController',function($scope,user_service) {
    var user = {};
    user = angular.fromJson(sessionStorage.user);

    $scope.checkEmail = function(){
        user.email = $scope.user.email;
        var emailReg = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
        if (emailReg.test(user.email)){
            $scope.checkResult = 'true';
        }else{
            $scope.checkResult = 'false';
        }
    }
    $scope.bindEmail = function(){
        user_service.bindEmail(user.userAccount,user.email).then(function (data) {
            if (data.retcode == 'true') {
                sessionStorage.threeFlag = JSON.stringify('true');
                sessionStorage.threeUrl = JSON.stringify('accountSecurity.html');
                sessionStorage.threeName = JSON.stringify('账号安全');
                window.location = "/taxation/view/jxshop/home/member/accountSecurity.html";
            } else {
                toastr.error("密码修改失败", {timeOut: 2000});
            }
        });
    }

});

taxationApp.controller('OrderListController',function($scope,user_service,toastr) {
  	
  	
});

taxationApp.controller('MyJxCommentController',function($scope,user_service,toastr) {
  	
  	
});

taxationApp.controller('CollectGoodsController',function($scope,user_service,toastr) {
  	
  	
});
taxationApp.controller('CollectStoreController',function($scope,user_service,toastr) {
  	
  	
});
taxationApp.controller('WatchHistoryController',function($scope,user_service,toastr) {
  	
  	
});
taxationApp.controller('NoPageController',function($scope,user_service,toastr) {
  	
  	
});
taxationApp.controller('UserPayController',function($scope,$state,user_service,order_service,toastr) {
	$scope.aliPay = function(){
		order_service.testPay().then(function (data) {
			sessionStorage.setItem("payResult",data.message);
			$state.go("index.payBlankPage",{},{reload:true});
	   });
   }
});

taxationApp.controller('UserPayBlankPageController',function($scope,$timeout,$sce,$state,order_service){
	var payResult = sessionStorage.getItem("payResult");
	$scope.payResult = $sce.trustAsHtml(payResult);
	$timeout(function(){
		document.forms[0].submit();
		//document.getElementsByName("punchout_form")[0].submit();
	});

});
