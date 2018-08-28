<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <title>新增成绩</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/style.css" />
	
    <link href="<%=request.getContextPath()%>/static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/examrecord/add.js"></script>
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
        <td width="10%" class="tableleft">身份证</td>
        <td><input type="text" name="per_id" id="per_id" /></td>
    </tr>	
    <tr>
		<td width="10%" class="tableleft">考试编号</td>
        <td> <form:select path="exam_id">
        		<form:options items="${examlist }" path="exam_id" />
        	</form:select>
		</td>
	</tr>
    <tr>
        <td width="10%" class="tableleft">成绩</td>
        <td><input type="text" name="exam_score" id="exam_score" onkeyup="javascript:CheckInputIntFloat(this);" /></td>
    </tr>

    <tr>
        <td class="tableleft">操作</td>
        <td>
            <button id="save" class="btn btn-primary" type="button">保存</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form:form>




	<script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="index";
		 });
		// 提交表单
 		$('#save').click(
				function() {	
					save();
				}); 

    });
    function CheckInputIntFloat(oInput) { 
    	   if('' != oInput.value.replace(/\d{1,}\.{0,1}\d{0,}/,'')) { 
    	        oInput.value = oInput.value.match(/\d{1,}\.{0,1}\d{0,}/) == null ? '' :oInput.value.match(/\d{1,}\.{0,1}\d{0,}/); 
    	   } 
    	}
</script>
</body>
</html>
