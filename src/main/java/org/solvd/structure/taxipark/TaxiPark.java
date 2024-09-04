package main.java.org.solvd.structure.taxipark;

import main.java.org.solvd.structure.exceptions.NoRatingException;
import main.java.org.solvd.structure.exceptions.NoRequestsException;
import main.java.org.solvd.structure.interfaces.DriversReceivable;
import main.java.org.solvd.structure.application.Request;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TaxiPark implements DriversReceivable {
    private String location;
    private Assistant assistant;
//    private Driver[] drivers;
    private Accounting accounting;
    private Request[] requests;

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
        try {
            System.out.println("Drivers:");
            for (var driver : getAllDrivers()){
                System.out.println(driver.getName() + " " + driver.getSurname());
            }
        } catch (NoRequestsException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Set<Driver> getAllDrivers() throws NoRequestsException {
        if (requests == null){
            throw new NoRequestsException();
        }
        Set<Driver> drivers = new HashSet<>();
        for (var request : requests){
            drivers.add(request.getDriver());
        }
//        Driver[] drivers = new Driver[requests.length];
//        int indicator = 0;
//        for(int i = 0; i < requests.length; i++){
//            for (var driver : drivers){
//                if(driver != null && driver.equals(requests[i].getDriver())){
//                    indicator = 1;
//                    break;
//                }
//            }
//            if(indicator != 1){
//                drivers[i] = requests[i].getDriver();
//            }
//            indicator = 0;
//        }
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
