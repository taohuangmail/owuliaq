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
				            	 d.groupId = $("#schGroupId").val();
				            	 d.groupName = $("#schGroupName").val();
		    				  }
				         },
				        "searching": false,
		    			"processing": true,
		    	        "serverSide": true,
		    	        //"scrollX": true,
		    	        "columns": [  {
							"mData" : "groupId",
							"bSortable" : false
						}, {
							"mData" : "groupName",
							"bSortable" : false
						}, {
							"mData" : "diKey",
							"bSortable" : false,
							
						}, {
							"mData" : "diValue",
							"bSortable" : false,
						},{
							"mData" : "diExplain",
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
	window.location.href = basePath + "/admin/auth/dictionary/addDictionary";
}

function edit(id){
	window.location.href = basePath + "/admin/auth/dictionary/editDictionary?id="+id;
}

function del(id){
	if(confirm("你确定要删除信息？")){
		$.ajax({
			type : "post",
			url : basePath + '/admin/auth/dictionary/delDictionary.json?id='+id,
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
