package com.etc.controller;

import com.etc.LoginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class ManageController {
    private Logger logger= LoggerFactory.getLogger(LoginFilter.class);

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    @RequestMapping("/sysindex")
    public ModelAndView sysIndex(){
        ModelAndView mv = new ModelAndView("sysindex");
        return mv;
    }

    @RequestMapping("/login")
    public String login(String uname, String password, String url ,HttpServletResponse response, HttpSession session) throws IOException {
        logger.info("---uname--"+uname+"---password--"+password);
        if("admin".equals(uname)&&"123456".equals(password)){
            session.setAttribute("user",uname);
            if(url!=null && !"".equals(url)){ //从访问的url地址过来登录，登录成功后，回到原url
                response.sendRedirect(url);
            }else{
                response.sendRedirect("sysindex");
            }
        }

        return "用户登录成功！";

    }
}
