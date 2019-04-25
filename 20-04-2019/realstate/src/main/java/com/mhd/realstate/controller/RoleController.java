package com.mhd.realstate.controller;



import com.mhd.realstate.entity.Role;
import com.mhd.realstate.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/role/")
public class RoleController {

    @Autowired
    private RoleRepo roleRepo;

    @GetMapping(value = "add-role")
    public String viewAdd(Model model){
        model.addAttribute("role",new Role());
        return "roles/add-role";
    }
    @PostMapping(value = "add-role")
    public String add(@Valid Role role, BindingResult result, Model model){
        if(result.hasErrors()){
            return "roles/add-role";
        }
        if(roleRepo.existsRoleByRoleName(role.getRoleName())){
            model.addAttribute("rejectMsg","Already Have This Entry");
        }else{
            role.setRoleName(role.getRoleName().toUpperCase());
            this.roleRepo.save(role);
            model.addAttribute("successMsg","Successfully Saved!");
        }

        return "roles/add-role";
    }
    @GetMapping(value = "edit-role/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id){
        model.addAttribute("role",roleRepo.getOne(id));
        return "roles/edit-role";
    }
    @PostMapping(value = "edit-role/{id}")
    public String edit(@Valid Role role, BindingResult result, Model model,@PathVariable("id") Long id){
        if(result.hasErrors()){
            return "roles/edit-role";
        }
        Optional<Role> rol = this.roleRepo.findByRoleName(role.getRoleName());
        if(rol.get().getId() != id){
            model.addAttribute("rejectMsg","Already Have This Entry");
            return "roles/edit-role";
        }else{
            role.setId(id);
            role.setRoleName(role.getRoleName().toUpperCase());
            this.roleRepo.save(role);
        }

        return "redirect:/role/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id){
        if(id != null) {
            this.roleRepo.deleteById(id);
        }
        return "redirect:/role/list-role";
    }

    @GetMapping(value = "list-role")
    public String list(Model model){
          model.addAttribute("list",this.roleRepo.findAll());
        return "roles/list-role";
    }

}
