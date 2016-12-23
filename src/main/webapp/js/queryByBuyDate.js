/**
 * Created by kangbiao on 2016/12/23.
 *
 */

var myChart = echarts.init(document.getElementById('main'));

$.ajax({
    type: "post",
    async: true,
    url: urlConfig.queryByBuyDate,
    dataType: "json",
    data:{distCityCode:902,orgCityCode:2802,date:"2016-12-23"},
    success: function (result) {
        if (result.status) {
            var series=[];
            var xAxisData= [];
            var legend={};
            var data = result.data;
            legend.data=Object.keys(data);
            var length=legend.data.length;
            var count=1;
            for (var date in data){
                var temp={smooth:true,
                    symbol: 'none',
                    sampling: 'average',name:date,type:"line",data:[]};
                for (var index in data[date]){
                    if (count!=length){
                        if (index==0){
                            xAxisData.push(data[date][index]['ticketDate']);
                        }
                    }
                    else {
                        xAxisData.push(data[date][index]['ticketDate']);
                    }
                    temp.data.push(data[date][index]['price']);
                    for(var i=count;i<length;i++){
                        temp.data.push("");
                    }
                }
                count++;
                series.push(temp);
            }
            console.log(xAxisData);
            console.log(series);
            myChart.setOption({
                title: {
                    text: '按购票日期查询'
                },
                tooltip: {
                    trigger: 'axis',
                    position: function (pt) {
                        return [pt[0], '10%'];
                    }
                },
                legend: legend,
                xAxis: {
                    name :"出发日期",
                    type: 'category',
                    boundaryGap: false,
                    data: xAxisData
                },
                yAxis: {
                    name :"价格/元",
                    type: 'value'
                },
                series: series
            });
        }
        else {
            alert(result.msg);
        }
    },
    error: function () {
        alert("服务器连接失败,请重试!");
    }
});