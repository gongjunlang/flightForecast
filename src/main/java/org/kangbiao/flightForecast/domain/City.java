package org.kangbiao.flightForecast.domain;

import javax.persistence.*;

/**
 * Created by kangbiao on 2016/12/17.
 *
 */
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name="cityCode")
    private String cityCode;

    @Column(name="cityName")
    private String cityName;

    @Column(name="cityAbbr")
    private String cityAbbr;

    @Column(name="cityEnglishName")
    private String cityEnglishName;

    @Column(name="cityIataCode")
    private String cityIataCode;

    @Column(name="abroad")
    private String  abroad;


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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
