package org.kangbiao.flightForecast.task;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.kangbiao.flightForecast.dao.CrawlerTaskDao;
import org.kangbiao.flightForecast.dao.FlightPriceDao;
import org.kangbiao.flightForecast.domain.Const;
import org.kangbiao.flightForecast.domain.CrawlerTask;
import org.kangbiao.flightForecast.domain.FlightPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kangbiao on 2016/12/18.
 *
 */
@Component
public class FlightCrawlerTask {

    @Value("${flight.interface.price-trend-url}")
    private String priceTrendUrl;

    @Autowired
    private FlightPriceDao flightPriceDao;

    @Autowired
    private CrawlerTaskDao crawlerTaskDao;

    @Autowired
    private FlightPriceDao flightPriceDao;

    /**
     * 每天两点执行爬虫任务
     */
    @Scheduled(cron = "0 0 2 * * *")
    public void process() {

        try {

            ArrayList<CrawlerTask> crawlerTasks = crawlerTaskDao.findByStatus(Const.CRAWLERTACK_STATUS_START);
            for (CrawlerTask crawlerTask : crawlerTasks) {
                String url = priceTrendUrl.replaceFirst("\\{dstCityCode\\}", crawlerTask.getDistCityCode())
                        .replaceFirst("\\{orgCityCode\\}", crawlerTask.getDistCityCode());
                String response = Jsoup.connect(url).ignoreContentType(true).execute().body();
                String responseJson = response.split("\\(")[1].replace(")", "");
                Gson gson = new Gson();
                Map jsonObject = gson.fromJson(responseJson, new TypeToken<Map<String, Object>>() {
                }.getType());

                if (!(Boolean) jsonObject.get("success")) {
                    //TODO 接口调用错误
                    return;
                }
                ArrayList flightPrices = (ArrayList) jsonObject.get("data");
                if (flightPrices == null || flightPrices.size() < 1) {
                    //TODO 航班价格信息不存在
                    return;
                }
                List<FlightPrice> flightPricesDB=new ArrayList<FlightPrice>();
                for (Object flightPriceObject : flightPrices) {
                    Map flightPrice=(Map)flightPriceObject;
                    flightPricesDB.add(new FlightPrice("buydate",
                            (String) flightPrice.get("date"),
                            (String) flightPrice.get("price"),
                            crawlerTask.getId()));
                }
                flightPriceDao.save(flightPricesDB);
            }
        }
        catch (Exception e){

        }
    }
}
