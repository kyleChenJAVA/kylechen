<!DOCTYPE html>
<html  ng-app="">
  
  <head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="../css/font.css">
	<link rel="stylesheet" href="../css/xadmin.css">
	<script type="text/javascript" src="../js/jquery-2.0.3.min.js"></script>
	<script type="text/javascript" src="../lib/layui/layui.js"
	charset="utf-8"></script>
	<script type="text/javascript" src="../js/xadmin.js"></script>
	<script src="../angular/angular.min.js"></script>
<	<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
	<!--[if lt IE 9]>
     <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
     <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script>
	var localhost = 'http://' + location.host + "/";
	var path = '${tableModel}';
	function baseCtrl($scope, $http) {

		$scope.pageData = {
			sumCount : 0,
			currentPage : 1,
			sumPage : 1,
			pageSize : 10
		};
		$scope.query = function() {
			$http({
				method : "POST",
				url : localhost + path + "/query",
				data : $scope.pageData
			}).then(function successCallback(response) {
				$scope.infos = response.data.list;
				$scope.currentPage = response.data.currentPage;
				$scope.sumCount = response.data.sumCount;
				$scope.sumPage = response.data.sumPage;
				$scope.msg = response.data.msg;

			}, function errorCallback(response) {
				// 请求失败执行代码
				$scope.msg = "系统异常";
			});
		}
		$scope.query();
		//上一页
		$scope.previous = function() {
			if($scope.pageData.currentPage=='1'){
				$scope.pageData.currentPage = '1' ;	
			}else{
				$scope.pageData.currentPage = $scope.pageData.currentPage - 1;	
			}
			$scope.query();
		}
		//下一页
		$scope.next = function() {
			if($scope.pageData.currentPage==$scope.sumPage){
				$scope.pageData.currentPage = $scope.currentPage ;	
			}else{
				$scope.pageData.currentPage = $scope.pageData.currentPage + 1;	
			}
			$scope.query();
		}
		//获取选择的那列数据
		$scope.getModel = function(Count) {
			$scope.conf = $scope.infos[Count];
			sessionStorage.setItem("keyid", $scope.conf.id);
		}
		$scope.del = function(Count) {
			$scope.conf1 = $scope.infos[Count];
			$http({
				method : "POST",
				url : localhost + path+"/del",
				data : $scope.conf1
			}).then(function successCallback(response) {
				$scope.query();
				$scope.msg = response.data.msg;
			}, function errorCallback(response) {
				$scope.message = "系统异常"
			});
		}
	}
</script>
  </head>
  
  <body class="layui-anim layui-anim-up"  ng-controller="baseCtrl">
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a>
          <cite></cite></a>
      </span>
 <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a> 
    </div>
    <div class="x-body">
      <div class="layui-row">
         <form class="layui-form layui-col-md12 x-so">
<!--           <input class="layui-input" placeholder="开始日" name="start" id="start">
          <input class="layui-input" placeholder="截止日" name="end" id="end">
          <input type="text" name="username"  placeholder="请输入用户名" autocomplete="off" class="layui-input"> -->
          <button class="layui-btn"  lay-submit=""  ng-click="query()" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form> 
      </div>
      <xblock>
        <button class="layui-btn" onclick="x_admin_show('添加','./${tableModel}-edit.html',600,400)"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">	总共数据 {{sumCount}} 条。共 {{sumPage}} 页，当前 {{currentPage}} 页。</span>
      </xblock>
      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <#list model as obj1>       
            <th>${obj1.columnMemo}</th>
           </#list>
            <th>操作</th>
           </tr>
        </thead>
        <tbody ng-repeat="x in infos">
          <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
            </td>
            <#list model as obj2>      
            <td>{{x.${obj2.columnName}}}</td>
             </#list>
            <td class="td-manage">
            
              <a title="编辑" ng-click="getModel($index)"   onclick="x_admin_show('编辑','./${tableModel}-edit.html',600,400)" href="javascript:;">
                <i class="layui-icon">&#xe642;</i>
              </a>
            
              <a title="删除"   ng-click="del($index)" href="javascript:;">
                <i class="layui-icon">&#xe640;</i>
              </a>
            </td>
          </tr>

        </tbody>
      </table>
      <div class="page">
        <div>
          <a class="prev" href="" ng-click="previous()">&lt;&lt;</a>
          <span class="current">{{currentPage}}</span>         
          <a class="next" href="" ng-click="next()" >&gt;&gt;</a>
        </div>
      </div>

    </div>
    <script>
      layui.use('laydate', function(){
        var laydate = layui.laydate;
        
        //执行一个laydate实例
        laydate.render({
          elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
          elem: '#end' //指定元素
        });
      });

       /*用户-停用*/
      function member_stop(obj,id){
          layer.confirm('确认要停用吗？',function(index){

              if($(obj).attr('title')=='启用'){

                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

              }else{
                $(obj).attr('title','启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!',{icon: 5,time:1000});
              }
              
          });
      }

      /*用户-删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              $(obj).parents("tr").remove();
              layer.msg('已删除!',{icon:1,time:1000});
          });
      }



      function delAll (argument) {

        var data = tableCheck.getData();
  
        layer.confirm('确认要删除吗？'+data,function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
/*             $(".layui-form-checked").not('.header').parents('tr').remove();
 */        });
      }
    </script>

  </body>

</html>