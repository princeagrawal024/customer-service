package com.demo.project.exception;

public class OperationFailedException extends RuntimeException{
    public OperationFailedException(String message){
        super(message);
    }
    public OperationFailedException(){
        super();
    }
}
