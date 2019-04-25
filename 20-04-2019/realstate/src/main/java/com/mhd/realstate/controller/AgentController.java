package com.mhd.realstate.controller;

import com.mhd.realstate.entity.Agents;
import com.mhd.realstate.repo.AgentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.validation.Valid;

@Controller
public class AgentController {
    private static String UPLOADED_FOLDER = "src/main/resources/static/img/";
    @Autowired
    private AgentRepo agentRepo;
    @Autowired
    private ImageOptimizer imageOptimizer;



    @GetMapping(value = "/add-agent")
    public String viewbbAdd(Model model){
        model.addAttribute("agent",new Agents());
        return "add-agents";
    }

    @PostMapping(value = "/add-agent")
    public String add(@Valid Agents agents, BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "add-agents";
        }

        try {
            //////////////////////For Image Upload start /////////////////////
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            agents.setFileName("new-" + file.getOriginalFilename());
            agents.setFileSize(file.getSize());
            // user.setFile(file.getBytes());
            agents.setFilePath("/img/" + "new-" + file.getOriginalFilename());
            agents.setFileExtension(file.getContentType());
            //////////////////////For Image Upload end/////////////////////

            this.agentRepo.save(agents);
            model.addAttribute("agents", new Agents());
            model.addAttribute("successMsg", "Congratulations! Successfully added data.");
            imageOptimizer.optimizeImage(UPLOADED_FOLDER, file, 0.3f, 100, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "add-agents";
    }
}
