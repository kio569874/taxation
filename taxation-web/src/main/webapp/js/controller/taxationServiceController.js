'use strict';
taxationApp.controller('TaxserviceController',function ($rootScope,$scope,$state,taxationService_service) {
	var getAllService = function () {
		taxationService_service.queryServiceProvider($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage).then(function (data) {
			$scope.paginationConf.totalItems = data.totalCount;
			$scope.providerList = angular.fromJson(data.data);
		});
	}

	//配置分页基本参数
	$scope.paginationConf = {
		currentPage: 1,
		totalItems: 0,
		itemsPerPage: 10,
		pagesLength: 9,
		perPageOptions: [10, 20, 30, 40, 50]
	};

	/***************************************************************
	 当页码和页面记录数发生变化时监控后台查询
	 如果把currentPage和itemsPerPage分开监控的话则会触发两次后台事件。
	 ***************************************************************/
	$scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', getAllService);

	$scope.gotoServiceDetail =function(providerId){
		$scope.params = {};
		$scope.params.providerId = providerId;
		$state.go("index.serviceDetail",{"params":JSON.stringify($scope.params)});
	}

});
taxationApp.controller('ServiceDetailController',function ($rootScope,$scope,$state,$stateParams,taxationService_service) {
	$scope.params = JSON.parse($stateParams.params);
	taxationService_service.queryServiceByProviderId($scope.params.providerId).then(function (data) {
		$scope.serviceList = angular.fromJson(data);
	});

	$scope.gotoServiceInfo = function(serviceId){
		$scope.params.serviceId = serviceId;
		$state.go("index.serviceInfo",{"params":JSON.stringify($scope.params)});
	}

});
taxationApp.controller('ServiceInfoController',function ($rootScope,$scope,$state,$stateParams,toastr,taxationService_service) {
	$scope.params = JSON.parse($stateParams.params);
	taxationService_service.queryServiceInfo($scope.params.serviceId).then(function (data) {
		$scope.serviceInfo = angular.fromJson(data);
	});
	taxationService_service.queryProviderInfo($scope.params.providerId).then(function (data) {
		$scope.providerInfo = angular.fromJson(data);
	});

	$scope.addToCart = function(){
		var serviceId = $scope.params.serviceId;
		var userId = (angular.fromJson(sessionStorage.getItem("user"))).userId;
		taxationService_service.addToCart(userId,serviceId).then(function (data) {
			if(data.retcode =='true'){
				toastr.success(data.message,{timeOut:2000});
			}else{
				toastr.warning(data.message,{timeOut:2000});
			}
		});
	}
	$scope.buyNow = function(){
		var userId = (angular.fromJson(sessionStorage.getItem("user"))).userId;
		$scope.params.userId = userId;
		$state.go("index.buyNow",{"params":JSON.stringify($scope.params)});
	}
});
taxationApp.controller('BuyNowController',function ($rootScope,$scope,$http,$state,$stateParams,toastr,taxationService_service) {
	$scope.$on('$viewContentLoaded', function() {
		$("input[name=accept_address]").click(function(){
			if($(this).val() == -1){
				$(".buy-step .main .order-info .receipt-info form.add-address").removeClass("hide");
			}else{
				$(".buy-step .main .order-info .receipt-info form.add-address").addClass("hide");
			}
		});

		$(".buy-step .main .order-info .pay-info ul li").click(function(){
			//选择支付方式
			$(this).addClass("selected").siblings().removeClass("selected");
		});
	});
	//加载地区下拉列表
	$scope.error = {};
	$scope.list = [];
	//初始化更新回显变量
	$scope.updateAddress = {};
	$scope.order = {};
	//获取参数
	$scope.params = JSON.parse($stateParams.params);
	//查询用户收获地址
	taxationService_service.queryUserAddress($scope.params.userId).then(function (data) {
		$scope.myAddressList = data;
	});
	//加载立即购买的服务信息
	taxationService_service.queryServiceInfo($scope.params.serviceId).then(function (data) {
		$scope.buyNowServiceInfo = angular.fromJson(data);
	});

	$scope.c = function () {
		$scope.error.province = false;
		$scope.error.city = false;
		$scope.error.area = false;
		$scope.city = "";
		$scope.area = "";
	};
	$scope.c2 = function () {
		$scope.error.city = false;
		$scope.error.area = false;
		$scope.area = "";
	};
	$scope.c3 = function () {
		$scope.error.area = false;
	};
	//加载默认地址
	var loadCityArea = function () {
		taxationService_service.queryCityArea().then(function (data){
			$scope.list = data;
		});
	}
	//添加新地址弹框
	$scope.addNewAddress = function(){
		loadCityArea();
		$("#modalBg").fadeIn();
		$("#addAddressDialog").fadeIn();
	}
	//修改地址信息弹框
	$scope.updateReceiptInfo = function (addressId){
		taxationService_service.queryAddressById(addressId).then(function (data) {
			//$scope.addressProvince_update.name;
			//$scope.addressCity_update.name =data;
			//$scope.addressArea_update.value;
			loadCityArea();
			var addressInfo = angular.fromJson(data)[0];
			$scope.updateAddress.addressProvince_update = addressInfo.addressProvince;
			$scope.updateAddress.addressCity_update = addressInfo.addressCity;
			$scope.updateAddress.addressArea_update = addressInfo.addressArea;
			$scope.updateAddress.addressText_update = addressInfo.addressText;
			$scope.updateAddress.oldAddress = addressInfo.addressProvince +" "+addressInfo.addressCity +" "+addressInfo.addressArea;
			$scope.updateAddress.addressId_update = addressInfo.addressId;
			$scope.updateAddress.userName_update = addressInfo.userName;
			$scope.updateAddress.userPhone_update = addressInfo.userPhone;
		});
		$scope.showDialog("#updateAddressDialog");
	}
	$scope.showDialog = function(id){
		$("#modalBg").fadeIn();
		$(id).fadeIn();
	}
	$scope.closeDialog = function(id){
		$("#modalBg").fadeOut();
		$(id).fadeOut();
	}
	$scope.updateOldAddress = function(){
		$scope.updateAddressOld = {};
		if ($scope.updateAddress.addressDefault_update==undefined){
			toastr.warning("请选择是否是默认收货地址",{timeOut:2000});
			return;
		}
		if ($scope.updateAddress.userName_update==undefined||$scope.updateAddress.userName_update.trim().length==0){
			toastr.warning("请输入收货人姓名",{timeOut:2000});
			return;
		}
		if ($scope.updateAddress.userPhone_update==undefined||$scope.updateAddress.userPhone_update.trim().length==0){
			toastr.warning("请输入联系电话",{timeOut:2000});
			return;
		}else{
			var reg = /^1[3|4|5|7|8][0-9]{9}$/;
			var flag = reg.test($scope.updateAddress.userPhone_update);
			if(!flag){
				toastr.warning("手机号码格式不正确",{timeOut:2000});
				return;
			}
		}
		if ($scope.updateAddress.addressProvince_update==undefined||$scope.updateAddress.addressProvince_update.name==undefined){
			toastr.warning('请选择省份',{timeOut:2000});
			return;
		}

		if ($scope.updateAddress.addressCity_update==undefined||$scope.updateAddress.addressCity_update.name==undefined){
			toastr.warning('请选择市/区',{timeOut:2000});
			return;
		}
		if ($scope.updateAddress.addressArea_update==undefined||$scope.updateAddress.addressArea_update.value==undefined){
			toastr.warning('请选择县级/区域',{timeOut:2000});
			return;
		}
		if ($scope.updateAddress.addressText_update==undefined||$scope.updateAddress.addressText_update.trim().length==0){
			toastr.warning('请输入详细地址',{timeOut:2000});
			return;
		}
		var user = angular.fromJson(sessionStorage.getItem("user"));
		$scope.updateAddressOld.addressId = $scope.updateAddress.addressId_update;
		$scope.updateAddressOld.addressProvince = $scope.updateAddress.addressProvince_update.name;
		$scope.updateAddressOld.addressCity = $scope.updateAddress.addressCity_update.name;
		$scope.updateAddressOld.addressArea = $scope.updateAddress.addressArea_update.value;
		$scope.updateAddressOld.addressText = $scope.updateAddress.addressText_update.trim();
		$scope.updateAddressOld.addressDefault = $scope.updateAddress.addressDefault_update;
		$scope.updateAddressOld.userId = user.userId;
		$scope.updateAddressOld.userName = $scope.updateAddress.userName_update.trim();
		$scope.updateAddressOld.userPhone = $scope.updateAddress.userPhone_update.trim();

		taxationService_service.updateOldAddress($scope.updateAddressOld).then(function (data) {
			if(data.retcode =='true'){
				$scope.closeDialog('updateAddressDialog');
				$state.go("index.buyNow",{"params":JSON.stringify($scope.params)},{reload:true});
			}else{
				toastr.warning(data.message,{timeOut:2000});
			}
		});
	}
	$scope.saveAddress = function(){
		$scope.newAddress = {};
		if ($scope.addressDefault_add==undefined){
			toastr.warning("请选择是否是默认收货地址",{timeOut:2000});
			return;
		}
		if ($scope.userName_add==undefined||$scope.userName_add.trim().length==0){
			toastr.warning("请输入收货人姓名",{timeOut:2000});
			return;
		}
		if ($scope.userPhone_add==undefined||$scope.userPhone_add.trim().length==0){
			toastr.warning("请输入联系电话",{timeOut:2000});
			return;
		}else{
			var reg = /^1[3|4|5|7|8][0-9]{9}$/;
			var flag = reg.test($scope.userPhone_add);
			if(!flag){
				toastr.warning("手机号码格式不正确",{timeOut:2000});
				return;
			}
		}
		if ($scope.addressProvince_add==undefined||$scope.addressProvince_add.name==undefined){
			toastr.warning('请选择省份',{timeOut:2000});
			return;
		}

		if ($scope.addressCity_add==undefined||$scope.addressCity_add.name==undefined){
			toastr.warning('请选择市/区',{timeOut:2000});
			return;
		}
		if ($scope.addressArea_add==undefined||$scope.addressArea_add.value==undefined){
			toastr.warning('请选择县级/区域',{timeOut:2000});
			return;
		}
		if ($scope.addressText_add==undefined||$scope.addressText_add.trim().length==0){
			toastr.warning('请输入详细地址',{timeOut:2000});
			return;
		}
		var user = angular.fromJson(sessionStorage.getItem("user"));
		$scope.newAddress.addressProvince = $scope.addressProvince_add.name;
		$scope.newAddress.addressCity = $scope.addressCity_add.name;
		$scope.newAddress.addressArea = $scope.addressArea_add.value;
		$scope.newAddress.addressText = $scope.addressText_add.trim();
		$scope.newAddress.addressDefault = $scope.addressDefault_add;
		$scope.newAddress.userId = user.userId;
		$scope.newAddress.userName = $scope.userName_add.trim();
		$scope.newAddress.userPhone = $scope.userPhone_add.trim();

		taxationService_service.addNewAddress($scope.newAddress).then(function (data) {
			if(data.retcode =='true'){
				$scope.closeDialog('addAddressDialog');
				$state.go("index.buyNow",{"params":JSON.stringify($scope.params)},{reload:true});
			}else{
				toastr.warning(data.message,{timeOut:2000});
			}
		});
	}
	$scope.submitOrder = function(){
		var orderParam = {};
		var user = angular.fromJson(sessionStorage.getItem("user"));
		var isSelectDefaultAddress = $(".receipt-info-list li input:radio[name='default_address']:checked");
		var isSelectPayType = $(".pay-info ul li.selected");
		//判断是否选择了收货地址
		if (isSelectDefaultAddress.length==1){
			orderParam.addressId = isSelectDefaultAddress.attr("ng-value");
		}else{
			toastr.warning("请选择收货地址",{timeOut:2000});
			return;
		}
		//判断是否选择了支付方式
		if(isSelectPayType.length==1){
			orderParam.payType = $(".pay-info ul li.selected").find("input[type='hidden']").val();
			//alert(orderParam.payType);
		}else{
			toastr.warning("请选择支付类型",{timeOut:2000});
			return;
		}
		//设置订单备注，用户可选输入
		if ($scope.orderRemark == undefined){
			orderParam.orderRemark = "";
		}else{
			orderParam.orderRemark = $scope.orderRemark.trim();
		}
		orderParam.userId = user.userId;
		orderParam.serviceId = $scope.params.serviceId;
		orderParam.providerId = $scope.params.providerId;
		orderParam.price = $scope.buyNowServiceInfo.price;
		orderParam.serviceName = $scope.buyNowServiceInfo.serviceName;
		taxationService_service.buyServiceNow(orderParam).then(function (data) {
			if(data.retcode =='true'){
				$scope.orderInfo = angular.fromJson(data.data);;
				//$scope.orderInfo.orderId = orderInfo.orderId;
				//$scope.params.orderNo = orderInfo.orderNo;
				$state.go("index.paying",{"params":JSON.stringify($scope.orderInfo)});
			}else{
				toastr.warning(data.message,{timeOut:2000});
			}
		});
	}

	$scope.gotoServiceInfo = function(){
		$state.go("index.serviceInfo",{"params":JSON.stringify($scope.params)},{reload:true});
	}
});
taxationApp.controller('PayingController',function ($rootScope,$scope,$stateParams,taxationService_service,order_service,toastr,$state) {
	//获取参数
	$scope.params = (JSON.parse($stateParams.params));
	console.log($scope.params);
	//更改支付方式
	$scope.changePayWay = function(payWay){
		if (payWay=='1'){
			$scope.params.payType = '2';
		}else{
			$scope.params.payType = '1';
		}
		$scope.updatePayType($scope.params.orderId,$scope.params.payType);
	}
	$scope.updatePayType = function(oderId,payType){
		taxationService_service.updatePayType(oderId,payType).then(function (data) {
			if(data.retcode =='true'){
			}else{
				toastr.warning("更改支付方式发生异常",{timeOut:2000});
			}
		});
	}
	$scope.payForService = function(payType){
		if (payType=='1'){
			//支付宝支付
			console.log($scope.params.orderNo +"；"+$scope.params.orderAmount +"；"+$scope.params.orderSubject);
			order_service.testPay($scope.params.orderNo,$scope.params.orderAmount,$scope.params.orderSubject).then(function (data) {
				sessionStorage.setItem("payResult",data.message);
				$state.go("index.payBlankPage",{},{reload:true});
			});
		}else if(payType=='2'){
			//微信支付
		}

	}
});