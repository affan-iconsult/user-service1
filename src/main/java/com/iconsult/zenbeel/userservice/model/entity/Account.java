package com.iconsult.zenbeel.userservice.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Account {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cnic;
    private String dob;
    private String accountNumber;
    private String accountType;
    private String branch;
    private String bankName;
    private String city;
    private String email;
    private String cellNumber;
    private String cnicIssuance;
    private String cnicExpiry;
    private String purposeOfAccount;
    private String sourceOfIncome;
    private String residentialAddress;
    private String lineOfBusiness;
    private String businessAddress;
    @ManyToOne
    @JoinColumn(name = "customer_id")
            @JsonBackReference
    Customer customer;
}
