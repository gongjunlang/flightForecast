package org.kangbiao.flightForecast.domain;

/**
 * Created by kangbiao on 2016/12/23.
 *
 */
public class Response {
    private Boolean status;
    private String msg;
    private Object data;



    public Response() {
        this.status = false;
    }

    public Response(Boolean status) {
        this.status = status;
    }

    public Response(Boolean status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
