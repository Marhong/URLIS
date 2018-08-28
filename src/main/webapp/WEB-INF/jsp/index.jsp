<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
  <title>城乡执法管理系统</title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <link href="<%=request.getContextPath()%>/static/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
  <link href="<%=request.getContextPath()%>/static/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
   <link href="<%=request.getContextPath()%>/static/assets/css/main-min.css" rel="stylesheet" type="text/css" />
 </head>
 <body>

  <div class="header">
    
      <div class="dl-title">
       <!--<img src="/chinapost/Public/assets/img/top.png">-->
      </div>

    <div class="dl-log">欢迎您，<span class="dl-log-user">${per_name }</span><a href="<%=request.getContextPath()%>/logout" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
  </div>
   <div class="content">
    <div class="dl-main-nav">
      <div class="dl-inform"><div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div></div>
      <ul id="J_Nav"  class="nav-list ks-clear">
        		<li class="nav-item dl-selected"><div class="nav-item-inner nav-home">人事信息管理</div></li>		     
				<li class="nav-item dl-selected"><div class="nav-item-inner nav-home">执法业务评测</div></li>		
				<li class="nav-item dl-selected"><div class="nav-item-inner nav-home">岗位绩效考核</div></li>		<li class="nav-item dl-selected"><div class="nav-item-inner nav-order">组织机构管理</div></li> 
      
	  </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>
   </div>
  <script type="text/javascript" src="<%=request.getContextPath()%>/static/assets/js/jquery-1.8.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/static/assets/js/bui-min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/static/assets/js/common/main-min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/static/assets/js/config-min.js"></script>
  <script>
    BUI.use('common/main',function(){
      var config = [{id:'1',homePage:'11',menu:[{text:'人事信息管理',items:[{id:'11',text:'人员基本信息管理',href:'person/index'},{id:'13',text:'人员信息变动管理',href:'change/index'}]}]},
	  {id:'3',homePage : '34',menu:[{text:'执法业务评测',items:[{id:'34',text:'考试管理',href:'exam/index'},{id:'31',text:'成绩管理',href:'examrecord/index'},{id:'36',text:'组卷管理',href:'paper/index'},{id:'33',text:'题目管理',href:'question/index'}]}]},
	  {id:'5',homePage : '51',menu:[{text:'岗位绩效考核',items:[{id:'51',text:'考核记录管理',href:'assessment/index.html'},{id:'52',text:'考勤记录管理',href:'attendance/index'}]}]},
	  {id:'6',homePage : '61',menu:[{text:'组织机构管理',items:[{id:'61',text:'组织机构管理',href:'department/index'}]}]},
];
      new PageUtil.MainPage({
        modulesConfig : config
      });
    });
  </script>
 </body>
</html>