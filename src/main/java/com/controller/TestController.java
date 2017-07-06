package com.controller;

import com.service.ITestService;
import com.vo.User;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by HSH on 2017/6/30.
 */
@Controller
public class TestController {
    private static Logger logger = Logger.getLogger(TestController.class);
    @Autowired
    private ITestService testService;

    @RequestMapping(value="/login",method=RequestMethod.POST)
    public  String login(User user, HttpServletRequest request){
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
        try {
            currentUser.login(token);
        } catch (UnknownAccountException e) {
            logger.error("用户名不存在",e);
            return "/error";
        } catch (IncorrectCredentialsException e) {
            logger.error("密码错误",e);
            return "/error";
        } catch (LockedAccountException e) {
            logger.error("您的账号在另一个地方登陆",e);
            return "/error";
        }
        return "index";
    }

    @RequestMapping(value="/login",method=RequestMethod.GET)
    public  String toLogin(){
        return "login";
    }

    @RequestMapping("success")
    public String success(){
        return "index";
    }



    @RequestMapping("unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }


    @RequestMapping("admin")
    @RequiresRoles("admin")
    public String admin () {
        return "admin";
    }

    @RequestMapping({"admin","user"})
    public String user () {
        return "user";
    }
}
