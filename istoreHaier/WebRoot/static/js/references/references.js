/**
 * 查看样板工程详情
 * 
 * @param xreferences_id
 */
function viewReferences(xreferences_id) {
	var url = "/istore/servlet/b2b/references/view.do";
	$.post(url, {'xreferences_id':xreferences_id.toString()}, function(result){
		var _vrd = $("#view-references-dialog");
		_vrd.html(result).dialog({
			modal: true,
			resizable:false,
			width: 900,
			height: 'auto',
			title:"样板工程详情",
			zIndex: 9999,
			close: function(event, ui){
				_vrd.dialog("destroy");
			}
		});
	});
}

/**
 * 审核通过
 * 
 * @param xreferences_id
 */
function approveReferences(xreferences_id, status) {
	if(status == "D") {
		alert("样板工程尚未提交审核，不能通过发布！");
	}else if (status == "P") {
		alert("样板工程已经通过");
	}else {
		if(confirm("确定通过吗？")){
			var url = "/istore/servlet/b2b/references/approve.do";
			$.post(url, {'xreferences_id':xreferences_id.toString()}, function(result){
				if(result == "success"){
					alert("发布成功！");
				}
				else{
					alert("发布失败！");
				}
				jQuery("#references").trigger("reloadGrid");
			});
		}
	}
}

/**
 * 拒绝
 * 
 * @param xreferences_id
 */
function rejectReferences(xreferences_id, status) {
	if (status == "D") {
		alert("样板工程尚未提交审核，不能拒绝！");
	}
	if (status == "R") {
		alert("样板工程已经拒绝，尚未重新编辑！");
	} else {
		var _rrd = $("#reject-references-dialog");
		_rrd.dialog({
			modal: true,
			resizable:false,
			width: 850,
			height: 310,
			title:"拒绝原因",
			close: function(event, ui) {
				_rrd.dialog("destroy");
			},
			buttons: {
				"拒绝": function() {
					var xcomment = $("#reject_xcomment").val();
					if(xcomment == "" || xcomment == null){
						alert("请输入拒绝原因！");
						$("#reject_xcomment").focus();
					}else{
						var url = "/istore/servlet/b2b/references/reject.do";
						$.post(url, {'xreferences_id':xreferences_id.toString(), 'xcomment':xcomment}, function(result){
							if(result == "success"){
								alert("拒绝成功！");
							}
							else{
								alert("拒绝失败！");
							}
							jQuery("#references").trigger("reloadGrid");
							_rrd.dialog("destroy");
						});
					}
				}
			}
		});
	}
}