package main.java.org.solvd.structure.application;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PhoneNumberOperator {
    public PhoneNumberOperator() {
    }

    public HashSet<String> getPhoneNumbers(Set<Request> requests){
        Iterator<Request> it = requests.iterator();
        HashSet<String> listOfPhones = new HashSet<>();
        while(it.hasNext()){
            Request copy = it.next();
            if (copy.getClient().getPhone() != null){
                listOfPhones.add(copy.getClient().getPhone());
            }
        }
        return listOfPhones;
    }

    public void savePhoneNumbers(Set<Request> requests){
        try {
            File file = new File("target/clientsPhones.txt");
            FileUtils.writeLines(file, getPhoneNumbers(requests));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
