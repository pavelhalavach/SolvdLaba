package main.java.org.solvd.structure.interfaces;

import main.java.org.solvd.structure.exceptions.NoRequestsException;
import main.java.org.solvd.structure.taxipark.Driver;

import java.util.Set;

public interface DriversReceivable {
    void printAllDrivers();
    Set<Driver> getAllDrivers() throws NoRequestsException;
}
