package main.java.org.solvd.structure.application;

import main.java.org.solvd.structure.AppUser;
import main.java.org.solvd.structure.CustomLinkedList;
import main.java.org.solvd.structure.exceptions.IncorrectMarkException;
import main.java.org.solvd.structure.interfaces.FeedbackCollectable;
import main.java.org.solvd.structure.taxipark.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FeedbackOperator implements FeedbackCollectable {
    public FeedbackOperator(){
    }
    private static final Logger logger = LogManager.getLogger("taxi");
    private static final Logger loggerRoot = LogManager.getRootLogger();

    @Override
    public CustomLinkedList<Rating> collectFeedback(Client client, Driver driver){
        CustomLinkedList<Rating> ratings = new CustomLinkedList<>();
        ratings.add(askForFeedback(client, mark -> mark < 1 || mark > 5, (Consumer<AppUser>) user -> {
            loggerRoot.trace("Executing FeedbackOperator.askForFeedback()");
            logger.info("Hello, client "  + user.getName() + " " + user.getSurname());
            logger.info("Please give us a feedback about your driver");
            logger.info("Firstly, put your mark");
        }));
        ratings.add(askForFeedback(driver, mark -> mark < 1 || mark > 5, (Consumer<AppUser>) user -> {
            loggerRoot.trace("Executing FeedbackOperator.askForFeedback()");
            logger.info("Hello, driver "  + user.getName() + " " + user.getSurname());
            logger.info("Please give us a feedback about your client");
            logger.info("Firstly, put your mark");
        }));
        return ratings;
    }

    @Override
    public Rating askForFeedback(AppUser user, Predicate<Integer> tester, Consumer<AppUser> block){
        block.accept(user);
        Scanner input = new Scanner(System.in);
        int mark;
        while(true){
            try{
                mark = input.nextInt();
                if (tester.test(mark)) throw new IncorrectMarkException();
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
