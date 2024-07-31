package com.ankit.bidding.execption;

public class SystemFailureExeption extends Exception{

    public SystemFailureExeption(){
        super();
    }

    public SystemFailureExeption(String message){
        super(message);
    }

    public SystemFailureExeption(String message, Throwable cause){
        super(message,cause);
    }

    public SystemFailureExeption(Throwable cause){
        super(cause);
    }
}
