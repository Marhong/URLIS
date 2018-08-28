<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <title>新增注册人员</title>
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
        <td><input type="text" name="per_name" id="per_name"/></td>
				<td width="10%" class="tableleft">身份证</td>
        <td><input type="text" name="per_id" id="per_id"/></td>
    </tr>

    <tr>
		<td width="10%" class="tableleft">性别</td>
        <td> <select class="form-control" name="per_gender" id="per_gender">
				<option>男</option>
				<option>女</option>

			</select>
		</td>
		<td width="10%" class="tableleft">出生日期</td>
        <td>              
			<div class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="16" type="text" value="" readonly id="per_birthday">
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
        <td> <select class="form-control" name="per_state" id="per_state">
				<option>在职</option>
				<option>离职</option>

			</select>
		</td>
		  <td width="10%" class="tableleft">联系电话</td>
        <td><input type="text" name="per_tel" id="per_tel"/></td>
	</tr>	

    <tr>
        <td width="10%" class="tableleft">账号密码</td>
        <td><input type="text" name="password" value="123456" id="password"/></td>
		<td width="10%" class="tableleft">任职日期</td>
        <td>              
			<div class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="16" type="text" value="" readonly id="per_worktime">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
		</td>
    </tr>

   <tr>
        <td width="10%" class="tableleft">参加工作时间</td>
        <td><input type="text" name="work_time" id="work_time"/></td>
		<td width="10%" class="tableleft">单位编号</td>
         <td><form:select path="dept_id">
        		<form:options items="${deptList }" path="dept_id" />
        	</form:select>
        </td>
    </tr>

    <tr>
        <td width="10%" class="tableleft">职务名称</td>
        <td><input type="text" name="pos_name" id="pos_name"/></td>
		<td width="10%" class="tableleft"></td>
        <td></td>
    </tr>	

    <tr>
		<td width="10%" class="tableleft">工作职责</td>
        <td><textarea cols="50" rows="3" name="work_duty"  style="resize:none" id="work_duty"></textarea></td>
        <td width="10%" class="tableleft">备注</td>
        <td><textarea cols="50" rows="3" name="per_remark"  style="resize:none" id="per_remark"></textarea></td>
    </tr>	

    <tr>
        <td class="tableleft">操作</td>
        <td>
            <button type="button" class="btn btn-primary" id="save">保存</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form:form>

	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/person/add.js"></script>

	<script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="index.html";
		 });
		// 提交表单
 		$('#save').click(
				function() {
					
					save();
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
