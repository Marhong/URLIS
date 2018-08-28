<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>修改成绩</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/style.css" />
	
    <link href="<%=request.getContextPath()%>/static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

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
<form action="index.html" method="post">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><input type="text" name="per_name"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">身份证</td>
        <td><input type="text" name="per_id"/></td>
    </tr>	
    <tr>
		<td width="10%" class="tableleft">考试编号</td>
        <td> <select class="form-control" name="exam_id" disabled="disabled">
				<option>第一次月考</option>
				<option>期中考试</option>
				<option>期末考试</option>
			</select>
		</td>
	</tr>
    <tr>
        <td width="10%" class="tableleft">成绩</td>
        <td><input type="text" name="exam_socre"/></td>
    </tr>

    <tr>
        <td class="tableleft">操作</td>
        <td>
            <button type="submit" class="btn btn-primary" type="button">保存</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/bootstrap-datetimepicker.zh-CN.js"></script>


	<script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="index.html";
		 });

    });
		$('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
</script>
</body>
</html>
