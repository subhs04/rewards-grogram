package com.rewards.rewards.program.exception;

/**
 * Custom exception for handling situation when customer
 * is not found.
 */
public class CustomerNotFoundException extends RuntimeException{
    public  CustomerNotFoundException(String message){
        super(message);
    }
}
