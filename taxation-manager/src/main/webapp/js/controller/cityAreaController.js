/**
 * @description：
 * @author：JustFresh
 * @time：2017-08:05 16:05
 */
myApp.controller('CityAreaController',function ($http,$rootScope,$scope,cityAreaService,$location,$state) {

	var checkAll = false;
 	
 	$rootScope.activeMenu = translateUrl($location.path());
 	
 	
 	/**
     * 翻译：Jerry Zhang
     * currentPage: 当前页码
     * totalItems: 总记录数
     * itemsPerPage:  每页记录数
     * onChange: 分页改变将会执行的方法
     * pagesLength: 页码显示数量
     * perPageOptions: 定义下拉每页多少记录，默认 [10, 15, 20, 30, 50]
     */
    $scope.paginationConf = {
    	order: 'desc',
    	sort: 'pid',
    	page: 1,
    	rows: 10,
        pname: ''
    };
    $scope.perPageOptions = [5, 10, 20, 50];
    
    /* 页面加载时候默认的分页数据信息绑定 */
    $scope.pagingInfo = {};
	$scope.pagingInfo = cityAreaService.getPaging($scope.paginationConf).then(function (response) { 
		if(response.message != null){
			$scope.pagingInfo = JSON.parse(response.message);
		}
    });

	/* 点击搜索按钮分页数据信息绑定 */
	$scope.searchPaging = function(){
		return cityAreaService.getPaging($scope.paginationConf).then(function (response) { 
			if(response.message != null){
				mui.toast("查询城市配置数据成功！！！");
				$scope.pagingInfo = JSON.parse(response.message);
			}
	    });
	};
    
	/* 点击分页页面查询分页数据信息绑定 */
	$scope.showPage = function(order,sort,page,rows){
		return cityAreaService.showPage(order,sort,page,rows).then(function(response){
			if(response.message != null){
				$scope.pagingInfo = JSON.parse(response.message);
			}
		});
	};
	
	/* 删除一条城市配置数据 */
	$scope.remove = function(pid){
		mui.confirm('确认要删除城市配置吗？', '警告信息', ['确认','取消'], function(e) {
			if(e.index == 0) {
				//确认要删除
				return cityAreaService.remove(pid).then(function(res){
					mui.toast(res.message);
					if(res.status = "success"){
						setTimeout(function(){
							window.location.reload();
						},2000);
					}
				});
			}else{
				return;
			}
		});
	};
	
	/* 更改状态——启用/禁用部分 */
	$scope.changeStatus = function(id,status){
		return systemUserService.changeStatus(id,status).then(function(res){
			mui.toast(res.message);
			if(res.status == "success"){
				setTimeout(function(){
					window.location.reload();
				},2000);
			}
		});
	};
	
	/* 全选/反选当前页所有数据 */
 	$scope.changeCheckboxStatus = function(inputName){
 		if(checkAll){
			//反选
			$("input[name=" + inputName + "]").prop("checked",false);
			checkAll = false;
		}else{
			//全选
			$("input[name='" + inputName + "']").prop("checked",true);
			checkAll = true;
		}
 	}
 	
 	/* 批量删除数据  */
 	$scope.batchRemove = function(inputName){
 		var ids = '';
		$.each($("input[name='" + inputName + "']:checked"),function(){
	           ids += $(this).val() + ",";
	       });
		if(ids == null || ids.trim() == ""){
			mui.toast("请选择需要批量删除的数据");
			return;
		}else{
			mui.confirm('确认要进行批量删除操作吗？', '警告信息', ['确认','取消'], function(e) {
				if(e.index == 0) {
					//确认要删除
					return cityAreaService.batchRemove(ids).then(function(res){
						mui.toast(res.message);
						if(res.status == "success"){
							setTimeout(function(){
								window.location.reload();
							},2000);
						}
					});
				}else{
					return;
				}
			});
		}
 		
 	}
	
});

myApp.controller("CityAreaMngController",function($http,$rootScope,$scope,cityAreaService,$location,$stateParams,$state){

	$rootScope.activeMenu = "index.city_area";
	$scope.displayCityAreaId = $stateParams.pid == undefined ? null : $stateParams.pid;
	
	$scope.city_area_form = {pid:'',pcode:'',pname:'',pshortname: '',plevel:'',parentid:''};
	
	/* 增加/编辑处处理表单提交数据部分 */
	$scope.submitCityAreaForm = function(){
		if($scope.displayCityAreaId != null && $scope.displayCityAreaId != ""){
			//编辑城市配置
			return cityAreaService.editCityArea($scope.city_area_form).then(function(response){
				mui.toast(response.message);
				if(response.status == "success"){
					setTimeout(function(){
						$state.go("index.city_area");
					},2000);	
				}
			});
		}else{
			//添加城市配置
			return cityAreaService.addCityArea($scope.city_area_form).then(function(response){
				mui.toast(response.message);
				if(response.status == "success"){
					setTimeout(function(){
						$state.go("index.city_area");
					},2000);	
				}
			});
		}
	};
	
	/* 查询一条城市配置数据，用于编辑的时候数据绑定 */
	$scope.oneCityArea = cityAreaService.getOne({pid: $scope.displayCityAreaId}).then(function(res){
		if(res.status == "success"){
			$scope.city_area_form = JSON.parse(res.message);
		}
	});
	
	/* 查询城市配置数据列表，用作父级的选择 */
	$scope.listConditions = {page:1,rows:200};
	$scope.oneCityArea = cityAreaService.getPaging($scope.listConditions).then(function(res){
		if(res.status = "success"){
			$scope.city_area_list = JSON.parse(res.message).list;
		}
	});
	
});