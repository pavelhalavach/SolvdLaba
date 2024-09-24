package main.java.org.solvd.structure.enums;

public enum DriverStatus {
    PERFECT(4.8f),//avg >4.8
    GREAT(4.5f),//>4.5 <4.8
    GOOD(4.2f), //>4.2
    AVERAGE(3.9f), //>3.9
    BAD(3), //>3
    TERRIBLE(0);
    private final float markStage;

    DriverStatus(float markStage){
        this.markStage = markStage;
    }

    public float getMarkStage() {
        return markStage;
    }
}
