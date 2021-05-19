package com.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {
    @RequestMapping("/index")
    public String toIndex(){
        return  "index";
    }

    @RequestMapping("/welcome-1.html")
    public String welcome1(){
        return  "welcome-1";
    }

    @RequestMapping("/table.html")
    public String table(){
        return  "table";
    }

    @RequestMapping("/table/edit.html")
    public String tableEdit(){
        return  "table/edit";
    }

    @RequestMapping("/table/add.html")
    public String tableAdd(){
        return  "table/add";
    }

    //密码重置页面
    @RequestMapping("/passwordreset.html")
    public String resetpassword(){
        return  "passwordreset";
    }




}
