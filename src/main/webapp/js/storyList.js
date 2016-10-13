var TableManaged = function() {

	return {
		init : function() {
			if (!jQuery().DataTable) {
				return;
			}
			var colDef = [];
		    var modelsTable = $('#models-data').DataTable({
				    	 "ajax": {
				             "url": "/admin/auth/story/infoAjaxList.json",
				             "type": "POST",
				             "data": function (d) {
				            	 d.name = $("#schName").val();
				            	 d.seqNum = $("#schSeqNum").val();
				            	 d.categoryId = $("#schCategoryId").val();
				            	 d.status = $("#schStatus").val();
		    				  }
				         },
				        "searching": false,
		    			"processing": true,
		    	        "serverSide": true,
		    	        //"scrollX": true,
		    	        "columns": [{
							"mData" : "id",
							"bSortable" : false,
						},{
							"mData" : "name",
							"bSortable" : false
						},{
							"mData" : "categoryName",
							"bSortable" : false
						},{
							"mData" : "click",
							"bSortable" : false
						},{
							"mData" : "status",
							"bSortable" : false,
							"mRender" : function(data, type, full) {
								if(data == 0){
									return "已上架<br><a href='javascript:void(0)' onclick='apply(\""+full.id+"\")'>下架</a>";
									
								}else if(data == 1){
									return "下架<br><a href='javascript:void(0)' onclick='apply(\""+full.id+"\")'>上架</a>";
								}
							}
						}, {
							"mData" : "seqNum",
							"bSortable" : false
						}, {
							"mData" : "url",
							"bSortable" : false
						},{
							"mData" : "tagRunner",
							"bSortable" : false,
							"mRender" : function(data, type, full) {
								return '<img src='+data+'  class="bj-img"/>';
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
	window.location.href = basePath + "/admin/auth/story/add";
}

function edit(id){
	window.location.href = basePath + "/admin/auth/story/edit?id="+id;

}

//推荐方法
function recommend(id){
	$.ajax({
		type : "post",
		url : basePath +'/admin/auth/story/recommend.json',
		data :{id:id},
		dataType:"json",
		success : function(result){
			alert(result.message);
			dataTable.ajax.reload();
		},
		error : function(data){
			alert("推荐失败");
		}
	});
}

//上架
function apply(id){
	$.ajax({
		type : "post",
		url : basePath +'/admin/auth/story/apply.json',
		data :{id:id},
		dataType:"json",
		success : function(result){
			alert(result.message);
			dataTable.ajax.reload();
		},
		error : function(data){
			alert("上架失败");
		}
	});
}
function del(id){
	if(confirm("你确定要删除信息？")){
		$.ajax({
			type : "post",
			url : basePath + '/admin/auth/story/delStory.json?id='+id,
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
