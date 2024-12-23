package com.example.frontAbsen.controller;

/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author asd a.k.a. Anggi Saputra
Java Developer
Created on 17/11/24 14.25
@Last Modified 17/11/24 14.25
Version 1.0
*/

import com.example.frontAbsen.dto.validasi.RegisEmployeeDTO;
import com.example.frontAbsen.service.EmployeeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("page")
public class SupervisioController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/daftarSupervisior")
    public String getRegisterSupervisior(Model model){
        RegisEmployeeDTO regisEmployeeDTO = new RegisEmployeeDTO();

        model.addAttribute("regisEmployee", regisEmployeeDTO);

        return "menu/daftarEmployee";
    }
    @PostMapping("/daftarSupervisior")
    public String registerSupervisior(HttpServletRequest request,
                                      Model model,
                                      @ModelAttribute("regisEmployee") RegisEmployeeDTO regisEmployeeDTO,
                                      RedirectAttributes redirectAttributes){
        return employeeService.regisEmployee(redirectAttributes, request, regisEmployeeDTO);
    }
}
