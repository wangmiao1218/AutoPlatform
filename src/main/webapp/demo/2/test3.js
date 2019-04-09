$("#floor").change(function(){
    var lastdata = [];
    //三个下拉框
    var a=$(this).val();
    var b=$("#building").val();
    var c=$("#city").val();
    
    var xset =new Array();//X轴数据集  
    var yset =new Array();//Y轴数据集
    $.ajax({
        data:{floor:a,building:b,city:c},
        datatype:"POST",
        url:"test.ashx",//改成后台controller
        success:function(data){
            var i,len=data.length;
                for( i=0;i<len;i++){
                    yset[i] = parseInt(data[i].num);
                    xset[i] = data[i].time.substr(5,8);
                }
            console.log(xset);
            console.log(yset);
            showChart(xset,yset);
            function showChart(xset,yset){
                var chart = new Highcharts.Chart({
                    chart: {
                        renderTo: 'container',
                        type: 'line',
                        marginRight: 130,
                        marginBottom: 85 
                    },
                    
                   
                    xAxis: {
                        categories: xset
                    },
                    yAxis: {
                        title: {
                            text: '人数'
                        },
                        plotLines: [{
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }]
                    },
                    tooltip: {
                        formatter: function(){ 
                                return '<b>'+ this.series.name +'</b><br/>'+
                                this.x +': '+ this.y;  
                        }
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'top',
                        x: -10,
                        y: 100,
                        borderWidth: 0
                    },
                    series: [{
                        name: '时间',
                        data: yset
                    }]
                });
            }
        }
    });
})

