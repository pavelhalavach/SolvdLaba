package main.java.org.solvd.structure.exceptions;

public class NoRequestsException extends Exception{
    public NoRequestsException(Object object) {
        super("List of requests is empty in the " + object);
    }

    public NoRequestsException() {
        super("Taxi Park's list of requests is empty");
    }
}
