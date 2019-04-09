<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>指标对比</title>

<%
	String rootPath = request.getContextPath();
%>
<script src="https://img.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
<!-- <script src="https://img.hcharts.cn/highcharts/highcharts.js"></script> -->
<script src="<%=rootPath%>/res/highcharts/highcharts.js"></script>
<script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>

<script src="<%=rootPath%>/demo/2/test3.js"></script>
<%-- <script src="<%=rootPath%>/demo/2/test2.js"></script> --%>


<body>
	<div>
		<h5>指标对比</h5>
	</div>


	<div id="container" style="height: 400px; max-width: 800px; margin: 0 auto"></div>
	
	<select id="floor">
	  <option value ="volvo">Volvo</option>
	  <option value ="saab">Saab</option>
	  <option value="opel">Opel</option>
	  <option value="audi">Audi</option>
	</select>
	

	<div class="select_checkBox"> 
	<div class="chartQuota"> 
	<p> 
	<a href="javascript:;" hidefocus="true" title="请选择指标"><span>选择指标</span> 
	<b></b> 
	</a> 
	</p> 
	 
	</div><br> 
	<div class="chartOptionsFlowTrend""> 
	<ul> 
	<li class=""><input type="checkbox" value="1"><span>浏览次数（PV）</span> 
	</li> 
	<li class=""><input type="checkbox" value="1"><span>独立访客（UV）</span> 
	</li> 
	<li class=""><input type="checkbox" value="1"><span>IP</span> 
	</li> 
	<li class=""><input type="checkbox" value="1"><span>新独立访客</span> 
	</li> 
	<li class=""><input type="checkbox" value="1"><span>访问次数</span> 
	</li> 
	</ul> 
	<p> 
	<a href="javascript:;" title="确定" hidefocus="true" class="a_0">确定</a><a
	href="javascript:;" title="取消" hidefocus="true" class="a_1">取消</a> 
	</p> 
	</div> 
	</div>
	<script type="text/javascript"> 
		$(function(){ 
			$(".select_checkBox").hover(function(){ 
				$(".chartOptionsFlowTrend").css("display","inline-block"); 
			},function(){ 
				$(".chartOptionsFlowTrend").css("display","none"); 
			}); 
		}); 
	</script>


	
</body>
</html>
