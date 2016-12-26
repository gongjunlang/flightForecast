package org.kangbiao.flightForecast.controller;

import org.kangbiao.flightForecast.dao.CityDao;
import org.kangbiao.flightForecast.dao.CrawlerTaskDao;
import org.kangbiao.flightForecast.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kangbiao on 2016/12/18.
 * 城市操作相关接口
 */
@RestController
@RequestMapping(value = "/city")
public class CityController {

    @Autowired
    private CityDao cityDao;

    @RequestMapping("/getAll")
    public Response getAll(){
        Response response=new Response();
        response.setData(cityDao.findAll());
        response.success();
        return response;
    }


    @RequestMapping("/getConfigOrgCityList")
    public Response getConfigOrgCityList(HttpServletRequest request){
        Response response=new Response();
        response.setData(cityDao.getConfigOrgCity());
        response.success();
        return response;
    }


    @RequestMapping("/getConfigDistCityList")
    public Response getConfigDistCityList(HttpServletRequest request){
        Response response=new Response();
        String orgCityCode=request.getParameter("orgCityCode");
        response.setData(cityDao.getConfigDistCity(orgCityCode));
        response.success();
        return response;
    }

}
