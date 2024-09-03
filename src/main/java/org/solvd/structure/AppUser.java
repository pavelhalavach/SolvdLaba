package org.solvd.structure;

import org.solvd.structure.application.Rating;
import org.solvd.structure.interfaces.RatingOperatable;

public abstract class AppUser extends Person implements RatingOperatable {
    private Rating[] ratings;
    public AppUser(String name, String surname) {
        super(name, surname);
    }

//    public abstract Rating giveFeedback(AppUser user);

    @Override
    public final float calculateAvgRating(){
        if (ratings == null) {
            System.out.println("there is no rating");
        }
        int sum = 0;
        for (var rating : ratings){
            sum += rating.getMark();
        }
        return (float) (sum / ratings.length);
    }

    @Override
    public final void addRating(Rating rating){
        if (this.ratings == null) {
            this.ratings = new Rating[]{rating};
        } else {
            Rating[] newRatings = new Rating[this.ratings.length+1];
            for(int i = 0; i < this.ratings.length; i++){
                newRatings[i] = this.ratings[i];
            }
            newRatings[this.ratings.length] = rating;
            this.ratings = newRatings;
        }
    }

    @Override
    public final void printRatings(){
        if (ratings == null) System.out.println("there is no rating");
        else {
            System.out.println("Rating for " + getName() + " " + getSurname());
            for (int i = 0; i < ratings.length; i++){
                System.out.println(ratings[i].toString());
            }
        }
    }

    public Rating[] getRatings() {
        return ratings;
    }

    public void setRatings(Rating[] ratings) {
        this.ratings = ratings;
    }
}
