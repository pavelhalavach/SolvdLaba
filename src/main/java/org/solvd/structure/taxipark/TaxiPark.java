package main.java.org.solvd.structure.taxipark;

import main.java.org.solvd.structure.application.Request;
import main.java.org.solvd.structure.exceptions.NoDriversException;
import main.java.org.solvd.structure.exceptions.NoRequestsException;
import main.java.org.solvd.structure.interfaces.DriversReceivable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TaxiPark implements DriversReceivable {
    private String location;
    private Assistant assistant;
    private Accounting accounting;
    private Set<Request> requests;
    private final AdvertisingDepartment advertisingDepartment;

    private static final Logger logger = LogManager.getRootLogger();
//    private static final Logger logger_err = LogManager.getLogger("errors");
    public TaxiPark(String location, Assistant assistant, Set<Request> requests) {
        this.location = location;
        this.assistant = assistant;
        this.requests = requests;
        this.accounting = new Accounting();
        this.advertisingDepartment = new AdvertisingDepartment();
    }

    public float calculateIncomeAfterBills(){
        return accounting.getIncomeAfterBills(requests, assistant);
    }

    @Override
    public void printAllDrivers(){
        logger.trace("Executing taxiPark.printAllDrivers()");
        try {
            logger.info("Drivers in the TaxiPark:");
            for (var driver : getAllDrivers()){
                logger.info(driver.getName() + " " + driver.getSurname());
            }
        } catch (NoDriversException e) {
            logger.error(e.getMessage());
        }
        logger.trace("Closing taxiPark.printAllDrivers()");
    }

    @Override
    public Set<Driver> getAllDrivers() throws NoDriversException {
        if (requests == null){
            throw new NoDriversException(this);
        }
        Set<Driver> drivers = new HashSet<>();
        for (var request : requests){
            drivers.add(request.getDriver());
        }
        return drivers;
    }

    public void sendPromotionsToClients(String message){
        advertisingDepartment.requestPhoneNumbers(requests);
        advertisingDepartment.sendPromotionToAll(message);
    }

    public void printPricesByDate(){
        logger.trace("Executing taxiPark.printPricesByDate()");
        try {
            HashMap<String, Float> map = getPricesByDate();
            logger.info("Time and price of requests in " + this);
            for (Map.Entry<String, Float> kv : map.entrySet()){
                String uncut = kv.getKey();
                String regex = " ";
                String[] arr = uncut.split(regex);
                logger.info(arr[1] + " : " + kv.getValue());
            }
        } catch (NoRequestsException e) {
            logger.error(e.getMessage());
        }
        logger.trace("Closing taxiPark.printPricesByDate()");
    }

    private HashMap<String, Float> getPricesByDate() throws NoRequestsException {
        if (requests == null){
            throw new NoRequestsException(this);
        }
        HashMap<String, Float> map = new HashMap<>();
        for (var request : requests){
            map.put(request.hashCode() + " " + request.getDate(), request.getPrice());
        }
        return map;
    }
    @Override
    public String toString() {
        return "TaxiPark{" +
                "location='" + location +
                ", assistant=" + assistant +
                ", accounting=" + accounting +
                ", requests=" + requests +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxiPark taxiPark = (TaxiPark) o;
        return Objects.equals(location, taxiPark.location) &&
                Objects.equals(assistant, taxiPark.assistant) &&
                Objects.equals(accounting, taxiPark.accounting) &&
                Objects.equals(requests, taxiPark.requests);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(location, assistant, accounting);
        result = 31 * result + Objects.hashCode(requests);
        return result;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
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
