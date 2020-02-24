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
							class="demo-psi-home"></i> <span class="menu-title"> <strong>设备管理</strong>
						</span>
					</a>
				   <i class="ion-chevron-right"></i><a href="#"><span class="menu-title"> <strong>设备</strong>
						</span>
					</a>
			</div>
               <div id="page-content">
				<div class="panel">
				    <div class="panel-heading">
				   	 <div class="panel-title">
						  <span class="">
						  		<c:if test="${loginUser.roleId==1 || loginUser.roleId==2}">
								<button id="add" type="button" class="btn btn-primary btn-sm" title='添加' data-toggle="tooltip">
									<i class="fa fa-plus"></i>
								</button>
								</c:if>
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
				    	<form id="queryForm" action="" method="post" class="">
							<div class="col-md-3">
								<div class="form-group">
									<input type="text" class="form-control" name="equipmentname" id="equipmentname"
										placeholder="设备名称">
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<input type="text" class="form-control" name="equipmentno" id="equipmentno"
										placeholder="设备号码">
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<input type="text" class="form-control" name="starDate" id="starDate"
										placeholder="开始时间">
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<input type="text" class="form-control" name="endDate" id="endDate"
										placeholder="结束时间">
								</div>
							</div>
							
							<div class="col-md-3">
								<div class="form-group" >
									<select id="pids" name ="pids"style="width: 100%" >
									</select>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group" >
									<select id="equipmentsys" name ="equipmentsys"style="width: 100%" >
									<option value="Android">Android</option>
									<option value="Iphone">Iphone</option>
									</select>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group" >
									<select id="statu" name ="statu" style="width: 100%" >
									<option value="0">在线</option>
									<option value="1">离线</option>
									<option value="2">禁用</option>
									<option value="3">损坏</option>
									</select>
								</div>
							</div>
						</form>			    
					    <div class=" panel-bodytable-responsive no-padding">
					        <table class="table table-bordered table-hover" id="table">
					      
					        </table>
					        <p>
				         <div class="box-footer clearfix">
		      				<div class="M-box3 text-center"></div>   
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
<input type="hidden"  id="userName1" name="userName1" value="${loginUser.userName}">
<input type="hidden"  id="roleId1" name="roleId1" value="${loginUser.roleId}">
<div id="modal"style="display:none">
	<div class="row">
		<div class="col-md-12">
			<div class="box-body" >
			<p>
				<form class="form-horizontal " id="form" method="post">
					<div class="form-group hide">
					<label for="id" class="col-md-3 control-label text-main">设备id&nbsp;&nbsp;</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="eid" name="eid">
							</div>
					</div>
					<div class="form-group ">
						<label for="number" class="col-sm-3 control-label text-main">设备号码<span class="text-danger "> *</span></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="equipmentNo" name="equipmentNo" >
						</div>
					</div>
					<div class="form-group ">
						<label for="name" class="col-sm-3 control-label text-main">设备名称<span class="text-danger"> *</span></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="equipmentName" name="equipmentName" placeholder="">
						</div>
					</div>
					<div class="form-group  ">
						<label for="name" class="col-sm-3 control-label text-main">设备系统<span class="text-danger"> *</span></label>
						<div class="col-sm-8">
							<select id="equipmentSys" name="equipmentSys" style="width: 100%" >
									<option value="Android">Android</option>
									<option value="Iphone">iphone</option>
							</select>
						</div>
					</div>
					<div class="form-group ">
						<label for="name" class="col-sm-3 control-label text-main">设备类型<span class="text-danger"> *</span></label>
						<div class="col-sm-8">
							<select id="equipmentType" name="equipmentType" style="width: 100%" >
									<option value="公用">公用</option>
									<option value="私用">私用</option>
							</select>
						</div>
					</div>
					<div class="form-group ">
						<label for="name" class="col-sm-3 control-label text-main">编号<span class="text-danger"> *</span></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="number" name="number" placeholder="">
						</div>
					</div>
					<div class="form-group  " id="ry">
						<label for="pid" class="col-sm-3 control-label text-main">人员<span class="text-danger"> *</span></label>
						<div class="col-sm-8">
							<select id="pid" name="pid" style="width: 100%" onchange="querypolice(this)">
								
							</select>
						</div>
					</div>
					<div class="form-group  "id="zh">
						<label for="userName" class="col-sm-3 control-label text-main">账户</label>
						<div class="col-sm-8">
								<input type="text" class="form-control" id="userName" name="userName" placeholder=""readonly="readonly" >
						</div>
					</div>
					<div class="form-group"id ="pwd">
						<label for="pid" class="col-sm-3 control-label text-main">密码</label>
						<div class="col-sm-8">
							<input type="password" class="form-control" id="userPwd" name="userPwd" placeholder="">
						</div>
					</div>
					<div class="form-group  "id="pwd2">
						<label for="pid" class="col-sm-3 control-label text-main">确认密码</label>
						<div class="col-sm-8">
							<input type="password" class="form-control" id="userPwd2" name="userPwd2" placeholder="">
						</div>
					</div>
					
					
				</form>
			</div>
			<div class="box-footer text-right">
				<button id="close" data-iziModal-close class="btn btn-default" style="margin-right:5px" >取消</button>
				<button id="save" data-loading-text="保存中..."style="margin-right:5px" type="button" class="btn btn-primary" >保存</button>
			</div>
			<p>
		</div>
	</div>
</div>
<div id="modal2"style="display:none">
	<div class="row">
		<div class="col-md-12">
			<div class="box-body" >
			<p>
			<form class="form-horizontal ">
			  <div class="form-group hide">
				<label for="id" class="col-md-3 control-label text-main">设备id&nbsp;&nbsp;</label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="id" name="id">
						</div>
				</div>
					<div class="form-group  ">
						<label for="name" class="col-sm-3 control-label text-main">设备状态</label>
						<div class="col-sm-8">
							<select id="status1" name="status1"style="width: 100%" >
							<option value="2">禁用</option>
							<option value="3">损坏</option>
							</select>
						</div>
					</div>
			</div>
			</form>
			<div class="box-footer text-right">
				<button id="close" data-iziModal-close class="btn btn-default" style="margin-right:5px" >取消</button>
				<button id="save2" data-loading-text="保存中..."style="margin-right:5px" type="button" class="btn btn-primary" >保存</button>
			</div>
			<p>
		</div>
	</div>
</div>
<div id="modal3"style="display:none">
	<div class="row">
		<div class="col-md-12">
			<div class="box-body" >
			<p>
			<form class="form-horizontal ">
			  <div class="form-group hide">
				<label for="id" class="col-md-3 control-label text-main">设备id&nbsp;&nbsp;</label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="id1" name="id1">
						</div>
				</div>
				<div class="form-group  "id="remarks">
						<label for="pid" class="col-sm-3 control-label text-main">更换原因</label>
						<div class="col-sm-8">
							<textarea class="form-control" id="remark" name="remark"></textarea>
						</div>
					</div>
			
			</div>
			</form>
			<div class="box-footer text-right">
				<button id="close" data-iziModal-close class="btn btn-default" style="margin-right:5px" >取消</button>
				<button id="save3" data-loading-text="保存中..."style="margin-right:5px" type="button" class="btn btn-primary" >保存</button>
			</div>
			<p>
		</div>
	</div>
</div>
<script type="text/javascript">
//初始化弹出框
$("#modal").iziModal({
	title: '编辑设备',
    headerColor: '#00c0ef',
    theme: '',  // light
    attached: '', // bottom, top
    icon: null,
    iconText: null,
    iconColor: '',
    rtl: false,
    width: 600,
    padding: 0,
    radius: 3,
    zindex: 1040,
    iframe: false,
    iframeHeight: 400,
    iframeURL: null,
    focusInput: false,
    group: '',
    loop: false,
    navigateCaption: true,
    navigateArrows: true, // closeToModal, closeScreenEdge
    history: true,
    restoreDefaultContent: false,
    autoOpen: false, // Boolean, Number
    bodyOverflow: true,
    fullscreen: true,
    openFullscreen: false,
    closeOnEscape: false,
    overlay: true,
    overlayClose: false,
    overlayColor: 'rgba(0, 0, 0, 0.4)',
    timeout: false,
    timeoutProgressbar: false,
    pauseOnHover: false,
    timeoutProgressbarColor: 'rgba(255,255,255,0.5)',
    transitionIn: 'comingIn',
    transitionOut: 'comingOut',
    transitionInOverlay: 'fadeIn',
    transitionOutOverlay: 'fadeOut',
    onFullscreen: function(){},
    onResize: function(){},
    onOpening: function(){
    },
    onOpened: function(){
    	 validator(); 
    },
    onClosing: function(){
    },
    onClosed: function(){
    	$('#form')[0].reset();
    	$("#userId").val("");
    	$("#userId").select2({ language:'zh-CN', placeholder:"请选择仓库", allowClear:true});
    	$("#form").data('bootstrapValidator').destroy();
    	
    }
});
$("#modal2").iziModal({
	title: '修改状态',
    headerColor: '#00c0ef',
    theme: '',  // light
    attached: '', // bottom, top
    icon: null,
    iconText: null,
    iconColor: '',
    rtl: false,
    width: 600,
    padding: 0,
    radius: 3,
    zindex: 1040,
    iframe: false,
    iframeHeight: 400,
    iframeURL: null,
    focusInput: false,
    group: '',
    loop: false,
    navigateCaption: true,
    navigateArrows: true, // closeToModal, closeScreenEdge
    history: true,
    restoreDefaultContent: false,
    autoOpen: false, // Boolean, Number
    bodyOverflow: true,
    fullscreen: true,
    openFullscreen: false,
    closeOnEscape: false,
    overlay: true,
    overlayClose: false,
    overlayColor: 'rgba(0, 0, 0, 0.4)',
    timeout: false,
    timeoutProgressbar: false,
    pauseOnHover: false,
    timeoutProgressbarColor: 'rgba(255,255,255,0.5)',
    transitionIn: 'comingIn',
    transitionOut: 'comingOut',
    transitionInOverlay: 'fadeIn',
    transitionOutOverlay: 'fadeOut',
    onFullscreen: function(){},
    onResize: function(){},
    onOpening: function(){
    },
    onOpened: function(){
    	
    },
    onClosing: function(){
    },
    onClosed: function(){
    	
    }
});
$("#modal3").iziModal({
	title: '设备更换申请',
    headerColor: '#00c0ef',
    theme: '',  // light
    attached: '', // bottom, top
    icon: null,
    iconText: null,
    iconColor: '',
    rtl: false,
    width: 600,
    padding: 0,
    radius: 3,
    zindex: 1040,
    iframe: false,
    iframeHeight: 400,
    iframeURL: null,
    focusInput: false,
    group: '',
    loop: false,
    navigateCaption: true,
    navigateArrows: true, // closeToModal, closeScreenEdge
    history: true,
    restoreDefaultContent: false,
    autoOpen: false, // Boolean, Number
    bodyOverflow: true,
    fullscreen: true,
    openFullscreen: false,
    closeOnEscape: false,
    overlay: true,
    overlayClose: false,
    overlayColor: 'rgba(0, 0, 0, 0.4)',
    timeout: false,
    timeoutProgressbar: false,
    pauseOnHover: false,
    timeoutProgressbarColor: 'rgba(255,255,255,0.5)',
    transitionIn: 'comingIn',
    transitionOut: 'comingOut',
    transitionInOverlay: 'fadeIn',
    transitionOutOverlay: 'fadeOut',
    onFullscreen: function(){},
    onResize: function(){},
    onOpening: function(){
    },
    onOpened: function(){
    	
    },
    onClosing: function(){
    },
    onClosed: function(){
    	$('#form')[0].reset();
    }
});

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
 function validator(){
	$('#form').bootstrapValidator({
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        live: 'enabled',
        message: '非有效值',
        fields: {
        	equipmentNo: {
                validators: {
                	notEmpty: {
                        message: '不能为空'
                    },
                    stringLength: {
                        max: 18,
                        message: '最多输入18个字符'
                    }
                }
	        }, 
	        equipmentName: {
                validators: {
                	notEmpty: {
                        message: '不能为空'
                    },
                    stringLength: {
                        max: 18,
                        message: '最多输入20个字符'
                    }
                }
	        }, 
	        equipmentSys: {
                validators: {
                	notEmpty: {
                        message: '不能为空'
                    }
                }
	        }, 
	        equipmentType: {
                validators: {
                	notEmpty: {
                        message: '不能为空'
                    }
                }
	        }, 
	        number: {
                validators: {
                	notEmpty: {
                        message: '不能为空'
                    },
                    stringLength: {
                        max: 18,
                        message: '最多输入20个字符'
                    }
                }
	        }, 
	        pid: {
                validators: {
                	notEmpty: {
                        message: '不能为空'
                    }
                }
	        }, 
        	userName: {
                validators: {
                	notEmpty: {
                        message: '不能为空'
                    },
                    stringLength: {
                        max: 18,
                        message: '最多输入18个字符'
                    }
                }
	        }, 
	        userPwd:{
	        	validators: {
	            	notEmpty: {
	                    message: '不能为空'
	                },
	                stringLength:{
	                   min: 6,
	                    max: 16,
	                    message: '最多输入16个字符'
	                },
	                
	            }
	        },
	        userPwd2:{
	        	validators: {
	        		notEmpty: {
	                    message: '不能为空'
	                },
	                identical:{
		            	field: 'userPwd',
	                    message: '两次密码不相同'
		            }
	            }
	        }
   	   	}
    });
} 
menuCss("设备管理");
//初始化下拉框
$("#pid").select2({ language:'zh-CN', placeholder:"请选择人员", allowClear:true});
$("#pids").select2({ language:'zh-CN', placeholder:"请选择人员", allowClear:true});
$("#statu").select2({ language:'zh-CN', placeholder:"请选择状态", allowClear:true});
$("#status").select2({ language:'zh-CN', placeholder:"请选择状态", allowClear:true});
$("#status1").select2({ language:'zh-CN', placeholder:"请选择状态", allowClear:true});
$("#equipmentSys").select2({ language:'zh-CN', placeholder:"请选择系统", allowClear:true});
$("#equipmentsys").select2({ language:'zh-CN', placeholder:"请选择系统", allowClear:true});
$("#equipmentType").select2({ language:'zh-CN', placeholder:"请选择系统", allowClear:true});
$("#pid").val(null).trigger("change");
$("#pids").val(null).trigger("change");
$("#statu").val(null).trigger("change");
$("#status").val(null).trigger("change");
$("#status1").val(null).trigger("change");
$("#equipmentSys").val(null).trigger("change");
$("#equipmentsys").val(null).trigger("change");
$("#equipmentType").val(null).trigger("change");
list(0,10);
var userName1=$("#userName1").val();
var roleId1=$("#roleId1").val();
//查询设备列表
function list(start,limit){
	$.ajax({  
		type : "post",
		url : "<%=basePath%>/equipment/list",  
		data : {
			"start":start,
			"limit":limit,
			"equipmentName":$("#equipmentname").val(),
			"equipmentNo":$("#equipmentno").val(),
			"starDate":$("#starDate").val(),
			"endDate":$("#endDate").val(),
			"pid":$("#pids").val(),
			"equipmentSys":$("#equipmentsys").val(),
			"equipmentStatus":$("#statu").val()
		},
		dataType: "json", 
		success : function(data) {
			try{
				if(data.ret != 200){
					return;
				}
				//分页
				$("#table").html('<tr >'
						+'<th  class="text-main" style="width: 35px">&nbsp</th>'
						+'<th class=" text-main text-center ">操作</th>'
						+'<th class="text-main ">设备号码</th>'
						+'<th class="text-main ">设备名称</th>'
						+'<th class="text-main">设备状态</th>'
						+'<th class="text-main">设备系统</th>'
						+'<th class="text-main">设备类型</th>'
						+'<th class="text-main">编号</th>'
						+'<th class="text-main">时间</th>'
						+'<th class="text-main">人员警号</th>'
						+'<th class="text-main">人员名称</th>'
						+'<th class="text-main">状态</th>'
					+'</tr>');//清空表格内容内容
				Page(data.data.total,limit,(start/limit+1));
				
				var list = data.data.list;
				if(list.length==0){
					$("#table").append("<tr><td class='text-center text-primary' colspan='11' >无记录</td></tr>");
				}else{
					
					$.each(list,function(i, item){
						var str="";
						var str2="";
						var buton="";
						if(item.equipmentStatus==0){
							str="在线";
						}else if(item.equipmentStatus==1){
							str="离线";
						}
						else if(item.equipmentStatus==2){
							str="禁用";
						}
						else if(item.equipmentStatus==3){
							str="损坏";
							if(item.status==0){
								buton="<button  style='margin-right:3px' id='update' type='button'class='btn btn-info btn-xs' data-toggle='tooltip'title='变更'onclick='change("+item.eid+")'>设备变换</button>";
							
							}else if(item.status==1){
							 	 if(roleId1==1 || roleId1==2){
								buton="<button  style='margin-right:3px' type='button'class='btn btn-info btn-xs' data-toggle='tooltip'title='变更'onclick='examine("+item.eid+")'>设备审核</button>";
								} 
								str2="申请中";
							}else if(item.status==2){
								str2="申请通过";
								buton="<button  style='margin-right:3px' type='button'class='btn btn-info btn-xs' data-toggle='tooltip'title='变更'onclick='replace("+item.eid+")'>设备更换</button>";;
							}else{
								str2=="";
								buton="";
							}
						
						}
						
 						$("#table").append("<tr  >"
 								+ "<td class='' style='width: 50px'>" + (start+i+1)  + "</td>" 
 								+"<td class=' text-main text-center' style='width: 180px'>"
 								
								/* +"<button  style='margin-right:3px' id='update' type='button'class='btn btn-info btn-xs' data-toggle='tooltip'title='修改'onclick='get("+item.eid+")'>"
								+"修改"
								+"</button>" */
								+"<button  style='margin-right:3px' id='update' type='button'class='btn btn-info btn-xs' data-toggle='tooltip'title='状态'onclick='del("+item.eid+")'>"
								+str
								+"</button>"
								+buton
								+"</td>"
								+ "<td class='text-main' style='width: 50px'>" + (item.equipmentNo ==null?"":item.equipmentNo)+ "</td>" 
								+ "<td class=' text-main' style='width:100px'>"+(item.equipmentName ==null?"":item.equipmentName)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+str+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.equipmentSys ==null?"":item.equipmentSys)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.equipmentType ==null?"":item.equipmentType)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.number ==null?"":item.number)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.createDate ==null?"":item.createDate)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.policeNo ==null?"":item.policeNo)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.name ==null?"":item.name)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+str2+"</td>"
			                    + "</tr>"); 
					});
				}
				 if($("#pids").html() == null || $("#pids").html().trim()== ""){
						var peresonlist = data.data.peresonlist;
						var str="";
						for(var i=0;i<peresonlist.length;i++){
								str = str + "<option value='"+peresonlist[i].pid+"'>"+peresonlist[i].name+"("+peresonlist[i].policeNo+")"+"</option>";
						}
						$("#pids").html(str);
						$("#pid").html(str);
						$("#pids").val(null).trigger("change");
						$("#pid").val(null).trigger("change");
					}
			}catch(error){
				$("#table").append("<tr><td class='text-center text-danger' colspan='11' >数据错误</td></tr>");
			}
		},
		failure:function(){
			$("#table").html();//清空表格内容内容
			$("#table").html('<tr >'
					+'<th  class="text-main" style="width: 35px">&nbsp</th>'
					+'<th class=" text-main text-center ">操作</th>'
					+'<th class="text-main ">设备号码</th>'
					+'<th class="text-main ">设备名称</th>'
					+'<th class="text-main">设备状态</th>'
					+'<th class="text-main">设备系统</th>'
					+'<th class="text-main">设备类型</th>'
					+'<th class="text-main">编号</th>'
					+'<th class="text-main">时间</th>'
					+'<th class="text-main">人员警号</th>'
					+'<th class="text-main">人员名称</th>'
				+'</tr>');//清空表格内容内容
			$("#table").append("<tr><td class='text-center text-danger' colspan='11' >加载失败</td></tr>");
		}
	});
}

function  querypolice(obj){
	if($(obj).val() != null && $(obj).val() != ""){
		$.ajax({
			type : "POST",
			url : "<%=basePath%>/equipment/policeNo",  
			data :{ pid:$(obj).val()},
			dataType: "json", 
			success : function(data) {
				try{
					$("#userName").val(data.data);
				}catch(error){
					new $.flavr({content:'数据错误',closeOverlay:true,closeEsc:true,autoclose:true,timeout:1500,buttons:[{text:'确定',style:'warning',action:function(){}}]});
				}
			},
		});
	}
}
//搜索查询
$("#search").click(function (){
	list(0,10);
});
//刷新
$("#refresh").click(function (){
	list(0,10);
});
//弹出模态框
$("#add").click(function(){
	$("#ry").removeClass("hide");
	$("#zh").removeClass("hide");
	$("#pwd").removeClass("hide");
	$("#pwd2").removeClass("hide");
	$('#modal').iziModal('open',this);
});	
//添加/修改	设备
$("#save").click(function(){
	var formData = new FormData($("#form")[0]);  
	$('#form').bootstrapValidator('validate');
	if($('#form').data('bootstrapValidator').isValid()){
		$('#save').button('loading');
		$.ajax({  
			type : "POST",
			url : "<%=basePath%>/equipment/addorupdate",
		 	data :formData,//整个表单提交
			dataType : "json",
			async: false,  
            cache: false,  
            contentType: false,
            processData: false, 
			success : function(data) {
				try {
					$('#save').button('reset'); 
					if (data.ret == 200) {
						list(0,10);
						$('#modal').iziModal('close');
						new $.flavr({content:data.msg,closeOverlay:true,closeEsc:true,autoclose:true,timeout:1500,buttons:[{text:'确定',style:'success',action:function(){}}]});
					} else {
						new $.flavr({content:data.msg,closeOverlay:true,closeEsc:true,autoclose:true,timeout:1500,buttons:[{text:'确定',style:'danger',action:function(){}}]});
					}
				} catch (error) {
					new $.flavr({content:'数据错误',closeOverlay:true,closeEsc:true,autoclose:true,timeout:1500,buttons:[{text:'确定',style:'warning',action:function(){}}]});
				}
			},
			
	   });
	}
});
//查询设备信息
function get(id){
	
	$.ajax({  
		type : "GET",
		url : "<%=basePath%>/equipment/get",
		data:{eid:id},
		dataType: "json", 
		success : function(data) {  
			$("#modal").iziModal('open');
			$("#eid").val(data.data.eid);
			$("#equipmentName").val(data.data.equipmentName);
			$("#equipmentSys").val(data.data.equipmentSys);
			$("#equipmentType").val(data.data.equipmentType);
			$("#equipmentNo").val(data.data.equipmentNo);
			$("#number").val(data.data.number);
			$("#pid").select2({ language:'zh-CN', placeholder:data.data.name, allowClear:true});
			$("#equipmentSys").select2({ language:'zh-CN', placeholder:data.data.equipmentSys, allowClear:true});
			$("#equipmentType").select2({ language:'zh-CN', placeholder:data.data.equipmentType, allowClear:true});
			if(data.data.status==0){
				$("#status").val(0);
				$("#status").select2({ language:'zh-CN', placeholder:"在线", allowClear:true});
			}else if(data.data.status==1){
				$("#status").val(1);
				$("#status").select2({ language:'zh-CN', placeholder:"离线", allowClear:true});
			}else if(data.data.status==2){
				$("#status").val(2);
				$("#status").select2({ language:'zh-CN', placeholder:"禁用", allowClear:true});
			}else if(data.data.status==3){
				$("#status").val(3);
				$("#status").select2({ language:'zh-CN', placeholder:"损坏", allowClear:true});
			}
		$("#zh").addClass("hide");
			$("#pwd").addClass("hide");
			$("#pwd2").addClass("hide");
		},
	});
} 
//修改设备状态
function del(id){
	$("#id1").val(id);
	$('#modal2').iziModal('open',this); 
		
}
$("#save2").click(function(){
	$.ajax({  
		type : "GET",
		url : "<%=basePath%>/equipment/del",
		data:{eid:$("#id1").val(),status:$("#status1").val()},
		dataType: "json", 
		success : function(data) {  
			if (data.ret == 200) {
				list(0,10);
				$('#modal2').iziModal('close',this); 
				new $.flavr({content:data.msg,closeOverlay:true,closeEsc:true,autoclose:true,timeout:1500,buttons:[{text:'确定',style:'success',action:function(){}}]});	
			}else{
				new $.flavr({content:data.msg,closeOverlay:true,closeEsc:true,autoclose:true,timeout:1500,buttons:[{text:'确定',style:'danger',action:function(){}}]});

			}
			
		},
	});
	
});
//设备更换申请
function change(){
	$('#modal3').iziModal('open',this);
}
$("#save3").click(function(){
	$.ajax({  
		type : "GET",
		url : "<%=basePath%>/equipment/change",
		data:{eid:$("#id1").val(),remark:$("#remark").val()},
		dataType: "json", 
		success : function(data) {  
			if (data.ret == 200) {
				list(0,10);
				$('#modal2').iziModal('close',this); 
				new $.flavr({content:data.msg,closeOverlay:true,closeEsc:true,autoclose:true,timeout:1500,buttons:[{text:'确定',style:'success',action:function(){}}]});	
			}else{
				new $.flavr({content:data.msg,closeOverlay:true,closeEsc:true,autoclose:true,timeout:1500,buttons:[{text:'确定',style:'danger',action:function(){}}]});

			}
			
		},
	});
	
});
//申请跟换设备
function examine(id){
	 new $.flavr({
			closeOverlay : true, 
			closeEsc : false,
			content : '是否审核改申请？',
			buttons:[
		    	{text:'取消',style:'info',action:function(){
		    			return;
		    		}
		    	},{
		    		text:'确定',style:'primary',action:function(){
		    			$.ajax({  
		    				type : "GET",
		    				url : "<%=basePath%>/equipment/examine",
		    				data:{eid:id},
		    				dataType: "json", 
		    				success : function(data) {  
		    					if (data.ret == 200) {
		    						list(0,10);
		    						$('#modal2').iziModal('close',this); 
		    						new $.flavr({content:data.msg,closeOverlay:true,closeEsc:true,autoclose:true,timeout:1500,buttons:[{text:'确定',style:'success',action:function(){}}]});	
		    					}else{
		    						new $.flavr({content:data.msg,closeOverlay:true,closeEsc:true,autoclose:true,timeout:1500,buttons:[{text:'确定',style:'danger',action:function(){}}]});

		    					}
		    					
		    				},
		    			});
			    	}
		    	}
		    ]  
		});
}
//跟换设备
 function replace(id){
		 $("#eid").val(id);
		 $("#ry").addClass("hide");
		 $("#zh").addClass("hide");
		$("#pwd").addClass("hide");
		$("#pwd2").addClass("hide");
		$('#modal').iziModal('open',this);
 }
 
</script>

</body>



</html>
