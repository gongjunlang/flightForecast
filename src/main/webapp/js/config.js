/**
 * Created by kangbiao on 2016/12/21.
 * 配置文件
 */

var host="http://localhost:8080/";
var urlConfig={};

urlConfig.getCity=host+"city/getAll";
urlConfig.queryByBuyDate=host+"flight/queryByBuyDate";
urlConfig.queryByTicketDate=host+"flight/queryByTicketDate";

urlConfig.getConfigOrgCityList=host+"city/getConfigOrgCityList";
urlConfig.getConfigDistCityList=host+"city/getConfigDistCityList";

urlConfig.getTaskList=host+"task/getList";
urlConfig.addTask=host+"task/add";
urlConfig.changeTaskStatus=host+"task/changeStatus";