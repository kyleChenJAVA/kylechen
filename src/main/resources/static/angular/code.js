var localhost = 'http://'+location.host+"/"; 
var path="code";
function myCtrl($scope, $http){

	$scope.selectData=["varchar", "int", "date"];
    $scope.TableModel ="";
    $scope.conf ="";
    $scope.list="";
    $scope.msg="";
    	$scope.addCloumn= function() {
        $http({
            method: "POST",
            url: localhost+"code/addColumn",
            data: $scope.TableModel
        }).then(function successCallback(response) {
        	$scope.list = response.data.list;
        	$scope.msg = response.data.msg;
        }, function errorCallback(response) {
        	$scope.msg="系统异常";
        });
    } 
    
}
