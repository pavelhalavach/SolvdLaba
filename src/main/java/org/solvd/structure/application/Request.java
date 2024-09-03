package org.solvd.structure.application;
import org.solvd.structure.AppUser;
import org.solvd.structure.FeedbackCollectable;
import org.solvd.structure.RatingPrintable;
import org.solvd.structure.taxipark.Driver;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Request implements RatingPrintable {
    private static final float multiplier = 8;
    private float price;
    private Client client;
    private Driver driver;
    private Path path;
    private LocalDate date;
    private Rating driverFeedback;
    private Rating clientFeedback;
    private final Service service;

    public Request(Client client, Driver driver, Path path, boolean wantFeedback) {
        this.client = client;
        this.driver = driver;
        this.path = path;
        this.date = LocalDate.now();
        this.service = new Service();
        setPrice();

        if (wantFeedback) {
            System.out.println(this.toString());
            setFeedback();
        }
    }

    public void setFeedback(){
        Rating[] ratings = service.collectFeedback(client, driver);
        clientFeedback = ratings[0];
        driverFeedback = ratings[1];
    }

    @Override
    public void printRatings(){
        System.out.println("Rating of driver " + driver.toString() + ":");
        if (clientFeedback == null) System.out.println("there is no rating");
        else {
            System.out.println(clientFeedback.toString());
        }
        System.out.println("Rating of client " + client.toString() + ":");
        if (driverFeedback == null) System.out.println("there is no rating");
        else {
            System.out.println(driverFeedback.toString());
        }
    }

    @Override
    public String toString() {
        return "Request{" +
                "price=" + price +
                ", client=" + client +
                ", driver=" + driver +
                ", path=" + path +
                ", date=" + date +
                ", driverFeedback=" + driverFeedback +
                ", clientFeedback=" + clientFeedback +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Float.compare(price, request.price) == 0 && Objects.equals(client, request.client) && Objects.equals(driver, request.driver) && Objects.equals(path, request.path) && Objects.equals(date, request.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, client, driver, path, date);
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Rating getDriverFeedback() {
        return driverFeedback;
    }

    public void setDriverFeedback(Rating driverFeedback) {
        this.driverFeedback = driverFeedback;
    }

    public Rating getClientFeedback() {
        return clientFeedback;
    }

    public void setClientFeedback(Rating clientFeedback) {
        this.clientFeedback = clientFeedback;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public static float getMultiplier() {
        return multiplier;
    }

//    public static void setMultiplier(float multiplier) {
//        Request.multiplier = multiplier;
//    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    private void setPrice() {
        this.price = this.path.getDistance() * multiplier;
    }
}
