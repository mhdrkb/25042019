package com.mhd.realstate.controller;

import com.mhd.realstate.entity.Housing;
import com.mhd.realstate.repo.AreaRepo;
import com.mhd.realstate.repo.CityRepo;
import com.mhd.realstate.repo.HousingRepo;
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
@RequestMapping(value = "/housing/")
public class HousingController {

    @Autowired
    private HousingRepo housingRepo;

    @Autowired
    private AreaRepo areaRepo;

    @Autowired
    private CityRepo cityRepo;

    @GetMapping(value = "add-housing")
    public String viewAdd(Model model){
        model.addAttribute("housing",new Housing());
        model.addAttribute("areas", this.areaRepo.findAll());
        model.addAttribute("cities", this.cityRepo.findAll());
        return "housings/add-housing";
    }

    @PostMapping(value = "add-housing")
    public String add(@Valid Housing housing, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("areas", this.areaRepo.findAll());
            model.addAttribute("cities", this.cityRepo.findAll());
            return "housings/add-housing";
        }
        if(housingRepo.existsHousingByHousingName(housing.getHousingName())){
            model.addAttribute("rejectMsg","Already Have This Entry");
        }else{
            housing.setHousingName(housing.getHousingName().toUpperCase());
            this.housingRepo.save(housing);
            model.addAttribute("housing",new Housing());
            model.addAttribute("areas", this.areaRepo.findAll());
            model.addAttribute("cities", this.cityRepo.findAll());
            model.addAttribute("successMsg","Successfully Saved!");
        }
        return "housings/add-housing";
    }

    @GetMapping(value = "delete-housing/{id}")
    public String del(@PathVariable("id") Long id){
        if(id != null) {
            this.housingRepo.deleteById(id);
        }
        return "redirect:/housing/housing-list";
    }

    @GetMapping(value = "housing-list")
    public String list(Model model){
        model.addAttribute("list",this.housingRepo.findAll());
        return "housings/housing-list";
    }
}
