package org.kangbiao.flightForecast.domain;

import javax.persistence.*;

/**
 * Created by kangbiao on 2016/12/22.
 *
 */
@Entity
@Table(name = "crawlerTask")
public class CrawlerTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String orgCityCode;

    private String distCityCode;

    private Integer excuteCount;

    private Integer status;

    private String lastExcuteTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgCityCode() {
        return orgCityCode;
    }

    public void setOrgCityCode(String orgCityCode) {
        this.orgCityCode = orgCityCode;
    }

    public String getDistCityCode() {
        return distCityCode;
    }

    public void setDistCityCode(String distCityCode) {
        this.distCityCode = distCityCode;
    }

    public Integer getExcuteCount() {
        return excuteCount;
    }

    public void setExcuteCount(Integer excuteCount) {
        this.excuteCount = excuteCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLastExcuteTime() {
        return lastExcuteTime;
    }

    public void setLastExcuteTime(String lastExcuteTime) {
        this.lastExcuteTime = lastExcuteTime;
    }
}
