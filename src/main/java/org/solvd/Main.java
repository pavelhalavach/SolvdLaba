package main.java.org.solvd;

import main.java.org.solvd.structure.application.Rating;
import main.java.org.solvd.structure.taxipark.Car;
import main.java.org.solvd.structure.TaxiCompany;
import main.java.org.solvd.structure.application.Client;
import main.java.org.solvd.structure.application.Path;
import main.java.org.solvd.structure.application.Request;
import main.java.org.solvd.structure.taxipark.Driver;
import main.java.org.solvd.structure.taxipark.Assistant;
import main.java.org.solvd.structure.taxipark.TaxiPark;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Logger logger = LogManager.getRootLogger();
        Logger logger_err = LogManager.getLogger("errors");
        Car car1 = new Car("Tesla", "8023", LocalDate.of(2022, 8, 27));
        Car car2 = new Car("Kia", "5243", LocalDate.of(2021, 2, 23));
        Car car3 = new Car("Kia", "6454", LocalDate.of(2021, 3, 12));
        Car car4 = new Car("Kia", "8654", LocalDate.of(2021, 5, 3));
        Car car5 = new Car("Kia", "9755", LocalDate.of(2022, 7, 5));
        Car car6 = new Car("Kia", "3234", LocalDate.of(2024, 1, 9));
        Path path1 = new Path(1, 5);
        Path path2 = new Path(3, 12);
        Path path3 = new Path(8, 18);
        Client client1 = new Client("Tom", "Allan");
        Client client2 = new Client("John", "Smith");
        Client client3 = new Client("George", "Brown");
        Client client4 = new Client("Anna", "Miller");
        client1.addRating(new Rating("alright", 5));
        Driver driver1 = new Driver("Jim", "Lorens", car2);
        Driver driver2 = new Driver("Bill", "Daniels", car1);
        Driver driver3 = new Driver("Will", "Davis", car5);
        Request request1 = new Request(client1, driver1, path1, false);
        TimeUnit.MILLISECONDS.sleep(200);
        Request request2 = new Request(client2, driver2, path3, false);
        TimeUnit.MILLISECONDS.sleep(200);
        Request request3 = new Request(client3, driver3, path2, false);
        TimeUnit.MILLISECONDS.sleep(200);
        Request request4 = new Request(client4, driver1, path1, false);
        TimeUnit.MILLISECONDS.sleep(200);
        Request request5 = new Request(client2, driver2, path2, false);
        TimeUnit.MILLISECONDS.sleep(200);
        Request request6 = new Request(client3, driver3, path3, false);
        Set<Request> requests1 = new HashSet<>(Arrays.asList(request2, request6, request4, request1, request3, request5));
        Set<Request> requests2 = new HashSet<>(List.of(request2));
        Set<Request> requests3 = new HashSet<>(Arrays.asList(request1, request2, request5));
        Set<Request> requests6;
        Assistant assistant1 = new Assistant("Cavin", "Tores", 5600);
        Assistant assistant2 = new Assistant("Nika", "Daron", 5000);
        TaxiPark taxiPark1 = new TaxiPark("Warsaw", assistant1, requests1);
        TaxiPark taxiPark2 = new TaxiPark("Krakow", assistant2, requests3);
        TaxiPark taxiPark3 = new TaxiPark("Krakow", assistant2, requests2);
        taxiPark1.printPricesByDate();

        TaxiCompany taxiCompany = new TaxiCompany(new HashSet<>(Arrays.asList(taxiPark1, taxiPark2, taxiPark3)));

        taxiCompany.printAllDrivers();
        Scanner input = new Scanner(System.in);
        logger.info("Creation of a new request");


    }
}