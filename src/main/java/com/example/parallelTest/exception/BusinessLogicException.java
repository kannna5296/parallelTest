package com.example.parallelTest.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusinessLogicException extends Exception{

    private String msg;

    private String code;

    public BusinessLogicException() {
        super();
    }

    public BusinessLogicException(String msg) {
        super();
        setMsg(msg);
    }

    public BusinessLogicException(String code, String msg) {
        super();
        setCode(code);
        setMsg(msg);
    }
}
