taxationApp.controller('indexController',function ($scope,$state,$stateParams) {
	   var indexData = {};
       $scope.indexData = indexData;
       indexData.user = angular.fromJson(sessionStorage.user);
});