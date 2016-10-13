<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/jscss.jsp"%>
<script src="${base}/dist/js/showdown.js"></script>
<title>推荐周-<c:if test="${edit}">编辑</c:if>  <c:if test="${!edit}">新增</c:if></title>
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
	              <h3 class="box-title">推荐周--><c:if test="${edit}">编辑</c:if>  <c:if test="${!edit}">新增</c:if></h3>
	            </div>
	            <!-- /.box-header -->
	            <form id="recommendWeekForm" enctype="multipart/form-data" method="POST" action="${base}/admin/auth/recommendWeek/save" >
	              <div class="box-body">
	                <!-- text input -->
	                <div class="form-group">
	                  <label>名称</label>
	                  <input type="hidden" id="id" name="id" value="${recommendWeek.id }">
	                  <input type="text" id="name" name="name" class="form-control" value="${recommendWeek.name }" placeholder="1-20个字符">
	                  <span class="help-block"></span>
	                </div>
	                
	                <div class="form-group">
	                  <label for="exampleInputFile">分类背景图</label>
	                  <input type="file" id="img" name="img">
	                  <p class="help-block"></p>
	                </div>
	                <div class="form-group">
	                  <label>描述</label>
	                  <textarea class="form-control" rows="3" id="des" name="des" placeholder="10-300个字符" value="" maxlength="300" >${recommendWeek.des }</textarea>
	                  <span class="help-block"></span>
	                </div>
	                
	                <div id ="divPreview" class="form-group">
	                	<img alt="预览图片" src="${recommendWeek.imgUrl }" id="imgPreview" style="max-width:100%;height: 50px;width: 100px;">
	                </div>
	                 <div class="form-group">
		                <label>上架状态</label>
		                <select id="stauts" name="stauts" class="form-control" value="${recommendWeek.stauts }">
		                  <option <c:if test="${!recommendWeek.stauts }"> selected="selected"</c:if> value=0>已上架</option>
		                  <option <c:if test="${recommendWeek.stauts }"> selected="selected"</c:if> value=1>下架</option>
		                </select>
		            </div>
	                <!-- textarea -->
	                <div class="form-group">
	                  <label>序号</label> 
	                  <input type="text" maxlength="3"  class="form-control" placeholder="0-999之间整数" id="seqNum" name="seqNum" value="${recommendWeek.seqNum }" >
	                  <span class="help-block"></span>
	                </div>
	                <div class="form-group">
	                  <label>故事ID(以英文逗号为分隔)</label>
	                  <textarea class="form-control" rows="3" id="storyIds" name="storyIds" placeholder="" value="">${recommendWeek.storyIds }</textarea>
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
				$("#recommendWeek").addClass("active");
				if(!$("#id").val()){
					$("#divPreview").addClass("file-input-name");
				}
				$("#img").on("change",function(){
					var reg = /^.*[^a][^b][^c]\.(?:png|jpg|bmp|gif|jpeg)$/;
					if(!reg.test($(this).val())){
						$(this).parent().addClass("has-error");
						$(this).parent().find("p").text("文件格式不正确,图片必须后缀为png|jpg|bmp|gif|jpeg");
						$("#divPreview").addClass("file-input-name");
						$(this).val("");
						return false;
					}
					url = getFileUrl(this.id);
					$("#imgPreview").attr("src",url);
					$(this).parent().removeClass("has-error");
					$(this).parent().find("p").text("");
					$("#divPreview").removeClass("file-input-name");
					return true;
				});
				$("#name").on("blur",function(){
					if($(this).val().trim().length<1||$(this).val().trim().length>20){
						$(this).parent().addClass("has-error");
						$(this).parent().find("span").text("请输入1-20个字符");
					}else{
						$(this).parent().removeClass("has-error");
						$(this).parent().find("span").text("");
					}
				});
				var regex =  /^[\d]{1,3}$/;
				$("#seqNum").on("blur",function(){
					if(($(this).val().trim()).match(regex)){
						$(this).parent().removeClass("has-error");
						$(this).parent().find("span").text("");
					}else{
						$(this).parent().addClass("has-error");
						$(this).parent().find("span").text("请输入0-999之间整数");
					}
				});
				$("#cencel").on("click",function(){
					window.history.back(-1);
				});
				var submitFlag = true;
				$("#submit").on("click",function(){
					var flag = true;
					if($("#name").val().trim().length<1||$("#name").val().trim().length>20){
						$("#name").trigger("blur");
						flag = false;
					}
					if(!($("#seqNum").val().trim()).match(regex)){
						$("#seqNum").trigger("blur");
						flag = false;
					}
					if(!$("#imgPreview").attr("src")){
						$("#img").trigger("change");
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
				} else {
					url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0)); 
				}
				return url;  
			} 
		</script>
	</div>
</body>
</html>