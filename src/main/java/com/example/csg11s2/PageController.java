package com.example.csg11s2;

import com.example.csg11s2.util.FileOperations;
import com.example.csg11s2.util.FormatUnwrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.csg11s2.framework.Page;

@Controller
public class PageController {

    @GetMapping("/menu")
    public String main(String att, Model model){
        model.addAttribute("att", att);
        return "menu";
    }

    @PostMapping("/menu")
    public String addPage(@RequestBody String title){
        Page.newPage(title.substring(6), "");
        return "menu";
    }



    @GetMapping("/bagels")
    public String bagels(Model model){
        return "bagels";}

    @PostMapping("/bagels")
    public String updateBagels(@RequestBody String content){
        FileOperations.writeOverHtml("/Users/anniezhuang/Documents/csg11s2/src/main/resources/templates/bagels.html", FormatUnwrapper.unwrapWebRepsonse(FormatUnwrapper.decodeURL(content.substring(8))), "bagels");
        return "bagels";}

}