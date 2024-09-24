package main.java.org.solvd.structure.interfaces;

import main.java.org.solvd.structure.application.Request;
import main.java.org.solvd.structure.exceptions.NoRequestsException;
import main.java.org.solvd.structure.taxipark.Assistant;

import java.util.Set;
import java.util.function.Supplier;

public interface IncomeGettable {
    float getIncomeAfterBills(Set<Request> requests, Assistant assistant);
//    void calculateIncome(Set<Request> requests, Assistant assistant) throws NoRequestsException;

    void calculateIncome(Set<Request> requests, Assistant assistant, Supplier<Float> supplier) throws NoRequestsException;
}
