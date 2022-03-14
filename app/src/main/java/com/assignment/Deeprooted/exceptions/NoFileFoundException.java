package com.assignment.Deeprooted.exceptions;

public class NoFileFoundException extends Exception {
    public NoFileFoundException() {
        super();
      }

    public NoFileFoundException(String message) {
        super(message);
      }

    @Override
    public String toString() {
        return "No File Found!";
    }
}
