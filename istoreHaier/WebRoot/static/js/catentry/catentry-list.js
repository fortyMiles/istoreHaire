$(document).ready(function() {
	jQuery("#attrs-list").jqGrid({
		url: '/istore/servlet/b2b/cantentry/dingyi/json.do',
		datatype: "json",
		colNames: ['属性ID', '属性名称', '属性描述',  '操作'],
		colModel: [{
			name: 'attr_id',
			index: 'attr_id',
			width: 20,
			align: "center",
			sortable: true
		},
		{
			name: 'name',
			index: 'name',
			width: 20,
			align: "center"
		},
		{
			name: 'description',
			index: 'description',
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
		pager: '#attrs_pager',
		sortname: 'attr_id',
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
	
	
	jQuery("#attrs-list").jqGrid('navButtonAdd', "#attrs_pager", {
		caption: "",
		position: "first",
		title: "属性删除",
		buttonicon: "ui-icon-trash",
		onClickButton: function() {
			var attr_id = jQuery("#attrs-list").jqGrid("getGridParam","selarrrow");
			if(attr_id == null || attr_id == ""){
				alert("请选择一个属性!");
			}else{
				if(confirm("确定删除吗？")){
					var url = "/istore/servlet/b2b/cantentry/delete.do";
					$.post(url, {'attr_id':attr_id.toString()}, function(result){
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
		

	//编辑属性
	jQuery("#attrs-list").jqGrid('navButtonAdd', "#attrs_pager", {
		caption: "",
		position: "first",
		title: "属性编辑",
		buttonicon: "ui-icon-pencil",
		onClickButton: function() {
			var attr_id = jQuery("#attrs-list").jqGrid("getGridParam","selarrrow");
			if(attr_id == null || attr_id == ""){
				alert("请选择一个属性!");
			}else{
				var url = "/istore/servlet/b2b/cantentry/editlist.do";
				$.post(url, {'attr_id':attr_id.toString()}, function(result){
					$("#edit-attr-dialog").html(result).dialog({
						modal: true,
						resizable:false,
						width: 400,
						height: 200,
						title:"属性编辑",
						close: function(event, ui){
							$("#edit-attr-dialog").dialog("close");
						},
						buttons: {
							"修改": function(){
								var name = $("#edit_xchannel_name").val();
								var description = $("#edit_seq").val();
								
								if(name == ""){
									alert("请输属性名称！");
									$("#edit_xchannel_name").focus();
								}
								else if(description == ""){
									alert("请输入属性描述！");
									$("#edit_seq").focus();
								}
							
								else{
									var url = "/istore/servlet/b2b/cantentry/edit.do";
									$.post(url, {'attr_id':attr_id.toString(), 'name':name, 'description':description}, function(result){
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
		title: "填加属性",
		buttonicon: "ui-icon-plusthick",
		onClickButton: function() {
			$("#attr-add-dialog").dialog({
				modal: true,
				resizable:false,
				width: 400,
				height: 200,
				title:"填加属性",
				close: function(event, ui) {
					$("#attr-add-dialog").dialog("close");
				},
				buttons: {
					"提交": function() {
						var name = $("#attr_name").val();
						var description = $("#attr_description").val();
						
						if(name == ""){
							alert("请输入属性名称！");
							$("#attr_name").focus();
						}
						else if(description == ""){
							alert("请输入属性内容！");
							$("#attr_description").focus();	
						}
						else{
							var url = "/istore/servlet/b2b/cantentry/add.do";
							$.post(url, {'name':name, 'description':description,  'type':1}, function(result){
								if(result == "success"){
									alert("添加成功！");
									$("#attr-add-dialog").dialog("close");
									$("#attr_name").val("");
									$("#attr_description").val("");
									jQuery("#attrs-list").trigger("reloadGrid");
								}
								else{
									alert("添加失败！");
									$("#attr-add-dialog").dialog("close");
									$("#attr_name").val("");
									$("#attr_description").val("");
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
