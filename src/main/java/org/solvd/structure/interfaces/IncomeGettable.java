package main.java.org.solvd.structure.interfaces;

import main.java.org.solvd.structure.application.Request;
import main.java.org.solvd.structure.taxipark.Assistant;

public interface IncomeGettable {
    float getIncomeAfterBills(Request[] requests, Assistant assistant);
    void calculateIncome(Request[] requests, Assistant assistant);
}
