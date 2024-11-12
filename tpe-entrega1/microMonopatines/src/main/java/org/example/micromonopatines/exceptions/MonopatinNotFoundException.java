package org.example.micromonopatines.exceptions;

public class MonopatinNotFoundException extends RuntimeException{
    public MonopatinNotFoundException(String message) {
        super(message);
    }
}
