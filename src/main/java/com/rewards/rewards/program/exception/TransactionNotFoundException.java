package com.rewards.rewards.program.exception;
/**
 * Custom exception for handling situation when transaction
 * didnt occur for the specified period.
 */
public class TransactionNotFoundException extends RuntimeException{

    public TransactionNotFoundException(String message){
        super(message);
    }
}
