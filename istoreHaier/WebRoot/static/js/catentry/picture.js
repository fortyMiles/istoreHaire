$(document).ready(function() {
	jQuery("#cats-list").jqGrid({
		url: '/istore/servlet/b2b/catmng/picturejson.do',
		datatype: "json",
		colNames: ['商品图片','商品ID',  '商品序号', '操作'],
		colModel: [{
			name: 'picture',
			index: 'picture',
			width: 20,
			align: "center"
		},
		{
			name: 'catentryId',
			index: 'catentryId',
			width: 20,
			align: "center",
			sortable: true,
		    hidden:true
		},
		
		{
			name: 'seq',
			index: 'seq',
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
		rowNum: 5,
		rowList: [5,7,9],
		pager: '#cats_pager',
		sortname: 'seq',
		viewrecords: true,
		sortorder: "DESC",
		autowidth: true,
		height: 'auto',
		multiselect: false,
		rownumbers: true,
		caption: false
		
	});
	
	jQuery("#cats-list").jqGrid('navGrid', '#cats_pager', {
		add: false,
		del: false,
		edit: false,
		search: false
	});
		
	
});