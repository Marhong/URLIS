<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>查看考卷</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/style.css" />
	

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
</head>
	<p><center><a id="export" href="" >导出为Word文档</a> &nbsp;&nbsp;&nbsp;&nbsp;<a href="index"  name="backid" id="backid">返回列表</a></center></p>
<div class="container">
	<div class="row">
		<div class="col-md-9" id="pagecontent">
	<p id="paper_name">${paper.paper_name }</p>
	<p id="papaer_score">考试时长:${paper.paper_time }&nbsp;&nbsp;&nbsp;&nbsp;总分:${paper.paper_score }</p>
	<p>注意事项:</p>
	<p id="paper_remark">${paper.paper_remark }</p>
	<c:forEach items="${qulist}" var="qu" varStatus="order">
		<p id="qu.qu_id">${order.index +1}.${qu.qu_content }&nbsp;&nbsp;(&nbsp;&nbsp;)</p>
		<p class="options">${qu.qu_option }</p>
	</c:forEach>
	</div>
	</div>
	</div>

<%-- <form action="index.html" method="post">
<table class="table table-bordered table-hover definewidth m10">
	
    <tr>
        <td width="10%" class="tableleft">考卷名称</td>
        <td><input type="text" name="paper_name" id="paper_name" value=${paper.paper_name } readonly/><input type="hidden" id="paper_id" name="paper_id" value=${paper.paper_id }/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">考卷分数</td>
        <td><input type="text" name="papaer_score" id="papaer_score"  readonly value=${paper.paper_score } /></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">考试时长</td>
        <td><input type="text" name="paper_time" id="paper_time"  readonly value=${paper.paper_time } /></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">考卷备注</td>
        
      
        <td> <input type="hidden" id="paper_remark_value" name="paper_remark_value" value=${paper.paper_remark } />
        <textarea cols="50" rows="3" name="paper_remark" id="paper_remark" style="resize:none" disabled="disabled"  ></textarea></td>
    </tr>
    <tr>
	 <td width="10%" class="tableleft">选题<br/>&nbsp;&nbsp;&nbsp;&nbsp;已选选择题:<span id="choiceItems">30题</span><br/>&nbsp;&nbsp;&nbsp;&nbsp;已选判断题<span id="judgementItems">20题</span></td>
	 <td >
       <table class="table table-bordered table-hover definewidth m10" id="table">
			<thead>
			<tr>
				<th width="5%"><select class="form-control" name="qu_type" id="qu_type" >
				<option>选择题</option>
				<option>判断题</option>
			</select></th>
				<th>题目编号</th>
				<th>题目名称</th>
				<th>题目类别</th>
				<th>题目分值</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach items="${qulist}" var="qu">
			<tr>
				<td><input type="checkbox" value="${qu.qu_id }" disabled="disabled"></checkbox></td>
				<td>${qu.qu_id}</td>
				<td>${qu.qu_content }</td>
				<td>${qu.qu_type}</td>
				<td>${qu.qu_score}</td>
			</tr>
</c:forEach>
		</table>
	</td>
    </tr>
     <tr>
        <td class="tableleft">操作</td>
        <td>
           <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>	
</table>
</form> --%>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/bootstrap.min.js"></script>
	<!-- <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script> -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/paper/FileSaver.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/paper/jquery.wordexport.js"></script>

	<script>
    $(function () {   
    	initPage();
    	$("#export").click(function(event) {
    		$("#pagecontent").wordExport();
    	});
    	type();
		$('#backid').click(function(){
				window.location.href="index.html";
		 });
		$('#qu_type').click(function(){
			type();
	 	});
    });
 // 根据不同选择，显示不同类型的题目
	function type(){
	 	$('#paper_remark').text($('#paper_remark_value').val());
		var type = $('#qu_type').val();
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
	 $("#export").css('font-size',20);
	 $("#backid").css('font-size',15);
	 $('.options').each(function(){
		 var options = $(this).text();
		 var result ="";
		 var tokens = options.split(";");
		 result += "A. "+tokens[0]+"  ";
		 result += "B. "+tokens[1]+"  ";
		 if(tokens[2] != null && tokens[2] != ""){
			 result += "C. "+tokens[2]+"  ";
		 }
		 if(tokens[3] != null && tokens[3] != ""){
			 result += "D. "+tokens[3];
		 }
		 $(this).html(result);
	 })
 }
</script>
</body>
</html>
