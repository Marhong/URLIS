/**
  * @Description: add.jsp页面的js函数
  * @Time: 2018年8月27日 下午19:15:31
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
// 根据月份和身份证确定该人员该月的分数
function generate(){
	$('#generate').parent().parent().hide();
	var per_id = $('#per_id').val();
	
	var per_month = format($('#per_month').val());
	
	var today = new Date();
	var per_year =  today.getFullYear();
	var yearmon = per_year+"-"+per_month;
	getRightName();
	calExamScore(per_id,yearmon);
	calAttnScore(per_id,yearmon);
	
	
}

// 计算该月的考试平均分
function calExamScore(per_id,yearmon){
	
	var question = {'per_id':per_id,'yearmon':yearmon};
	$.ajax({
		url: getRootPath()+"/assessment/calAssessmentScore",
		  type:'post',
		  async:'false',
		  dataType:'json',
		  data:question,
		  success:function(data){
		  	$('#exam_score').val(data.score);
		  },
		  error:function(){
		  alert("ajax error");
		  window.location.href = "add";
		  }
		  });
}
// 计算该月的考勤分
function calAttnScore(per_id,yearmon){
	var question = {'per_id':per_id,'yearmon':yearmon};
	$.ajax({
		url: getRootPath()+"/assessment/calAttendanceScore",
		  type:'post',
		  async:'false',
		  dataType:'json',
		  data:question,
		  success:function(data){
		  	$('#attn_score').val(data.score);
		
		  	var asse_score = accAdd(accMul($('#exam_score').val(),0.7),accMul($('#attn_score').val(),0.3));
			$('#asse_score').val(asse_score);
			setName();
		  },
		  error:function(){
		  alert("ajax error");
		  window.location.href = "add";
		  }
		  });
}
//验证输入信息是否合法
function validateQuestion(){
	if($('#per_name').val() == "" || $('#per_name').val()== null){
		alert("姓名不能为空!");
		return false;
	}if($('#asse_score').val() == "" || $('#asse_score').val()== null){
		alert("分数不能为空!");
		return false;
	}else if(!isExist($('#per_id'))){
		
		return false;
	}
	return true;
}
// 通过ajax方式提交表单信息
function summitQuestion(){
	var per_name = $('#per_name').val();
	var per_id = $('#per_id').val();
	var asse_score = $('#asse_score').val();
	var today = new Date();
	var per_year =  today.getFullYear();
	var per_month = format($('#per_month').val());
	var asse_date = per_year +"-"+per_month;

	var question = {'per_name':per_name,'per_id':per_id,'asse_score':asse_score,'asse_date':asse_date};
	$.ajax({
		url: getRootPath()+"/assessment/save",
		  type:'post',
		  dataType:'json',
		  data:question,
		  success:function(data){
		  	if(data.result == "success"){
		  		alert("添加成功!");
		  		window.location.href = "index";
		  	}else if(data.result == "exist"){
		  		alert("此人员该月考核记录已经生成!");
		  		window.location.href = "index";
		  	}else{
		  		alert("添加失败!");
		  		 window.location.href = "add";
		  	}
		  },
		  error:function(){
			  alert("error!");
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

// 将通过bootstrap控件得到的日期转为规范的日期格式
function format(date){
	var newdate = "";
		switch(date){
			case "一月":
				newdate += "01";
				break;
			case "二月":
				newdate += "02";
				break;
			case "三月":
				newdate += "03";
				break;
			case "四月":
				newdate += "04";
				break;
			case "五月":
				newdate += "05";
				break;
			case "六月":
				newdate += "06";
				break;
			case "七月":
				newdate += "07";
				break;
			case "八月":
				newdate += "08";
				break;
			case "九月":
				newdate += "9";
				break;
			case "十月":
				newdate += "10";
				break;
			case "十一月":
				newdate += "11";
				break;
			case "十二月":
				newdate += "12";
				break;
		}
	if(newdate != ""){
		return newdate;
	}
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

		  	if(data.result == "success" && data.person != null){
		  		
		  		summitQuestion();
		  		
		  	}else{
		  		alert("该身份证对应账号不存在!");
		  		window.location.href = "add";
		  	}
		  },
		  error:function(){
		  alert("出错了");
		  window.location.href = "add";
		  }
		  });	 
}
function getRightName(){
	var per_id = $('#per_id').val();
	
	var person = {'per_id':per_id};
	$.ajax({
		url: getRootPath()+"/person/verify",
		async:false,
		  type:'post',
		  dataType:'json',
		  data:person,
		  success:function(data){

		  	if(data.result == "success" && data.person != null){
		  		
		  		$('#per_name').val(data.person.per_name);
		  		
		  	}else{
		  		alert("该身份证对应账号不存在!");
		  		window.location.href = "add";
		  	}
		  },
		  error:function(){
		  alert("出错了");
		  window.location.href = "add";
		  }
		  });	
}
function getAttnScore(per_id,yearmon){
	var question = {'per_id':per_id,'yearmon':yearmon};
	$.ajax({
		url: getRootPath()+"/assessment/calAttendanceScore",
		  type:'post',
		  async:'false',
		  dataType:'json',
		  data:question,
		  success:function(data){
		  	$('#attn_score').val(data.score);
			setName();
		  },
		  error:function(){
		  alert("ajax error");
		  window.location.href = "add";
		  }
		  });
}
function initPage(){
	var asse_date = $("#asse_date").val();
	var yearmon = asse_date.split("-")[0]+"-"+asse_date.split("-")[1];
	$('#asse_date').val(asse_date.split("-")[0]+"年"+asse_date.split("-")[1]+"月份");
	var per_id=$("#per_id").val();
	calExamScore(per_id,yearmon);
	getAttnScore(per_id,yearmon);
}
    function setCurMon(){
    	var today = new Date();
    	var month = today.getMonth()+1;
    	var per_month="";
    	switch(month){
    	case 1:
    		per_month = "一月";
    		break;
    	case 2:
    		per_month = "二月";
    		break;
    	case 3:
    		per_month = "三月";
    		break;
    	case 4:
    		per_month = "四月";
    		break;
    	case 5:
    		per_month = "五月";
    		break;
    	case 6:
    		per_month = "六月";
    		break;
    	case 7:
    		per_month = "七月";
    		break;
    	case 8:
    		per_month = "八月";
    		break;
    	case 9:
    		per_month = "九月";
    		break;    	
    	case 10:
        		per_month = "十月";
        		break;
        case 11:
        		per_month = "十一月";
        		break;
        case 12:
        		per_month = "十二月";
        		break;
        		
    	}
    	$("#per_month").val(per_month);
    }
// 加法运算
function accAdd(arg1,arg2){
	var r1,r2,m;
	try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
	try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
	m=Math.pow(10,Math.max(r1,r2))
	return (arg1*m+arg2*m)/m
	}
// 乘法运算
function accMul(arg1,arg2)
{
var m=0,s1=arg1.toString(),s2=arg2.toString();
try{m+=s1.split(".")[1].length}catch(e){}
try{m+=s2.split(".")[1].length}catch(e){}
return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
}