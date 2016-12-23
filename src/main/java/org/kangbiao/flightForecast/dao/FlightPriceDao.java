package org.kangbiao.flightForecast.dao;

import org.kangbiao.flightForecast.domain.City;
import org.kangbiao.flightForecast.domain.FlightPrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kangbiao on 2016/12/22.
 *
 */
@Repository
public interface FlightPriceDao  extends CrudRepository<FlightPrice,Long>{

    @Query("from FlightPrice f where f.crawlerTaskId=:crawlerTaskId and f.buyDate=:buyDate order by f.buyDate")
    List<FlightPrice> findByTaskIdBuyDate(@Param("crawlerTaskId") Integer crawlerTaskId, @Param("buyDate")String buyDate);

    @Query("from FlightPrice f where f.crawlerTaskId=:crawlerTaskId and f.ticketDate=:ticketDate order by f.ticketDate")
    List<FlightPrice> findByTaskIdTicketDate(@Param("crawlerTaskId") Integer crawlerTaskId, @Param("ticketDate")String ticketDate);


}
