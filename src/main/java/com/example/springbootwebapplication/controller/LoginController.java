package com.example.springbootwebapplication.controller;

import com.example.springbootwebapplication.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

//org.springframework.ui provides utility classes and interfaces for handling UI-related tasks in Spring MVC.

@Controller
@SessionAttributes("name")
public class LoginController {

//    @RequestMapping(value= "/login")
////    Without @ResponseBody, Spring will treat the return value "Hello World dummy" as a view name and try to resolve it to a template (e.g., Hello World dummy.jsp), leading to an error if the view doesn't exist.
////    @ResponseBody //@ResponseBody tells Spring to directly write the return value of a method to the HTTP response body, instead of resolving it as a view name.
//    public String LoginMessage(@RequestParam String name, ModelMap model){
////        Model and ModelMap in this package are used to pass data to views
//        model.put("name", name);
//        return "login";
//    }
//    @ResponseBody
//    public String welcomeMessage(){
//        return "welcome to spring boot web application";
//    }
    private LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String showLoginPage(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String handleLogin (ModelMap model , @RequestParam String name, @RequestParam String password){
        System.out.println("Login name: " + name);
        System.out.println("Password before validation: " + password);
        if (loginService.ValidateUser(name, password)) {
            System.out.println("Login successful for user: " + name);
            System.out.println("Password: " + password);
            model.put("name", name);
            return "welcome";
        }
        else{
            model.put("errorMessage", "Invalid Credentials");
            return "login";
        }
    }

}
