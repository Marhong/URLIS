<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>组卷管理</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/style.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/kkpager_blue.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/kkpager.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/paper/index.js"></script>

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
    考卷名称：
    <input type="text" name="paper_name" id="paper_name"class="abc input-default" placeholder="" value="">&nbsp;&nbsp; 

   &nbsp;&nbsp;&nbsp;&nbsp; <button type="button" id="search" class="btn btn-primary">查询</button>&nbsp;&nbsp;&nbsp;&nbsp; <button type="button" class="btn btn-primary" id="add">新增考卷</button>&nbsp;&nbsp;&nbsp;&nbsp; <button type="button" class="btn btn-primary" id="deleteSome">删除</button>
</form>
<form class="form-inline definewidth m20" action="index.html" method="post"> 
<input type="hidden" id="totalRecords" value=${totalRecords } />
		<input type="hidden" id="totalPage" value=${totalPage } />
<table class="table table-bordered table-hover definewidth m10" id="table">
    <thead>
    <tr>
	<th><input type="checkbox" id="selectAll"></checkbox>序号</th>
        <th>考卷编号</th>
        <th>考卷名称</th>
        <th>考卷总分</th>
		<th>考试时长</th>
		<th>备注</th>
		<th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${paperlist}" var="paper"  varStatus="order">
	     <tr>
            <td><input type="checkbox" value="${paper.paper_id }"></checkbox>${order.index+1 }</td>
            <td>${paper.paper_id }</td>
            <td>${paper.paper_name }</td>
			<td>${paper.paper_score }</td>
            <td>${paper.paper_time }</td>
			<td>${paper.paper_remark }</td>
            <td>
                  <a href="edit?paper_id=${paper.paper_id }">修改</a>&nbsp;&nbsp;&nbsp;<a href="index" class="deleteItem">删除</a>&nbsp;&nbsp;&nbsp;<a href="detail?paper_id=${paper.paper_id }" >查看</a>
                  
            </td>
        </tr>
        </c:forEach>
        </tbody>
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
		$('#search').click(function(){
			search();
		});
		$('#add').click(function(){
			window.location.href="add";
		});
		$('.deleteItem').click(function() {
			var id = $(this).parent().parent().children("td").eq(1).text();
			
			del(id);
		});
		$('#deleteSome').click(function() {
			var url = "/paper/deleteSome";
			deleteSomePerson(url);
		});
		$('#selectAll').click(function() {
			checkAll();
		});

	})
</script>