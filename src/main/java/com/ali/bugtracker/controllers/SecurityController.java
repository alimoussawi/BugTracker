package com.ali.bugtracker.controllers;

import com.ali.bugtracker.entities.Employee;
import com.ali.bugtracker.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SecurityController {

    @Autowired
    EmployeeRepository employeeRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/login")
    public  String displaySignIn(){
        return "/main/sign-in";
    }

    @GetMapping("/register")
    public String displayRegisterPage(Model model){
        Employee employee =new Employee();
        model.addAttribute("employee",employee);
        return "/main/register";
    }

    @PostMapping("/register/save")
    public String saveEmployee(@Valid Employee employee, Model model , BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "/main/register";
        }
        else {
            employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
            employeeRepo.save(employee);
            return "redirect:/login";
        }
    }
}
