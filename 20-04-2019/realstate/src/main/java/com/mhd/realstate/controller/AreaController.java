package com.mhd.realstate.controller;


import com.mhd.realstate.entity.Area;
import com.mhd.realstate.repo.AreaRepo;
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
@RequestMapping(value = "/area/")
public class AreaController {
    @Autowired
    private AreaRepo areaRepo;

    @Autowired
    private CityRepo cityRepo;

    @GetMapping(value = "add-area")
    public String viewAdd(Model model){
        model.addAttribute("area",new Area());
        model.addAttribute("cities", this.cityRepo.findAll());
        return "areas/add-area";
    }

    @PostMapping(value = "add-area")
    public String add(@Valid Area area, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("cities", this.cityRepo.findAll());
            return "areas/add-area";
        }
        if(areaRepo.existsAreaByAreaName(area.getAreaName())){
            model.addAttribute("rejectMsg","Already Have This Entry");
        }else{
            area.setAreaName(area.getAreaName().toUpperCase());
            this.areaRepo.save(area);
            model.addAttribute("area",new Area());
            model.addAttribute("cities", this.cityRepo.findAll());
            model.addAttribute("successMsg","Successfully Saved!");
        }
        return "areas/add-area";
    }

    @GetMapping(value = "delete-area/{id}")
    public String del(@PathVariable("id") Long id){
        if(id != null) {
            this.areaRepo.deleteById(id);
        }
        return "redirect:/area/area-list";
    }

    @GetMapping(value = "area-list")
    public String list(Model model){
        model.addAttribute("list",this.areaRepo.findAll());
        return "areas/area-list";
    }
}
