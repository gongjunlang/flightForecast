package org.kangbiao.flightForecast.domain;

import javax.persistence.*;

/**
 * Created by kangbiao on 2016/12/22.
 *
 */
@Entity
@Table(name = "flightPrice")
public class FlightPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String buyDate;

    private String ticketDate;

    private String price;

    private Integer crawlerTaskId;


    public FlightPrice() {}

    public FlightPrice(String buyDate, String ticketDate, String price, Integer crawlerTaskId) {
        this.buyDate = buyDate;
        this.ticketDate = ticketDate;
        this.price = price;
        this.crawlerTaskId = crawlerTaskId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(String ticketDate) {
        this.ticketDate = ticketDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getCrawlerTaskId() {
        return crawlerTaskId;
    }

    public void setCrawlerTaskId(Integer crawlerTaskId) {
        this.crawlerTaskId = crawlerTaskId;
    }
}
