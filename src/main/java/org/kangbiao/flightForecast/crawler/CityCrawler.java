package org.kangbiao.flightForecast.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by kangbiao on 2016/12/17. 爬取城市code信息
 */
public class CityCrawler {

	@Test
	public void getCityCode() throws IOException {
		String body = Jsoup.connect("http://www.tuniu.com/flight/").get()
				.body().toString();
		String cityString = body.split("var CitiesList = ")[1].split(";")[0];
		System.out.println(cityString);
		Gson gson = new Gson();
		List<CityInfo> cityList = gson.fromJson(cityString,
				new TypeToken<ArrayList<CityInfo>>() {
				}.getType());
		for (CityInfo cityInfo : cityList) {
			System.out.println("INSERT INTO `city` VALUES('"
					+ cityInfo.getCityName() + "','" + cityInfo.getCityCode()
					+ "','" + cityInfo.getCityAbbr() + "','"
					+ cityInfo.getCityEnglishName() + "','"
					+ cityInfo.getCityIataCode() + "','" + cityInfo.getAbroad()
					+ "')");
		}

		// System.out.print(body);
	}

}

class CityInfo {
	private String cityCode;
	private String cityName;
	private String cityAbbr;
	private String cityEnglishName;
	private String cityIataCode;
	private String abroad;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityAbbr() {
		return cityAbbr;
	}

	public void setCityAbbr(String cityAbbr) {
		this.cityAbbr = cityAbbr;
	}

	public String getCityEnglishName() {
		return cityEnglishName;
	}

	public void setCityEnglishName(String cityEnglishName) {
		this.cityEnglishName = cityEnglishName;
	}

	public String getCityIataCode() {
		return cityIataCode;
	}

	public void setCityIataCode(String cityIataCode) {
		this.cityIataCode = cityIataCode;
	}

	public String getAbroad() {
		return abroad;
	}

	public void setAbroad(String abroad) {
		this.abroad = abroad;
	}
}
