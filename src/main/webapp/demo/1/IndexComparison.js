$(function () {
    $('#container').highcharts({
        chart: {
            zoomType: 'xy'
        },
        title: {
            text: '指标数据'
        },
        subtitle: {
            text: document.ontouchstart === undefined ?
            '鼠标拖动可以进行缩放' : '手势操作进行缩放'
        },     
        /*subtitle: {
            text: '数据来源: xxxx'
        },*/
        xAxis: [{
            categories: ['2000', '2001', '2002', '2003', '2004', '2005',
                         '2006', '2007', '2008', '2009', '2010', '2011'],
            crosshair: true
        }],
        yAxis: [{ // Primary yAxis
            labels: {
                format: '{value}单位',
                style: {
                    color: Highcharts.getOptions().colors[2]
                }
            },
            title: {
                text: '指标1',
                style: {
                    color: Highcharts.getOptions().colors[2]
                }
            },
            opposite: true
        }, { // Secondary yAxis
            gridLineWidth: 0,
            title: {
                text: '指标2',
                style: {
                    color: Highcharts.getOptions().colors[0]
                }
            },
            labels: {
                format: '{value} 单位',
                style: {
                    color: Highcharts.getOptions().colors[0]
                }
            }
        }, { // Tertiary yAxis
            gridLineWidth: 0,
            title: {
                text: '指标3',
                style: {
                    color: Highcharts.getOptions().colors[1]
                }
            },
            labels: {
                format: '{value} 单位',
                style: {
                    color: Highcharts.getOptions().colors[1]
                }
            },
            opposite: true
        }],
        tooltip: {
            shared: true
        },
        legend: {
            layout: 'vertical',
            align: 'left',
            x: 80,
            verticalAlign: 'top',
            y: 55,
           // floating: true //指标点选显示
           // backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
        },
        series: [{
            name: '指标2',
            type: 'column',
            yAxis: 1,
            data: [39.9, 51.5, 41.4, 169.2, 134.0, 156.0, 145.6, 14.5, 55.4, 164.1, 89.6, 99.4],
            tooltip: {
                valueSuffix: ' 单位'
            }
        }, {
            name: '指标3',
            type: 'spline',
            yAxis: 2,
            data: [1015, 1015, 1014.9, 1014.5, 1011.3, 1010.5, 1010.6, 1011.2, 1014.1, 1016.9, 1017.2, 1015.7],
            marker: {
                enabled: false
            },
            dashStyle: 'shortdot',
            tooltip: {
                valueSuffix: ' 单位'
            }
        }, {
            name: '指标1',
            type: 'spline',
            data: [6.0, 5.9, 8.5, 13.5, 17.2, 20.5, 19.2, 25.5, 22.3, 17.3, 12.9, 10.6],
            tooltip: {
                valueSuffix: ' 单位'
            }
        }]
    });
});