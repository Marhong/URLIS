<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>新增组织机构</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/style.css" />
	
    <link href="<%=request.getContextPath()%>/static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/department/add.js"></script>
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
        <td width="10%" class="tableleft">机构编号</td>
        <td><input type="text" name="dept_id" id="dept_id"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">机构名称</td>
        <td><input type="text" name="dept_name" id="dept_name"/></td>
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
    });

</script>
</body>
</html>
