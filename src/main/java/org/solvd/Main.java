package main.java.org.solvd;

import main.java.org.solvd.structure.CustomLinkedList;
import main.java.org.solvd.structure.application.*;
import main.java.org.solvd.structure.taxipark.*;


import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Car car1 = new Car("Tesla", "8023", LocalDate.of(2022, 8, 27));
        Car car2 = new Car("Kia", "5243", LocalDate.of(2021, 2, 23));
        Car car3 = new Car("Kia", "6454", LocalDate.of(2021, 3, 12));
        Car car4 = new Car("Kia", "8654", LocalDate.of(2021, 5, 3));
        Car car5 = new Car("Kia", "9755", LocalDate.of(2022, 7, 5));
        Car car6 = new Car("Kia", "3234", LocalDate.of(2024, 1, 9));
        Path path1 = new Path(1, 5);
        Path path2 = new Path(3, 12);
        Path path3 = new Path(8, 18);
        Client client1 = new Client("Tom", "Allan", "+48869328192");
        Client client2 = new Client("John", "Smith", "+48771239123");
        Client client3 = new Client("George", "Brown", "+2298501");
        Client client4 = new Client("Anna", "Miller", "+9812353");
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


//        TaxiCompany taxiCompany = new TaxiCompany(new HashSet<>(Arrays.asList(taxiPark1, taxiPark2, taxiPark3)));

//        taxiCompany.printAllDrivers();
        taxiPark1.sendPromotionsToClients("Only today 20% off for >5 km drives!");
//        taxiPark1.printPricesByDate();

//        Scanner input = new Scanner(System.in);
//        logger.info("Creation of a new request");


    }
}