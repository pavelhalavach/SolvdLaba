package main.java.org.solvd.structure.application;

import main.java.org.solvd.structure.AppUser;
import main.java.org.solvd.structure.exceptions.IncorrectMarkException;
import main.java.org.solvd.structure.interfaces.FeedbackCollectable;
import main.java.org.solvd.structure.taxipark.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Service //implements FeedbackCollectable
{
//    public Service(){
//    }
    private static final Logger logger = LogManager.getRootLogger();

//    @Override
    public static Rating[] collectFeedback(Client client, Driver driver){
        return new Rating[]{askForFeedback(client), askForFeedback(driver)};
    }

//    x@Override
    public static Rating askForFeedback(AppUser user){
        System.out.println("Hello, "  + user.getName() + " " + user.getSurname());
        System.out.println("Please give us a feedback about your drive");
        System.out.println("Firstly, put your mark");
        Scanner input = new Scanner(System.in);
        int mark;
        while(true){
            try{
                input = new Scanner(System.in);
                mark = input.nextInt();
                if (mark < 1 || mark > 5) throw new IncorrectMarkException();
                break;
            } catch (IncorrectMarkException e){
                logger.error(e.getMessage());

            } catch (InputMismatchException ex){
                logger.error(ex.getClass());
            }
        }
        System.out.println("Then add some comments");
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
