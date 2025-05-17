package com.example.csg11s2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @GetMapping("/eek")
    public String eek(@RequestParam(name = "show", required=false, defaultValue="") String show, Model model){
        model.addAttribute("show", show);
        return "eek";
    }

    @GetMapping("/testpage")
    public String testpage(@RequestParam(name = "show", required=false, defaultValue="") String attName, Model model){
        return "testpage";
    }
}