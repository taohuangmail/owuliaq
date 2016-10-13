<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	.col-md-4{
		padding-right: 5px;
	}
</style>
<div class="modal-dialog modal-wide">
	<div class="modal-content well node">
		<div class="modal-header mbt20">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">更新APP版本</h4>
		</div>
		<hr class="line mbt20" />
		<form id="form">
			<input type="hidden" value="${appVersion.id}" name="id" />
			<div class="row">
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label">版本号：</label>
					<div class="col-md-8">
						<input class="form-control" type="text" data-bv-notempty="true" name="appVersionCode" id="appVersionCode" value="${appVersion.appVersionCode}"  />
					</div>
				</div>
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label">是否强制更新：</label>
					<div class="col-md-8">
						<select class="form-control" name="forced" id="forced">
							<option value=""></option>
							<option value="1" <c:if test="${appVersion.forced != null && appVersion.forced}">SELECTED</c:if>>是</option>
							<option value="0" <c:if test="${appVersion.forced != null && !appVersion.forced}">SELECTED</c:if>>否</option>										
						</select>
					</div>
				</div>	
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label">下载地址：</label>
					<div class="col-md-8">
						<input class="form-control" type="text" name="url" value="${appVersion.url}" />
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
				appVersionCode: {
	                validators: {
	                    notEmpty: {
	                        message: '版本号不能为空'
	                    },
	                    integer:{
		                	message: '版本号只能是整数'
		                }
	                }
	           },
	           forced: {
	                validators: {
	                    notEmpty: {
	                        message: '是否强制更新为必选'
	                    }
	                }
	            }
	        },
			submitHandler: function(validator, form, submitButton) {
				if(confirm('您确定要保存APP版本号吗？')){
					$(form).ajaxSubmit({
						type : "post",
						url : "saveAppVersion.json",
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
							alert("新增/修改APP版本号失败");
						}
					});
				}
			}
		});
	}); 
</script>

