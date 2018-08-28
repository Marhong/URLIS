<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <title>查看人员信息变动</title>
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
        <td width="10%" class="tableleft">姓名</td>
        <td><form:hidden path="per_name"/><a href="" id="link_name_value"></a></td>
		<td width="10%" class="tableleft">现单位</td>
        <td><form:hidden path="dept_id"  /><span id="dept_id_value" ></span></td>
    </tr>

    <tr>
		<td width="10%" class="tableleft">身份证</td>
        <td> <form:hidden path="per_id"  /><span id="per_id_value" ></span>
		</td>
		<td width="10%" class="tableleft">现职务</td>
        <td><form:hidden path="pos_name"  /><span id="pos_name_value" ></span>
		</td>
	</tr>
    <tr>
		<td colspan="4" ><center>变动信息汇总</center></td>
 
	</tr>	
    <tr>
		<td width="10%" class="tableleft">在职状态</td>
        <td colspan="3"><form:hidden path="chan_onpost"  /><span id="chan_onpost_value" ></span>
		</td>
	</tr>
    <tr>
		<td width="10%" class="tableleft">单位编号</td>
        <td colspan="3"> <form:hidden path="chan_dep"  /><span id="chan_dep_value" ></span>
		</td>
	</tr>
    <tr>
		<td width="10%" class="tableleft">工作职务</td>
        <td colspan="3"> <form:hidden  path="chan_posname"  /><span id="chan_posname_value" ></span>
		</td>
	</tr>
    <tr>
		<td width="10%" class="tableleft">工作职责</td>
        <td colspan="3"> <form:hidden   path="chan_workduty" /><span id="chan_workduty_value" ></span>
		</td>
	</tr>
    <tr>
		<td width="10%" class="tableleft">电话</td>
        <td colspan="3"> <form:hidden path="chan_tel"  /><span id="chan_tel_value" ></span>
		</td>
	</tr>
    <tr>
		<td width="10%" class="tableleft">变更时间</td>
        <td colspan="3"><form:hidden path="chan_time"  /><span id="chan_time_value" ></span>
		</td>
	</tr>
    <tr>
        <td class="tableleft">操作</td>
        <td>
           <button type="button" class="btn btn-success" name="back" id="back">返回列表</button>
        </td>
    </tr>
</table>
</form:form>
  <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/bootstrap.min.js"></script>
	<script>
    $(function () {  
    	initPage();
		$('#back').click(function(){
				window.location.href="index";
		 });

    });
    function initPage(){
    	var per_name_value = $('#per_name').val();
    	var dept_id_value = $('#dept_id').val();
    	var per_id_value = $('#per_id').val();
    	var chan_onpost_value=$('#chan_onpost').val();
    	var chan_dep_value=$('#chan_dep').val();
    	var chan_posname_value=$('#chan_posname').val();
    	var chan_workduty_value=$('#chan_workduty').val();
    	var chan_tel_value=$('#chan_tel').val();
    	var chan_time_value=$('#chan_time').val();
    	var pos_name_value=$('#pos_name').val();
    	
    	$('#pos_name_value').text(pos_name_value);
    	$('#link_name_value').text(per_name_value);
    	$('#link_name_value').attr('href','/URLIS/person/detail?per_id='+per_id_value);
    	$('#dept_id_value').text(dept_id_value);
    	$('#per_id_value').text(per_id_value);
    	$('#chan_onpost_value').text(chan_onpost_value);
    	$('#chan_dep_value').text(chan_dep_value);
    	$('#chan_posname_value').text(chan_posname_value);
    	$('#chan_workduty_value').text(chan_workduty_value);
    	$('#chan_tel_value').text(chan_tel_value);
    	$('#chan_time_value').text(chan_time_value);
    }
</script>
</body>
</html>
