 $.blockUI({ message: '<h3> 正在生成目录文件的静态页面...</h3>' });	
 
  background: gray; /* standard */
 
 background: pink9; /* IE 8 and below */
 
 *background: green; /* IE 7 and below */
 
 _background: blue; /* IE 6 */

http://envato.stammtec.de/themeforest/grape/img


Manipulating/Grid Data--- delete/Add
Advanced/Multi Select  Checkbox
        /Master Detail  two table action
        /Search Big Sets  ext search
        Search Big Sets 自定义搜索
New since beta 3.0        After Load Callback   字体变色
New in version 3.2       After Insert Row event
Custom Multi Select
New since beta 3.0  After Load Callback  red 
New in version 3.5  Summary Footer Row统计
New in version 3.1 Post Data  remove
New in version 3.2 Custom Button and Forms 
New in version 3.3 Dynamic height and width
New in version 3.5 Summary Footer Row统计

 {name:'id',index:'id', width:90, sorttype:"int", editable: true}, {name:'name',index:'name', width:150,
 editable: true,editoptions:{size:"20",maxlength:"30"}}, {name:'stock',index:'stock', width:60, 
 
 editable: true,
 edittype:"checkbox",
 editoptions: {value:"Yes:No"}},
 
  {name:'ship',index:'ship', width:90, editable:
  true,edittype:"select",editoptions:{value:"FE:FedEx;IN:InTime;TN:TNT;AR:ARAMEX"}}, {name:'note',index:'note', width:200, sortable:false,editable: true,
 edittype:"textarea", editoptions:{rows:"2",cols:"10"}}
 
 
 
 
 //UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
 
 
 <!--spring security页面运用 start-->
 <#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
 <@security.authorize ifAnyGranted="ROLE_ADMIN">

     Hello admin!

</@security.authorize>
 <!--spring security页面运用 end-->


Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate("");
		FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
 
 
 <#if pagination.total==0>
	{
	    "total": 0,
	    "page": 1,
	    "rows": []
	}
<#else>
	{
	    "total": ${pagination.total},
	    "page": ${pagination.page},
	    "rows": [
	    	<#list pagination.object as role>
	    		{
				    "id"  : ${role.id},
				    "cell": [
				             "${role.id}",
				             "${role.name}",
				             "${role.department.name}",
				             "${role.description}",
				             "${role.createTime}",
				             "${role.lastUpdate}"
				       	    ]
		        }
	    		<#if role_has_next>
		        ,
	    		</#if>
    		</#list>
	    ]
	}
</#if>
 