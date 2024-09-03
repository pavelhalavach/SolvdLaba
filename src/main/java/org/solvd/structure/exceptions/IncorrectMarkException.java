package main.java.org.solvd.structure.exceptions;

import java.util.InputMismatchException;

public class IncorrectMarkException extends Exception {
    public IncorrectMarkException(){
        super("Mark should be integer, in range 1-5");
    }
}
