package com.example.frontAbsen.service;

import com.example.frontAbsen.dto.validasi.RegisEmployeeDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author asd a.k.a. Anggi Saputra
Java Developer
Created on 19/11/24 22.58
@Last Modified 19/11/24 22.58
Version 1.0
*/
@Service
public class EmployeeService {

    public String regisEmployee(RedirectAttributes redirectAttributes,
                                HttpServletRequest request,
                                RegisEmployeeDTO regisEmployeeDTO){
        String url = "http://localhost:8080/api/user/v1/register/supervisior";
        Cookie[] cookies = request.getCookies();
        String token = null;
        for (Cookie cookie:cookies){
            if(cookie.getName().equalsIgnoreCase("authLogin")){
                token = cookie.getValue();
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);  // Set your token here
        headers.set("Content-Type", "application/json");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> entity = new HttpEntity<>(regisEmployeeDTO ,headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    String.class);

            redirectAttributes.addFlashAttribute("success", "Sukses melakukan registrasi");
            return "redirect:/page/daftarSupervisior";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("failedReg", "Gagal melakukan registrasi");
            return "redirect:/page/daftarSupervisior";
        }
    }
}
