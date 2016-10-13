var TableManaged = function() {

	return {
		init : function() {
			if (!jQuery().DataTable) {
				return;
			}
			var colDef = [];
		    var modelsTable = $('#models-data').DataTable({
				    	 "ajax": {
				             "url": "/admin/auth/member/infoAjaxList.json",
				             "type": "POST",
				             "data": function (d) {
				            	 d.username = $("#schUsername").val();
				            	 d.id = $("#schId").val();
		    				  }
				         },
				        "searching": false,
		    			"processing": true,
		    	        "serverSide": true,
		    	        //"scrollX": true,
		    	        "columns": [  {
							"mData" : "id",
							"bSortable" : false
						}, {
							"mData" : "username",
							"bSortable" : false,
						}, {
							"mData" : "city",
							"bSortable" : false
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
	window.location.href = basePath + "/admin/auth/member/add";
}

function edit(id){
	window.location.href = basePath + "/admin/auth/member/edit?id="+id;

}

function del(id){
	if(confirm("你确定要删除信息？")){
		$.ajax({
			type : "post",
			url : basePath + '/admin/auth/member/delMember.json?id='+id,
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
