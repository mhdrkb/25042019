package com.mhd.realstate.controller;

import com.mhd.realstate.entity.City;
import com.mhd.realstate.repo.CityRepo;
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
@RequestMapping(value = "/city/")
public class CityController {
    @Autowired
    private CityRepo cityRepo;


    @GetMapping(value = "add-city")
    public String viewAdd(Model model){
        model.addAttribute("city",new City());
        return "cities/add-city";
    }

    @PostMapping(value = "add-city")
    public String add(@Valid City city, BindingResult result, Model model){
        if(result.hasErrors()){
            return "cities/add-city";
        }
        if(cityRepo.existsCityByCityName(city.getCityName())){
            model.addAttribute("rejectMsg","Already Have This Entry");
        }else{
            city.setCityName(city.getCityName().toUpperCase());
            this.cityRepo.save(city);
            model.addAttribute("successMsg","Successfully Saved!");
        }
        return "cities/add-city";
    }

    @GetMapping(value = "delete-city/{id}")
    public String del(@PathVariable("id") Long id){
        if(id != null) {
            this.cityRepo.deleteById(id);
        }
        return "redirect:/city/city-list";
    }

    @GetMapping(value = "city-list")
    public String list(Model model){
        model.addAttribute("list",this.cityRepo.findAll());
        return "cities/city-list";
    }
}
