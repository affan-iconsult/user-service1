package com.corebanking.system.repository;

import com.corebanking.system.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    Customer findByEmail(String email);
    Optional<Customer> findByCnicAndEmailAndMobileNumber(String cnic, String email, String mobileNumber);
}