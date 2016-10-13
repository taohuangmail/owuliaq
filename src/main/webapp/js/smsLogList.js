var TableManaged = function() {

	return {
		init : function() {
			if (!jQuery().DataTable) {
				return;
			}
			var colDef = [];
		    var modelsTable = $('#models-data').DataTable({
				    	 "ajax": {
				             "url": "/admin/auth/smsLog/infoAjaxList.json",
				             "type": "POST",
				             "data": function (d) {
				            	 d.mobile = $("#schMobile").val();
				            	 d.tplValue = $("#schTplValue").val();
				            	 d.address = $("#schAddress").val();
		    				  }
				         },
				        "searching": false,
		    			"processing": true,
		    	        "serverSide": true,
		    	        //"scrollX": true,
		    	        "columns": [  {
							"mData" : "mobile",
							"bSortable" : false
						}, {
							"mData" : "tplId",
							"bSortable" : false,
						}, {
							"mData" : "tplValue",
							"bSortable" : false
						}, {
							"mData" : "errorCode",
							"bSortable" : false
						}, {
							"mData" : "address",
							"bSortable" : false
						}, {
							"mData" : "reason",
							"bSortable" : false,
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
