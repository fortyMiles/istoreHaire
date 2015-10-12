$(document).ready(function() {
	jQuery("#verifyUsers").jqGrid({
		url: '/istore/servlet/b2b/userVerifyList/verifyJson.do',
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
		pager: '#verifyUsers_pager',
		sortname: 'attr_id',
		viewrecords: true,
		sortorder: "DESC",
		autowidth: true,
		height: 'auto',
		multiselect: true,
		rownumbers: true,
		caption: false
	});
	jQuery("#verifyUsers").jqGrid('navGrid', '#verifyUsers_pager', {
		add: false,
		del: false,
		edit: false,
		search: false
	});
	
	
	
	
	
	//待审核详情弹出框
	$("#verifyUser-detail").dialog({
		modal: true,
		autoOpen: false,
		resizable:false,
		width:500,
		height: 600,
		title:"会员详情",
		close: function(event, ui) {location.reload();},
		buttons:{
			
			"审核":function(){
			var users_id=$("#userIds").val();
		    
			var approveResult=$("#verifyContent").val();
			$.post('/istore/servlet/b2b/userVerifyList/verifyUser.do',{'users_id':users_id.toString(),approveResult:approveResult},function(result)
				{
				if(result=="success")
					{
					alert("审核成功");
					location.reload();
					}
				else{
					alert("审核失败");
					location.reload();
				}
				   
				});
			},
			"拒绝":function(){
				var users_id=$("#userIds").val();
				var approveResult=$("#verifyContent").val();
				$.post('/istore/servlet/b2b/userVerifyList/refuseUser.do',{'users_id':users_id.toString(),approveResult:approveResult},function(result)
					{
					if(result=="success")
						{
						alert("拒绝成功");
						location.reload();
						}
					else
						{
						alert("拒绝失败");
						location.reload();
						}
					   });}
		}
	});
	//P状态除外
	$("#AverifyUser-detail").dialog({
		modal: true,
		autoOpen: false,
		resizable:false,
		width:500,
		height: 600,
		title:"会员详情",
		close: function(event, ui) {location.reload();},
		buttons:{
			
			"关闭":function(){
			location.reload();	
			}	
		}
	});
	//
	
});
function showVerifyDetail(obj,status)
{
	var users_id=obj;
	var status1=status;
	alert(status1);
	$.post('/istore/servlet/b2b/userVerifyList/verifyDetailJson.do',{'users_id':users_id.toString()},function(result){
		
		if(status1=='待审核')
			{
		
		 $("#verifyUser-detail").html(result).dialog('open'); }
		else
			{
			 $("#AverifyUser-detail").html(result).dialog('open');
			}
	}
	)
}


