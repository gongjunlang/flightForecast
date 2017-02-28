package org.kangbiao.flightForecast.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kangbiao on 2016/12/16.
 * 测试框架集成
 */

@RestController
@RequestMapping(value = "/")
public class TestController {
    @RequestMapping("/")
    public void home(HttpServletResponse response) throws IOException {
        response.sendRedirect("/html/queryByBuyDate.html");
    }
}
