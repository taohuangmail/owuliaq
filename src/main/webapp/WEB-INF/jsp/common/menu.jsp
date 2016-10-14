<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<aside class="main-sidebar">
  <section class="sidebar" id="menuList">
    <ul class="sidebar-menu">
		<li>
			<a href="javascript:;"> 
				<i class="fa fa-cog"></i> <span>管理控制台</span>
          		<i class="fa fa-angle-left pull-right"></i>
			</a>
			<ul class="treeview-menu">
				<li>
					<a href="${base}/admin/auth/user/index.htm"><i class="fa fa-circle-o"></i> 用户管理 </a>
				</li>
			</ul>
		</li>
		<li id="category">
			<a href="${base}/admin/auth/dictionary/dictionaryList.htm"> 
				<i class="fa fa-list-ul"></i> <span>字典管理</span>
			</a>
		</li>
		<li id="partner">
			<a href="${base}/admin/auth/partner/partnerList.htm"> 
				<i class="fa fa-list-ul"></i> <span>伙伴管理</span>
			</a>
		</li>
    </ul>
  </section>
</aside>

<script type="text/javascript">
    $(function() {
    	var pathname = window.location.pathname;
    	$("#menuList li a").each(function(){
    		var hrefStr = $(this).attr("href");
    		if(hrefStr == pathname){
    			$(this).parent().addClass("active");
    			$(this).parent().parent().parent().addClass("active");
    		}
    	});
    }); 
</script>
