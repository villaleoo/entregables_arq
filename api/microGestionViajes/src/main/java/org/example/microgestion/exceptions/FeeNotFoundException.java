package org.example.microgestion.exceptions;

public class FeeNotFoundException extends RuntimeException{
    public FeeNotFoundException(String message){
        super(message);
    }
}
