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
$("#confirm").click(function() {
	AJAX("http://localhost:8080/json/price");
});

function jsonChange(dataString, priceString) {
	var option = {
		title : {
			text : '票价条形图'
		},
		tooltip : {},
		legend : {
			data : [ '销量' ]
		},
		xAxis : {
			data : dataString
		},
		yAxis : {},
		series : [ {
			name : '销量',
			type : 'bar',
			data : priceString
		} ]
	};
	return option;
}
function AJAX(url) {
	$.ajax({
		type : "get",
		async : true,
		url : url,
		dataType : "json",
		success : function(result) {
			if (result.success) {
				var mycars = new Array();
				var mycar = new Array();
				$.each(result.data, function(i, p) {
					mycars[i] = p['date'];
					mycar[i] = p['price'];
				})
				option = jsonChange(mycars, mycar);
				myChart.setOption(option);
			} else {
				alert("数据获取失败");
			}
		},
		error : function() {
			alert("服务器出错，500");
		}
	})
}
AJAX("http://localhost:8080/json/price");
