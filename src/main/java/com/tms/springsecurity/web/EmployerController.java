package com.tms.springsecurity.web;


import com.tms.springsecurity.dto.EmployerDto;
import com.tms.springsecurity.model.Applicant;
import com.tms.springsecurity.model.Employer;
import com.tms.springsecurity.service.ApplicantService;
import com.tms.springsecurity.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/employer")
public class EmployerController {

    private final EmployerService service;
    private final ApplicantService applicantService;

    @GetMapping
    public ModelAndView viewApplicant() {
        ModelAndView modelAndView = new ModelAndView("employer");
        List<Applicant> applicant = service.getApplicant();
        modelAndView.addObject("emp_applicant", applicant);
        List<Applicant> applicants = applicantService.getAll();
        modelAndView.addObject("applicants", applicants);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView addApplicant(@RequestParam(name = "id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("employer");
        service.addApplicant(id);
        List<Applicant> applicant = service.getApplicant();
        modelAndView.addObject("emp_applicant", applicant);
        return modelAndView;
    }

    @GetMapping("/getapplicant")
    public ModelAndView getApplicant() {
        ModelAndView modelAndView = new ModelAndView("list_applicant");
        List<Applicant> applicant = service.getApplicant();
        modelAndView.addObject("emp_applicant", applicant);
        List<Applicant> applicants = applicantService.getAll();
        modelAndView.addObject("applicants", applicants);
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteApplicant(@RequestParam(name = "id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("employer");
        service.deleteApplicant(id);
        List<Applicant> applicant = service.getApplicant();
        modelAndView.addObject("emp_applicant", applicant);
        return modelAndView;
    }
    @GetMapping("/update")
    public ModelAndView update(){
        ModelAndView modelAndView = new ModelAndView("update_employer");
        Employer employer = service.getCurrentEmpl();
        modelAndView.addObject("employer", employer);
        return modelAndView;
    }
    @PostMapping("/update")
    public ModelAndView update(EmployerDto dto){
        ModelAndView modelAndView = new ModelAndView("update_employer");
        service.update(dto);
        Employer employer = service.getCurrentEmpl();
        modelAndView.addObject("employer", employer);
        return modelAndView;
    }
    @GetMapping("/delaccount")
    public String delAccount(){
        service.delete();
        return "redirect:/";
    }
}
