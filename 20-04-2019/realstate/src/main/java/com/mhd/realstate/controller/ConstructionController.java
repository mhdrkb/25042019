package com.mhd.realstate.controller;

import com.mhd.realstate.entity.ConstructionType;
import com.mhd.realstate.repo.ConstructionRepo;
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
@RequestMapping(value = "/types/")
public class ConstructionController {
    @Autowired
    private ConstructionRepo constructionRepo;

    @GetMapping(value = "add-type")
    public String viewAdd(ConstructionType constructionType, Model model){
        model.addAttribute("types",new ConstructionType());
        return "types/add-type";
    }

    @PostMapping(value = "add-type")
    public String add(@Valid ConstructionType constType, BindingResult result, Model model){
        if(result.hasErrors()){
            return "types/add-type";
        }
        if(constructionRepo.existsConstructionTypeByConstTypeName(constType.getConstTypeName())){
            model.addAttribute("rejectMsg","Already Have This Entry");
        }else{
            constType.setConstTypeName(constType.getConstTypeName().toUpperCase());
            this.constructionRepo.save(constType);
            model.addAttribute("types",new ConstructionType());
            model.addAttribute("successMsg","Successfully Saved!");
        }
        return "types/add-type";
    }

    @GetMapping(value = "delete-type/{id}")
    public String del(@PathVariable("id") Long id){
        if(id != null) {
            this.constructionRepo.deleteById(id);
        }
        return "redirect:/types/type-list";
    }

    @GetMapping(value = "type-list")
    public String list(Model model){
        model.addAttribute("list",this.constructionRepo.findAll());
        return "types/type-list";
    }
}
