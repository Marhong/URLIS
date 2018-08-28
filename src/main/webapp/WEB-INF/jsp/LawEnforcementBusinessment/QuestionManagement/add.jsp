<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>新增题目</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/question/add.js"></script>


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
<form id="question_form" action="save" method="post">
	<table class="table table-bordered table-hover definewidth m10">
		<tr>
			<td width="10%" class="tableleft">题目类别</td>
			<td><select class="form-control" name="qu_type" id="qu_type">
					<option>选择题</option>
					<option>判断题</option>
			</select></td>
		</tr>
		<tr>
			<td width="10%" class="tableleft">题目名</td>
			<td><textarea cols="50" rows="3" name="qu_content" id="qu_content"
					></textarea><span id="qu_id" type="hiden"></span></td>
		</tr>
		<tr>
			<td width="10%" class="tableleft">A 选项</td>
			<td><input type="text" id="optionA" /></td>
		</tr>
		<tr>
			<td width="10%" class="tableleft">B 选项</td>
			<td><input type="text" id="optionB" /></td>
		</tr>
		<tr>
			<td width="10%" class="tableleft">C 选项</td>
			<td><input type="text" id="optionC" /></td>
		</tr>
		<tr>
			<td width="10%" class="tableleft">D 选项</td>
			<td><input type="text" id="optionD" /> <input type="hidden"
				id="qu_option" name="qu_option"/></td>

		</tr>
		<tr>
			<td width="10%" class="tableleft">题目分值</td>
			<td><input type="text" name="qu_score" id="qu_score" value="2"  readonly /></td>
		</tr>
		<tr>
			<td width="10%" class="tableleft">题目答案</td>
			<td><select class="form-control" name="qu_answer" id="qu_answer">
					<option>A</option>
					<option>B</option>
					<option id="optionc">C</option>
					<option id="optiond">D</option>
			</select></td>
		</tr>


		<tr>
			<td class="tableleft">操作</td>
			<td>
				<button type="button" class="btn btn-primary" id="save">保存</button>&nbsp;&nbsp;
				<button type="button" class="btn btn-success" name="backid"
					id="backid">返回列表</button>
			</td>
		</tr>
	</table>
</form>

<script>
	$(function() {
		// 返回index页面
		$('#backid').click(function() {
			window.location.href = "index.html";
		});	
		// 根据题目不同类型题型显示不同数量的选项，判断题则只有两个选项
		$('#qu_type').click(function(){
			change();
			
		});
		// 提交表单
 		$('#save').click(
				function() {
					save("/question/save");
				}); 
	});
	
</script>
</body>
</html>
