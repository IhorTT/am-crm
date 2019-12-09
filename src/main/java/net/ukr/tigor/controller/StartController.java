package net.ukr.tigor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class StartController {

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String main() {
       return "main";
    }

}
