$(document).ready(function() {
	jQuery("#orderPart").jqGrid({
		url: '/istore/servlet/b2b/user/json.do',
		datatype: "json",
		colNames: ['id','会员姓名','会员登录名','线下会员卡号','手机号码','用户生日','所属门店', '操作'],
		colModel: [{
			name: 'usersId',
			index: 'usersId',
			width: 55,
			hidden: true,
			align: "center"
		},
		{
			name: 'userName',
			index: 'userName',
			width: 60,
			align: "center"
		},
		{
			name: 'logonId',
			index: 'logonId',
			width: 60,
			align: "center"
		},
		{
			name: 'codeNum',
			index: 'codeNum',
			width: 60,
			align: "center"
		},
		{
			name: 'phone',
			index: 'phone',
			width: 60,
			align: "center"
		},
		{
			name: 'birthday',
			index: 'birthday',
			width: 60,
			align: "center"
		},
		{
			name: 'shopId',
			index: 'shopId',
			width: 60,
			align: "center"
		},
		{
			name: 'ACTION',
			index: 'ACTION',
			width: 30,
			align: "center",
			sortable: false
		}],
		rowNum: 10,
		rowList: [10,12,15],
		pager: '#orderPart_pager',
		sortname: 'CREATETIME',
		viewrecords: true,
		sortorder: "DESC",
		autowidth: true,
		height: 'auto',
		multiselect: false,
		rownumbers: true,
		caption: false,
		gridComplete: function() {
			var ids = jQuery("#orderPart").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var ret = jQuery("#orderPart").jqGrid('getRowData',ids[i]);
				var usersId = ret.usersId;
				var url="/istore/servlet/b2c/user/viewUsers.do?storeId=0&usersId="+usersId+"&info=one";
				jQuery("#orderPart").jqGrid('setRowData', ids[i], {
					ACTION: "<a href='"+ url +"' style='color: blue'>查看</a>"
				});
			}
		}
	});
	jQuery("#orderPart").jqGrid('navGrid', '#orderPart_pager', {
		add: false,
		del: false,
		edit: false,
		search: false
	});
	
	
});
