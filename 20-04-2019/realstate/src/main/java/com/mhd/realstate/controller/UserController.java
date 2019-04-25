package com.mhd.realstate.controller;

import com.mhd.realstate.entity.User;
import com.mhd.realstate.repo.RoleRepo;
import com.mhd.realstate.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping(value = "/user/")
public class UserController {

    private static String UPLOADED_FOLDER = "src/main/resources/static/images/";

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ImageOptimizer imageOptimizer;

    @GetMapping(value = "add")
    public String addShow(User user, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("rolelistAdd", this.roleRepo.findAll());
        return "users/add";
    }

    @PostMapping(value = "add")
    public String userSave(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("rolelistAdd", this.roleRepo.findAll());
            return "users/add";
        }
        if (userRepo.existsByEmail(user.getEmail())){
            model.addAttribute("rejectMsg","Already Have this Entry");
        }else {
            String username = user.getEmail().split("\\@")[0];
            user.setUserName(username);
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setConfirmationToken(UUID.randomUUID().toString());
            this.userRepo.save(user);
            model.addAttribute("successMsg","Successfully Send");
        }

        model.addAttribute("rolelistAdd", this.roleRepo.findAll());
        return "users/add";
    }


    @GetMapping(value = "list")
    public String userIndex(Model model) {
        model.addAttribute("userlist", this.userRepo.findAll());
        return "users/user-list";
    }


    @GetMapping(value = "edit/{id}")
    public String editShow(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", this.userRepo.getOne(id));
        model.addAttribute("rolelistAdd", this.roleRepo.findAll());
        return "users/edit";
    }


    @PostMapping(value = "edit/{id}")
    public String userEdit(@Valid User user, BindingResult bindingResult, @PathVariable("id") Long id, Model model, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("rolelistAdd", this.roleRepo.findAll());
            return "users/edit";
        }
        User u = this.userRepo.getOne(id);
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setMobile(user.getMobile());
        try {
            //////////////////////For Image Upload start /////////////////////
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            user.setFileName("new-" + file.getOriginalFilename());
            user.setFileSize(file.getSize());
            // user.setFile(file.getBytes());
            user.setFilePath("/images/" + "new-" + file.getOriginalFilename());
            user.setFileExtension(file.getContentType());
            //////////////////////For Image Upload end/////////////////////
            this.userRepo.save(u);
            model.addAttribute("user", new User());
            model.addAttribute("success", "Congratulations! Data save sucessfully");
            imageOptimizer.optimizeImage(UPLOADED_FOLDER, file, 0.3f, 100, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("rolelistAdd", this.roleRepo.findAll());
        return "redirect:/user/list";
    }


    @GetMapping(value = "del/{id}")
    public String roledel(@PathVariable("id") Long id) {
        if (id != null) {
            this.userRepo.deleteById(id);
        }
        return "redirect:/user/list";
    }


//    @Autowired
//    private UserRepo repo;
//
//    @Autowired
//    private RoleRepo roleRepo;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    @GetMapping(value = "add")
//    public String viewAdd(Model model){
//        model.addAttribute("user",new User());
//        return "users/add";
//    }
//    @PostMapping(value = "add")
//    public String add(@Valid User user, BindingResult result, Model model){
//        if(result.hasErrors()){
//            return "users/add";
//        }
//        if(repo.existsByEmail(user.getEmail())){
//            model.addAttribute("rejectMsg","Already Have This Entry");
//        }else{
//            String username = user.getEmail().split("\\@")[0];
//            user.setUserName(username);
//            user.setEnabled(true);
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            user.setConfirmationToken(UUID.randomUUID().toString());
//            this.repo.save(user);
//            model.addAttribute("successMsg","Successfully Saved!");
//        }
//
//        return "users/add";
//    }
//
//
//    @GetMapping(value = "edit/{id}")
//    public String viewEdit(Model model, @PathVariable("id") Long id){
//        model.addAttribute("user",repo.getOne(id));
//        return "users/edit";
//    }
//    @PostMapping(value = "edit/{id}")
//    public String edit(@Valid User user, BindingResult result, Model model,@PathVariable("id") Long id){
//        if(result.hasErrors()){
//            return "users/edit";
//        }
//        Optional<User> u = this.repo.findByEmail(user.getEmail());
//        if(u.get().getId() != id){
//            model.addAttribute("rejectMsg","Already Have This Entry");
//            return "users/edit";
//        }else{
//            user.setId(id);
//            this.repo.save(user);
//        }
//
//        return "redirect:/user/list";
//    }
//
//    @GetMapping(value = "del/{id}")
//    public String del(@PathVariable("id") Long id){
//        if(id != null) {
//            this.repo.deleteById(id);
//        }
//        return "redirect:/user/list";
//    }
//
//    @GetMapping(value = "list")
//    public String list(Model model){
//          model.addAttribute("list",this.repo.findAll());
//        return "users/user-list";
//    }

}
