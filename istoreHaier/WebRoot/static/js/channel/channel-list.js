$(document).ready(function() {
	jQuery("#channel").jqGrid({
		url: '/istore/servlet/b2b/channel/json.do',
		datatype: "json",
		colNames: ['频道编号', '频道名称', '优先级','类型', '新闻/样板管理'],
		colModel: [{
			name: 'XCHANNEL_ID',
			index: 'XCHANNEL_ID',
			width: 5,
			align: "center",
			sortable: true
		},
		{
			name: 'XCHANNEL_NAME',
			index: 'XCHANNEL_NAME',
			width: 20,
			align: "center"
		},
		{
			name: 'SEQ',
			index: 'SEQ',
			width: 5,
			align: "center"
		},
		{
			name: 'TYPE',
			index: 'TYPE',
			width: 20,
			align: "center"
		},
		{
			name: 'MANAGE',
			index: 'MANAGE',
			width: 30,
			align: "center",
			sortable: false
		}],
		rowNum: 10,
		rowList: [10,12,15],
		pager: '#channel_pager',
		sortname: 'XCHANNEL_ID',
		viewrecords: true,
		sortorder: "DESC",
		autowidth: true,
		height: 'auto',
		multiselect: true,
		rownumbers: true,
		caption: false,
	});
	
	jQuery("#channel").jqGrid('navGrid', '#channel_pager', {
		add: false,
		del: false,
		edit: false,
		search: false
	});
	
	//高级搜索
	$("#channel").jqGrid('navButtonAdd', "#channel_pager", {
		caption: "",
		position: "first",
		title: "搜索",
		buttonicon: "ui-icon-search",
		onClickButton: function() {
			var _scd = $("#search-channel-dialog");
			_scd.dialog({
				modal: true,
				resizable:false,
				width: 400,
				height: 'auto',
				title:"搜索",
				close: function(event, ui) {
					_scd.dialog("destroy");
				},
				buttons: {
					"搜索": function() {
						var xchannel_name = $("#search_xchannel_name").val();
						var tag = "";
						/*var tag = $("#search_tag").val();*/
						var type = $("input[name='search_type']:checked").val();
						
						var url = "/istore/servlet/b2b/channel/search.do";
						jQuery("#channel").jqGrid('setGridParam',{
							url: url,
							postData:{
								xchannel_name: xchannel_name,
								type: type
							},
							page: 1
						}).trigger("reloadGrid");
						_scd.dialog("destroy");
					}
				}
			});
		}
	});
	
	//删除频道
	jQuery("#channel").jqGrid('navButtonAdd', "#channel_pager", {
		caption: "",
		position: "first",
		title: "频道删除",
		buttonicon: "ui-icon-trash",
		onClickButton: function() {
			var row = jQuery("#channel").jqGrid("getGridParam","selarrrow");
			if(row == null || row == ""){
				alert("请选择一个频道!");
			}else if(row == 1){
				alert("该频道不能删除！");
			}else{
				if(confirm("确定删除吗？")){
					var xchannel_id= $("#channel").getCell(row, "XCHANNEL_ID");
					var url = "/istore/servlet/b2b/channel/delete.do";
					$.post(url, {'xchannel_id':xchannel_id.toString()}, function(result){
						if(result == "success"){
							alert("删除成功！");
							jQuery("#channel").trigger("reloadGrid");
						}
						else{
							alert("修改失败！");
							jQuery("#channel").trigger("reloadGrid");
						}
					});
				}
			}
		}
	});
	
	//频道编辑
	jQuery("#channel").jqGrid('navButtonAdd', "#channel_pager", {
		caption: "",
		position: "first",
		title: "频道编辑",
		buttonicon: "ui-icon-pencil",
		onClickButton: function() {
			var row = jQuery("#channel").jqGrid("getGridParam","selarrrow");
			if(row == null || row == ""){
				alert("请选择一个频道!");
			}else{
				var xchannel_id= $("#channel").getCell(row, "XCHANNEL_ID");
				var url = "/istore/servlet/b2b/channel/editlist.do";
				$.post(url, {'xchannel_id':xchannel_id.toString()}, function(result){
					var _ecd = $("#edit-channel-dialog");
					_ecd.html(result).dialog({
						modal: true,
						resizable:false,
						width: 400,
						height: 'auto',
						title:"频道编辑",
						close: function(event, ui){
							_ecd.dialog("destroy");
						},
						buttons: {
							"修改": function(){
								var xchannel_name = $("#edit_xchannel_name").val();
								var seq = $("#edit_seq").val();
								var type = $("input[name='edit_type']:checked").val();
								if(xchannel_name == ""){
									alert("请输入频道名称！");
									$("#edit_xchannel_name").focus();
								}
								else if(seq == ""){
									alert("请输入优先级！");
									$("#edit_seq").focus();
								}
								else{
									var url = "/istore/servlet/b2b/channel/edit.do";
									$.post(url, {'xchannel_id':xchannel_id.toString(), 'xchannel_name':xchannel_name, 'seq':seq, 'type':type}, function(result){
										if(result == "success"){
											alert("修改成功！");
											_ecd.dialog("destroy");
											jQuery("#channel").trigger("reloadGrid");
										}
										else{
											alert("修改失败！");
											_ecd.dialog("destroy");
											jQuery("#channel").trigger("reloadGrid");
										}
									});					
								}
							}
						}
					});
				});
			}
		}
	});
	
	//新建频道
	jQuery("#channel").jqGrid('navButtonAdd', "#channel_pager", {
		caption: "",
		position: "first",
		title: "新建频道",
		buttonicon: "ui-icon-plusthick",
		onClickButton: function() {
			var _acd = $("#add-channel-dialog");
			_acd.dialog({
				modal: true,
				resizable:false,
				width: 400,
				height: 'auto',
				title:"添加新频道",
				close: function(event, ui) {
					_acd.dialog("destroy");
				},
				buttons: {
					"提交": function() {
						var xchannel_name = $("#add_xchannel_name").val();
						var seq = $("#add_seq").val();
						var type = $("input[name='add_type']:checked").val();
						if(xchannel_name == ""){
							alert("请输入频道名称！");
							$("#add_xchannel_name").focus();
						}
						else if(seq == ""){
							alert("请输入优先级！");
							$("#add_seq").focus();
						}
						else{
							var url = "/istore/servlet/b2b/channel/add.do";
							$.post(url, {'xchannel_name':xchannel_name, 'seq':seq, 'type':type}, function(result){
								if(result == "success"){
									alert("添加成功！");
									_acd.dialog("destroy");
									jQuery("#channel").trigger("reloadGrid");
								}
								else{
									alert("添加失败！");
									_acd.dialog("destroy");
									jQuery("#channel").trigger("reloadGrid");
								}
							});					
						}
					}
				}
			});
		}
	});
});
