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
<script
	src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>

<script src="<%=rootPath%>/demo/1/IndexComparison.js"></script>
<body>
	<div>
		<h5>指标对比</h5>
	</div>

	<div id="container" style="min-width: 400px; height: 400px"></div>


</body>
</html>
