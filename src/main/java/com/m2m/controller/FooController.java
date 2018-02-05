package com.m2m.controller;


import com.m2m.mail.ExMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/foo")
public class FooController extends BaseController {
    @Autowired
    ExMailService exMailService;
    @ResponseBody
    @RequestMapping(value = "error", method = RequestMethod.GET)
    public Object test() {
        Integer i = null;
        i.toString();
        return "";
    }

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World")
                                       String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
