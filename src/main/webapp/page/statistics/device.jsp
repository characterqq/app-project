<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>设备服务管理</title>
<link href="<%=basePath%>/images/favicon.ico" rel="shortcut icon" />
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="<%=basePath%>/nifty/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/css/nifty.min.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/css/demo/nifty-demo-icons.min.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/css/demo/nifty-demo.min.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/plugins/ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/plugins/pagination/style/pagination.css" media="screen">
<link rel="stylesheet" href="<%=basePath%>/nifty/plugins/select2/css/select2.min.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/plugins/flavr/flavr.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/plugins/iCheck/all.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/plugins/iziModal/css/iziModal.min.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
<script src="<%=basePath%>/nifty/js/jquery-2.2.4.min.js"></script>
<script src="<%=basePath%>/nifty/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/nifty/js/nifty.min.js"></script>
<script src="<%=basePath%>/nifty/js/demo/nifty-demo.min.js"></script>
<script src="<%=basePath%>/nifty/plugins/pagination/js/jquery.pagination.min.js"></script>
<script src="<%=basePath%>/nifty/plugins/flavr/flavr.min.js"></script>
<script src="<%=basePath%>/nifty/plugins/bootstrap-validator/bootstrapValidator.min.js"></script>
<script src="<%=basePath%>/nifty/plugins/iCheck/icheck.min.js"></script>
<script src="<%=basePath%>/nifty/plugins/select2/js/select2.min.js"></script>
<script src="<%=basePath%>/nifty/plugins/select2/js/i18n/zh-CN.js"></script>
<script src="<%=basePath%>/nifty/js/page.js"></script>
<script src="<%=basePath%>/nifty/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="<%=basePath%>/nifty/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath%>/nifty/plugins/iziModal/js/iziModal.min.js"></script>
<script src="<%=basePath%>/nifty/js/g2.js"></script>

</head>
<body>
<div id="container" class="effect aside-float aside-bright mainnav-lg">
	<!--NAVBAR-->
	<!--===================================================-->
	<header id="navbar">
		<jsp:include page="../menuTop.jsp"></jsp:include>
	</header>
	<div class="boxed">
		<jsp:include page="../menu.jsp"></jsp:include>
		<div id="content-container">
			<div id="page-title">
					<a href="#"> <i
							class="demo-psi-home"></i> <span class="menu-title"> <strong>人员信息统计</strong>
						</span>
					</a>
				   <i class="ion-chevron-right"></i><a href="#"><span class="menu-title"> <strong>图表</strong>
						</span>
					</a>
			</div>
               <div id="page-content">
				<div class="panel">
				    <div class="panel-heading">
				   	 <div class="panel-title">
						  <span class="">
						  <DIV style="margin-bottom: 20px">
						  	<form id="queryForm" action="" method="post" class="">
							<div class="col-md-2">
								<div class="form-group">
									<input type="text" class="form-control" name="starDate" id="starDate"
										placeholder="开始时间">
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<input type="text" class="form-control" name="endDate" id="endDate"
										placeholder="结束时间">
								</div>
							</div>
						
						</form>	
						</DIV>	
							</span>
							<span class="pull-right">
								<button id="refresh" type="button" class="btn btn-primary btn-sm" data-toggle="tooltip" title="刷新">
									<i class="fa fa-refresh"></i>
								</button>
								<button id="search" type="button" class="btn btn-primary btn-sm" data-toggle="tooltip" title="查询" >
									<i class="fa fa-search"></i>
								</button>
								
							</span>
						</div>
				    </div>
				    <div class="panel-body" >
				    		    
					    <div class="">
				      		<div class="col-md-12">
				          <!--饼图  --> 
					    		 <span style="">
					    		<h4>禁用，在线设备占总数百分比</h4>    
					    		<span id="jy" ></span>
					    		 
					    		 </span>
				      		</div>
				      		
				      		<div class="col-md-12" >
				                 <span style="">
					    			<h4>每小时各在线设备</h4>    
					    		 <span id="zx" ></span>	    		 
					    		 </span>
				     		 </div>
			   				 <p>
					    </div>
					 </div>
				</div>
             </div>
		 <footer id="footer">
			<div class="pull-right pad-rgt">
				<b>Version</b> 1.0.0
			</div>
			<strong class="pad-lft">Copyright &copy; 2016 <a href="#">Gable</a>.
			</strong> All rights reserved.
		 </footer>
	</div>
</div>

<script type="text/javascript">


//初始化时间控件
$("#starDate").datetimepicker({
	autoclose: true,
	todayHighlight:true,
	language:'zh-CN',
	forceParse:true,
	format:'yyyy-mm-dd',
	todayBtn:true,
	minView:2,
	}).on('hide',function(e) {
	$('#form').data('bootstrapValidator')  
		.updateStatus('starDate', 'NOT_VALIDATED',null)  
		.validateField('starDate'); 
});
$("#endDate").datetimepicker({
	autoclose: true,
	todayHighlight:true,
	language:'zh-CN',
	forceParse:true,
	format:'yyyy-mm-dd',
	todayBtn:true,
	minView:2,
	}).on('hide',function(e) {
	$('#form').data('bootstrapValidator')  
		.updateStatus('endDate', 'NOT_VALIDATED',null)  
		.validateField('endDate'); 
});
charts();
zxt();
menuCss("设备信息统计");



//搜索查询
$("#search").click(function (){
	charts();
	zxt();
});
//刷新
$("#refresh").click(function (){
	charts();
	zxt();
});

/**
 * 画饼图的方法
 * @param data
 */

function charts(){
	 $.ajax({
         type: "GET",
         url: "<%=basePath%>/statistics/devicedata",//请求链接
         dataType: "json",
         data:{"starDate":$("#starDate").val(), "endDate":$("#endDate").val()},
         success: function(date){
        	 $("#jy").empty();
        	var data = eval('(' +date.data+ ')');//将json格式的字符串转换成json类型
        	 var Stat = G2.Stat;
	            var chart = new G2.Chart({
	              id: 'jy',
	              forceFit: true,
	               animate: true,
	              height: 450,
	              width:450
	            });
	            chart.source(data);
	            // 重要：绘制饼图时，必须声明 theta 坐标系
	            chart.coord('theta', {
	              radius: 0.8 // 设置饼图的大小
	            });
	            chart.legend('name', {
	              position: 'bottom',
	              itemWrap: true,
	              formatter: function(val) {
	            	  for(var i = 0, len = data.length; i < len; i++) {
	                      var obj = data[i];
	                      if (obj.name === val) {
	                        return val + ': ' + obj.value + '%'; 
	                      }
	                    }
	              }
	            });
	            chart.tooltip({
	              title: null,
	              map: {
	                value: 'value'
	              }
	            });
	            chart.intervalStack()
	              .position(Stat.summary.percent('value'))
	              .color('name')
	              .label('name*..percent',function(name, percent){
	              percent = (percent * 100).toFixed(2) + '%';
	              return name + ' ' + percent;
	            });
	            chart.render();
	            // 设置默认选中
	            var geom = chart.getGeoms()[0]; // 获取所有的图形
	            var items = geom.getData(); // 获取图形对应的数据
		            geom.setSelected(items[1]); // 设置选中
         }
  }) 
  
}
function zxt(){
	$.ajax({
		url:"<%=basePath%>/statistics/onLineDevice",//请求链接,
		type:"get",
		dataType:"json",
		data:{"starDate":$("#starDate").val(), "endDate":$("#endDate").val()},
		success:function(d){
			$("#zx").html("");
		    var data=  eval('(' + d.data + ')');
	                  var chart = new G2.Chart({
	                    id: 'zx',
	                    forceFit: true,
	                    height: 450,
	                  
	                  });
	                  chart.source(data, {
	                    name: {
	                      alias: '小时',
	                      range: [0, 1]
	                    },
	                    value: {
	                      alias: '在线数量'
	                    }
	                  });
	                  chart.line().position('name*value').size(2);
	                  chart.render();
		
		}
	});
}
</script>

</body>



</html>
