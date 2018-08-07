var localhost = 'http://'+location.host+"/"; 
var myApp = angular.module('myApp', []);
var path="'code'";
myApp.controller('baseCtrl', function($scope,$http) {
    $scope.pageData = {
    	sumCount:0,
    	currentPage:1,
    	sumPage:1,
    	pageSize:10
    };
    $scope.query = function(path) {
        $http({
            method: "POST",
            url: localhost+path+"/query",
            data: $scope.pageData
        }).then(function successCallback(response) {
            $scope.infos = response.data.list;
            $scope.currentPage = response.data.currentPage;
            $scope.sumCount = response.data.sumCount;
            $scope.sumPage = response.data.sumPage;
            $scope.msg = response.data.msg;

        }, function errorCallback(response) {
            // 请求失败执行代码
        	$scope.msg="系统异常";
        });
    } 
    $scope.query('userinfo');
    //上一页
    $scope.previous = function(path) {
    	$scope.pageData.currentPage =$scope.pageData.currentPage-1;
   		$scope.query(path);
    } 
    //下一页
    $scope.next = function(path) {
    	$scope.pageData.currentPage = $scope.pageData.currentPage+1;
   		$scope.query();
   } 
    //获取选择的那列数据
    $scope.getModel = function(Count) {
    	$scope.conf =$scope.infos[Count];
    	//$rootScope.conf1=conf;
    	sessionStorage.setItem("username", $scope.conf.id);

    }
    $scope.add = function(path) {
        $http({
            method: "POST",
            url: localhost+path+"/add",
            data: $scope.conf
        }).then(function successCallback(response) {
        	$scope.query(path);
        	$scope.conf="";
        	$scope.msg = response.data.msg;
        }, function errorCallback(response) {
        	$scope.msg="系统异常";
        });
    } 
    $scope.del = function(Count,path) {
    	$scope.conf =$scope.infos[Count];
        $http({
            method: "POST",
            url: localhost+path+"/del",
            data: $scope.conf
        }).then(function successCallback(response) {
        	$scope.query(path);
        	 $scope.msg = response.data.msg;
        }, function errorCallback(response) {
        	$scope.msg="系统异常";
        });
    } 
});
myApp.controller('saveCtrl', function($scope,$http) {
	  $scope.keyid=sessionStorage.getItem("keyid");
	  $scope.add = function(path) {
	        $http({
	            method: "POST",
	            url: localhost+path+"/add",
	            data: $scope.conf
	        }).then(function successCallback(response) {
	        	$scope.query(path);
	        	$scope.conf="";
	        	$scope.msg = response.data.msg;
	        }, function errorCallback(response) {
	        	$scope.msg="系统异常";
	        });
	    } 
	  $scope.selectOne = function(path) {
        $http({
            method: "POST",
            url: localhost+path+"/selectOne",
            data: $scope.keyid
        }).then(function successCallback(response) {
            $scope.conf = response.data.conf;
        }, function errorCallback(response) {
            // 请求失败执行代码
        	$scope.msg="系统异常";
        });
    } 

    $scope.selectOne(path);
     sessionStorage.removeItem("keyid");	   
});