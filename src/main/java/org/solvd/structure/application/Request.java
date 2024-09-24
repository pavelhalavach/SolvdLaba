package main.java.org.solvd.structure.application;

import main.java.org.solvd.structure.CustomLinkedList;
import main.java.org.solvd.structure.enums.Currency;
import main.java.org.solvd.structure.exceptions.NoRatingException;
import main.java.org.solvd.structure.interfaces.RatingPrintable;
import main.java.org.solvd.structure.taxipark.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Request implements RatingPrintable {
    private static final float multiplier = 8;
    private static final float multiplierUSD = 8 / 3.84f;
    private static final float multiplierEUR = 8 / 4.28f;
    private float price;
    private Currency priceCurrency;
    private Client client;
    private Driver driver;
    private Path path;
    private LocalDateTime date;
    private Rating driverFeedback;
    private Rating clientFeedback;
    private final FeedbackOperator feedbackOperator;
    private static final Logger logger = LogManager.getLogger("taxi");
    private static final Logger loggerRoot = LogManager.getRootLogger();

    public Request(Client client, Driver driver, Path path, Currency priceCurrency, boolean wantFeedback) {
        this.client = client;
        this.driver = driver;
        this.path = path;
        this.date = LocalDateTime.now();
        this.feedbackOperator = new FeedbackOperator();
        this.priceCurrency = priceCurrency;
        setPrice();
        this.client.setMoneySpent(getPrice());
        if (wantFeedback) {
            logger.info(this);
            setFeedback();
        }
        this.driver.updateDriverStatus();
    }

    public void setFeedback(){
        List<Rating> ratings = feedbackOperator.collectFeedback(client, driver);
        clientFeedback = ratings.get(0);
        driverFeedback = ratings.get(1);
        client.addRating(driverFeedback);
        driver.addRating(clientFeedback);
    }

    @Override
    public void printRatings(){
        loggerRoot.trace("Executing request.printRatings()");
        logger.info("Rating of driver " + driver + ":");
        try {
            if (clientFeedback == null)
                throw new NoRatingException();
            logger.info(clientFeedback);
        } catch (NoRatingException e) {
            logger.error(e.getMessage() + driver);
        }
        logger.info("Rating of client " + client + ":");
        try {
            if (driverFeedback == null)
                throw new NoRatingException();
            logger.info(driverFeedback);
        } catch (NoRatingException e) {
            logger.error(e.getMessage() + client);
        }
        loggerRoot.trace("Closing request.printRatings()");
    }

    @Override
    public String toString() {
        return "Request{" +
                "price=" + price +
                ", priceCurrency=" + priceCurrency.getSymbol() +
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
        return Float.compare(price, request.price) == 0 &&
                Objects.equals(client, request.client) &&
                Objects.equals(driver, request.driver) &&
                Objects.equals(path, request.path) &&
                Objects.equals(date, request.date) &&
                Objects.equals(priceCurrency, request.priceCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, priceCurrency, client, driver, path, date);
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    private void setPrice() {
        if (priceCurrency == Currency.USD){
            this.price = this.path.getDistance() * multiplierUSD;
        }else if(priceCurrency == Currency.EUR){
            this.price = this.path.getDistance() * multiplierEUR;
        }else {
            this.price = this.path.getDistance() * multiplier;
        }
    }

    public Currency getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(Currency priceCurrency) {
        this.priceCurrency = priceCurrency;
    }
}
