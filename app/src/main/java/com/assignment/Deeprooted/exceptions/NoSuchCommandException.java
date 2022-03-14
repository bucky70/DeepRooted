package com.assignment.Deeprooted.exceptions;

public class NoSuchCommandException extends Exception{

    public NoSuchCommandException() {
        super();
      }

    public NoSuchCommandException(String message) {
        super(message);
      }

    @Override
    public String toString() {
        return "No such Command Found!";
    }
}