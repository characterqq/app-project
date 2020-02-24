<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

 <script src="<%=basePath%>/nifty/js/personnelPage.js"></script>
</head>
<body contextpath="<%=request.getContextPath()%>">
<!--MAIN NAVIGATION-->
<nav id="mainnav-container">
<!--  -->	<div id="mainnav">
		<!--Menu-->
		<!--================================-->
		<div id="mainnav-menu-wrap">
			<div class="nano">
				<div class="nano-content">
					<!--Profile Widget-->
					<!--================================-->
					<div id="mainnav-profile" class="mainnav-profile">
						<div class="profile-wrap">
							<div class="pad-btm pull-left">
								<img class="img-circle img-sm img-border"
									src="<%=basePath%>/nifty/img/profile-photos/1.png"
									alt="Profile Picture" />
							</div>
							<p></p>
							<div style="margin-left: 70px">
								<p class="mnp-name">${loginUser.userName}</p>
								<a href="#" class="mnp-desc">${loginUser.roleName}</a>
							</div>
						</div>
					</div>
					<p></p>
			<ul id="mainnav-menu" class="list-group">
 
						<!--Menu list item-->
			<li class="active-link"><a href="<%=basePath%>/index/page"><i class="demo-psi-home"></i><span class="menu-title"> <strong>首页提醒</strong></span>
			</a>
			</li>
			<c:if test="${loginUser.roleId==2}">
			<li class="treeview"><a href="#">
            <i class="fa fa-dashboard"></i>
            <span>系统管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="<%=basePath%>/user/page"><i class="fa fa-circle-o"></i><span class="menuInfo">用户管理</span></a></li>
            <li><a href="<%=basePath%>/role/page"><i class="fa fa-circle-o"></i><span class="menuInfo">角色管理</span></a></li>
           <%--  <li><a href="<%=basePath%>/user/page"><i class="fa fa-circle-o"></i> <span class="menuInfo">权限管理</span></a></li> --%>
          </ul>
        </li>
        </c:if>
		 <li class="treeview">
          <a href="<%=basePath%>/equipment/page">
            <i class="fa fa-files-o"></i>
            <span>设备管理</span>
          </a>
        </li>
        <li>
          <a href="<%=basePath%>/dept/page">
            <i class="fa fa-files-o"></i> <span class="menuInfo">部门组织管理</span>
          </a>
        </li> 
         <li>
         <a href="<%=basePath%>/person/page">
            <i class="fa fa-files-o"></i> <span class="menuInfo">人员管理</span>
          </a>
        </li>
       
        <li class="treeview">
          <a href="#">
            <i class="fa fa-pie-chart"></i>
            <span>数据统计分析</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="<%=basePath%>/statistics/device"><i class="fa fa-circle-o"></i><span class="menuInfo">设备信息统计</span></a></li>
            <li><a href="<%=basePath%>/statistics/person"><i class="fa fa-circle-o"></i><span class="menuInfo">人员信息统计</span></a></li>
            <li><a href="<%=basePath%>/log/page"><i class="fa fa-circle-o"></i> <span class="menuInfo">系统日志</span></a></li>
          </ul>
        </li>
					</ul>
				</div>
			</div>
		</div>
		<!--================================-->
		<!--End menu-->

	</div>
</nav>
</body>
</html>
<!--END MAIN NAVIGATION-->