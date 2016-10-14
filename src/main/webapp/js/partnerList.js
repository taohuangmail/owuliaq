var TableManaged = function() {

	return {
		init : function() {
			if (!jQuery().DataTable) {
				return;
			}
			var colDef = [];
		    var modelsTable = $('#models-data').DataTable({
				    	 "ajax": {
				             "url": "infoAjaxList.json",
				             "type": "POST",
				             "data": function (d) {
				            	 d.nickName = $("#schNickName").val();
				            	 d.email = $("#schEmail").val();
				            	 d.mobile = $("#schMobile").val();
				            	 d.jobName = $("#schJobName").val();
		    				  }
				         },
				        "searching": false,
		    			"processing": true,
		    	        "serverSide": true,
		    	        //"scrollX": true,
		    	        "columns": [  {
							"mData" : "nickName",
							"bSortable" : false
						}, {
							"mData" : "email",
							"bSortable" : false
						}, {
							"mData" : "mobile",
							"bSortable" : false,
							
						}, {
							"mData" : "jobId",
							"bSortable" : false,
						},{
							"mData" : "jobName",
							"bSortable" : false,
						},{
							"mData" : "about",
							"bSortable" : false,
						},{
							"mData" : "createTime",
							"bSortable" : false,
							"mRender" : function(data, type, full) {
								return data;
							}
						}, {
							"mData" : "id",
							"bSortable" : false,
							"mRender" : function(data, type, full) {
								
								return "<a href='javascript:void(0)' onclick='edit(\""+full.id+"\")'>编辑</a>&nbsp;<a href='javascript:void(0)' onclick='del(\""+full.id+"\")'>删除</a>";
							}
						}
			            ],
			            "language": {
			                 "url":base+"/js/jquery.dataTable.cn.js"
			             }
		    		}
				);

			return modelsTable;
		}

	};

}();

function add(){
	window.location.href = basePath + "/admin/auth/partner/addPartner";
}

function edit(id){
	window.location.href = basePath + "/admin/auth/partner/editPartner?id="+id;
}

function del(id){
	if(confirm("你确定要删除信息？")){
		$.ajax({
			type : "post",
			url : basePath + '/admin/auth/partner/delPartner.json?id='+id,
			dataType : "json",
			success : function(result) {
				alert(result.message);
				dataTable.ajax.reload();
			},
			error:function(data){
				alert("删除信息失败");
			}
		});
	}
	
}
