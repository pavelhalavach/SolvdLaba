package main.java.org.solvd.structure.enums;

public enum ClientStatus {
    BASIC(50, 1),
    SILVER(200, 2),
    GOLD(500, 3),
    PLATINUM(100000000, 4);

    private final int moneySpentLevel;
    private final int index;

    ClientStatus(int moneySpentLevel, int index) {
        this.moneySpentLevel = moneySpentLevel;
        this.index = index;
    }

    public int getMoneySpentLevel() {
        return moneySpentLevel;
    }

    public int getIndex() {
        return index;
    }
}
