<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>设备管理服务</title>
<link href="<%=basePath%>/images/favicon.ico" rel="shortcut icon" />
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="<%=basePath%>/nifty/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/css/nifty.min.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/css/demo/nifty-demo-icons.min.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/css/demo/nifty-demo.min.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/plugins/pace/pace.min.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/plugins/ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/plugins/iziModal/css/iziModal.min.css">
<link rel="stylesheet" href="<%=basePath%>/nifty/plugins/flavr/flavr.css">

<script src="<%=basePath%>/nifty/js/jquery-2.2.4.min.js"></script>
<script src="<%=basePath%>/nifty/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/nifty/js/nifty.min.js"></script>
<script src="<%=basePath%>/nifty/js/demo/nifty-demo.min.js"></script>
<script src="<%=basePath%>/nifty/plugins/flavr/flavr.min.js"></script>
<script src="<%=basePath%>/nifty/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<script src="<%=basePath%>/nifty/plugins/iziModal/js/iziModal.min.js"></script>
<script src="<%=basePath%>/nifty/js/page.js"></script>
<script src="<%=basePath%>/nifty/plugins/select2/js/select2.min.js"></script>
<script src="<%=basePath%>/nifty/plugins/select2/js/i18n/zh-CN.js"></script>

</head>
<!-- ADD THE CLASS sidedar-collapse TO HIDE THE SIDEBAR PRIOR TO LOADING THE SITE -->
<body>
	<div id="container" class="effect aside-float aside-bright mainnav-lg">
		<!--NAVBAR-->
		<!--===================================================-->
		<header id="navbar">
			<jsp:include page="menuTop.jsp"></jsp:include>
		</header>
		<div class="boxed">
			<!--MAIN NAVIGATION-->
			<jsp:include page="menu.jsp"></jsp:include>

			<!--CONTENT CONTAINER-->
			<!--===================================================-->
			<div id="content-container">
				<div id="page-title">
					<h2 class="page-header text-overflow pull-left">信息提醒</h2>

					<!--Searchbox-->
					<div class="pull-right pad-all">
						<a href="#"> <i
								class="demo-psi-home"></i> <span class="menu-title"> <strong>首页</strong>
							</span>
						</a>
					   <i class="ion-chevron-right"></i><a href="#"><span class="menu-title"> <strong>提醒</strong>
							</span>    
						</a>
					</div>
				</div>
				
				<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
				<!--End page title-->
				<div id="page-content">
					<div class="row">
						<div class="col-sm-3 col-lg-3">
							<!--Sparkline Area Chart-->
							<div class="panel panel-success panel-colorful">
								<div class="pad-all">
									<p class="text-lg">设备总台数</p>
									<p class="text-sm text-semibold"><span id="sumE"></span></p>
								</div>
								<div class="pad-all text-center"></div>
							</div>
						</div>
						<div class="col-sm-3 col-lg-3">
							<!--Sparkline Area Chart-->
							<div class="panel panel-info panel-colorful">
								<div class="pad-all">
									<p class="text-lg">人员总数</p>
									<p class="text-sm text-semibold"><span id="sumP"></span></p>
								</div>
								<div class="pad-all text-center"></div>
							</div>
						</div>
						<div class="col-sm-3 col-lg-3">
							<!--Sparkline Area Chart-->
							<div class="panel panel-warning panel-colorful">
								<div class="pad-all">
									<p class="text-lg">当天在线总设备</p>
									<p class="text-sm text-semibold"><span id="countE"></span></p>
								</div>
								<div class="pad-all text-center"></div>
							</div>
						</div>
						<div class="col-sm-3 col-lg-3">
							<!--Sparkline Area Chart-->
							<div class="panel panel-purple panel-colorful">
								<div class="pad-all">
									<p class="text-lg">当天在线总人数</p>
									<p class="text-sm text-semibold"><span id="countP"></span></p>
								</div>
								<div class="pad-all text-center"></div>
							</div>
						</div>
					</div>
				</div>
			
			</div>
			<!-- page-content -->
			 	<footer id="footer">
	
	            <div class="pull-right pad-rgt">
	      			<b>Version</b> 1.0.0
	    		</div>
	    			<strong class="pad-lft">Copyright &copy; 2016 <a href="#">Gable</a>.</strong> All rights reserved.
        	</footer>
		</div>
	</div>
	<script type="text/javascript">
	

	//直接加载
	$(function() {
		$.ajax({  
			type : "POST",
			url : "<%=basePath%>/equipment/sum",  
			dataType: "json", 
			success : function(data) {
				if(data.data!=null){
				$("#sumE").text(data.data);
				
				}
			},
		
		});
		$.ajax({  
			type : "POST",
			url : "<%=basePath%>/equipment/sumCount",  
			dataType: "json", 
			success : function(data) {
				if(data.data!=null){
				$("#countE").text(data.data);
				$("#countP").text(data.data);
				}
			},
		
		});
		$.ajax({  
			type : "POST",
			url : "<%=basePath%>/person/sum",  
			dataType: "json", 
			success : function(data) {
				if(data.data!=null){
				$("#sumP").text(data.data);
				}
			},
		
		});
		
	});
	</script>
</body>



</html>
