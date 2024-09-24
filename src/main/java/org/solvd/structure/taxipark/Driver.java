package main.java.org.solvd.structure.taxipark;

import main.java.org.solvd.structure.AppUser;
import main.java.org.solvd.structure.enums.DriverStatus;
import main.java.org.solvd.structure.exceptions.NoRatingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public final class Driver extends AppUser {
    private static float percentage = 0.7f;
    private Car car;
    private DriverStatus driverStatus;
    private static final Logger logger = LogManager.getLogger("taxi");
    private static final Logger loggerRoot = LogManager.getRootLogger();

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
    public void printRatings() {
        loggerRoot.trace("Executing driver.printRatings()");
        if (getRatings() == null) {
            try {
                throw new NoRatingException(this);
            } catch (NoRatingException e) {
                logger.error(e.getMessage());
            }
        }
        logger.info("Rating for driver " + getName() + " " + getSurname());
        getRatings().stream().forEach(rating -> logger.info(rating));
//        for (var rating : getRatings()){
//            logger.info(rating);
//        }
        loggerRoot.trace("Closing driver.printRatings()");
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

    public DriverStatus getDriverStatus() {
        updateDriverStatus();
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public void updateDriverStatus() {
        if (getRatings() == null) {
            this.driverStatus = null;
        } else {
            float avgRating = 0;
            if (avgRating >= DriverStatus.PERFECT.getMarkStage()){
                this.driverStatus = DriverStatus.PERFECT;
            } else if (avgRating >= DriverStatus.GREAT.getMarkStage()){
                this.driverStatus = DriverStatus.GREAT;
            } else if (avgRating >= DriverStatus.GOOD.getMarkStage()){
                this.driverStatus = DriverStatus.GOOD;
            } else if (avgRating >= DriverStatus.AVERAGE.getMarkStage()){
                this.driverStatus = DriverStatus.AVERAGE;
            } else if (avgRating >= DriverStatus.BAD.getMarkStage()){
                this.driverStatus = DriverStatus.BAD;
            } else {
                this.driverStatus = DriverStatus.TERRIBLE;
            }
        }
    }

}
