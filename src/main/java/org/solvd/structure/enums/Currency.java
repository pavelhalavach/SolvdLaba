package main.java.org.solvd.structure.enums;

public enum Currency {
    USD("$"),
    EUR("€"),
    PLN("zł");

    private final String symbol;

    Currency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
