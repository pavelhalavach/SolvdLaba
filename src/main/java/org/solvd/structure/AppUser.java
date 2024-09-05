package main.java.org.solvd.structure;

import main.java.org.solvd.structure.application.Rating;
import main.java.org.solvd.structure.exceptions.NoRatingException;
import main.java.org.solvd.structure.interfaces.RatingOperatable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public abstract class AppUser extends Person implements RatingOperatable {
    //    private Rating[] ratings;
    private ArrayList<Rating> ratings;
    private static final Logger logger = LogManager.getRootLogger();
    private static final Logger logger_err = LogManager.getLogger("errors");

    public AppUser(String name, String surname) {
        super(name, surname);
    }

    //    public abstract Rating giveFeedback(AppUser user);
    @Override
    public void printAvgRating() {
        logger_err.info("Executing AppUser.printAvgRating()");
        try {
            logger.info("The average rating is " + calculateAvgRating());
        } catch (NoRatingException e) {
            logger.error(e.getMessage());
        }
        logger_err.info("Closing AppUser.printAvgRating()");
    }

    @Override
    public final float calculateAvgRating() throws NoRatingException {
        if (ratings == null) {
            throw new NoRatingException();
        }
        int sum = 0;
        for (var rating : ratings) {
            sum += rating.getMark();
        }
        return (float) (sum / ratings.size());
    }

    @Override
    public final void addRating(Rating rating) {
        if (this.ratings == null) this.ratings = new ArrayList<>();
        this.ratings.add(rating);
    }

    @Override
    public abstract void printRatings() throws NoRatingException;


    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }
}
