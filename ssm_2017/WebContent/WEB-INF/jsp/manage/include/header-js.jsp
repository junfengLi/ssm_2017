<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx }/static/layui/layui.js"></script>
<script>
layui.use(['layer', 'form'], function(){
  var layer = layui.layer
  ,form = layui.form();
});
function closeFrame(){
	layui.use(['layer', 'form'], function(){
		var layer = layui.layer;
		layer.closeAll();
	});
}
function openFrame(title,url,width,height){
	layui.use(['layer', 'form'], function(){
	  var layer = layui.layer;
	  layer.open({
		  title:title,
			type: 2,
			shade: [0.8, '#393D49'],
			area : [width+'px' , height+'px'],
			maxmin: true,
			content: url,
	        btn: ['提交', '关闭'],
	        zIndex: layer.zIndex,
	        yes: function(){
	        	try {  
					if(typeof(eval(formSub))=="function"){formSub();}
				} catch(e){} 
	        },
	        btn2: function(){
	        	layer.close(layer.zIndex);
	        }
	      });
	});
}
</script> 

<!-- ace settings handler -->
<script type="text/javascript" src="${ctx }/static/ace/js/ace-extra.min.js"></script>

<!--[if lt IE 9]><script src="${ctx }/static/ace/js/html5shiv.js"></script>
	<script src="${ctx }/static/ace/js/respond.min.js"></script><![endif]-->
	
<script type="text/javascript">
	function LoadPage(obj,id) {
		LoadMainPage($(obj).attr('data-url'),id)
		return false;
	}
	function LoadMainPage(url,id){
		
		$.get(url,{n:Math.random()},function(html){
			if (html) {
				$("#" + id).html("<P></P>");
				setTimeout(function() { $("#" + id).html(html); }, 10);
	     	}
		});
	}
</script>