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

<script src="<%=rootPath%>/demo/test/test.js"></script>

<script language="javascript" type="text/javascript">
    var chart;
    $(document).ready(function () {
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                defaultSeriesType: 'line', //图表类别，可取值有：line、spline、area、areaspline、bar、column等
                marginRight: 130,
                marginBottom: 25
            },
            title: {
                text: 'Monthly Average Temperature', //设置一级标题
                x: -20 //center
            },
            subtitle: {
                text: 'Source: WorldClimate.com', //设置二级标题
                x: -20
            },
            xAxis: {
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
            'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']//设置x轴的标题
            },
            yAxis: {
                title: {
                    text: 'Temperature (Â°C)' //设置y轴的标题
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
               this.x + ': ' + this.y + 'Â°C';  //鼠标放在数据点的显示信息，但是当设置显示了每个节点的数据项的值时就不会再有这个显示信息
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right', //设置说明文字的文字 left/right/top/
                verticalAlign: 'top',
                x: -10,
                y: 100,
                borderWidth: 0
            },
            exporting: {
                enabled: true, //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
                url: "http://localhost:49394/highcharts_export.aspx" //导出图片的URL，默认导出是需要连到官方网站去的哦
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true //显示每条曲线每个节点的数据项的值
                    },
                    enableMouseTracking: false
                }
            },
            series: [{
                name: 'Tokyo', //每条线的名称
                data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]//每条线的数据
            }, {
                name: 'New York',
                data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
            }, {
                name: 'Berlin',
                data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
            }, {
                name: 'London',
                data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
            }]
        });

    });
 </script> 


<body>
	<div>
		<h5>指标对比</h5>
	</div>
    
	<div id="container" style="height: 400px; max-width: 800px; margin: 0 auto"></div>
	

	
</body>
</html>
