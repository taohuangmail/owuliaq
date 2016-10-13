<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.col-md-4{
		padding-right: 5px;
	}
	.modal-dialog{
		width: 400px;	
	}
	
</style>
<div class="modal-dialog modal-wide">
	<div class="modal-content well node">
		<div class="modal-header mbt20">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">修改密码</h4>
		</div>
		<form id="editPwdForm">
			<div class="row" style="margin-top: 10px;">
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label">原密码：</label>
					<div class="col-md-8">
						<input class="form-control"  type="password" value="" name="password" id="oldPassword"/>
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label">新密码：</label>
					<div class="col-md-8">
						<input class="form-control"  type="password" value="" name="newPassword" id="newPassword"/>
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label">重复密码：</label>
					<div class="col-md-8">
						<input class="form-control"  type="password" value=""  id="repeatPassword"/>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-success" id="saveForm">
					修改
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
	
		$("#editPwdForm").submit(function() {
				$(this).ajaxSubmit({
					type : "post",
					url : basePath + "/admin/auth/savePwd.json",
					dataType : "json",
					beforeSubmit : function(){
						var oldPassword = $("#oldPassword").val();
						if(oldPassword == null || oldPassword == ''){
							alert("请输入原密码");
							return false;
						}
						var newPassword = $("#newPassword").val();
						if(newPassword == null || newPassword == ''){
							alert("请输入新密码");
							return false;
						}
						var repeatPassword = $("#repeatPassword").val();
						if(repeatPassword == null || repeatPassword == ''){
							alert("重复密码不能为空");
							return false;
						}
						if(repeatPassword != newPassword){
							alert("和新密码不匹配，重新输入");
							return false;
						}
					},
					success : function(result) {
						alert(result.message);
						//返回提示信息
						if (result.status == 'true') {
							$('#editPwdDialog').modal('hide');
						}
					},
					error:function(data){
						$.dopAlert("修改密码失败");
					}
				});
				return false;
		});
	}); 
</script>


