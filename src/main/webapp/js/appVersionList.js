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
				            	 d.appVersionCode = $("#schAppVersionCode").val();
				            	 d.forced = $("#schForced").val();
		    				  }
				         },
				        "searching": false,
		    			"processing": true,
		    	        "serverSide": true,
		    	        //"scrollX": true,
		    	        "columns": [  {
							"mData" : "appVersionCode",
							"bSortable" : false
						}, {
							"mData" : "forced",
							"bSortable" : false,
							"mRender" : function(data, type, full) {
								if(data == "1"){
									return "是";
								}else if(data == "0"){
									return "否";
								}
							}
						}, {
							"mData" : "url",
							"bSortable" : false
						}, {
							"mData" : "updateTime",
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
	$.get(basePath + '/admin/auth/appVersion/addAppVersion.json', function(retData) {
		$('#editDialog').html(retData);
	});
	$('#editDialog').modal({
		show : true
	});
}

function edit(id){
	$.get(basePath + '/admin/auth/appVersion/editAppVersion.json?id=' + id, function(retData) {
		$('#editDialog').html(retData);
	});
	$('#editDialog').modal({
		show : true
	});
}

function del(id){
	if(confirm("你确定要删除信息？")){
		$.ajax({
			type : "post",
			url : basePath + '/admin/auth/appVersion/delAppVersion.json?id='+id,
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
