<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>打卡</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/attendance/add.js"></script>
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
        <td width="10%" class="tableleft">身份证号</td>
        <td><input type="text" name="per_id" id="per_id"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><input type="text" name="per_name" id="per_name"/></td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">单位</td>
        <td><input type="text" name="dept_id" id="dept_id"/></td>
    </tr>
     <tr>
        <td width="10%" class="tableleft">职务名称</td>
        <td><input type="text" name="pos_name" id="pos_name"/></td>
    </tr>
    <tr>
        <td class="tableleft">操作</td>
        <td>
            <button id="save" class="btn btn-primary" type="button">保存</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>

	<script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="index.html";
		 });
		$('#save').click(function() {
			save();
		});	
		$("#per_id").on(" input propertychange",function(){
			  getPerson();
			})

    });

</script>
</body>
</html>
