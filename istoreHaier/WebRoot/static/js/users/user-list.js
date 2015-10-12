$(document).ready(function() {
	jQuery("#users").jqGrid({
		url: '/istore/servlet/b2b/userList/json.do',
		datatype: "json",
		colNames: ['id','会员登录名','顾客标识','类型','状态','更新时间','操作'],
		colModel: [{
			name: 'usersId',
			index: 'usersId',
			width: 55,
			hidden: true,
			align: "center"
		},
		{
			name: 'logonId',
			index: 'logonId',
			width: 60,
			align: "center"
		},
		{
			name: 'customer_id',
			index: 'customer_id',
			width: 60,
			align: "center"
		},
		{
			name: 'type',
			index: 'type',
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
			name: 'lastUpdate',
			index: 'lastUpdate',
			width: 60,
			align: "center"
		},
		{
			name: 'action',
			index: 'action',
			width: 40,
			align: "center"
		}
		],
		rowNum: 10,
		rowList: [10,12,15],
		pager: '#users_pager',
		sortname: 'attr_id',
		viewrecords: true,
		sortorder: "DESC",
		autowidth: true,
		height: 'auto',
		multiselect: true,
		rownumbers: true,
		caption: false
	});
	jQuery("#users").jqGrid('navGrid', '#users_pager', {
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
		title: '查询会员',
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

	
	
	
	$("#user-detail1").dialog({
		modal: true,
		autoOpen: false,
		resizable:false,
		width:500,
		height: 300,
		title:"detail",
		close: function(event, ui) {location.reload();},
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
	
	
	
});

function showDetail(obj){
	var users_id = obj;
	 $.post('/istore/servlet/b2b/userList/detailJson.do',{'users_id':users_id.toString()},function(result){
		 $("#user-detail1").html(result).dialog('open'); 
	});
}

function showVerifyDetail(obj)
{
	var users_id=obj;
	$.post('/istore/servlet/b2b/userList/verifyDetailJson.do',{'users_id':users_id.toString()},function(result){
		
		 $("#verifyUser-detail").html(result).dialog('open'); 
	});
}

