package com.example.csg11s2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @GetMapping("/menu")
    public String main(String att, Model model){
        model.addAttribute("att", att);
        return "menu";
    }

    @GetMapping("/eek")
    public String eek(@RequestParam(name = "show", required=false, defaultValue="") String show, Model model){
        model.addAttribute("show", show);
        return "eek";
    }

    @GetMapping("/bagels")
    public String bagels(@RequestParam(name = "show", required=false, defaultValue="") String attName, Model model){
        return "bagels";
    }
    @GetMapping("/cheese")
    public String cheese(@RequestParam(name = "show", required=false, defaultValue="") String attName, Model model){
        return "cheese";
    }
    @GetMapping("/goose")
    public String goose(@RequestParam(name = "show", required=false, defaultValue="") String attName, Model model){
        return "goose";
    }
}