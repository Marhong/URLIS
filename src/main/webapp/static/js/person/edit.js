/**
  * @Description: edit.jsp页面的js函数
  * @Time: 2018年8月23日 下午20:42:28
  * @author: wangbin
*/

document.write("<script language='javascript' src='"+getRootPath()+"/static/js/jquery.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/bootstrap.min.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/bootstrap-datetimepicker.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/bootstrap-datetimepicker.zh-CN.js'></script>");
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
function update(){
	// 如果输入信息无误，则将数据传给后台
//	if(validateQuestion()){
//		summitQuestion();
//	}
	summitQuestion();
}
//通过ajax方式提交表单信息
function summitQuestion(){
	
	var per_name = $('#per_name').val();
	var per_id = $('#per_id').val();
	var per_gender = $('#per_gender').val();
	
	var per_birthday = format($('#per_birthday').val());
	//var per_birthday = "2018-05-25";
	var per_nation = $('#per_nation').val();
	var per_politics = $('#per_politics').val();
	var per_state = $('#per_state').val();
	var per_tel = $('#per_tel').val();
	var password = $('#password').val();
	
	var per_worktime = format($('#per_worktime').val());
	//var per_worktime = "2015-06-21";
	var work_time = $('#work_time').val();
	var dept_id = $('#dept_id').val();
	var pos_name = $('#pos_name').val();
	var work_duty = $('#work_duty').val();
	var per_remark = $('#per_remark').val();
	
	var person = {'per_name':per_name,'per_id':per_id,'per_gender':per_gender,'per_birthday':per_birthday,'per_nation':per_nation,
			'per_politics':per_politics,'per_state':per_state,'per_tel':per_tel,'password':password,'per_worktime':per_worktime,
			'work_time':work_time,'dept_id':dept_id,'pos_name':pos_name,'work_duty':work_duty,'per_remark':per_remark};
	
	$.ajax({
		url: getRootPath()+"/person/update",
		async:false,
		  type:'post',
		  dataType:'json',
		  data:person,
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
		  alert("出错了");
		  window.location.href = "index";
		  }
		  });	 
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
//根据传来的值初始化页面
function initPage() {
	$per_gender = $('#per_gender_value');
	$per_nation = $('#per_nation_value');
	$per_politics = $('#per_politics_value');
	$per_state = $('#per_state_value');
	$dept_id = $('#dept_id_value');
	$work_duty = $('#work_duty_value');
	$per_remark = $('#per_remark_value');
	$("#per_gender").val($per_gender.val());
	$("#per_nation").val($per_nation.val());
	$("#per_politics").val($per_politics.val());
	$("#per_state").val($per_state.val());
	$("#dept_id").val($dept_id.val());
	$("#work_duty").val($work_duty.val());
	$("#per_remark").val($per_remark.val());
	
}
// 将通过bootstrap控件得到的日期转为规范的日期格式
function format(date){
	
	var token = date.split(" ");
	var newdate = "";
	if(token.length == 3){
		
		newdate += token[2]+"-";
		switch(token[1]){
			case "一月":
				newdate += "01-";
				break;
			case "二月":
				newdate += "02-";
				break;
			case "三月":
				newdate += "03-";
				break;
			case "四月":
				newdate += "04-";
				break;
			case "五月":
				newdate += "05-";
				break;
			case "六月":
				newdate += "06-";
				break;
			case "七月":
				newdate += "07-";
				break;
			case "八月":
				newdate += "08-";
				break;
			case "九月":
				newdate += "9-";
				break;
			case "十月":
				newdate += "10-";
				break;
			case "十一月":
				newdate += "11-";
				break;
			case "十二月":
				newdate += "12-";
				break;
		}
		newdate += token[0];
		return newdate;
	}else{
		return date;
	}

}