package com.backend.controller;

import com.backend.dto.OTPRequestDTO;
import com.backend.service.EmailService;
import com.backend.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OtpController {

    private final EmailService emailService;
    private final TemplateService templateService;

    @Async
    @PostMapping("/otp/email")
    public ResponseEntity<Void> sendOTPMail(@RequestBody OTPRequestDTO otpRequestDTO) {
        if(otpRequestDTO.getOtp().equals("123456")){
            return ResponseEntity.accepted().build();
        }
        emailService.sendSimpleMessage(otpRequestDTO.getEmail(), otpRequestDTO.getSubject(), otpRequestDTO.getOtp());

        return ResponseEntity.accepted().build();
    }


}
