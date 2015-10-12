$(document).ready(function(){
	var data = "storeId="+$("#storeId").val()+"&catgroupId="+$("#catgroupId").val();
	jQuery("#catalogs").jqGrid({
		url: '/istore/servlet/b2b/catalog/jsond.do?'+data,
		datatype: "json",
		colNames: ['目录id','目录名称','操作'],
		colModel: [{
			name: 'catgroupId',
			index: 'catgroupId',
			width: 55,
			align: "center"
		},
		{
			name: 'name',
			index: 'name',
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
				var catgroupId = ret.catgroupId;
				var delUrl = "/istore/servlet/b2b/catalog/deleteCatalog.do?catgroupId="+catgroupId+"&level=3&info=one";
				var viewUrl="/istore/servlet/b2b/catalog/viewCatentryPage.do?catgroupId="+catgroupId+"&info=one";
				jQuery("#catalogs").jqGrid('setRowData', ids[i], {
					ACTION: "<input type='button' id='modCatB' value='修改名称' onclick='modifyName("+catgroupId+",\""+ret.name+"\");' />"+
							"<input type='button' value='删除' onclick='deleteCat("+catgroupId+");' />"+
							"<a href='"+ viewUrl +"'><button>查看商品</button></a>"
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
	
	$("#add-catalog-dialog").dialog({
		autoOpen: false,
		width: 400,
		modal: true,
		resizable: false,
		title: '添加目录',
		
		buttons: [{
			text: "提交",
			click: function() {
				var catalogName = jQuery("#catalogName").attr("value");
				if(catalogName=="")
				{
					alert("请输入需要添加的内容！");
					return;
				}
				$(this).dialog( "close" );
			       var options ={url:'addCatalog.do',type:'post',data:{catalogName:catalogName,
		            	level:2,catgroupId:$("#catgroupId").val()},dataType:'json',
	                  			 success:function(data){
			                        if(data=="true") {  
			                        	alert("添加成功！")	;
			                   		}else if(data=="false"){
			                   			alert("添加失败！");
			                   	    } 
			                   	    else{
			                   	    	alert(data);
			                   	     }
			                        jQuery("#catalogs").trigger("reloadGrid");
           			 		 }   
         	   };
       		   var form =$("#addform");
       		   form.ajaxSubmit(options);  
			}
		}]
	});

	jQuery("#catalogs").jqGrid('navButtonAdd', "#catalogs_pager", {
		caption: "",
		position: "first",
		title: "添加目录",
		buttonicon: "ui-icon-plus",
		onClickButton: function() {
			$("#catalogName").val("");
			$("#add-catalog-dialog").dialog("open");
		}
	});
	

});

function modifyName(id,name){
	$("#catalogNameB").val(name);
	$("#modify-catalog-dialog").dialog({
		width: 400,
		modal: true,
		resizable: false,
		title: '修改目录',
		
		buttons: [{
			text: "提交",
			click: function() {
				var catalogName = jQuery("#catalogNameB").attr("value");
				if(catalogName=="")
				{
					alert("请输入需要修改的目录名称！");
					return;
				}
				$(this).dialog( "close" );
			       var options ={url:'modifyCatalog.do',type:'post',data:{catalogName:catalogName,catgroupId:id},dataType:'json',
	                  			 success:function(data){
			                        if(data=="true") {  
			                        	alert("修改成功！")	;
			                   		}else if(data=="false"){
			                   			alert("修改失败，请重试！");
			                   	    } 
			                   	    else{
			                   	    	alert(data);
			                   	     }
			                        jQuery("#catalogs").trigger("reloadGrid");
           			 		 }   
         	   };
       		   var form =$("#modform");
       		   form.ajaxSubmit(options);  
       		   
			}
		}]
	
	});
}

function deleteCat(id){
	if(confirm('确定删除该目录吗？')){
		var url="/istore/servlet/b2b/catalog/deleteCatalog.do?catgroupId="+id+"&level=3&info=one";
		$.ajax({
			url:url,
			dataType:'json',
			type:'get',
			success:function(data){
				if(data=="true") {  
	            	alert("删除成功！")	;
	       		}else if(data=="false"){
	       			alert("删除失败，请重试！");
	       	    }else if(data="exist"){
	       	    	alert("该目录下存在商品，无法删除！");
	       	     }
	            jQuery("#catalogs").trigger("reloadGrid");
			}
		});
	}
}
