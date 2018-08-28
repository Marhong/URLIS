/**
  * @Description: index.jsp页面的js函数
  * @Time: 2018年8月25日 上午11:18:24
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
    	
    	var per_name = $('#per_name').val();
    	
    	if(per_name != null && per_name != ""){
    		
        	var per_id = $('#per_id').val();
    		var params = {'per_name':per_name,'per_id':per_id};
    		$.ajax({
    			url: getRootPath()+"/change/search",
    			  async:false,
    			  type:'post',
    			  dataType:'json',
    			  data:params,
    			  success:function(data){
    				  repaintTable(data.changelist);
    			  },
    			  error:function(){
    			  alert(data.result);
    			  window.location.href = "index";
    			  }
    			  });	
    	}

    }
    // 重绘表格
    function repaintTable(perlist){
    	
    	if(perlist!=null){
    		$('#table tbody').html("");
    	  	var tbody = "";
       	 //下面使用each进行遍历
           $.each(perlist, function (n, value) { 
               var trs = "";
               var chanid = value.per_id+":"+value.chan_time;        
               trs = "<tr><td><input type='checkbox' value="+chanid+"></checkbox>"+(n+1)+"</td><td>"+value.per_name+"</td><td>"+value.per_id+"</td><td>"+value.dept_id+"</td><td>"+value.pos_name+"</td>"+
               "<td><a href='detail?chanid="+value.per_id+" "+value.chan_time+"'>查看变动信息</a></td></tr>";
               tbody += trs;
           });

           $("#table").append(tbody);
           $('#kkpager').hide();
    	}else{
    		alert("暂无数据");	
    	}
    }
    // 删除一个人员信息
	function del(id)
	{
		
		if(confirm("确定要删除吗？"))
		{
			
			var params = {'per_id':id};
			$.ajax({
				url: getRootPath()+"/person/deleteItem",
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
    // 删除多个人员变动信息
    function deleteSomeChange(url)
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