package com.example.csg11s2;

import com.example.csg11s2.util.FileOperations;
import com.example.csg11s2.util.FormatUnwrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    @GetMapping("/menu")
    public String main(String att, Model model){
        model.addAttribute("att", att);
        return "menu";
    }



    @GetMapping("/goose")
    public String goose(Model model){
        return "goose";}

    @PostMapping("/goose")
    public String updateGoose(@RequestBody String content){
        FileOperations.writeOverHtml("/Users/anniezhuang/Documents/csg11s2/src/main/resources/templates/goose.html", FormatUnwrapper.unwrapFormat(FormatUnwrapper.decodeURL(content.substring(8)), ""), "goose");
        return "goose";}

}