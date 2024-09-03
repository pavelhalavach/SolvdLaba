package org.solvd.structure.interfaces;

import org.solvd.structure.AppUser;
import org.solvd.structure.application.Client;
import org.solvd.structure.application.Rating;
import org.solvd.structure.taxipark.Driver;

public interface FeedbackCollectable {
    Rating[] collectFeedback(Client client, Driver driver);
    Rating askForFeedback(AppUser user);

}
