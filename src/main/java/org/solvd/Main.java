package main.java.org.solvd;

import main.java.org.solvd.structure.AppUser;
import main.java.org.solvd.structure.CustomLinkedList;
import main.java.org.solvd.structure.Person;
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
//        Logger logger = LogManager.getRootLogger();
//        Logger logger_err = LogManager.getLogger("errors");
//        Car car1 = new Car("Tesla", "8023", LocalDate.of(2022, 8, 27));
//        Car car2 = new Car("Kia", "5243", LocalDate.of(2021, 2, 23));
//        Car car3 = new Car("Kia", "6454", LocalDate.of(2021, 3, 12));
//        Car car4 = new Car("Kia", "8654", LocalDate.of(2021, 5, 3));
//        Car car5 = new Car("Kia", "9755", LocalDate.of(2022, 7, 5));
//        Car car6 = new Car("Kia", "3234", LocalDate.of(2024, 1, 9));
//        Path path1 = new Path(1, 5);
//        Path path2 = new Path(3, 12);
//        Path path3 = new Path(8, 18);
//        Client client1 = new Client("Tom", "Allan");
//        Client client2 = new Client("John", "Smith");
//        Client client3 = new Client("George", "Brown");
//        Client client4 = new Client("Anna", "Miller");
//        client1.addRating(new Rating("alright", 5));
//        Driver driver1 = new Driver("Jim", "Lorens", car2);
//        Driver driver2 = new Driver("Bill", "Daniels", car1);
//        Driver driver3 = new Driver("Will", "Davis", car5);
//        Request request1 = new Request(client1, driver1, path1, false);
//        TimeUnit.MILLISECONDS.sleep(200);
//        Request request2 = new Request(client2, driver2, path3, false);
//        TimeUnit.MILLISECONDS.sleep(200);
//        Request request3 = new Request(client3, driver3, path2, false);
//        TimeUnit.MILLISECONDS.sleep(200);
//        Request request4 = new Request(client4, driver1, path1, false);
//        TimeUnit.MILLISECONDS.sleep(200);
//        Request request5 = new Request(client2, driver2, path2, false);
//        TimeUnit.MILLISECONDS.sleep(200);
//        Request request6 = new Request(client3, driver3, path3, false);
//        Set<Request> requests1 = new HashSet<>(Arrays.asList(request2, request6, request4, request1, request3, request5));
//        Set<Request> requests2 = new HashSet<>(List.of(request2));
//        Set<Request> requests3 = new HashSet<>(Arrays.asList(request1, request2, request5));
//        Set<Request> requests6;
//        Assistant assistant1 = new Assistant("Cavin", "Tores", 5600);
//        Assistant assistant2 = new Assistant("Nika", "Daron", 5000);
//        TaxiPark taxiPark1 = new TaxiPark("Warsaw", assistant1, requests1);
//        TaxiPark taxiPark2 = new TaxiPark("Krakow", assistant2, requests3);
//        TaxiPark taxiPark3 = new TaxiPark("Krakow", assistant2, requests2);
//        taxiPark1.printPricesByDate();
//
//        TaxiCompany taxiCompany = new TaxiCompany(new HashSet<>(Arrays.asList(taxiPark1, taxiPark2, taxiPark3)));
//
//        taxiCompany.printAllDrivers();
//        Scanner input = new Scanner(System.in);
//        logger.info("Creation of a new request");

        LinkedList<Integer> i = new LinkedList<>();
        i.add(1);
        i.add(2);
        i.add(3);
        i.add(4);
        i.add(5);
        i.add(2, 100);
//        System.out.println(i);

        CustomLinkedList<Integer> ii = new CustomLinkedList<>();
        ii.add(1);
        ii.add(2);
        ii.add(3);
        ii.add(4);
        ii.add(5);
        System.out.println(ii);
//        i.removeAll(ii);
//        ii.removeAll(i);
//        System.out.println(i);
//        System.out.println(ii);

        System.out.println(ii);
        System.out.println(ii.isEmpty());
        System.out.println(ii.contains(3) + " " + ii.contains(5) + " " + ii.contains(6));


        class Employee {
            int salary = 60000;
        }
        class Engineer extends Employee {
            private int benefits;
            public Engineer(int b){
                this.benefits = b;
            }
        }
        Engineer a = new Engineer(5);
        Employee b = new Employee();
        Engineer c = new Engineer(8);

        CustomLinkedList<Employee> iii = new CustomLinkedList<>();
        iii.add(b);
        iii.add(a);
        iii.add(c);
        System.out.println(iii);
        CustomLinkedList<Employee> i4 = new CustomLinkedList<>();
        i4.add(c);
        i4.add(a);
        i4.add(b);
        System.out.println(i4);


        System.out.println(i4.equals(iii));
//        System.out.println(iii.containsAll(i4));
//        iii.addAll(i4);
//        System.out.println(iii);
//        iii.removeAll(i4);
//        System.out.println(iii);
//        System.out.println("TEST");
//        LinkedList<Engineer> test = new LinkedList<>();
//        test.add(a);
//        test.add(new Engineer(6));
//        System.out.println(test);
//        System.out.println(iii);
////        System.out.println(test.removeAll(iii));
//        System.out.println(iii.removeAll(test));
//        System.out.println(test);
//        System.out.println(iii);
//        System.out.println(iii.get(0));

//        iii.add(3);
//        iii.clear();

//        System.out.println(iii);
    }
}