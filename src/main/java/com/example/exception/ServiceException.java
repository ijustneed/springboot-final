package com.example.exception;

import lombok.Getter;

/**
 * @version 1.0
 * @author： xiaoxu
 * @date： 2022-05-29 14:44
 */

@Getter
public class ServiceException extends RuntimeException{
    private String code;
    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }

}
