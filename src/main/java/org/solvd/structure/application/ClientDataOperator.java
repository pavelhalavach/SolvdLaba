package main.java.org.solvd.structure.application;

import main.java.org.solvd.structure.enums.ClientStatus;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ClientDataOperator {
    public ClientDataOperator() {
    }

    public HashSet<String> getClientData(Set<Request> requests){
        Iterator<Request> it = requests.iterator();
        HashSet<String> listOfPhonesAndStatuses = new HashSet<>();
        while(it.hasNext()){
            Request copy = it.next();
            if (copy.getClient().getPhone() != null){
                listOfPhonesAndStatuses.add(copy.getClient().getPhone() +
                        " " + copy.getClient().getClientStatus().getIndex());
            }
        }
        return listOfPhonesAndStatuses;
    }

    public void saveClientData(Set<Request> requests){
        try {
            File file = new File("target/clientsData.txt");
            FileUtils.writeLines(file, getClientData(requests));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateClientsStatus(Set<Request> requests) {
        Iterator<Request> it = requests.iterator();
        while (it.hasNext()) {
            Request copy = it.next();
            if (copy.getClient().getMoneySpent() < ClientStatus.BASIC.getMoneySpentLevel()) {
                copy.getClient().setClientStatus(ClientStatus.BASIC);
            } else if (copy.getClient().getMoneySpent() < ClientStatus.SILVER.getMoneySpentLevel()) {
                copy.getClient().setClientStatus(ClientStatus.SILVER);
            } else if (copy.getClient().getMoneySpent() < ClientStatus.GOLD.getMoneySpentLevel()) {
                copy.getClient().setClientStatus(ClientStatus.GOLD);
            } else {
                copy.getClient().setClientStatus(ClientStatus.PLATINUM);
            }
        }
    }
}
