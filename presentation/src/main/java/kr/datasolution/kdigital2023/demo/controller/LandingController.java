package kr.datasolution.kdigital2023.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LandingController {
    private static final Logger log = LoggerFactory.getLogger(LandingController.class);

    LandingController() {}

    @GetMapping("")
    public String getIndex(Model model) {
        log.info("accessed index");
        return "/index";
    }
}
