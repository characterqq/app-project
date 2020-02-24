/**
 * 倒计时插件 
 * 调用 $(".fnTimeCountDown").fnTimeCountDown(overTimeName);
 * overTimeName=回调函数名
 */
var t=true;
$.extend($.fn,
		{fnTimeCountDown:function(overTimeName){
            this.each(function(){
                var $this = $(this);
                var o = {
                    hm: $this.find(".hm"),
                    sec: $this.find(".sec"),
                    mini: $this.find(".mini"),
                    hour: $this.find(".hour")
                };
                var f = {
                    haomiao: function(n){
                        if(n < 10)return "0" + n.toString();
                        if(n < 100)return n.toString();
                        return n.toString().substr(0,2);
                    },
                    zero: function(n){
                        var _n = parseInt(n, 10);//解析字符串,返回整数
                        if(_n > 0){
                            if(_n <= 9){
                                _n = "0" + _n
                            }
                            return String(_n);
                        }else{
                            return "00";
                        }
                    },
                    dv: function(){
                        //d = d || Date.UTC(2050, 0, 1); //如果未定义时间，则我们设定倒计时日期是2050年1月1日
                        var _d = $this.data("end");
                        var now = new Date(),
                            endDate = new Date(_d);
                        //现在将来秒差值
                        //alert(future.getTimezoneOffset());
                        var dur = (endDate - now.getTime()) / 1000 , mss = endDate - now.getTime() ,pms = {
                            hm:"000",
                            sec: "00",
                            mini: "00",
                            hour: "00"
                        };
                        if(mss > 0){
                        	var hour=Math.floor((dur / 3600)) > 0? f.zero(Math.floor((dur / 3600)) % 24) : "00";
                        	if(Math.floor((dur / 3600)) % 24>1){
                        		pms.mini=hour;
                        		pms.sec =Math.floor((dur / 60)) > 0? f.zero(Math.floor((dur / 60)) % 60) : "00";
                        		pms.hm =f.zero(dur % 60);
                        	}else{
//	                        	pms.hour = Math.floor((dur / 3600)) > 0? f.zero(Math.floor((dur / 3600)) % 24) : "00";
	                        	pms.mini = Math.floor((dur / 60)) > 0? f.zero(Math.floor((dur / 60)) % 60) : "00";
	                        	pms.sec = f.zero(dur % 60);
	                            pms.hm = f.haomiao(mss % 1000);
                        	}
                        }else{
                            pms.mini=pms.sec="00";
                            pms.hm = "00";
                            t=false;
                            return pms;
                        }
                        return pms;
                    },
                    ui: function(){
                        if(o.hm){
                            o.hm.html(f.dv().hm);
                        }
                        if(o.sec){
                            o.sec.html(f.dv().sec);
                        }
                        if(o.mini){
                            o.mini.html(f.dv().mini);
                        }
                        if(o.hour){
                            o.hour.html(f.dv().hour);
                        }
                        if(o.day){
                            o.day.html(f.dv().day);
                        }
                        if(o.month){
                            o.month.html(f.dv().month);
                        }
                        if(o.year){
                            o.year.html(f.dv().year);
                        }
                        if(t){
                          setTimeout(f.ui, 1);
                        }else{//时间停止，执行回调事件
                          eval(overTimeName+'()');
                        }
                    }
                };
                f.ui();
            });
        }
 });
