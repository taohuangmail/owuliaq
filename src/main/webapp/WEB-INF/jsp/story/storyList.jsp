<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/jscss.jsp"%>
<title>故事</title>
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
	.bj-img{
	    height: 80px;
    	width: 80px;
	}
	.error_span{
		color: red;
        font-size: 14px;
	}
</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<%@include file="../common/top.jsp"%>
		<%@include file="../common/menu.jsp"%>
		<div class="content-wrapper">
			<section class="content">
			 <div class="form-body form-horizontal this-dgd">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-4 form-group">
							<label class="control-label col-md-4 textRight">名称</label>
							<div class="col-md-8">
								<input type="text" class="form-control" name="schName" id="schName" />
							</div>
						</div>
						<div class="col-md-4 form-group">
							<label class="control-label col-md-4 textRight">序号</label>
							<div class="col-md-8">
								<input type="text" class="form-control" name="schSeqNum" id="schSeqNum" />
							</div>
						</div>
						<div class="col-md-4 form-group">
							<label class="control-label col-md-4 textRight">上架状态</label>
							<div class="col-md-8">
				                <select id="schStatus" name="schStatus" class="form-control">
				              	  <option  value=>请选择</option>
				                  <option  value=0>已上架</option>
				                  <option  value=1>下架</option>
				                </select>
							</div>
						</div>
						<div class="col-md-4 form-group">
							<label class="control-label col-md-4 textRight">故事分类</label>
							<div class="col-md-8">
				                <select id="schCategoryId" name="schCategoryId" class="form-control">
				                  <option value="" >请选择 </option>
				                  <c:forEach items="${categoryList}" var="categorye"> 
				                  	<option value="${categorye.id}" >${categorye.name} </option>
				                  </c:forEach>
				                </select>
							</div>
						</div>
						<div  class="col-md-4 form-group">
					 		<input type="button" value="查询" class="btn btn-primary" onclick="reloadTable();">
					 		<input type="button" value="新增" class="btn btn-primary" onclick="add();")>
					 	</div> 
					 </div>
					<div class="col-md-12">
						<span class="error_span">${msg}</span>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">故事</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<table id="models-data" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th width="10%">ID</th>
										<th width="10%">故事名称</th>
										<th width="10%">分类</th>
										<th width="8%">收藏数</th>
										<th width="10%">推荐状态</th>
										<th width="7%">序号</th>
										<th width="15%">文件地址</th>
										<th width="10%">专辑封面</th>
										<!-- <th width="10%">作者</th> -->
										<th width="10%">操作</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>

				</div>
			</section>
		</div>

		<%@include file="../common/footer.jsp"%>

		<div class="modal fade" id="editDialog" tabindex="-1" role="basic" aria-hidden="true"></div>
		<script type="text/javascript" src="${base}/plugins/bootstrap-file-input/bootstrap.file-input.js"></script>
		<script type="text/javascript" src="${base}/js/storyList.js"></script>
		<script>
			jQuery(document).ready(function() {
				dataTable = TableManaged.init();
				$("#story").addClass("active");
			});
		</script>
	</div>
</body>
</html>