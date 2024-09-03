package main.java.org.solvd.structure.exceptions;

public class NoRatingException extends Exception{
    public NoRatingException(){
        super("There is no rating added");
    }
}
