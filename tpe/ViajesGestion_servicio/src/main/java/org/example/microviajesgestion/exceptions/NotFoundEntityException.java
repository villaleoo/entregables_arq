package org.example.microviajesgestion.exceptions;

public class NotFoundEntityException extends RuntimeException{
    public NotFoundEntityException(String message){
        super(message);
    }
}
