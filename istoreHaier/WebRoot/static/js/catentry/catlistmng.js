$(document).ready(function() {
	jQuery("#cats-list").jqGrid({
		url: '/istore/servlet/b2b/catmng/json.do',
		datatype: "json",
		colNames: ['商品ID', '商品名称', '商品款号', '操作'],
		colModel: [{
			name: 'catentryId',
			index: 'catentryId',
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
			name: 'partnumber',
			index: 'partnumber',
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
		pager: '#cats_pager',
		sortname: 'catentryId',
		viewrecords: true,
		sortorder: "DESC",
		autowidth: true,
		height: 'auto',
		multiselect: true,
		rownumbers: true,
		caption: false
		
	});
	
	jQuery("#cats-list").jqGrid('navGrid', '#cats_pager', {
		add: false,
		del: false,
		edit: false,
		search: false
	});
	
	//高级搜索
	$("#cats-list").jqGrid('navButtonAdd', "#cats_pager", {
		caption: "",
		position: "first",
		title: "搜索",
		buttonicon: "ui-icon-search",
		onClickButton: function() {
			var _scd = $("#search-catentry-dialog");
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
						var catentry_key = $("#search_catentry_key").val();
						var tag = "";
						/*var tag = $("#search_tag").val();*/
						var type = $("input[name='search_type']:checked").val();
						
						var url = "/istore/servlet/b2b/catmng/search.do";
						jQuery("#cats-list").jqGrid('setGridParam',{
							url: url,
							postData:{
							catentry_key: catentry_key,
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
	
	
	
});