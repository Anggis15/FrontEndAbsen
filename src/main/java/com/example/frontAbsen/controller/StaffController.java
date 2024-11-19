package com.example.frontAbsen.controller;

/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author asd a.k.a. Anggi Saputra
Java Developer
Created on 20/11/24 00.33
@Last Modified 20/11/24 00.33
Version 1.0
*/

import com.example.frontAbsen.dto.validasi.RegisEmployeeDTO;
import com.example.frontAbsen.dto.validasi.SignSupervisiorDTO;
import com.example.frontAbsen.service.StaffService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("page")
public class StaffController {

    @Autowired
    StaffService staffService;

    @GetMapping("/daftar-staff")
    public String getRegisterSupervisior(Model model){
        RegisEmployeeDTO regisEmployeeDTO = new RegisEmployeeDTO();

        model.addAttribute("regisEmployee", regisEmployeeDTO);

        return "menu/daftarStaff";
    }
    @PostMapping("/daftar-staff")
    public String registerSupervisior(HttpServletRequest request,
                                      Model model,
                                      @ModelAttribute("regisEmployee") RegisEmployeeDTO regisEmployeeDTO,
                                      RedirectAttributes redirectAttributes){
        return staffService.regisStaff(redirectAttributes, request, regisEmployeeDTO);
    }

    @GetMapping("list-staff")
    public String getListStaff(Model model, HttpServletRequest request){
        return staffService.listStaff(model, request);
    }

    @GetMapping("/sign-supervisior")
    public String signSupervisior(Model model, HttpServletRequest request){
        SignSupervisiorDTO signSupervisiorDTO = new SignSupervisiorDTO();
        model.addAttribute("signSupervisor", signSupervisiorDTO);
        return staffService.signSupervisior(model, request);
    }

    @PostMapping("/sign-supervisior")
    public String signSupervisor(HttpServletRequest request,
                                 Model model,
                                 @ModelAttribute("signSupervisor") SignSupervisiorDTO signSupervisiorDTO,
                                 RedirectAttributes redirectAttributes){
        return staffService.signSupervisior(model, request);

    }
}
