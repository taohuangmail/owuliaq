<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/jscss.jsp"%>
<script src="${base}/dist/js/showdown.js"></script>
<title>故事--><c:if test="${edit}">编辑</c:if>  <c:if test="${!edit}">新增</c:if></title>
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
	
    .modal1{    
	    position: fixed;
	    top: 0;
	    right: 0;
	    bottom: 0;
	    left: 0;
	    z-index: 1050;
	    display: none;
	    overflow: hidden;
	    -webkit-overflow-scrolling: touch;
	    outline: 0;
	    background: rgba(0,0,0,0.3);
	    box-sizing: border-box;
    }
    
    .show-upload{
		display: block;
    	padding-right: 17px;
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
	              <h3 class="box-title">故事--><c:if test="${edit}">编辑</c:if>  <c:if test="${!edit}">新增</c:if></h3>
	            </div>
	            <!-- /.box-header -->
	            <form id="storyForm" enctype="multipart/form-data" method="POST" action="${base}/admin/auth/story/save" >
	              <div class="box-body">
	                 <div class="form-group">
		                <label>故事分类</label>
		                <select id="categoryId" name="categoryId" class="form-control">
		                  <c:forEach items="${categoryList}" var="categorye"> 
		                  	<option value="${categorye.id}" >${categorye.name} </option>
		                  </c:forEach>
		                </select>
		            </div>
	                <!-- text input -->
	                <c:if test="${edit}">
		                <div class="form-group">
		                  <label>名称</label>
		                  <input type="hidden" id="id" name="id" value="${story.id }">
		                  <input type="text" id="name" name="name" class="form-control" value="${story.name }" placeholder="1-50个字符" disabled="true">
		                  <span class="help-block"></span>
		                </div>
	                </c:if>
	                <div class="form-group">
	                  <label>收藏数</label>
	                  	                  
	                  <input type="text" id="click" name="click" class="form-control" value="${story.click }" value="0" placeholder="整数">
	                  <span class="help-block"></span>
	                </div>
	                <div class="form-group">
		                <label>是否推荐</label>
		                <select id="recommend" name="recommend" class="form-control" value="">
		                  <option <c:if test="${story.recommend }"> selected="selected"</c:if> value=0>普通</option>
		                  <option <c:if test="${!story.recommend }"> selected="selected"</c:if> value=1>推荐</option>
		                </select>
		            </div>
		            
		             <div class="form-group">
		                <label>上架状态</label>
		                <select id="status" name="status" class="form-control" value="${story.status }">
		                  <option <c:if test="${!story.status }"> selected="selected"</c:if> value=0>已上架</option>
		                  <option <c:if test="${story.status }"> selected="selected"</c:if> value=1>下架</option>
		                </select>
		            </div>
		            
		            <div class="form-group">
	                  <label>序号(作用于接口排序)</label>
	                  <input type="text" id="seqNum" name="seqNum" class="form-control" value="${story.click }" placeholder="整数">
	                  <span class="help-block"></span>
	                </div>
	                <div class="form-group">
	                  <label for="exampleInputFile"><c:if test="${edit}">变更</c:if>故事音频文件(必须要有专辑封面)</label>
	                  <input type="file" id="storyfile" name="storyfile">
	                  <input type="hidden" id="url" name="url" value="${story.url }">
	                  <p class="help-block"></p>
	                </div>
	                <div class="form-group">
	                  <label for="exampleInputFile">专辑封面</label>
	                  <input type="file" id="img" name="img">
	                   <input type="hidden" id="tagRunner" name="tagRunner" value="${story.tagRunner }">
	                  <p class="help-block"></p>
	                </div>
	                <div id ="divPreview" class="form-group" >
	                	<img alt="预览图片" src="${story.tagRunner }"  id="imgPreview" style="max-width:100%;height: 100px;width: 100px;">
	                </div>
	                <%-- <div class="form-group">
	                  <label>作者</label> 
	                  <input type="text" maxlength="50"  class="form-control" placeholder="" id="author" name="author" value="${story.author }" >
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
		<div  class="modal1" id="uploadDialog" tabindex="-1" role="basic" aria-hidden="true">
			<div class="modal-dialog modal-wide">
				<div class="modal-content well node">
					<div class="modal-header mbt20">
						<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button> -->
						<h4 class="modal-title">正在上传文件请稍后</h4>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="${base}/plugins/bootstrap-file-input/bootstrap.file-input.js"></script>
		<script>
			jQuery(document).ready(function() {
				$("#story").addClass("active");
				if(!$("#id").val()){
					$("#divPreview").addClass("file-input-name");
				}
				$("#storyfile").on("change",function(){
					var reg = /^.*[^a][^b][^c]\.(?:mp3)$/;
					if(!reg.test($(this).val())){
						$(this).parent().addClass("has-error");
						$(this).parent().find("p").text("文件格式不正确,必须为MP3格式文件");
						$(this).val("");
						return false;
					}
					$(this).parent().find("p").text("");
					return true;
				});
				$("#img").on("change",function(){
					var reg = /^.*[^a][^b][^c]\.(?:jpg)$/;
					if(!reg.test($(this).val())){
						$(this).parent().addClass("has-error");
						$(this).parent().find("p").text("文件格式不正确,必须为.jpg格式文件");
						$("#divPreview").addClass("file-input-name");
						$(this).val("");
						return false;
					}
					var url = getFileUrl(this.id);
					$("#imgPreview").attr("src",url);
					$("#divPreview").removeClass("file-input-name");
					$(this).parent().removeClass("has-error");
					$(this).parent().find("p").text("");
					return true;
				});
				/* $("#name").on("blur",function(){
					if($(this).val().trim().length<1||$(this).val().trim().length>50){
						$(this).parent().addClass("has-error");
						$(this).parent().find("span").text("请输入1-50个字符");
					}else{
						$(this).parent().removeClass("has-error");
						$(this).parent().find("span").text("");
					}
				}); */
				var regex =  /^[\d]{1,11}$/;
				$("#seqNum").on("blur",function(){
					if(($(this).val().trim()).match(regex)){
						$(this).parent().removeClass("has-error");
						$(this).parent().find("span").text("");
					}else{
						$(this).parent().addClass("has-error");
						$(this).parent().find("span").text("请输入0-999之间整数");
					}
				});
				var regex =  /^[\d]{1,11}$/;
				$("#click").on("blur",function(){
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
				var subimtFlag = true;
				$("#submit").on("click",function(){
					var flag = true;
					/* if($("#name").val().trim().length<1||$("#name").val().trim().length>20){
						$("#name").trigger("blur");
						flag = false;
					} */
					if(!($("#seqNum").val().trim()).match(regex)){
						$("#seqNum").trigger("blur");
						flag = false;
					}
					if(!$("#url").val() && !$("#storyfile").val()){
						$("#storyfile").trigger("change");
						flag = false;
					}
					if(!$("#tagRunner").val() && !$("#img").val()){ 
						$("#img").trigger("change");
						flag = false;
					}
					if(flag && subimtFlag){
						subimtFlag = false;
						$('#uploadDialog').addClass("show-upload");
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