package org.kangbiao.flightForecast.task;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by kangbiao on 2016/12/18.
 *
 */
@Component
public class FlightCrawlerTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 每天两点执行爬虫任务
     */
    @Scheduled(cron = "0 0 2 * * *")
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }
}
