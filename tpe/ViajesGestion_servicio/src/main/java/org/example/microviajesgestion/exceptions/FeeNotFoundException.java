package org.example.microviajesgestion.exceptions;

public class FeeNotFoundException extends RuntimeException{
    public FeeNotFoundException(String message){
        super(message);
    }
}
