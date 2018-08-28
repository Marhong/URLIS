<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>修改题目</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/style.css" />	
    <link href="<%=request.getContextPath()%>/static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/question/edit.js"></script>
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
<form action="update" method="post">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
		<td width="10%" class="tableleft">题目类别</td>
        <td> <input type="hidden" id="qu_type_value" name="qu_type_value" value=${question.qu_type} />
				<select class="form-control" name="qu_type" id="qu_type" >
				<option value="选择题">选择题</option>
				<option value="判断题">判断题</option>
			</select>
		</td>
	</tr>
    <tr>
        <td width="10%" class="tableleft">题目名</td>
        <td ><input type="hidden" id="qu_id_value" name="qu_id_value" value=${question.qu_id} />
        <textarea cols="150" rows="3" name="qu_content" id="qu_content"   >${question.qu_content}</textarea></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">A 选项</td>
        <td><input type="text" name="optionA" id="optionA" value=${optionA} /></td>
    </tr>
	    <tr>
        <td width="10%" class="tableleft">B 选项</td>
        <td><input type="text" name="optionB" id="optionB" value=${optionB} /></td>
    </tr>
	    <tr>
        <td width="10%" class="tableleft">C 选项</td>
        <td><input type="text" name="optionC" id="optionC" value=${optionC} ></td>
    </tr>
	    <tr>
        <td width="10%" class="tableleft">D 选项</td>
        <td><input type="text" name="optionD" id="optionD" value=${optionD}  ><input type="hidden"
				id="qu_option" name="qu_option"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">题目分值</td>
        <td><input type="text" name="qu_score" id="qu_score" value=${question.qu_score} /></td>
    </tr>	
    <tr>
		<td width="10%" class="tableleft">题目答案</td>
        <td><input type="hidden" id="qu_answer_value" name="qu_answer_value" value=${question.qu_answer} />
         <select class="form-control" name="qu_answer" id="qu_answer" >
				<option id="optiona" value="A">A</option>
				<option id="optionb" value="B">B</option>
				<option id="optionc" value="C">C</option>
				<option id="optiond" value="D">D</option>
			</select>
		</td>
	</tr>


    <tr>
        <td class="tableleft">操作</td>
        <td>
            <button type="button" class="btn btn-primary" id="save">保存</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
<script>
    $(function () {
    	initPage();
		$('#backid').click(function(){
				window.location.href="index.html";
		 });
		// 根据题目不同类型题型显示不同数量的选项，判断题则只有两个选项
		$('#qu_type').click(function(){
			change();	
		});
		// 提交表单
		$('#save').click(
			function() {				
				update();
			}); 
    });
</script>
</body>
</html>
