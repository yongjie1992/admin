package com.guoyiguan.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangyj
 * @version V1.0
 * @since 2019-09-02 21:15
 */
@Controller
public class TestController {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "I'm just a test";
    }
}