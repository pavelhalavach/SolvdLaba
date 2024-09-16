package main.java.org.solvd.structure.application;

import main.java.org.solvd.structure.AppUser;
import main.java.org.solvd.structure.exceptions.NoRatingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public final class Client extends AppUser {
    private String phone;
    private static final Logger logger = LogManager.getRootLogger();
//    private static final Logger logger_err = LogManager.getLogger("errors");

    public Client(String name, String surname, String phone) {
        super(name, surname);
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "{" + getName() +
                " " + getSurname() +
                " " + getPhone() +
                "}";
    }

    @Override
    public void printRatings() {
        logger.trace("Executing client.printRatings()");
        if (getRatings() == null) try {
            throw new NoRatingException(this);
        } catch (NoRatingException e) {
            logger.error(e.getMessage());
        }
        logger.info("Rating for client " + getName() + " " + getSurname());
        for (var rating : getRatings()){
            logger.info(rating);
        }
        logger.trace("Closing client.printRatings()");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return Objects.equals(getName(), client.getName()) &&
                Objects.equals(getSurname(), client.getSurname()) &&
                Objects.equals(getRatings(), client.getRatings()) &&
                Objects.equals(getPhone(), client.getPhone()
                );
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getName(), getSurname(), getPhone());
        result = 31 * result + Objects.hashCode(getRatings());
        return result;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
