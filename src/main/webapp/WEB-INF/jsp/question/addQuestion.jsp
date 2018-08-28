<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加学生</title>
<link href="<%=request.getContextPath()%>/static/css/global.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="maincontainer">
		<div class="columnconn">
			<span class="columntitle">信息录入</span>
		</div>
		<div class="fmcontainer">
			<form action="<%=request.getContextPath() %>/myQuestion/save" method="post">
				<table class="inputtable" width="98%">
					<tr>
						<td class="leftlabel">题目编号：</td>
						<td><input name="qu_id" type="text" /></td>
						<td class="leftlabel">题目分值：</td>
						<td><input name="qu_score" type="text" /></td>
					</tr>
					<tr>
						<td class="leftlabel">题目类别：</td>
						<td><input name="qu_type" type="text" /></td>
						<td class="leftlabel">题目内容：</td>
						<td><input name="qu_content" type="text" /></td>
					</tr>
										<tr>
						<td class="leftlabel">题目选项：</td>
						<td><input name="qu_option" type="text" /></td>
						<td class="leftlabel">题目答案：</td>
						<td><input name="qu_answer" type="text" /></td>
					</tr>
				</table>
				<div class="fmsubmitbtn">
					<input type="submit" name="submit" class="btn" value="提 交" /> <input
						type="button" name="cancle" class="btn" value="取 消" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>