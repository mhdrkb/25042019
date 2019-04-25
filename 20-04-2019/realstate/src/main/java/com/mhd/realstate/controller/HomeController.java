package com.mhd.realstate.controller;


import com.mhd.realstate.entity.Summary;
import com.mhd.realstate.repo.BuilldingRepo;
import com.mhd.realstate.repo.SummaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    private BuilldingRepo repo;
    @Autowired
    private SummaryRepo summaryRepo;
    @GetMapping(value = "/")
    public String displayHome(Model model){
        model.addAttribute("buildinglist",repo.findAll());
        return "home";
    }
    @GetMapping(value = "/property-single")
    public String single(Model model){
        return "property-single";
    }
//    @PostMapping(value = "/property-single")
//    public String add(@Valid Summary summary, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "property-single";
//        }
//        this.summaryRepo.save(summary);
//        model.addAttribute("successMsg", "Successfully Saved!");
//        return "property-single";
//    }

    @GetMapping(value = "/dashboard")
    public String displayDashboard(){
        return "index";
    }
    @GetMapping(value = "/about")
    public String displayAbout(){
        return "about";
    }
}
