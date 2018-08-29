<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>考试管理</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/style.css" />
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/exam/index.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/kkpager_blue.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/kkpager.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/util.js"></script>
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
    考试名称：
    <input type="text" name="exam_name" id="exam_name"class="abc input-default" placeholder="" value="">&nbsp;&nbsp; 


   &nbsp;&nbsp;&nbsp;&nbsp; <button type="button" id="search" class="btn btn-primary">查询</button>&nbsp;&nbsp;&nbsp;&nbsp; <button type="button" class="btn btn-primary" id="add" >新增考试</button>&nbsp;&nbsp;&nbsp;&nbsp; <button type="button" class="btn btn-primary" id="deleteSome">删除</button>
</form>
<form class="form-inline definewidth m20" action="index.html" method="post"> 
<input type="hidden" id="totalRecords" value=${totalRecords } />
		<input type="hidden" id="totalPage" value=${totalPage } />
<table class="table table-bordered table-hover definewidth m10" id="table">
    <thead>
    <tr>
	<th><input type="checkbox" id="selectAll"></checkbox>序号</th>
        <th>考试编号</th>
        <th>考试名称</th>
        <th>试卷名称</th>
        <th>考试时间</th>
		<th>状态</th>
		<th>操作</th>
    </tr>
    </thead>
    <tbody>
	  <c:forEach items="${examlist}" var="exam"  varStatus="order">
	     <tr>
            <td><input type="checkbox" value="${exam.exam_id }"></checkbox>${order.index+1 }</td>
            <td>${exam.exam_id }</td>
            <td>${exam.exam_name }</td>
            <td>${exam.paper_id }</td>
			<td>${exam.exam_datetime }</td>
            <td>尚未进行</td>
            <td>
                <a href="/URLIS/examrecord/getRecordsByExamId?exam_id=${exam.exam_id }" >查看成绩</a>
                  
            </td>
        </tr>
       </c:forEach>
</table>
</form>
	<div style="width:800px;margin:0 auto;">
<div id="kkpager"></div>
</div>
</body>
</html>
<script>
    $(function () {
    	initPage();
    	init();
		$('#add').click(function(){
				
				window.location.href="add";
		 });
		$('#deleteSome').click(function() {

			deleteSome("/exam/deleteSome");
		});

		$('#selectAll').click(function() {
			checkAll();
		});
		$('#search').click(function() {
			search();
		});

    });

</script>