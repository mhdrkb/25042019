package com.mhd.realstate.controller;


import com.mhd.realstate.entity.Catagory;
import com.mhd.realstate.repo.CatagoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/catagory/")
public class CatagoryController {
    @Autowired
    private CatagoryRepo catagoryRepo;


    @GetMapping(value = "add-catagory")
    public String viewAdd(Model model){
        model.addAttribute("catagory",new Catagory());
        return "catagories/add-catagory";
    }

    @PostMapping(value = "add-catagory")
    public String add(@Valid Catagory catagory, BindingResult result, Model model){
        if(result.hasErrors()){
            return "catagories/add-catagory";
        }
        if(catagoryRepo.existsCatagoryByCatagoryName(catagory.getCatagoryName())){
            model.addAttribute("rejectMsg","Already Have This Entry");
        }else{
            catagory.setCatagoryName(catagory.getCatagoryName().toUpperCase());
            this.catagoryRepo.save(catagory);
            model.addAttribute("successMsg","Successfully Saved!");
        }
        return "catagories/add-catagory";
    }

    @GetMapping(value = "delete-catagory/{id}")
    public String del(@PathVariable("id") Long id){
        if(id != null) {
            this.catagoryRepo.deleteById(id);
        }
        return "redirect:/catagory/catagory-list";
    }

    @GetMapping(value = "catagory-list")
    public String list(Model model){
        model.addAttribute("list",this.catagoryRepo.findAll());
        return "catagories/catagory-list";
    }
}
