<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<input type="hidden" value="${base}" id="basePath" />
<header class="main-header">
  <!-- Logo -->
  <a href="${base}/admin/auth/index.htm" class="logo">
    <!-- mini logo for sidebar mini 50x50 pixels -->
    <span class="logo-mini"><b>管理</b></span>
    <!-- logo for regular state and mobile devices -->
    <span class="logo-lg"><b>OWULIA</b></span>
  </a>
  <!-- Header Navbar: style can be found in header.less -->
  <nav class="navbar navbar-static-top" role="navigation">
    <!-- Sidebar toggle button-->
    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </a>
    <div class="navbar-custom-menu">
      <ul class="nav navbar-nav">
        <li class="dropdown user user-menu">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <span class="hidden-xs">管理员</span>
          </a>
          <ul class="dropdown-menu">
            <!-- Menu Footer-->
            <li class="user-footer">
              <div class="pull-left" id="editPwdli">
                <a href="javascript:void(0);" class="btn btn-default btn-flat">修改密码</a>
              </div>
              <div class="pull-right">
                <a href="${base}/admin/auth/logout.htm" class="btn btn-default btn-flat">退出</a>
              </div>
            </li>
          </ul>
        </li>
        <!-- Control Sidebar Toggle Button
        <li>
          <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
        </li>
         -->
      </ul>
    </div>
  </nav>
</header>

<div class="modal fade" id="editPwdDialog" tabindex="-1" role="basic" aria-hidden="true">
	<img src="${base}/assets/img/ajax-modal-loading.gif" alt="" class="loading">
</div>
<script type="text/javascript">
	var base='${base}';
	$(function(){
		$("#editPwdli").click(function(){
			$.get(basePath + '/admin/auth/editPwd.htm', function(retData) {
					$('#editPwdDialog').html(retData);
				});
				$('#editPwdDialog').modal({
					show : true
				});
		});
	});	
	
</script>