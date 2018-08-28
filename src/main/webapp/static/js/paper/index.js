/**
  * @Description: index.jsp页面的js函数
  * @Time: 2018年8月23日 上午11:35:55
  * @author: wangbin
*/
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/jquery.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/jquery.sorted.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/bootstrap.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/ckform.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/common.js'></script>");




// 获取工程绝对路径
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
    	var paper_name = $('#paper_name').val();
    	if(paper_name != null && paper_name != ""){
    		var params = {'paper_name':paper_name};
    		$.ajax({
    			url: getRootPath()+"/paper/search",
    			  async:false,
    			  type:'post',
    			  dataType:'json',
    			  data:params,
    			  success:function(data){
    				  repaintTable(data.paperlist);
    			  },
    			  error:function(){
    			  alert(data.result);
    			  window.location.href = "index";
    			  }
    			  });
    	}
    }
    // 删除一份考卷
	function del(id)
	{
		
		if(confirm("确定要删除吗？"))
		{
			
			var params = {'paper_id':id};
			$.ajax({
				url: getRootPath()+"/paper/deleteItem",
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
    // 删除多个份考卷
    function deleteSomePerson(url)
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
    function repaintTable(paperlist){
    	
    	if(paperlist!=null){
    	  	var tbody = "";
    	   	$('#table tbody').html("");
            
       	 //下面使用each进行遍历
           $.each(paperlist, function (n, value) {
        	   var trs = "";
               trs = "<tr><td><input type='checkbox' value="+value.paper_id+"></checkbox>"+(n+1)+"</td>"+"<td>"+value.paper_id+"<td>"+value.paper_name+"</td><td>"+value.paper_score+"</td><td>"+value.paper_time+"</td><td>"+value.paper_remark+"</td>"+
               "<td><a href='edit?paper_id="+value.paper_id+"' class='editItem'>修改</a>&nbsp;&nbsp;&nbsp;"+
               "<a href='index' onclick='del(\""+value.paper_id+"\")'>删除</a>&nbsp;&nbsp;&nbsp;"+
               "<a href='detail?paper_id="+value.paper_id+"'>查看</a></td>";
               tbody += trs;
           });

           $("#table").append(tbody);
           $('#kkpager').hide();
    	}else{
    		alert("暂无数据");
    		
    	}
  
	}