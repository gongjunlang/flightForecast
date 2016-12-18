package org.kangbiao.flightForecast.controller;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by kangbiao on 2016/12/18.
 *
 */
@RestController
@RequestMapping(value = "/flight")
public class FlightController {

    @Value("${flight.interface.price-trend-url}")
    private String priceTrendUrl;

    @RequestMapping(value = "/getPriceTrend")
    public String  getPriceTrend(@Param(value = "orgCityCode") String orgCityCode,
                                 @Param(value = "dstCityCode") String dstCityCode) throws IOException {

        String url= priceTrendUrl.replaceFirst("\\{dstCityCode\\}",dstCityCode).replaceFirst("\\{orgCityCode\\}",orgCityCode);
        String response=Jsoup.connect(url).ignoreContentType(true).execute().body();
        String responseJson=response.split("\\(")[1].replace(")","");
        return responseJson;
    }

}
