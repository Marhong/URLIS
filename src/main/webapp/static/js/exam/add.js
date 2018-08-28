/**
  * @Description: add.jsp页面的js函数
  * @Time: 2018年8月27日 上午11:03:51
  * @author: wangbin
*/
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/jquery.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/bootstrap.min.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/jquery-3.3.1.js'></script>");
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
//验证输入信息是否合法
function validateQuestion(){
	if($('#exam_name').val() == "" || $('#exam_name').val()== null){
		alert("考试名称不能为空!");
		return false;
	}else if($('#exam_datetime').val() == null || $('#exam_datetime').val() == ""){
		alert("考试日期不能为空!");
		return false;
	}else if(!isValid(format($('#exam_datetime').val()))){
		alert("考试日期不能为过期的日期!")
		$('#exam_datetime').val('');
		return false;
	}
	return true;
}
// 通过ajax方式提交表单信息
function summitQuestion(){
	
	var exam_name = $('#exam_name').val();
	var paper_id = $('#paper_id').val();
	var exam_datetime = format($('#exam_datetime').val());
	

	var question = {'exam_name':exam_name,'paper_id':paper_id,'exam_datetime':exam_datetime};
	$.ajax({
		url: getRootPath()+"/exam/save",
		  type:'post',
		  dataType:'json',
		  data:question,
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
		  alert("ajax error");
		  window.location.href = "add";
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
	}
	if(newdate != ""){
		return newdate;
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