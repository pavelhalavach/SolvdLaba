package main.java.org.solvd.structure.application;

import main.java.org.solvd.structure.AppUser;
import main.java.org.solvd.structure.CustomLinkedList;
import main.java.org.solvd.structure.exceptions.IncorrectMarkException;
import main.java.org.solvd.structure.interfaces.FeedbackCollectable;
import main.java.org.solvd.structure.taxipark.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FeedbackOperator implements FeedbackCollectable {
    public FeedbackOperator(){
    }
    private static final Logger logger = LogManager.getLogger("taxi");
    private static final Logger loggerRoot = LogManager.getRootLogger();

    @Override
    public CustomLinkedList<Rating> collectFeedback(Client client, Driver driver){
        CustomLinkedList<Rating> ratings = new CustomLinkedList<>();
        ratings.add(askForFeedback(client));
        ratings.add(askForFeedback(driver));
        return ratings;
    }

    @Override
    public Rating askForFeedback(AppUser user){
        loggerRoot.trace("Executing FeedbackOperator.askForFeedback()");
        logger.info("Hello, "  + user.getName() + " " + user.getSurname());
        logger.info("Please give us a feedback about your drive");
        logger.info("Firstly, put your mark");
        Scanner input;
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
        loggerRoot.trace("Closing FeedbackOperator.askForFeedback()");

        return new Rating(comment, mark);
    }
}
