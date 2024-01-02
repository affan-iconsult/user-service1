package com.corebanking.system.service.Impl;

import com.corebanking.system.exception.ResourceNotFoundException;
import com.corebanking.system.repository.CustomerRepository;
import com.corebanking.system.model.dto.CreateOTPDto;
import com.corebanking.system.model.dto.LoginDto;
import com.corebanking.system.model.dto.UserDto;
import com.corebanking.system.model.entity.Customer;
import com.corebanking.system.service.CustomerService;
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
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public String addUser(Customer customer) {
        Optional<Customer> cnicExist = customerRepository.findByCnicAndEmailAndMobileNumber(customer.getCnic(),customer.getEmail(),customer.getMobileNumber());
        if(cnicExist.isPresent())
        {
            logger.error("Customer Already registered against cnic No: {} email: {} mobile No: ",customer.getCnic(),customer.getEmail(), customer.getMobileNumber());
            return "Customer Already registered against cnic : "+customer.getCnic() +"email: "+customer.getEmail()+" password:  "+ customer.getMobileNumber();
        }
        Customer newCustomer = save(customer);
        logger.info("customer save Successfully");
        return "customer register successfully against cnic number : "+ customer.getCnic();
    }

    /*@Override
    public Customer register(Customer customer)
    {
        logger.info("Sign up Request recvived");
        if(customer != null)
        {
            Customer customer1 = addUser(customer);
            logger.info("Customer has been saved with Id {}", customer1.getId());
            return new ResponseEntity<>(customer, HttpStatus.OK).getBody();
        }
        else
        {
            logger.error("Entity not found");
        }
        return customer;
    }*/

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
        logger.info("Customer has been updated with Id {}", updateCustomer.getId());

        return ResponseEntity.ok(updateCustomer).getBody();
    }

    public Customer createOTP(CreateOTPDto createOTPDto)
    {
        return null;
    }
    public String login(LoginDto loginDto) {
        Customer customer = customerRepository.findByEmail(loginDto.getEmail());
        if(customer!=null)
        {
            if(customer.getEmail().equals(loginDto.getEmail()) && customer.getPassword().equals(loginDto.getPassword()))
            {
                return "welcome";
            }
            else {
                throw new ResourceNotFoundException("invalid email or password");
            }
        }
        return "invalid email";
    }
}
