package com.ankit.bidding.execption;

public class BusinessValidationException extends Exception{

    public BusinessValidationException(){
        super();
    }

    public BusinessValidationException(String message){
        super(message);
    }

    public BusinessValidationException(String message, Throwable cause){
        super(message,cause);
    }

    public BusinessValidationException(Throwable cause){
        super(cause);
    }
}
