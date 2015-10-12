$(document).ready(function(){
	var data = "catgroupId="+$("#h_catgroup_id").val();
	jQuery("#catalogs").jqGrid({
		url: '/istore/servlet/b2b/catalog/viewCatentry.do?'+data,
		datatype: "json",
		colNames: ['商品id','商品款号','商品名称','操作'],
		colModel: [{
			name: 'catentryId',
			index: 'catentryId',
			width: 55,
			align: "center"
		},
		{
			name:'partnumber',
			index:'partnumber',
			width:60,
			align:"center"
		},
		{
			name: 'catentryName',
			index: 'catentryName',
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
		rowList: [10,15,20],
		pager: '#catalogs_pager',
		sortname: 'CREATETIME',
		viewrecords: true,
		sortorder: "DESC",
		autowidth: true,
		height: 'auto',
		multiselect: false,
		rownumbers: true,
		caption: false,
		gridComplete: function() {
			var ids = jQuery("#catalogs").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var ret = jQuery("#catalogs").jqGrid('getRowData',ids[i]);
				var catentryId = ret.catentryId;
				var catgroup_id = $("#h_catgroup_id").val();
				jQuery("#catalogs").jqGrid('setRowData', ids[i], {
					ACTION: 
							"<input type='button' value='删除' onclick='deleteCat("+catentryId+","+catgroup_id+");' />"
				});
			}
		}
	});
	jQuery("#catalogs").jqGrid('navGrid', '#catalogs_pager', {
		add: false,
		del: false,
		edit: false,
		search: false,
		refresh: true
	});
	
	$("#add-catentry-dialog").dialog({
		autoOpen: false,
		width: 530,
		height:450,
		modal: true,
		resizable: false,
		title: '添加商品',
		
		buttons: [{
			text: "提交",
			click: function() {
				var selectedId = "selectedIds=";
				var selr = jQuery('#addCatentry').jqGrid('getGridParam','selarrrow');
				for(var i=0;i<selr.length;i++)
				{
					var myrow = jQuery('#addCatentry').jqGrid('getRowData',selr[i]);
					selectedId+=myrow.catentryId+",";
				}
				selectedId=selectedId+"&catalogid="+$("#h_catalog_id").val()+"&catgroupid="+$("#h_catgroup_id").val();
				$.ajax({
					url:'/istore/servlet/b2b/catalog/addSaleCatentry.do',
					dataType:'json',
					type:'post',
					data:selectedId,
					success:function(data){
						if(data=="true"){
							alert("商品成功挂入目录！");
						}else if(data=="false"){
							alert("商品挂入目录失败！");
						}
						$("#add-catentry-dialog").dialog("close");
						jQuery("#catalogs").trigger("reloadGrid");
						jQuery("#addCatentry").trigger("reloadGrid");
					}
				});
			}
		}]
	});

	jQuery("#catalogs").jqGrid('navButtonAdd', "#catalogs_pager", {
		caption: "",
		position: "first",
		title: "添加商品",
		buttonicon: "ui-icon-plus",
		onClickButton: function() {
			$("#partnumber").val("");
			$("#catentryName").val("");
			$("#add-catentry-dialog").dialog("open");
		}
	});
	

});

function deleteCat(catentry_Id, catgroup_id){
	if(confirm('确定删除该商品吗？')){
		var url="/istore/servlet/b2b/catalog/deleteCatentry.do?catgroup_id="+catgroup_id+"&catentry_id="+catentry_Id;
		$.ajax({
			url:url,
			dataType:'json',
			type:'get',
			success:function(data){
				if(data=="true") {  
	            	alert("删除成功！")	;
	       		}else if(data=="false"){
	       			alert("删除失败，请重试！");
	       	    }else{
	       	    	alert(data);
	       	     }
	            jQuery("#catalogs").trigger("reloadGrid");
			}
		});
	}
	
}
