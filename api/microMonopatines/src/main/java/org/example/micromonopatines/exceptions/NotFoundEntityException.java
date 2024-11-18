package org.example.micromonopatines.exceptions;

public class NotFoundEntityException extends RuntimeException{
    public NotFoundEntityException(String message){
        super(message);
    }
}