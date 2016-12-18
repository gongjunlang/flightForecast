package org.kangbiao.flightForecast.controller;

import org.kangbiao.flightForecast.dao.CityDao;
import org.kangbiao.flightForecast.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by kangbiao on 2016/12/18.
 *
 */
@RestController
@RequestMapping(value = "/city")
public class CityController {

    @Autowired
    private CityDao cityDao;

    @RequestMapping("/getAll")
    public ArrayList<City> getAll(){
        return (ArrayList<City>) cityDao.findAll();
    }

}
