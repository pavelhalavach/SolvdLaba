package main.java.org.solvd.structure.taxipark;

import main.java.org.solvd.structure.exceptions.NoRequestsException;
import main.java.org.solvd.structure.interfaces.IncomeGettable;
import main.java.org.solvd.structure.application.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

public class Accounting implements IncomeGettable {
    private static float bill;
    static {
        bill = 0.26f;
    }
    private float income;
    private static final Logger logger = LogManager.getLogger("taxi");
    private static final Logger loggerRoot = LogManager.getRootLogger();

    protected Accounting() {
    }

    @Override
    public float getIncomeAfterBills(Set<Request> requests, Assistant assistant){
        loggerRoot.trace("Executing Accounting.getIncomeAfterBills()");
        try {
            calculateIncome(requests, assistant, () -> Driver.getPercentage());
        } catch (NoRequestsException e) {
            logger.error(e.getMessage());
        }
        loggerRoot.trace("Closing Accounting.getIncomeAfterBills()");
        return income * (1 - bill);
    }

    @Override
    public void calculateIncome(Set<Request> requests, Assistant assistant, Supplier<Float> supplier) throws NoRequestsException {
        float income = assistant.getSalary();
        if (requests == null){
            this.income = income;
            throw new NoRequestsException();
        }
        else{
            this.income = (float) requests.stream()
                    .mapToDouble(request -> request.getPrice() * (1 - supplier.get()))
                    .sum();
//            for (var request : requests){
//                income += request.getPrice() * (1 - supplier.get());
//            }
//            this.income = income;
        }
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
