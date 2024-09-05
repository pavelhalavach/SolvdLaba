package main.java.org.solvd.structure.taxipark;

import main.java.org.solvd.structure.exceptions.NoDriversException;
import main.java.org.solvd.structure.exceptions.NoRatingException;
import main.java.org.solvd.structure.exceptions.NoRequestsException;
import main.java.org.solvd.structure.interfaces.DriversReceivable;
import main.java.org.solvd.structure.application.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TaxiPark implements DriversReceivable {
    private String location;
    private Assistant assistant;
    private Accounting accounting;
    private Request[] requests;
    private static final Logger logger = LogManager.getRootLogger();
    private static final Logger logger_err = LogManager.getLogger("errors");
    public TaxiPark(String location, Assistant assistant, Request[] requests) {
        this.location = location;
        this.assistant = assistant;
        this.requests = requests;
        this.accounting = new Accounting();
    }

    public float calculateIncomeAfterBills(){
        return accounting.getIncomeAfterBills(requests, assistant);
    }

    @Override
    public void printAllDrivers(){
        logger_err.info("Executing taxiPark.printAllDrivers()");
        try {
            logger.info("Drivers:");
            for (var driver : getAllDrivers()){
                logger.info(driver.getName() + " " + driver.getSurname());
            }
        } catch (NoDriversException e) {
            logger.error(e.getMessage() + this);
        }
        logger_err.info("Closing taxiPark.printAllDrivers()");
    }

    @Override
    public Set<Driver> getAllDrivers() throws NoDriversException {
        if (requests == null){
            throw new NoDriversException();
        }
        Set<Driver> drivers = new HashSet<>();
        for (var request : requests){
            drivers.add(request.getDriver());
        }
        return drivers;
    }

    @Override
    public String toString() {
        return "TaxiPark{" +
                "location='" + location +
                ", assistant=" + assistant +
                ", accounting=" + accounting +
                ", requests=" + Arrays.toString(requests) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxiPark taxiPark = (TaxiPark) o;
        return Objects.equals(location, taxiPark.location) && Objects.equals(assistant, taxiPark.assistant) && Objects.equals(accounting, taxiPark.accounting) && Arrays.equals(requests, taxiPark.requests);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(location, assistant, accounting);
        result = 31 * result + Arrays.hashCode(requests);
        return result;
    }

    public Request[] getRequests() {
        return requests;
    }

    public void setRequests(Request[] requests) {
        this.requests = requests;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    public Accounting getAccounting() {
        return accounting;
    }

    public void setAccounting(Accounting accounting) {
        this.accounting = accounting;
    }
}
