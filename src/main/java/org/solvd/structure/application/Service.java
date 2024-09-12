package main.java.org.solvd.structure.application;

import main.java.org.solvd.structure.AppUser;
import main.java.org.solvd.structure.exceptions.IncorrectMarkException;
import main.java.org.solvd.structure.taxipark.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Service //implements FeedbackCollectable
{
//    public Service(){
//    }
    private static final Logger logger = LogManager.getRootLogger();
    private static final Logger logger_err = LogManager.getLogger("errors");

//    @Override
    public static ArrayList<Rating> collectFeedback(Client client, Driver driver){
        ArrayList<Rating> ratings = new ArrayList<>();
        ratings.add(askForFeedback(client));
        ratings.add(askForFeedback(driver));
        return ratings;
    }

//    @Override
    public static Rating askForFeedback(AppUser user){
        logger_err.info("Executing Service.askForFeedback()");
        logger.info("Hello, "  + user.getName() + " " + user.getSurname());
        logger.info("Please give us a feedback about your drive");
        logger.info("Firstly, put your mark");
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
        logger.info("Then add some comments");
        input.nextLine();
        String comment = input.nextLine();
        logger_err.info("Closing Service.askForFeedback()");

        return new Rating(comment, mark);
    }
}
