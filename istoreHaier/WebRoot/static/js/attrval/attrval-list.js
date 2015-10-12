$(document).ready(function() {
	jQuery("#attrs-list").jqGrid({
		url: '/istore/servlet/b2b/attrval/json.do',
		datatype: "json",
		colNames: ['属性值ID', '属性值内容', '属性序号'],
		colModel: [{
			name: 'attrval_id',
			index: 'attrval_id',
			width: 20,
			align: "center",
			sortable: true
		},
		{
			name: 'value',
			index: 'value',
			width: 20,
			align: "center"
		},
		{
			name: 'SEQUENCE',
			index: 'SEQUENCE',
			width: 20,
			align: "center"
		}],
		rowNum: 10,
		rowList: [10,12,15],
		pager: '#attrs_pager',
		sortname: 'attrval_id',
		viewrecords: true,
		sortorder: "DESC",
		autowidth: true,
		height: 'auto',
		multiselect: true,
		rownumbers: true,
		caption: false
	});
	
	jQuery("#attrs-list").jqGrid('navGrid', '#attrs_pager', {
		add: false,
		del: false,
		edit: false,
		search: false
	});
	
	/**
	 * 删除属性值
	 */
	jQuery("#attrs-list").jqGrid('navButtonAdd', "#attrs_pager", {
		caption: "",
		position: "first",
		title: "属性值删除",
		buttonicon: "ui-icon-trash",
		onClickButton: function() {
			var attrval_id = jQuery("#attrs-list").jqGrid("getGridParam","selarrrow");
			if(attrval_id == null || attrval_id == ""){
				alert("请选择一个属性值!");
			}else{
				if(confirm("确定删除吗？")){
					var url = "/istore/servlet/b2b/attrval/delete.do";
					$.post(url, {'attrval_id':attrval_id.toString()}, function(result){
						if(result == "success"){
							alert("删除成功！");
							jQuery("#attrs-list").trigger("reloadGrid");
						}
						else{
							alert("删除失败！");
							jQuery("#attrs-list").trigger("reloadGrid");
						}
					});
				}
			}
		}
	});

	/**
	 * 修改属性值
	 */
	jQuery("#attrs-list").jqGrid('navButtonAdd', "#attrs_pager", {
		caption: "",
		position: "first",
		title: "属性编辑",
		buttonicon: "ui-icon-pencil",
		onClickButton: function() {
			var attrval_id = jQuery("#attrs-list").jqGrid("getGridParam","selarrrow");
			if(attrval_id == null || attrval_id == ""){
				alert("请选择一个属性值!");
			}else{
				var url = "/istore/servlet/b2b/attrval/editlist.do";
				$.post(url, {'attrval_id':attrval_id.toString()}, function(result){
					$("#edit-attr-dialog").html(result).dialog({
						modal: true,
						resizable:false,
						width: 400,
						height: 200,
						title:"属性值编辑",
						close: function(event, ui){
							$("#edit-attr-dialog").dialog("close");
						},
						buttons: {
							"修改": function(){
								var value = $("#editval_name").val();
								var sq = $("#editval_seq").val();
								
								if(value == ""){
									alert("请输属性值！");
									$("#editval_name").focus();
								}
								else if(sq == ""){
									alert("请输入属性顺序！");
									$("#editval_seq").focus();
								}
							
								else{
									var url = "/istore/servlet/b2b/attrval/edit.do";
									$.post(url, {'attrval_id':attrval_id.toString(), 'value':value, 'sq':sq}, function(result){
										if(result == "success"){
											alert("修改成功！");
											$("#edit-attr-dialog").dialog("close");
											jQuery("#attrs-list").trigger("reloadGrid");											
										}
										else{
											alert("修改失败！");
											$("#edit-attr-dialog").dialog("close");									
											jQuery("#attrs-list").trigger("reloadGrid");
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
	
	
	
	//填加属性
	jQuery("#attrs-list").jqGrid('navButtonAdd', "#attrs_pager", {
		caption: "",
		position: "first",
		title: "填加属性值",
		buttonicon: "ui-icon-plusthick",
		onClickButton: function() {
			$("#attrval-add-dialog").dialog({
				modal: true,
				resizable:false,
				width: 400,
				height: 240,
				title:"填加属性值",
				close: function(event, ui) {
					$("#attrval-add-dialog").dialog("close");
				},
				buttons: {
					"提交": function() {
						var value = $("#attr_val").val();
						var sq = $("#attrval_sq").val();
						
						if(value == ""){
							alert("请输入属性值！");
							$("#attr_val").focus();
						}
						else if(sq == ""){
							alert("请输入属性值顺序！");
							$("#attrval_sq").focus();	
						}
						else{
							var url = "/istore/servlet/b2b/attrval/add.do";
							$.post(url, {'value':value, 'sq':sq}, function(result){
								if(result == "success"){
									alert("添加成功！");
									
									$("#attrval-add-dialog").dialog("close");
									$("#attr_val").val("");
									$("#attrval_sq").val("");
									jQuery("#attrs-list").trigger("reloadGrid");
								}
								else{
									alert("添加失败！");
									$("#attrval-add-dialog").dialog("close");
									$("#attr_val").val("");
									$("#attrval_sq").val("");
									jQuery("#attrs-list").trigger("reloadGrid");
								}
							});					
						}
					}
				}
			});
		}
	});
		
	
	
});
