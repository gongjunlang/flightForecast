import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by kangbiao on 2016/12/17.
 *
 */

public class CrawlerTest {

    @Test
    public void test(){
        String temp="https://flight-api.tuniu.com/query/queryCalendarPrices?callback=jQuery17207817263226723168_1479379501967&%7B%22orgCityCode%22:%22{orgCityCode}%22,%22dstCityCode%22:%22{dstCityCode}%22,%22type%22:1,%22backDate%22:%22%22%7D";

        System.out.println(temp.replaceFirst("\\{dstCityCode\\}","ssssssssss"));
//        System.out.println(String.format(temp,"sssss","ssssssss"));
    }

    @Test
    public void test1() throws IOException {
        String response = Jsoup.connect("https://flight-api.tuniu.com/query/queryCalendarPrices?callback=jQuery17207817263226723168_1479379501967&%7B%22orgCityCode%22:%22906%22,%22dstCityCode%22:%222802%22,%22type%22:1,%22backDate%22:%22%22%7D").ignoreContentType(true).execute().body();
        String responseJson = response.split("\\(")[1].replace(")", "");
        Gson gson=new Gson();
        Map jsonObject=gson.fromJson(responseJson,new TypeToken<Map<String,Object>>(){}.getType());
        if (!(Boolean)jsonObject.get("success")){
            //TODO

            return;
        }
        ArrayList flightPrices=(ArrayList)jsonObject.get("data");
        for (Object flightPrice:flightPrices){
            System.out.println(((Map)flightPrice).get("date"));
        }
    }
}
