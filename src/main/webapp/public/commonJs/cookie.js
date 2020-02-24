var common=$;
common.setCookie = function(cookieName, cookieValue, expiresNum) {
	        var options = {
	            'path': '/',
//	            'domain': common.globals.domain,
	            'secure': false,//关闭https传输cookie
	            'raw': true,//关闭cookie的自动编码功能
	            'expires': expiresNum || 30 //cookie的过期时间，如没有传值默认30天过期
	        };
	
	        $.cookie(cookieName, cookieValue, options);
	        
	    }
	
common.getCookie = function(cookieName) {
	        return $.cookie(cookieName);
	    }
	
common.getToken = function() {
	        return $.cookie('token');
	    }
	
common.delCookie = function(cookieName) {
	        $.cookie(cookieName, '', {
	            'path': '/',
	            'expires': -1
	        });
	    }
