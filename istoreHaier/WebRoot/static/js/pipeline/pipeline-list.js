$(document).ready(function() {
	jQuery("#pipeline").jqGrid({
		url: '/istore/servlet/b2b/pipelineView/pipelineListJson.do',
		datatype: "json",
		colNames: ['POPELINE_ID','STATUS','NAME','ADDRESS','SUBMIT_DATE', 'LAST_UPDATE','ORDERS_ID','EXPECTED_DATE','CONFIDENCE','COMMENT','PHONE','EMAIL','PROJECT_TYPE','PROJECT_AREA','LOCATION','N_TIME','OPERATE'],
		colModel: [
		
		{
			name: 'popeline_id',
			index: 'popeline_id',
			align: "center"
		},
		{
			name: 'status',
			index: 'status',
			align: "center"
		},
		{
			name: 'name',
			index: 'name',
			align: "center"
		},
		{
			name: 'address_name',
			index: 'address_name',
			align: "center"
		},
		{
			name: 'submit_date',
			index: 'submit_date',
			align: "center"
		},
		{
			name: 'last_update',
			index: 'last_update',
			align: "center"
		},
		{
			name: 'orders_id',
			index: 'orders_id',
			align: "center"
		},
		{
			name: 'expected_date',
			index: 'expected_date',
			align: "center"
		},
		{
			name: 'confidence',
			index: 'confidence',
			align: "center"
		},
		{
			name: 'xcomment',
			index: 'xcomment',
			align: "center"
		},
		{
			name: 'phone',
			index: 'phone',
			align: "center"
		},
		{
			name: 'email',
			index: 'email',
			align: "center"
		},
		{
			name: 'project_type',
			index: 'project_type',
			align: "center"
		},
		{
			name: 'project_area',
			index: 'project_area',
			align: "center"
		},
		{
			name: 'location',
			index: 'location',
			align: "center"
		},
		{
			name: 'n_time',
			index: 'n_time',
			align: "center"
		},
		{
			name: 'action',
			index: 'action',
			align: "center",
			sortable: false
		}
		
		
		],
		rowNum: 10,
		rowList: [10,12,15],
		pager: '#pipeline_pager',
		viewrecords: true,
		autowidth: true,
		height: 'auto',
		multiselect: false,
		rownumbers: true,
		caption: false 
	
	});
	jQuery("#pipeline").jqGrid('navGrid', '#pipeline_pager', {
		add: false,
		del: false,
		edit: false,
		search: false
	});
	
});

