<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>成绩管理</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/style.css" />
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/examrecord/index.js"></script>
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
    <input type="text" name="per_name" id="per_name" class="abc input-default" placeholder="" value="">&nbsp;&nbsp; 
  考试名称：
    <input type="text" name="exam_name" id="exam_name" class="abc input-default" placeholder="" value="">

   &nbsp;&nbsp;&nbsp;&nbsp; <button type="button" id="search" class="btn btn-primary">查询</button>&nbsp;&nbsp;&nbsp;&nbsp; <button type="button" class="btn btn-primary" id="add">新增成绩</button>&nbsp;&nbsp;&nbsp;&nbsp; <button type="button" class="btn btn-primary" id="deleteSome">删除</button>
</form>

<form class="form-inline definewidth m20" action="index.html" method="post"> 
<input type="hidden" id="totalRecords" value=${totalRecords } />
		<input type="hidden" id="totalPage" value=${totalPage } />
		<input type="hidden" id="hidepage" value=${hidepage } />
<table class="table table-bordered table-hover definewidth m10" id="table">
    <thead>
    <tr>
	<th><input type="checkbox" id="selectAll"></checkbox>序号</th>
        <th>姓名</th>
        <th>身份证</th>
        <th>单位</th>
		<th>职务</th>
		<th>考试名称</th>
		<th>分数</th>
		<th>操作</th>
    </tr>
    </thead>
     <tbody>
	  <c:forEach items="${recordlist}" var="examrecord"  varStatus="order">
	     <tr>
            <td><input type="checkbox" value="${examrecord.exam_id }:${examrecord.per_id}"></checkbox>${order.index+1 }</td>
            <td>${examrecord.per_name}</td>
            <td>${examrecord.per_id}</td>
			<td>${examrecord.dept_name}</td>
            <td>${examrecord.posname}</td>
			<td>${examrecord.exam_name}</td>
            <td>${examrecord.exam_score}</td>
            <td>
                  <a href="index.html" class="deleteItem" onclick="del('${examrecord.exam_id }','${examrecord.per_id}')">删除</a>
                  
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
    	init();
		$('#add').click(function(){
				window.location.href="add";
		 });
		$('#deleteSome').click(function() {

			deleteSome();
		});
		$('.deleteItem').click(function() {
			var id = $(this).parent().parent().children("td").eq(0).find(input[type="checkbox"]).val();	
			alert(id+" deleteitem");
			var exam_id = id.split(":")[0];
			var per_id = id.split(":")[1];
			del(exam_id,per_id);
		});
		$('#selectAll').click(function() {
			checkAll();
		});
		$('#search').click(function() {
			search();
		});

    });

</script>