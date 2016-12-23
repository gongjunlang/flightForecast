package org.kangbiao.flightForecast.dao;

import org.kangbiao.flightForecast.domain.CrawlerTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
/**
 * Created by kangbiao on 2016/12/22.
 *
 */
@Repository
public interface CrawlerTaskDao extends CrudRepository<CrawlerTask,Integer> {

//    User findByName(String name);
//
//    @Query("from User u where u.name=:name")
//    User findUser(@Param("name") String name);


    ArrayList<CrawlerTask> findByStatus(Integer status);

}
