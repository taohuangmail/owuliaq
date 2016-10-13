<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/jscss.jsp"%>
<title>查看推荐周故事</title>
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
							<label class="control-label col-md-12">
								<div  class="col-md-4">
									<input type="button" value="返回" class="btn btn-primary" onclick="history.back(-1);")>
								</div>
								<!-- <div  class="col-md-4">
							 		<input type="button" value="查询" class="btn btn-primary" onclick="reloadTable();">
							 	</div> -->
							 	
							</label>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">${recommendWeek.name }</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<table id="models-data" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th width="10%">ID</th>
										<th width="10%">故事名称</th>
										<th width="10%">分类</th>
										<th width="8%">点击数</th>
										<th width="7%">序号</th>
										<th width="15%">文件地址</th>
										<th width="10%">专辑封面</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ storyList}" var="story">
										<tr>
											<td >${story.id }</td>
											<td >${story.name }</td>
											<td >${story.categoryName }</td>
											<td >${story.click }</td>
											<td >${story.seqNum }</td>
											<td >${story.url }</td>
											<td ><img class="bj-img" src="${story.tagRunner }"></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>

				</div>
			</section>
		</div>

		<%@include file="../common/footer.jsp"%>
	</div>
</body>
</html>