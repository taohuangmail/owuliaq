var TableManaged = function() {

	return {
		init : function() {
			if (!jQuery().DataTable) {
				return;
			}
			var colDef = [];
		    var modelsTable = $('#models-data').DataTable({
				    	 "ajax": {
				             "url": "/admin/auth/category/infoAjaxList.json",
				             "type": "POST",
				             "data": function (d) {
				            	 d.name = $("#schName").val();
				            	 d.seqNum = $("#schSeqNum").val();
		    				  }
				         },
				        "searching": false,
		    			"processing": true,
		    	        "serverSide": true,
		    	        //"scrollX": true,
		    	        "columns": [  {
							"mData" : "name",
							"bSortable" : false
						}, {
							"mData" : "imgUrl",
							"bSortable" : false,
							"mRender" : function(data, type, full) {
								return '<img src='+data+'  class="bj-img"/>';
							}
						}, {
							"mData" : "seqNum",
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
	window.location.href = basePath + "/admin/auth/category/add";
}

function edit(id){
	window.location.href = basePath + "/admin/auth/category/edit?id="+id;

}

function del(id){
	if(confirm("你确定要删除信息？")){
		$.ajax({
			type : "post",
			url : basePath + '/admin/auth/category/delCategory.json?id='+id,
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
