/**
 * Created by kangbiao on 2016/12/17.
 *
 */
var myChart = echarts.init(document.getElementById('main'));
var option = {
    title: {
        text: 'ECharts 入门示例'
    },
    tooltip: {},
    legend: {
        data:['销量']
    },
    xAxis: {
        data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
    },
    yAxis: {},
    series: [{
        name: '销量',
        type: 'bar',
        data: [5, 20, 36, 10, 10, 20]
    }]
};
myChart.setOption(option);

$("#confirm").click(function(){
    $.ajax({
        type: "get",
        async: true,
        url: "http://localhost:63342/fligh",
        dataType: "json",
        success: function (result) {

        },
        error: function () {

        }
    });
});