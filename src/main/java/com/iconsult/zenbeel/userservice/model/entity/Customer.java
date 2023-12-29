package com.iconsult.zenbeel.userservice.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String cnic;
    private String email;
    private String userName;
    private String password;
    private String securityPicture;
//    @OneToMany(mappedBy = customer, cascade = CascadeType.ALL)
//    List<Account> accountList;
}
