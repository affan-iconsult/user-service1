package com.iconsult.zenbeel.userservice.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class AccountDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "cnic required")
    @Size(min = 13, message = "invalid cnic")
    private String cnic;

//    @Past(message = "Date of birth must be in the past")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dob;

    private String accountNumber;

    @NotBlank(message = "Account type is required")
    private String accountType;

    @NotBlank(message = "Branch is required")
    private String branch;

    @NotBlank(message = "Bank name is required")
    private String bankName;

    @NotBlank(message = "City is required")
    private String city;

    @Email(message = "Invalid email address")
    private String email;

    @Pattern(regexp = "\\+\\d{10,14}", message = "Invalid cell number format")
    private String cellNumber;

    @NotBlank(message = "CNIC Issuance is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must be in yyyy-MM-dd format")
    private String cnicIssuance;

    @NotBlank(message = "CNIC Expiry is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must be in yyyy-MM-dd format")
    private String cnicExpiry;

    @NotBlank(message = "Purpose of account is required")
    private String purposeOfAccount;

    @NotBlank(message = "Source of income is required")
    private String sourceOfIncome;

    @NotBlank(message = "Residential address is required")
    private String residentialAddress;

    @NotBlank(message = "Line of business is required")
    private String lineOfBusiness;

    @NotBlank(message = "Business address is required")
    private String businessAddress;

    private Long customerId;

}


    /*
    private String name;
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

}*/
