/**
  * @Description: index.jsp页面的js函数
  * @Time: 2018年8月24日 下午19:27:40
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
    	var dept_name = $('#dept_name').val();
		var params = {'dept_name':dept_name};
		$.ajax({
			url: getRootPath()+"/department/search",
			  async:false,
			  type:'post',
			  dataType:'json',
			  data:params,
			  success:function(data){
				  repaintTable(data.deptlist);
			  },
			  error:function(){
			  alert(data.result);
			  window.location.href = "index";
			  }
			  });	
    }
    // 重绘表格
    function repaintTable(perlist){
    	
    	if(perlist!=null){
    	  	var tbody = "";
    		$('#table tbody').html("");
       	 //下面使用each进行遍历
           $.each(perlist, function (n, value) {
        	  
           
               var trs = "";
               trs = "<tr><td>"+(n+1)+"<td>"+value.dept_id+"</td><td>"+value.dept_name+"</td>"+
               "<td><a href='edit?dept_id="+value.dept_id+"' class='editItem'>修改</a>&nbsp;&nbsp;&nbsp;";
               tbody += trs;
           });

           $("#table").append(tbody);
           $('#kkpager').hide();
    	}else{
    		alert("暂无数据");
    		
    	}
  
    }
