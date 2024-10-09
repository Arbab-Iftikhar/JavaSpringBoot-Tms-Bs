package com.backend.tms.comm.constant;

public enum FunctionReturnType {
    INFO(1), QUESTION(2), WRANING(3), SUCCESS(4);

    private int code;

    FunctionReturnType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
