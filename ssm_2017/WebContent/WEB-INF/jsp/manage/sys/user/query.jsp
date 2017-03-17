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

<body>
<div class="page-content">
	<div class="page-header">
		<ul class="breadcrumb">
			<li>
				<i class="icon-home home-icon"></i>
				<a href="#" onclick="openFrame('百度','${ctx }/user/add/forward',500,400);" >首页</a>
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
$(function(){
	pageInit();
});
function pageInit(){
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	
	jQuery(grid_selector).jqGrid({
	      url : '${ctx}/user/getPage',
	      datatype : "json",
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
	                   {name: 'flag', label: '操作', width: 150, align: 'center',formatter: 
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
	      caption : "表格名称",
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
function operate(value,rowData,rowIndex){
	var html = [];
	html.push("<a href='#' onclick='show(\""+rowData.id+"\")'>查看</a>");
	html.push("<a href='#' onclick='show(\""+rowData.id+"\")'>编辑</a>");
	html.push("<a href='#' onclick='show(\""+rowData.id+"\")'>删除</a>");
	return html.join("&nbsp;|&nbsp;");
}


/*****************************************         add方法        ****************************************************/
function formSub(){
	 closeFrame();
}
</script>
</body>
</html>

