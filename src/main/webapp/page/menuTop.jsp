<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<script src="<%=basePath%>/nifty/js/page.js"></script>
<div id="navbar-container" class="boxed">
	<input type="hidden" name="updatePasswordUrl" id="updatePasswordUrl" value="<%=basePath%>">
	<!--Brand logo & name-->
	<div class="navbar-header">
		<a href="#" class="navbar-brand"> <img
			src="<%=basePath%>/nifty/img/logo.png" alt="Nifty Logo"
			class="brand-icon">
			<div class="brand-title">
				<span class="brand-text">设备管理服务</span>
			</div>
		</a>
	</div>
	<!--End brand logo & name-->

	<div class="navbar-content clearfix">
		<ul class="nav navbar-top-links pull-left">

			<!--Navigation toogle button-->
			<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
			<li class="tgl-menu-btn"><a class="mainnav-toggle" href="#">
					<i class="demo-pli-view-list"></i>
			</a></li>

		</ul>
		<ul class="nav navbar-top-links pull-right">
			   <li id="dropdown-user" class="dropdown">
                            <a href="#" data-toggle="dropdown" class="dropdown-toggle text-right">
                                <span class="pull-right">
                                    <!--<img class="img-circle img-user media-object" src="img/profile-photos/1.png" alt="Profile Picture">-->
                                    <i class="demo-pli-male ic-user"></i>
                                </span>
                                <div class="username hidden-xs">${loginUser.userName}</div>
                            </a>


                            <div class="dropdown-menu dropdown-menu-md dropdown-menu-right panel-default">
                                <!-- Dropdown heading  -->
                                <div class="pad-all bord-btm">
                                    <p class="text-main mar-btm"><span class="text-bold">${loginUser.roleName}</span>
                                   
                                    </p>
                                </div>


                                <!-- User dropdown menu -->
                                <ul class="head-list">
                                    <li>
                                        <a href="javascript:updetePassword()">
                                             	修改密码
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:loginFromNew()">
                                           	    重新登录
                                        </a>
                                    </li>
                             
                                    <!-- <li>
                                        <a href="#">
                                            <span class="label label-success pull-right">New</span>
                                            <i class="demo-pli-gear icon-lg icon-fw"></i> Settings
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="demo-pli-information icon-lg icon-fw"></i> Help
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="demo-pli-computer-secure icon-lg icon-fw"></i> Lock screen
                                        </a>
                                    </li> -->
                                </ul>

                                <!-- Dropdown footer -->
                                <div class="pad-all text-right">
                                    <a href="javascript:loginFromNew()" class="btn btn-primary" >
                                        <i class="demo-pli-unlock"></i> 退出
                                    </a>
                                </div>
                            </div>
                        </li>
		
			<li><a href="#" class="aside-toggle navbar-aside-icon"> <i
					class="pci-ver-dots"></i>
			</a></li>
		</ul>
	</div>
</div>

