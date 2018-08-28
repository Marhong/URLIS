<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <title>修改组织机构</title>
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
<form:form action="index.html" method="post">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">机构名称</td>
        <td><form:input path="dept_id" readonly="true" /></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">机构名称</td>
        <td><form:input path="dept_name" /></td>
    </tr>

    <tr>
        <td class="tableleft">操作</td>
        <td>
            <button id="update" class="btn btn-primary" type="button">保存</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form:form>
   
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/department/edit.js"></script>


	<script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="index.html";
		 });
		// 提交表单
 		$('#update').click(
				function() {
					update();
				}); 

    });

</script>
</body>
</html>
