package org.kangbiao.flightForecast.task;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.kangbiao.flightForecast.dao.CrawlerTaskDao;
import org.kangbiao.flightForecast.dao.FlightPriceDao;
import org.kangbiao.flightForecast.domain.Const;
import org.kangbiao.flightForecast.domain.CrawlerTask;
import org.kangbiao.flightForecast.domain.FlightPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private CrawlerTaskDao crawlerTaskDao;

    @Autowired
    private FlightPriceDao flightPriceDao;

    /**
     * 每天两点执行爬虫任务
     */
    @Scheduled(cron ="0 26 16 * * *")
    public void process() {
        try {
            ArrayList<CrawlerTask> crawlerTasks = crawlerTaskDao.findByStatus(Const.CRAWLERTACK_STATUS_START);
            Date date=new Date();
            DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            String today=format.format(date);
            for (CrawlerTask crawlerTask : crawlerTasks) {
                if (crawlerTask.getLastExcuteTime().equals(today)){
                    continue;
                }
                String url = priceTrendUrl.replaceFirst("\\{orgCityCode\\}", crawlerTask.getOrgCityCode())
                        .replaceFirst("\\{dstCityCode\\}", crawlerTask.getDistCityCode());
                String response = Jsoup.connect(url).ignoreContentType(true).execute().body();
                String responseJson = response.split("\\(")[1].replace(")", "");
                Gson gson = new Gson();
                Map jsonObject = gson.fromJson(responseJson, new TypeToken<Map<String, Object>>() {}.getType());
                if (!(Boolean) jsonObject.get("success")) {
                    //TODO 接口调用错误
                    continue;
                }
                ArrayList flightPrices = (ArrayList) jsonObject.get("data");
                if (flightPrices == null || flightPrices.size() < 1) {
                    //TODO 航班价格信息不存在
                    continue;
                }
                List<FlightPrice> flightPricesList=new ArrayList<FlightPrice>();
                for (Object flightPriceObject : flightPrices) {
                    Map flightPrice=(Map)flightPriceObject;
                    flightPricesList.add(new FlightPrice(today,
                                        (String) flightPrice.get("date"),
                                        (String) flightPrice.get("price"),
                                        crawlerTask.getId()));
                }
                flightPriceDao.save(flightPricesList);
                crawlerTask.setLastExcuteTime(today);
                crawlerTask.setExcuteCount(crawlerTask.getExcuteCount()+1);
                crawlerTaskDao.save(crawlerTask);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
