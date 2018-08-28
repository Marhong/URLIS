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
    	var qu_content = $('#qu_content').val();
    	var qu_type = $('#qu_type').val();
		var params = {'qu_content':qu_content,'qu_type':qu_type};
		$.ajax({
			url: getRootPath()+"/question/search",
			  async:false,
			  type:'post',
			  dataType:'json',
			  data:params,
			  success:function(data){
				  repaintTable(data.qulist);
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
               trs = "<tr><td><input type='checkbox' value="+value.qu_id+"></checkbox>"+(n+1)+"</td><td>"+value.qu_id+"</td><td>"+value.qu_content+"</td><td>"+value.qu_type+"</td><td>"+value.qu_score+"</td>"+
               "<td><a href='edit?qu_id="+value.qu_id+"' class='editItem'>修改</a>&nbsp;&nbsp;&nbsp;"+
               /*"<a href='index' class='deleteItem' onclick='del("+value.qu_id+")'>删除</a>&nbsp;&nbsp;&nbsp;"+*/
               "<a href='index' onclick='del(\""+value.qu_id+"\")'>删除</a>&nbsp;&nbsp;&nbsp;"+
               "<a href='detail?qu_id="+value.qu_id+"'>查看</a></td>";
               tbody += trs;
           });

           $("#table").append(tbody);
           initPage();
           $('#kkpager').hide();
    	}else{
    		alert("暂无数据");
    		
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
