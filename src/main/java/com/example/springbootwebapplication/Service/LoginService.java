package com.example.springbootwebapplication.Service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service

public class LoginService {

   public Boolean ValidateUser (String name, String password){
       return name.equalsIgnoreCase("Sumit") && password.equalsIgnoreCase("007");


   }
}
