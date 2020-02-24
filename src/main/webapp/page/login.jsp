<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>设备管理服务</title>
        <link href="<%=basePath%>/nifty/images/favicon.ico" rel="shortcut icon" />
        <link rel="stylesheet" href="<%=basePath%>/nifty/login/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=basePath%>/nifty/login/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="<%=basePath%>/nifty/login/css/form-elements.css">
        <link rel="stylesheet" href="<%=basePath%>/nifty/login/css/style.css">
        <link rel="shortcut icon" href="<%=basePath%>/assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=basePath%>/nifty/login/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=basePath%>/nifty/login/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=basePath%>/nifty/login/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="<%=basePath%>/nifty/login/ico/apple-touch-icon-57-precomposed.png">

    </head>
    <body onkeydown="">

        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1>设备管理服务</h1>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>登录</h3>
                            		<p>输入账号密码登录</p>
                            		
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-key"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form id="form" role="form" action="login" method="post" class="login-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="username">登录</label>
			                        	<input type="text" name="userName" placeholder="账号" class="form-username form-control" id="userName" value="${username}">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="userpwd">密码</label>
			                        	<input type="password" name="userPwd" placeholder="密码" class="form-password form-control" id="userPwd">
			                        </div>
			                        <%-- <p class="text-danger">${param.msg}</p> --%>
			                        <p class="text-danger" id="msg">${msg}</p>
			                        <p>
			                        <button type="button" class="btn" onclick="login()">登录</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
<!-- Javascript -->
<script src="<%=basePath%>/nifty/login/js/jquery-1.11.1.min.js"></script>
<script src="<%=basePath%>/nifty/login/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/nifty/login/js/jquery.backstretch.min.js"></script>
<script src="<%=basePath%>/nifty/login/js/scripts.js"></script>

<script type="text/javascript">

$(document).keypress(function(e) { 
	 if(e.which == 13) {  
		 login();
	 }
});

	function login(){
		$.ajax({  
			type : "POST",
			url : "<%=basePath%>/login/login",  
			data : $("#form").serialize(),
			dataType: "json", 
			success : function(data) {
				try{
					if(data.ret != 200){
						$("#msg").html(data.msg);
						return;
					}
					$("#msg").html("");
					location.href="<%=basePath%>/page/index.jsp";
				}catch(error){
					$("#msg").html("登录异常");
				}
			},
			failure:function(){
				$("#msg").html("登录失败");
			}
		});
	}

</script> 


    </body>

</html>