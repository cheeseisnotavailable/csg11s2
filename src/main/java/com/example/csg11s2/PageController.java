package com.example.csg11s2;

import com.example.csg11s2.util.FormatUnwrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.csg11s2.util.FileOperations.writeOver;

@Controller
public class PageController {

    @GetMapping("/menu")
    public String main(String att, Model model){
        model.addAttribute("att", att);
        return "menu";
    }

    @GetMapping("/bagels")
    public String bagels(@RequestParam(name = "show", required=false, defaultValue="") String attName, Model model){
        return "bagels";}

    @PostMapping("/bagels/{content}")
    public String updateBagels(@PathVariable("content") String content){
        writeOver("/Users/anniezhuang/Documents/csg11s2/src/main/resources/templates/bagels.html", FormatUnwrapper.unwrapFormat(content.toString(), ""), "bagels");
        return "bagels";
    }

    @GetMapping("/cheese")
    public String cheese(@RequestParam(name = "show", required=false, defaultValue="") String attName, Model model){
        return "cheese";}
    @GetMapping("/goose")
    public String goose(@RequestParam(name = "show", required=false, defaultValue="") String attName, Model model){
        return "goose";}
    @GetMapping("/dollshouse")
    public String dollshouse(@RequestParam(name = "show", required=false, defaultValue="") String attName, Model model){
        return "dollshouse";}
}