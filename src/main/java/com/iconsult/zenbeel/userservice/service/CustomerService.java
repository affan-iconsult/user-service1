package com.iconsult.zenbeel.userservice.service;


import com.iconsult.zenbeel.userservice.model.dto.CreateOTPDto;
import com.iconsult.zenbeel.userservice.model.entity.Customer;

public interface CustomerService
{
    Customer addUser(Customer customer);

    Customer createOTP(CreateOTPDto createOTPDto);

    Customer register(Customer customer);

    void deleteUser(Long id);

    Customer updateCustomer(Customer customer);

    Customer findByEmailAddress(String email);
}
