package com.udemy.flightReservation.controller;

import com.udemy.flightReservation.entity.User;
import com.udemy.flightReservation.repository.UserRepository;
import com.udemy.flightReservation.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    SecurityService securityService;

    @RequestMapping("/registerUser")
    public String showRegistration(){
        return "backoffice/registerUser";
    }

    @RequestMapping("/backoffice")
    public String showLogin(){
        return "backoffice/login";
    }

    @RequestMapping("register-user")
    public String register(@ModelAttribute User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "backoffice/login";
    }

    @PostMapping("login-user")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap){
        Boolean userExist = securityService.login(email,password);
        if(userExist){
            return "backoffice/findFlight";
        }
        else{
            modelMap.addAttribute("msg","Invalid credentials");
        }
        return "backoffice/login";
    }
}
