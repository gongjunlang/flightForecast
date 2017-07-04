package org.kangbiao.flightForecast.dao;

import org.kangbiao.flightForecast.domain.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


/**
 * Created by kangbiao on 2016/12/18.
 *
 */
@Repository
public interface CityDao extends CrudRepository<City,Integer> {

    @Query("from City a order by a.cityAbbr")
    ArrayList<City> findAll();

    @Query("select distinct a from City a, CrawlerTask b where a.cityCode=b.orgCityCode order by a.cityAbbr")
    ArrayList<City> getConfigOrgCity();

    @Query("select distinct a from City a,CrawlerTask b where a.cityCode=b.distCityCode and b.orgCityCode=:orgCityCode order by a.cityAbbr")
    ArrayList<City> getConfigDistCity(@Param("orgCityCode") String orgCityCode);

}
