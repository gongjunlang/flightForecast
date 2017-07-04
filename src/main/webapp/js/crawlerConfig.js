/**
 * Created by kangbiao on 2016/12/26.
 *
 */
var citys={};
initSelect();
renderTaskTable();

$("#saveBtn").click(function(){
    var data=$("#addTaskForm").serializeArray();
    $.ajax({
        type: "post",
        async: true,
        url: urlConfig.addTask,
        dataType: "json",
        data: data,
        success: function (result) {
            if (result.status) {
                renderTaskTable();
                $("#addTaskModal").modal("hide");
            }
            else {
                alert(result.msg);
            }
        },
        error: function () {
            alert("服务器连接失败,请重试!");
        }
    });
});

function renderTaskTable(){
    $("#taskTable").find("tbody").html("");
    $.ajax({
        type: "post",
        async: true,
        url: urlConfig.getTaskList,
        dataType: "json",
        success: function (result) {
            if (result.status) {
                var trs="";
                for(var i in result.data) {
                    if(!result.data[i].excuteCount){
                        result.data[i].excuteCount="未曾执行";
                    }
                    if(!result.data[i].lastExcuteTime){
                        result.data[i].lastExcuteTime="未曾执行";
                    }
                    trs+="<tr></tr><td>"+citys[result.data[i].orgCityCode]+"</td>"+
                     "<td>"+citys[result.data[i].distCityCode]+"</td>"+
                     "<td>"+result.data[i].excuteCount+"</td>"+
                     "<td>"+result.data[i].lastExcuteTime+"</td>";
                    if (result.data[i].status==1){
                        trs+="<td><span style='color: green'>执行中</span></td>" +
                            "<td><button class='btn btn-primary btn-sm'>暂停</button></td>";
                    }else if (result.data[i].status==2){
                        trs+="<td><span style='color: red'>未执行</span></td>" +
                            "<td><button class='btn btn-primary btn-sm'>开始</button></td>";
                    }else {
                        trs+="<td><span style='color: red'>状态异常</span></td>";
                    }
                    trs+="</tr>";
                }
                $("#taskTable").find("tbody").html(trs);
            }
            else {
                $("#taskTable").find("tbody").html(result.msg);
            }
        },
        error: function () {
            $("#taskTable").find("tbody").html("服务器连接失败,请重试!");
        }
    });
}

function initSelect(){
    $.ajax({
        type: "post",
        async: false,
        url: urlConfig.getCity,
        dataType: "json",
        success: function (result) {
            if (result.status) {
                var options="";
                for(var i in result.data) {
                    citys[result.data[i].cityCode]=result.data[i].cityName;
                    options+="<option value='"+result.data[i].cityCode+"'>"+result.data[i].cityName+"</option>";
                }
                $("#orgCityCode").html(options);
                $("#distCityCode").html(options);
            }
            else {
                $("#orgCityCode").html("<option value=''>"+result.msg+"</option>");
            }
        },
        error: function () {
            $("#orgCityCode").html("<option value=''>服务器连接失败,请重试!</option>");
        }
    });
}




