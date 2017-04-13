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
			<li>
				<a href="#" onclick="openFrame('百度','${ctx }/user/add/forward',600,400);" ><i class="icon-legal home-icon">添加</i></a>
			</li>
		</ul><!-- .breadcrumb -->
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
$(function(){
	//queryData.loginname='lijunfeng';
	pageInit();
	layui.use(['layer'], function(){layer = layui.layer;});
});

function pageReload(){
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	jQuery(grid_selector).jqGrid("clearGridData");
	jQuery(grid_selector).jqGrid("setGridParam", { postData: queryData });
	jQuery(grid_selector).trigger("reloadGrid");
//	jQuery(grid_selector).jqGrid("setGridParam", { postData: queryData }).trigger("reloadGrid");
}
function pageInit(){
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	
	jQuery(grid_selector).jqGrid({
	      url : '${ctx}/user/getPage',
	      datatype : "json",
	      postData : queryData,
	      mtype:'POST',
	      colModel : [ 
	                   {name : 'id',label: '操作', width : 55,sortable : false, align : 'center'}, 
	                   {name : 'name',label: '操作', width : 100, sortable : false, align : 'center'}, 
	                   {name : 'id',label: '操作', width : 55, sortable : false, align : 'center'}, 
	                   {name : 'name',label: '操作', width : 100, sortable : false, align : 'center'}, 
	                   {name : 'id',label: '操作', width : 55, sortable : false, align : 'center'}, 
	                   {name : 'name',label: '操作', width : 100, sortable : false, align : 'center'}, 
	                   {name : 'id',label: '操作', width : 55, sortable : false, align : 'center'}, 
	                   {name : 'name',label: '操作', width : 100, sortable : false, align : 'center'},
	                   {name: 'flag', label: '操作', width: 250, align: 'center',formatter: 
	                	   function (cellvalue, options, rowObject) {return operate(cellvalue, options, rowObject);}},
	                 ],
	      rowNum : 10,
	      rowList : [ 10, 20, 30 ],
	      pager : pager_selector,
	      sortname : 'id',
	      viewrecords : true,
	      autowidth: true,
	      sortorder : "desc",
	      rownumbers : true,
	      //multiselect: true,
	      //multiboxonly: true,
	      //viewrecords : true,
	      //emptyrecords : "暂无数据",
	      caption : "用户管理",
	      height: 'auto',
	      loadComplete : function() {
	    	  $(grid_selector).setGridHeight($(window).height() - 290);  
				var w2 = parseInt($('.ui-jqgrid-labels>th:eq(2)').css('width'))-3;  
					$('.ui-jqgrid-lables>th:eq(2)').css('width',w2);  
					$('#grid-table tr').find("td:eq(2)").each(function(){  
					$(this).css('width',w2);  
				});  
				var table = this;
				setTimeout(function(){
					updatePagerIcons(table);
				}, 0);
			},
	    });
}	
function operate(cellvalue, options, rowObject){
	var html = [];
	html.push("<a href='#' class='icon-eye-open' onclick='show(\""+rowObject.id+"\")'>查看</a>");
	html.push("<a href='#' class='icon-edit' onclick='edit(\""+rowObject.id+"\")'>编辑</a>");
	html.push("<a href='#' class='icon-undo' onclick='resetPassword(\""+rowObject.id+"\")'>重置密码</a>");
	if (rowObject.isdelete == '0') {
		html.push("<a href='#' class='icon-unlock' onclick='lock(\""+rowObject.id+"\",\"锁定\")'>锁定</a>");
	} else {
		html.push("<a href='#' class='icon-lock' onclick='lock(\""+rowObject.id+"\",\"解锁\")'>解锁</a>");
	}
	return html.join("&nbsp;|&nbsp;");
}
function show(id){
	openFrame('百度','${ctx }/user/show/forward',600,400);
}
function edit(id){
	openFrame('百度','${ctx }/user/add/forward?id=' + id,600,400);
}
function resetPassword(id){
	$.post('${ctx}/user/resetPassword',{id:id},function(data){
		if (data.result) {
			layer.msg("重置成功");
		} else {
			layer.msg("重置失败");
		}
	});
}
function lock(id,open){
	$.post('${ctx}/user/lock',{id:id,open:open},function(data){
		if (data.result) {
			layer.msg(open + "成功");
			pageReload();
		} else {
			layer.msg(open + "失败");
		}
	});
}

</script>
</body>
</html>

