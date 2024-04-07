package com.tms.springsecurity.web;

import com.tms.springsecurity.dto.ApplicantDto;
import com.tms.springsecurity.model.*;
import com.tms.springsecurity.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping("/applicant")
public class ApplicantController {
    private final ApplicantService service;
    private final LanguageService languageService;
    private final SpecialityService specialityService;


    @GetMapping
    public ModelAndView applicant() {
        ModelAndView modelAndView = new ModelAndView("applicant");
        Applicant applicant = service.getCurrentAppl();
        modelAndView.addObject("info_applicant", applicant);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView save(ApplicantDto dto){
        ModelAndView modelAndView = new ModelAndView("applicant");
        service.save(dto);
        return modelAndView;
    }
    @GetMapping("/update")
    public ModelAndView update() {
        ModelAndView modelAndView = new ModelAndView("update_applicant");
        List<Language> languages = languageService.findAll();
        modelAndView.addObject("lang", languages);
        List<Speciality> specialities = specialityService.getAll();
        modelAndView.addObject("spec", specialities);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        Integer id = principal.getId();
        ApplicantDto byId = service.findById(id);
        modelAndView.addObject("info_applicant", byId);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(ApplicantDto dto) {
        ModelAndView modelAndView = new ModelAndView("update_applicant");
        List<Language> languages = languageService.findAll();
        modelAndView.addObject("lang", languages);
        List<Speciality> specialities = specialityService.getAll();
        modelAndView.addObject("spec", specialities);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        Applicant applicant = principal.getApplicant();
        modelAndView.addObject("info_applicant", applicant);
        service.update(dto);
        return modelAndView;
    }
    @GetMapping("/delaccount")
    public String delete(){
        service.delete();
        return "redirect:/";
    }
}
