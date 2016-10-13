<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/jscss.jsp"%>
<title>字典管理</title>
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
							<label class="control-label col-md-4 textRight">分组ID</label>
							<div class="col-md-8">
								<input type="text" class="form-control" name="schGroupId" id="schGroupId" />
							</div>
						</div>
						<div class="col-md-4 form-group">
							<label class="control-label col-md-4 textRight">分组名称</label>
							<div class="col-md-8">
								<input type="text" class="form-control" name="schGroupName" id="schGroupName" />
							</div>
						</div>
						<div class="col-md-4 form-group">
							<label class="control-label col-md-12">
								<div  class="col-md-4">
							 		<input type="button" value="查询" class="btn btn-primary" onclick="reloadTable();">
							 	</div>
								<div  class="col-md-4">
									<input type="button" value="新增" class="btn btn-primary" onclick="add();")>
								</div>
								
							</label>
						</div>
				</div>
			</div>

			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">用户列表</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<table id="models-data" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th width="15%">分组ID</th>
										<th width="15%">分组名称</th>
										<th width="15%">字典键</th>
										<th width="15%">字典值</th>
										<th width="15%">说明</th>
										<th width="15%">创建时间</th>
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
		<script type="text/javascript" src="${base}/js/dictionaryList.js"></script>
		<script>
			jQuery(document).ready(function() {
				dataTable = TableManaged.init();
			});
		</script>
	</div>
</body>
</html>