/**
 * Created by kangbiao on 2016/12/17.
 * 
 */
var myChart = echarts.init(document.getElementById('main'));
/*
 * var option = { title : { text : '票价条形图' }, tooltip : {}, legend : { data : [
 * '销量' ] }, xAxis : { data : [ "衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子" ] }, yAxis :
 * {}, series : [ { name : '销量', type : 'bar', data : [ 11, 20, 36, 10, 10, 20 ] } ] };
 */
// myChart.setOption(option);

$("#confirm")
		.click(
				function() {
					$
							.ajax({
								type : "get",
								async : true,
								url : "http://localhost:8080/json/price",
								dataType : "json",
								success : function(result) {
									// alert(result.data[0].date);
									var dataString='';
									var priceString='';
									//alert(result.data)
									$.each(result.data,function(i,p){
										dataString+= '"'+p['date']+'"'+",";	
										priceString+='"'+p['price']+'"'+",";
									})
									//alert(priceString);
									var obj = JSON.parse("衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子");
									console.log(obj);
									option = jsonChange('"衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"','"80","90","12","68","89","56"');
									myChart.setOption(option);
								},
								error : function() {
								}
							});
				});


function jsonChange(dataString,priceString){
	var option = {
			title : {
				text : '票价条形图'
			},
			tooltip : {},
			legend : {
				data : [ '销量' ]
			},
			xAxis : {
				//data : [ "衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子" ]
				data : [ dataString ]
			},
			yAxis : {},
			series : [ {
				name : '销量',
				type : 'bar',
				data : [ priceString ]
			} ]
		};
	console.log(option);
	return option;
}
/*
 * $.each(data,function(i,p){ label[i]=p['label'];
 * value[i]={'name':p['label'],'value':p['value']}; }); Chart.hideLoading();
 * optionPie.legend.data=label; optionPie.series[0]['data']=value;
 * optionPie.series[0]['radius']=[0,100]; Chart.setOption(optionPie);
 */