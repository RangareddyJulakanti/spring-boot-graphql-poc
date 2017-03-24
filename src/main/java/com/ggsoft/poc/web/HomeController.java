package com.ggsoft.poc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Victor Gil on 3/23/2017.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "/home";
    }
}
