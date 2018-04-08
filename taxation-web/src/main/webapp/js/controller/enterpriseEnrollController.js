'use strict';

taxationApp.controller('ChooseServiceController', ['$scope','toastr','$state','companyService_service', function($scope,toastr,$state,companyService_service) {
    $scope.renderFinish = function(){
        console.log('渲染完之后的操作');

        $(".box ul.services li").click(function(){
            $(this).toggleClass("selected").siblings().removeClass("selected");

            //alert($(this).find("input[type='hidden']").val());
            //写入隐藏表单数据部分
        });
    }
    //重新选择服务商：移除所选服务商
    $scope.resetChoose = function(){
        //alert($(".box ul.services li.selected").find("input[type='hidden']").val());
        $(".box ul.services li").removeClass("selected");
    }
    $scope.gotoBaseInfo = function(){
        if ($(".box ul.services li.selected").find("input[type='hidden']").val()==undefined){
            toastr.error('请选择服务商',{timeOut:2000});
        }else{
            var params = {};
            params.providerId = $(".box ul.services li.selected").find("input[type='hidden']").eq(0).val();
            params.serviceId = $(".box ul.services li.selected").find("input[type='hidden']").eq(2).val();
            params.price = $(".box ul.services li.selected").find("input[type='hidden']").eq(1).val();
            params.providerName = $(".box ul.services li.selected div.name").text();
            $state.go("index.baseInfo",{"params":JSON.stringify(params)});

        }

    }

    var getProvider = function () {
        companyService_service.queryProvider($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage).then(function (data) {
            $scope.paginationConf.totalItems = data.totalCount;
            $scope.providerList = angular.fromJson(data.data);
            console.log($scope.providerList);
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
    $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', getProvider);

}]);
taxationApp.controller('BaseInfoController',function ($rootScope,$scope,$state,$stateParams,toastr) {
    var companyService ={};
    $scope.companyService = companyService;

    //用户名称校验
    $scope.checkUserName = function(){
        if ($scope.companyService == undefined||$scope.companyService.userName == undefined){
            $scope.flagUserName = '3';
            return false;
        }else if($scope.companyService.userName.length>0&&$scope.companyService.userName.length<=64){
            $scope.flagUserName = '1';
            return true;
        }else{
            $scope.flagUserName = '2';
            return false;
        }
    }
    //身份证号码校验
    $scope.checkIdCard = function(){
        if ($scope.companyService == undefined||$scope.companyService.userIdcard == undefined){
            $scope.flagIdCard = '3';
            return false;
        }
        var reg = /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/;
        var flag = reg.test($scope.companyService.userIdcard);
        if(!flag){
            $scope.flagIdCard = '2';
            return false;
        }else{
            $scope.flagIdCard = '1';
            return true;
        }
    }
    //手机号码校验
    $scope.checkUserPhone = function(){
        if ($scope.companyService == undefined||$scope.companyService.userPhone == undefined){
            $scope.flagUserPhone = '3';
            return false;
        }
        var reg = /^1[3|4|5|7|8][0-9]{9}$/;
        var flag = reg.test($scope.companyService.userPhone);
        if(!flag){
            $scope.flagUserPhone = '2';
            return false;
        }else{
            $scope.flagUserPhone = '1';
            return true
        }
    }

    $scope.checkSex = function(){
        if ($scope.companyService==undefined||$scope.companyService.userSex==undefined){
            $scope.flagUserSex = '2';
            return false;
        }else{
            $scope.flagUserSex = '1';
            return true;
        }
    }
    $(":radio").click(function(){
        $scope.flagUserSex = '1';
    });
    $scope.gotoProductionSite = function(){
        if ($scope.checkUserName()&$scope.checkIdCard()&$scope.checkUserPhone()&$scope.checkSex()){
            $scope.params = JSON.parse($stateParams.params);
            $scope.params.userName = $scope.companyService.userName;
            $scope.params.userSex = $scope.companyService.userSex;
            $scope.params.userIdcard = $scope.companyService.userIdcard;
            $scope.params.userPhone = $scope.companyService.userPhone;
            //接受传递过来的参数
            $scope.params.providerId = (JSON.parse($stateParams.params)).providerId;
            $scope.params.providerName=(JSON.parse($stateParams.params)).providerName;
            $state.go("index.productionSite",{"params":JSON.stringify($scope.params)});
        }

    }

});
taxationApp.controller('ProductionSiteController',function ($rootScope,$scope,$http,$state,$stateParams,toastr,taxationService_service) {
    //经营行所性质
    $("#product-place span").click(function(){
        $(this).toggleClass("selected").siblings().removeClass("selected");
    });
    //生产经营类别
    $("#product-type span").click(function(){
        $(this).toggleClass("selected").siblings().removeClass("selected");
    });

    $scope.error = {};
    $scope.list = [];
    //$http.get('/taxation-web/json/guiyang.json').success(function (data) {
    //    $scope.list = data;
    //});
    var loadCityArea = function () {
        taxationService_service.queryCityArea().then(function (data){
            $scope.list = data;
        });
    }
    loadCityArea();
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
    //获取传递过来的参数
    $scope.params = JSON.parse($stateParams.params);
    $scope.gotoEnclosure = function(){
        $scope.params.operateSiteNature = $("#product-place span.selected").find("input[type='hidden']").val();
        $scope.params.produceOperateType = $("#product-type span.selected").find("input[type='hidden']").val();
        if ($scope.params.operateSiteNature==undefined){
            toastr.warning('请选择经营场所性质',{timeOut:2000});
            return;
        }
        if ($scope.params.produceOperateType==undefined){
            toastr.warning('请选择生产经营类别',{timeOut:2000});
            return;
        }
        if ($scope.province==undefined||$scope.province.name==undefined){
            toastr.warning('请选择省份',{timeOut:2000});
            return;
        }

        if ($scope.city==undefined||$scope.city.name==undefined){
            toastr.warning('请选择市/区',{timeOut:2000});
            return;
        }
        if ($scope.area==undefined||$scope.area.value==undefined){
            toastr.warning('请选择县级/区域',{timeOut:2000});
            return;
        }
        if ($scope.address==undefined||$scope.address.trim().length==0){
            toastr.warning('请输入详细地址',{timeOut:2000});
            return;
        }
        $scope.params.address=$scope.address.trim();
        $scope.params.province=$scope.province.name;
        $scope.params.city=$scope.city.name;
        $scope.params.area=$scope.area.value;

        $state.go("index.addressAndPay",{"params":JSON.stringify($scope.params)});
        //alert("province="+$scope.province.name+"  city="+$scope.city.name+"  area="+$scope.area.value);
    }

});

taxationApp.controller('AddressAndPayController',function ($rootScope,$scope,$http,$state,$stateParams,toastr,taxationService_service) {
    $scope.$on('$viewContentLoaded', function() {
        $(".buy-step .main .order-info .pay-info ul li").click(function(){
            //选择支付方式
            $(this).addClass("selected").siblings().removeClass("selected");
        });
    });
    //获取传递过来的参数
    $scope.params = JSON.parse($stateParams.params);
    //加载地区下拉列表
    $scope.error = {};
    $scope.list = [];
    //初始化更新回显变量
    $scope.updateAddress = {};
    var user = angular.fromJson(sessionStorage.getItem("user"));
    taxationService_service.queryUserAddress(user.userId).then(function (data) {
        $scope.myAddressList = data;
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
                $state.go("index.addressAndPay",{"params":JSON.stringify($scope.params)},{reload:true});
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
                $state.go("index.addressAndPay",{"params":JSON.stringify($scope.params)},{reload:true});
            }else{
                toastr.warning(data.message,{timeOut:2000});
            }
        });
    }
    $scope.gotoEnclosure = function(){
        var isSelectDefaultAddress = $(".receipt-info-list li input:radio[name='default_address']:checked");
        var isSelectPayType = $(".pay-info ul li.selected");
        //判断是否选择了收货地址
        if (isSelectDefaultAddress.length==1){
            $scope.params.addressId = isSelectDefaultAddress.attr("ng-value");
            console.log($scope.params.addressId);
        }else{
            toastr.warning("请选择收货地址",{timeOut:2000});
            return;
        }
        //判断是否选择了支付方式
        if(isSelectPayType.length==1){
            $scope.params.payType = $(".pay-info ul li.selected").find("input[type='hidden']").val();
            //alert(orderParam.payType);
        }else{
            toastr.warning("请选择支付类型",{timeOut:2000});
            return;
        }
        $state.go("index.enclosure",{"params":JSON.stringify($scope.params)});
    }


});

taxationApp.controller('EnclosureController',function ($rootScope,$scope,$http,$state,$stateParams,FileUploader,toastr) {
    //获取传递过来的参数
    $scope.params = JSON.parse($stateParams.params);

    var uploader = $scope.uploader = new FileUploader({
        url: '/taxation-web/enterpriseEnroll/saveFiles.htm',
        queueLimit: 5,
        formData:[{providerId:$scope.params.providerId}]
    });

    // FILTERS

    // a sync filter
    uploader.filters.push({
        name: 'syncFilter',
        fn: function(item /*{File|FileLikeObject}*/, options) {
            console.log('syncFilter');
            return this.queue.length < 6;
        }
    });

    uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
        console.info('onWhenAddingFileFailed', item, filter, options);
    };
    uploader.onAfterAddingFile = function(fileItem) {
        console.info('onAfterAddingFile', fileItem);
    };
    uploader.onAfterAddingAll = function(addedFileItems) {
        console.info('onAfterAddingAll', addedFileItems);
    };
    uploader.onBeforeUploadItem = function(item) {
        console.info('onBeforeUploadItem', item);
    };
    //uploader.onProgressItem = function(fileItem, progress) {
    //    console.info('onProgressItem', fileItem, progress);
    //};
    //uploader.onProgressAll = function(progress) {
    //    console.info('onProgressAll', progress);
    //};
    uploader.onSuccessItem = function(fileItem, response, status, headers) {
        console.info('onSuccessItem', fileItem, response, status, headers);
    };
    uploader.onErrorItem = function(fileItem, response, status, headers) {
        console.info('onErrorItem', fileItem, response, status, headers);
    };
    uploader.onCancelItem = function(fileItem, response, status, headers) {
        console.info('onCancelItem', fileItem, response, status, headers);
    };
    uploader.onCompleteItem = function(fileItem, response, status, headers) {

    };
    uploader.onCompleteAll = function() {
        toastr.success('附件上传成功',{timeOut:2000});
    };


    $scope.gotoEnsure = function(){
        $state.go("index.ensure",{"params":JSON.stringify($scope.params)});
        //alert("province="+$scope.province.name+"  city="+$scope.city.name+"  area="+$scope.area.value);
    }



});
taxationApp.controller('EnsureController',function ($rootScope,$scope,$http,$state,$stateParams,operateSiteNatureArr,produceOperateTypeArr,userSexArr,companyService_service,toastr,taxationService_service) {
    $scope.operateSiteNatureArr = operateSiteNatureArr;
    $scope.produceOperateTypeArr = produceOperateTypeArr;
    $scope.userSexArr = userSexArr;
    //获取传递过来的参数
    $scope.params = JSON.parse($stateParams.params);
    var user = angular.fromJson(sessionStorage.getItem("user"));
    $scope.params.userId = user.userId;
    taxationService_service.queryAddressById($scope.params.addressId).then(function (data) {
        $scope.address =  angular.fromJson(data)[0];
    });
    //更改支付方式
    $scope.changePayWay = function(payWay){
        if (payWay=='1'){
            $scope.params.payType = '2';
        }else{
            $scope.params.payType = '1';
        }
    }
    $scope.gotoBusinessStatus = function(){
        companyService_service.saveCompanyServiceInfo($scope.params).then(function (data) {
            if(data.retcode =='true'){
                $state.go("index.businessStatus");
            }else{
                toastr.warning(data.message,{timeOut:2000});
            }
        });
    }


});
taxationApp.directive('repeatFinish',function(){
    return {
        link: function(scope,element,attr){
            if(scope.$last == true){
                console.log('ng-repeat执行完毕');
                scope.$eval( attr.repeatFinish )
            }
        }
    }
});

