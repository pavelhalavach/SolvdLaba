package org.solvd.structure;

import org.solvd.structure.application.Request;
import org.solvd.structure.taxipark.Assistant;

public interface IncomeGettable {
    float getIncomeAfterBills(Request[] requests, Assistant assistant);
    void calculateIncome(Request[] requests, Assistant assistant);
}
