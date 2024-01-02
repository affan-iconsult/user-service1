package com.corebanking.system.controller;


import com.corebanking.system.model.dto.CreateOTPDto;
import com.corebanking.system.model.dto.LoginDto;
import com.corebanking.system.model.entity.Customer;
import com.corebanking.system.service.Impl.CustomerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class UserController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @GetMapping("/ping")
    public String ping()
    {
        LOGGER.info("User-Service is running {}", LocalDateTime.now());
        return "Hello World";
    }

    @PostMapping("/signup")
    public String register(@RequestBody Customer customer)
    {
        return this.customerServiceImpl.addUser(customer);
    }

    @PostMapping("/createOTP")
    public Customer createOTP(@RequestBody CreateOTPDto createOTPDto)
    {
        return this.customerServiceImpl.createOTP(createOTPDto);
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginPage(@RequestBody LoginDto loginDto)
    {
         String response =  customerServiceImpl.login(loginDto);
         return new ResponseEntity<>(response,HttpStatus.FOUND);
    }
}
