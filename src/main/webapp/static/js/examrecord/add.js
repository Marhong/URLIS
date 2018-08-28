/**
  * @Description: add.jsp页面的js函数
  * @Time: 2018年8月27日 下午14:48:48
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
//验证输入信息是否合法
function validateQuestion(){
	if($('#per_id').val() == "" || $('#per_id').val()== null){
		alert("身份证不能为空!");
		return false;
	}else if($('#exam_score').val() == null || $('#exam_score').val() == ""){
		alert("成绩不能为空!");
		return false;
	}else if(!isExist()){	
		return false;
	}
	return true;
}
//验证该id是否已经注册过
function isExist(){

	var per_id = $('#per_id').val();
	
	var person = {'per_id':per_id};
	$.ajax({
		url: getRootPath()+"/person/verify",
		async:false,
		  type:'post',
		  dataType:'json',
		  data:person,
		  success:function(data){
		  	if(data.result == "success"){
		  		summitQuestion();
		  		return true;
		  	}else{
		  		alert("该身份证对应账号不存在!");
		  		 window.location.href = "add";
		  		return false;
		  	}
		  },
		  error:function(){
		  alert("出错了");
		  window.location.href = "add";
		  }
		  });	 
}
// 通过ajax方式提交表单信息
function summitQuestion(){
	
	var per_id = $('#per_id').val();
	var exam_id = $('#exam_id').val();
	var exam_score = $('#exam_score').val();
	

	var question = {'per_id':per_id,'exam_id':exam_id,'exam_score':exam_score,'per_name':'','posname':'','dept_name':'','exam_name:':''};
	
	$.ajax({
		url: getRootPath()+"/examrecord/save",
		  type:'post',
		  dataType:'json',
		  data:question,
		  success:function(data){
		  	if(data.result == "success"){
		  		alert("添加成功!");
		  		window.location.href = "index?exam_id="+exam_id;
		  	}else{
		  		alert("添加失败!");
		  		window.location.href = "add";
		  	}
		  },
		  error:function(){
		  alert("ajax error");
		  window.location.href = "add";
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
//根据传来的值初始化页面
function initPage() {
	$type = $('#qu_type_value');
	$answer = $('#qu_answer_value');

	$("#qu_type").val($type.val());
	$("#qu_answer").val($answer.val());
	if ($type.val() == "判断题") {
		$('#optionC').parent().parent().hide();
		$('#optionD').parent().parent().hide();
		$('#optionc').hide();
		$('#optiond').hide();
	}
}

	// 判断该考试日期是否合法(在当天或当天之后)
	function isValid(date){
		var today = new Date();
		var year = today.getFullYear();
	    var month = today.getMonth() + 1;
        var day = today.getDate();
     
        var tokens = date.split("-");
        if(tokens[0]<year){
        
        	return false;
        }else if(tokens[1].charAt(0) != 0  ){
        	if(tokens[1] < month){
            	
            	return false;
        	}
        }else if(tokens[1].charAt(0) == 0 ){
        	if(tokens[1].charAt(1) < month){
        		
            	return false;
        	}  
        }
        if(tokens[2].charAt(0) != 0 ) {	
        	if(tokens[2] < day){
            	
            	return false;
        	} 	
        }else if(tokens[2].charAt(0) == 0 ){
        	
        	if( tokens[2].charAt(1) < day){
        	
            	return false;
        	}	
        }
        return true;
        
        
	}