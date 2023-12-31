package com.example.tictactoe.Exceptions;

public class InvalidMoveException extends Exception{
    public InvalidMoveException(String message){
        super(message);
    }
}
