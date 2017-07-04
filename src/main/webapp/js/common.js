/**
 * Created by kangbiao on 2016/12/26.
 *
 */

function initFirstSelect(url,target){
    $.ajax({
        type: "post",
        async: true,
        url: url,
        dataType: "json",
        success: function (result) {
            if (result.status) {
                var options="";
                for(var i in result.data) {
                    options+="<option value='"+result.data[i].cityCode+"'>"+result.data[i].cityName+"</option>";
                }
                $(target).html(options);
            }
            else {
                $(target).html("<option value=''>"+result.msg+"</option>");
            }
        },
        error: function () {
            $(target).html("<option value=''>服务器连接失败,请重试!</option>");
        }
    });
}


function initSecondSelect(url,origin,target){
    var orgCityCode=$(origin).val();
    if (!orgCityCode){
        return ;
    }
    $.ajax({
        type: "post",
        async: true,
        url: url+"?orgCityCode="+orgCityCode,
        dataType: "json",
        success: function (result) {
            if (result.status) {
                var options="";
                for(var i in result.data) {
                    options+="<option value='"+result.data[i].cityCode+"'>"+result.data[i].cityName+"</option>";
                }
                $(target).html(options);
            }
            else {
                $(target).html("<option value=''>"+result.msg+"</option>");
            }
        },
        error: function () {
            $(target).html("<option value=''>服务器连接失败,请重试!</option>");
        }
    });
}