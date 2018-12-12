package com.gosuncn.myartifact.myblog.common.util.exception;

public class ServiceException extends RuntimeException {
    private int errorCode = -1;
    public ServiceException(String msg) {


    }
}
