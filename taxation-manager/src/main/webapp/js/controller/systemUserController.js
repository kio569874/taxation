/**
 * @description：
 * @author：JustFresh
 * @time：2017-07-26 14:14
 */
myApp.controller('SystemUserController',function ($http,$rootScope,$scope,systemUserService,$location,$state) {

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
    	sort: 'id',
    	page: 1,
    	rows: 10,
        perPageOptions: [5, 10, 20, 50]
    };
    
    /* 页面加载时候默认的分页数据信息绑定 */
    $scope.pagingInfo = {};
    $scope.pagingInfo = systemUserService.querySysTemUserList(1,10,10).then(function (response) {
		if(response.status == "success"){
			$scope.pagingInfo = JSON.parse(response.message);
		}
    });
    
    /* 删除一条系统用户数据 */
	$scope.remove = function(id){
		mui.confirm('确认要删除系统用户吗？', '警告信息', ['确认','取消'], function(e) {
			if(e.index == 0) {
				//确认要删除
				return systemUserService.remove(id).then(function(res){
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
					return systemUserService.remove(ids).then(function(res){
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

myApp.controller("SystemUserMngController",function($http,$rootScope,$scope,systemUserService,$location,$stateParams,$state){

	$rootScope.activeMenu = "index.system_user_mng";
	$scope.displaySystemUserId = $stateParams.id == undefined ? null : $stateParams.id;

	/* 查询一条系统用户数据，用于编辑的时候数据绑定 */
	$scope.getOneSystemUser = systemUserService.getOneSystemUser({id:$scope.displaySystemUserId}).then(function(res){
		$scope.system_user_form = JSON.parse(res.message);
	});

	/* 增加/编辑处处理表单提交数据部分 */
	$scope.submitSystemUserForm = function(){
		
		//表单校验
		if(checkSystemUserForm($scope.system_user_form)){
			if($scope.system_user_form != null && $scope.system_user_form.id > 0){
				//编辑系统用户
				return systemUserService.editSystemUser($scope.system_user_form).then(function(res){
					mui.toast(res.message);
					if(res.status == "success"){
						setTimeout(function(){
							$state.go("index.system_user");
						},2000);
					}
				});
			}else{
				//添加系统用户
				return systemUserService.addSystemUser($scope.system_user_form).then(function(res){
					mui.toast(res.message);
					if(res.status == "success"){
						setTimeout(function(){
							$state.go("index.system_user");
						},2000);
					}
				});
			}
		}
	};

});

//公用JS方法部分
var checkSystemUserForm = function(myForm){
	if(myForm == null || myForm.userCode == null || myForm.userCode == ""){
		mui.toast("请输入用户编码！！！");
		return false;
	}
	if(myForm == null || myForm.userPassword == null || myForm.userPassword == ""){
		mui.toast("请输入用户密码！！！");
		return false;
	}
	return true;
	
}