/**
  * @Description: add.jsp页面的js函数
  * @Time: 2018年8月25日 下午21:14:25
  * @author: wangbin
*/
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/jquery.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/bootstrap.min.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/jquery-3.3.1.js'></script>");


//获取工程绝对路径
function getRootPath(){
	   var currentPagepath=location.href;
	   var pathName = window.document.location.pathname;
	   var pos = currentPagepath.indexOf(pathName);
	   var localhostPath = currentPagepath.substring(0,pos);
	   var projectName = pathName.substring(0,pathName.substr(1).indexOf("/")+1);
	   return localhostPath+projectName;
	   
}
function getPerson(){

	var per_id = $('#per_id').val();
	var person = {'per_id':per_id};
	$.ajax({
		url: getRootPath()+"/person/getperson",
		async:false,
		  type:'post',
		  dataType:'json',
		  data:person,
		  success:function(data){
		  	if(data.person != null){
		  		$('#per_name').val(data.person.per_name);
		  		$('#dept_id').val(data.person.dept_id);
		  		$('#pos_name').val(data.person.pos_name);
		  		
		  	}
		  },
		  error:function(){
		  alert("出错了");
		  window.location.href = "endwork";
		  }
		  });
}
// 验证用户是否存在
function isExist(){

	var per_id = $('#per_id').val();
	
	var person = {'per_id':per_id};
	$.ajax({
		url: getRootPath()+"/person/verify",
		  async:true,
		  type:'post',
		  dataType:'json',
		  data:person,
		  success:function(data){
		  	if(data.result == "success"){	
		  		summitQuestion();
		  		return true;
		  	}else{
		  		alert("该员工不存在!");
		  		$('#per_id').val('');
		  		return false;
		  	}
		  },
		  error:function(){
		  alert("出错了");
		  window.location.href = "endwork";
		  }
		  });	 
}
//验证输入信息是否合法
function validateQuestion(){
	if($('#per_id').val() == "" || $('#per_id').val()== null){
		alert("身份证不能为空!");
		return false;
	}else if($('#per_name').val() == null || $('#paper_score').val() == ""){
		alert("姓名不能为空!");
		return false;
	}else if($('#dept_id').val() == null || $('#dept_id').val() == ""){
		alert("单位不能为空!");
		return false;
	}else if($('#pos_name').val() == null || $('#pos_name').val() == ""){
		alert("职务不能为空!");
		return false;
	}else if(!isExist($('#per_id').val())){
		
		return false;
	}
	return true;
}
// 通过ajax方式提交表单信息
function summitQuestion(){
	
	var per_id = $('#per_id').val();

	var question = {'per_id':per_id};
	$.ajax({
		url: getRootPath()+"/attendance/update",
		  type:'post',
		  dataType:'json',
		  data:question,
		  success:function(data){
		  	if(data.result == "success"){
		  		alert("打卡成功!");
		  		window.location.href = "endwork";
		  	}else{
		  		alert("打卡失败!");
		  		window.location.href = "endwork";
		  	}
		  },
		  error:function(){
		  alert("打卡失败");
		  window.location.href = "endwork";
		  }
		  });
		 
}

// 新添加一个问题
function save(){
	// 如果输入信息无误，则将数据传给后台
	if(validateQuestion()){
		summitQuestion();
	}
}

