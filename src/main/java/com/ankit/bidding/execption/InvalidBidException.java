package com.ankit.bidding.execption;

public class InvalidBidException extends RuntimeException {
    public InvalidBidException(String message) {
        super(message);
    }
}

