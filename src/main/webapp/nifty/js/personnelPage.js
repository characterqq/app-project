var contextpath = $("body").attr("contextpath");
//删除
function personDelete(pid){
	var msg = "您真的确定要删除吗？\n\n请确认！";
	if (confirm(msg)==true){
		$.post( "http://localhost:8080/YDJW//person/personDelete", {
			"pid":pid,
		}, 
		function(data) {
			$("#container").html(data);
			if(data.message == "yes"){
				alert("删除成功"+data.message);
				$.post("person/list", function(data) {
					$("#container").html(data);
				});
				
			}else{
				alert("删除失败");
				$.post("person/list", function(data) {
					$("#container").html(data);
				});
			}
		});
	}else{
		$.post("person/page", {
		}, 
		function(data) {
			$("#container").html(data);
		});
		
	}
}


//跳转到功能查询页面和分页查询所有
function personnelPage(pageNow) {
	//警员编号
	var police_no = $("#police_no").val();
	//alert(police_no+"--------");
	//真实姓名
	var name = $("[name='name']").val();
	//身份证号
	var idcard_no = $("[name='idcard_no']").val();
	//alert("999"+pageNow+police_no+name+idcard_no+"====")
	$.ajax({
		type : "POST",
		url:"person/page",
		//url : contextPath + "/person/page",
		data : {"pageNow":pageNow,"police_no":police_no,"name":name,"idcard_no":idcard_no},
		dataType : "html",
		success : function(data) {
			$("#container").html(data);
		}
	})
}

//添加功能表方法	
function personAdd(pids) {
	alert("lailemei"+pids)
	//警员编号
	var policeNo = $("[name='policeNo']").val();
	//真实姓名
	var name = $("[name='name']").val();
	//身份证号
	var idcardNo = $("[name='idcardNo']").val();
	//联系电话
	var mobile = $("[name='mobile']").val();
	//电子证书
	var publicKey = $("[name='publicKey']").val();
	//所属部门
	var deptId = $("[name='deptId']").val();
	//警员状态
	var status = $("[name='status']").val();
	//可用设备数量
	var isCount = $("[name='isCount']").val();
	//用户邮箱
	var email = $("[name='email']").val();
	//备注
	var remark = $("[name='remark']").val();
	//添加
if(pids==00){
	$.ajax({
        cache: true,
        type: "POST",
        url:'person/personAdd',
        data:$('#person').serialize(),// 你的formid
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        
        success: function(data) {
           // $("#container").parent().html(data);
        	debugger
        	if (data.message == "yes") {
					alert("添加成功")
					$("#fullbg,#dialog").hide(); 
				$.post("person/page", function(data) {
					$("#container").html(data);
				});
				
			} else {
				if (data.message == "no") {
					alert("添加失败")
					$("#fullbg,#dialog").hide(); 
					$.post("person/page", function(data) {
						$("#container").html(data);
					});
				} 
			}
        }
    });
	//修改
}else{
	$.ajax({
        cache: true,
        type: "POST",
        url:'http://localhost:8080/YDJW//person/personAdd',
        data:$('#person').serialize(),// 你的formid
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        
        success: function(data) {
           // $("#container").parent().html(data);
        	debugger
        	if (data.message == "yes") {
					alert("添加成功")
					$("#fullbg,#dialog").hide(); 
				$.post("http://localhost:8080/YDJW//person/page", function(data) {
					$("#container").html(data);
				});
				
			} else {
				if (data.message == "no") {
					alert("添加失败")
					$("#fullbg,#dialog").hide(); 
					$.post("http://localhost:8080/YDJW//person/page", function(data) {
						$("#container").html(data);
					});
				} 
			}
        }
    });
}
}
	
//显示灰色 jQuery 遮罩层  修改
 	function show(pid) { 
 		alert("lailssss");alert("修改pid"+pid);
		$.post("person/page", {
			"pid":pid
		})
		var bh = $("body").height(); 
		var bw = $("body").width(); 
		
		$("#fullbg").css({ 
			height:bh, 
			width:bw, 
			display:"block" 
		}); 
			$("#dialog").show(); 
		} 
	
//关闭灰色 jQuery 遮罩 
	function closeBg() { 
		$("#fullbg,#dialog").hide(); 
	}  
	
