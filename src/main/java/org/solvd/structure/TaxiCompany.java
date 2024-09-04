package main.java.org.solvd.structure;

import main.java.org.solvd.structure.exceptions.NoRequestsException;
import main.java.org.solvd.structure.interfaces.DriversReceivable;
import main.java.org.solvd.structure.taxipark.Driver;
import main.java.org.solvd.structure.taxipark.TaxiPark;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TaxiCompany implements DriversReceivable {
    private TaxiPark[] taxiParks;
    private static final Logger logger = LogManager.getRootLogger();
    private static final Logger logger_err = LogManager.getLogger("errors");
    static {
        logger.info("The Taxi Company was created");
        logger.info("It has many TaxiParks");
        logger.info("With information about location, accounting department, assistant " +
                "and requests");
        logger.info("Every request includes driver, client, path and price");
        logger.info("Each request has a function to ask a feedback about the drive");
    }

    public TaxiCompany(TaxiPark[] taxiParks) {
        this.taxiParks = taxiParks;
    }

    @Override
    public void printAllDrivers(){
        logger.info("Drivers:");
        for (var driver : getAllDrivers()){
            logger.info(driver.getName() + " " + driver.getSurname());
        }
    }

    @Override
    public Set<Driver> getAllDrivers() {
        logger_err.info("Executing taxiCompany.getAllDrivers()");
//        int length = 0;
//        Driver[] drivers = new Driver[length];
        Set<Driver> drivers = new HashSet<>();
        for (var taxiPark : taxiParks) {
            try {
                drivers.addAll(taxiPark.getAllDrivers());
            } catch (NoRequestsException e) {
                logger_err.error(e.getMessage());
            }
//            length += taxiPark.getAllDrivers().length;
        }
        logger_err.info("Closing taxiCompany.getAllDrivers()");
//        int j = 0;
//
//        int indicator;
//        for (var taxiPark : taxiParks){
//            indicator = 0;
//            for (int i = 0; i < taxiPark.getAllDrivers().length; i++){
//                for (var driver : drivers){
//                    if(driver != null && driver.equals(taxiPark.getAllDrivers()[i])){
//                        indicator = 1;
//                        break;
//                    }
//                }
//                if(indicator != 1){
//                    drivers[i+j] = taxiPark.getAllDrivers()[i];
//                }
//            }
//            j++;
//        }
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
                "taxiParks=" + Arrays.toString(taxiParks) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxiCompany that = (TaxiCompany) o;
        return Arrays.equals(taxiParks, that.taxiParks);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(taxiParks);
    }

    public TaxiPark[] getTaxiParks() {
        return taxiParks;
    }

    public void setTaxiParks(TaxiPark[] taxiParks) {
        this.taxiParks = taxiParks;
    }
}
