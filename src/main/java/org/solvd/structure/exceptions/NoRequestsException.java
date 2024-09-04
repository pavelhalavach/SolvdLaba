package main.java.org.solvd.structure.exceptions;

public class NoRequestsException extends Exception{
    public NoRequestsException() {
        super("Taxi Park's list of requests is empty");
    }
}
