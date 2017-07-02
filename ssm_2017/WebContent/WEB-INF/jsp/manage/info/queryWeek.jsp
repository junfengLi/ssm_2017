<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Bootstrap控制台</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body style="overflow-x:hidden;">
<div class="page-content">
	<div class="page-header">
		<ul class="breadcrumb">
		</ul><!-- .breadcrumb -->
		<div style="float:right;">
			<table  class="">
				<tr>
					<td>
					<a href="###" class="menu-text" onclick="doExportMonth();">
						<span class="menu-text"> <i class=" icon-cloud-download"></i>按月导出 </span>
					</a>
					</td>
					<td >
					<div class="tabs-name on" data-type="weeksearch" >按周统计</div>
					<div class="tabs-name"  data-type="monthsearch" >按月统计</div>
					</td>
					<td style="width:190px;" >
						<div class="widget-toolbar" id="weeksearch">
							<input class="form-control input-small date-picker" id="id-date-picker-1" type="text" name="starttime" style="    margin-top: 2px;float: left; display: block;" />
							<span class="input-group-addon" style="float: left;height: 34px; margin-top: 2px;line-height: 23px; width:35px;"><i class="icon-calendar bigger-110"></i></span>
							<span class="input-group-addon clearDate"  style="float: left; margin-top: 2px;height: 34px;line-height: 23px;width:35px;"><i class="icon-remove"></i></span>
						</div>
						<div class="widget-toolbar hide" id="monthsearch">
							<input class="form-control input-small date-picker" id="id-date-picker-2" type="text" name="starttime" style="    margin-top: 2px;float: left; display: block;" />
							<span class="input-group-addon" style="float: left;height: 34px; margin-top: 2px;line-height: 23px; width:35px;"><i class="icon-calendar bigger-110"></i></span>
							<span class="input-group-addon clearDate"  style="float: left; margin-top: 2px;height: 34px;line-height: 23px;width:35px;"><i class="icon-remove"></i></span>
						</div>					
					</td>
					<td style="width:20px; cursor: pointer;" onclick="searchWeekMonth();"><i class="icon-search nav-search-icon bigger-160"></i></td>
				</tr>
			</table>
		</div>
	</div><!-- /.page-header -->

	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
			<!-- PAGE CONTENT ENDS -->
		</div><!-- /.col -->
	</div><!-- /.row -->
</div><!-- /.page-content -->

<script type="text/javascript">
var layer = null;
var queryData = {};
var datatype='weeksearch';
$(function(){
	$('#id-date-picker-1').datepicker({dateFormat: "yy-mm-dd",autoclose:true});
	$('#id-date-picker-2').datetimepicker({language: "zh-CN",format: "yyyy-mm",autoclose:true,
		startView: 3, //这里就设置了默认视图为年视图
        minView: 3, //设置最小视图为年视图
	});
	queryData.userid='${userid}';
	queryData.page = 1;
	pageInit();
	layui.use(['layer'], function(){layer = layui.layer;});
	$(".tabs-name").click(function(){
		$(this).toggleClass("on");
		$(this).siblings().toggleClass("on");
		datatype = $(this).attr("data-type");
		$("#" + datatype).toggleClass("hide");;
		$("#" + datatype).siblings().toggleClass("hide");;
	});
});

$(".page-content").resize(function(){
	var grid_selector = "#grid-table";
	jQuery(grid_selector).setGridWidth($(".col-xs-12").width());
});

function doExportMonth() {
	if (datatype=='monthsearch') {
		var monthtime=$("#id-date-picker-2").val();
		if (monthtime=='')
			layer.alert("请选择月份");
		else {
			window.open("${ctx}/info/doExport?monthtime="+ monthtime);   
		}
	} else {
		layer.alert("请选择月份");
	}
}

function searchWeekMonth(){
	
	if (datatype=='weeksearch') {
		var weektime=$("#id-date-picker-1").val();
		queryData.weektime=weektime;
		queryData.monthtime='';
	} else {
		var monthtime=$("#id-date-picker-2").val();
		queryData.monthtime=monthtime;
		queryData.weektime='';
	}
	pageReload();
}

function pageReload(){
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	jQuery(grid_selector).jqGrid("setGridParam", { postData: queryData });
	jQuery(grid_selector).trigger("reloadGrid");
}
function pageInit(){
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	
	jQuery(grid_selector).jqGrid({
	      url : '${ctx}/info/getPageForWeek',
	      datatype : "json",
	      postData : queryData,
	      rownumbers: true,
	      mtype:'POST',
	      colModel : [ 
	                   {name : 'name',label: '姓名', width : 60,sortable : false, align : 'center'}, 
	                   {name : 'projectname',label: '项目名称', width : 500,sortable : false, align : 'center'}, 
	                   {name : 'mobile',label: '电话', width : 100, sortable : false, align : 'center'}
	                 ],
	      rowNum : 20,
	      rowList : [ 20, 30, 50],
	      pager : pager_selector,
	      sortname : 'id',
	      viewrecords : true,
	      autowidth: true,
	      sortorder : "desc",
	      rownumbers : true,
	      emptyrecords : "暂无数据",
	      caption : "统计",
	      height: 'auto',
	      loadComplete : function() {
	    	  $(grid_selector).setGridParam().hideCol("isrefuse").trigger("reloadGrid");
	    	  $(grid_selector).setGridHeight($(window).height() - 290);  
				var w2 = parseInt($('.ui-jqgrid-labels>th:eq(0)').css('width'))-3;  
				$('.ui-jqgrid-lables>th:eq(0)').css('width',w2);  
				$('#grid-table tr').find("td:eq(0)").each(function(){  
					$(this).css('width',w2);  
				});
				var table = this;
				setTimeout(function(){
					updatePagerIcons(table);
				}, 0);
			},
			rowattr: function (rd) { //grid配置  
				if (rd.isrefuse == '2') {  
	                return {"style": "color:#FF974B;"};  
	            } else if (rd.isrefuse == '1') {  
	                return {"style": "color:#b9b9b9;"};  
	            } else {
	            	 if (rd.backtime != '' && rd.backtime != undefined) {  
	 	                return {"style": "color:#00bf00;"};  
	 	            }  
	            	return {"style": "color:black;"};  
	            } 
	            return null;  
	        },
	        recordtext:"第 {0} - {1} 条，共 {2} 条",
	    });
}	

function updatePagerIcons(table) {
	var replacement = 
	{
		'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
		'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
		'ui-icon-seek-next' : 'icon-angle-right bigger-140',
		'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
	};
	//$("#grid-pager_right div").hide();
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
		var icon = $(this);
		var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
		
		if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
	})
}


</script>
</body>
</html>

