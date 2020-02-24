/**
 * 分页插件
 * @param total
 * @param limit
 * @param current
 * @returns
 */
function Page(total,limit,current){
		$('.M-box3').empty();
		$('.M-box3').pagination({
			totalData:total,
		    showData:limit,//数据总数totalData和每页显示的条数showData必须同时配置，否则默认使用总页数pageCount;limit可选值 5 10 20 50
			//pageCount:50,
			current:current,
		    jump:true,
		    coping:true,
		    homePage:'首页',
		    endPage:'末页',
		    prevContent:'上页',
		    nextContent:'下页',
			callback:function(index){
				start = (index.getCurrent()-1)*limit;
				limit = index.getShowData();
				list(start,limit);
			}
		});
}


function show(){
	if($("body").hasClass("sidebar-collapse")){
		//$("body").attr("class","skin-blue sidebar-mini sidebar-collapse");
		$("body").removeClass("sidebar-collapse");
	}else{
		//$("body").attr("class","skin-blue sidebar-mini");
		$("body").addClass("sidebar-collapse");
	}
	if($("body").hasClass("sidebar-open")){
		$("body").removeClass("sidebar-open");
	}else{
		$("body").addClass("sidebar-open");
	}
}

/**
 * 修改左边菜单样式
 * @param str
 * @returns
 */
function menuCss(str){
	$("#mainnav-menu").find("li.active-link").removeClass("active-link");
	$(".treeview").removeClass("active active-sub");
	$(".menuInfo").each(function (){	
		if($(this).html() == str){
			$(this).parent().parent().addClass("active-link");
			$(this).parent().parent().parent().parent().addClass("active active-sub");
		}
	});
}



/**
 * 修改密码初始化
 */
function updetePassword(){
	var html = '<div id="updatePasswordmodal" style="display:none">'
		     + '<div class="box-body" style="margin:5px 0 -10px 0">'
			 + '<form class="form-horizontal" role="form" id="updatePasswordForm" method="post">'
             + '<div class="form-group">'
			 + '<label for="username" class="col-sm-2 control-label text-dark">原密码<span class="text-danger"> *</span></label>'
			 + '<div class="col-sm-9">'
			 + '<input type="password" class="form-control" id="oldPassword" name="oldPassword" placeholder="">'
			 + '</div>'
			 + '</div>'
			 + '<div class="form-group" id="passwordInfo">'
		     + '<label for="userPwd" class="col-sm-2 control-label text-dark">新密码<span class="text-danger"> *</span></label>'
			 + '<div class="col-sm-9">'
			 + '<input type="password" class="form-control" id="userPwd" name="userPwd">'
		     + '</div>'
		     + '</div>'
		     + '<div class="form-group" id="confirmPasswordInfo">'
		     + '<label for="confirmPassword" class="col-sm-2 control-label text-dark">确认密码<span class="text-danger"> *</span></label>'
		     + '<div class="col-sm-9">'
		     + '<input type="password" class="form-control" id="confirmPassword" name="confirmPassword">'
		     + '</div>'
			 + '</div>'
             + '</form>'
	         + '</div>'
	         + '<br/>'
		     + '<div class="box-footer text-right">'
			 + '<button data-iziModal-close class="btn btn-default">取消</button>&nbsp;&nbsp;&nbsp;'
		 	 + '<button type="button" class="btn btn-primary" style="margin-right:5px;" onclick="updatePasswordSubmit()">保存</button>'
		     + '</div>'
		     + '<p>'
	         + '</div>';
	
		$("body").append(html);
		
		/**
		 * 修改密码的modal框
		 */
		$("#updatePasswordmodal").iziModal({
			title: '修改密码',
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
		    zindex: 100,
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
		    	updatePasswordValidator();
		    },
		    onOpened: function(){},
		    onClosing: function(){
		    	$('#updatePasswordForm')[0].reset();
		    	$("#updatePasswordForm").data('bootstrapValidator').destroy();
		    },
		    onClosed: function(){   
		    }
		});
	$("#updatePasswordmodal").iziModal("open",this);
}


/**
 * 修改密码校验
 * @returns
 */
function updatePasswordValidator(){
	$('#updatePasswordForm').bootstrapValidator({
	    excluded: [':disabled', ':hidden', ':not(:visible)'],
	    live: 'enabled',
	    message: '非有效值',
	    fields: {
	        oldPassword: {
	            validators: {
	            	notEmpty: {
	                    message: '不能为空'
	                },stringLength: {
	                    max: 16,
	                    message: '最多输入16个字符'
	                } 
	            }
	        },
	        userPwd: {
	            validators: {
	            	notEmpty: {
	                    message: '不能为空'
	                },stringLength: {
	                    max: 16,
	                    message: '最多输入16个字符'
	                } 
	            }
	        },
	        confirmPassword:{
	        	validators: {
	            	notEmpty: {
	                    message: '不能为空'
	                },
	                stringLength:{
	                    max: 16,
	                    message: '最多输入16个字符'
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


/**
 * 修改密码提交
 */
var updatePasswordBool = false;
function updatePasswordSubmit(){
	if(updatePasswordBool == false){
	$('#updatePasswordForm').bootstrapValidator('validate');
	if($('#updatePasswordForm').data('bootstrapValidator').isValid()){
		updatePasswordBool = true;
		var url = $("#updatePasswordUrl").val();
		url = url + "/login/updatePassword";
			$.ajax({  
				type : "POST",
				url : url,
			 	data :$('#updatePasswordForm').serialize(),//整个表单提交
				dataType : "json",
				success : function(data) {
					try {
						if(data.ret == 200){
							flavrShowByTime(data.msg,null,"success",false);
							$("#updatePasswordmodal").iziModal("close",this);
						}else{
							flavrShowByTime(data.msg,null,"danger",false);
						}
						updatePasswordBool = false;
					} catch (error) {
						flavrShowByTime(data.msg,null,"danger",false);
						updatePasswordBool = false;
					}
				},
				failure : function() {
					flavrShowByTime(data.msg,null,"danger",false);
					updatePasswordBool = false;
				}
			});
		}
	}
}

function flavrShow(msg,url,color,isHref){
	new $.flavr({
	    closeOverlay:true,
	    closeEsc : false,
	    content :msg,
	    buttons : [{text:'确定',style:color}]
	})
}


/**
 * 提示用户框
 * @param msg
 * @param urls
 * @param color
 * @param isHref
 * @returns
 */
function flavrShowByTime(msg,urls,color,isHref){
	new $.flavr({ 
		content : msg,
		autoclose:true,
        closeOverlay :true,
        closeEsc : true,
        buttons: [{text:'ok',style:color}],
        timeout : 1500,
        onClose:function (){
        	if(isHref && urls != null){
	    		window.location.href=urls;
	    	}
        }});
}

/**
 * 重新登陆
 * @returns
 */
function loginFromNew(){
	$.ajax({  
		type : "POST",
		url :  $("#updatePasswordUrl").val()+"/login/logout",
		dataType : "json",
		success : function(data) {
			try {
				if(data.ret == 200){
					flavrShowByTime(data.msg,null,"success",false);
					var url = $("#updatePasswordUrl").val();
					window.location.href=url+"/login/loginPage";
				}
			} catch (error) {
				flavrShowByTime(data.msg,null,"danger",false);			}
		},
		failure : function() {
			flavrShowByTime(data.msg,null,"danger",false);
		}
	});

}


function isEmpty(obj){
	return obj == null ? "" : obj;
}