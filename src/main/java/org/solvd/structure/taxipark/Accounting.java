package main.java.org.solvd.structure.taxipark;

import main.java.org.solvd.structure.interfaces.IncomeGettable;
import main.java.org.solvd.structure.application.Request;

import java.util.Objects;

public class Accounting implements IncomeGettable {
    private static float bill;
    static {
        bill = 0.26f;
    }
    private float income;

    protected Accounting() {
    }

    @Override
    public float getIncomeAfterBills(Request[] requests, Assistant assistant){
        calculateIncome(requests, assistant);
        return income * bill;
    }

    @Override
    public void calculateIncome(Request[] requests, Assistant assistant) {
        float income = 0;
        for (var request : requests){
            income += request.getPrice() * (1 - Driver.getPercentage());
        }
        income += assistant.getSalary();
        this.income = income;
    }

    @Override
    public String toString() {
        return "Accounting{" +
                "income=" + income +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounting that = (Accounting) o;
        return Float.compare(income, that.income) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(income);
    }

    public static float getBill() {
        return bill;
    }

    public static void setBill(float bill) {
        Accounting.bill = bill;
    }

    public float getIncome() {
        return income;
    }

//    HAVE TO CHANGE IT
    public void setIncome(float income) {
        this.income = income;
    }
}
