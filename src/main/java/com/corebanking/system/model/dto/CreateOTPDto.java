package com.corebanking.system.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOTPDto {
    private String mobileNumber, email;
}
