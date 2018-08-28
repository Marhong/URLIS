<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.js"></script>
<script>
$(document).ready(function(){
	  $(".delete").click(function(){
		  var params = {};
			//params.XX必须与Spring Mvc controller中的参数名称一致  
			//否则在controller中使用@RequestParam绑定
			var id = $(this).parent().parent().children("td").eq(0).text();
			params.qu_id = id;
			$.ajax({
				async:false,
				type: "POST",
				url: "<%=request.getContextPath() %>/myQuestion/delete",//注意路径
				data:params,
				dataType:"json",
				success:function(data){
					if(data.result=='SUCCESS'){
						alert("修改成功");
					}else{
						alert("修改失败，失败原因【" + data + "】");
					}
				},
				error:function(data){
					alert(data.result);
				}
			});

	  });
	});
</script>
<title>问题列表</title>
<style type="text/css">
body {
	font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica,
		sans-serif;
	color: #4f6b72;
}

a {
	color: #c75f3e;
	text-decoration: none;
}

#mytable {
	width: 100%;
	padding: 0;
	margin: 0;
}

caption {
	padding: 0 0 5px 0;
	width: 100%;
	font: 30px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	text-align: center;
}

th {
	font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	color: #4f6b72;
	border-right: 1px solid #C1DAD7;
	border-bottom: 1px solid #C1DAD7;
	border-top: 1px solid #C1DAD7;
	letter-spacing: 2px;
	text-transform: uppercase;
	text-align: center;
	padding: 6px 6px 6px 12px;
	background: #CAE8EA url(images/bg_header.jpg) no-repeat;
}

th.nobg {
	border-top: 0;
	border-left: 0;
	border-right: 1px solid #C1DAD7;
	background: none;
}

td {
	border-right: 1px solid #C1DAD7;
	border-bottom: 1px solid #C1DAD7;
	background: #fff;
	font-size: 11px;
	padding: 6px 6px 6px 12px;
	color: #4f6b72;
}

td.alt {
	background: #F5FAFA;
	color: #797268;
}

th.spec {
	border-left: 1px solid #C1DAD7;
	border-top: 0;
	background: #fff url(images/bullet1.gif) no-repeat;
	font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
}

th.specalt {
	border-left: 1px solid #C1DAD7;
	border-top: 0;
	background: #f5fafa url(images/bullet2.gif) no-repeat;
	font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	color: #797268;
}
</style>
</head>
<body>
<p>这是一个标题</p>
<button id="test">点我</button>
	<div style="margin-left: 1200px;">
		<a href="<%=request.getContextPath() %>/myQuestion/add"  class="add">新增用户</a>
	</div>
	<table id="mytable" cellspacing="0">
		<caption>用户列表信息</caption>
		<tr>
			<th scope="col">题目编号</th>
			<th scope="col">题目分数</th>
			<th scope="col">题目类别</th>
			<th scope="col">题目内容</th>
			<th scope="col">题目选项</th>
			<th scope="col">题目答案</th>
			<th scope="col">操作</th>
		</tr>
		<c:forEach items="${qulist}" var="qu">
			<tr>
				<td>${qu.qu_id}</td>
				<td>${qu.qu_score}</td>
				<td>${qu.qu_type}</td>
				<td>${qu.qu_content}</td>
				<td>${qu.qu_option}</td>
				<td>${qu.qu_answer}</td>
				<td><a href="#"  class="update">修改</a> <a href="#" class="delete" >删除</a></td>
				
			</tr>
		</c:forEach>
	</table>
</body>
</html>