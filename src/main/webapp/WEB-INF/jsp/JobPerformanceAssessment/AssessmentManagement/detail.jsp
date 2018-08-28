<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
    <title>查看考核记录</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css//style.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/assessment/add.js"></script>


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
        <td><input type="text" name="per_name" id="per_name" value=${assessment.per_name } readonly /></td>
    </tr>

    <tr>
		<td width="10%" class="tableleft">身份证</td>
        <td> <input type="text" name="per_id" id="per_id" value=${assessment.per_id } readonly/>
		</td>
	</tr>
	<tr>
		<td width="10%" class="tableleft">考核月份</td>
        <td><input type="text" name="asse_date" id="asse_date" value=${assessment.asse_date } readonly />
		</td>
	</tr>
    <tr>
		<td width="10%" class="tableleft">考试得分</td>
        <td> <input type="text" name="exam_socre" id="exam_score" readonly/>
		</td>
	</tr>
    <tr>
		<td width="10%" class="tableleft">考勤得分</td>
        <td> <input type="text" name="attn_score" id="attn_score" readonly/>
		</td>
	</tr>
    <tr>
		<td width="10%" class="tableleft">最终分</td>
        <td> <input type="text" name="asse_score"  id="asse_score" value=${assessment.asse_score } readonly/>
		</td>
	</tr>
    <tr>
        <td class="tableleft">操作</td>
        <td>
            <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
	<script>
    $(function () {      
    	initPage();
		$('#backid').click(function(){
				window.location.href="index";
		 });
		
    });

</script>
</body>
</html>
