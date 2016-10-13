<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/jscss.jsp"%>
<title>APP版本管理</title>
<style type="text/css">
	table.dataTable thead .sorting_asc:after {
	    content: "";
	}
</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<%@include file="../common/top.jsp"%>
		<%@include file="../common/menu.jsp"%>
		<div class="content-wrapper">
			<section class="content">
			<form method="post" action="">
				<div class="form-body form-horizontal this-dgd">
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-4 form-group">
								<label class="control-label col-md-4 textRight">版本号</label>
								<div class="col-md-8">
									<input type="text" class="form-control" name="appVersionCode" id="schAppVersionCode" />
								</div>
							</div>
							<div class="col-md-4 form-group">
								<label class="control-label col-md-4 textRight">是否强制更新</label>
								<div class="col-md-8">
									<select class="form-control" name="forced" id="schForced">
										<option value=""></option>
										<option value="1">是</option>
										<option value="0">否</option>										
									</select>
								</div>
							</div>
							<div class="col-md-4 form-group">
								<label class="control-label col-md-4 textRight">
									<input type="button" value="新增" class="btn btn-primary pull-right" onclick="add();")>
								</label>
								<label class="control-label col-md-4 textRight">
								 	<input type="button" value="查询" class="btn btn-primary pull-right" onclick="reloadTable();">
								</label>
								
							</div>
						</div>
					</div>

				</div>
			</form>

			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">APP版本列表</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<table id="models-data" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th width="20%">版本号</th>
										<th width="20%">是否强制更新</th>
										<th width="35%">下载地址</th>
										<th width="15%">更新时间</th>
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

		<script type="text/javascript" src="${base}/js/appVersionList.js"></script>
		<script>
			jQuery(document).ready(function() {
				dataTable = TableManaged.init();
			});
		</script>
	</div>
</body>
</html>