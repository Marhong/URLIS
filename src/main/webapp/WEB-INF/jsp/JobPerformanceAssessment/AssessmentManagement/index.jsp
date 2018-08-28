<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>考核记录管理</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/style.css" />
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/assessment/index.js"></script>
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
    姓名：
    <input type="text" name="per_name" id="per_name"class="abc input-default" placeholder="" value="">&nbsp;&nbsp; 
	 

   &nbsp;&nbsp;&nbsp;&nbsp; <button type="button" id="search" class="btn btn-primary">查询</button>&nbsp;&nbsp;&nbsp;&nbsp; <button type="button" class="btn btn-primary" id="add">新增考核记录</button>&nbsp;&nbsp;&nbsp;&nbsp; <a href="generate" type="button" class="btn btn-primary" id="generate">生成当月考核记录</a>&nbsp;&nbsp;&nbsp;&nbsp; <button type="button" class="btn btn-primary" id="deleteSome">删除</button>
</form>
<form class="form-inline definewidth m20" action="index.html" method="post"> 
<input type="hidden" id="totalRecords" value=${totalRecords } />
		<input type="hidden" id="totalPage" value=${totalPage } />
<table class="table table-bordered table-hover definewidth m10" id="table">
    <thead>
    <tr>
	<th><input type="checkbox" id="selectAll"></checkbox>序号</th>
        <th>姓名</th>
        <th>身份证</th>
        <th>考核日期</th>
		<th>分数</th>
		<th>操作</th>
    </tr>
    </thead>
     <tbody>
	  <c:forEach items="${assesslist}" var="assessment"  varStatus="order">
	     <tr>
            <td><input type="checkbox" value=${assessment.asse_id }></checkbox>${order.index+1 }</td>
            <td>${assessment.per_name }</td>
            <td>${assessment.per_id }</td>
			<td>${assessment.asse_date}</td>
            <td>${assessment.asse_score }</td>
            <td>
                <a href="detail?asse_id=${assessment.asse_id }">查看</a>&nbsp;&nbsp;&nbsp;&nbsp; <a href="index.html" class="deleteItem" onclick="del(${assessment.asse_id})">删除</a>
                  
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
			deleteSome();
		});

		$('#selectAll').click(function() {
			checkAll();
		});
		$('#search').click(function() {
			search();
		});
/* 		$('#generate').click(function() {
			generate();
		}); */

    });

</script>