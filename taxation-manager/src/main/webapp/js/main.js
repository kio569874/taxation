var myApp = angular.module("myApp",
    [
        "ui.router"
    ]);
    
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