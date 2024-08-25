package structure;

import structure.application.Rating;

public abstract class AppUser extends Person implements RatingPrintable{
    protected Rating[] ratings;
    public AppUser(String name, String surname) {
        super(name, surname);
    }

    public abstract Rating giveFeedback(AppUser user);

    public void addRating(Rating rating){
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
    public void printRatings(){
        if (ratings == null) System.out.println("there is no rating");
        else {
            System.out.println("Rating for " + name + " " + surname);
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
