<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>修改考卷</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/style.css" />
	
    <link href="<%=request.getContextPath()%>/static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/paper/edit.js"></script>
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
        <td width="10%" class="tableleft">考卷名称</td>
        <td><input type="text" name="paper_name" id="paper_name" value=${paper.paper_name } />
        <input type="hidden" id="paper_id" name="paper_id" value=${paper.paper_id } />
        <input type="hidden" id="ids" name="ids" value=${ids } />
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">考卷分数</td>
        <td><input type="text" name="paper_score" id="paper_score"   value=${paper.paper_score }  readonly/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">考试时长</td>
        <td><input type="text" name="paper_time" id="paper_time"   value=${paper.paper_time } /></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">考卷备注</td>
        
      
        <td> <input type="hidden" id="paper_remark_value" name="paper_remark_value" value=${paper.paper_remark } />
        <textarea cols="50" rows="3" name="paper_remark" id="paper_remark" style="resize:none"  ></textarea></td>
    </tr>
    <tr>
	 <td width="10%" class="tableleft">选题<br/>&nbsp;&nbsp;&nbsp;&nbsp;还须选选择题:<span id="choiceItems">全部选好</span>题<br/>&nbsp;&nbsp;&nbsp;&nbsp;已选判断题<span id="judgementItems">全部选好</span>题</td>
	 <td >
       <table class="table table-bordered table-hover definewidth m10" id="table">
			<thead>
			<tr>
				<th width="5%"><select class="form-control" name="qu_type" id="qu_type" >
				<option>选择题</option>
				<option>判断题</option>
			</select></th>
				<th>题目编号</th>
				<th>题目名称</th>
				<th>题目类别</th>
				<th>题目分值</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach items="${qulist}" var="qu">
			<tr>
				<td><input type="checkbox" value="${qu.qu_id }" class="choose"></checkbox></td>
				<td>${qu.qu_id}</td>
				<td>${qu.qu_content }</td>
				<td>${qu.qu_type}</td>
				<td>${qu.qu_score}</td>
			</tr>
</c:forEach>
		</table>
	</td>
    </tr>
    <tr>
        <td class="tableleft">操作</td>
        <td>
            <button type="button" class="btn btn-primary" id="update">保存</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>	
</table>
</form>

	<script>
    $(function () {  
    	var ids = $('#ids').val();
    	initPage(ids);
    	type();
		$('#backid').click(function(){
				window.location.href="index.html";
		 });
		$('#update').click(function(){
			save();
	 });
		$('#qu_type').click(function(){
			type();
	 	});
	$('.choose').click(function(){
			
			var choice = false;
			var type;
			if($(this).is(':checked')){
				choice = true;
			}else{
				choice = false;
			}
			if($(this).parent().parent().children('td').eq(3).text() == "判断题"){
				type = "判断题";
			}else{
				type = "选择题";
			}
			if(choice){
				if($('#choiceItems').text()>0 && type=="选择题"){
					choose(choice,type);			
				}else if($('#judgementItems').text()>0 && type == "判断题"){
					choose(choice,type);
				}else{
					alert("已经选好全部题目!");
					$(this).prop("checked",false);
				}
			}else{
				choose(choice,type);
			}
			
		});
    });

</script>
</body>
</html>
