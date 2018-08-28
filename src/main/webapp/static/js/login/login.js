/**
  * @Description: login.jsp页面的js函数
  * @Time: 2018年8月28日 下午18:52:55
  * @author: wangbin
*/
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/login/min.js'></script>");
 

// 获取工程绝对路径
function getRootPath(){
	   var currentPagepath=location.href;
	   var pathName = window.document.location.pathname;
	   var pos = currentPagepath.indexOf(pathName);
	   var localhostPath = currentPagepath.substring(0,pos);
	   var projectName = pathName.substring(0,pathName.substr(1).indexOf("/")+1);
	   return localhostPath+projectName;
	   
}
// 验证登陆
function valid(){
	if($('#per_id').val() == "" || $('#per_id').val()== null){
		alert("身份证号不能为空!");
		return false;
	}else if($('#password').val() == null || $('#password').val() == ""){
		alert("密码不能为空!");
		return false;
	}
	return true;
}
//验证输入信息是否合法
function login(){
	var per_id = $('#per_id').val();
	var password = $('#password').val();
	
	var question = {'per_id':per_id,'password':password};
	$.ajax({
		url: getRootPath()+"/person/login",
		  type:'post',
		  dataType:'json',
		  data:question,
		  success:function(data){
		  	if(data.result == "success"){	
		  		window.location.href = getRootPath()+"/webindex?per_id="+per_id;
		  	}else if(data.result == "wrong"){
		  		alert("用户名或密码错误!");
		  		location.reload();
		  	}
		  },
		  error:function(){
		  alert("ajax error");
		  location.reload();
		  }
		  });
}