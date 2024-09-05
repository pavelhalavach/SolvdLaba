package main.java.org.solvd.structure.taxipark;

import main.java.org.solvd.structure.AppUser;
import main.java.org.solvd.structure.application.Client;
import main.java.org.solvd.structure.application.Rating;
import main.java.org.solvd.structure.application.Request;
import main.java.org.solvd.structure.exceptions.NoRatingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public final class Driver extends AppUser {
    private static float percentage = 0.7f;
    private Car car;
    private static final Logger logger = LogManager.getRootLogger();
    private static final Logger logger_err = LogManager.getLogger("errors");

    public Driver(String name,
                  String surname,
                  Car car) {
        super(name, surname);
        this.car = car;
    }



    @Override
    public String toString() {
        return "{" + getName() +
                " " + getSurname() +
                ", car: " + car +
                "}";
    }

    @Override
    public void printRatings() throws NoRatingException {
        logger_err.info("Executing driver.printRatings()");
        if (getRatings() == null) throw new NoRatingException();
        logger.info("Rating for " + getName() + " " + getSurname());
        for (var rating : getRatings()){
            logger.info(rating);
        }
        logger_err.info("Closing driver.printRatings()");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(getName(), driver.getName()) &&
                Objects.equals(getSurname(), driver.getSurname()) &&
                Objects.equals(car, driver.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), car);
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public static float getPercentage() {
        return percentage;
    }

    public static void setPercentage(float percentage) {
        Driver.percentage = percentage;
    }
}
