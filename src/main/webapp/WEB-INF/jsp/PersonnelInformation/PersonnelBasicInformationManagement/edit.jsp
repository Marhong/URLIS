<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <title>修改人员信息</title>
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
<form:form action="save" method="post">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><form:input path="per_name"  readonly="true" /></td>
				<td width="10%" class="tableleft">身份证</td>
        <td><form:input path="per_id"  readonly="true" /></td>
    </tr>

    <tr>
		<td width="10%" class="tableleft">性别</td>
        <td> <form:select path="per_gender" >
                   <form:option value="男" label="男" />
				    <form:option value="女" label="女" />
            </form:select>
		</td>
		<td width="10%" class="tableleft">出生日期</td>
        <td>              
			<div class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    
                     <form:input path="per_birthday"   readonly="true" /> 
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
		</td>
	</tr>

	<tr>
		<td width="10%" class="tableleft">民族</td>
        <td><form:select path="per_nation">
        		<form:options items="${nationList }" path="per_nation" />
        	</form:select>
        </td>
		<td width="10%" class="tableleft">政治面貌</td>
         <td><form:select path="per_politics">
        		<form:options items="${politicsList }" path="per_politics" />
        	</form:select>
        </td>
	</tr>
	
    <tr>
		<td width="10%" class="tableleft">在值状态</td>
        <td>
			<form:select path="per_state">
        		<form:option value="在职" >在职</form:option>
        		<form:option value="离职" >离职</form:option>
        	</form:select>
		</td>
		  <td width="10%" class="tableleft">联系电话</td>
        <td><form:input path="per_tel"  /></td>
	</tr>	

    <tr>
        <td width="10%" class="tableleft">账号密码</td>
        <td><form:input path="password"  /></td>
		<td width="10%" class="tableleft">任职日期</td>
        <td>              
			<div class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    
                    <form:input path="per_worktime"  readonly="true" />
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
		</td>
    </tr>

   <tr>
        <td width="10%" class="tableleft">参加工作时间</td>
        <td><form:input path="work_time"  /></td>
		<td width="10%" class="tableleft">单位编号</td>
         <td><form:select path="dept_id">
        		<form:options items="${deptList }" path="dept_id" />
        	</form:select>
        </td>
    </tr>

    <tr>
        <td width="10%" class="tableleft">职务名称</td>
        <td><form:input path="pos_name"  /></td>
		<td width="10%" class="tableleft"></td>
        <td></td>
    </tr>	

    <tr>
		<td width="10%" class="tableleft">工作职责</td>
        <td><form:textarea cols="50" rows="3" path="work_duty"  /></td>
        <td width="10%" class="tableleft">备注</td>
        <td> <form:textarea cols="50" rows="3" path="per_remark"  /></td>
    </tr>	

    <tr>
        <td class="tableleft">操作</td>
        <td>
            <button type="button" class="btn btn-primary" id="update">保存</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form:form>

	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/person/edit.js"></script>

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
