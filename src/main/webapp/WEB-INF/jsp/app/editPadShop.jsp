<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.col-md-4{
		padding-right: 5px;
	}
</style>
<div class="modal-dialog modal-wide">
	<div class="modal-content well node">
		<div class="modal-header mbt20">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">更新平板门店配置</h4>
		</div>
		<hr class="line mbt20" />
		<form id="form">
			<input type="hidden" value="${padShop.id}" name="id" />
			<div class="row">
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label">平板名称：</label>
					<div class="col-md-8">
						<input class="form-control" type="text" name="padName" id="padName" value="${padShop.padName}"  />
					</div>
				</div>
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label">平板MAC地址：</label>
					<div class="col-md-8">
						<input class="form-control" type="text" name="mac" id="mac" value="${padShop.mac}" />
					</div>
				</div>	
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label">接口参数：</label>
					<div class="col-md-8">
						<input class="form-control" type="text" name="interfaceParam" id="interfaceParam" value="${padShop.interfaceParam}" />
					</div>
				</div>	
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label">门店编号：</label>
					<div class="col-md-8">
						<input class="form-control" type="text" name="workshopCode" id="workshopCode" value="${padShop.workshopCode}" />
					</div>
				</div>	
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label">门店名称：</label>
					<div class="col-md-8">
						<input class="form-control" type="text" name="workshopName" id="workshopName" value="${padShop.workshopName}" />
					</div>
				</div>	
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-success" id="save">
					保存
				</button>
				<button type="button" class="btn default" data-dismiss="modal">
					关闭
				</button>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		$('#form').bootstrapValidator({
			fields: {
				padName: {
	                validators: {
	                    notEmpty: {
	                        message: '请输入平板名称'
	                    }
	                }
	           },
	           mac: {
	                validators: {
	                    notEmpty: {
	                        message: '请输入平板mac地址'
	                    }
	                }
	            },
	            interfaceParam: {
	                validators: {
	                    notEmpty: {
	                        message: '请输入接口参数'
	                    }
	                }
	            },
	           workshopCode: {
	                validators: {
	                    notEmpty: {
	                        message: '请输入门店编码'
	                    }
	                }
	            },
	           workshopName: {
	                validators: {
	                    notEmpty: {
	                        message: '请输入门店名称'
	                    }
	                }
	            }
	        },
			submitHandler: function(validator, form, submitButton) {
				if(confirm('您确定要保存平板门店配置吗？')){
					$(form).ajaxSubmit({
						type : "post",
						url : "savePadShop.json",
						dataType : "json",
						success : function(result) {
							alert(result.message);
							//返回提示信息
							if (result.status == 'true') {
								$('#editDialog').modal('hide');
								dataTable.ajax.reload();
							}
						},
						error:function(data){
							alert("新增/修改平板门店配置失败");
						}
					});
					return false;
				}
			}
		});
		
	}); 
</script>

