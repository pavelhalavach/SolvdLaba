package main.java.org.solvd.structure.exceptions;

public class NoDriversException extends Exception{
    public NoDriversException() {
        super("There are no drivers in TaxiPark ");
    }
}
