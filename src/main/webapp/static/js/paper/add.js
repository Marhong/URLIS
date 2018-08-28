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
//验证输入信息是否合法
function validateQuestion(){
	if($('#paper_name').val() == "" || $('#paper_name').val()== null){
		alert("考卷名称不能为空!");
		return false;
	}else if($('#paper_score').val() == null || $('#paper_score').val() == ""){
		alert("考卷分数不能为空!");
		return false;
	}else if($('#paper_time').val() == null || $('#paper_time').val() == ""){
		alert("考试时长不能为空!");
		return false;
	}else if(($('#choiceItems').text() != "全部选好" || $('#judgementItems').text() != "全部选好") ){
		alert("必须先选好全部题目!");
		return false;
	}
	var ids= "";
	$('#table tbody input[type="checkbox"] ').each(function(){
	if($(this).is(':checked')){
		ids += $(this).val()+",";
	}
});
	$('#paper_id').val(ids);
	return true;
}
// 通过ajax方式提交表单信息
function summitQuestion(){
	
	var paper_id = $('#paper_id').val();
	var paper_name = $('#paper_name').val();
	var paper_score = $('#paper_score').val();
	var paper_time = $('#paper_time').val();
	var paper_remark = $('#paper_remark').val();

	var question = {'paper_id':paper_id,'paper_name':paper_name,'paper_score':paper_score,'paper_time':paper_time,'paper_remark':paper_remark};
	$.ajax({
		url: getRootPath()+"/paper/save",
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
		  alert("添加失败");
		  window.location.href = "add";
		  }
		  });
		 
}


// 根据不同选择，显示不同类型的题目
	function type(){
		
		var type = $('#type').val();
		if(type == "选择题"){
			$('#table tbody tr ').each(function(){
				if($(this).children("td").eq(3).text() == "判断题"){
					$(this).hide();
				}else{
					$(this).show();
				}
			});
		}else{
			$('#table tbody tr ').each(function(){
				if($(this).children("td").eq(3).text() == "选择题"){
					$(this).hide();
				}else{
					$(this).show();
				}
			});
		}
	}
    function initPage(){
    	
		$('#table tbody input[type="checkbox"] ').each(function(){
			var question = $(this).parent().parent().children("td").eq(2);
			if(question.text().length>30){
				question.text(question.text().substring(0,30)+"....");
			}
		});
    }
	// 根据是否选中一个题目，更新已经选中的题目数量
	function choose(choice,type){	
		var choiceItems = $('#choiceItems');
		var judgementItems = $('#judgementItems');

		if(choice){
			if(type == "选择题"){
				
				choiceItems.text(choiceItems.text()-1);
			}else{
				judgementItems.text(judgementItems.text()-1);
			}	
		}else{
			
			if(type == "选择题"){
				if(choiceItems.text() == "全部选好"){
					choiceItems.text(1);
				}else{
					choiceItems.text(1+parseInt(choiceItems.text()));
				}
				
			}else{
				if(judgementItems.text() == "全部选好"){
					judgementItems.text(1);
				}else{
					judgementItems.text(1+parseInt(judgementItems.text()));
				}		
			}
		}
		if(choiceItems.text() == 0){
			choiceItems.text("全部选好");
			choiceItems.css('color','red');
			choiceItems.css('font-size',15);
		}
		if(judgementItems.text() == 0){
			judgementItems.text("全部选好");
			judgementItems.css('color','red');
			judgementItems.css('font-size',15);
		}


	}
// 新添加一个问题
function save(){
	// 如果输入信息无误，则将数据传给后台
	if(validateQuestion()){
		summitQuestion();
	}
}
