var localhost = 'http://'+location.host; 
var baseurl=localhost;
function myCtrl($scope, $http){
	
	$scope.msg='';
    $scope.loginRequest = {
        	username:'',
        	userpass:''    
    };
    $scope.login = function() {
    $http({
        method: "POST",
        url: baseurl+"/app/login",
        data: $scope.loginRequest
    }).then(function successCallback(response) {
        $scope.msg = response.data.msg;
        if(response.data.msg=="超级管理员"){ 
    		window.location=baseurl+"/adminIndex.html";
        }
    }, function errorCallback(response) {
    	$scope.msg="系统异常"
    });
    }
}