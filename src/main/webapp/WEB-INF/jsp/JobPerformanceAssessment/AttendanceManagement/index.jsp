<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>考勤记录管理</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/attendance/index.js"></script>


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
<body>
<form class="form-inline definewidth m20" action="index.html" method="get">  
    姓名：
    <input type="text" name="per_name" id="per_name"class="abc input-default" placeholder="" value="">&nbsp;&nbsp; 
<!-- 	身份证： -->
    <input type="hidden" name="per_id" id="per_id"class="abc input-default" placeholder="" value="">&nbsp;&nbsp;


   &nbsp;&nbsp;&nbsp;&nbsp; <button type="button" class="btn btn-primary" id="search">查询</button>
     &nbsp;&nbsp;&nbsp;&nbsp; <a type="button"  class="btn btn-primary" id="start_work" href="startwork">上班打卡</a>
       &nbsp;&nbsp;&nbsp;&nbsp; <a type="button"  class="btn btn-primary" id="endwork" href="endwork">下班打卡</a>
</form>
<form class="form-inline definewidth m20" action="index.html" method="post"> 
<table class="table table-bordered table-hover definewidth m10" id="table">
    <thead>
    <tr>
    <th>序号</th>
	<th>编号</th>
        <th>姓名</th>
        <th>身份证</th>
		<th>日期</th>
        <th>上班时间</th>
		<th>下班时间</th>
		<th>状态</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${attnlist}" var="attn" varStatus="order">
	     <tr>
	     <td>${order.index+1 }</td>
            <td>${attn.attn_id }</td>
            <td>${attn.per_name }</td>
            <td>${attn.per_id }</td>
			<td>${attn.attn_date }</td>
			<td>${attn.start_time }</td>
			<td>${attn.end_time }</td>
            <td>${attn.attn_status}</td>
        </tr>
        </c:forEach>
    </tbody>
</table>
</form>
<div class="inline pull-right page">
         10122 条记录 1/507 页  <a href='#'>下一页</a>     <span class='current'>1</span><a href='#'>2</a><a href='/chinapost/index.php?m=Label&a=index&p=3'>3</a><a href='#'>4</a><a href='#'>5</a>  <a href='#' >下5页</a> <a href='#' >最后一页</a>    </div>
</body>
</html>
<script>
    $(function () {
		initPage();
		$('#search').click(function(){
			search();
		});
	})
</script>