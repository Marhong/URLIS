<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/style.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/change/index.js"></script>

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
<!-- 
身份证： -->
    <input type="hidden" name="per_id" id="per_id"class="abc input-default" placeholder="" value="">&nbsp;&nbsp;

   &nbsp;&nbsp;&nbsp;&nbsp; <button type="button" class="btn btn-primary" id="search">查询</button>&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary" id="deleteSome">删除</button>
</form>
<form class="form-inline definewidth m20" action="index.html" method="post"> 
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
      
    <c:forEach items="${changeList}" var="change" varStatus="order">
	     <tr>
            <td><input type="checkbox" value="${change.per_id }:${change.chan_time }"></checkbox>${order.index+1 }</td>
            <td>${change.per_name }</td>
            <td>${change.per_id }</td>
			<td>${change.dept_id }</td>
            <td>${change.pos_name }</td>
            <td>
                  <a href="detail?chanid=${change.per_id } ${change.chan_time }">查看变动信息</a>
                
            </td>
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
		$('#search').click(function(){
			
			search();
		});
		$('#deleteSome').click(function() {
			var url = "/change/deleteSome";
			deleteSomeChange(url);
		});
		$('#selectAll').click(function() {
			checkAll();
		});
	})
</script>