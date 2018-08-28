<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>题目管理</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css/style.css" />
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/kkpager_blue.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/kkpager.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/util.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/question/index.js"></script>

<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
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
		题目名： <input type="text" name="qu_content" id="qu_content"
			class="abc input-default" placeholder="" value="">&nbsp;&nbsp;

		题目类别： <select class="form-control" name="qu_type" id="qu_type">
			<option>选择题</option>
			<option>判断题</option>

		</select>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		<button type="button" class="btn btn-primary" id="search">查询</button>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="button" class="btn btn-primary" id="add">新增题目</button>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="button" class="btn btn-primary" id="deleteSome">删除</button>
	</form>
	<form class="form-inline definewidth m20" action="index"
		method="post">
		<input type="hidden" id="totalRecords" value=${totalRecords } />
		<input type="hidden" id="totalPage" value=${totalPage } />
		<table class="table table-bordered table-hover definewidth m10"
			id="table">
			<thead>
				<tr>
					<th><input type="checkbox" id="selectAll">
					</checkbox>序号</th>
					<th>题目编号</th>
					<th>题目名称</th>
					<th>题目类别</th>
					<th>题目分值</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${qulist}" var="qu" varStatus="order">
					<tr>
						<td><input type="checkbox" value="${qu.qu_id }">
						</checkbox>${order.index+1}</td>
						<td>${qu.qu_id}</td>
						<td>${qu.qu_content }</td>
						<td>${qu.qu_type}</td>
						<td>${qu.qu_score}</td>
						<td><a href="edit?qu_id=${qu.qu_id }" class="editItem">修改</a>&nbsp;&nbsp;&nbsp;<a
							href="index" class="deleteItem">删除</a>&nbsp;&nbsp;&nbsp;<a
							href="detail?qu_id=${qu.qu_id }">查看</a></td>
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
	$(function() {
		initPage();
		init();
		$('#search').click(function() {
			search();
		});
		$('#add').click(function() {
			window.location.href = "add";
		});
		$('.deleteItem').click(function() {
			var id = $(this).parent().parent().children("td").eq(1).text();
			del(id);
		});
		$('#deleteSome').click(function() {

			deleteSome("/question/deleteSome");
		});

		$('#selectAll').click(function() {
			checkAll();
		});
	});

</script>