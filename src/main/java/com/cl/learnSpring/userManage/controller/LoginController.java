package com.cl.learnSpring.userManage.controller;

import com.cl.learnSpring.userManage.entity.UserPo;
import com.cl.learnSpring.userManage.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by cl on 2017/8/12.
 */
@Controller
@EnableAutoConfiguration
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "欢迎";
    }

    @RequestMapping(path = "/login.do", method = {RequestMethod.GET})
    public String login() {
        return "login";    }

    @RequestMapping(path = "/userLogin.do", method = {RequestMethod.POST})
    public ModelAndView userLogin(HttpSession session, @RequestParam String username, @RequestParam String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UserPo userPo = userService.login(username, password);
        if (userPo == null) {
            return new ModelAndView("login", "error", "账户名称或密码错误");
        }
        session.setAttribute("isLogin", true);
        session.setAttribute("user_id", userPo.getId());
        session.getServletContext().setAttribute("userId", userPo.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(userPo);
        modelAndView.setViewName("main");
        return modelAndView;
    }

}
