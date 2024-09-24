package main.java.org.solvd.structure;

import main.java.org.solvd.structure.application.Rating;
import main.java.org.solvd.structure.exceptions.NoRatingException;
import main.java.org.solvd.structure.interfaces.RatingOperatable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class AppUser extends Person implements RatingOperatable {
    private List<Rating> ratings;
    private static final Logger logger = LogManager.getLogger("taxi");
    private static final Logger loggerRoot = LogManager.getRootLogger();

    public AppUser(String name, String surname) {
        super(name, surname);
    }
    @Override
    public void printAvgRating() {
        loggerRoot.trace("Executing AppUser.printAvgRating()");
        try {
            logger.info("The average rating is " + calculateAvgRating());
        } catch (NoRatingException e) {
            logger.error(e.getMessage());
        }
        loggerRoot.trace("Closing AppUser.printAvgRating()");
    }

    @Override
    public final float calculateAvgRating() throws NoRatingException {
        if (ratings == null) {
            throw new NoRatingException();
        }
//        int sum = 0;
//        for (var rating : ratings) {
//            sum += rating.getMark();
//        }
        return (float) ratings
                .stream()
                .mapToInt(rating -> rating.getMark())
                .average().getAsDouble()                ;
    }

    @Override
    public final void addRating(Rating rating) {
        if (this.ratings == null) this.ratings = new ArrayList<>();
        this.ratings.add(rating);
    }

    @Override
    public abstract void printRatings();


    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
