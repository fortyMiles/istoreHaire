/**
 * 查看新闻详情
 * 
 * @param xreferences_id
 */
function viewNews(xnews_id) {
	var url = "/istore/servlet/b2b/news/view.do";
	$.post(url, {
		'xnews_id' : xnews_id.toString()
	}, function(result) {
		var _vnd = $("#view-news-dialog");
		_vnd.html(result).dialog({
			modal : true,
			resizable : false,
			width : 900,
			height : 'auto',
			title : "新闻详情",
			close : function(event, ui) {
				_vnd.dialog("destroy");
			}
		});
	});
}

/**
 * 审核通过
 * 
 * @param xreferences_id
 */
function approveNews(xnews_id, status) {
	if(status == "D") {
		alert("新闻尚未提交审核，不能通过发布！");
	}else if (status == "P") {
		alert("新闻已经通过");
	}else {
		if (confirm("确定通过吗？")) {
			var url = "/istore/servlet/b2b/news/approve.do";
			$.post(url, {
				'xnews_id' : xnews_id.toString()
			}, function(result) {
				if (result == "success") {
					alert("发布成功！");
				} else {
					alert("发布失败！");
				}
				jQuery("#news").trigger("reloadGrid");
			});
		}
	}
}

/**
 * 拒绝
 * 
 * @param xreferences_id
 */
function rejectNews(xnews_id, status) {
	if (status == "D") {
		alert("新闻尚未提交审核，不能拒绝！");
	}
	if (status == "R") {
		alert("新闻已经拒绝，尚未重新编辑！");
	} else {
		var _rnd = $("#reject-news-dialog");
		_rnd.dialog({
			modal : true,
			resizable : false,
			width : 900,
			height : 310,
			title : "拒绝原因",
			close : function(event, ui) {
				_rnd.dialog("destroy");
			},
			buttons : {
				"拒绝" : function() {
					var xcomment = $("#reject_xcomment").val();
					if (xcomment == "" || xcomment == null) {
						alert("请输入拒绝原因！");
						$("#reject_xcomment").focus();
					} else {
						var url = "/istore/servlet/b2b/news/reject.do";
						$.post(url, {
							'xnews_id' : xnews_id.toString(),
							'xcomment' : xcomment
						}, function(result) {
							if (result == "success") {
								alert("拒绝成功！");
							} else {
								alert("拒绝失败！");
							}
							jQuery("#news").trigger("reloadGrid");
							_rnd.dialog("destroy");
						});
					}
				}
			}
		});
	}
}
