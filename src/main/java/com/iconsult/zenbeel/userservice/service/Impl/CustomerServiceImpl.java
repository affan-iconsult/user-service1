package com.iconsult.zenbeel.userservice.service.Impl;

import com.iconsult.zenbeel.userservice.exception.ResourceNotFoundException;
import com.iconsult.zenbeel.userservice.model.dto.CreateOTPDto;
import com.iconsult.zenbeel.userservice.model.dto.UserDto;
import com.iconsult.zenbeel.userservice.model.entity.Customer;
import com.iconsult.zenbeel.userservice.repository.CustomerRepository;
import com.iconsult.zenbeel.userservice.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer addUser(Customer customer)
    {
        return save(customer);
    }

    @Override
    public Customer register(Customer customer)
    {
        LOGGER.info("Sign up Request recvived");
        if(customer != null)
        {
            Customer customer1 = addUser(customer);
            LOGGER.info("Customer has been saved with Id {}", customer1.getId());
            return new ResponseEntity<>(customer, HttpStatus.OK).getBody();
        }
        else
        {
            LOGGER.error("Entity not found");
        }
        return customer;
    }

    @Override
    public void deleteUser(Long id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public Customer save(Customer customer)
    {
        return customerRepository.save(customer);
    }

    public Customer findByEmailAddress(String email)
    {
        return Optional.ofNullable(this.customerRepository.findByEmail(email)).orElseThrow(() -> new ResourceNotFoundException(String.format("User with email %s not found", email)));
    }

    public List<Customer> findAllUsers()
    {
        return this.customerRepository.findAll();
    }

    public Customer updateCustomer(UserDto userDto)
    {

        Customer updateCustomer = this.customerRepository.findByEmail(userDto.getEmailAddress());

        if(updateCustomer == null)
        {
            throw new ResourceNotFoundException(String.format("User with email %s not found", userDto.getEmailAddress()));
        }

        updateCustomer.setFirstName(userDto.getFirstName());
        updateCustomer.setLastName(userDto.getLastName());
        updateCustomer.setEmail(userDto.getEmailAddress());

        save(updateCustomer);
        LOGGER.info("Customer has been updated with Id {}", updateCustomer.getId());

        return ResponseEntity.ok(updateCustomer).getBody();
    }

    public Customer createOTP(CreateOTPDto createOTPDto)
    {

    }
}
