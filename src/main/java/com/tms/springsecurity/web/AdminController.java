package com.tms.springsecurity.web;

import com.tms.springsecurity.dto.ApplicantDto;
import com.tms.springsecurity.dto.EmployerDto;
import com.tms.springsecurity.dto.UserDto;
import com.tms.springsecurity.service.AdminService;
import com.tms.springsecurity.service.DbUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final DbUserDetailService userDetailService;
    private final AdminService service;

    @GetMapping
    public ModelAndView getListEmployer(){
        ModelAndView modelAndView = new ModelAndView("admin_employer");
        List<EmployerDto> employers = service.getEmployers();
        modelAndView.addObject("employers", employers);
        return modelAndView;
    }
    @PostMapping
    public String deleteEmployer(@RequestParam(name = "id") Integer id){
        service.deleteEmployer(id);
        return "redirect:/admin";
    }

    @GetMapping("/applicants")
    public ModelAndView getListApplicant(){
        ModelAndView modelAndView = new ModelAndView("admin_applicant");
        List<ApplicantDto> applicants = service.getApplicants();
        modelAndView.addObject("applicants", applicants);
        return modelAndView;
    }
    @PostMapping("/delete")
    public String deleteApplicant(@RequestParam (name = "id") Integer id){
        service.deleteApplicant(id);
        return "redirect:/admin/applicants";
    }
//    @GetMapping
//    public String loadPageAdmin(){
//        return "admin";
//    }
//    @PostMapping
//    public String loginAdmin(){
//        return "main";
//    }
    @GetMapping("/add")
    public String addAdmin(){
        return "main";
    }
    @PostMapping("/add")
    public String addAdmin(UserDto dto){
        userDetailService.save(dto);
        return "main";
    }
}
