package org.kangbiao.flightForecast.controller;

/**
 * Created by kangbiao on 2016/12/16.
 * 测试框架集成
 */
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class TestController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}