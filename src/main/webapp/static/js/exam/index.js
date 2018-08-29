/**
  * @Description: index.jsp页面的js函数
  * @Time: 2018年8月27日 上午09:06:20
  * @author: wangbin
*/
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/jquery.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/jquery.sorted.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/bootstrap.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/ckform.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/common.js'></script>");
//获取工程绝对路径
function getRootPath(){
	   var currentPagepath=location.href;
	   var pathName = window.document.location.pathname;
	   var pos = currentPagepath.indexOf(pathName);
	   var localhostPath = currentPagepath.substring(0,pos);
	   var projectName = pathName.substring(0,pathName.substr(1).indexOf("/")+1);
	   return localhostPath+projectName;
	   
}


// 根据问题内容和问题类别查询问题

    function search(){
    	var exam_name = $('#exam_name').val();
		var params = {'exam_name':exam_name};
		$.ajax({
			url: getRootPath()+"/exam/search",
			  async:false,
			  type:'post',
			  dataType:'json',
			  data:params,
			  success:function(data){
				  repaintTable(data.examlist);
			  },
			  error:function(){
			  alert(data.result);
			  window.location.href = "index";
			  }
			  });	
    }
    // 删除一个问题
	function del(id)
	{	
		if(confirm("确定要删除吗？"))
		{
			var params = {'qu_id':id};
			$.ajax({
				url: getRootPath()+"/question/deleteItem",
				  async:false,
				  type:'post',
				  dataType:'json',
				  data:params,
				  success:function(data){
				  	if(data.result == "success"){
				  		alert("删除成功!");
				  		window.location.href = "index";
				  	}else{
				  		alert("删除失败!");
				  		window.location.href = "index";
				  	}
				  },
				  error:function(){
				  alert(data.result);
				  window.location.href = "index";
				  }
				  });		
		
		}
	}
    // 删除多个问题
	function deleteSome(url)
	{
		
		var ids ="";
		
		$('#table tbody input[type="checkbox"] ').each(function(){
			if($(this).is(':checked')){
				ids += $(this).val()+",";
			}
		});
		if(confirm("确定要删除吗？"))
		{
			var params = {'ids':ids};
			$.ajax({
				url: getRootPath()+url,
				  async:false,
				  type:'post',
				  dataType:'json',
				  data:params,
				  success:function(data){
				  	if(data.result == "success"){
				  		alert("删除成功!");
				  		window.location.href = "index";
				  	}else{
				  		alert("删除失败!");
				  		window.location.href = "index";
				  	}
				  },
				  error:function(){
				  alert(data.result);
				  window.location.href = "index";
				  }
				  });		
		
		}
	}
    // 全选或全部选所有checkbox
	function checkAll() {   
		var check = false;
		if($('#selectAll').is(':checked')){
			check = true;	
		}else{
			check  = false;		
		}
	    if (check) {                
	        $('#table').find("input[type='checkbox']").prop("checked", true);        
	    } else {                
	        $('#table').find("input[type='checkbox']").prop("checked", false);        
	    }
	}
    // 重绘表格
    function repaintTable(qulist){
    
    	if(qulist!=null){
    	  	var tbody = "";
       	 //下面使用each进行遍历
           $.each(qulist, function (n, value) {
           	$('#table tbody').html("");
               var trs = "";
               var state = "";
               if(isValid(value.exam_datetime)){
            	   state = "尚未进行";
               }else{
            	   state = "结束";
               }
               trs = "<tr><td><input type='checkbox' value="+value.exam_id+"></checkbox>"+(n+1)+"</td>"+"<td>"+value.exam_id+"<td>"+value.exam_name+"</td>"+"<td>"+value.paper_id+"<td>"+value.exam_datetime+"</td><td>"+state+"</td>"+
               "<td><a href='/URLIS/examrecord/index?exam_id="+value.exam_id+"'>查看成绩</a></td></tr>";
               tbody += trs;
           });

           $("#table").append(tbody);
           $('#kkpager').hide();
           initPage();
    	}else{
    		alert("暂无数据");
    		
    	}
  
    }

	// 判断该考试日期是否合法(在当天或当天之后)
	function isValid(date){
		var today = new Date();
		var year = today.getFullYear();
	    var month = today.getMonth() + 1;
        var day = today.getDate();
     
        var tokens = date.split("-");
        if(tokens[0]>year){
        	return true;
        }else if(tokens[0] == year){
        	if(tokens[1] > month || tokens[1].charAt(1) > month){
        		return true;
        	}else if(tokens[1] == month || tokens[1].charAt(1) == month){
        		if(tokens[2] > day || tokens[2].charAt(1) > day){
        			return true;
        		}
        	}
        }
        return false;         
	}
	// 初始化首页，考试日期在今天之前均为已经结束的考试
	function initPage() {
		$('#table tbody tr').each(function(){
			var datetime = $(this).children("td").eq(4);
			
			if(isValid(datetime.text())){
				$(this).children("td").eq(5).text("尚未进行");
				$(this).children("td").eq(6).text("暂无成绩");
				$(this).find("td:last").find("a:first").attr('href','#');
			}else{
				$(this).children("td").eq(5).text("结束");
			}
		});
	}