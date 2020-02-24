
var contextpath;
var starttime;
var endtime;
var business;
$(function() {
	contextpath=$("body").attr("contextpath");	 
	charts();
		})	 
function selectPerson(type){
	var data="";
	 if(type!='-1'&&type!='-2'&&type!='-3'&&type!='-4'&&type!='-5'){//如果条件选择的是某一天
		    starttime=$('#startDate').datebox('getValue'); 
		    endtime=$('#endDate').datebox('getValue'); 
		    business=$('#business') .val();
		    if(starttime!=""&&endtime!=""){
		    data="startTime="+starttime+"&&endTime="+endtime;
		    }
		    if(business!='0'){
		    	data+="&&businessId="+business;
		    }
	 }else{//选择的是今天 昨天 七天内 三十天内
		 data="selectType="+type;
		 if(business!='0'){
		    	data+="&&businessId="+business;
		    }
	 }
	// var data=$("#myform").serialize();
	 charts(data,'/chart/BusinessPerson','loginData');//调用画图的方法
}

function selectRegistPerson(type){
	$("#registerData").html(""); 
	var data="";
	 if(type!='-1'&&type!='-2'&&type!='-3'&&type!='-4'&&type!='-5'){//如果条件选择的是某一天
		    starttime=$('#startDate').datebox('getValue'); 
		    endtime=$('#endDate').datebox('getValue'); 
		    business=$('#business') .val();
		    if(starttime!=""&&endtime!=""){
		    data="startDate="+starttime+"&&endDate="+endtime;
		    }
		    if(business!='0'){
		    	data+="&&businessId="+business;
		    }
	 }else{//选择的是今天 昨天 七天内 三十天内
		 data="pageNo="+type;
		 if(business!='0'){
		    	data+="&&businessId="+business;
		    }
	 }
	charts(data,'/chart/SelectRegistPerson','registerData');//调用画图的方法
}
/**
 * 数据上方查询的公用方法
 * @param type
 */
function commfunction(type){
	selectPerson(type);
	selectProductCount(type);
	 charts3(type);
	 selectRegistPerson(type);
    
}
/**
 * 画饼图的方法
 * @param data
 */
function charts(){
	 $.ajax({
         type: "post",
         url: contextpath+"/statistics/devicedata",//请求链接
         data:data,
         dataType: "json",
         success: function(d){
        	 $("#jy").empty();
        	var datas= d.data;
        	var data = eval('(' + datas + ')');//将json格式的字符串转换成json类型
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
	                    return val + ': ' + obj.value + '台'; 
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

function selectProductCount(type){
	 var data="";
	 if(type!='-1'&&type!='-2'&&type!='-3'&&type!='-4'&&type!='-5'){
		 starttime=$('#startDate').datebox('getValue'); 
		 endtime=$('#endDate').datebox('getValue'); 
		 if(starttime!=""&&endtime!=""){
		 data="startTime="+starttime+"&&endTime="+endtime;
		 }
		 business=$('#business') .val();
		 if(business!='0'){
		    	data+="&&businessId="+business;
		    }
	 }else{
		 data="selectType="+type;
		 if(business!='0'){
		    	data+="&&businessId="+business;
		    }
	 }
	 charts2(data);//调用画图的方法
}
/**
 * 画柱型图的方法
 * @param data
 */
function  charts2(data){
	   $.ajax({
		   url:contextpath+"/chart/productCount",
           type:"post",
           data:data,
           dataType:"json",
		   success:function(d){
			   $("#productData").empty();
			   var datas= d.data;
	        	var data = eval('(' + datas + ')');//将json格式的字符串转换成json类型
	        	  var chart = new G2.Chart({
	        	        id: 'productData', // 指定图表容器 ID
	        	        forceFit: true,
	        	        width : 1000, // 指定图表宽度
	        	        height : 500,// 指定图表高度
	        	        plotCfg: {
	        	            margin: [60, 280, 100, 80]
	        	          }
	        	        
	        	      });
	        	      // Step 2: 载入数据源,定义列信息
	        	      chart.source(data, {
	        	        genre: {
	        	          alias: '商品名称' // 列定义，定义该属性显示的别名
	        	        },
	        	        sold: {
	        	          alias: '点击量'
	        	        }
	        	      });
	        	      chart.axis('value',{
	        	          title: '点击量',
	        	          labelOffset: 10
	        	        });
	        	      chart.legend({
	        	    	  position: 'right',
	        	    	  title: '商品名',
	        	    	  dx:0,
	        	    	  itemWrap:true,
	        	    	  width:100,
	        	    	  labelOffset: 10,
	        	    	  word: {
	        	    		    fontSize: 4
	        	    		  }, // 图例各个子项文本的颜色
	        	      });
	        	      // Step 3：创建图形语法，绘制柱状图，由 genre 和 sold 两个属性决定图形位置，genre 映射至 x 轴，sold 映射至 y 轴
	        	      chart.interval().position('name*value').color('name').label('value');
	        	      // Step 4: 渲染图表
	        	      chart.render();
		   }
	   })
	   
	   
}
function charts3(type){
	 var data="";
	 if(type!='-1'&&type!='-2'&&type!='-3'&&type!='-4'&&type!='-5'){//如果条件选择的是某一天
		    starttime=$('#startDate').datebox('getValue'); 
		    endtime=$('#endDate').datebox('getValue'); 
		    business=$('#business') .val();
		    if(starttime!=""&&endtime!=""){
		    data="startDate="+starttime+"&&endDate="+endtime;
		    }
		    if(business!='0'){
		    	data+="&&businessId="+business;
		    }
	 }else{//选择的是今天 昨天 七天内 三十天内
		 data="pageNo="+type;
		 if(business!='0'){
		    	data+="&&businessId="+business;
		    }
	 }
	userDateRecode(data);
}
function userDateRecode(data){
	
	$("#userData").html("<img src='../images/timg.gif' class='find' />");
	
	$.ajax({
		url:contextpath+"/chart/userDataselect",
		type:"post",
		data:data,
		dataType:"json",
		success:function(d){
			$("#userData").html("");
		    var datas= d.data;
	        var data = eval('(' + datas + ')');//将json格式的字符串转换成json类型
	        var type="[";
	        for (var prop in data[0])
	        {
	          if(prop!='date'){
	          type=type+"'"+prop+"',"
	          }
	        }
	        type=type.substring(0,type.length-1);
	        type+="]";
			var Frame = G2.Frame;
	        var frame = new Frame(data);
	        frame = Frame.combinColumns(frame, eval('(' + type + ')'), 'value', 'city', 'date'); 
	        var chart = new G2.Chart({
	          id: 'userData',
	          forceFit: true,
	          height: 450,
	          plotCfg: {
	            margin: [20, 20, 100, 80]
	          }
	        });
	        chart.source(frame, {
	        	 date: {
	        		 range: [0, 1],
	                 tickCount: 9
	               },
	          value: {
	            alias: '留存率(%)'
	          }
	        });
	        chart.legend({
	          position: 'bottom'
	        });
	        chart.axis('date', {
	          line: null,
	          tickLine: {
	            stroke: '#000',
	            value: 6 // 刻度线长度
	          },
	          title: null
	        });
	        chart.axis('value', {
	          tickLine: {
	            stroke: '#000',
	            value: 6 // 刻度线长度	            
	          },
	          labels: {
	            label: {
	              fill: '#000'
	            }
	          },
	          line: {
	            stroke: '#000'
	          },
	          grid: null
	        });
	        chart.line().position('date*value').color('city', ['#1f77b4', '#ff7f0e', '#2ca02c']).shape('line').size(2);
	        chart.render();
		}
	});
	}

		