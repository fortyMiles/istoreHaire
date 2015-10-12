$(document).ready(function() {
	var _href = location.href;
	var index = _href.indexOf("xchannelId");
	var xchannel_id = _href.substring(index + 11, index + 12);
	
	jQuery("#news").jqGrid({
		url: '/istore/servlet/b2b/news/json.do?xchannel_id=' + xchannel_id,
		datatype: "json",
		colNames: ['新闻编号','标题', '创建时间', '作者', '所属频道', '优先级', '前台用户', '状态编号', '状态', '查看', '通过', '拒绝'],
		colModel: [{
			name: 'XNEWS_ID',
			index: 'XNEWS_ID',
			width: 10,
			hidden: true,
			align: "center"
		},
		{
			name: 'TITLE',
			index: 'TITLE',
			width: 13,
			align: "center"
		},
		{
			name: 'CREATETIME',
			index: 'CREATETIME',
			width: 13,
			align: "center"
		},
		{
			name: 'AUTHOR',
			index: 'AUTHOR',
			width: 10,
			align: "center"
		},
		{
			name: 'XCHANNEL_ID',
			index: 'XCHANNEL_ID',
			width: 10,
			align: "center"
		},
		{
			name: 'SEQ',
			index: 'SEQ',
			width: 10,
			align: "center"
		},
		{
			name: 'XGROUP_ID',
			index: 'XGROUP_ID',
			width: 10,
			align: "center"
		},
		{
			name: 'STATUS_ID',
			index: 'STATUS_ID',
			width: 10,
			align: "center",
			hidden: true
		},
		{
			name: 'STATUS_NAME',
			index: 'STATUS_NAME',
			width: 10,
			align: "center"
		},
		{
			name: 'VIEW',
			index: 'VIEW',
			width: 8,
			align: "center",
			sortable: false
		},
		{
			name: 'APPROVE',
			index: 'APPROVE',
			width: 8,
			align: "center",
			sortable: false
		},
		{
			name: 'REJECT',
			index: 'REJECT',
			width: 8,
			align: "center",
			sortable: false
		}],
		rowNum: 10,
		rowList: [10,12,15],
		pager: '#news_pager',
		sortname: 'CREATETIME',
		viewrecords: true,
		sortorder: "DESC",
		autowidth: true,
		height: 'auto',
		multiselect: true,
		rownumbers: true,
		caption: false,
	});
	
	jQuery("#news").jqGrid('navGrid', '#news_pager', {
		add: false,
		del: false,
		edit: false,
		search: false
	});
	

	//高级搜索
	$("#news").jqGrid('navButtonAdd', "#news_pager", {
		caption: "",
		position: "first",
		title: "搜索",
		buttonicon: "ui-icon-search",
		onClickButton: function() {
			var _snd = $("#search-news-dialog");
			_snd.dialog({
				modal: true,
				resizable:false,
				width: 400,
				height: 'auto',
				title:"搜索",
				close: function(event, ui) {
					_snd.dialog("destroy");
				},
				buttons: {
					"搜索": function() {
						var title = $("#search_title").val();
						var details = $("#search_details").val();
						
						var url = "/istore/servlet/b2b/news/search.do";
						jQuery("#news").jqGrid('setGridParam',{
							url: url,
							postData:{
								title: title,
								details: details,
								xchannel_id: xchannel_id
							},
							page: 1
						}).trigger("reloadGrid");
						_snd.dialog("destroy");
					}
				}
			});
		}
	});
	
	//提交新闻审核
	jQuery("#news").jqGrid('navButtonAdd', "#news_pager", {
		caption: "",
		position: "first",
		title: "新闻提交审核或查看拒绝原因",
		buttonicon: "ui-icon-key",
		onClickButton: function() {
			var row = jQuery("#news").jqGrid("getGridParam","selarrrow");
			if(row == null || row == ""){
				alert("请选择一条新闻!");
			}else{
				var xnews_id= $("#news").getCell(row, "XNEWS_ID");
				var status = $("#news").getCell(row, "STATUS_ID");
				if(status == "W"){
					alert("该新闻正在审核中......");
				}else if(status == "P"){
					alert("新闻已经发布，不用提交审核");
				}
				else if(status == "R"){
					var url = "/istore/servlet/b2b/news/rejectcase.do";
					$.post(url, {'xnews_id':xnews_id.toString()}, function(result){
						var _rcd = $("#reject-case-dialog");
						_rcd.html(result).dialog({
							modal: true,
							resizable:false,
							width: 300,
							height: 'auto',
							title:"新闻被拒绝的原因：",
							close: function(event, ui){
								_end.dialog("destroy");
							},
							buttons: {
								"确认": function(){
									_rcd.dialog("destroy");
									jQuery("#news").trigger("reloadGrid");
								}
							}
						});
					});
				}else{
					if(confirm("确定提交审核吗？")){
						var url = "/istore/servlet/b2b/news/refer.do";
						$.post(url, {'xnews_id':xnews_id.toString()}, function(result){
							if(result == "success"){
								alert("提交成功！");
							}
							else{
								alert("提交失败！");
							}
							jQuery("#news").trigger("reloadGrid");
						});
					}
				}
			}
		}
	});
	
	//删除新闻
	jQuery("#news").jqGrid('navButtonAdd', "#news_pager", {
		caption: "",
		position: "first",
		title: "新闻删除",
		buttonicon: "ui-icon-trash",
		onClickButton: function() {
			var row = jQuery("#news").jqGrid("getGridParam","selarrrow");
			if(row == null || row == ""){
				alert("请选择一条新闻!");
			}else{
				if(confirm("确定删除吗？")){
					var xnews_id= $("#news").getCell(row, "XNEWS_ID");
					var url = "/istore/servlet/b2b/news/delete.do";
					$.post(url, {'xnews_id':xnews_id.toString()}, function(result){
						if(result == "success"){
							alert("删除成功！");
						}
						else{
							alert("修改失败！");
						}
						jQuery("#news").trigger("reloadGrid");
					});
				}
			}
		}
	});
	
	//新闻编辑
	jQuery("#news").jqGrid('navButtonAdd', "#news_pager", {
		caption: "",
		position: "first",
		title: "新闻编辑",
		buttonicon: "ui-icon-pencil",
		onClickButton: function() {
			var row = jQuery("#news").jqGrid("getGridParam","selarrrow");
			if(row == null || row == ""){
				alert("请选择一条新闻!");
			}else{
				var xnews_id = $("#news").getCell(row, "XNEWS_ID");
				var url = "/istore/servlet/b2b/news/editlist.do";
				$.post(url, {'xnews_id':xnews_id.toString()}, function(result){
					var editor;
					var _end = $("#edit-news-dialog");
					_end.html(result).dialog({
						modal: true,
						resizable:false,
						width: 900,
						height: 'auto',
						title:"频道编辑",
						open: function(event, ui){
							editor = KindEditor.create('#edit_details', {
								resizeType : 1,
								uploadJson: '/istore/servlet/b2b/news/upload.do'
							});
						},
						beforeClose: function(event, ui){
							KindEditor.remove('#edit_details');
						},
						close: function(event, ui){
							_end.dialog("destroy");
						},
						buttons: {
							"修改": function(){
								var title = $("#edit_title").val();
								var seq = $("#edit_seq").val();
								var group = "";
								$("input[name='edit_group']").each(function(){
									if($(this).attr("checked") == "checked"){
										group += $(this).val();
									}
								});
								var details = editor.html();
								if(title == "" || title == null){
									alert("请输入新闻标题！");
									$("#edit_title").focus();
								}
								else if(seq == "" || seq == null){
									alert("请输入优先级！");
									$("#edit_seq").focus();
								}
								else if(group == "" || group == null){
									alert("请输入优先级！");
								}
								else if(details == "" || details == null){
									alert("请输入新闻内容！");
								}
								else{
									var url = "/istore/servlet/b2b/news/edit.do";
									$.post(url, {'xnews_id':xnews_id.toString(), 'title':title, 'seq':seq, 'details':details, 'group':group}, function(result){
										if(result == "success"){
											alert("修改成功！");
										}
										else{
											alert("修改失败！");
										}
										KindEditor.remove('#edit_details');
										_end.dialog("destroy");
										jQuery("#news").trigger("reloadGrid");
									});					
								}
							}
						}
					});
				});
			}
		}
	});
	
	//新建新闻
	jQuery("#news").jqGrid('navButtonAdd', "#news_pager", {
		caption: "",
		position: "first",
		title: "新建频道",
		buttonicon: "ui-icon-plusthick",
		onClickButton: function() {
			var editor;
			var _and = $("#add-news-dialog");
			_and.dialog({
				modal: true,
				resizable:false,
				width: 900,
				height: 'auto',
				title:"添加新闻",
				open: function(event, ui){
					editor = KindEditor.create('#add_details', {
						allowFileManager: true,
						resizeType : 1,
						uploadJson: '/istore/servlet/b2b/news/upload.do'
					});
				},
				beforeClose: function(event, ui){
					KindEditor.remove('#add_details');
				},
				close: function(event, ui) {
					_and.dialog("destroy");
				},
				buttons: {
					"提交": function() {
						var title = $("#add_title").val();
						var seq = $("#add_seq").val();
						var group = "";
						$("input[name='add_group']").each(function(){
							if($(this).attr("checked") == "checked"){
								group += $(this).val();
							}
						});
						var details = editor.html();
						if(title == "" || title == null){
							alert("请输入新闻标题！");
							$("#add_title").focus();
						}
						else if(seq == "" || seq == null){
							alert("请输入优先级！");
							$("#add_seq").focus();
						}
						else if(group == "" || group == null){
							alert("请输入优先级！");
						}
						else if(details == "" || details == null){
							alert("请输入新闻内容！");
						}
						else{
							var url = "/istore/servlet/b2b/news/add.do";
							$.post(url, {'title':title, 'seq':seq, 'details':details, 'group':group, 'xchannel_id':xchannel_id}, function(result){
								if(result == "success"){
									alert("添加成功！");
								}
								else{
									alert("添加失败！");
								}
								KindEditor.remove('#add_details');
								_and.dialog("destroy");
								jQuery("#news").trigger("reloadGrid");
							});				
						}
					}
				}
			});
		}
	});
});
