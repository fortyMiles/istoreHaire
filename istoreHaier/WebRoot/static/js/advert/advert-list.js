$(document).ready(function() {
	
	var advertType=$("#advertType").val();
	var webUrl="http://10.176.0.40/advert/images/";
	
jQuery("#advert").jqGrid({
		url: '/istore/servlet/b2b/advert/advertUpListJson.do?advertType='+advertType,
		datatype: "json",
		colNames: ['编号','图片','链接','文字','优先级','商品目录'],
		colModel: [	{
					name: 'id',
					index: 'id',
					width: 15,
					hidden: false,
					align: "center"
				},
		{
			name: 'image',
			index: 'image',
			width: 55,
			hidden: false,
			align: "center"
		},
		{
			name: 'url',
			index: 'url',
			width: 50,
			align: "center"
		},
		{
			name: 'title',
			index: 'title',
			width: 40,
			align: "center"
		},
		{
			name: 'priority',
			index: 'priority',
			width: 10,
			hidden: false,
			align: "center"
		},
		{
			name: 'name',
			index: 'name',
			width: 20,
			hidden: true,
			align: "center"
		}
		],
		rowNum: 10,
		rowList: [10,12,15],
		pager: '#advert_pager',
		
		viewrecords: true,
		sortorder: "DESC",
		autowidth: true,
		height: 'auto',
		multiselect: false,
		rownumbers: true,
		caption: false,
		multiselect:true
//		toolbar: [true, "top"],
		
	});

if(advertType==3)
	{
	
	$("#advert").jqGrid('showCol',"name");
	
	}


jQuery("#advert").jqGrid('navGrid', '#advert_pager', {
	add: false,
	del: false,
	edit: false,
	search: false
});

//add advert

jQuery("#advert").jqGrid('navButtonAdd', "#advert_pager", {
	caption: "",
	position: "first",
	title: "新建首页（上）广告位",
	buttonicon: "ui-icon-plusthick",

	onClickButton: function() {
	
		var type=$("#advertType").val();
				
				if(type=='3')
				{
				$("#productSelect").show();	
					
				}
		var url = "/istore/servlet/b2b/advert/addAdvert.do";
			$.post(url, {'advertType':type}, function(result){
				var _ecd = $("#add-advert-dialog");
				_ecd.html(result).dialog({
			modal: true,
			resizable:false,
			width: 400,
			height: 400,
			title:"添加广告位",
			close: function(event, ui) {
				$("#add-advert-dialog").dialog("close");
			},
			buttons: {
			"提交": function() {
				var prodouctGroup=$("#prodouctGroup").val();
			
				var image_priority=$("#maxPriority").val();
				
				//图片路径
				var image_url = finalfilename;
				if(image_url == null || image_url == ""){
					alert("请先上传图片");
					return ;
				}
				
				//图片链接
				var link = $("#link-url").val();
				if(link == null){
					link = "";
				}
				
				//type
				var type=$("#advertType").val();
				
				//优先级
			
				//文字说明
				var desc = $("#image-desc").val();
				if(desc == null){
					desc = "";
				}
				//商品目录
			
				
					var url = "/istore/servlet/b2b/advert/advertImageAdd.do";
					$.post(url, {priority: image_priority.toString(), link: link, imageUrl:image_url, desc: desc,type:type,prodouctGroup:prodouctGroup}, function(result){
						if(result == "success"){
							alert("添加成功！");
							$("#add-advert-dialog").dialog("close");
							jQuery("#advert").trigger("reloadGrid");
							finalfilename = ""
						}
						else{
							alert("添加失败！");
							$("#add-advert-dialog").dialog("close");
							jQuery("#advert").trigger("reloadGrid");
						}
					});					
				
			}
		}});
				});
	}
});
//end

//修改广告位

jQuery("#advert").jqGrid('navButtonAdd', "#advert_pager", {
	caption: "",
	position: "first",
	title: "广告位修改",
	buttonicon: "ui-icon-pencil",
	onClickButton: function() {
		var row = jQuery("#advert").jqGrid("getGridParam","selarrrow");
		var advert_id = jQuery("#advert").getCell(row, "id"); 
	
		if(row == null || row == ""){
			alert("请选择一个广告位!");
		}else{
			var advertType=$("#advertType").val();
		 
			var url = "/istore/servlet/b2b/advert/editAdvert.do";
			$.post(url, {'advert_id':advert_id.toString(),'advertType':advertType}, function(result){
				var _ecd = $("#edit-advert-dialog");
				_ecd.html(result).dialog({
					modal: true,
					resizable:false,
					width: 400,
					height: 440,
					title:"广告位编辑",
					close: function(event, ui){
						_ecd.dialog("destroy");
					},
					buttons: {
						"修改": function(){
							
							var link = $("#link-url1").val();
							if(link == null){
								link = "";
							}
							var desc = $("#image-desc1").val();
							if(desc == null){
								desc = "";
							}
					
							if(finalfilename=="")
								{
								finalfilename=$("#imagepath").val();
								alert(finalfilename);
								}
							var prodouctGroup=$("#prodouctGroup").val();
								var url = "/istore/servlet/b2b/advert/edit.do";
								$.post(url, {advert_id:advert_id.toString(),finalfilename:finalfilename, link:link, desc:desc,prodouctGroup:prodouctGroup,advertType:advertType}, function(result){
									if(result == "success"){
										alert("修改成功！");
										_ecd.dialog("destroy");
										jQuery("#advert").trigger("reloadGrid");
									
									}
									else{
										alert("修改失败！");
										_ecd.dialog("destroy");
										jQuery("#advert").trigger("reloadGrid");
									}
								});					
							
						}
					}
				});
			});
		}
	}
});

//删除广告位
jQuery("#advert").jqGrid('navButtonAdd', "#advert_pager", {
	caption: "",
	position: "first",
	title: "广告位删除",
	buttonicon: "ui-icon-trash",
	onClickButton: function() {
		
		var row = jQuery("#advert").jqGrid("getGridParam","selarrrow");
		var advert_id = jQuery("#advert").getCell(row, "id");
		if(row == null || row == ""){
			alert("请选择一个广告位!");
		}else{
			if(confirm("确定删除吗？")){
				var url = "/istore/servlet/b2b/advert/delete.do";
				$.post(url, {'advert_id':advert_id.toString()}, function(result){
					if(result == "success"){
						alert("删除成功！");
						jQuery("#advert").trigger("reloadGrid");
						
					}
					else{
						alert("删除失败！");
						jQuery("#advert").trigger("reloadGrid");
					}
				});
			}
		}
	}
});
//向上移动楼层

jQuery("#advert").jqGrid('navButtonAdd', "#advert_pager",{
	caption : "",
	position : "first",
	title : "上调优先级",
	buttonicon : "ui-icon-arrowthick-1-n",
	onClickButton : function() {
	var row = jQuery("#advert").jqGrid("getGridParam", "selrow");//获得当前行
	//var row=jQuery("#advert").getGridParam("reccount");
	var nowPriority = jQuery("#advert").getCell(row, "priority"); 
	if(nowPriority == 1){
		alert("已经是最高优先级");
		jQuery("#advert").trigger("reloadGrid");
	}
	else{
		var beforeId = jQuery("#advert").getCell(row - 1, "id"); // 上一行的 espotId 值			
		var id = jQuery("#advert").getCell(row, "id"); // 当前行的 espotId 值
		var beforePriority = jQuery("#advert").getCell(row - 1, "priority"); // 上一行的 espotId 值
		var idPriority = jQuery("#advert").getCell(row, "priority"); // 当前行的 espotId 值			
		if (row != null && row != "") {
			$.post('AdvertAjaxUpMove.do',{
				beforeId: beforeId, 
				id: id, 
				beforePriority: beforePriority, 
				idPriority: idPriority, 
				storeId: 0
			}, function(result){
				if(result == "success"){
					alert("上移成功！");
					jQuery("#advert").trigger("reloadGrid");
				}
				else{
					alert("上移失败！");
					jQuery("#advert").trigger("reloadGrid");
				}
			});
		}
		else{
			alert("请选择上移的广告位");
		}		
	}
}
});

//下移广告位

jQuery("#advert").jqGrid('navButtonAdd', "#advert_pager",{
	caption : "",
	position : "first",
	title : "下调优先级",
	buttonicon : "ui-icon-arrowthick-1-s",
	onClickButton : function() {
	var row = jQuery("#advert").jqGrid("getGridParam", "selrow");//获得当前行
	var afterId = jQuery("#advert").getCell(parseInt(row) + 1, "id");
	var nowPriority = jQuery("#advert").getCell(parseInt(row) + 1, "priority"); // 下一行的 espotId 值
	
	if(nowPriority == false){
		alert("已经是最低优先级");
		jQuery("#advert").trigger("reloadGrid");
	}
	else{
		var id = jQuery("#advert").getCell(row, "id"); // 当前行的 espotId 值
		var afterPriority = jQuery("#advert").getCell(parseInt(row) + 1, "priority"); // 下一行的 espotId 值
		var idPriority = jQuery("#advert").getCell(row, "priority"); // 当前行的 espotId 值			
		if (row != null && row != "") {
			$.post('AdvertAjaxUpMove.do', 
					{
				beforeId: afterId, 
						id: id, 
						beforePriority: afterPriority, 
						idPriority: idPriority, 
						storeId: 0
					}, function(result){
						if(result == "success"){
							alert("下移成功！");
							jQuery("#advert").trigger("reloadGrid");
						}
						else{
							alert("下移失败！");
							jQuery("#advert").trigger("reloadGrid");
						}
					});
		}
		else{
			
			alert("请选择下移的广告位");
		}		
	}
}
});

//
});

var finalfilename = "";

//上传tupian
function uploadIMG(){

	
	$.ajaxFileUpload({
     url: '/istore/servlet/b2b/advert/WebAdvertUpload.do',//处理上传用的后台程序,可以是PHP,也可以是ASP等
     secureuri: false,//异步
     fileElementId: 'uploadfile',//上传控件ID
     dataType: 'json',//返回的数据信息格式
     success: function(data, status) {
    	 
    	

     	if(data.message==1){
     		//上传成功
     		finalfilename = data.finalfilename;
     	
     		
     		var htmlImg = "<img src='http://10.176.0.40/advert/images/"+finalfilename+"' style='width:200px;height:100px'/>";
     		$('#imgresult').html(htmlImg).show();
     		$('#imgMessage').html('上传成功').show();
     		//setTimeout(function(){
     		//	$('#imgMessage').fadeOut(500);
     		//},  3000);
     
     	}
     	
     	else{
     		$('#imgMessage'+type).html('上传失败').show();
     		setTimeout(function(){
     			$('#imgMessage'+type).fadeOut(500);
     		},  3000);
     	}
     }
	});
	return false;
}
//修改图片
function uploadIMG1(){

	$.ajaxFileUpload({
     url: '/istore/servlet/b2b/advert/WebAdvertUploadEdit.do',//处理上传用的后台程序,可以是PHP,也可以是ASP等
     secureuri: false,//异步
     fileElementId: 'uploadfile1',//上传控件ID
     dataType: 'json',//返回的数据信息格式
     success: function(data, status) {
    	// alert(data.message);
    	
     	if(data.message==1){
     		//上传成功
     		
     		finalfilename = data.finalfilename;
     		
     		var htmlImg = "<img src='http://10.176.0.40/advert/images/"+finalfilename+"' style='width:200px;height:100px'/>";
     		$('#imgresult1').html(htmlImg).show();
     		$('#imgMessage1').html('上传成功').show();
    
     		//setTimeout(function(){
     		//	$('#imgMessage').fadeOut(500);
     		//},  3000);
     	}
     	
     	else{
     		$('#imgMessage1'+type).html('上传失败').show();
     		
     	}
     }
	});
	return false;
}

//生成静态HTML
function setAdvertStatic(usage){
	if(confirm('是否生成静态页面吗 ？')){ 
		alert("shengcheng");
	$.blockUI({ message: '<h3> 正在生成静态页面...</h3>' });	
		var urlX = "/istore/servlet/b2b/advert/CreateAdvertHtml.do";
		$.post(urlX,{'usage':usage},function(result){
			//$.unblockUI();
			var data = parseInt(result);
			if(data=='1'){
				alert("生成成功。");
			}else{
				alert("生成失败。");
			}
		});
	}
}



