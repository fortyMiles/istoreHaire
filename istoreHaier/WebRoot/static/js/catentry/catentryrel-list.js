$(document).ready(function(){
	
	var data = "catentryid="+$("#h_catentry_id").val();
	jQuery("#catalogs").jqGrid({
		url: '/istore/servlet/b2b/catmng/viewCatentry.do?'+data,
		datatype: "json",
		colNames: ['商品id','商品款号','商品名称','序列','操作'],
		colModel: [{
			name: 'catentryId',
			index: 'catentryId',
			width: 45,
			align: "center"
		},
		{
			name:'partnumber',
			index:'partnumber',
			width:60,
			align:"center"
		},
		{
			name: 'name',
			index: 'name',
			width: 50,
			align: "center"
		},
		{
			name:'seq',
			index:'seq',
			width:30,
			align:'center'
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
//		toolbar: [true, "top"],
		gridComplete: function() {
			var ids = jQuery("#catalogs").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var ret = jQuery("#catalogs").jqGrid('getRowData',ids[i]);
				var catentryId = ret.catentryId;
				var catgroup_id = $("#h_catgroup_id").val();
				jQuery("#catalogs").jqGrid('setRowData', ids[i], {
					ACTION: 
							"<input type='button' value='删除' onclick='deleteCat("+catentryId+");' />"+
							"<input type='button' value='修改序列' onclick='modifySeq("+catentryId+");' />"
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
				//alert(selectedId);
				selectedId=selectedId+"&catentryId="+$("#h_catentry_id").val();
				$.ajax({
					url:'/istore/servlet/b2b/catmng/addSaleCatentry.do',
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

function deleteCat(catentry_id_rel){
	if(confirm('确定删除该商品吗？')){
		var url="/istore/servlet/b2b/catmng/deleteCatentry.do?catentry_id="+$("#h_catentry_id").val()+"&catentry_id_rel="+catentry_id_rel;
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

function modifySeq(catentry_id_rel){
	$("#seq").val("");
	
	$("#mod-catentry-seq").dialog({
		width: 400,
		modal: true,
		resizable: false,
		title: '修改序列',
		
		buttons: [{
			text: "提交",
			click: function() {
				var seq = $("#seq").val();
				var patt = /^[1-9]?[1-9]?[0-9]$/g;
				if(!patt.test(seq)){
					alert("请输入三位以内的正整数！");
					return;
				}
				var url = "/istore/servlet/b2b/catmng/modifySeq.do?catentry_id="+$("#h_catentry_id").val()+"&catentry_id_rel="+catentry_id_rel+"&seq="+seq;
				$.ajax({
					url:url,
					dataType:'json',
					type:'post',
					success:function(data){
						if(data=="true"){
							alert("序列修改成功！");
						}else if(data=="false"){
							alert("序列修改失败！");
						}
						$("#mod-catentry-seq").dialog("close");
						jQuery("#catalogs").trigger("reloadGrid");
					}
				});
			}
		}]
	});
	$("#mod-catentry-seq").dialog("open");
}
