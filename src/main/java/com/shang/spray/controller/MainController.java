package com.shang.spray.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * info:
 * Created by shang on 16/7/26.
 */
@Controller
public class MainController extends BaseController{

    @RequestMapping(value = "")
    public String index() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main")
    public String main(Model model) {
       //将用户名放入session
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Session session = subject.getSession();
            if (session.getAttribute("username") == null) {
                if (subject.getPrincipals() != null) {
                    session.setAttribute("username", (String) subject.getPrincipals().getPrimaryPrincipal());
                }
            }
        }
        return "main";
    }

    @RequestMapping(value = "/hello")
    public String hello(Model model) {
        model.addAttribute("str", "欢迎你");
        return "hello";
    }

}
