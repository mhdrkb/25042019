package com.mhd.realstate.controller;

import com.mhd.realstate.entity.Availablilty;
import com.mhd.realstate.repo.AvailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/avail/")
public class AvailController {
    @Autowired
    private AvailRepo availRepo;

    @GetMapping(value = "add-avail")
    public String viewAdd(Model model){
        model.addAttribute("availability",new Availablilty());
        return "avail/add-avail";
    }

    @PostMapping(value = "add-avail")
    public String add(@Valid Availablilty availablilty, BindingResult result, Model model){
        if(result.hasErrors()){
            return "avail/add-avail";
        }else{
            availablilty.setAvailName(availablilty.getAvailName().toUpperCase());
            this.availRepo.save(availablilty);
            model.addAttribute("successMsg","Successfully Saved!");
        }
        return "avail/add-avail";
    }
}
