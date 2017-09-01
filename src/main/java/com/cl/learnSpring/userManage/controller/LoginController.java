package com.cl.learnSpring.userManage.controller;

import com.cl.learnSpring.userManage.entity.UserPo;
import com.cl.learnSpring.userManage.service.CacheService;
import com.cl.learnSpring.userManage.service.UserService;
import com.cl.learnSpring.userManage.service.impl.CacheServiceImpl;
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

    @Resource
    CacheService cacheService;

    @RequestMapping("/")
    public String index() {
        return "欢迎";
    }

    @RequestMapping(path = "/login.do", method = {RequestMethod.GET})
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/cache.do", method = {RequestMethod.GET})
    public String cache() {
        return "cache";
    }

    @RequestMapping(path = "/cacheAble.do", method = {RequestMethod.GET})
    public String getByCacheAble() {
        return "getByCacheAble";
    }

    @RequestMapping(path = "/userLogin.do", method = {RequestMethod.POST})
    public ModelAndView userLogin(HttpSession session, @RequestParam String username, @RequestParam String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UserPo userPo = userService.login(username, password);
        if (userPo == null) {
            return new ModelAndView("login", "error", "账户名称或密码错误");
        }
        return getmodelAndView(session,userPo);

    }

    /**
     * 用于自定义缓存测试
     * @param session
     * @param username
     * @param password
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(path = "/userCache.do", method = {RequestMethod.POST})
    public ModelAndView userCache(HttpSession session, @RequestParam String username, @RequestParam String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UserPo userPo = cacheService.login(username, password);
        if (userPo == null) {
            return new ModelAndView("cache", "error", "账户名称或密码错误");
        }
        return getmodelAndView(session,userPo);

    }

    /**
     * 用于自定义缓存测试
     * 利用 @Cacheable 注解
     * @param session
     * @param username
     * @param password
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(path = "/getByCacheAble.do", method = {RequestMethod.POST})
    public ModelAndView getByCacheAble(HttpSession session, @RequestParam String username, @RequestParam String password) throws Exception {
        UserPo userPo = cacheService.getUserPo(username,password);
        if (userPo == null) {
            return new ModelAndView("cache", "error", "账户名称或密码错误");
        }
        return getmodelAndView(session,userPo);
    }

    ModelAndView getmodelAndView(HttpSession session,UserPo userPo){
        session.setAttribute("isLogin", true);
        session.setAttribute("user_id", userPo.getId());
        session.getServletContext().setAttribute("userId", userPo.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(userPo);
        modelAndView.setViewName("main");
        return modelAndView;
    }

}
