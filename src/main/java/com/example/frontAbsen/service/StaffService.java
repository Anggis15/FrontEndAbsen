package com.example.frontAbsen.service;

import com.example.frontAbsen.dto.response.ResStaffDTO;
import com.example.frontAbsen.dto.response.ResSupervisiorDTO;
import com.example.frontAbsen.dto.response.ResponseAbsenDTO;
import com.example.frontAbsen.dto.validasi.RegisEmployeeDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author asd a.k.a. Anggi Saputra
Java Developer
Created on 20/11/24 00.36
@Last Modified 20/11/24 00.36
Version 1.0
*/
@Service
public class StaffService {

    public String signSupervisior(Model model,
                                  HttpServletRequest request){
        String url = "http://localhost:8080/api/staff/findStafftoSign";
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
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    });
            Map<String, Object> resultData = (Map<String, Object>) response.getBody().get("data");
            List<Map<String, Object>> menuList = (List<Map<String, Object>>) resultData.get("content");
            System.out.println(menuList);
            List<ResStaffDTO> listContent = new ArrayList<>();
            for (Map<String, Object> mapz : menuList) {
                ResStaffDTO resStaffDTO = new ResStaffDTO();
                resStaffDTO.setId(Long.parseLong(String.valueOf(mapz.get("id"))));
                resStaffDTO.setNama(String.valueOf(mapz.get("nama")));
                resStaffDTO.setCreatedAt(String.valueOf(mapz.get("createdAt")));
                listContent.add(resStaffDTO);
            }
            model.addAttribute("listContent", listContent);

            String urlSu = "http://localhost:8080/api/staff/findStafftoSign";

            ResponseEntity<Map<String, Object>> responseSu = restTemplate.exchange(
                    urlSu,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    });
            Map<String, Object> resultDataSu = (Map<String, Object>) responseSu.getBody().get("data");
            List<Map<String, Object>> menuListSu = (List<Map<String, Object>>) resultDataSu.get("content");
            System.out.println(menuListSu);
            List<ResSupervisiorDTO> listContentSu = new ArrayList<>();
            for (Map<String, Object> mapz : menuListSu) {
                ResSupervisiorDTO resStaffDTO = new ResSupervisiorDTO();
                resStaffDTO.setId(Long.parseLong(String.valueOf(mapz.get("id"))));
                resStaffDTO.setNama(String.valueOf(mapz.get("nama")));
                resStaffDTO.setCreatedAt(String.valueOf(mapz.get("createdAt")));
                listContentSu.add(resStaffDTO);
                System.out.println(resStaffDTO.getNama());
            }
            model.addAttribute("listContentSu", listContentSu);
                return "menu/signSupervisior";
            } catch (Exception e) {
                return "menu/signSupervisior";
            }
    }


    public String regisStaff(RedirectAttributes redirectAttributes,
                                HttpServletRequest request,
                                RegisEmployeeDTO regisEmployeeDTO){
        String url = "http://localhost:8080/api/user/v1/register/staff";
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
            return "redirect:/page/daftar-staff";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("failedReg", "Gagal melakukan registrasi");
            return "redirect:/page/daftar-staff";
        }
    }

    public String listStaff(Model model, HttpServletRequest request){
        String url = "http://localhost:8080/api/staff/findall";
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
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Map<String,Object>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<Map<String, Object>>() {});
            Map<String,Object> resultData = (Map<String, Object>) response.getBody().get("data");
            List<Map<String,Object>> menuList = (List<Map<String,Object>>) resultData.get("content");
            List<ResStaffDTO> listContent = new ArrayList<>();
            for(Map<String,Object> mapz : menuList){
                ResStaffDTO resStaffDTO = new ResStaffDTO();
                resStaffDTO.setNama(String.valueOf(mapz.get("nama")));
                resStaffDTO.setCreatedAt(String.valueOf(mapz.get("createdAt")));
                listContent.add(resStaffDTO);
            }

            model.addAttribute("listContent", listContent);
            return "menu/listStaff";
        }catch (Exception e){
            return "menu/listStaff";
        }
    }
}
