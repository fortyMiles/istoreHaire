$(document).ready(function() {
	jQuery("#orderComment").jqGrid({
		url: '/istore/servlet/b2b/orderComment/json.do',
		datatype: "json",
		colNames: ['订单评论编号', '订单编号', '评论人', '评论时间', '操作'],
		colModel: [{
			name: 'COMMENT_ID',
			index: 'COMMENT_ID',
			width: 10,
			align: "center",
			sortable: true
		},
		{
			name: 'ORDERS_ID',
			index: 'ORDERS_ID',
			width: 20,
			align: "center"
		},
		{
			name: 'USER_NAME',
			index: 'USER_NAME',
			width: 5,
			align: "center"
		},
		{
			name: 'LASTUPDATE',
			index: 'LASTUPDATE',
			width: 20,
			align: "center"
		},
		{
			name: 'OPERATION',
			index: 'OPERATION',
			width: 30,
			align: "center",
			sortable: false
		}],
		rowNum: 10,
		rowList: [10,12,15],
		pager: '#orderComment_pager',
		sortname: 'COMMENT_ID',
		viewrecords: true,
		sortorder: "DESC",
		autowidth: true,
		height: 'auto',
		multiselect: true,
		rownumbers: true,
		caption: false,
	});
	
	jQuery("#orderComment").jqGrid('navGrid', '#orderComment_pager', {
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
				height: 240,
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
	
});
