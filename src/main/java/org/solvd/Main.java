package main.java.org.solvd;

import main.java.org.solvd.structure.TaxiCompany;
import main.java.org.solvd.structure.application.*;
import main.java.org.solvd.structure.enums.CarModel;
import main.java.org.solvd.structure.enums.Currency;
import main.java.org.solvd.structure.taxipark.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Logger logger = LogManager.getLogger("taxi");
        Car car1 = new Car(CarModel.TESLA_MODEL_S, "8023", LocalDate.of(2022, 8, 27));
        Car car2 = new Car(CarModel.KIA_RIO, "5243", LocalDate.of(2021, 2, 23));
        Car car3 = new Car(CarModel.TOYOTA_COROLLA, "6454", LocalDate.of(2021, 3, 12));
        Car car4 = new Car(CarModel.VOLVO_EM90, "8654", LocalDate.of(2021, 5, 3));
        Car car5 = new Car(CarModel.BMW_M2, "9755", LocalDate.of(2022, 7, 5));
        Car car6 = new Car(CarModel.KIA_RIO, "3234", LocalDate.of(2024, 1, 9));
        Path path1 = new Path(1, 50);
        Path path2 = new Path(3, 6);
        Path path3 = new Path(8, 18);
        Client client1 = new Client("Tom", "Allan", "+48869328192");
        Client client2 = new Client("John", "Smith", "+48771239123");
        Client client3 = new Client("George", "Brown", "+2298501");
        client3.addRating(new Rating("asd", 3));
        client3.addRating(new Rating("gg", 5));
        client3.addRating(new Rating("nxcd", 5));
        client3.addRating(new Rating("nxcd", 5));
        Client client4 = new Client("Anna", "Miller", "+9812353");
        client1.addRating(new Rating("alright", 5));
        Driver driver1 = new Driver("Jim", "Lorens", car2);
        Driver driver2 = new Driver("Bill", "Daniels", car1);
        Driver driver3 = new Driver("Will", "Davis", car5);
        driver3.addRating(new Rating("asd", 2));
        driver3.addRating(new Rating("gg", 1));
        driver3.addRating(new Rating("nxcd", 3));
        driver3.addRating(new Rating("nxcd", 1));
//        System.out.println(driver3.getDriverStatus());
        Request request1 = new Request(client1, driver1, path1, Currency.PLN, false);
        TimeUnit.MILLISECONDS.sleep(200);
        Request request2 = new Request(client2, driver2, path3, Currency.PLN, false);
        TimeUnit.MILLISECONDS.sleep(200);
        Request request3 = new Request(client3, driver3, path2, Currency.PLN, false);
        TimeUnit.MILLISECONDS.sleep(200);
        Request request4 = new Request(client4, driver1, path1, Currency.USD, false);
        TimeUnit.MILLISECONDS.sleep(200);
        Request request5 = new Request(client2, driver2, path2, Currency.EUR, false);
        Set<Request> requests1 = new HashSet<>(Arrays.asList(request2, request4, request1, request3, request5));
        Set<Request> requests2 = new HashSet<>(List.of(request2));
        Set<Request> requests3 = new HashSet<>(Arrays.asList(request1, request2, request5));
        Set<Request> requests6 = null;
        Assistant assistant1 = new Assistant("Cavin", "Tores", 5600);
        Assistant assistant2 = new Assistant("Nika", "Daron", 5000);
        TaxiPark taxiPark1 = new TaxiPark("Warsaw", assistant1, requests1);
        TaxiPark taxiPark2 = new TaxiPark("Krakow", assistant2, requests3);
        TaxiPark taxiPark3 = new TaxiPark("Krakow", assistant2, requests2);



        TaxiCompany taxiCompany = new TaxiCompany(new HashSet<>(Arrays.asList(taxiPark1, taxiPark2, taxiPark3)));

        taxiCompany.printAllDrivers();
        taxiPark1.printPricesByDate();
        taxiPark1.sendPromotionsToClients();

//        logger.info("Creation of a new request:");
        Request request6 = new Request(client3, driver3, path3, Currency.PLN, false);
        logger.info("Average rating of the client:");
        request6.getClient().printAvgRating();



        logger.info("REFLECTION");
        Class<? extends Request> reqClass = request6.getClass();
        Class<? extends Client> clientClass = client3.getClass();
        Class<?> userClass = clientClass.getSuperclass();
        logger.info("CALLING METHOD TO MODIFY AVG RATING OF THE CLIENT");
        try {
            Method addRatingMethod = userClass.getMethod("addRating", Rating.class);
            addRatingMethod.invoke(client3, new Rating("asd", 5));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        logger.info("Average rating of the client:");
        request6.getClient().printAvgRating();

        logger.info("UPDATING FIELD CLIENT IN REQUEST OBJECT");
        try {
            Field field = reqClass.getDeclaredField("client");
            field.setAccessible(true);
            Client newClient = clientClass
                    .getConstructor(String.class, String.class, String.class)
                            .newInstance("NewName", "NewSurname", "NewPhone");
            field.set(request6, newClient);
        } catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException | InstantiationException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        logger.info("Average rating of the client:");
        request6.getClient().printAvgRating();
    }
}