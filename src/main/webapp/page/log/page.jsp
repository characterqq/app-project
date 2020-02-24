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
							class="demo-psi-home"></i> <span class="menu-title"> <strong>日志管理</strong>
						</span>
					</a>
				   <i class="ion-chevron-right"></i><a href="#"><span class="menu-title"> <strong>日志</strong>
						</span>
					</a>
			</div>
               <div id="page-content">
				<div class="panel">
				    <div class="panel-heading">
				   	 <div class="panel-title">
						   <span class="">
						   		<a href="<%=basePath%>/log/page" type="button" class="btn btn-primary btn-sm">操作日志查询</a>
						  		<a  href="<%=basePath%>/log/pageLog" type="button" class="btn btn-primary btn-sm">登录日志查询</a>
								<!-- <button id="add" type="button" class="btn btn-primary btn-sm" title='添加' data-toggle="tooltip">
									<i class="fa fa-plus"></i>
								</button> -->
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
							<div class="col-md-2">
								<div class="form-group">
									<input type="text" class="form-control" name="userName" id="userName"
										placeholder="操作人">
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<input type="text" class="form-control" name="policeNo" id="policeNo"
										placeholder="警号">
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<input type="text" class="form-control" name="content" id="content"
										placeholder="操作类别">
								</div>
							</div>
							
						</form>			    
					    <div class=" panel-bodytable-responsive no-padding">
					        <table class="table table-bordered table-hover" id="user-table">
					      
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
			<strong class="pad-lft">Copyright &copy; 2017 <a href="#">Gable</a>.
			</strong> All rights reserved.
		 </footer>
	</div>
</div>
<div id="user-modal"style="display:none">
	<div class="row">
		<div class="col-md-12">
			 <div class="box-body" >
			<p>
				<form class="form-horizontal " id="form" method="post">
					<!-- <div class="form-group hide">
					<label for="id" class="col-md-3 control-label text-main">用户id&nbsp;&nbsp;</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="uid" name="uid">
							</div>
					</div>
					<div class="form-group ">
						<label for="number" class="col-sm-3 control-label text-main">用户名<span class="text-danger "> *</span></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="userName" name="userName" >
						</div>
					</div>
					<div class="form-group ">
						<label for="name" class="col-sm-3 control-label text-main">密码<span class="text-danger"> *</span></label>
						<div class="col-sm-8">
							<input type="password" class="form-control" id="userPwd" name="userPwd" placeholder="">
						</div>
					</div>
					<div class="form-group  ">
						<label for="name" class="col-sm-3 control-label text-main">新密码<span class="text-danger"> *</span></label>
						<div class="col-sm-8">
							<input type="password" class="form-control" id="userPwd2" name="userPwd2" placeholder="">
						</div>
					</div>
					<div class="form-group  ">
						<label for="name" class="col-sm-3 control-label text-main">角色</label>
						<div class="col-sm-8">
							<select id="roleId" name="roleId"style="width: 100%" >
							</select>
						</div>
					</div>
					<div class="form-group  "id="zt">
						<label for="status" class="col-sm-3 control-label text-main">状态</label>
						<div class="col-sm-8">
							<select id="status" name="status" style="width: 100%" >
								<option value="0">正常</option>
								<option value="1">禁用</option>
							</select>
						</div>
					</div>
					 -->
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
<script type="text/javascript">
$("#user-modal").iziModal({
	title: '用户',
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
    	$("#form").data('bootstrapValidator').destroy();
    	
    }
});
function validator(){
	$('#form').bootstrapValidator({
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        live: 'enabled',
        message: '非有效值',
        fields: {
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
	                identical:{
		            	field: 'userPwd',
	                    message: '两次密码不相同'
		            }
	            }
	        }
   	   	}
    });
}
menuCss("日志管理");
/*  $("#roleId").select2({ language:'zh-CN', placeholder:"请选择角色", allowClear:true});
$("#roleIds").select2({ language:'zh-CN', placeholder:"请选择角色", allowClear:true});
$("#status").select2({ language:'zh-CN', placeholder:"请选择角色", allowClear:true});
$("#roleId").val(null).trigger("change");
$("#roleIds").val(null).trigger("change");
$("#status").val(null).trigger("change");  */
list(0,10)
//查询酒庄列表
function list(start,limit){
	$.ajax({  
		type : "post",
		url : "<%=basePath%>/log/list",  
		data : {
			"start":start,
			"limit":limit,
			"userName":$("#userName").val(),
			"policeNo":$("#policeNo").val(),
			"content":$("#content").val()
		},
		dataType: "json", 
		success : function(data) {
			try{
				if(data.ret != 200){
					return;
				}
				//分页
				$("#user-table").html('<tr >'
						+'<th  class="text-main" style="width: 35px">&nbsp</th>'
						+'<th class="text-main ">操作人</th>'
						+'<th class="text-main ">被操作人</th>'
						+'<th class="text-main ">人员警号</th>'
						+'<th class="text-main">操作编号</th>'
						+'<th class="text-main">操作类别</th>'
						+'<th class="text-main">操作时间</th>'
						/* +'<th class="text-main ">登录人</th>'
						+'<th class="text-main ">所管设备</th>'
						+'<th class="text-main ">设备编号</th>'
						+'<th class="text-main">所属角色</th>'
						+'<th class="text-main">登录ip</th>'
						+'<th class="text-main">登录地址</th>' */
						
					+'</tr>');//清空表格内容内容
				Page(data.data.total,limit,(start/limit+1));
				
				var list = data.data.list;
				if(list.length==0){
					$("#user-table").append("<tr><td class='text-center text-primary' colspan='7' >无记录</td></tr>");
				}else{
					var str="";
					var bon="";
					$.each(list,function(i, item){
						/*  if(item.status==0){
							str="正常";
							bon="<button  style='margin-right:3px' type='button' class='btn btn-danger btn-xs' data-toggle='tooltip' onclick='del2("+item.uid+")'>正常</button>"
						}else if(item.status==1){
							str="禁用";
							bon="<button  style='margin-right:3px' type='button' class='btn btn-danger btn-xs' data-toggle='tooltip' onclick='del1("+item.uid+")'>禁用</button>"
						} */
 						$("#user-table").append("<tr  >"
 								+ "<td class='' style='width: 50px'>" + (start+i+1)  + "</td>" 
 							/* 	+"<td class=' text-main text-center' style='width: 80px'>"
 								
								
								+bon +"</td>" */
								+ "<td class='text-main' style='width: 50px'>" + (item.userName ==null?"":item.userName)+ "</td>" 
								+ "<td class=' text-main' style='width:100px'>"+(item.name ==null?"":item.name)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.policeNo ==null?"":item.policeNo)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.type ==null?"":item.type)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.content ==null?"":item.content)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.createDate ==null?"":item.createDate)+"</td>"
								/* + "<td class=' text-main' style='width:100px'>"+(item.userName ==null?"":item.userName)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.equipmentName ==null?"":item.equipmentName)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.eid ==null?"":item.eid)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.roleName ==null?"":item.roleName)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.loginIp ==null?"":item.loginIp)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.loginPlcae ==null?"":item.loginPlcae)+"</td>" */
			                    + "</tr>"); 
					});
				}
				  /* if($("#roleIds").html() == null || $("#roleIds").html().trim()== ""){
						var roleList = data.data.roleList;
						var str = "<option value=''>请选择角色</option>";
						for(var i=0;i<roleList.length;i++){
								str = str + "<option value='"+roleList[i].rid+"'>"+roleList[i].roleName+"</option>";
						}
						$("#roleIds").html(str);
						$("#roleId").html(str);
						$("#roleIds").val(null).trigger("change");
						$("#roleId").val(null).trigger("change");
					}  */
			}catch(error){
				$("#user-table").append("<tr><td class='text-center text-danger' colspan='7' >数据错误</td></tr>");
			}
		},
		 failure:function(){
			$("#user-table").html();//清空表格内容内容
			$("#user-table").html('<tr >'
					+'<th  class="text-main" style="width: 35px">&nbsp</th>'
					+'<th class=" text-main text-center "></th>'
					+'<th class="text-main ">用户名</th>'
					+'<th class="text-main ">创建时间</th>'
					+'<th class="text-main">角色</th>'
					+'<th class="text-main">状态</th>'
					+'<th class="text-main">备注</th>'
				+'</tr>');//清空表格内容内容
			$("#user-table").append("<tr><td class='text-center text-danger' colspan='7' >加载失败</td></tr>");
		} 
	});
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
	$("#zt").addClass("hide");
	$('#user-modal').iziModal('open',this);
});	
//添加/修改	用户
$("#save").click(function(){
	var formData = new FormData($("#form")[0]);  
	//$('#form').bootstrapValidator('validate');
//	if($('#form').data('bootstrapValidator').isValid()){
		$('#save').button('loading');
		$.ajax({  
			type : "POST",
			url : "<%=basePath%>/user/addorupdate",
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
						$('#user-modal').iziModal('close');
						new $.flavr({content:data.msg,closeOverlay:true,closeEsc:true,autoclose:true,timeout:1500,buttons:[{text:'确定',style:'success',action:function(){}}]});
					} else {
						new $.flavr({content:data.msg,closeOverlay:true,closeEsc:true,autoclose:true,timeout:1500,buttons:[{text:'确定',style:'danger',action:function(){}}]});
					}
				} catch (error) {
					new $.flavr({content:'数据错误',closeOverlay:true,closeEsc:true,autoclose:true,timeout:1500,buttons:[{text:'确定',style:'warning',action:function(){}}]});
				}
			},
			
	   });
	//}
});

function get(id){
	
	$.ajax({  
		type : "GET",
		url : "<%=basePath%>/user/get",
		data:{uid:id},
		dataType: "json", 
		success : function(data) {  
			$("#user-modal").iziModal('open');
			$("#uid").val(data.data.uid);
			$("#userName").val(data.data.userName);
			$("#userPwd").val(data.data.userPwd);
			$("#userPwd2").val(data.data.userPwd);
			$("#roleId").select2({ language:'zh-CN', placeholder:data.data.roleName, allowClear:true});
			if(data.data.status==0){
				$("#status").val(0);
				$("#status").select2({ language:'zh-CN', placeholder:"正常", allowClear:true});
			}else if(data.data.status==1){
				$("#status").val(1);
				$("#status").select2({ language:'zh-CN', placeholder:"禁用", allowClear:true});
			}
			
			$("#zt").removeClass("hide");
		},
	});
} 
function del2(id){
	  new $.flavr({
			closeOverlay : true, 
			closeEsc : false,
			content : '是否要禁用该用户？',
			buttons:[
		    	{text:'取消',style:'info',action:function(){
		    			return;
		    		}
		    	},{
		    		text:'确定',style:'primary',action:function(){
		    			$.ajax({  
		    				type : "GET",
		    				url : "<%=basePath%>/user/del",
		    				data:{uid:id},
		    				dataType: "json", 
		    				success : function(data) {  
		    					if (data.ret == 200) {
		    						list(0,10);
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
function del1(id){
	  new $.flavr({
			closeOverlay : true, 
			closeEsc : false,
			content : '是否取消禁用该用户？',
			buttons:[
		    	{text:'取消',style:'info',action:function(){
		    			return;
		    		}
		    	},{
		    		text:'确定',style:'primary',action:function(){
		    			$.ajax({  
		    				type : "GET",
		    				url : "<%=basePath%>/user/del1",
		    				data:{uid:id},
		    				dataType: "json", 
		    				success : function(data) {  
		    					if (data.ret == 200) {
		    						list(0,10);
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
</script>

</body>



</html>
