package com.tms.springsecurity.web;

import com.tms.springsecurity.dto.UserDto;
import com.tms.springsecurity.service.DbUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequiredArgsConstructor
@Controller
@RequestMapping
public class RegisterController {
    private final DbUserDetailService service;

    @GetMapping("/registration")
        public String pageRegister(){
        return "registration";
        }
    @PostMapping("/registration")
    public String save(UserDto user){
        service.save(user);
        return "index";
    }
}
