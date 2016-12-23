package org.kangbiao.flightForecast.controller;

import org.kangbiao.flightForecast.dao.CityDao;
import org.kangbiao.flightForecast.dao.CrawlerTaskDao;
import org.kangbiao.flightForecast.domain.CrawlerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangbiao on 2016/12/16.
 * 测试框架集成
 */

@RestController
@EnableAutoConfiguration
public class TestController {

    @Autowired
    private CrawlerTaskDao crawlerTaskDao;

    @RequestMapping("/")
    CrawlerTask home() {
        return crawlerTaskDao.findByOrgDistCityCode("2802","902");
    }
}
