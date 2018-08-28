/**
  * @Description: add.jsp页面的js函数
  * @Time: 2018年8月24日 下午19:51:28
  * @author: wangbin
*/


document.write("<script language='javascript' src='"+getRootPath()+"/static/js/jquery.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/bootstrap.min.js'></script>");

//获取工程绝对路径
function getRootPath(){
	   var currentPagepath=location.href;
	   var pathName = window.document.location.pathname;
	   var pos = currentPagepath.indexOf(pathName);
	   var localhostPath = currentPagepath.substring(0,pos);
	   var projectName = pathName.substring(0,pathName.substr(1).indexOf("/")+1);
	   return localhostPath+projectName;
	   
}
//新添加一个问题
function save(){	
	
	// 如果输入信息无误，则将数据传给后台
	if(validateQuestion()){
		summitQuestion();
	}
}
//通过ajax方式提交表单信息
function summitQuestion(){
	
	var dept_id = $('#dept_id').val();
	var dept_name = $('#dept_name').val();

	var person = {'dept_id':dept_id,'dept_name':dept_name};
	
	$.ajax({
		url: getRootPath()+"/department/save",
		async:false,
		  type:'post',
		  dataType:'json',
		  data:person,
		  success:function(data){
		  	if(data.result == "success"){
		  		alert("添加成功!");
		  		window.location.href = "index";
		  	}else{
		  		alert("添加失败!");
		  		window.location.href = "add";
		  	}
		  },
		  error:function(){
		  
		  window.location.href = "add";
		  }
		  });	 
}
//验证输入信息是否合法
function validateQuestion(){
	
	if($('#dept_id').val() == "" || $('#dept_id').val()== null){
		alert("机构编号不能为空!");
		return false;
	}else if($('#dept_name').val() == null || $('#dept_name').val() == ""){
		alert("机构名称不能为空!");
		return false;
	}else if($('#dept_id').val().length > 20){
		alert("机构编号长度不能超过20!");
		return false;
	}else if(isExist($('#dept_id').val())){
		
		alert("该机构已被注册!");
		return false;
	}
	return true;
}
// 验证该id是否已经注册过
function isExist(){

	var per_id = $('#dept_id').val();
	
	var person = {'per_id':per_id};
	$.ajax({
		url: getRootPath()+"/department/verify",
		async:false,
		  type:'post',
		  dataType:'json',
		  data:person,
		  success:function(data){
		  	if(data.result == "success"){
		  		alert("该机构已被注册!");
		  		window.location.href = "add";
		  	}else{
		  		return false;
		  	}
		  },
		  error:function(){
		 
		  window.location.href = "add";
		  }
		  });	 
}