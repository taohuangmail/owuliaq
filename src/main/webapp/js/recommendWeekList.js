var TableManaged = function() {

	return {
		init : function() {
			if (!jQuery().DataTable) {
				return;
			}
			var colDef = [];
		    var modelsTable = $('#models-data').DataTable({
				    	 "ajax": {
				             "url": "/admin/auth/recommendWeek/infoAjaxList.json",
				             "type": "POST",
				             "data": function (d) {
				            	 d.name = $("#schName").val();
				            	 d.des = $("#schDes").val();
				            	 d.stauts = $("#schStauts").val();
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
							"mData" : "des",
							"bSortable" : false,
						},  {
							"mData" : "stauts",
							"bSortable" : false,
							"mRender" : function(data, type, full) {
								if(data == 0){
									return "已上架<br><a href='javascript:void(0)' onclick='apply(\""+full.id+"\")'>下架</a>";
								}else if(data == 1){
									return "下架<br><a href='javascript:void(0)' onclick='apply(\""+full.id+"\")'>上架</a>";
								}
							}
						},{
							"mData" : "seqNum",
							"bSortable" : false
						}, {
							"mData" : "id",
							"bSortable" : false,
							"mRender" : function(data, type, full) {
								return "<a href='javascript:void(0)' onclick='edit(\""+full.id+"\")'>编辑</a>&nbsp;<a href='javascript:void(0)' onclick='del(\""+full.id+"\")'>删除</a>&nbsp;<a href='javascript:void(0)' onclick='showStory(\""+full.id+"\")'>查看故事</a>";
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
	//alert($("#schName").val());
	window.location.href = basePath + "/admin/auth/recommendWeek/add";
}

function edit(id){
	window.location.href = basePath + "/admin/auth/recommendWeek/edit?id="+id;

}

function del(id){
	if(confirm("你确定要删除信息？")){
		$.ajax({
			type : "post",
			url : basePath + '/admin/auth/recommendWeek/delRecommendWeek.json?id='+id,
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

function apply(id){
	$.ajax({
		type : "post",
		url : basePath + '/admin/auth/recommendWeek/applyRecommendWeek.json?id='+id,
		dataType : "json",
		success : function(result) {
			alert(result.message);
			dataTable.ajax.reload();
		},
		error:function(data){
			alert("变更信息失败");
		}
	});
}


function showStory(id){
	window.location.href = basePath + "/admin/auth/recommendWeek/showWeekStory.htm?id="+id;
}
