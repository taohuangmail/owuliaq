<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/jscss.jsp"%>
<title>故事分类</title>
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
	    height: 50px;
    	width: 100px;
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
							<label class="control-label col-md-4 textRight">手机号码</label>
							<div class="col-md-8">
								<input type="text" class="form-control" name="schUsername" id="schUsername" />
							</div>
						</div>
						<div class="col-md-4 form-group">
							<label class="control-label col-md-4 textRight">ID</label>
							<div class="col-md-8">
								<input type="text" class="form-control" name="schId" id="schId" />
							</div>
						</div>
						<div  class="col-md-4 form-group">
					 		<input type="button" value="查询" class="btn btn-primary" onclick="reloadTable();">
					 		<input type="button" value="新增" class="btn btn-primary" onclick="add();")>
					 	</div> 
					 </div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">会员管理</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<table id="models-data" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th width="15%">ID</th>
										<th width="15%">手机号</th>
										<th width="20%">城市</th>
										<th width="15%">操作</th>
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
		<script type="text/javascript" src="${base}/js/memberList.js"></script>
		<script>
			jQuery(document).ready(function() {
				dataTable = TableManaged.init();
				$("#member").addClass("active");
			});
		</script>
	</div>
</body>
</html>