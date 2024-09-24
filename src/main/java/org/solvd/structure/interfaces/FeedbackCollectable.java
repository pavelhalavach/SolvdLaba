package main.java.org.solvd.structure.interfaces;

import main.java.org.solvd.structure.AppUser;
import main.java.org.solvd.structure.CustomLinkedList;
import main.java.org.solvd.structure.application.Client;
import main.java.org.solvd.structure.application.Rating;
import main.java.org.solvd.structure.taxipark.Driver;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface FeedbackCollectable {
    CustomLinkedList<Rating> collectFeedback(Client client, Driver driver);

//    Rating askForFeedback(AppUser user, Predicate<Integer> tester);

    Rating askForFeedback(AppUser user, Predicate<Integer> tester, Consumer<AppUser> block);
}
