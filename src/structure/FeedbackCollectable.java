package structure;

import structure.application.Rating;

public interface FeedbackCollectable {
    Rating askForFeedback(AppUser user);

}
