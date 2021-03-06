<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>组织机构管理</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/department/index.js"></script>
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
    组织机构名称：
    <input type="text" name="dept_name" id="dept_name"class="abc input-default" placeholder="" value="">&nbsp;&nbsp; 


   &nbsp;&nbsp;&nbsp;&nbsp; <button type="button" class="btn btn-primary" id="search">查询</button>&nbsp;&nbsp;&nbsp;&nbsp; <button type="button" class="btn btn-primary" id="add">新增机构</button> 
</form>
<form class="form-inline definewidth m20" action="index.html" method="post"> 
<input type="hidden" id="totalRecords" value=${totalRecords } />
		<input type="hidden" id="totalPage" value=${totalPage } />
<table class="table table-bordered table-hover definewidth m10" id="table">
    <thead>
    <tr>
		<th>序号</th>
        <th>机构编号</th>
        <th>机构名称</th>
		<th>操作</th>
    </tr>
    </thead>
    <tbody>
    	<c:forEach items="${deptlist}" var="dept" varStatus="order">
	     <tr>
            <td>${order.index+1 }</td>
            <td>${dept.dept_id }</td>
            <td>${dept.dept_name }</td>
            <td>
                <a href="edit?dept_id=${dept.dept_id }">修改</a>
                  
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
		$('#add').click(function() {
			window.location.href = "add";
		});	
    });


</script>