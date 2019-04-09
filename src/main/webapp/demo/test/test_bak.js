//定义一个Highcharts的变量，初始值为null
var oChart = null;

//定义oChart的布局环境
//布局环境组成：X轴、Y轴、数据显示、图标标题
var oOptions = {  
    //设置图表关联显示块和图形样式
    chart: {  
        renderTo: 'container',  //设置显示的页面块
        type:'line'//设置显示的方式
        //type: 'column'
    },
    
    //图标标题
    title: {  
        text: '图表展示范例'
        //text: null, //设置null则不显示标题
    },
    
    //x轴
    xAxis: {
    	//直接动态
    	//type: 'datetime',
        title: {
            text: 'X 轴 标 题'
        },
        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']  
    },
    
    //y轴
    yAxis: {
        title: { text: 'Y 轴 标 题' }
    },
    
    //数据列(可动态加载，参考test.js)
    series: [{
        data:[120,360,560,60,360,160,40,360,60,230,230,300]
        }] 
}; 

$(document).ready(function(){
    oChart = new Highcharts.Chart(oOptions);
    //异步动态加载数据列
    LoadSerie_Ajax();
}); 


//异步读取数据并加载到图表
function LoadSerie_Ajax() { 
    oChart.showLoading(); 
    $.ajax({  
        //url : 'ajax/get_value.aspx',
        url : 'demo/2/test.ashx',
        type : 'POST',
        dataType : 'json',
        //用静态数据，要把这个去掉
        //contentType: "application/x-www-form-urlencoded; charset=utf-8",   
        success : function(rntData){
             var oSeries = {
                name: "第二条",
                    data: rntData.rows1
                };
                oChart.addSeries(oSeries);
            }
        });
        oChart.hideLoading(); 
}










