package com.mycompany.message;

public class MessageNotFoundException extends Throwable{
    public MessageNotFoundException(String message) {
        super(message);
    }
}
