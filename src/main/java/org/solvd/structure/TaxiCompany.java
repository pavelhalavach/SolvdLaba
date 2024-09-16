package main.java.org.solvd.structure;

import main.java.org.solvd.structure.exceptions.NoDriversException;
import main.java.org.solvd.structure.interfaces.DriversReceivable;
import main.java.org.solvd.structure.taxipark.Driver;
import main.java.org.solvd.structure.taxipark.TaxiPark;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TaxiCompany implements DriversReceivable {
    private Set<TaxiPark> taxiParks;
    private static final Logger logger = LogManager.getRootLogger();

    static {
        logger.info("The Taxi Company was created");
        logger.info("It has many TaxiParks");
        logger.info("With information about location, accounting department, assistant " +
                "and requests");
        logger.info("Every request includes driver, client, path and price");
        logger.info("Each request has a function to ask a feedback about the drive");
    }

    public TaxiCompany(Set<TaxiPark> taxiParks) {
        this.taxiParks = taxiParks;
    }

    @Override
    public void printAllDrivers(){
        logger.trace("Executing taxiCompany.printAllDrivers()");
        logger.info("Drivers in the Company:");
        for (var driver : getAllDrivers()){
            logger.info(driver.getName() + " " + driver.getSurname());
        }
        logger.trace("Closing taxiCompany.printAllDrivers()");
    }

    @Override
    public Set<Driver> getAllDrivers() {
        Set<Driver> drivers = new HashSet<>();
        for (var taxiPark : taxiParks) {
            try {
                drivers.addAll(taxiPark.getAllDrivers());
            } catch (NoDriversException e) {
                logger.error(e.getMessage());
            }
        }
        return drivers;

    }

    public void printTotalIncomeAfterBills(){
        System.out.println("Total income of the company after bills: " +
                calculateTotalIncomeAfterBills());
    }

    private float calculateTotalIncomeAfterBills(){
        float income = 0;
        for (var taxiPark : taxiParks){
            income += taxiPark.calculateIncomeAfterBills();
        }
        return income;
    }

    @Override
    public String toString() {
        return "TaxiCompany{" +
                "taxiParks=" + Objects.toString(taxiParks) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxiCompany that = (TaxiCompany) o;
        return Objects.equals(taxiParks, that.taxiParks);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(taxiParks);
    }

    public Set<TaxiPark> getTaxiParks() {
        return taxiParks;
    }

    public void setTaxiParks(Set<TaxiPark> taxiParks) {
        this.taxiParks = taxiParks;
    }
}
