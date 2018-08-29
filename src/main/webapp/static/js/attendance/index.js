/**
  * @Description: index.jsp页面的js函数
  * @Time: 2018年8月2日  上午09:53:40
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
		var per_id = $('#per_id').val();
		
		var params = {'per_name':per_name,'per_id':per_id};
		$.ajax({
			url: getRootPath()+"/attendance/search",
			  async:false,
			  type:'post',
			  dataType:'json',
			  data:params,
			  success:function(data){
				  repaintTable(data.attnlist);
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
               trs = "<tr><td>"+(n+1)+"</td><td>"+value.attn_id+"</td><td>"+value.per_name+"</td>"+
               "<td>"+value.per_id+"</td><td>"+value.attn_date+"</td>"+
               "<td>"+value.start_time+"</td><td>"+value.end_time+"</td><td>"+value.attn_status+"</td></tr>";
               tbody += trs;
           });

           $("#table").append(tbody);
           initPage();
           $('#kkpager').hide();
    	}else{
    		alert("暂无数据");
 
    	}
  
    }
    // 将表格中迟到的数据标红
    function initPage(){
    	$('#table tbody tr').each(function(){
    		
    		if($(this).children("td").eq(7).text() != "正常"){
    			$(this).css('background','red');
    		}

		});
    }
