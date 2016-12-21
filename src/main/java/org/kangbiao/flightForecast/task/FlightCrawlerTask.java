package org.kangbiao.flightForecast.task;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by kangbiao on 2016/12/18.
 *
 */
@Component
public class FlightCrawlerTask {

    @Value("${flight.interface.price-trend-url}")
    private String priceTrendUrl;

    /**
     * 每天两点执行爬虫任务
     */
    @Scheduled(cron = "0 0 2 * * *")
    public void reportCurrentTime() {
        try {
            String url= priceTrendUrl.replaceFirst("\\{dstCityCode\\}","dstCityCode")
                    .replaceFirst("\\{orgCityCode\\}","orgCityCode");
            String response = Jsoup.connect(url).ignoreContentType(true).execute().body();
            String responseJson = response.split("\\(")[1].replace(")", "");
            Gson gson=new Gson();
            Map jsonObject=gson.fromJson(responseJson,new TypeToken<Map<String,Object>>(){}.getType());

            if (!(Boolean)jsonObject.get("success")){
                //TODO 接口调用错误
                return;
            }
            ArrayList flightPrices=(ArrayList)jsonObject.get("data");
            if (flightPrices==null||flightPrices.size()<1){
                //TODO 航班价格信息不存在
                return;
            }
            for (Object flightPrice:flightPrices){
                System.out.println(((Map)flightPrice).get("date"));
            }
//            System.out.print(((Map)((ArrayList)jsonObject.get("data")).get(0)).get("date"));
        }
        catch (IOException e) {
            //TODO 日志记录
        }
    }
}
