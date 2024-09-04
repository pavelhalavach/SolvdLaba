package main.java.org.solvd.structure.interfaces;

import main.java.org.solvd.structure.application.Rating;
import main.java.org.solvd.structure.exceptions.NoRatingException;

public interface AvgRatingCalculable {
    void printAvgRating();
    float calculateAvgRating() throws NoRatingException;
}
