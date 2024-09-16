package main.java.org.solvd.structure.taxipark;

import main.java.org.solvd.structure.application.PhoneNumberOperator;
import main.java.org.solvd.structure.application.Request;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class AdvertisingDepartment {
    private final PhoneNumberOperator phoneNumberOperator;
    public AdvertisingDepartment() {
        this.phoneNumberOperator = new PhoneNumberOperator();
    }

    private static final Logger logger = LogManager.getLogger("taxi");

    public void requestPhoneNumbers(Set<Request> requests){
        phoneNumberOperator.savePhoneNumbers(requests);
    }

    public void sendPromotionToAll(String message) {
        File file = new File("target/clientsPhones.txt");
        try (LineIterator it = FileUtils.lineIterator(file, "UTF-8")){
            while (it.hasNext()) {
                String phoneNumber = it.nextLine();
                this.sendPromotion(phoneNumber, message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendPromotion(String phoneNumber, String message){
        if (StringUtils.contains(phoneNumber, "+48")){
            logger.info("Sending " + "\"" + message + "\" " + "to " + phoneNumber + " using Polish operator");
        } else if (StringUtils.contains(phoneNumber, "+375")){
            logger.info("Sending " + "\"" + message + "\" " + "to " + phoneNumber + " using Belarusian operator");
        } else {
            logger.info("Sending " + "\"" + message + "\" " + "to " + phoneNumber + " using Worldwide operator");
        }
    }

    public PhoneNumberOperator getPhoneNumberOperator() {
        return phoneNumberOperator;
    }
}
