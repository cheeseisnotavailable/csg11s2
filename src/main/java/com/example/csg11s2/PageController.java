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

@GetMapping("/aaa")
    public String aaa(@RequestParam(name = "show", required=false, defaultValue="") String show, Model model){
        model.addAttribute("show", show);
        return "eek";
    }
}