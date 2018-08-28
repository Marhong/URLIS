/**
  * @Description: index.jsp页面的js函数
  * @Time: 2018年8月27日 下午18:49:10
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
    
    	var per_name = $('#per_name').val();
		var params = {'per_name':per_name};
		if(per_name != null && per_name != ""){

		$.ajax({
			url: getRootPath()+"/assessment/search",
			  async:false,
			  type:'post',
			  dataType:'json',
			  data:params,
			  success:function(data){
				  repaintTable(data.assesslist);
			  },
			  error:function(){
			  alert(data.result);
			  window.location.href = "index";
			  }
			  });	
		
		}
    }
    // 删除一个问题
	function del(asse_id)
	{	
		
		if(confirm("确定要删除吗？"))
		{
			var params = {'asse_id':asse_id};
			$.ajax({
				url: getRootPath()+"/assessment/deleteItem",
				  async:false,
				  type:'post',
				  dataType:'json',
				  data:params,
				  success:function(data){
				  	if(data.result == "success"){
				  		alert("删除成功!");
				  		
				  	}else{
				  		alert("删除失败!");
				  		
				  	}
				  	window.location.href = "index";
				  },
				  error:function(){
				  alert(data.result);
				  window.location.href = "index";
				  }
				  });		
		
		}
	}
    // 删除多个问题
	function deleteSome()
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
				url: getRootPath()+"/assessment/deleteSome",
				  async:false,
				  type:'post',
				  dataType:'json',
				  data:params,
				  success:function(data){
				  	if(data.result == "success"){
				  		alert("删除成功!");
				  	
				  	}else{
				  		alert("删除失败!");	
				  	}
				  	window.location.href = "index";
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

               trs = "<tr><td><input type='checkbox' value="+value.asse_id+"></checkbox>"+(n+1)+"</td>"+"<td>"+value.per_name+"<td>"+value.per_id+"</td>"+"<td>"+value.asse_date+"<td>"+value.asse_score+"</td>"+
               "<td><a href='detail?asse_id="+value.asse_id+"'>查看</a>&nbsp;&nbsp;&nbsp;"+
               "<a href='index' onclick='del(\""+value.asse_id+"\",\""+value.per_id+"\")'>删除</a></td></tr>";
               tbody += trs;
           });

           $("#table").append(tbody);
    	}else{
    		alert("暂无数据");		
    	}
  
    }
    
    // 初始化页面
    function initPage(){
    	
    	var today = new Date();
    	var day = today.getDate();
    	if(day == 28){
    		$("#generate").show();
    	}else{
    		$("#generate").hide();
    	}
    }


