package com.controller;

import com.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by HSH on 2017/6/30.
 */
@Controller
@RequestMapping("test")
public class TestController {
    @Autowired
    private ITestService testService;

    @RequestMapping("test")
    public  void test(){
        int count = testService.query();
    }

}
