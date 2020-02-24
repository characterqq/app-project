function Map() {   
    /** ��ż�������(�����õ�) */  
    this.keys = new Array();   
    /** ������� */  
    this.data = new Object();   
       
    /**  
     * ����һ����ֵ��  
     * @param {String} key  
     * @param {Object} value  
     */  
    this.put = function(key, value) {   
        if(this.data[key] == null){   
            this.keys.push(key);   
        }   
        this.data[key] = value;   
    };   
       
    /**  
     * ��ȡĳ����Ӧ��ֵ  
     * @param {String} key  
     * @return {Object} value  
     */  
    this.get = function(key) {   
        return this.data[key];   
    };   
       
    /**  
     * ɾ��һ����ֵ��  
     * @param {String} key  
     */  
    this.remove = function(key) { 
    	for(var i=0;i<this.keys.length;i++){
    		if(this.keys[i] == key){
    			this.keys.splice(i,1);
    	        this.data[key] = null;
    	        break;
    		}
    	}
    };   
       
    /**  
     * ����Map,ִ�д�����  
     *   
     * @param {Function} �ص����� function(key,value,index){..}  
     */  
    this.each = function(fn){   
        if(typeof fn != 'function'){   
            return;   
        }   
        var len = this.keys.length;   
        for(var i=0;i<len;i++){   
            var k = this.keys[i];   
            fn(k,this.data[k],i);   
        }   
    };   
       
    /**  
     * ��ȡ��ֵ����(����Java��entrySet())  
     * @return ��ֵ����{key,value}������  
     */  
    this.entrys = function() {   
        var len = this.keys.length;   
        var entrys = new Array(len);   
        for (var i = 0; i < len; i++) {   
            entrys[i] = {   
                key : this.keys[i],   
                value : this.data[i]   
            };   
        }   
        return entrys;   
    };   
       
    /**  
     * �ж�Map�Ƿ�Ϊ��  
     */  
    this.isEmpty = function() {   
        return this.keys.length == 0;   
    };   
       
    /**  
     * ��ȡ��ֵ������  
     */  
    this.size = function(){   
        return this.keys.length;   
    };   
       
    /**  
     * ��дtoString   
     */  
    this.toString = function(){   
        var s = "{";   
        for(var i=0;i<this.keys.length;i++,s+=','){   
            var k = this.keys[i];   
            s += k+"="+this.data[k];   
        }   
        s+="}";   
        return s;   
    };   
}   
