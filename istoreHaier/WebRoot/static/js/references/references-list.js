$(document).ready(function() {
	var _href = location.href;
	var index = _href.indexOf("xchannelId");
	var xchannel_id = _href.substring(index + 11, index + 12);
	
	jQuery("#references").jqGrid({
		url: '/istore/servlet/b2b/references/json.do?xchannel_id=' + xchannel_id,
		datatype: "json",
		colNames: ['样板工程编号','序列表', '创建时间', '作者', '所属频道', '优先级', '前台用户', '状态编号', '状态', '查看', '通过', '拒绝'],
		colModel: [{
			name: 'XREFERENCES_ID',
			index: 'XREFERENCES_ID',
			width: 9,
			hidden: true,
			align: "center"
		},
		{
			name: 'SERIALNUMBER',
			index: 'SERIALNUMBER',
			width: 9,
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
			width: 9,
			align: "center"
		},
		{
			name: 'XCHANNEL_ID',
			index: 'XCHANNEL_ID',
			width: 9,
			align: "center"
		},
		{
			name: 'SEQ',
			index: 'SEQ',
			width: 9,
			align: "center"
		},
		{
			name: 'XGROUP_ID',
			index: 'XGROUP_ID',
			width: 9,
			align: "center"
		},
		{
			name: 'STATUS_ID',
			index: 'STATUS_ID',
			width: 9,
			align: "center",
			hidden: true
		},
		{
			name: 'STATUS_NAME',
			index: 'STATUS_NAME',
			width: 9,
			align: "center",
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
		pager: '#references_pager',
		sortname: 'CREATETIME',
		viewrecords: true,
		sortorder: "DESC",
		autowidth: true,
		height: 'auto',
		multiselect: true,
		rownumbers: true,
		caption: false,
	});
	
	jQuery("#references").jqGrid('navGrid', '#references_pager', {
		add: false,
		del: false,
		edit: false,
		search: false
	});
	

	//高级搜索
	$("#references").jqGrid('navButtonAdd', "#references_pager", {
		caption: "",
		position: "first",
		title: "搜索",
		buttonicon: "ui-icon-search",
		onClickButton: function() {
			var _srd = $("#search-references-dialog");
			_srd.dialog({
				modal: true,
				resizable:false,
				width: 900,
				height: 'auto',
				title:"搜索",
				zIndex: 9999,
				close: function(event, ui) {
					_srd.dialog("destroy");
				},
				buttons: {
					"搜索": function() {
						var serialnumber = $("#search_serialnumber").val();
						var country = $("#search_country").val();
						var projectplace = $("#search_projectplace").val();
						var description = $("#search_description").val();
						var type = $("#search_type").val();
						var installtime = $("#search_installtime").val();
						var installseries = $("#search_installseries").val();
						var installdetails = $("#search_installdetails").val();
						var totalcapacity = $("#search_totalcapacity").val();
						var keycapacity = $("#search_keycapacity").val();
						if(description == null){
							description = "";
						}
						if(installdetails == null){
							installdetails = "";
						}
						if(keycapacity == null){
							keycapacity = "";
						}
						
						var url = "/istore/servlet/b2b/references/search.do";
						jQuery("#references").jqGrid('setGridParam',{
							url: url,
							postData:{
								serialnumber: serialnumber,
								country: country,
								projectplace: projectplace,
								description: description,
								type: type,
								installtime: installtime,
								installseries: installseries,
								installdetails: installdetails,
								totalcapacity: totalcapacity,
								keycapacity: keycapacity,
								xchannel_id: xchannel_id
							},
							page: 1
						}).trigger("reloadGrid");
						_srd.dialog("destroy");
					}
				}
			});
		}
	});
	
	//样板工程提交审核
	jQuery("#references").jqGrid('navButtonAdd', "#references_pager", {
		caption: "",
		position: "first",
		title: "样板工程提交审核",
		buttonicon: "ui-icon-key",
		onClickButton: function() {
			var row = jQuery("#references").jqGrid("getGridParam","selarrrow");
			if(row == null || row == ""){
				alert("请选择一条样板工程!");
			}else{
				var xreferences_id= $("#references").getCell(row, "XREFERENCES_ID");
				var status = $("#references").getCell(row, "STATUS_ID");
				if(status == "W"){
					alert("该样板工程正在审核中......");
				}else if(status == "P"){
					alert("样板工程已经发布，不用提交审核");
				}
				else if(status == "R"){
					var url = "/istore/servlet/b2b/references/rejectcase.do";
					$.post(url, {'xreferences_id':xreferences_id.toString()}, function(result){
						var _rcd = $("#reject-case-dialog");
						_rcd.html(result).dialog({
							modal: true,
							resizable:false,
							width: 300,
							height: 'auto',
							title:"样板工程被拒绝的原因：",
							close: function(event, ui){
								_end.dialog("destroy");
							},
							buttons: {
								"确认": function(){
									_rcd.dialog("destroy");
									jQuery("#references").trigger("reloadGrid");
								}
							}
						});
					});
				}else{
					if(confirm("确定提交审核吗？")){
						var url = "/istore/servlet/b2b/references/refer.do";
						$.post(url, {'xreferences_id':xreferences_id.toString()}, function(result){
							if(result == "success"){
								alert("提交成功！");
							}
							else{
								alert("提交失败！");
							}
							jQuery("#references").trigger("reloadGrid");
						});
					}
				}
			}
		}
	});
	
	//删除样板工程
	jQuery("#references").jqGrid('navButtonAdd', "#references_pager", {
		caption: "",
		position: "first",
		title: "新闻删除",
		buttonicon: "ui-icon-trash",
		onClickButton: function() {
			var row = jQuery("#references").jqGrid("getGridParam","selarrrow");
			if(row == null || row == ""){
				alert("请选择一条样板工程!");
			}else{
				if(confirm("确定删除吗？")){
					var xreferences_id= $("#references").getCell(row, "XREFERENCES_ID");
					var url = "/istore/servlet/b2b/references/delete.do";
					$.post(url, {'xreferences_id':xreferences_id.toString()}, function(result){
						if(result == "success"){
							alert("删除成功！");
						}
						else{
							alert("修改失败！");
						}
						jQuery("#references").trigger("reloadGrid");
					});
				}
			}
		}
	});
	
	//样板工程编辑
	jQuery("#references").jqGrid('navButtonAdd', "#references_pager", {
		caption: "",
		position: "first",
		title: "新闻编辑",
		buttonicon: "ui-icon-pencil",
		onClickButton: function() {
			var row = jQuery("#references").jqGrid("getGridParam","selarrrow");
			if(row == null || row == ""){
				alert("请选择一条样板工程!");
			}else{
				var xreferences_id = $("#references").getCell(row, "XREFERENCES_ID");
				var url = "/istore/servlet/b2b/references/editlist.do";
				$.post(url, {'xreferences_id':xreferences_id.toString()}, function(result){
					var editor;
					var _erd = $("#edit-references-dialog");
					_erd.html(result).dialog({
						modal: true,
						resizable:false,
						width: 900,
						height: 500,
						title:"样板工程编辑",
						open: function(event, ui){
							editor = KindEditor.create('#edit_pictures', {
								resizeType : 1,
								uploadJson: '/istore/servlet/b2b/references/upload.do'
							});
						},
						beforeClose: function(event, ui){
							KindEditor.remove('#edit_pictures');
						},
						close: function(event, ui){
							_erd.dialog("destroy");
						},
						buttons: {
							"修改": function(){
								var serialnumber = $("#edit_serialnumber").val();
								var country = $("#edit_country").val();
								var projectplace = $("#edit_projectplace").val();
								var description = $("#edit_description").val();
								var type = $("#edit_type").val();
								var installtime = $("#edit_installtime").val();
								var installseries = $("#edit_installseries").val();
								var installdetails = $("textarea[name='edit_installdetails']").val();
								var totalcapacity = $("#edit_totalcapacity").val();
								var keycapacity = $("textarea[name='edit_keycapacity']").val();
								var seq = $("#edit_seq").val();
								var group = "";
								$("input[name='edit_group']").each(function(){
									if($(this).attr("checked") == "checked"){
										group += $(this).val();
									}
								});
								var catgroup = $("#edit_catgroup").val();
								var pictures = editor.html();
								if(serialnumber == ""){
									alert("请输入序列表！");
									$("#edit_serialnumber").focus();
								}
								else if(country == ""){
									alert("请输入国家！");
									$("#edit_country").focus();
								}
								else if(projectplace == ""){
									alert("请输入工程名称或所在地！");
									$("#edit_projectplace").focus();
								}
								else if(description == ""){
									alert("请输入描述！");
									$("#edit_description").focus();
								}
								else if(type == ""){
									alert("请输入类型！");
									$("#edit_type").focus();
								}
								else if(installtime == ""){
									alert("请输入安装时间！");
									$("#edit_installtime").focus();
								}
								else if(installseries == ""){
									alert("请输入安装的产品系列！");
									$("#edit_installseries").focus();
								}
								else if(installdetails == ""){
									alert("请输入安装的详细信息！");
									$("#edit_installdetails").focus();
								}
								else if(totalcapacity == ""){
									alert("请输入装机容量！");
									$("#edit_totalcapacity").focus();
								}
								else if(keycapacity == ""){
									alert("请输入核心竞争指标！");
									$("#edit_keycapacity").focus();
								}
								else if(seq == ""){
									alert("请输入优先级！");
									$("#edit_seq").focus();
								}
								else if(pictures == ""){
									alert("请输入样板工程图片！");
								}
								else if(group == "" || group == null){
									alert("请选择群组！");
								}
								else if(catgroup == 0){
									alert("请选择关联目录！");
								}
								else{
									var url = "/istore/servlet/b2b/references/edit.do";
									$.post(
										url, 
										{
											'xreferences_id':xreferences_id.toString(), 
											'serialnumber':serialnumber, 
											'country':country, 
											'projectplace':projectplace, 
											'description':description, 
											'type':type, 
											'installtime':installtime, 
											'installseries':installseries, 
											'installdetails':installdetails, 
											'totalcapacity':totalcapacity, 
											'keycapacity':keycapacity, 
											'seq':seq, 
											'pictures':pictures, 
											'group':group, 
											'catgroup':catgroup, 
										}, 
										function(result){
										if(result == "success"){
											alert("修改成功！");
										}
										else{
											alert("修改失败！");
										}
										KindEditor.remove('#edit_pictures');
										_erd.dialog("destroy");
										jQuery("#references").trigger("reloadGrid");
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
	jQuery("#references").jqGrid('navButtonAdd', "#references_pager", {
		caption: "",
		position: "first",
		title: "新建频道",
		buttonicon: "ui-icon-plusthick",
		onClickButton: function() {
			var editor;
			var _ard = $("#add-references-dialog");
			_ard.dialog({
				modal: true,
				resizable:false,
				width: 900,
				height: 500,
				title:"添加样板工程",
				open: function(event, ui){
					editor = KindEditor.create('#add_pictures', {
						allowFileManager: true,
						resizeType : 1,
						uploadJson: '/istore/servlet/b2b/references/upload.do'
					});
				},
				beforeClose: function(event, ui){
					KindEditor.remove('#add_pictures');
				},
				close: function(event, ui) {
					editor.destroy();
					_ard.dialog("destroy");
				},
				buttons: {
					"提交": function() {
						var serialnumber = $("#add_serialnumber").val();
						var country = $("#add_country").val();
						var projectplace = $("#add_projectplace").val();
						var description = $("#add_description").val();
						var type = $("#add_type").val();
						var installtime = $("#add_installtime").val();
						var installseries = $("#add_installseries").val();
						var installdetails = $("textarea[name='add_installdetails']").val();
						var totalcapacity = $("#add_totalcapacity").val();
						var keycapacity = $("textarea[name='add_keycapacity']").val();
						var seq = $("#add_seq").val();
						var group = "";
						$("input[name='add_group']").each(function(){
							if($(this).attr("checked") == "checked"){
								group += $(this).val();
							}
						});
						var catgroup = $("#add_catgroup").val();
						var pictures = editor.html();
						if(serialnumber == ""){
							alert("请输入序列表！");
							$("#add_serialnumber").focus();
						}
						else if(country == ""){
							alert("请输入国家！");
							$("#add_country").focus();
						}
						else if(projectplace == ""){
							alert("请输入工程名称或所在地！");
							$("#add_projectplace").focus();
						}
						else if(description == ""){
							alert("请输入描述！");
							$("#add_description").focus();
						}
						else if(type == ""){
							alert("请输入类型！");
							$("#add_type").focus();
						}
						else if(installtime == ""){
							alert("请输入安装时间！");
							$("#add_installtime").focus();
						}
						else if(installseries == ""){
							alert("请输入安装的产品系列！");
							$("#add_installseries").focus();
						}
						else if(installdetails == ""){
							alert("请输入安装的详细信息！");
							$("#add_installdetails").focus();
						}
						else if(totalcapacity == ""){
							alert("请输入装机容量！");
							$("#add_totalcapacity").focus();
						}
						else if(keycapacity == ""){
							alert("请输入核心竞争指标！");
							$("#add_keycapacity").focus();
						}
						else if(seq == ""){
							alert("请输入优先级！");
							$("#add_seq").focus();
						}
						else if(pictures == ""){
							alert("请输入样板工程图片！");
						}
						else if(group == "" || group == null){
							alert("请选择群组！");
						}
						else if(catgroup == 0){
							alert("请选择关联目录！");
						}
						else{
							var url = "/istore/servlet/b2b/references/add.do";
							$.post(
								url, 
								{
									'serialnumber':serialnumber, 
									'country':country, 
									'projectplace':projectplace, 
									'description':description, 
									'type':type, 
									'installtime':installtime, 
									'installseries':installseries, 
									'installdetails':installdetails, 
									'totalcapacity':totalcapacity, 
									'keycapacity':keycapacity, 
									'seq':seq, 
									'pictures':pictures, 
									'group':group, 
									'catgroup':catgroup, 
									'xchannel_id':xchannel_id
								}, 
								function(result){
								if(result == "success"){
									alert("添加成功！");
								}
								else{
									alert("添加失败！");
								}
								KindEditor.remove('#add_pictures');
								_ard.dialog("destroy");
								jQuery("#references").trigger("reloadGrid");
							});				
						}
					}
				}
			});
		}
	});
});
