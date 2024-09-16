package main.java.org.solvd.structure.interfaces;

import main.java.org.solvd.structure.AppUser;
import main.java.org.solvd.structure.CustomLinkedList;
import main.java.org.solvd.structure.application.Client;
import main.java.org.solvd.structure.application.Rating;
import main.java.org.solvd.structure.taxipark.Driver;

public interface FeedbackCollectable {
    CustomLinkedList<Rating> collectFeedback(Client client, Driver driver);
    Rating askForFeedback(AppUser user);

}
