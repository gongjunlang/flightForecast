package org.kangbiao.flightForecast.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.junit.Test;
import org.kangbiao.flightForecast.domain.City;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by kangbiao on 2016/12/17. 爬取城市信息
 */
public class CityCrawler {

	@Test
	public void getCityCode() throws IOException {
		String body = Jsoup.connect("http://www.tuniu.com/flight/").get()
				.body().toString();
		String cityString = body.split("var CitiesList = ")[1].split(";")[0];
		Gson gson = new Gson();
		List<City> cityList = gson.fromJson(cityString,
				new TypeToken<ArrayList<City>>() {
				}.getType());
		for (City city : cityList) {
			System.out.println("INSERT INTO `city` VALUES('"
					+ city.getCityName() + "','" + city.getCityCode() + "','"
					+ city.getCityAbbr() + "','" + city.getCityEnglishName()
					+ "','" + city.getCityIataCode() + "','" + city.getAbroad()
					+ "')");
		}
	}
}
