<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <title>查看人员信息</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/person/add.js"></script>

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
<form:form action="index" method="post">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
         <td width="10%" class="tableleft"><form:label path="per_name">用户名</form:label></td>
         <td><form:input path="per_name" readonly="true" /></td>
		 <td width="10%" class="tableleft"><form:label path="per_id">身份证：</form:label></td>
         <td><form:input path="per_id" readonly="true" /></td>
    </tr>

    <tr>
		<td width="10%" class="tableleft">性别</td>
        <td>
         	<form:select path="per_gender" disabled="true">
                   <form:option value="男" label="男" />
				    <form:option value="女" label="女" />
            </form:select>
		</td>
		<td width="10%" class="tableleft">出生日期</td>
        <td>              
			<div class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                   <form:input path="per_birthday" disabled="true" />
            </div>
		</td>
	</tr>

	<tr>
		<td width="10%" class="tableleft">民族</td>
        <td>
        	<form:select path="per_nation" disabled="true">
        		<form:options items="${nationList }" path="per_nation" />
        	</form:select>
		</td>
		<td width="10%" class="tableleft">政治面貌</td>
        <td>
          	<form:select path="per_politics" disabled="true">
        		<form:options items="${politicsList }" path="per_politics" />
        	</form:select>
		</td>
	</tr>
	
    <tr>
		<td width="10%" class="tableleft">在值状态</td>
        <td>
        <form:select path="per_state" disabled="true">
                   <form:option value="在职" label="在职" />
				    <form:option value="离职" label="离职" />
            </form:select>
		</td>
		  <td width="10%" class="tableleft">联系电话</td>
        <td> <form:input path="per_tel" disabled="true" /></td>
	</tr>	

    <tr>
        <td width="10%" class="tableleft">账号密码</td>
        <td> <form:input path="password" disabled="true" /></td>
		<td width="10%" class="tableleft">任职日期</td>
        <td>              
			<div class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                     <form:input path="per_worktime" disabled="true" />
            </div>
		</td>
    </tr>

   <tr>
        <td width="10%" class="tableleft">参加工作时间</td>
        <td> <form:input path="work_time" disabled="true" /></td>
		<td width="10%" class="tableleft">单位编号</td>
        <td>
         <form:select path="dept_id" disabled="true">
        		<form:options items="${deptList }" path="dept_id" />
        	</form:select>
		</td>
    </tr>

    <tr>
        <td width="10%" class="tableleft">职务名称</td>
        <td> <form:input path="pos_name" disabled="true" /></td>
		<td width="10%" class="tableleft"></td>
        <td></td>
    </tr>	

    <tr>
		<td width="10%" class="tableleft">工作职责</td>
        <td>
         <form:textarea cols="50" rows="3" path="work_duty" disabled="true" /></td>
        <td width="10%" class="tableleft">备注</td>
        <td>
        <form:textarea cols="50" rows="3" path="per_remark" disabled="true" /></td>
    </tr>	

    <tr>
        <td class="tableleft">操作</td>
        <td>
           <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form:form>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/bootstrap.min.js"></script>

	<script>
    $(function () {  
    	//initPage();
		$('#backid').click(function(){
				//window.location.href="index.html";
			window.history.back(-1); 
		 });

    });

</script>
</body>
</html>
