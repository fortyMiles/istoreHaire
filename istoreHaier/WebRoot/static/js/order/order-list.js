$(document).ready(function() {
	jQuery("#order").jqGrid({
		url: '/istore/servlet/b2b/order/orderListJson.do',
		datatype: "json",
		colNames: ['订单号','订单总价','下单时间','购买人','订单状态', '操作'],
		colModel: [{
			name: 'orders_id',
			index: 'orders_id',
			width: 55,
			hidden: true,
			align: "center",
			formatter: "showlink", 
			formatoptions:{baseLinkUrl:"orderPartList_html",idName:"usersId",addParam:"&usersType=receiptType"}
		},
		{
			name: 'totalproduct',
			index: 'totalproduct',
			width: 60,
			align: "center"
		},
		{
			name: 'orderTime',
			index: 'orderTime',
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
			name: 'status',
			index: 'status',
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
		pager: '#order_pager',
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
			var ids = jQuery("#order").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var ret = jQuery("#order").jqGrid('getRowData',ids[i]);
				var ordersId = ret.orders_id;
				ae="<input height:22px;width:70px;' type='button' value='编辑' onclick='editOrder("+ordersId+")'/>";
				be="<input height:22px;width:70px;' type='button' value='审批' onclick='approveOrder("+ordersId+")'/>";
				jQuery("#order").jqGrid('setRowData', ids[i], {
					ACTION: ae + be 
				});
			}
		}
	});
	jQuery("#order").jqGrid('navGrid', '#users_pager', {
		add: false,
		del: false,
		edit: false,
		search: false
	});
	
	$("#search-user-dialog").dialog({
		autoOpen: false,
		width: 400,
		modal: true,
		resizable: false,
		title: '查询订单',
		open: function( event, ui ) {
			$("#back_user_startDate").datepicker({
				changeMonth: true,
				changeYear: true,
				onSelect: function(dateText, inst)    // 使结束时间大于开始时间  
				{  
				    var arys = new Array();  
				    var arys = dateText.split('-');  
				    $("#back_user_endDate").datepicker('option', 'minDate', new Date(arys[0],arys[1]-1,arys[2]));  
				} 
			});
			$("#back_user_endDate").datepicker({
				changeMonth: true,
				changeYear: true,
				onSelect: function(dateText, inst)  
				{  
				   var arys = new Array();  
				   var arys = dateText.split('-');  
				   $("#back_user_startDate").datepicker('option','maxDate',new Date(arys[0],arys[1]-1,arys[2]));
				}
			});
		},
		buttons: [{
			text: "查询",
			click: function() {
				var userName = jQuery("#username").attr("value");
				var userCode = jQuery("#user_code").attr("value");
				var phone = jQuery("#phone").attr("value");
				var searchShopId =jQuery("#search_shopId").attr("value");
				var startDate = $("#back_user_startDate").val();
				var endDate = $("#back_user_endDate").val();
				if(userName=="" && userCode=="" && phone==""&& searchShopId=="" && startDate==""&&endDate=="")
				{
					alert("请输入需要搜索的内容！");
					return;
				}
				$("#users").jqGrid('setGridParam',{ 
		            url:"searchUsers.do?storeId=0", 
		            postData:{
						userName:userName,
						userCode:userCode,
						phone:phone,
						startDate:startDate,
						endDate:endDate,
						searchShopId:searchShopId,
						searchType:'adminType'
					}, //发送数据 
		            page:1 
		        }).trigger("reloadGrid"); //重新载入 
				$(this).dialog("close");
			}
		},
		{
			text: "重置",
			click: function() {
				document.getElementById("username").value="";
				document.getElementById("user_code").value="";
				document.getElementById("phone").value="";
				document.getElementById("search_shopId").value="";
				document.getElementById("back_user_startDate").value="";
				document.getElementById("back_user_endDate").value="";
			}
		}]
	});

	//搜索用户
	jQuery("#users").jqGrid('navButtonAdd', "#users_pager", {
		caption: "",
		position: "first",
		title: "搜索会员",
		buttonicon: "ui-icon-search",
		onClickButton: function() {
			$("#search-user-dialog").dialog("open");
		}
	});
	
	var exportButton = '<button id="searchOrderButton" onclick="searchOrderButton()" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-primary" role="button" aria-disabled="false">' 
    	+ '<span class="ui-button-icon-primary ui-icon ui-icon-person"></span><span class="ui-button-text">查询订单</span></button>';
	$("#t_order").append(exportButton);
});

function searchOrderButton(){
	
}

function editOrder(obj){
	location.href='/istore/servlet/b2b/order/orderDetail.do?storeId=0&orderId='+obj ;
}
