/**
  * @Description: index.jsp页面的js函数
  * @Time: 2018年8月27日 下午14:26:40
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
    	var per_name = $('#per_name').val();
		var params = {'exam_name':exam_name,'per_name':per_name};
		if((exam_name != null && exam_name != "") || (per_name != null && per_name != "")){

		$.ajax({
			url: getRootPath()+"/examrecord/search",
			  async:false,
			  type:'post',
			  dataType:'json',
			  data:params,
			  success:function(data){
				  repaintTable(data.recordlist);
			  },
			  error:function(){
			  alert(data.result);
			  window.location.href = "index";
			  }
			  });	
		
		}
    }
    // 删除一个问题
	function del(exam_id,per_id)
	{	
		
		if(confirm("确定要删除吗？"))
		{
			var params = {'exam_id':exam_id,'per_id':per_id};
			$.ajax({
				url: getRootPath()+"/examrecord/deleteItem",
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
				  	window.location.href = "index?exam_id="+exam_id;
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
		
		var exam_id;
		if(ids != null && ids != ""){
			
			exam_id = ids.split(",")[0].split(":")[0];
			
		}
		
		if(confirm("确定要删除吗？"))
		{
			var params = {'ids':ids};
			$.ajax({
				url: getRootPath()+"/examrecord/deleteSome",
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
				  	window.location.href = "index?exam_id="+exam_id;
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

               trs = "<tr><td><input type='checkbox' value="+value.exam_id+":"+value.per_id+"></checkbox>"+(n+1)+"</td>"+"<td>"+value.per_name+"<td>"+value.per_id+"</td>"+"<td>"+value.dept_name+"<td>"+value.posname+"</td><td>"+value.exam_name+"</td><td>"+value.exam_score+"</td>"+
              
               "<td><a href='index' onclick='del(\""+value.exam_id+"\",\""+value.per_id+"\")'>删除</a></td></tr>";
               tbody += trs;
           });

           $("#table").append(tbody);
           $('#kkpager').hide();
    	}else{
    		alert("暂无数据");		
    	}
  
    }

