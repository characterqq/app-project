<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>人员管理</title>
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
	<header id="navbar">
		<jsp:include page="../menuTop.jsp"></jsp:include>
	</header>
	<div class="boxed">
		<jsp:include page="../menu.jsp"></jsp:include>
		<div id="content-container">
			<div id="page-title">
					<a href="#"> <i
							class="demo-psi-home"></i> <span class="menu-title"> <strong>员工管理</strong>
						</span>
					</a>
				   <i class="ion-chevron-right"></i><a href="#"><span class="menu-title"> <strong>员工</strong>
						</span>
					</a>
			</div>
               <div id="page-content">
				<div class="panel">
				    <div class="panel-body" >
				    	<form id="queryForm" action="" method="post" class="">
				    	<div class="col-md-2">
								<div class="form-group">
									<input type="text" class="form-control" name="policeNo" id="police_no"
										placeholder="警号">
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<input type="text" class="form-control" name="name" id="names"
										placeholder="真实姓名">
								</div>
							</div> 
							<div class="col-md-2">
								<div class="form-group">
									<input type="text" class="form-control" name="idcardNo" id="idcard_no"
										placeholder="身份证号">
								</div>
							</div> 
							
							<span class="pull-right">
								
								<button id="refresh" type="button" class="btn btn-primary btn-sm" data-toggle="tooltip" title="刷新">
									<i class="fa fa-refresh"></i>
								</button>
								<button id="search" type="button" class="btn btn-primary btn-sm" data-toggle="tooltip" title="查询" >
									<i class="fa fa-search"></i>
								</button>
								<c:if test="${loginUser.roleId!=3}">
								<button id="add" type="button" class="btn btn-primary btn-sm" title='添加' data-toggle="tooltip">
									<i class="fa fa-plus"></i>
								</button>&nbsp;&nbsp;
								</c:if>
							</span>
						</form>			    
					    <div class=" panel-bodytable-responsive no-padding">
					        <table class="table table-bordered table-hover" id="person-table">
					      
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
<div id="person-modal"style="display:none">
	<div class="row">
		<div class="col-md-12">
			<div class="box-body" >
			<p>
				<form class="form-horizontal " id="form" method="post">
					<div class="form-group hide">
					<label for="pid" class="col-md-3 control-label text-main">员工id&nbsp;&nbsp;</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="pid" name="pid">
							</div>
					</div>
					<div class="form-group ">
						<label for="policeNo" class="col-sm-3 control-label text-main">警号&nbsp;&nbsp;<span class="text-danger "> *</span></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="policeNo" name="policeNo" placeholder="">
						</div>
					</div>
					<div class="form-group ">
						<label for="personPwd" class="col-sm-3 control-label text-main">警员密码<span class="text-danger "> *</span></label>
						<div class="col-sm-8">
							<input type="password" class="form-control" id="personPwd" name="personPwd" placeholder="">
						</div>
					</div>
					<div class="form-group ">
						<label for="name" class="col-sm-3 control-label text-main">真实姓名<span class="text-danger "> *</span></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="name" name="name" placeholder="">
						</div>
					</div>
					<div class="form-group ">
						<label for="idcardNo" class="col-sm-3 control-label text-main">身份证号<span class="text-danger"> *</span></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="idcardNo" name="idcardNo" placeholder="">
						</div>
					</div>
					<div class="form-group  ">
						<label for="mobile" class="col-sm-3 control-label text-main">联系电话<span class="text-danger"> *</span></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="mobile" name="mobile" placeholder="">
						</div>
					</div>
					<!-- <div class="form-group  ">
						<label for="publicKey" class="col-sm-3 control-label text-main">证书<span class="text-danger"> *</span></label>
						<div class="col-sm-8">
							<input type=text class="form-control" id="publicKey" name="publicKey" placeholder="">
						</div>
					</div> -->
					<div class="form-group  ">
						<label for="deptId" class="col-sm-3 control-label text-main">所属部门</label>
						<div class="col-sm-8">
							<select id="deptId" name="deptId"style="width: 100%" >
							</select>
						</div>
					</div>
					
					<div class="form-group  ">
						<label for="isCount" class="col-sm-3 control-label text-main">可用设备数量<span class="text-danger"> *</span></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="isCount" name="isCount" placeholder="">
						</div>
					</div>
					
					<div class="form-group  ">
						<label for="email" class="col-sm-3 control-label text-main">用户邮箱<span class="text-danger"> </span></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="email" name="email" placeholder="">
						</div>
					</div>
					<div class="form-group  ">
						<label for="remark" class="col-sm-3 control-label text-main">备注<span class="text-danger"> </span></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="remark" name="remark" placeholder="">
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
<script type="text/javascript">
$("#person-modal").iziModal({
	title: '员工',
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
        	policeNo:{
	        	validators: {
	            	notEmpty: {
	                    message: '不能为空'
	                },
	            }
	        },
	        name:{
	        	validators: {
	            	notEmpty: {
	                    message: '不能为空'
	                },
	            }
	        },
	        
	        idcardNo:{
	        	validators: {
	            	notEmpty: {
	                    message: '不能为空'
	                },
	                  stringLength:{
	                    min: 15,
	                    max: 19,
	                    message: '请输入正确的身份证号码 '
	                },  
	            }
	        },
	        personPwd:{
	        	validators: {
	            	notEmpty: {
	                    message: '不能为空'
	                },
	            }
	        },
	        mobile:{
	        	validators: {
	            	notEmpty: {
	                    message: '不能为空'
	                },
	            }
	        },
	        isCount:{
	        	validators: {
	            	notEmpty: {
	                    message: '不能为空'
	                },
	            }
	        },
   	   	}
    });
}
menuCss("员工管理");
$("#status").select2({ language:'zh-CN', placeholder:"请选择状态", allowClear:true});
//这个影响到了下拉框取值
$("#deptId").select2({ language:'zh-CN', placeholder:"请选择部门", allowClear:true});
$("#status").val(null).trigger("change");
$("#deptId").val(null).trigger("change");
list(0,10)
//查询酒庄列表
function list(start,limit){
	$.ajax({  
		type : "post",
		url : "<%=basePath%>/person/list",  
		data : {
			"start":start,
			"limit":limit,
			"policeNo":$("#police_no").val(),
			"name":$("#names").val() ,
			"idcardNo":$("#idcard_no").val() ,
		
		},
		dataType: "json", 
		success : function(data) {
			try{
				if(data.ret != 200){
					return;
				}
				//分页
				$("#person-table").html('<tr >'
						+'<th  class="text-main" style="width: 35px">&nbsp</th>'
						+'<th class=" text-main text-center ">操作</th>'
						+'<th class="text-main ">警号</th>'
						+'<th class="text-main ">警员密码</th>'
						+'<th class="text-main ">真实姓名</th>'
						+'<th class="text-main">身份证号</th>'
						+'<th class="text-main">联系电话</th>'
						+'<th class="text-main">警员状态</th>'
						+'<th class="text-main">所属部门</th>'
						+'<th class="text-main">可注册设备数量</th>'
						+'<th class="text-main">邮箱</th>'
					+'</tr>');//清空表格内容内容
				Page(data.data.total,limit,(start/limit+1));
				
				var list = data.data.list;
				if(list.length==0){
					$("#person-table").append("<tr><td class='text-center text-primary' colspan='7' >无记录</td></tr>");
				}else{
					var str="";
					var bon="";
					var buttn = "";
					var datas='<c:out value="${loginUser.roleId}"/>';
					$.each(list,function(i, item){
						//2为超级管理员 ，1为管理员 ，3：为普通用户
						if(datas!=3){
							if(item.status==0){
								bon="<button  style='margin-right:3px' type='button' class='btn btn-danger btn-xs' data-toggle='tooltip' onclick='del2("+item.pid+")'>正常</button>"
							}else if(item.status==1){
								bon="<button  style='margin-right:3px' type='button' class='btn btn-danger btn-xs' data-toggle='tooltip' onclick='del1("+item.pid+")'>禁用</button>"
							}
							buttn="<button  style='margin-right:3px' id='update' type='button'class='btn btn-info btn-xs' data-toggle='tooltip'title='修改'onclick='get("+item.pid+")'>修改"
						}
					 	if(item.status==0){
							str="正常";
						}else if(item.status==1){
							str="禁用";
						} 
 						$("#person-table").append("<tr  >"
 								+ "<td class='' style='width: 50px'>" + (start+i+1)  + "</td>" 
 								+"<td class=' text-main text-center' style='width: 160px'>"
 								+buttn
 								+"</button>"
								+bon +"</td>"
								+ "<td class='text-main' style='width: 50px'>" + (item.policeNo ==null?"":item.policeNo)+ "</td>" 
								+ "<td class='text-main' style='width: 50px'>" + (item.personPwd ==null?"":item.personPwd)+ "</td>" 
								+ "<td class=' text-main' style='width:100px'>"+(item.name ==null?"":item.name)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.idcardNo ==null?"":item.idcardNo)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.mobile ==null?"":item.mobile)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+str+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.dname ==null?"":item.dname)+"</td>" 
								+ "<td class=' text-main' style='width:100px'>"+(item.isCount ==null?"":item.isCount)+"</td>"
								+ "<td class=' text-main' style='width:100px'>"+(item.email ==null?"":item.email)+"</td>"
			                    + "</tr>"); 
					});
				}
				 if($("#deptId").html() == null || $("#deptId").html().trim()== ""){
					 	var da='<c:out value="${loginUser.roleId}"/>';
					 	//2为超级管理员 ，1为管理员 ，3：为普通用户
						if(da==1){
							var deptvo = data.data.deptvo;
							var str = "<option value=''>请选择部门</option>";
							str = str + "<option value='"+deptvo.did+"'>"+deptvo.dname+"</option>";
						}else{
							var deptList = data.data.deptList;
							var str = "<option value=''>请选择部门</option>";
							for(var i=0;i<deptList.length;i++){
									str = str + "<option value='"+deptList[i].did+"'>"+deptList[i].dname+"</option>";
							}
						}
						$("#deptId").html(str);
						$("#deptId").val(null).trigger("change");
					}
			}catch(error){
				$("#person-table").append("<tr><td class='text-center text-danger' colspan='7' >数据错误</td></tr>");
			}
		},
		failure:function(){
			$("#person-table").html();//清空表格内容内容
			$("#person-table").html('<tr >'
					+'<th  class="text-main" style="width: 35px">&nbsp</th>'
					+'<th class=" text-main text-center "></th>'
					+'<th class="text-main ">警号</th>'
					+'<th class="text-main ">警号密码</th>'
					+'<th class="text-main ">真实姓名</th>'
					+'<th class="text-main">身份证号</th>'
					+'<th class="text-main">联系电话</th>'
					+'<th class="text-main">警员状态</th>'
					+'<th class="text-main">所属部门</th>'
					+'<th class="text-main">可注册设备数量</th>'
					+'<th class="text-main">邮箱</th>'
				+'</tr>');//清空表格内容内容
			$("#person-table").append("<tr><td class='text-center text-danger' colspan='7' >加载失败</td></tr>");
		}
	});
}
//搜索查询
$("#search").click(function (start,limit){
	list(0,10);
});


//刷新
$("#refresh").click(function (){
	list(0,10);
});
//弹出模态框
$("#add").click(function(){
	$("#status").show();
	$('#person-modal').iziModal('open',this);
});	
//添加/修改	用户
$("#save").click(function(){
	var formData = new FormData($("#form")[0]);  
	//$('#form').bootstrapValidator('validate');
//	if($('#form').data('bootstrapValidator').isValid()){
		$('#save').button('loading');
		$.ajax({  
			type : "POST",
			url : "<%=basePath%>/person/personAdd",
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
						$('#person-modal').iziModal('close');
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
		url : "<%=basePath%>/person/get",
		data:{pid:id},
		dataType: "json", 
		success : function(data) {  
			$("#person-modal").iziModal('open');
			$("#pid").val(data.data.pid);
			$("#policeNo").val(data.data.policeNo);
			$("#personPwd").val(data.data.personPwd);
			$("#name").val(data.data.name);
			$("#idcardNo").val(data.data.idcardNo);
			$("#mobile").val(data.data.mobile);
			$("#publicKey").val(data.data.publicKey);
			$("#deptId").select2({ language:'zh-CN', placeholder:data.data.dname, allowClear:true});
			/* if(data.data.status==0){
				$("#status").select2({ language:'zh-CN', placeholder:"正常", allowClear:true});
			}else if(data.data.status==1){
				$("#status").select2({ language:'zh-CN', placeholder:"禁用", allowClear:true});
			}
			$("#status").hide(); */
			$("#isCount").val(data.data.isCount);
			$("#email").val(data.data.email);
			$("#remark").val(data.data.isCount);
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
		    				url : "<%=basePath%>/person/del",
		    				data:{pid:id},
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
		    				url : "<%=basePath%>/person/del1",
		    				data:{pid:id},
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
