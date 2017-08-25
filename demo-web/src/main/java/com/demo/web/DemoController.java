package com.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "demo")
public class DemoController {
    @RequestMapping(value = "/toDemoPage", method = RequestMethod.GET)
    public String toDemoPage(Model model) {
        return "demo";
    }
}
