/**
 * @auth:wangmiao
 * @param globalObj：对象ID，用于防止内存泄露
 * @param elementID ：显示对象的elementID
 * @param title
 * @param subTitle
 * @param categories：X轴数据 一维数组
 * @param xName
 * @param yName
 * @param seriesArr：Y轴数据 二维数组
 */
function addLineChart(globalObj, elementID, title, subTitle, categories, xName,
		yName, seriesArr) {
	globalObj = new Highcharts.Chart({
		chart : {
			renderTo : elementID,
			defaultSeriesType : 'line',
			marginRight : 0,
			marginBottom : 80
		},
		title : {
			text : title,
			style : {
				fontSize : '12px'
			},
			x : -20
		},
		subtitle: {
            text: document.ontouchstart === undefined ?
            '鼠标拖动可以进行缩放' : '手势操作进行缩放'
        }, 
		xAxis : {
			title : {
				text : xName
			},
			categories : categories,
			labels : {
				rotation : -45,
				align : 'right'
			}
		},
		yAxis : {
			title : {
				text : yName
			},
			min : 0,
			plotLines : [ {
				value : 0,
				width : 1,
				color : '#808080'
			} ]
		},
		tooltip : {
			formatter : function() {
				return '<b>' + this.x + '</b><br/>' + this.series.name + ': '
						+ this.y + '单位';
			}
		},
		legend : {
			layout : 'vertical',
			align : 'right',
			verticalAlign : 'top',
			x : 'left',
			y : -10,
			borderWidth : 0,
			layout : 'horizontal'
		},
		series : seriesArr
	});
}
