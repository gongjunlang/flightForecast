package org.kangbiao.flightForecast.controller;

import org.kangbiao.flightForecast.dao.CrawlerTaskDao;
import org.kangbiao.flightForecast.domain.CrawlerTask;
import org.kangbiao.flightForecast.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kangbiao on 2016/12/23.
 * 任务配置
 */
@RestController
@RequestMapping(value = "/task")
public class TaskController {
    @Autowired
    private CrawlerTaskDao crawlerTaskDao;

    @RequestMapping("/getList")
    public Response getList(HttpServletRequest request) {
        Response response = new Response();
        response.setData(crawlerTaskDao.findAll());
        response.success();
        return response;
    }

    @RequestMapping("/add")
    public Response add(HttpServletRequest request) {
        Response response = new Response();
        String orgCityCode = request.getParameter("orgCityCode");
        String distCityCode = request.getParameter("distCityCode");
        Integer status = Integer.valueOf(request.getParameter("status"));
        if (crawlerTaskDao.findByOrgDistCityCode(orgCityCode, distCityCode) != null) {
            response.setMsg("该任务已配置");
            return response;
        }
        CrawlerTask crawlerTask = new CrawlerTask();
        crawlerTask.setStatus(status);
        crawlerTask.setDistCityCode(distCityCode);
        crawlerTask.setOrgCityCode(orgCityCode);
        crawlerTaskDao.save(crawlerTask);
        response.success();
        return response;
    }

    @RequestMapping("/changeStatus")
    public Response changeStatus(HttpServletRequest request) {
        Response response = new Response();
        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer status = Integer.valueOf(request.getParameter("status"));
        CrawlerTask crawlerTask = crawlerTaskDao.findOne(id);
        if (crawlerTask == null) {
            response.setMsg("该任务不存在");
            return response;
        }
        crawlerTask.setStatus(status);
        crawlerTaskDao.save(crawlerTask);
        response.success();
        return response;
    }
}
