function SearchViewByText(){
	var sel_search = $('#sel_search').val();
	var in_txt = $('#in_txt').val();
	
	$("#pipeline").jqGrid('setGridParam',{ 
        url:"pipelineListJson.do", 
        postData:{
			sel_search:sel_search,
			in_txt:in_txt,
		}, //发送数据 
        page:1 
    }).trigger("reloadGrid");
	
}
function sh_pipeline(pipelineid,status){
	var this_status = status;
	var this_pipelineid=pipelineid;
	$("#sel_status").find("option").remove(); 
	if(this_status=='S'){
		$("<option value='U'>U</option>").appendTo("#sel_status");
		$("<option value='N'>N</option>").appendTo("#sel_status");
		$("<option value='O'>O</option>").appendTo("#sel_status");
		$("<option value='C'>C</option>").appendTo("#sel_status");
	}else if(this_status == 'U'){
		$("<option value='N'>N</option>").appendTo("#sel_status");
		$("<option value='O'>O</option>").appendTo("#sel_status");
		$("<option value='C'>C</option>").appendTo("#sel_status");
	}else if(this_status =='N'){
		$("<option value='O'>O</option>").appendTo("#sel_status");
		$("<option value='C'>C</option>").appendTo("#sel_status");
	}else if(this_status == 'O'){
		$("<option value='C'>C</option>").appendTo("#sel_status");
	}else{
		return;
	}
	
	$('#editText').val(this_pipelineid);
	
	$("#edit-pipeline-dialog").dialog({
		modal: true,
		resizable:false,
		width: 600,
		height: 300,
		title:"商机单审核",
		close: function(event, ui){
			$("#edit-pipeline-dialog").dialog("destroy");
		},
		buttons: {
			"审核": function(){
			
					var urlX= "/istore/servlet/b2b/pipelineView/pipelineEdit.do";
					$.post(urlX, {'pipelineid':this_pipelineid,'status':$('#sel_status').val(),"comment":$('#txt_comment').val(),"ordersid":$('#in_orderid').val()}, function(data){
						if(data == "1"){
							alert("success");
						}
						else{
							alert("fail");
						}
						$("#edit-pipeline-dialog").dialog("destroy");
						jQuery("#pipeline").trigger("reloadGrid");
					});
				
			}
		}	
	});
}


function opne_order(){
	var sel_value = $('#sel_status').val();
	alert(sel_value);
	if(sel_value == 'O'){
		$('#tr_orderid').show();
	}else{
		$('#tr_orderid').removeAttr("style").attr("style","display:none");
	}
}