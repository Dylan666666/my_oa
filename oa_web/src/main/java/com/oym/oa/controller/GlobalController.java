package com.oym.oa.controller;

import com.oym.oa.biz.IGlobalBiz;
import com.oym.oa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller("globalController")
public class GlobalController {

    @Autowired
    private IGlobalBiz iGlobalBiz;

    @RequestMapping("/to_Login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(HttpSession session, @RequestParam String sn, @RequestParam String password) {
        Employee employee = iGlobalBiz.login(sn,password);
        if (employee == null) {
            return "redirect:to_login";
        }

        session.setAttribute("employee",employee);
        return "redirect:self";
    }

    @RequestMapping("/self")
    public  String self() {
        return "self";
    }

    @RequestMapping("/quit")
    public String quit(HttpSession session) {
        session.setAttribute("employee","");
        return "redirect:to_Login";
    }

    @RequestMapping("/to_change_password")
    public String toChangePassword() {
        return "change_password";
    }

    @RequestMapping("/change_password")
    public String changePassword(HttpSession session, @RequestParam String old, @RequestParam String new1,
                                 @RequestParam String new2) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee.getPassword().equals(old)) {
            if (!("".equals(new1.trim())) && new1.equals(new2)) {
                employee.setPassword(new1);
                iGlobalBiz.changePassword(employee);
                return "redirect:self";
            }
        }

        return "redirect:to_change_password";
    }




}
