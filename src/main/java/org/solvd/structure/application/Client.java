package main.java.org.solvd.structure.application;

import main.java.org.solvd.structure.AppUser;
import main.java.org.solvd.structure.enums.ClientStatus;
import main.java.org.solvd.structure.exceptions.NoRatingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public final class Client extends AppUser {
    private String phone;
    private ClientStatus clientStatus;
    private float moneySpent;
    private static final Logger logger = LogManager.getLogger("taxi");
    private static final Logger loggerRoot = LogManager.getRootLogger();

    public Client(String name, String surname, String phone) {
        super(name, surname);
        this.phone = phone;
        this.clientStatus = ClientStatus.BASIC;
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
        loggerRoot.trace("Executing client.printRatings()");
        if (getRatings() == null) try {
            throw new NoRatingException(this);
        } catch (NoRatingException e) {
            logger.error(e.getMessage());
        }
        logger.info("Rating for client " + getName() + " " + getSurname());
        getRatings().stream().forEach(rating -> logger.info(rating));
//        for (var rating : getRatings()){
//            logger.info(rating);
//        }
        loggerRoot.trace("Closing client.printRatings()");
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

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }

    public float getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(float moneySpent) {
        this.moneySpent += moneySpent;
    }
}
