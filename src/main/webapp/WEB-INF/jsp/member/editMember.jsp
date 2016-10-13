<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/jscss.jsp"%>
<script src="${base}/dist/js/showdown.js"></script>
<title>会员-->新增</title>
<style type="text/css">
	table.dataTable thead .sorting_asc:after {
	    content: "";
	}
	.btn-default{
		color: white;
	}
	.file-input-name{
		display: none;
	}
	.btn-default {
    	background-color: #A4F221;
	}
	.leadp{text-indent: 20px;}
	#preTitle{
		text-ailgn:center;
	}
</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<%@include file="../common/top.jsp"%>
		<%@include file="../common/menu.jsp"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
		<div class="content-wrapper">
			<section class="content">
			<div class="box box-info">
	            <div class="box-header with-border">
	              <h3 class="box-title">会员管理--><c:if test="${!edit }">新增</c:if> <c:if test="${edit }">编辑</c:if></h3>
	            </div>
	            <!-- /.box-header -->
	            <form id="memberForm" enctype="multipart/form-data" method="POST" action="${base}/admin/auth/member/save" >
	              <div class="box-body">
	                <!-- text input -->
	                <div class="form-group">
	                  <label>用户名</label>
	                  <input type="hidden" id="id" name="id" value="${member.id }">
	                  <input type="text" id="username" name="username" class="form-control" value="${member.username }" placeholder="6-20个字符">
	                  <span class="help-block"></span>
	                </div>
	                <c:if test="${!edit }">
	                	<div class="form-group">
		                  <label>密码</label>
		                  <input type="password" id="password" name="password" class="form-control" value="${member.password }" placeholder="6-20个字符">
		                  <span class="help-block"></span>
		                </div>
	                </c:if>
	                
	                <div class="form-group">
	                  <label>手机号码</label>
	                  <input type="text" id="mobile" name="mobile" class="form-control" value="${member.mobile }" placeholder="11位手机号码">
	                  <span class="help-block"></span>
	                </div>
	                <%--  <div class="form-group">
	                  <label>邮箱</label>
	                  <input type="text" id="email" name="email" class="form-control" value="${member.email }" placeholder="6-20个字符">
	                  <span class="help-block"></span>
	                </div>
	                <div class="form-group">
	                  <label>昵称</label>
	                  <input type="text" id="nickname" name="nickname" class="form-control" value="${member.nickname }" placeholder="6-20个字符">
	                  <span class="help-block"></span>
	                </div> --%>
		            <div class="box-footer">
	                    <button type="button" id="cencel" class="btn btn-default">取消</button>
	                    <button type="button" id="submit" class="btn btn-info pull-right">保存</button>
	                    <button type="subimt" id="submitBu" class="file-input-name">保存</button>
	                </div>
	            </div>
	             </form>
            <!-- /.box-body -->
          </div>
			</section>
		</div>
		<%@include file="../common/footer.jsp"%>

		<div id="preHtml" class="modal fade"  tabindex="-1" role="basic" aria-hidden="true">
			<div class="content-wrapper">
				<div class="content body">
					<section id="introduction">
					   <div id="preview-body"></div>
					</section>
				</div>
				<div style="text-align: center;">
				  	<button type="button" id="htmlPreviewHide" class="btn btn-default">确定</button>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="${base}/plugins/bootstrap-file-input/bootstrap.file-input.js"></script>
		<script>
			jQuery(document).ready(function() {
				$("#member").addClass("active");
				if(!$("#id").val()){
					$("#divPreview").addClass("file-input-name");
				}
				$("#username").on("blur",function(){
					if($(this).val().trim().length<6||$(this).val().trim().length>20){
						$(this).parent().addClass("has-error");
						$(this).parent().find("span").text("请输入6-20个字符");
					}else{
						$.ajax({
							url : basePath + "/admin/auth/member/checkUsername",
							data : {username:$(this).val()},
							type : "get",
							dataType : "json",
							async : false, 
							success : function(data){
								if(data.success){
									$("#username").parent().addClass("has-error");
									$("#username").parent().find("span").text(data.msg);
								}else{
									$("#username").parent().removeClass("has-error");
									$("#username").parent().find("span").text("");
								}
							}
						});
					}
				});
				$("#password").on("blur",function(){
					if($(this).val().trim().length<6||$(this).val().trim().length>20){
						$(this).parent().addClass("has-error");
						$(this).parent().find("span").text("请输入6-20个字符");
					}else{
						$(this).parent().removeClass("has-error");
						$(this).parent().find("span").text("");
					}
				});
				var regex =  /^[\d]{1,3}$/;
				$("#cencel").on("click",function(){
					window.history.back(-1);
				});
				var submitFlag = true;
				$("#submit").on("click",function(){
					var flag = true;
					if($("#username").val().trim().length<6||$("#username").val().trim().length>20){
						$("#username").trigger("blur")
						flag = false;
					}
					if($("#password").val().trim().length<6||$("#password").val().trim().length>20){
						$("#password").trigger("blur")
						flag = false;
					}
					if(flag && submitFlag){
						submitFlag = false;
						$("#submitBu").trigger("click");
					}
				});
			});
			
			/** 
			* 从 file 域获取 本地图片 url 
			*/ 
			function getFileUrl(sourceId) { 
				var url; 
				if (navigator.userAgent.indexOf("MSIE")>=1) { // IE 
				url = document.getElementById(sourceId).value; 
				} else if(navigator.userAgent.indexOf("Firefox")>0) { // Firefox 
				url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0)); 
				} else if(navigator.userAgent.indexOf("Chrome")>0) { // Chrome 
				url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0)); 
				} 
				return url; 
			} 
		</script>
	</div>
</body>
</html>