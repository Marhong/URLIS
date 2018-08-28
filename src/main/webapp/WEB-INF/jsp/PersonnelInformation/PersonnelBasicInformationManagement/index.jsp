<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>人员基本信息管理</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/style.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-datetimepicker.min.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/person/index.js"></script>

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
<form class="form-inline definewidth m20" action="search" method="post">  
    姓名：
    <input type="text" name="per_name" id="per_name"class="abc input-default" placeholder="" value="">&nbsp;&nbsp; 

<!-- 身份证： -->
    <input type="hidden" name="per_id" id="per_id"class="abc input-default" placeholder="" value="">&nbsp;&nbsp;

   &nbsp;&nbsp;&nbsp;&nbsp; <button type="button" class="btn btn-primary" id="search">查询</button>&nbsp;&nbsp;&nbsp;&nbsp; <button type="button" class="btn btn-primary" id="add">新增注册</button>&nbsp;&nbsp;&nbsp;&nbsp; <button type="button" class="btn btn-primary" id="deleteSome">删除</button>
</form>
<form class="form-inline definewidth m20" action="index" method="post"> 
<input type="hidden" id="totalRecords" value=${totalRecords } />
		<input type="hidden" id="totalPage" value=${totalPage } />
<table class="table table-bordered table-hover definewidth m10" id="table">
    <thead>
    <tr>
	<th><input type="checkbox" id="selectAll"></checkbox>序号</th>
        <th>姓名</th>
        <th>身份证</th>
        <th>单位</th>
		<th>职务</th>
		<th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${perlist}" var="per" varStatus="order">
	     <tr>
            <td><input type="checkbox" value="${per.per_id }"></checkbox>${order.index+1 }</td>
            <td>${per.per_name }</td>
            <td>${per.per_id }</td>
			<td>${per.dept_id }</td>
            <td>${per.pos_name }</td>
            <td><a href="edit?per_id=${per.per_id }" class="editItem">修改</a>&nbsp;&nbsp;&nbsp;<a
							href="index" class="deleteItem" >删除</a>&nbsp;&nbsp;&nbsp;<a
							href="detail?per_id=${per.per_id }">查看</a>
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
		$('#add').click(function() {
			window.location.href = "add";
		});	
		$('#search').click(function(){
			search();
		});
		
		$('.deleteItem').click(function() {
			var id = $(this).parent().parent().children("td").eq(2).text();
			
			del(id);
		});
		$('#deleteSome').click(function() {
			var url = "/person/deleteSome";
			deleteSomePerson(url);
		});
		$('#selectAll').click(function() {
			checkAll();
		});
    });

</script>