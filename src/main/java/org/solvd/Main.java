package main.java.org.solvd;

import main.java.org.solvd.structure.application.Rating;
import main.java.org.solvd.structure.exceptions.InvalidPointsException;
import main.java.org.solvd.structure.taxipark.Car;
import main.java.org.solvd.structure.TaxiCompany;
import main.java.org.solvd.structure.application.Client;
import main.java.org.solvd.structure.application.Path;
import main.java.org.solvd.structure.application.Request;
import main.java.org.solvd.structure.taxipark.Driver;
import main.java.org.solvd.structure.taxipark.Assistant;
import main.java.org.solvd.structure.taxipark.TaxiPark;


import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaxiCompany taxiCompany = createTaxiCompany();
//        taxiCompany.printTotalIncomeAfterBills();
//        taxiCompany.printAllDrivers();
//        System.out.println(taxiCompany.toString());

    }

    public static TaxiCompany createTaxiCompany(){
        Car car1 = new Car("Tesla", "8023", LocalDate.of(2022, 8, 27));
        Car car2 = new Car("Kia", "5243", LocalDate.of(2021, 2, 23));
        Car car3 = new Car("Kia", "6454", LocalDate.of(2021, 3, 12));
        Car car4 = new Car("Kia", "8654", LocalDate.of(2021, 5, 3));
        Car car5 = new Car("Kia", "9755", LocalDate.of(2022, 7, 5));
        Car car6 = new Car("Kia", "3234", LocalDate.of(2024, 1, 9));

        Path path1 = null;
        try {
            path1 = new Path(1, 5);
        } catch (InvalidPointsException e) {
            throw new RuntimeException(e);
        }
        Path path2 = null;
        try {
            path2 = new Path(5, 12);
        } catch (InvalidPointsException e) {
            throw new RuntimeException(e);
        }
        Path path3 = null;
        try {
            path3 = new Path(1, 25);
        } catch (InvalidPointsException e) {
            throw new RuntimeException(e);
        }

//        Scanner input = new Scanner(System.in);
//        while(true){
//            try {
//                System.out.println("enter a1 and a2");
//                int a1 = input.nextInt();
////                input.nextLine();
//                int a2 = input.nextInt();
//                path1 = new Path(a1, a2);
//                break;
////            path2 = new Path(5, 12);
////            path3 = new Path(1, 25);
//            } catch (InvalidPointsException e) {
//                System.out.println(e.getMessage());;
//            }
//        }


//        System.out.println(path1.getDistance());


        Client client1 = new Client("Tom", "Allan");
        Client client2 = new Client("John", "Smith");
        Client client3 = new Client("George", "Brown");
        Client client4 = new Client("Anna", "Miller");

        client1.addRating(new Rating("alright", 5));

        Driver driver1 = new Driver("Jim", "Lorens", car2);
        Driver driver2 = new Driver("Bill", "Daniels", car1);
        Driver driver3 = new Driver("Will", "Davis", car5);

        Request request1 = new Request(client1, driver1, path1, false);
        Request request2 = new Request(client2, driver2, path3, false);
        Request request3 = new Request(client3, driver3, path2, false);
        Request request4 = new Request(client4, driver1, path1, false);
        Request request5 = new Request(client2, driver2, path2, false);
        Request request6 = new Request(client3, driver3, path3, false);


//        client1.printRatings();
        request1.setFeedback();

        request1.printRatings();

//        client3.printRatings();
//        driver3.printRatings();
//        request6.printRatings();

        Request[] requests1 = {request4, request5, request4, request1, request3, request5};
        Request[] requests2 = {request2};
        Request[] requests3 = {request1, request2, request5};

        Assistant assistant1 = new Assistant("Cavin", "Tores", 5600);
        Assistant assistant2 = new Assistant("Nika", "Daron", 5000);

        TaxiPark taxiPark1 = new TaxiPark("Warsaw", assistant1, requests1);
        TaxiPark taxiPark2 = new TaxiPark("Krakow", assistant2, requests3);

        return new TaxiCompany(new TaxiPark[]{taxiPark1, taxiPark2});
    }
}