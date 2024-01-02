package com.corebanking.system.service;


import com.corebanking.system.model.dto.CreateOTPDto;
import com.corebanking.system.model.entity.Customer;

public interface CustomerService
{
    String addUser(Customer customer);

    Customer createOTP(CreateOTPDto createOTPDto);

    //Customer register(Customer customer);

    void deleteUser(Long id);

    Customer updateCustomer(Customer customer);

    Customer findByEmailAddress(String email);
}
