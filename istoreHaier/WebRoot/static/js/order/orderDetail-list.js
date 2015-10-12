$(document).ready(function() {
	$("#orderDetail_info").tabs();
	var backOrderId=$("#back_orderId").val();
	jQuery("#orderitem").jqGrid({
		url: '/istore/servlet/b2b/order/orderDetailList.do?storeId=0&orderId='+backOrderId,
		datatype: "json",
		colNames: ['子订单号','商品目录Id','SKU','购买人','商品单价','数量','商品总价','商品状态','订单更新时间', '操作'],
		colModel: [{
			name: 'orderitems_id',
			index: 'orderitems_id',
			width: 55,
			hidden: true,
			align: "center"
		},
		{
			name: 'catentry_id',
			index: 'catentry_id',
			width: 60,
			hidden：true,
			align: "center"
		},
		{
			name: 'partnum',
			index: 'partnum',
			width: 60,
			align: "center"
		},
		{
			name: 'member_id',
			index: 'member_id',
			width: 60,
			align: "center"
		},
		{
			name: 'price',
			index: 'price',
			width: 60,
			align: "center"
		},
		{
			name: 'quantity',
			index: 'quantity',
			width: 60,
			align: "center"
		},
		{
			name: 'totalproduct',
			index: 'totalproduct',
			width: 60,
			align: "center"
		},
		{
			name: 'status',
			index: 'status',
			width: 60,
			align: "center"
		},
		{
			name: 'lastupdate',
			index: 'lastupdate',
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
		pager: '#orderitem_pager',
		sortname: 'orderTime',
		viewrecords: true,
		sortorder: "DESC",
		autowidth: true,
		height: 'auto',
		multiselect: false,
		rownumbers: true,
		caption: false,
		toolbar: [true, "top"],
		gridComplete: function() {
			var ids = jQuery("#orderitem").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var ret = jQuery("#orderitem").jqGrid('getRowData',ids[i]);
				var ordersId = ret.orders_id;
				ae="<input height:22px;width:70px;' type='button' value='编辑' onclick='editOrder("+ordersId+")'/>";
				be="<input height:22px;width:70px;' type='button' value='审批' onclick='approveOrder("+ordersId+")'/>";
				jQuery("#order").jqGrid('setRowData', ids[i], {
					ACTION: ae + be 
				});
			}
		}
	});
	jQuery("#orderitem").jqGrid('navGrid', '#orderitem_pager', {
		add: false,
		del: false,
		edit: false,
		search: false
	});
});


