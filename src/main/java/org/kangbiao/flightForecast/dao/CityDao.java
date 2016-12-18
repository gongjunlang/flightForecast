package org.kangbiao.flightForecast.dao;

import org.kangbiao.flightForecast.domain.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by kangbiao on 2016/12/18.
 *
 */
@Repository
public interface CityDao extends CrudRepository<City,Long> {}
