package main.java.org.solvd.structure.taxipark;

import main.java.org.solvd.structure.application.ClientDataOperator;
import main.java.org.solvd.structure.application.Request;
import main.java.org.solvd.structure.enums.ClientStatus;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class AdvertisingDepartment {
    private static final String message1 = "Only today %d%% off for >%d km drives!";
    private final ClientDataOperator clientDataOperator;
    private static final Logger logger = LogManager.getLogger("taxi");
    public AdvertisingDepartment() {
        this.clientDataOperator = new ClientDataOperator();
    }

    public void getClientDataUpdate(Set<Request> requests){
        this.clientDataOperator.updateClientsStatus(requests);
        this.clientDataOperator.saveClientData(requests);
    }



    public void sendPromotionToAll(Set<Request> requests) {
        getClientDataUpdate(requests);
        File file = new File("target/clientsData.txt");
        try (LineIterator it = FileUtils.lineIterator(file, "UTF-8")){
            while (it.hasNext()) {
                String data = it.nextLine();
                String phoneNumber = StringUtils.substringBefore(data, " ");
                String statusIndex = StringUtils.substringAfter(data, " ");
                this.sendPromotion(phoneNumber, statusIndex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendPromotion(String phoneNumber, String statusIndex){
        int promoPercentage = 5;
        String message = String.format(message1, promoPercentage*Integer.parseInt(statusIndex), 5);
        if (StringUtils.contains(phoneNumber, "+48")){
            logger.info("Sending " + "\"" + message + "\" " + "to " + phoneNumber + " using Polish operator");
        } else if (StringUtils.contains(phoneNumber, "+375")){
            logger.info("Sending " + "\"" + message + "\" " + "to " + phoneNumber + " using Belarusian operator");
        } else {
            logger.info("Sending " + "\"" + message + "\" " + "to " + phoneNumber + " using Worldwide operator");
        }
    }

    public ClientDataOperator getClientDataOperator() {
        return clientDataOperator;
    }
}
