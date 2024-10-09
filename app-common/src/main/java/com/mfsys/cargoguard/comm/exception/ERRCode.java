package com.backend.tms.comm.exception;

public enum ERRCode implements ErrorMessage {

    JWT_EXPIRED_EXCEPTION("ERR_SEC_0001", "JWT Expired Exception"),

    USER_ALREADY_REGISTERED("ERR_USER_0001", "User Already Registered"),
    OLD_PASSWORD("ERR_USER_0002", "Old Password not allowed"),
    INVALID_OTP("ERR_USER_0003", "Invalid OTP"),
    USER_NOT_FOUND("ERR_USER_0004", "User not found"),
    INCORRECT_PASSWORD("ERR_USER_0005", "Invalid Password"),
    SAME_PASSWORD("ERR_USER_0006", "same password cannot be set as new password"),
    APPLICATION_PENDING("ERR_USER_0007", "Application is pending"),
    PHONE_NUMBER_ALREADY_REGISTERED("ERR_USER_0008", "Phone number already registered"),
    EMAIL_ALREADY_REGISTERED("ERR_USER_0010", "Email already registered"),
    USER_ALREADY_REGISTERED_WITH_EMAIL("ERR_USER_0009", "User already registered"),
    ;
    private String code;
    private String description;

    private ERRCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
