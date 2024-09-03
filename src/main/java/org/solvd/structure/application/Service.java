package org.solvd.structure.application;

import org.solvd.structure.AppUser;
import org.solvd.structure.FeedbackCollectable;
import org.solvd.structure.RatingPrintable;
import org.solvd.structure.taxipark.Driver;

import java.util.Scanner;

public class Service implements FeedbackCollectable {
    public Service(){
    }

    @Override
    public Rating[] collectFeedback(Client client, Driver driver){
        return new Rating[]{askForFeedback(client), askForFeedback(driver)};
    }

    @Override
    public Rating askForFeedback(AppUser user){
        System.out.println("Hello, "  + user.getName() + " " + user.getSurname());
        System.out.println("Please give us a feedback about your drive");
        System.out.println("First put your mark, then add some comments");
        Scanner input = new Scanner(System.in);
        int mark = input.nextInt();
        input.nextLine();
        String comment = input.nextLine();

        return new Rating(comment, mark);
    }

//    @Override
//    public void printRatings(){
//        System.out.println("Rating of driver " + driver.toString() + ":");
//        if (clientFeedback == null) System.out.println("there is no rating");
//        else {
//            System.out.println(clientFeedback.toString());
//        }
//        System.out.println("Rating of client " + client.toString() + ":");
//        if (driverFeedback == null) System.out.println("there is no rating");
//        else {
//            System.out.println(driverFeedback.toString());
//        }
//    }
}
