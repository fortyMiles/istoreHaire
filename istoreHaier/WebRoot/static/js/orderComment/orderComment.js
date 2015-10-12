function replyComment(comment_id, isReplyed){
	var url = "/istore/servlet/b2b/orderComment/editlist.do";
	$.post(url, {'comment_id':comment_id.toString()}, function(result){
		if(isReplyed=="N"){
			$("#edit-orderComment-dialog").html(result).dialog({
				modal: true,
				resizable:false,
				width: 600,
				height: 300,
				title:"订单评论回复",
				close: function(event, ui){
					$("#edit-orderComment-dialog").dialog("destroy");
				},
				
				buttons: {
					"回复": function(){
						var replyContent = $("#replyContent").val();
						if(replyContent==""){
							alert("请输入回复内容");
						}else if(replyContent.length==300){
							alert("回复内容不能超过300字");
						}else{
							var urlX= "/istore/servlet/b2b/orderComment/edit.do";
							$.post(urlX, {'comment_id':comment_id.toString(),'replyContent':replyContent.toString()}, function(data){
								if(data == "success"){
									alert("回复成功！");
									$("#edit-orderComment-dialog").dialog("destroy");
									jQuery("#orderComment").trigger("reloadGrid");
								}
								else{
									alert("回复失败！");
									$("#edit-orderComment-dialog").dialog("destroy");
									jQuery("#orderComment").trigger("reloadGrid");
								}
							});
						}
					}
				}
			});
		}else{
			$("#edit-orderComment-dialog").html(result).dialog({
				modal: true,
				resizable:false,
				width: 600,
				height: 300,
				title:"订单评论回复查看",
				close: function(event, ui){
					$("#edit-orderComment-dialog").dialog("destroy");
				},
			});
		}
		
	});
}