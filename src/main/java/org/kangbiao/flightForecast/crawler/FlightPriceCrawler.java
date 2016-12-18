package org.kangbiao.flightForecast.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.junit.Test;

/**
 * Created by kangbiao on 2016/12/17.
 *
 */
public class FlightPriceCrawler {
	@Test
	public void getFligtPrice() throws IOException {
		URL doc = new URL(
				"https://flight-api.tuniu.com/query/queryCalendarPrices?callback=jQuery172042180330051982584_1482042271205&{%22orgCityCode%22:%222802%22,%22dstCityCode%22:%22200%22,%22departureDate%22:%222016-12-18%22,%22type%22:1}&_=1482042274818");
		BufferedReader in = new BufferedReader(new InputStreamReader(doc
				.openConnection().getInputStream()));
		String jsonString = "";
		String currentString;
		while ((currentString = in.readLine()) != null) {
			jsonString += currentString;
		}
		jsonString = jsonString.split("\\(")[1].split("\\)")[0];
		System.out.println(jsonString);
	}

}
