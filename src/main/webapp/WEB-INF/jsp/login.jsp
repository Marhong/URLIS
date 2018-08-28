<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>城乡执法管理系统</title>
<meta name="author" content="DeathGhost" />

   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/loginstyle.css" tppabs="css/style.css" />
<style>
body{height:100%;background:#16a085;overflow:hidden;}
canvas{z-index:-1;position:absolute;}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/login/login.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/login/Particleground.js" tppabs="<%=request.getContextPath()%>/static/js/login/Particleground.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/login/verificationNumbers.js" tppabs="<%=request.getContextPath()%>/static/js/login/verificationNumbers.js"></script>

<script>
$(document).ready(function() {

  //粒子背景特效
  $('body').particleground({
    dotColor: '#5cbdaa',
    lineColor: '#5cbdaa'
  });
  //验证码
  createCode();
  //测试提交，对接程序删除即可

  $("#login").click(function(){
	 if(valid()){
		 if(validate()){	 
			 login();
		 }
	 }
  });
});
</script>
</head>
<body>
<dl class="admin_login">
 <dt>
  <strong>城乡执法管理系统</strong>
  <em>Management System</em>
 </dt>
 <dd class="user_icon">
  <input type="text"  class="login_txtbx"  placeholder="账号" name="per_id" id="per_id" onkeyup="this.value=this.value.replace(/\D/g,'')" />
 </dd>
 <dd class="pwd_icon">
  <input type="password"  class="login_txtbx" placeholder="密码"  name="password" id="password" />
 </dd>
 <dd class="val_icon">
  <div class="checkcode">
    <input type="text" id="J_codetext" placeholder="验证码" maxlength="4" class="login_txtbx">
    <canvas class="J_codeimg" id="myCanvas" onclick="createCode()">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
  </div>
  <input type="button" value="更换验证码" class="ver_btn" onClick="createCode();">
 </dd>
 <dd>
  <input type="button" value="立即登陆" id="login" class="submit_btn"/>
 </dd>

</dl>
</body>
</html>
