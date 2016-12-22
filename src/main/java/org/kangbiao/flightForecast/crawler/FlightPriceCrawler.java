package org.kangbiao.flightForecast.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.jsoup.Jsoup;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.media.jfxmedia.control.VideoDataBuffer;

/**
 * Created by kangbiao on 2016/12/17.
 *
 */
@RestController
@RequestMapping(value = "/json")
public class FlightPriceCrawler {
	@ResponseBody
	@RequestMapping("/price")
	public String getPrice() throws IOException {
		String response = Jsoup
				.connect(
						"https://flight-api.tuniu.com/query/queryCalendarPrices?callback=jQuery17207817263226723168_1479379501967&%7B%22orgCityCode%22:%22906%22,%22dstCityCode%22:%222802%22,%22departureDate%22:%222016-12-09%22,%22type%22:1,%22backDate%22:%22%22%7D&_=1479379503757")
				.ignoreContentType(true).execute().body();
		String responseJson = response.split("\\(")[1].replace(")", "");
		//System.out.println(11111);
		return responseJson;
	}
}
