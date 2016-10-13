<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="common/jscss.jsp" %>
<title>登录</title>
</head>

<body class="hold-transition login-page">
   <div class="login-box">
     <div class="login-logo">
       <a href="javascript:void(0);"><b>OWULIA</b>管理平台</a>
     </div><!-- /.login-logo -->
     <div class="login-box-body">
       <p class="login-box-msg">请输入你的账号和密码</p>
       <form action="${base}/admin/login.htm" method="post">
         <div class="form-group has-feedback">
           <input type="text" class="form-control" placeholder="用户名" name="username">
           <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
         </div>
         <div class="form-group has-feedback">
           <input type="password" class="form-control" placeholder="密码" name="password">
           <span class="glyphicon glyphicon-lock form-control-feedback"></span>
         </div>
         <div class="row">
           <div class="col-xs-8">
           	<span style="color:red;">${msg}</span>
           </div>
           <div class="col-xs-4">
             <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
           </div><!-- /.col -->
         </div>
       </form>
     </div>
   </div>

</body>
</html>