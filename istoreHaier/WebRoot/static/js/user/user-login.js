$(document).ready(function() {
//	$("#dushi_logon").click(function(event) {
//		var logonId = $("#dushi_logonId").attr("value");
//		var password = $("#dushi_password").attr("value");
//		$.ajax({
//            type : 'post',
//            url : '/istore/servlet/b2c/user/logon.do',
//            dataType :"html",
//            data:{logonId:logonId,password:password},
//            success : function(msg) {
//				var timestamp = Date.parse(new Date());
//                if('success'==msg){alert(msg)
//                    location.href='/istore/servlet/b2c/user/index.do?t='+timestamp ;
//                }else{

//                    location.href='/istore/servlet/b2c/logon.html' ;
//                }
                    
                    
                    
//            }
//		})
//	   })      
	   
	   
	   
	   //用户登入
	$("#dushi_logon").click(function() {
		logon();
	});
	
	//回车事件
	document.onkeydown = function(e){
	    var ev = document.all ? window.event : e;
	    if(ev.keyCode==13) {
	    	logon();
	     }
	}
	
	
});

function getOs() 
{ 
    var OsObject = ""; 
    if(navigator.userAgent.indexOf("MSIE")>0) { 
         return "MSIE"; 
    } 
    if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){ 
         return "Firefox"; 
    } 
    if(isSafari=navigator.userAgent.indexOf("Safari")>0) { 
         return "Safari"; 
    }  
    if(isCamino=navigator.userAgent.indexOf("Camino")>0){ 
         return "Camino"; 
    } 
    if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){ 
         return "Gecko"; 
    } 
} 


function KeyDown(){
      var browser = getOs();
      if(browser=="Firefox"){
    	  $("html").die().live("keydown",function(event){     
                if(event.keyCode==13){
                	$("#dushi_logon").click();
                }     
          });   
      }
      if(browser=="" || browser=="MSIE"){
          if(event.keyCode==13){
        	  $("#dushi_logon").click();
            }
      }
}



function logon(){
	$("#error").attr("style","display:none;");
	
	var username = $("#username").val();
	if ("" == username) {
		alert("请输入账号!");
		$("#username").focus();
		return false;
	}
	var password = $("#password").val();
	if ("" == password) {
		alert("请输入密码!");
		$("#password").focus();
		return false;
	}
//	alert("/istore/servlet/b2c/user/index.do");
	$.ajax({
		url: "/istore/servlet/b2b/user/login.do",
		type: "post",
		dataType: "json",
		data: {'username': username,
		'password': password},
		success: function(data)
		{
			if (data.status == 1)
			{
				window.location.href="/istore/servlet/b2b/frame.html";
			}
			else
			{
				var mesg = data.message;
				$('#alertBox-generated').attr("style","");
				$('#alertBox-generated').text(mesg);
				$("#error").attr("style","");
				$("#username").val("");
				$("#password").val("");
				$("#username").focus();
			}
		}
	});
}