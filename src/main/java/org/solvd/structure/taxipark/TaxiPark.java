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
    private Map<String, Float> pricesByDate;
    private final AdvertisingDepartment advertisingDepartment;
    private static final Logger logger = LogManager.getLogger("taxi");
    private static final Logger loggerRoot = LogManager.getRootLogger();

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
        loggerRoot.trace("Executing taxiPark.printAllDrivers()");
        try {
            logger.info("Drivers in the TaxiPark:");
            getAllDrivers().stream()
                    .forEach(driver -> logger.info(driver.getName() + " " + driver.getSurname()));
//            for (var driver : getAllDrivers()){
//                logger.info(driver.getName() + " " + driver.getSurname());
//            }
        } catch (NoDriversException e) {
            logger.error(e.getMessage());
        }
        loggerRoot.trace("Closing taxiPark.printAllDrivers()");
    }

    @Override
    public Set<Driver> getAllDrivers() throws NoDriversException {
        if (requests == null){
            throw new NoDriversException(this);
        }
        Set<Driver> drivers = new HashSet<>();
        requests.stream().forEach(request -> drivers.add(request.getDriver()));
//        for (var request : requests){
//            drivers.add(request.getDriver());
//        }
        return drivers;
    }

    public void sendPromotionsToClients(){
        logger.info("Advertising Department in TaxiPark " + this);
        advertisingDepartment.sendPromotionToAll(requests);
    }

    public void printPricesByDate(){
        loggerRoot.trace("Executing taxiPark.printPricesByDate()");
        try {
            Map<String, Float> map = getPricesByDate();
            logger.info("Time and price of requests in " + this);

            map.entrySet().stream().forEach(kv -> {
                String uncut = kv.getKey();
                String regex = " ";
                String[] arr = uncut.split(regex);
                logger.info(arr[1] + " : " + kv.getValue());
            });
//            for (Map.Entry<String, Float> kv : map.entrySet()){
//                String uncut = kv.getKey();
//                String regex = " ";
//                String[] arr = uncut.split(regex);
//                logger.info(arr[1] + " : " + kv.getValue());
//            }
        } catch (NoRequestsException e) {
            logger.error(e.getMessage());
        }
        loggerRoot.trace("Closing taxiPark.printPricesByDate()");
    }

    private Map<String, Float> getPricesByDate() throws NoRequestsException {
        if (requests == null){
            throw new NoRequestsException(this);
        }
        Map<String, Float> map = new HashMap<>();
        requests.stream()
                .forEach(request -> map.put(request.hashCode() + " " + request.getDate(), request.getPrice()));
//        for (var request : requests){
//            map.put(request.hashCode() + " " + request.getDate(), request.getPrice());
//        }
        this.pricesByDate = map;
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

    public Optional<Set<Request>> getRequests() {
        return Optional.ofNullable(requests);
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

    public AdvertisingDepartment getAdvertisingDepartment() {
        return advertisingDepartment;
    }
}
