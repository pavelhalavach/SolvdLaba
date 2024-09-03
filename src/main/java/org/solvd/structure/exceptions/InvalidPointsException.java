package main.java.org.solvd.structure.exceptions;

public class InvalidPointsException extends Exception{
    public InvalidPointsException() {
        super("Points must be positive and EndPoint must be greater than StartPoint");
    }
}
