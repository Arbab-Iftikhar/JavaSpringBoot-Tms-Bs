package com.backend.tms.comm.commonservices;

import java.security.SecureRandom;
import org.springframework.stereotype.Component;

@Component
public class OtpService {
    private final SecureRandom secureRandom = new SecureRandom();
    private final int OTP_LENGTH = 6;

    public String generateOtp() {
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(secureRandom.nextInt(10));
        }

        return otp.toString();
    }
}
