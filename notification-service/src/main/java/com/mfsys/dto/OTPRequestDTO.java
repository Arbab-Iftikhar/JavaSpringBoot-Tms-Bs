package com.backend.dto;

import lombok.Data;

@Data
public class OTPRequestDTO {
    private String userName;
    private String email;
    private String phoneNumber;
    private String otp;
    private String subject;
}
