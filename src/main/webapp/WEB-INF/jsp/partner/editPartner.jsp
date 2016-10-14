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
	              <h3 class="box-title">字典管理--><c:if test="${!edit }">新增</c:if> <c:if test="${edit }">编辑</c:if></h3>
	            </div>
	            <!-- /.box-header -->
	            <form id="memberForm" enctype="multipart/form-data" method="POST" action="${base}/admin/auth/partner/savePartner" >
	              <div class="box-body">
	             <!-- text input -->
	                <div class="form-group">
	                  <label>昵称</label>
	                  <input type="hidden" id="id" name="id" value="${partner.id }">
	                  <input class="form-control" type="text" name="nickName" id="nickName" value="${partner.nickName}"   maxlength="50"/>
	                  <span class="help-block"></span>
	                </div>
                	<div class="form-group">
	                  <label>邮箱</label>
							<input class="form-control" type="text" name="email" id="email" value="${partner.email}"  maxlength="50" />
	                  <span class="help-block"></span>
	                </div>
	                
	                <div class="form-group">
	                  <label>电话号码</label>
						<input class="form-control" type="text" name="mobile" id="mobile" value="${partner.mobile}"  maxlength="11"/>
	                  <span class="help-block"></span>
	                </div>
	                <div class="form-group">
	                   <label>职业</label>
	                   <input type="hidden" name="jobName" id="jobName" >
					   <select id="jobId" name="jobId" class="form-control">
		                  <c:forEach items="${jobList}" var="job"> 
		                  	<option value="${job.diKey}" >${job.diValue} </option>
		                  </c:forEach>
		                </select>
		                <span class="help-block"></span>
	                </div>
	                 <div class="form-group">
	                  <label>密码(修改视为充值密码使用MD5加密)</label>
						<input class="form-control" type="password" name="password" id="password" value="${partner.password}"  maxlength="200"/>
	                  <span class="help-block"></span>
	                </div>
	                <div class="form-group">
	                  <label>关于</label>
	                  <input type="text" id="about" name="about" class="form-control" value="${partner.about }" maxlength="200">
	                  <span class="help-block"></span>
	                </div> 
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
				$("#cencel").on("click",function(){
					window.history.back(-1);
				});
				var submitFlag = true;
				$("#submit").on("click",function(){
					submitFlag = false;
					$("#jobName").val($("#jobId").find("option:selected").text());
					$("#submitBu").trigger("click");
				});
			});
		</script>
	</div>
</body>
</html>