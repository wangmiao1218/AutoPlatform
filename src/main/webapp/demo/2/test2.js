//暂时不好用，还没调好

$(function(){  
    $(document).ready(function() {
        Highcharts.setOptions({
            global: {
                useUTC: false// 是否使用世界标准时间
            }
        });
          
        var chart;
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                type: 'spline',
                marginRight: 10,
                events: {
                    load: function() {
                         var series = this.series;
                         setInterval(function() {
                             var result = reload();
                             var x = result.time;
                             for(var i=0; i<series.length; i++) {
                                 var y = result.y[i];
                                 series[i].addPoint([x, y], true, true);
                             }
                         }, 1000*5);  
                    }  
                }  
            },  
              
            title: {
                text: 'xxxxxx'
            },
            
            xAxis: {
            	type: 'datetime',
            	tickPixelInterval: 150,
            	tickInterval: 60 * 1000
            },
            
            yAxis: {
            	title: {
            		text: 'Value'
            	},
            	plotLines: [{
            		value: 0,
            		width: 1,
            		color: '#808080'
            	}]
            },
            tooltip: {
            	formatter: function (){
            		return '<b>' + this.series.name + '</b><br/>' +
            		Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
            		Highcharts.numberFormat(this.y, 2);
            	}
            },
            //图例属性
            legend:{
            	layout: 'vertical',
            	align: 'right',
            	verticalAlign: 'top',
            	borderWidth: 0
            },
            exporting: {
            	enabled: false
            },
            //使用动态数据
            series: create()
        });
    });
});


//后台数据，并且x,y的值都需要由后台决定,create()方法:
function create(){
    var series = new Array();
    $.ajax({
        type: "POST",
        url: "xxxx/yyyyy.json",
        async: false, //表示同步，如果要得到ajax处理完后台数据后的返回值，最好这样设置 
        success: function(result){
            var ccc = result.key;
            var size = ccc.length;
            for(var i=0; i<size; i++){
                var name = ccc[i].yyyy;
                var perTotalCnt = ccc[i].xxxx;
                var data = function(){
                    var data = [],
                        time = result.time,
                        i;
                    for(i=-6; i<=0; i++) {
                        data.push({
                            x: aaaa,
                            y: zzzz
                        });
                    }
                    return data;
                }();
                series.push({"name": name, "data": data});
            }
        }
    });
    alert(series);
    return series;
}  













