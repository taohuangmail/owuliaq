<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/jscss.jsp"%>
<title>平板门店配置</title>
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
							<label class="control-label col-md-4 textRight">平板名称</label>
							<div class="col-md-8">
								<input type="text" class="form-control" name="padName" id="schPadName" />
							</div>
						</div>
						<div class="col-md-4 form-group">
							<label class="control-label col-md-4 textRight">平板MAC地址</label>
							<div class="col-md-8">
								<input type="text" class="form-control" name="mac" id="schMac" />
							</div>
						</div>
						<div class="col-md-4 form-group">
							<label class="control-label col-md-4 textRight">门店编号</label>
							<div class="col-md-8">
								<input type="text" class="form-control" name="workshopCode" id="schWorkshopCode" />
							</div>
						</div>
						
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<div class="col-md-4 form-group">
							<label class="control-label col-md-4 textRight">门店名称</label>
							<div class="col-md-8">
								<input type="text" class="form-control" name="workshopName" id="schWorkshopName" />
							</div>
						</div>
						<div class="col-md-4 form-group">
							<label class="control-label col-md-12">
								<div  class="col-md-4">
									<input type="button" value="新增" class="btn btn-primary" onclick="add();")>
								</div>
								<div  class="col-md-4">
							 		<input type="button" value="查询" class="btn btn-primary" onclick="reloadTable();">
							 	</div>
							 	
							</label>
						</div>
						<div class="col-md-4 form-group">
							<div  class="col-md-6">
						 	<a href="${base}/平板电脑和门店配置.xls"><input type="button" value="模版下载" class="btn btn-primary"></a>
						 	</div>
						 	<div  class="col-md-6">
						 	<form action="${base}/admin/auth/padShop/importPadShop.json" method="post" accept-charset="utf-8" id="importForm">
								<input onchange="" class="btn btn-primary" type="file" title="批量导入" name="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel" />&nbsp;&nbsp;
							</form>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">平板门店列表</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<table id="models-data" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th width="15%">平板名称</th>
										<th width="15%">平板MAC地址</th>
										<th width="15%">接口参数</th>
										<th width="15%">门店编号</th>
										<th width="15%">门店名称</th>
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
		<script type="text/javascript" src="${base}/plugins/bootstrap-file-input/bootstrap.file-input.js"></script>
		<script type="text/javascript" src="${base}/js/padShopList.js"></script>
		<script>
			jQuery(document).ready(function() {
				$('input[type=file]').bootstrapFileInput();
				$('input[type=file]').change(function(){
					$("#importForm").submit();
				});
				
				dataTable = TableManaged.init();
				
				$("#importForm").submit(function() {
					$(this).ajaxSubmit({
						type : "post",
						url : "${base}/admin/auth/padShop/importPadShop.json",
						dataType : "json",
						success : function(result) {
							alert(result.message);
							//返回提示信息
							if (result.status == 'true') {
								window.location.reload();
							}
						},
						error:function(data){
							alert("导入excel失败");
							$('input[type=file]').val("");
						}
					});
					return false;
				});
			});
		</script>
	</div>
</body>
</html>