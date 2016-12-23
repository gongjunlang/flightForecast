package org.kangbiao.flightForecast.dao;

import org.kangbiao.flightForecast.domain.City;
import org.kangbiao.flightForecast.domain.FlightPrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kangbiao on 2016/12/22.
 *
 */
@Repository
public interface FlightPriceDao  extends CrudRepository<FlightPrice,Long>{



}
