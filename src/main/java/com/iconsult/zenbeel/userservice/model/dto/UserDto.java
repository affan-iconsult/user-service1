package com.iconsult.zenbeel.userservice.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String emailAddress;
    private String mobileNumber;
    private String cnic;
    private String dateOfBirth;
    private String firstName;
    private String middleName;
    private String lastName;
}
