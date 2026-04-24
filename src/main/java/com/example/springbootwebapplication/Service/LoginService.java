package com.example.springbootwebapplication.Service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {

   public Boolean ValidateUser (String name, String password){
       return name.equalsIgnoreCase("Sumit") ;


   }
}
