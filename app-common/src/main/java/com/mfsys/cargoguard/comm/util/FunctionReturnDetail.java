package com.backend.tms.comm.util;


import com.backend.tms.comm.constant.FunctionReturnType;
import com.backend.tms.comm.constant.TransactionMessageCode;

public class FunctionReturnDetail<T> extends FunctionReturn {

    private T[] arguments;


    public FunctionReturnDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

    public FunctionReturnDetail(FunctionReturnType type, TransactionMessageCode messageCode, T[] arguments) {
        super(type, messageCode);
        this.arguments = arguments;
    }

    public T[] getArguments() {
        return arguments;
    }


}
