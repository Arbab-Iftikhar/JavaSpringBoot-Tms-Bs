package com.backend.tms.comm.util;


import com.backend.tms.comm.constant.FunctionReturnType;
import com.backend.tms.comm.constant.TransactionMessageCode;

public class FunctionReturn {

    protected int returnCode;
    protected String messageCode;

    public FunctionReturn() {
        super();
        // TODO Auto-generated constructor stub
    }

    public FunctionReturn(FunctionReturnType type, TransactionMessageCode messageCode) {
        this.returnCode = type.getCode();
        this.messageCode = messageCode.getCode();
    }

    public int getReturnCode() {
        return returnCode;
    }

    public String getMessageCode() {
        return this.messageCode;
    }

}
