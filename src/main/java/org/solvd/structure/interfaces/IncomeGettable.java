package main.java.org.solvd.structure.interfaces;

import main.java.org.solvd.structure.application.Request;
import main.java.org.solvd.structure.exceptions.NoRequestsException;
import main.java.org.solvd.structure.taxipark.Assistant;

import java.util.Set;

public interface IncomeGettable {
    float getIncomeAfterBills(Set<Request> requests, Assistant assistant);
    void calculateIncome(Set<Request> requests, Assistant assistant) throws NoRequestsException;
}
