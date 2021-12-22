package com.example.parallelTest.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotValidException extends BusinessLogicException{

    private String msg;

    private String code;

    public NotValidException() {
        super();
    }

    public NotValidException(String msg) {
        super();
        setMsg(msg);
    }

    public NotValidException(String code, String msg) {
        super();
        setCode(code);
        setMsg(msg);
    }
}
