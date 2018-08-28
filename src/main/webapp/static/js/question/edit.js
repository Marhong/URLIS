/**
  * @Description: edit.jsp页面的js函数
  * @Time: 2018年8月23日 上午12:22:25
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
	if($('#qu_content').val() == "" || $('#qu_content').val()== null){
		alert("题目名不能为空!");
		return false;
	}else if($('#optionA').val() == null || $('#optionA').val() == ""){
		alert("选项A不能为空!");
		return false;
	}else if($('#optionB').val() == null || $('#optionB').val() == ""){
		alert("选项B不能为空!");
		return false;
	}else if(($('#optionC').val() == null || $('#optionC').val() == "") &&  $('#optionC').is(':visible')){
		
		alert("选项C不能为空!");
		return false;
	}else if(($('#optionD').val() == null || $('#optionD').val() == "") && $('#optionD').is(':visible')){
		alert("选项D不能为空!");
		return false;
	}else if($('#qu_score').val() == "" || $('#qu_score').val() == null){
		alert("分数不能为空!");
		return false;
	}
	return true;
}
// 通过ajax方式提交表单信息
function summitQuestion(){
	
	var qu_id = $('#qu_id_value').val();
	var qu_content = $('#qu_content').val();
	var qu_type = $('#qu_type').val();
	var qu_option = $('#qu_option').val();
	var qu_score = $('#qu_score').val();
	var qu_answer = $('#qu_answer').val();
	var question = {'qu_id':qu_id,'qu_content':qu_content,'qu_type':qu_type,'qu_option':qu_option,'qu_score':qu_score,'qu_answer':qu_answer};
	$.ajax({
		url: getRootPath()+"/question/update",
		  type:'post',
		  dataType:'json',
		  data:question,
		  success:function(data){
		  	if(data.result == "success"){
		  		alert("修改成功!");
		  		window.location.href = "index";
		  	}else{
		  		alert("修改失败!");
		  		window.location.href = "index";
		  	}
		  },
		  error:function(){
		  alert("ajax error");
		  window.location.href = "index";
		  }
		  });
		 
}

// 根据不同类型题目显示不同选项数目
function change(){
	var type = $('#qu_type').val();
	
	if(type == "判断题"){
	
		$('#optionC').parent().parent().hide();
		$('#optionD').parent().parent().hide();
		$('#optionc').hide();
		$('#optiond').hide();
		
	}else{
		
		$('#optionC').parent().parent().show();
		$('#optionD').parent().parent().show();
		$('#optionc').show();
		$('#optiond').show();
	}
}

// 新添加一个问题
function update(){
	
	// 将四个选项的值组合到一起成为qu_option
	$qu_option = $('#qu_option');
	var option = $('#optionA').val() + ";"
			+ $('#optionB').val() + ";" + $('#optionC').val()
			+ ";" + $('#optionD').val();
	$qu_option.val(option);
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