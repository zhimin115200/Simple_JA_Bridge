/**
 * zhimin115200
 * android.call是js调用android的入口
 * js.call是android调用js
 */
PROTOTYPE = "JA_Bridge";
CALLBACK=[];
var Util = {
		getPort: function () {
			return Math.floor(Math.random() * (1 << 30));
		},
		getUri:function( method, params, port){
			params = this.getParam(params);
			var uri = PROTOTYPE + '://' + method + ':' + port + '?' + params;
			return uri;
		},
		getParam:function(params){
			if (params && typeof params === 'object') {
				return JSON.stringify(params);
			} else {
				return params || '';
			}
		}
};

var android = {
		/**
		 * method：调用android的方法名，params：传递的数据 ， callback：回调函数
		 */
		call:function( method, params, callback){
			var port = Util.getPort();
			var uri = Util.getUri(method, params, port);
			CALLBACK[port] = callback;//缓存回调函数
			window.prompt(uri, "");//调用prompt，在webchromeclient中的onjsprompt被调用
		}
};

var js = {
		/**
		 * port：回调函数的索引，jsonObj：回调的数据
		 */
		call:function( port, jsonObj){
			var callback = CALLBACK[port];
			callback && callback(jsonObj);
            delete CALLBACK[port];
		}
};
