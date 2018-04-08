/**
 * @desc: Angular.js项目启动配置模块文件
 * @time: 2017-06-23 12:22
 * @autohor: JustFresh
 * @version: v1.0.0
 */

var myApp = angular.module('myApp',['ui.router'],
	function($httpProvider) {
		$httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
		$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

		// Override $http service's default transformRequest
		$httpProvider.defaults.transformRequest = [ function(data) {
			var param = function(obj) {
				var query = '';
				var name, value, fullSubName, subName, subValue, innerObj, i;
				for (name in obj) {
					value = obj[name];
					if (value instanceof Array) {
						for (i = 0; i < value.length; ++i) {
							subValue = value[i];
							fullSubName = name + '[' + i + ']';
							innerObj = {};
							innerObj[fullSubName] = subValue;
							query += param(innerObj) + '&';
						}
					} else if (value instanceof Object) {
						for (subName in value) {
							subValue = value[subName];
							fullSubName = name + '[' + subName
									+ ']';
							innerObj = {};
							innerObj[fullSubName] = subValue;
							query += param(innerObj) + '&';
						}
					} else if (value !== undefined
							&& value !== null) {
						query += encodeURIComponent(name) + '='
								+ encodeURIComponent(value) + '&';
					}
				}
				return query.length ? query.substr(0,
						query.length - 1) : query;
			};
			return angular.isObject(data)
					&& String(data) !== '[object File]' ? param(data)
					: data;
		} ];
	});


/**
 * @desc: 公用的获取页面URL的？后面的参数
 * @author: JustFresh
 * @returns {Object}
 */
function GetNormalRequest() {   
   var url = location.search; //获取url中"?"符后的字串 
   var theRequest = new Object();   
   if (url.indexOf("?") != -1) {   
      var str = url.substr(1);   
      strs = str.split("&");   
      for(var i = 0; i < strs.length; i ++) {   
         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);   
      }   
   }   
   return theRequest;   
}

function translateUrl(routeUrl){
    var res = "";
    var urlArray = routeUrl.split("/");
    if(urlArray != null && urlArray.length > 0){
        for(var i=0;i<urlArray.length;i++){
            if(urlArray[i] != null && urlArray[i] != ""){
                res += urlArray[i].substring(0,urlArray[i].indexOf(".")) + ".";
            }
        }
    }
    if(res.substring(res.length-1,res.length) == "."){
        res = res.substring(0,res.length-1);
    }
    return res;
}