package com.backend.service;

import org.springframework.stereotype.Service;

@Service
public class TemplateService {

    public String createOtpEmailContent(String userName, String otp) {
        String emailTemplate = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        /* Your CSS styles */\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "    <h2>Your One-Time Password (OTP) for UCO</h2>\n" +
                "    <p>Hello %s,</p>\n" +
                "    <p>Your One-Time Password (OTP) for accessing UCO is:</p>\n" +
                "    <p class=\"otp\">[%s]</p>\n" +
                "    <p>Please enter this OTP to proceed. Remember, this OTP is valid for only 1 minutes and should not be shared with anyone.</p>\n" +
                "    \n" +
                "    <p>Thank you,<br>The UCO Team</p>\n" +
                "    <div class=\"footer\">\n" +
                "       \n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
        // TODO: Need to fetch above string from database
        return String.format(emailTemplate, userName, otp);
    }
}
