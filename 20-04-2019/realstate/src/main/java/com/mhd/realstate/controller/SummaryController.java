package com.mhd.realstate.controller;

import com.mhd.realstate.entity.Summary;
import com.mhd.realstate.repo.SummaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SummaryController {
    @Autowired
    private SummaryRepo summaryRepo;

    @GetMapping(value = "/add-summary")
    public String viewAdd(Model model) {
        model.addAttribute("summary", new Summary());
        return "property-single";
    }

    @PostMapping(value = "/add-summary")
    public String add(@Valid Summary summary, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "property-single";
        }
        this.summaryRepo.save(summary);
        model.addAttribute("successMsg", "Successfully Saved!");
        return "property-single";
    }
}
