$(document).ready(function() {
	$("#user_info").tabs();
	var icons = {
		header: "ui-icon-circle-arrow-e",
		activeHeader: "ui-icon-circle-arrow-s"
	};
	$("#accordionUser").accordion({
		icons: icons,
		heightStyle: 'content'
	});
	$("#user-back-btn").button({
		icons: {
			primary: "ui-icon-disk"
		}
	}).click(function(event) {
			location.href='/istore/servlet/b2c/user/list.do' ;
	});
	
	
	
	jQuery("#users").jqGrid({
		url: '/istore/servlet/b2c/user/json.do',
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
		pager: '#users_pager',
		sortname: 'CREATETIME',
		viewrecords: true,
		sortorder: "DESC",
		autowidth: true,
		height: 'auto',
		multiselect: false,
		rownumbers: true,
		caption: false,
//		toolbar: [true, "top"],
		gridComplete: function() {
			var ids = jQuery("#users").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var ret = jQuery("#users").jqGrid('getRowData',ids[i]);
				var usersId = ret.usersId;
				var url="/istore/servlet/b2c/user/viewUsers.do?storeId=0&usersId="+usersId+"&info=one";
				jQuery("#users").jqGrid('setRowData', ids[i], {
					ACTION: "<a href='"+ url +"' style='color: blue'>查看</a>"
				});
			}
		}
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
	
	
	//待审核会员列表
	jQuery("#verifyUsers").jqGrid({
		url: '/istore/servlet/b2b/userList/verifyJson.do',
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
		pager: '#verifyUsers_pager',
		sortname: 'CREATETIME',
		viewrecords: true,
		sortorder: "DESC",
		autowidth: true,
		height: 'auto',
		multiselect: false,
		rownumbers: true,
		caption: false,
//		toolbar: [true, "top"],
		gridComplete: function() {
			var ids = jQuery("#verifyUsers").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var ret = jQuery("#verifyUsers").jqGrid('getRowData',ids[i]);
				var usersId = ret.usersId;
				var url="/istore/servlet/b2c/user/viewUsers.do?storeId=0&usersId="+usersId+"&info=one";
				jQuery("#users").jqGrid('setRowData', ids[i], {
					ACTION: "<a href='"+ url +"' style='color: blue'>查看</a>"
				});
			}
		}
	});
	
	jQuery("#verifyUsers").jqGrid('navGrid', '#verifyUsers_pager', {
		add: false,
		del: false,
		edit: false,
		search: false
	});
	
	//

});


